package prev26lang.phase.imrlin;

import prev26lang.phase.imrgen.IMR;

import java.util.Vector;

public class ImrLin {
    // record as helpre for reorder
    static record ReorderResult(Vector<IMR.Stmt> stmts, Vector<IMR.Expr> exprs) {}

    static boolean isNop(IMR.Stmt s) {
        return s instanceof IMR.ESTMT && ((IMR.ESTMT)s).expr instanceof IMR.CONST;
    }
    static boolean commute(IMR.Stmt a, IMR.Expr b) {
        return isNop(a) || b instanceof IMR.NAME || b instanceof IMR.CONST;
    }
    // reorder function takes a list of expressions and returns a pair of (statement, expression-list).
    // the statement contains all the things that must be executed before the expression-list.
    static ReorderResult reorder(Vector<IMR.Expr> exprs) {
        Vector<IMR.Stmt> stmts = new Vector<>();
        for (IMR.Expr expr : exprs) {
            if (expr instanceof IMR.SEXPR) {
                stmts.add(((IMR.SEXPR) expr).stmt);
            }
        }
    }

    // linearize removes eseqs and moves calls to top level
    static public IMR.STMTS linearize(IMR.Stmt s) {
        return null;
    }
}


