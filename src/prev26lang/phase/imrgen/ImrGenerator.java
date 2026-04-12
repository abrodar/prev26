package prev26lang.phase.imrgen;

import prev26lang.phase.abstr.AST;
import prev26lang.phase.memory.Layouter;
import prev26lang.phase.memory.MEM;
import prev26lang.phase.memory.Memory;
import prev26lang.phase.seman.SemAn;
import prev26lang.phase.seman.TYP;

import java.util.ArrayDeque;
import java.util.Deque;

public class ImrGenerator implements AST.FullVisitor<Object, Object> {
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
    public Object visit(AST.AtomExpr atomExpr, Object arg) {
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
            long size = accessType.size;
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
        if (SemAn.ofTypeAttr.get(arrExpr.arrExpr) instanceof TYP.ArrType arrType) {
            arrExpr.arrExpr.accept(this, arg);
            IMR.Expr E1 = ImrGen.genExprIMRAttr.get(arrExpr.arrExpr);
            arrExpr.idx.accept(this, arg);
            IMR.Expr E2 = ImrGen.genExprIMRAttr.get(arrExpr.idx);
            ImrGen.genExprIMRAttr.put(new IMR.BINOP(IMR.BINOP.Oper.ADD, E1, new IMR.BINOP(IMR.BINOP.Oper.MUL, E2, )), E2);
        }
        return null;
    }

}



