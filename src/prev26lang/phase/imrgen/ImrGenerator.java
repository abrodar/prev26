package prev26lang.phase.imrgen;

import prev26lang.common.report.Report;
import prev26lang.phase.abstr.AST;
import prev26lang.phase.memory.Layouter;
import prev26lang.phase.memory.MEM;
import prev26lang.phase.memory.Memory;
import prev26lang.phase.seman.SemAn;
import prev26lang.phase.seman.TYP;
import prev26lang.phase.seman.TypeConstructor;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImrGenerator implements AST.FullVisitor<Object, Object> {
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
            this.depth = depth;
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
            case AST.AtomExpr.Type.INT :
                                        ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(Long.parseLong(atomExpr.value)));
                                        break;
            case AST.AtomExpr.Type.PTR: // posebej za nil, ki je po typ 19 ptr(void)
                                        ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(0));
            case AST.AtomExpr.Type.BOOL :
                                        if (atomExpr.value.equals("true")) {
                                            ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(1));
                                        } else {
                                            ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(0));
                                        }
                                        break;
            case AST.AtomExpr.Type.CHAR:
                                        ImrGen.genExprIMRAttr.put(atomExpr, new IMR.CONST(atomExpr.value.charAt(0)));
                                        break;
        }
        if (atomExpr.type == AST.AtomExpr.Type.STR) {
            MEM.AbsAccess absAccess = Memory.stringAttr.get(atomExpr);
            MEM.Label label = absAccess.label;
            IMR.NAME name = new IMR.NAME(label);
            ImrGen.genExprIMRAttr.put(atomExpr, name);
        }
        return null;
    }

    //AB's helper
    IMR.Expr getNestedFP(FrameCtx currentFrame, long ra_depth) {
        IMR.Expr fp = currentFrame.FP;
        long diff = currentFrame.depth-ra_depth;
        for (int i = 0; i < diff; i++) {
            fp = new IMR.MEM8(fp);
        }
        return fp;
    }

    @Override
    public Object visit(AST.NameExpr nameExpr, Object arg) {
        if (SemAn.isAddrAttr.get(nameExpr)) {
            FrameCtx currentFrame = stack.peek();
            AST.Defn astDefn = SemAn.defAtAttr.get(nameExpr);
            MEM.Access accessType = Memory.accessAttr.get(astDefn);
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
            if (SemAn.isAddrAttr.get(arrExpr.idx)) {
                E2 = new IMR.MEM8(E2);
            }
            long elemSize = Layouter.sizeOf(arrType.elemType);
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

        ImrGen.genExprIMRAttr.put(sfxExpr, new IMR.MEM8(E));
        return null;
    }

}



