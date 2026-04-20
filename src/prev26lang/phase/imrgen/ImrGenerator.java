package prev26lang.phase.imrgen;

import prev26lang.Compiler;
import prev26lang.common.report.Report;
import prev26lang.phase.abstr.AST;
import prev26lang.phase.memory.Layouter;
import prev26lang.phase.memory.MEM;
import prev26lang.phase.memory.Memory;
import prev26lang.phase.seman.SemAn;
import prev26lang.phase.seman.TYP;
import prev26lang.phase.seman.TypeConstructor;

import java.io.DataOutput;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.Vector;

import static prev26lang.phase.memory.Layouter.sizeOf;

public class ImrGenerator implements AST.FullVisitor<Object, Object> {
    //AB's helper
    IMR.Expr getNestedFP(FrameCtx currentFrame, long depth) {
        IMR.Expr fp = currentFrame.FP;
        long diff = currentFrame.depth-depth;
        for (int i = 0; i < diff; i++) {
            fp = new IMR.MEM8(fp);
        }
        return fp;
    }
    // little helper map
    private static final Map<AST.BinExpr.Oper, IMR.BINOP.Oper> operMap = Map.ofEntries(
            Map.entry(AST.BinExpr.Oper.ADD, IMR.BINOP.Oper.ADD),
            Map.entry(AST.BinExpr.Oper.SUB, IMR.BINOP.Oper.SUB),
            Map.entry(AST.BinExpr.Oper.MUL, IMR.BINOP.Oper.MUL),
            Map.entry(AST.BinExpr.Oper.DIV, IMR.BINOP.Oper.DIV),
            Map.entry(AST.BinExpr.Oper.MOD, IMR.BINOP.Oper.MOD),
            Map.entry(AST.BinExpr.Oper.EQU, IMR.BINOP.Oper.EQU),
            Map.entry(AST.BinExpr.Oper.NEQ, IMR.BINOP.Oper.NEQ),
            Map.entry(AST.BinExpr.Oper.LTH, IMR.BINOP.Oper.LTH),
            Map.entry(AST.BinExpr.Oper.GTH, IMR.BINOP.Oper.GTH),
            Map.entry(AST.BinExpr.Oper.LEQ, IMR.BINOP.Oper.LEQ),
            Map.entry(AST.BinExpr.Oper.GEQ, IMR.BINOP.Oper.GEQ),
            Map.entry(AST.BinExpr.Oper.AND, IMR.BINOP.Oper.AND),
            Map.entry(AST.BinExpr.Oper.OR,  IMR.BINOP.Oper.OR)
    );
    // little helper for deref
    private IMR.Expr deref(IMR.Expr e, AST.Expr astExpr) {
        if (SemAn.isAddrAttr.get(astExpr)) {
            long size = Layouter.sizeOf(SemAn.ofTypeAttr.get(astExpr));
            return size == 1 ? new IMR.MEM1(e) : new IMR.MEM8(e);
        }
        return e;
    }
    // vlecemo sabo tc za compexpr hashmap
    private final TypeConstructor tc;
    /** Constructs a new TypeChecke+r. */
    public ImrGenerator(TypeConstructor tc) {
        this.tc = tc;
    }
    // za lazji izracun (npr. SL sledenje pri abs access ipd.)
    private static class FrameCtx {
        MEM.Label label;
        long depth;
        IMR.TEMP FP;

        public FrameCtx(MEM.Label label, long depth, MEM.Temp FP) {
            this.label = label;
            this.depth = depth; // ie static depth
            this.FP = new IMR.TEMP(FP);
        }
    }
    // stack and curr depth
    private Deque<FrameCtx> stack = new ArrayDeque<>();
    private long currentdepth = 0;

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        // stack bookeeping
        MEM.Frame frame = Memory.frameAttr.get(defFunDefn);
        FrameCtx ctx = new FrameCtx(frame.label, frame.depth, frame.FP);
        stack.push(ctx);
        currentdepth++;
        // visit children
        defFunDefn.type.accept(this, arg);
        defFunDefn.pars.accept(this, arg);
        defFunDefn.expr.accept(this, arg);
        // entry and exit labels
        MEM.Label entryLabel = new MEM.Label();
        MEM.Label exitLabel = new MEM.Label();
        ImrGen.bodyEntryLabelAttr.put(defFunDefn, entryLabel);
        ImrGen.bodyExitLabelAttr.put(defFunDefn, exitLabel);
        // function body IMR — entry label, body, move result to RV, exit label
        IMR.Expr bodyExpr = ImrGen.genExprIMRAttr.get(defFunDefn.expr);
        Vector<IMR.Stmt> stmts = new Vector<>();
        stmts.add(new IMR.LABEL(entryLabel));
        stmts.add(new IMR.MOVE(new IMR.TEMP(frame.RV), bodyExpr));
        stmts.add(new IMR.LABEL(exitLabel));
        ImrGen.genStmtIMRAttr.put(defFunDefn, new IMR.STMTS(stmts));

        // stack bookeeping
        currentdepth--;
        stack.pop();
        return null;
    }


    @Override
    public Object visit(AST.AtomExpr atomExpr, Object arg) {
        switch (atomExpr.type) {
            case AST.AtomExpr.Type.STR :
                                        MEM.AbsAccess absAccess = Memory.stringAttr.get(atomExpr);
                                        MEM.Label label = absAccess.label;
                                        IMR.NAME name = new IMR.NAME(label);
                                        ImrGen.genExprIMRAttr.put(atomExpr, name);
                                        break;
            case AST.AtomExpr.Type.INT: ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(new java.math.BigInteger(atomExpr.value).longValue()));
                                        break;
            case AST.AtomExpr.Type.PTR: // posebej za nil, ki je po typ 19 ptr(void)
                                        ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(0));
                                        break;
            case AST.AtomExpr.Type.BOOL :
                                        if (atomExpr.value.equals("true")) {
                                            ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(1));
                                        } else {
                                            ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(0));
                                        }
                                        break;
            case AST.AtomExpr.Type.CHAR:
                                        String raw = atomExpr.value; // e.g. 'a' or '\'' or '\x41'
                                        char c;
                                        if (raw.charAt(1) == '\\') {
                                            if (raw.charAt(2) == '\'') c = '\'';
                                            else if (raw.charAt(2) == '\\') c = '\\';
                                            else if (raw.charAt(2) == 'x') c = (char) Integer.parseInt(raw.substring(3, 5), 16);
                                            else c = raw.charAt(2);
                                        } else {
                                            c = raw.charAt(1);
                                        }
                                        ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(c));
                                        break;
            case AST.AtomExpr.Type.VOID:
                ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(0));
                break;

        }
        return null;
    }



    @Override
    public Object visit(AST.NameExpr nameExpr, Object arg) {
        AST.Defn astDefn = SemAn.defAtAttr.get(nameExpr);
        // function reference — return its label as address
        if (astDefn instanceof AST.DefFunDefn funDefn) {
            MEM.Frame frame = Memory.frameAttr.get(funDefn);
            ImrGen.genExprIMRAttr.put(nameExpr, new IMR.NAME(frame.label));
            return null;
        }
        if (astDefn instanceof AST.ExtFunDefn extFunDefn) {
            ImrGen.genExprIMRAttr.put(nameExpr, new IMR.NAME(new MEM.Label(extFunDefn.name)));
            return null;
        }
        MEM.Access accessType = Memory.accessAttr.get(astDefn);
        if (SemAn.isAddrAttr.get(nameExpr)) {
            FrameCtx currentFrame = stack.peek();
            if (accessType instanceof MEM.RelAccess ra) {
                IMR.Expr fp = getNestedFP(currentFrame, ra.depth);
                IMR.Expr e = new IMR.BINOP(IMR.BINOP.Oper.ADD, fp, new IMR.CONST(ra.offset));
                ImrGen.genExprIMRAttr.put(nameExpr, e);
            } else  if (accessType instanceof MEM.AbsAccess aa) {
                IMR.NAME name = new IMR.NAME(aa.label);
                ImrGen.genExprIMRAttr.put(nameExpr, name);
            }
        }
        return null;
    }

    @Override
    public Object visit(AST.ArrExpr arrExpr, Object arg) {
        if (SemAn.ofTypeAttr.get(arrExpr.arrExpr).actualType() instanceof TYP.ArrType arrType) {
            // visit subexpressions
            arrExpr.arrExpr.accept(this, arg);
            IMR.Expr E1 = ImrGen.genExprIMRAttr.get(arrExpr.arrExpr);
            arrExpr.idx.accept(this, arg);
            IMR.Expr E2 = ImrGen.genExprIMRAttr.get(arrExpr.idx);
            // dereference index if it's an address
            E2 =deref(E2, arrExpr.idx);
            long elemSize = sizeOf(arrType.elemType);
            IMR.Expr offset = new IMR.BINOP(IMR.BINOP.Oper.MUL, E2, new IMR.CONST(elemSize));
            ImrGen.genExprIMRAttr.put(arrExpr, new IMR.BINOP(IMR.BINOP.Oper.ADD, E1, offset));
        }
        return null;
    }

    @Override
    public Object visit(AST.CompExpr compExpr, Object arg) {
        compExpr.recExpr.accept(this, arg);
        IMR.Expr E = ImrGen.genExprIMRAttr.get(compExpr.recExpr);

        TYP.Type recType = SemAn.ofTypeAttr.get(compExpr.recExpr);
        // unwrap
        TYP.Type actual = recType;
        if (actual instanceof TYP.NameType) {
            actual = actual.actualType();
        }
        MEM.RelAccess ra = null;
        if (actual instanceof TYP.StrType strType) {
            AST.StrType astStr = tc.strTypeMap.get(strType);
            for (int i = 0; i < astStr.comps.size(); i++) {
                AST.CompDefn comp = astStr.comps.get(i);
                if (comp.name.equals(compExpr.name)) {
                    ra = (MEM.RelAccess) Memory.accessAttr.get(comp);
                }
            }
        } else if (actual instanceof TYP.UniType uniType) {
            AST.UniType astUni = tc.uniTypeMap.get(uniType);
            for (int i = 0; i < astUni.comps.size(); i++) {
                AST.CompDefn comp = astUni.comps.get(i);
                if (comp.name.equals(compExpr.name)) {
                    ra = (MEM.RelAccess) Memory.accessAttr.get(comp);
                }
            }
        }
        ImrGen.genExprIMRAttr.put(compExpr, new IMR.BINOP(IMR.BINOP.Oper.ADD, E, new IMR.CONST(ra.offset))); 
        return null;
    }


    @Override
    public Object visit(AST.SfxExpr sfxExpr, Object arg) {
        sfxExpr.subExpr.accept(this, arg);
        IMR.Expr E = ImrGen.genExprIMRAttr.get(sfxExpr.subExpr);
        E = deref(E, sfxExpr.subExpr);
        ImrGen.genExprIMRAttr.put(sfxExpr, E);
        return null;
    }
    @Override
    public Object visit(AST.PfxExpr pfxExpr, Object arg) {
        pfxExpr.subExpr.accept(this, arg);
        IMR.Expr E = ImrGen.genExprIMRAttr.get(pfxExpr.subExpr);
        if (pfxExpr.oper == AST.PfxExpr.Oper.NOT) {
            E = deref(E,  pfxExpr.subExpr);
            ImrGen.genExprIMRAttr.put(pfxExpr, new IMR.UNOP(IMR.UNOP.Oper.NOT, E));
        } else if (pfxExpr.oper == AST.PfxExpr.Oper.SUB) {
            E = deref(E,  pfxExpr.subExpr);
            ImrGen.genExprIMRAttr.put(pfxExpr, new IMR.UNOP(IMR.UNOP.Oper.NEG, E));
        } else if (pfxExpr.oper == AST.PfxExpr.Oper.ADD) {
            E = deref(E,  pfxExpr.subExpr);
            ImrGen.genExprIMRAttr.put(pfxExpr, E); // za +, ki nima vpliva brez unop
        } else if (pfxExpr.oper == AST.PfxExpr.Oper.PTR) { // sym 13
            ImrGen.genExprIMRAttr.put(pfxExpr, E);
        }
        return null;
    }

    @Override
    public Object visit(AST.BinExpr binExpr, Object arg) {
        binExpr.fstExpr.accept(this, arg);
        binExpr.sndExpr.accept(this, arg);
        IMR.Expr E1 =  ImrGen.genExprIMRAttr.get(binExpr.fstExpr);
        IMR.Expr E2 = ImrGen.genExprIMRAttr.get(binExpr.sndExpr);
        E1 = deref(E1, binExpr.fstExpr);
        E2 = deref(E2, binExpr.sndExpr);
        ImrGen.genExprIMRAttr.put(binExpr, new IMR.BINOP(operMap.get(binExpr.oper), E1, E2));
        return null;
    }

    @Override
    public Object visit(AST.Exprs exprs, Object arg) {
        exprs.exprs.accept(this, arg);
        int size = exprs.exprs.size();
        if (size == 1) {
            IMR.Expr E = ImrGen.genExprIMRAttr.get(exprs.exprs.get(0));
            E = deref(E, exprs.exprs.get(0));
            ImrGen.genExprIMRAttr.put(exprs, E);
        } else {
            Vector<IMR.Stmt> stmts = new Vector<>();
            for (int i = 0; i < size - 1; i++) {
                AST.Expr e = exprs.exprs.get(i);
                IMR.Expr imrE = ImrGen.genExprIMRAttr.get(e);
                stmts.add(new IMR.ESTMT(imrE));
            }
            AST.Expr last = exprs.exprs.get(size - 1);
            IMR.Expr lastE = deref(ImrGen.genExprIMRAttr.get(last), last);
            ImrGen.genExprIMRAttr.put(exprs, new IMR.SEXPR(new IMR.STMTS(stmts), lastE));
        }
        return null;
    }

    @Override
    public Object visit(AST.CallExpr callExpr, Object arg) {
        callExpr.funExpr.accept(this, arg);
        callExpr.argExprs.accept(this, arg);
        // get funAddr
        IMR.Expr funAddr = ImrGen.genExprIMRAttr.get(callExpr.funExpr);
        funAddr = deref(funAddr, callExpr.funExpr);
        // get args and sl
        Vector<Long> offsets = new Vector<>();
        Vector<IMR.Expr> argsE = new  Vector<>();
        //the static link must be included
        AST.Defn calleDefn = null;
        if (callExpr.funExpr instanceof AST.NameExpr) {
            calleDefn = SemAn.defAtAttr.get(callExpr.funExpr);
        }
        MEM.Frame calleFrame = null;
        if (calleDefn instanceof AST.DefFunDefn) {
            calleFrame = Memory.frameAttr.get(calleDefn);
        }
        FrameCtx currFrame = stack.peek();

        IMR.Expr sl;
        if (calleFrame == null || calleFrame.depth == 0) {
            sl = new IMR.CONST(0); // globalne nimajo SL
        } else {
            sl = getNestedFP(currFrame, calleFrame.depth - 1); // tre.globina-calle.globina da prides do klicateljeve globine, nato -1 da prides do njegovega static encloserja
        }
        argsE.add(sl);
        offsets.add(0L);
        // dejanski argumenti
        for  (int i = 0; i < callExpr.argExprs.size(); i++) {
            AST.Expr argExpr = callExpr.argExprs.get(i);
            IMR.Expr e = deref(ImrGen.genExprIMRAttr.get(argExpr), argExpr);
            argsE.add(e);
            offsets.add((i+1)*8L);
        }

        ImrGen.genExprIMRAttr.put(callExpr, new IMR.CALL(funAddr, offsets, argsE));

        return null;
    }

    // sem20 - 22
    @Override
    public Object visit(AST.CastExpr castExpr, Object arg) {
        castExpr.type.accept(this, arg);
        castExpr.expr.accept(this, arg);
        IMR.Expr E = ImrGen.genExprIMRAttr.get(castExpr.expr);
        E = deref(E, castExpr.expr);

        // type ne sme biti bool ali char
        TYP.Type castType = SemAn.isTypeAttr.get(castExpr.type);
        if (TYP.equiv(castType, TYP.BoolType.type)) {
            E = new IMR.BINOP(IMR.BINOP.Oper.MOD, E, new IMR.CONST(2));
        } else if(TYP.equiv(castType, TYP.CharType.type)) {
            E = new IMR.BINOP(IMR.BINOP.Oper.MOD, E, new IMR.CONST(256));
        }
        ImrGen.genExprIMRAttr.put(castExpr, E);
        return null;
    }

    @Override
    public Object visit(AST.SizeExpr sizeExpr, Object arg) {
        sizeExpr.type.accept(this, arg);
        TYP.Type  sizeType = SemAn.isTypeAttr.get(sizeExpr.type);
        ImrGen.genExprIMRAttr.put(sizeExpr, new IMR.CONST(sizeOf(sizeType)));
        return null;
    }

    @Override
    public Object visit(AST.AsgnExpr asgnExpr, Object arg) {
        asgnExpr.fstExpr.accept(this, arg);
        asgnExpr.sndExpr.accept(this, arg);
        IMR.Expr E1 = ImrGen.genExprIMRAttr.get(asgnExpr.fstExpr);
        IMR.Expr E2 = ImrGen.genExprIMRAttr.get(asgnExpr.sndExpr);
        E2 =  deref(E2, asgnExpr.sndExpr);

        // E1 is an address — wrap in MEM to make it a valid MOVE dst
        long size = Layouter.sizeOf(SemAn.ofTypeAttr.get(asgnExpr.fstExpr));
        IMR.Expr dst = size == 1 ? new IMR.MEM1(E1) : new IMR.MEM8(E1);

        IMR.Stmt move = new IMR.MOVE(dst, E2);
        ImrGen.genExprIMRAttr.put(asgnExpr, new IMR.SEXPR(move, new IMR.CONST(0)));

        return null;
    }

    @Override
    public Object visit(AST.LetExpr letExpr, Object arg) {
        letExpr.defns.accept(this, arg);
        letExpr.expr.accept(this, arg);
        IMR.Expr E = ImrGen.genExprIMRAttr.get(letExpr.expr);
        // no deref — Exprs already handled it
        ImrGen.genExprIMRAttr.put(letExpr, E);
        return null;
    }

    @Override
    public Object visit(AST.IfThenExpr ifExpr, Object arg) {
        ifExpr.condExpr.accept(this, arg);
        ifExpr.thenExpr.accept(this, arg);

        IMR.Expr cond = ImrGen.genExprIMRAttr.get(ifExpr.condExpr);

        MEM.Label thenLabel = new MEM.Label();
        MEM.Label endLabel = new MEM.Label();

        Vector<IMR.Stmt> stmts = new Vector<>();

        // CJUMP(cond, then, end)
        stmts.add(new IMR.CJUMP(cond, new IMR.NAME(thenLabel), new IMR.NAME(endLabel)));
        // LABEL(then)
        stmts.add(new IMR.LABEL(thenLabel));
        // then body as ESTMT (result is void/discarded per SEM:25)
        stmts.add(new IMR.ESTMT(ImrGen.genExprIMRAttr.get(ifExpr.thenExpr)));
        // LABEL(end)
        stmts.add(new IMR.LABEL(endLabel));

        // if returns void = 0
        ImrGen.genExprIMRAttr.put(ifExpr, new IMR.SEXPR(new IMR.STMTS(stmts), new IMR.CONST(0)));
        return null;
    }
    @Override
    public Object visit(AST.IfThenElseExpr ifThenElseExpr, Object arg) {
        ifThenElseExpr.condExpr.accept(this, arg);
        ifThenElseExpr.thenExpr.accept(this, arg);
        ifThenElseExpr.elseExpr.accept(this, arg);

        IMR.Expr cond = ImrGen.genExprIMRAttr.get(ifThenElseExpr.condExpr);
        MEM.Label thenLabel = new MEM.Label();
        MEM.Label elseLabel = new MEM.Label();
        MEM.Label endLabel = new MEM.Label();

        Vector<IMR.Stmt> stmts = new Vector<>();

        stmts.add(new IMR.CJUMP(cond, new IMR.NAME(thenLabel), new IMR.NAME(elseLabel)));
        stmts.add(new IMR.LABEL(thenLabel));
        stmts.add(new IMR.ESTMT(ImrGen.genExprIMRAttr.get(ifThenElseExpr.thenExpr)));
        stmts.add(new IMR.JUMP(new IMR.NAME(endLabel)));
        stmts.add(new IMR.LABEL(elseLabel));
        stmts.add(new IMR.ESTMT(ImrGen.genExprIMRAttr.get(ifThenElseExpr.elseExpr)));
        stmts.add(new IMR.LABEL(endLabel));

        ImrGen.genExprIMRAttr.put(ifThenElseExpr, new IMR.SEXPR(new IMR.STMTS(stmts), new IMR.CONST(0)));
        return null;
    }

    @Override
    public Object visit(AST.WhileExpr whileExpr, Object arg) {
        whileExpr.condExpr.accept(this, arg);
        whileExpr.expr.accept(this, arg);
        IMR.Expr cond = ImrGen.genExprIMRAttr.get(whileExpr.condExpr);
        IMR.Expr body = ImrGen.genExprIMRAttr.get(whileExpr.expr);

        //cond = deref(cond, whileExpr.condExpr);

        MEM.Label testLabel = new MEM.Label();
        MEM.Label bodyLabel = new MEM.Label();
        MEM.Label doneLabel = new MEM.Label();

        Vector<IMR.Stmt> stmts = new Vector<>();
        stmts.add(new IMR.LABEL(testLabel));
        stmts.add(new IMR.CJUMP(cond, new IMR.NAME(bodyLabel), new IMR.NAME(doneLabel)));
        stmts.add(new IMR.LABEL(bodyLabel));
        stmts.add(new IMR.ESTMT(body));
        stmts.add(new IMR.JUMP(new IMR.NAME(testLabel)));
        stmts.add(new IMR.LABEL(doneLabel));

        ImrGen.genExprIMRAttr.put(whileExpr, new IMR.SEXPR(new IMR.STMTS(stmts), new IMR.CONST(0)));
        return null;
    }
}



