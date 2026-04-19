package prev26lang.phase.imrlin;

import prev26lang.common.report.Report;
import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.memory.MEM;

import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Vector;

/**
 * Interpreter - for testing purposes only.
 */
public class Interpreter {

    private Scanner scanner = new Scanner(System.in);
    private boolean debug = false;
    private Random random;

    private HashMap<Long, Byte> memory;
    private HashMap<MEM.Temp, Long> temps;
    private HashMap<MEM.Label, Long> dataMemLabels;
    private HashMap<MEM.Label, Integer> jumpMemLabels;
    private HashMap<MEM.Label, LIN.CodeChunk> callMemLabels;

    private MEM.Temp SP;
    private MEM.Temp FP;
    private MEM.Temp RV;
    private MEM.Temp HP;

    public Interpreter(Vector<LIN.DataChunk> dataChunks, Vector<LIN.CodeChunk> codeChunks) {
        random = new Random();
        this.memory = new HashMap<>();
        this.temps = new HashMap<>();

        SP = new MEM.Temp();
        tempST(SP, 0x7FFFFFFFFFFFFFF8L);
        HP = new MEM.Temp();
        tempST(HP, 0x2000000000000000L);

        this.dataMemLabels = new HashMap<>();
        for (LIN.DataChunk dataChunk : dataChunks) {
            if (debug)
                System.out.printf("### %s @ %d\n", dataChunk.label.name, tempLD(HP, false));
            this.dataMemLabels.put(dataChunk.label, tempLD(HP, false));
            if (dataChunk.init != null) {
                for (int c = 0; c < dataChunk.init.length() - 2; c++)
                    memST(tempLD(HP, false) + 8 * c, (long) dataChunk.init.charAt(c + 1), false);
                memST(tempLD(HP, false) + 8 * (dataChunk.init.length() - 2), 0L, false);
            }
            tempST(HP, tempLD(HP, false) + dataChunk.size, debug);
        }
        if (debug)
            System.out.printf("###\n");

        this.jumpMemLabels = new HashMap<>();
        this.callMemLabels = new HashMap<>();
        for (LIN.CodeChunk codeChunk : codeChunks) {
            this.callMemLabels.put(codeChunk.frame.label, codeChunk);
            Vector<IMR.Stmt> stmts = codeChunk.stmts();
            for (int i = 0; i < stmts.size(); i++) {
                if (stmts.get(i) instanceof IMR.LABEL lbl)
                    jumpMemLabels.put(lbl.label, i);
            }
        }
    }

    // --- memory helpers ---

    private void memST(Long address, Long value) { memST(address, value, debug); }
    private void memST(Long address, Long value, boolean dbg) {
        if (dbg) System.out.printf("### [%d] <- %d\n", address, value);
        for (int b = 0; b <= 7; b++) {
            long longval = value % 0x100;
            memory.put(address + b, (byte) longval);
            value = value >> 8;
        }
    }

    private Long memLD(Long address) { return memLD(address, debug); }
    private Long memLD(Long address, boolean dbg) {
        Long value = 0L;
        for (int b = 7; b >= 0; b--) {
            Byte byteval = memory.get(address + b);
            if (byteval == null)
                byteval = (byte) (random.nextLong() / 0x100);
            long longval = (long) byteval;
            value = (value * 0x100) + (longval < 0 ? longval + 0x100 : longval);
        }
        if (dbg) System.out.printf("### %d <- [%d]\n", value, address);
        return value;
    }

    private void tempST(MEM.Temp temp, Long value) { tempST(temp, value, debug); }
    private void tempST(MEM.Temp temp, Long value, boolean dbg) {
        temps.put(temp, value);
        if (dbg) {
            if (temp == SP) { System.out.printf("### SP <- %d\n", value); return; }
            if (temp == FP) { System.out.printf("### FP <- %d\n", value); return; }
            if (temp == RV) { System.out.printf("### RV <- %d\n", value); return; }
            if (temp == HP) { System.out.printf("### HP <- %d\n", value); return; }
            System.out.printf("### T%d <- %d\n", temp.temp, value);
        }
    }

    private Long tempLD(MEM.Temp temp) { return tempLD(temp, debug); }
    private Long tempLD(MEM.Temp temp, boolean dbg) {
        Long value = temps.get(temp);
        if (value == null)
            throw new Report.Error("Uninitialized temporary variable T" + temp.temp + ".");
        if (dbg) {
            if (temp == SP) { System.out.printf("### %d <- SP\n", value); return value; }
            if (temp == FP) { System.out.printf("### %d <- FP\n", value); return value; }
            if (temp == RV) { System.out.printf("### %d <- RV\n", value); return value; }
            if (temp == HP) { System.out.printf("### %d <- HP\n", value); return value; }
            System.out.printf("### %d <- T%d\n", value, temp.temp);
        }
        return value;
    }

    // --- expression evaluator ---

    private class ExprInterpreter implements IMR.Visitor<Long, Object> {

        @Override
        public Long visit(IMR.BINOP imcBinop, Object arg) {
            Long fstExpr = imcBinop.fstExpr.accept(this, null);
            Long sndExpr = imcBinop.sndExpr.accept(this, null);
            switch (imcBinop.oper) {
                case OR:  return (fstExpr != 0) | (sndExpr != 0) ? 1L : 0L;
                case AND: return (fstExpr != 0) & (sndExpr != 0) ? 1L : 0L;
                case EQU: return fstExpr.equals(sndExpr) ? 1L : 0L;
                case NEQ: return !fstExpr.equals(sndExpr) ? 1L : 0L;
                case LEQ: return (fstExpr <= sndExpr) ? 1L : 0L;
                case GEQ: return (fstExpr >= sndExpr) ? 1L : 0L;
                case LTH: return (fstExpr < sndExpr) ? 1L : 0L;
                case GTH: return (fstExpr > sndExpr) ? 1L : 0L;
                case ADD: return fstExpr + sndExpr;
                case SUB: return fstExpr - sndExpr;
                case MUL: return fstExpr * sndExpr;
                case DIV: return fstExpr / sndExpr;
                case MOD: return fstExpr % sndExpr;
            }
            throw new Report.InternalError();
        }

        @Override
        public Long visit(IMR.CALL imcCall, Object arg) {
            // CALL should never appear inside an expression after canonicalization
            throw new Report.InternalError();
        }

        @Override
        public Long visit(IMR.CONST imcConst, Object arg) {
            return imcConst.value;
        }

        @Override
        public Long visit(IMR.MEM8 imcMem, Object arg) {
            return memLD(imcMem.addr.accept(this, null));
        }

        @Override
        public Long visit(IMR.MEM1 imcMem, Object arg) {
            return memLD(imcMem.addr.accept(this, null)) & 0xFFL;
        }

        @Override
        public Long visit(IMR.NAME imcName, Object arg) {
            Long addr = dataMemLabels.get(imcName.label);
            if (addr != null) return addr;
            // function pointer — encode label identity
            return (long) System.identityHashCode(imcName.label);
        }

        @Override
        public Long visit(IMR.SEXPR imcSExpr, Object arg) {
            // should not appear after canonicalization
            throw new Report.InternalError();
        }

        @Override
        public Long visit(IMR.TEMP imcTemp, Object arg) {
            return tempLD(imcTemp.temp);
        }

        @Override
        public Long visit(IMR.UNOP imcUnop, Object arg) {
            Long subExpr = imcUnop.subExpr.accept(this, null);
            switch (imcUnop.oper) {
                case NOT: return (subExpr == 0) ? 1L : 0L;
                case NEG: return -subExpr;
            }
            throw new Report.InternalError();
        }
    }

    // --- statement interpreter, returns label to jump to (null = fall through) ---

    private class StmtInterpreter implements IMR.Visitor<MEM.Label, Object> {

        @Override
        public MEM.Label visit(IMR.CJUMP imcCJump, Object arg) {
            if (debug) System.out.println(imcCJump);
            Long cond = imcCJump.cond.accept(new ExprInterpreter(), null);
            IMR.Expr target = (cond != 0) ? imcCJump.posAddr : imcCJump.negAddr;
            if (target instanceof IMR.NAME n) return n.label;
            throw new Report.InternalError();
        }

        @Override
        public MEM.Label visit(IMR.ESTMT imcEStmt, Object arg) {
            if (debug) System.out.println(imcEStmt);
            if (imcEStmt.expr instanceof IMR.CALL c) {
                call(c);
                return null;
            }
            imcEStmt.expr.accept(new ExprInterpreter(), null);
            return null;
        }

        @Override
        public MEM.Label visit(IMR.JUMP imcJump, Object arg) {
            if (debug) System.out.println(imcJump);
            if (imcJump.addr instanceof IMR.NAME n) return n.label;
            throw new Report.InternalError();
        }

        @Override
        public MEM.Label visit(IMR.LABEL imcLabel, Object arg) {
            if (debug) System.out.println(imcLabel);
            return null;
        }

        @Override
        public MEM.Label visit(IMR.MOVE imcMove, Object arg) {
            if (debug) System.out.println(imcMove);

            Long src;
            if (imcMove.src instanceof IMR.CALL c) {
                call(c);
                src = memLD(tempLD(SP));
            } else {
                src = imcMove.src.accept(new ExprInterpreter(), null);
            }

            if (imcMove.dst instanceof IMR.MEM8 mem) {
                memST(mem.addr.accept(new ExprInterpreter(), null), src);
                return null;
            }
            if (imcMove.dst instanceof IMR.MEM1 mem) {
                memST(mem.addr.accept(new ExprInterpreter(), null), src & 0xFFL);
                return null;
            }
            if (imcMove.dst instanceof IMR.TEMP t) {
                tempST(t.temp, src);
                return null;
            }
            throw new Report.InternalError();
        }

        @Override
        public MEM.Label visit(IMR.STMTS imcStmts, Object arg) {
            if (debug) System.out.println(imcStmts);
            throw new Report.InternalError();
        }

        private void call(IMR.CALL imcCall) {
            // write args to stack using their offsets
            for (int i = 0; i < imcCall.args.size(); i++) {
                Long callValue = imcCall.args.get(i).accept(new ExprInterpreter(), null);
                memST(tempLD(SP) + imcCall.offs.get(i), callValue);
            }

            // resolve callee label
            MEM.Label callLabel = null;
            if (imcCall.addr instanceof IMR.NAME n) {
                callLabel = n.label;
            } else {
                long encoded = imcCall.addr.accept(new ExprInterpreter(), null);
                for (MEM.Label lbl : callMemLabels.keySet()) {
                    if ((long) System.identityHashCode(lbl) == encoded) {
                        callLabel = lbl;
                        break;
                    }
                }
                if (callLabel == null)
                    throw new Report.Error("Cannot resolve function pointer.");
            }

            String name = callLabel.name;
            if (name.equals("_exit")) {
                System.exit(0);
            }
            if (name.equals("_putint")) {
                Long c = memLD(tempLD(SP, false) + 8, false);
                System.out.printf("%d", c);
                return;
            }
            if (name.equals("_getint")) {
                Long l = scanner.nextLong();
                memST(tempLD(SP), l, false);
                return;
            }
            if (name.equals("_putchar")) {
                Long c = memLD(tempLD(SP, false) + 8, false);
                System.out.printf("%c", (char) ((long) c) % 0x100);
                return;
            }
            if (name.equals("_getchar")) {
                char c = '\n';
                try { c = (char) System.in.read(); } catch (Exception ignored) {}
                memST(tempLD(SP), (long) c, false);
                return;
            }

            funCall(callLabel);
        }
    }

    public void funCall(MEM.Label entryMemLabel) {
        LIN.CodeChunk chunk = callMemLabels.get(entryMemLabel);
        if (chunk == null)
            throw new Report.Error("Unknown function: " + entryMemLabel.name);

        MEM.Frame frame = chunk.frame;
        Vector<IMR.Stmt> stmts = chunk.stmts();

        HashMap<MEM.Temp, Long> storedMemTemps;
        MEM.Temp storedFP;
        MEM.Temp storedRV;
        int stmtOffset;

        /* PROLOGUE */
        {
            if (debug)
                System.out.printf("###\n### CALL: %s\n", entryMemLabel.name);
            storedMemTemps = temps;
            storedFP = FP;
            storedRV = RV;
            temps = new HashMap<>(temps);
            FP = frame.FP;
            RV = frame.RV;
            tempST(frame.FP, tempLD(SP));
            tempST(SP, tempLD(SP) - frame.size);
            stmtOffset = jumpMemLabels.get(chunk.entryLabel);
        }

        /* BODY */
        {
            MEM.Label label = null;
            while (true) {
                if (label != null) {
                    Integer offset = jumpMemLabels.get(label);
                    if (offset == null) throw new Report.InternalError();
                    stmtOffset = offset;
                }
                // check exit BEFORE executing
                IMR.Stmt current = stmts.get(stmtOffset);
                if (current instanceof IMR.LABEL lbl && lbl.label == chunk.exitLabel)
                    break;
                label = current.accept(new StmtInterpreter(), null);
                stmtOffset++;
            }
        }

        /* EPILOGUE */
        {
            memST(tempLD(frame.FP), tempLD(frame.RV));
            tempST(SP, tempLD(SP) + frame.size);
            Long hp = tempLD(HP);
            temps = storedMemTemps;
            tempST(HP, hp);
            FP = storedFP;
            RV = storedRV;
            if (debug)
                System.out.printf("### RETURN: %s\n###\n", entryMemLabel.name);
        }
    }

    public long run(String entryMemLabel) {
        for (MEM.Label label : callMemLabels.keySet()) {
            if (label.name.equals(entryMemLabel)) {
                funCall(label);
                return memLD(tempLD(SP));
            }
        }
        throw new Report.InternalError();
    }
}