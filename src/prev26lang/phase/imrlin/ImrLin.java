package prev26lang.phase.imrlin;

import prev26lang.phase.imrgen.IMR;

import java.util.List;
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
    // ESTMT(CONST(0)) but for prev26
    static ReorderResult nopNull =  new ReorderResult(new Vector<>(), new Vector<>());
    // reorder function takes a list of expressions and returns a pair of (statement, expression-list).
    // the statement contains all the things that must be executed before the expression-list.
    static ReorderResult reorder(Vector<IMR.Expr> exprs) {
        return null;
    }

    // do functions take SEXPR out of statements or expressions
    static IMR.SEXPR do_expr(IMR.Expr e) {
        if (e instanceof IMR.SEXPR se) { //SEXPR is a special case since it's the only expr with stmt child
            Vector<IMR.Stmt> stmts = do_stmt(se.stmt);
            IMR.SEXPR inner = do_expr(se.expr);
            stmts.addAll(inner.stmt instanceof IMR.STMTS st ? st.stmts : new Vector<>(List.of(inner.stmt)));
            return new IMR.SEXPR(new IMR.STMTS(stmts), inner.expr);
        }
        // everything else just call kids() and reorder
        Vector<IMR.Expr> l = e.kids(); // get immediate subexpressions of e in a list l
        ReorderResult result = reorder(l);
        return new IMR.SEXPR(new IMR.STMTS(result.stmts.isEmpty() ? nopNull.stmts : result.stmts), e.build(result.exprs));
    }
    static Vector<IMR.Stmt> do_stmt(IMR.Stmt s) {
        Vector<IMR.Expr> l = s.kids(); // get immediate subexpressions of s in a list l

    }
    // linearize removes eseqs and moves calls to top level
    static public IMR.STMTS linearize(IMR.Stmt s) {
        return null;
    }
}


