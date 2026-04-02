package prev26lang.phase.memory;

import prev26lang.phase.abstr.AST;

public class Layouter implements AST.FullVisitor<Object, Object>{
    /** Constructs a layouter. */
    public Layouter() {
    }

    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        MEM.Label fLabel = new MEM.Label();
        long depth = 0;
        long locs_size = 0;
        long args_size = defFunDefn.pars.size();
        long frame_size = 0;
        MEM.Frame function_frame = new MEM.Frame(fLabel, depth, locs_size, args_size, frame_size);
        Memory.frameAttr.put(defFunDefn, function_frame);
        return null;
    }

    @Override
    public Object visit(AST.VarDefn varDefn, Object arg) {
        return null;
    }

    @Override
    public Object visit(AST.ParDefn parDefn, Object arg) {
        return null;
    }

    @Override
    public Object visit(AST.CompDefn compDefn, Object arg) {
        return null;
    }

    @Override
    public Object visit(AST.AtomExpr atomExpr, Object arg) {
        return null;
    }

}
