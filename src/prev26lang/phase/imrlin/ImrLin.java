package prev26lang.phase.imrlin;

import prev26lang.phase.Phase;
import prev26lang.phase.abstr.AST;
import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.imrgen.ImrGen;
import prev26lang.phase.memory.MEM;
import prev26lang.phase.memory.Memory;

import java.util.Vector;


public class ImrLin {

    public static Vector<LIN.Chunk> chunks = new Vector<>();


    public static void linearize(AST.Nodes<AST.FullDefn> tree) {
        tree.accept(new AST.FullVisitor<Object,Object>(){
            @Override
            public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
                IMR.Stmt body = ImrGen.genStmtIMRAttr.get(defFunDefn);
                if (body == null) return null;
                MEM.Frame frame = Memory.frameAttr.get(defFunDefn);
                MEM.Label entryLabel = ImrGen.bodyEntryLabelAttr.get(defFunDefn);
                MEM.Label exitLabel = ImrGen.bodyExitLabelAttr.get(defFunDefn);
                Vector<IMR.Stmt> stmts = ImrLinearize.linearize(body);
                chunks.add(new LIN.CodeChunk(frame, stmts, entryLabel, exitLabel));
                System.out.println("=== " + defFunDefn.name + " ===");
                for (IMR.Stmt s : stmts) {
                    System.out.println(s);
                }
                return null;
            }
            @Override
            public Object visit(AST.VarDefn varDefn, Object arg) {
                MEM.Access access = Memory.accessAttr.get(varDefn);
                if (access instanceof MEM.AbsAccess aa) {
                    chunks.add(new LIN.DataChunk(aa));
                }
                return null;
            }
            @Override
            public Object visit(AST.AtomExpr atomExpr, Object arg) {
                if (atomExpr.type == AST.AtomExpr.Type.STR) {
                    MEM.AbsAccess aa = Memory.stringAttr.get(atomExpr);
                    chunks.add(new LIN.DataChunk(aa));
                }
                return null;
            }
        }, null);
    }
}