package prev26lang;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;

import prev26lang.common.report.*;
import prev26lang.phase.asmgen.Instr;
import prev26lang.phase.lexan.*;
import prev26lang.phase.livean.AssemFlowGraph;
import prev26lang.phase.livean.Liveness;
import prev26lang.phase.regall.Regall;
import prev26lang.phase.synan.*;
import prev26lang.phase.abstr.*;
import prev26lang.phase.seman.*;
import prev26lang.phase.memory.*;
import prev26lang.phase.imrgen.*;
import prev26lang.phase.imrlin.*;

import javax.print.DocFlavor;

import static prev26lang.phase.regall.Regall.allRegs;
import static prev26lang.phase.regall.Regall.okColors;

/**
 * The Prev26 compiler.
 *
 *
 */
public class Compiler {
    /** (Default) values of command line options. */
    private static final HashMap<String, String> cmdLineOpts = new HashMap<String, String>(Map.ofEntries( //
            Map.entry("--src-file-name", ""), // ---: the name of the source file
            Map.entry("--dst-file-name", ""), // ---: the name of the destination file
            Map.entry("--target-phase", "all"), // -: the name of the last performed phase
            Map.entry("--logged-phase", "all"), // -: the name of the logged phase (or none or all)
            Map.entry("--xml", ""), // -------------: the name of xml files (without phase name and extension)
            Map.entry("--xsl", "../lib/xsl/"), // --: the directory with the xsl templates for the logs
            Map.entry("--dev-mode", "on"), // -------: development mode (on or off)
            Map.entry("--num-regs", "") // ---: number of physical regs to be allocated
    ));

    /** All valid phases name of the compiler. */
    private static final Vector<String> phaseNames = new Vector<String>(Arrays.asList( //
            "none", // ---: no phase
            "lexan", // --: lexical analysis
            "synan", // --: syntax analysis
            "abstr", // --: abstract syntax tree
            "seman", // --: semantic analysis
            "memory", // --: memory layout
            "imrgen", // --: generation of intermediate representation
            "imrlin", // --: linearization of intermediate representation
            "asmgen", //--: instruction selection
            "livean", //--: liveness analysis
            "regall" //--: register allocation
    ));
    // za lepsi print def in use
    public static String formatTemps(HashSet<MEM.Temp> temps, MEM.Frame frame) {
        if (temps == null) return "null";
        StringBuilder sb = new StringBuilder("[");
        for (MEM.Temp t : temps) {
            String mapped = frame.tempMap(t);
            sb.append(mapped != null ? mapped : t.toString()).append(", ");
        }
        if (!temps.isEmpty()) sb.setLength(sb.length() - 2);
        sb.append("]");
        return sb.toString();
    }
    /** Specifies whether the compiler is run in the development mode. */
    private static boolean devMode = false;

    /**
     * Returns information on whether the compiler is run in the development mode.
     *
     * @return {@code true} if the compiler is run in the development mode,
     *         {@code false} otherwise.
     */
    public static final boolean devMode() {
        return devMode;
    }

    /**
     * Returns the value of a command line option.
     *
     * @param cmdLineOptName Command line option name.
     * @return Command line option value.
     */
    public static final String cmdLineOptValue(final String cmdLineOptName) {
        String cmdLineOptValue = cmdLineOpts.get(cmdLineOptName);
        if (cmdLineOptValue == null)
            throw new Report.InternalError();
        return cmdLineOpts.get(cmdLineOptName);
    }

    /** (Unused but included to keep javadoc happy.) */
    private Compiler() {
        throw new Report.InternalError();
    }

    /**
     * The compiler's main driver running all phases one after another.
     *
     * @param cmdLineArgs Command line arguments (see {@link prev26lang}).
     */
    public static void main(final String[] cmdLineArgs) {
        try {
            Report.info("This is Prev26 compiler:");

            // Scan the command line.
            {
                HashSet<String> usedCmdLineOpts = new HashSet<String>();
                for (int argc = 0; argc < cmdLineArgs.length; argc++) {
                    if (cmdLineArgs[argc].startsWith("--")) {
                        // Command line option.
                        final String cmdLineOptName = cmdLineArgs[argc].replaceFirst("=.*", "");
                        final String cmdLineOptValue = cmdLineArgs[argc].replaceFirst("^[^=]*=", "");
                        if (!cmdLineOpts.keySet().contains(cmdLineOptName)) {
                            Report.warning("Unknown command line option '" + cmdLineOptName + "'.");
                            continue;
                        }
                        if (!usedCmdLineOpts.contains(cmdLineOptName)) {
                            // Not yet successfully specified command line option.

                            // Check the value of the command line option.
                            if (cmdLineOptName.equals("--target-phase") && (!phaseNames.contains(cmdLineOptValue))) {
                                Report.warning("Illegal phase specification in '" + cmdLineArgs[argc] + "' ignored.");
                                continue;
                            }
                            if (cmdLineOptName.equals("--logged-phase") && (!phaseNames.contains(cmdLineOptValue))) {
                                Report.warning("Illegal phase specification in '" + cmdLineArgs[argc] + "' ignored.");
                                continue;
                            }
                            if (cmdLineOptName.equals("--dev-mode") && (!cmdLineOptValue.matches("on|off"))) {
                                Report.warning("Illegal value in '" + cmdLineArgs[argc] + "' ignored.");
                                continue;
                            }
                            if (cmdLineOptName.equals("--num-regs")) {
                                try  {
                                    int num = Integer.parseInt(cmdLineOptValue);
                                    if (num > 25) {
                                        throw new Report.Error("Cannot have more than 25 (general-purpose) registers. You entered: "+num+".");
                                    } else if (num <= 0) {
                                        throw new Report.Error("Cannot have 0 or less registers. You entered: " + num + ".");
                                    }
                                    // set the number of physical register
                                    Regall.K = num;
                                } catch (NumberFormatException e) {
                                    Report.warning("Illegal value: '"+cmdLineOptValue+"'in '" + cmdLineArgs[argc] + "' ignored.");
                                }
                            }

                            cmdLineOpts.put(cmdLineOptName, cmdLineOptValue);
                            usedCmdLineOpts.add(cmdLineOptValue);
                        } else {
                            // Repeated specification of a command line option.
                            Report.warning("Command line option '" + cmdLineArgs[argc] + "' ignored.");
                            continue;
                        }
                    } else {
                        // Source file name.
                        if (cmdLineOpts.get("--src-file-name") == "") {
                            cmdLineOpts.put("--src-file-name", cmdLineArgs[argc]);
                            usedCmdLineOpts.add("--src-file-name");
                        } else {
                            Report.warning("Source file '" + cmdLineArgs[argc] + "' ignored.");
                            continue;
                        }
                    }
                }
            }
            // Check the command line option values.
            {
                // Source file name.
                if (cmdLineOpts.get("--src-file-name") == "") {
                    try {
                        // Source file has not been specified, so consider using the last modified
                        // p26 file in the working directory.
                        final String currWorkDir = new File(".").getCanonicalPath();
                        FileTime latestTime = FileTime.fromMillis(0);
                        Path latestPath = null;
                        for (final Path path : java.nio.file.Files.walk(Paths.get(currWorkDir))
                                .filter(path -> path.toString().endsWith(".p26")).toArray(Path[]::new)) {
                            final FileTime time = Files.getLastModifiedTime(path);
                            if (time.compareTo(latestTime) > 0) {
                                latestTime = time;
                                latestPath = path;
                            }
                        }
                        if (latestPath != null) {
                            cmdLineOpts.put("--src-file-name", latestPath.toString());
                            Report.warning("Source file not specified, using '" + latestPath.toString() + "'.");
                        }
                    } catch (final IOException __) {
                        throw new Report.Error("Source file not specified.");
                    }

                    if (cmdLineOpts.get("--src-file-name") == "") {
                        throw new Report.Error("Source file not specified.");
                    }
                }
                // Destination file name.
                if (cmdLineOpts.get("--dst-file-name") == "") {
                    cmdLineOpts.put("--dst-file-name",
                            cmdLineOpts.get("--src-file-name").replaceFirst("\\.[^./]*$", ".asm"));
                }
                if (cmdLineOpts.get("--xml") == "") {
                    cmdLineOpts.put("--xml", cmdLineOpts.get("--src-file-name").replaceFirst("\\.[^./]*$", ""));
                }
                // Development mode (on or off).
                switch (cmdLineOpts.get("--dev-mode")) {
                    case "on":
                        devMode = true;
                        break;
                    case "off":
                        devMode = false;
                        break;
                    default:
                        Report.warning("Illegal value '" + cmdLineOpts.get("--devMode") + "' for --dev-mode switch.");
                        break;
                }
            }

            // Carry out the compilation phase by phase.
            {
                while (true) {

                    if (cmdLineOpts.get("--target-phase").equals("none"))
                        break;

                    // === LEXICAL ANALYSIS ===
                    if (cmdLineOpts.get("--target-phase").equals("lexan")) {
                        try (final LexAn lexan = new LexAn()) {
                            // Read all tokens one by one.
                            while (lexan.lexer.nextToken().getType() != LexAn.LocLogToken.EOF) {
                            }
                        }
                        break;
                    } else {
                        // Unless the lexical analysis is not the last phase to be performed, it is
                        // performed during the syntax analysis.
                    }

                    // === SYNTAX ANALYSIS ===
                    try (LexAn lexan = new LexAn(); SynAn synan = new SynAn(lexan)) {
                        SynAn.tree = synan.parser.source();
                        synan.log(SynAn.tree);
                    }
                    if (cmdLineOpts.get("--target-phase").equals("synan"))
                        break;

                    // === ABSTRACT SYNTAX ===
                    try (Abstr abstr = new Abstr()) {
                        Abstr.tree = SynAn.tree.ast;
                        Abstr.locAttr.lock();
                        SynAn.tree = null;
                        (new Abstr.Logger(abstr.xmlLogger)).visit(Abstr.tree);
                    }
                    if (cmdLineOpts.get("--target-phase").equals("abstr"))
                        break;

                    // === SEMANTIC ANALYSIS ===
                    TypeConstructor tc;
                    try (SemAn seman = new SemAn()) {
                        (new NameResolver()).visit(Abstr.tree);
                        tc = new TypeConstructor();
                        tc.visit(Abstr.tree);
                        TypeChecker checker = new TypeChecker(tc);
                        checker.visit(Abstr.tree);
                        checker.checkMain(Abstr.tree);
                        SemAn.defAtAttr.lock();
                        SemAn.ofTypeAttr.lock();
                        SemAn.isTypeAttr.lock();
                        SemAn.isConstAttr.lock();
                        SemAn.isAddrAttr.lock();
                        (new SemAn.Logger(seman.xmlLogger)).visit(Abstr.tree);
                    }
                    if (cmdLineOpts.get("--target-phase").equals("seman"))
                        break;
                    // === MEMORY LAYOUT ===
                    try (Memory memory = new Memory()) {
                        (new Layouter()).visit(Abstr.tree);
                        Memory.frameAttr.lock();
                        Memory.accessAttr.lock();
                        Memory.stringAttr.lock();
                        (new Memory.Logger(memory.xmlLogger)).visit(Abstr.tree);
                    }
                    if (cmdLineOpts.get("--target-phase").equals("memory"))
                        break;

                    // === GENERATION OF INTERMEDIATE REPRESENTATION ===
                    try (ImrGen imrGen = new ImrGen()) {
                        (new ImrGenerator(tc)).visit(Abstr.tree);
                        (new ImrGen.Logger(imrGen.xmlLogger)).visit(Abstr.tree);
                    }
                    if (cmdLineOpts.get("--target-phase").equals("imrgen"))
                        break;
                    // === LINEARIZATION OF INTERMEDIATE REPRESENTATION ===
                    ImrLin.linearize(Abstr.tree);
                    Vector<LIN.DataChunk> dataChunks = new Vector<>();
                    Vector<LIN.CodeChunk> codeChunks = new Vector<>(); // kot jedro funkcije (pred trace schedule)
                    for (LIN.Chunk c : ImrLin.chunks) {
                        if (c instanceof LIN.DataChunk dc)
                            dataChunks.add(dc);
                        else if (c instanceof LIN.CodeChunk cc)
                            codeChunks.add(cc);
                    }
                    Vector<LIN.CodeChunk> tracedChunks = new Vector<>(); //jedro funkcije oz. sled
                    for (LIN.CodeChunk c : codeChunks) {
                        BasicBlocks b = new BasicBlocks(c.stmts(), c.exitLabel);
                        /*
                        System.out.println("===== "+c.frame.label.name + "'s basic blocks and trace schedule: =====");
                        System.out.println();
                        for (int i = 0; i < b.blocks.size(); i++) {
                            System.out.println("Basic block no. "+ i);
                            for (IMR.Stmt s : b.blocks.get(i)) {
                                System.out.println(s);
                            }
                            System.out.println();
                        }
                        System.out.println();*/
                        // trace schedule
                        TraceSchedule ts = new TraceSchedule(b, c.entryLabel);
                        tracedChunks.add(new LIN.CodeChunk(c.frame, ts.stmts, c.entryLabel, c.exitLabel));
                        /*System.out.println("Trace scheduled:");

                        for (IMR.Stmt s : ts.stmts) {
                            System.out.println(s);
                        }
                        System.out.println();
                        System.out.println();*/
                    }

                    //Interpreter interp = new Interpreter(dataChunks, tracedChunks);
                    //long result = interp.run("_main");
                    //System.out.println("\nInterpreted result: " + result);
                    if (cmdLineOpts.get("--target-phase").equals("imrlin"))
                        break;

                    // === INSTRUCTION SELECTION ===
                    HashMap<LIN.CodeChunk, Vector<Instr>> map = new HashMap<>(); //vsako jedro funkcije slika v seznam ukazov
                    for (LIN.CodeChunk c : tracedChunks) {
                        Vector<Instr> instrs = c.frame.codegen(new IMR.STMTS(c.stmts()));
                        // clear double labels
                        /*
                        Vector<Instr> newInstrs = new Vector<>();
                        for (int i = 0; i < instrs.size(); i++) {
                            Instr instr1 = instrs.get(i);
                            if (i < instrs.size() - 1) {
                                Instr instr2 = instrs.get(i + 1);
                                if (instr1 instanceof LABEL l1 && instr2 instanceof LABEL l2 && l1.label.name.equals(l2.label.name)) {
                                    continue;
                                }
                            }
                            newInstrs.add(instr1);
                        }*/
                        map.put(c, instrs); /*
                        System.out.println("===== " + c.frame.label.name + " =====");
                        for (Instr instr : instrs) {
                            if(instr instanceof LABEL) {
                                System.out.println(instr.format(c.frame) + "        # def: {" + instr.def() + "} use: {" + instr.use() + "} jumps: {" + instr.jumps()+"}");
                            } else {
                                System.out.println("    " + instr.format(c.frame) + "       # def: {" + instr.def() + "} use: {" + instr.use() + "} jumps: {" + instr.jumps()+"}");
                            }
                        }
                        System.out.println();
                        */
                    }

                    if (cmdLineOpts.get("--target-phase").equals("asmgen"))
                        break;
                    // === LIVENESS ANALYSIS ===
                    for (LIN.CodeChunk c : tracedChunks) {
                        //System.out.println("===== " + c.frame.label.name + " =====");
                        AssemFlowGraph cfg =  new AssemFlowGraph(map.get(c));
                        Liveness liveness = new Liveness(cfg, c.frame);
                        /*for (Instr instr : map.get(c)) {
                            Node n = cfg.instrMap.get(instr);
                            //System.out.println("{ " + formatTemps(liveness.in.get(n), c.frame) + " }");
                            if (instr instanceof LABEL) {
                                System.out.println(instr.format(c.frame)); //+ "            # def: {" + formatTemps(instr.def() != null ? new HashSet<>(instr.def()) : null, c.frame) + "}    use: {" + formatTemps(instr.use() != null ? new HashSet<>(instr.use()) : null, c.frame) + "}      jumps: {" + instr.jumps()+"}");
                            } else {
                                System.out.println("    " + instr.format(c.frame));// + "               # def: {" + formatTemps(instr.def() != null ? new HashSet<>(instr.def()) : null, c.frame) + "}    use: {" + formatTemps(instr.use() != null ? new HashSet<>(instr.use()) : null, c.frame) + "}      jumps: {" + instr.jumps()+"}");
                            }
                        }
                        Node last = cfg.instrMap.get(map.get(c).lastElement());
                        System.out.println("{ "+liveness.in.get(last)+ " }");
                        System.out.println();
                        System.out.println("Interference graph: ");
                        liveness.show();
                        System.out.println();*/
                    }
                    if (cmdLineOpts.get("--target-phase").equals("livean"))
                        break;
                    // === REGISTER ALLOCATION ===
                    // we register allocate for each function body separately
                    // setup the number of allowed colors based on --num-regs or use default value 25
                    int count = 0;
                    okColors.clear();
                    for (String r : allRegs) {
                        if (count == Regall.K) {
                            break;
                        }
                        okColors.add(r);
                        count++;
                    }
                    PrintWriter out = new PrintWriter(new FileWriter(cmdLineOpts.get("--dst-file-name")));
                    // data segment - dodamo globalne/statične spremenljivke
                    out.println(".data");
                    out.println("_heap:");
                    out.println("   .space 65536");
                    out.println("_heap_ptr:");
                    out.println("   .space 8");
                    for (LIN.DataChunk dc : dataChunks) {
                        out.println(dc.label.name + ":");
                        if (dc.init != null) {
                            // string bytes
                            out.println("   .string "+dc.init);
                        } else {
                            out.print("   .byte ");
                            for (int i = 0; i < dc.size; i++) {
                                if (i < dc.size -1) {
                                    out.print("0, ");
                                } else {
                                    out.println("0");
                                }
                            }
                        }
                    }
                    out.println(".text");
                    //startup routine
                    emitStartup(out);
                    //stdlib routine
                    emitStdlib(out);

                    for (LIN.CodeChunk c : tracedChunks) {
                        Regall regAlloc = new Regall();
                        //out.println("#### " + c.frame.label.name + " ####");
                        Vector<Instr> instrs = map.get(c);
                        Vector<String> coloredLines = regAlloc.main(instrs, c.frame);
                        // which regs were used
                        HashSet<String> savedRegs = regAlloc.usedPhysRegs();
                        // order them deterministically
                        ArrayList<String> savedRegsList = new ArrayList<>();
                        for (String r : allRegs) {
                            if (savedRegs.contains(r)) {
                                savedRegsList.add(r);
                            }
                        }
                        int savedRegsSize = savedRegsList.size() * 8;
                        // update frame size for all the new temps added during spilling
                        c.frame.size += c.frame.tempsSize + savedRegsSize;
                        emitFunction(out, c, coloredLines, savedRegsList);
                    }
                    out.close();
                    if (cmdLineOpts.get("--target-phase").equals("regall"))
                        break;

                    break;
                }
            }

            // But beware:
            // 1. The generated translation of the source file might be erroneous :-o
            // 2. The source file might not be what the programmer intended it to be ;-)
            Report.info("Done.");

        } catch (final Report.Error error) {
            System.err.println(error.getMessage());
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    // print int=1, read int = 5, print char=11, read char = 12, exit=10,. read=63
    private static void emitStdlib(PrintWriter out) {
        // putint
        out.println("_putint:");
        out.println("   addi sp, sp, -24"); // need to save 3 regs into mini-frame
        out.println("   sd s0, 0(sp)");
        out.println("   sd ra, 8(sp)");
        out.println("   sd a7, 16(sp)");
        out.println("   addi s0, sp, 24");
        out.println("   ld a0, 8(s0)");
        out.println("   li a7, 1");
        out.println("   ecall");
        out.println("   ld s0, 0(sp)");
        out.println("   ld ra, 8(sp)");
        out.println("   ld a7, 16(sp)");
        out.println("   addi sp, sp, 24");
        out.println("   ret");
        // putchar
        out.println("_putchar:");
        out.println("   addi sp, sp, -24");
        out.println("   sd s0, 0(sp)");
        out.println("   sd ra, 8(sp)");
        out.println("   sd a7, 16(sp)");
        out.println("   addi s0, sp, 24");
        out.println("   ld a0, 8(s0)");
        out.println("   li a7, 11");
        out.println("   ecall");
        out.println("   ld s0, 0(sp)");
        out.println("   ld ra, 8(sp)");
        out.println("   ld a7, 16(sp)");
        out.println("   addi sp, sp, 24");
        out.println("   ret");
        // getint
        out.println("_getint:");
        out.println("   addi sp, sp, -24");
        out.println("   sd s0, 0(sp)");
        out.println("   sd ra, 8(sp)");
        out.println("   sd a7, 16(sp)");
        out.println("   addi s0, sp, 24");
        out.println("   li a7, 5");
        out.println("   ecall"); // result in a0
        out.println("   ld s0, 0(sp)");
        out.println("   ld ra, 8(sp)");
        out.println("   ld a7, 16(sp)");
        out.println("   addi sp, sp, 24");
        out.println("   ret");
        // getchar
        out.println("_getchar:");
        out.println("   addi sp, sp, -24");
        out.println("   sd s0, 0(sp)");
        out.println("   sd ra, 8(sp)");
        out.println("   sd a7, 16(sp)");
        out.println("   addi s0, sp, 24");
        out.println("   li a7, 12");
        out.println("   ecall"); // result in a0
        out.println("   ld s0, 0(sp)");
        out.println("   ld ra, 8(sp)");
        out.println("   ld a7, 16(sp)");
        out.println("   addi sp, sp, 24");
        out.println("   ret");
        // exit
        out.println("_exit:");
        out.println("    li a7, 10");
        out.println("    ecall");
        // malloc
        out.println("_new:");
        out.println("   addi sp, sp, -32");
        out.println("   sd s0, 0(sp)");
        out.println("   sd ra, 8(sp)");
        out.println("   sd a7, 16(sp)");
        out.println("   sd a6, 24(sp)");
        out.println("   addi s0, sp, 32");
        out.println("   la a6, _heap_ptr"); // get hp's address
        out.println("   ld a0, 0(a6)"); // load hp in to a7
        out.println("   ld a7, 8(s0)"); // get the size in a0
        out.println("   add a7, a0, a7"); // hp += size
        out.println("   sd a7, 0(a6)"); // update in mem
        out.println("   ld s0, 0(sp)");
        out.println("   ld ra, 8(sp)");
        out.println("   ld a7, 16(sp)");
        out.println("   ld a6, 24(sp)");
        out.println("   addi sp, sp, 32");
        out.println("   ret");
        // free
        out.println("_del:");
        out.println("   ret");
    }

    // startup routine, executes, before main()
    private static void emitStartup(PrintWriter out) {
        out.println("_start:");
        out.println("    li sp, 0x7FFFFF00");
        out.println("    la t0, _heap");      // ← init heap ptr
        out.println("    la t1, _heap_ptr"); // point it to heap
        out.println("    sd t0, 0(t1)");
        out.println("    jal ra, _main");
        out.println("    li a7, 10");   // exit syscall number in Ripes
        out.println("    ecall");
    }
    // prolog/epilog:
    private static void emitFunction(PrintWriter out, LIN.CodeChunk c, Vector<String> coloredLines, ArrayList<String> savedRegsList) {
        long size = c.frame.size;
        long argsSize = c.frame.argsSize;
        long locsSize = c.frame.locsSize;
        long tempsSize = c.frame.tempsSize;
        int savedRegsSize = savedRegsList.size() * 8;
        // saved regs = sp + args + i*8
        // ra slot = s0 - locsize + 8
        // old s0 = s0 - locsize  + 16
        for (int i = 0; i < coloredLines.size(); i++) {
            String line = coloredLines.get(i);
            if (line.equals(c.entryLabel.name + ":")) {
                out.println(c.frame.label.name + ":");
                // prolog, then emit entry label at the end of it
                out.println("   addi sp, sp, -" + size); // premaknemo SP
                out.println("   sd s0, "+(argsSize+savedRegsSize+tempsSize)+"(sp)"); // shranimo star s0
                out.println("   sd ra, "+(8+argsSize+savedRegsSize+tempsSize)+"(sp)"); // shranimo star s0
                out.println("   addi s0, sp, " + size); // premaknemo FP
                // shranimo uporabljene registre v savedregisters
                for (int j = 0; j < savedRegsList.size(); j++) {
                    out.println("   sd "+savedRegsList.get(j)+", "+(j*8 + argsSize)+"(sp)");
                }
                out.println(line);
            } else if (line.equals(c.exitLabel.name + ":")) {
                // emit exit label, then epilog follows
                out.println(line);
                // restore registers
                for (int j = 0; j < savedRegsList.size(); j++) {
                    out.println("   ld "+savedRegsList.get(j)+", "+(j*8 + argsSize)+"(sp)");
                }
                // restore s0, sp, ra; a0 has return value
                out.println("   ld s0, "+(argsSize+savedRegsSize+tempsSize)+"(sp)");
                out.println("   ld ra, "+(8+argsSize+savedRegsSize+tempsSize)+"(sp)");
                out.println("   addi sp, sp, " + size);
                // return to the caller
                out.println("   ret");
            } else if (line.endsWith(":")){
                out.println(line);
            } else {
                out.println("   "+line);
            }
        }
    }
}