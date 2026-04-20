package prev26lang.phase.imrlin;

import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.memory.MEM;

import java.util.List;
import java.util.Vector;

public class ImrLinearize {
    static public <T> Vector<T> prepend(T head, Vector<T> tail) {
        Vector<T> list = new Vector<>();
        list.add(head);
        list.addAll(tail);
        return list;
    }
    static Vector<IMR.Stmt> flattenStmt(IMR.Stmt s) {
        if (s instanceof IMR.STMTS st) {
            Vector<IMR.Stmt> result = new Vector<>();
            for (IMR.Stmt child : st.stmts)
                result.addAll(flattenStmt(child));
            return result;
        }
        Vector<IMR.Stmt> result = new Vector<>();
        result.add(s);
        return result;
    }
    // record as helper for reorder
    static record ReorderResult(Vector<IMR.Stmt> stmts, Vector<IMR.Expr> exprs) {}

    static boolean isNop(IMR.Stmt s) {
        return (s instanceof IMR.ESTMT && ((IMR.ESTMT)s).expr instanceof IMR.CONST) ||
                (s instanceof IMR.STMTS st && st.stmts.isEmpty());
    }
    static boolean commute(IMR.Stmt a, IMR.Expr b) {
        return isNop(a) || b instanceof IMR.NAME || b instanceof IMR.CONST;
    }
    // ESTMT(CONST(0)) but for prev26
    static ReorderResult nopNull =  new ReorderResult(new Vector<>(), new Vector<>());
    // reorder function takes a list of expressions and returns a pair of (statement, expression-list).
    // the statement contains all the things that must be executed before the expression-list.
    // ! reorder never sees any CALL that is already the immediate child of a move
    static ReorderResult reorder(Vector<IMR.Expr> exprs) {
        if (exprs.isEmpty()) {
            return nopNull;
        }
        IMR.Expr head = exprs.get(0);
        Vector<IMR.Expr> tail = new Vector<>(exprs.subList(1, exprs.size()));
        if (head instanceof IMR.CALL) {
            // store result in a fresh temp
            IMR.TEMP t = new IMR.TEMP(new MEM.Temp());
            IMR.Expr e = new IMR.SEXPR(new IMR.MOVE(t, head), t);
            // call reorder on the eseq again to percolate the MOVE up
            Vector<IMR.Expr> result = new Vector<>();
            result.add(e);
            result.addAll(tail);
           // System.out.println("call node as head expressions vector: " + result);
            return reorder(result);
        } else {
            IMR.SEXPR aa = do_expr(head);
            ReorderResult rest = reorder(tail);
            if (commute(new IMR.STMTS(rest.stmts),aa.expr)) {
                // safe to reorder — s.expr can go after rest.stmts
                Vector<IMR.Stmt> stmts = flattenStmt(aa.stmt);
                stmts.addAll(rest.stmts);
                Vector<IMR.Expr> exprsOut = prepend(aa.expr, rest.exprs);
                //System.out.println("statements vector: "+stmts+" expressions vector: " + exprsOut);
                return new ReorderResult(stmts, exprsOut);
            } else {
                // must eval head rn and store it in new temp
                IMR.TEMP t = new IMR.TEMP(new MEM.Temp());
                Vector<IMR.Stmt> stmts = flattenStmt(aa.stmt);
                stmts.add(new IMR.MOVE(t, aa.expr));
                stmts.addAll(rest.stmts);
                Vector<IMR.Expr> exprsOut = prepend(t, rest.exprs);
                //System.out.println("statements vector: "+stmts+" expressions vector: " + exprsOut);
                return new ReorderResult(stmts, exprsOut);
            }
        }
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

    static public Vector<IMR.Stmt> do_stmt(IMR.Stmt s) {
        if (s instanceof IMR.MOVE moveCall && moveCall.dst instanceof IMR.TEMP t && moveCall.src instanceof IMR.CALL c) {
            //makes sure reorder doesn't see CALL node but only f and args as kids
            ReorderResult rr = reorder(c.kids());
            IMR.Expr newCall = c.build(rr.exprs);
            Vector<IMR.Stmt> result = new Vector<>(rr.stmts);
            result.add(new IMR.MOVE(t, newCall));
            return result;
        } else if (s instanceof IMR.MOVE mv &&
                (mv.dst instanceof IMR.MEM8 || mv.dst instanceof IMR.MEM1) &&
                mv.src instanceof IMR.CALL c) {
            ReorderResult rr = reorder(c.kids());
            IMR.Expr newCall = c.build(rr.exprs);
            Vector<IMR.Stmt> result = new Vector<>(rr.stmts);
            result.add(new IMR.MOVE(mv.dst, newCall));
            return result;
        } else if (s instanceof IMR.MOVE mv &&
                (mv.dst instanceof IMR.MEM8 || mv.dst instanceof IMR.MEM1)) {
            // MOVE(MEM8/MEM1, non-CALL src) — canonicalize dst addr and src separately
            IMR.Expr dstAddr = mv.dst instanceof IMR.MEM8 m8 ? m8.addr : ((IMR.MEM1)mv.dst).addr;
            IMR.SEXPR dstS = do_expr(dstAddr);
            IMR.SEXPR srcS = do_expr(mv.src);
            Vector<IMR.Stmt> result = flattenStmt(dstS.stmt);
            result.addAll(flattenStmt(srcS.stmt));
            IMR.Expr newDst = mv.dst instanceof IMR.MEM8
                    ? new IMR.MEM8(dstS.expr)
                    : new IMR.MEM1(dstS.expr);
            result.add(new IMR.MOVE(newDst, srcS.expr));
            return result;
        } else if (s instanceof IMR.ESTMT es && es.expr instanceof IMR.CALL c) {
            ReorderResult rr = reorder(c.kids());
            IMR.Expr newCall = c.build(rr.exprs);
            Vector<IMR.Stmt> result = new Vector<>(rr.stmts);
            result.add(new IMR.ESTMT(newCall));
            return result;
        } else if (s instanceof IMR.STMTS ss) {
            Vector<IMR.Stmt> result = new Vector<>();
            for (IMR.Stmt stmt : ss.stmts) {
                result.addAll(do_stmt(stmt));
            }
            return result;
        }
        // general case
        Vector<IMR.Expr> kids = s.kids();
        ReorderResult rr = reorder(kids);
        Vector<IMR.Stmt> result = new Vector<>(rr.stmts);
        result.add(s.build(rr.exprs));
        return result;
    }

    // linearize removes eseqs and moves calls to top level
    static public Vector<IMR.Stmt> linearize(IMR.Stmt s) {
        return do_stmt(s);
    }
}


