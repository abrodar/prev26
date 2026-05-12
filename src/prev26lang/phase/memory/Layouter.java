package prev26lang.phase.memory;

import prev26lang.common.report.Report;
import prev26lang.phase.abstr.AST;
import prev26lang.phase.seman.SemAn;
import prev26lang.phase.seman.TYP;

import java.util.ArrayDeque;
import java.util.Deque;

public class Layouter implements AST.FullVisitor<Object, Object>{
    // helpers by AB
    private static long align(long size) {
        return (size + 7) / 8 * 8;
    }
    public static long sizeOf(TYP.Type type){
        try {
            if (type instanceof TYP.IntType) {
                return 8;
            }
            if (type instanceof TYP.BoolType) {
                return 1;
            }
            if (type instanceof TYP.CharType) {
                return 1;
            }
            if (type instanceof TYP.VoidType) {
                return 0;
            }
            if (type instanceof TYP.PtrType) {
                return 8;
            }
            if (type instanceof TYP.NameType nt) {
                return sizeOf(nt.actualType());
            }
            if (type instanceof TYP.FunType) {
                return 8;
            }
            if (type instanceof TYP.ArrType at) {
                long elSize = sizeOf(at.elemType);
                return elSize* at.numElems;
            }
            if (type instanceof TYP.StrType st) {
                long size = 0;
                for (TYP.Type t : st.compTypes) {
                    size += align(sizeOf(t));
                }
                return size;
            }
            if (type instanceof TYP.UniType ut) {
                long maxSize = 0;
                for (TYP.Type t : ut.compTypes) {
                    long temp = sizeOf(t);
                    if (maxSize < temp) {
                        maxSize = temp;
                    }
                }
                return align(maxSize);
            }
        } catch (StackOverflowError e) {
            throw new Report.Error("Cyclic type definition causes infinite size.");
        }
        throw new Report.Error("Couldn't get size of the following type: '"+type+"'.");
    }

    /** Constructs a layouter. */
    public Layouter() {
    }
    // frame ctx za lazji izracun
    private static class FrameCtx {
        MEM.Label label;
        long depth;
        long parOffset = 8; // incoming params začnejp na +8 (SL na 0 od SP)
        long locsOffset = 0; // nasl. spr. (raste negativno)
        long argsOffset = 0; // outgoing args, zacne na 0 (od FP; btw SL samo ce imas klic)

        public FrameCtx(MEM.Label label, long depth) {
            this.label = label;
            this.depth = depth;
        }
    }
    // stack and curr depth
    private Deque<FrameCtx> stack = new ArrayDeque<>();
    private long currentdepth = 0;

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        // globalne funckije dobijo named label druge anonymous label
        defFunDefn.type.accept(this, arg);
        MEM.Label label = (currentdepth == 0) ?
                          new MEM.Label(defFunDefn.name)
                         :new MEM.Label();
        FrameCtx frameCtx = new FrameCtx(label, currentdepth);
        stack.push(frameCtx);
        currentdepth++;
        // obisci parametre - poz. offset od fp
        defFunDefn.pars.accept(this, arg);
        // obisci telo funkcije - vars, gnezdene fun./klici
        defFunDefn.expr.accept(this, arg);
        currentdepth--;
        stack.pop();
        long locsSize = -frameCtx.locsOffset;
        long argsSize = frameCtx.argsOffset;
        long frameSize = locsSize + 8 + 8 + argsSize;  // 8 + 8 za ret addr in old FP
        Memory.frameAttr.put(defFunDefn, new MEM.Frame(label, frameCtx.depth, locsSize, argsSize, frameSize));
        return null;
    }
    @Override
    public Object visit(AST.ExtFunDefn extFunDefn, Object arg) {
        // external functions have no frame, skip pars entirely
        extFunDefn.type.accept(this, arg);
        //extFunDefn.pars.accept(this, arg);
        return null;
    }

    @Override
    public Object visit(AST.VarDefn varDefn, Object arg) {
        varDefn.type.accept(this, arg);
        long size = sizeOf(SemAn.isTypeAttr.get(varDefn.type));
        if (stack.isEmpty()) { // globalna spremenljivka
            Memory.accessAttr.put(varDefn, new MEM.AbsAccess(size, new MEM.Label(varDefn.name)));
        } else {
            FrameCtx currFrame = stack.peek();
            currFrame.locsOffset -= align(size);
            Memory.accessAttr.put(varDefn, new MEM.RelAccess(size, currFrame.locsOffset, currFrame.depth));
        }
        return null;
    }

    @Override
    public Object visit(AST.ParDefn parDefn, Object arg) {
        parDefn.type.accept(this, arg);
        long size = sizeOf(SemAn.isTypeAttr.get(parDefn.type));
        FrameCtx currFrame = stack.peek();
        Memory.accessAttr.put(parDefn, new MEM.RelAccess(size, currFrame.parOffset, currFrame.depth));
        currFrame.parOffset += align(size);
        return null;
    }

    @Override
    public Object visit(AST.StrType strType, Object arg) {
        long offset = 0;
        long size;
        for (AST.CompDefn compDefn : strType.comps) {
            compDefn.type.accept(this, arg);
            size =  sizeOf(SemAn.isTypeAttr.get(compDefn.type));
            Memory.accessAttr.put(compDefn, new MEM.RelAccess(size, offset, -1));
            offset += align(size);
        }
        return null;
    }
    @Override
    public Object visit(AST.UniType uniType, Object arg) {
        long size;
        for (AST.CompDefn compDefn : uniType.comps) {
            compDefn.type.accept(this, arg);
            size =  sizeOf(SemAn.isTypeAttr.get(compDefn.type));
            Memory.accessAttr.put(compDefn, new MEM.RelAccess(size, 0, -1));
        }
        return null;
    }


    @Override
    public Object visit(AST.AtomExpr atomExpr, Object arg) {
        //string type only
        if (atomExpr.type == AST.AtomExpr.Type.STR) {
            // string constant — each char is 1 byte + null terminator, -2 za narekovaje iz atomExpr.value
            long size = ((long) atomExpr.value.length() + 1 - 2);
            Memory.stringAttr.put(atomExpr, new MEM.AbsAccess(size, new MEM.Label(), atomExpr.value));
        }
        return null;
    }

    @Override
    public Object visit(AST.CallExpr callExpr, Object arg) {
        callExpr.funExpr.accept(this, arg);
        for (AST.Expr argExpr : callExpr.argExprs)
            argExpr.accept(this, arg);
        if (!stack.isEmpty()) {
            long needed = 8 + (long) callExpr.argExprs.size() * 8;
            FrameCtx ctx = stack.peek();
            ctx.argsOffset = Math.max(ctx.argsOffset, needed);
        }
        return null;
    }

}
