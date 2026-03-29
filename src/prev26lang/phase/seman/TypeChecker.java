package prev26lang.phase.seman;

import prev26lang.common.report.Report;
import prev26lang.phase.abstr.*;

// handles AST.Expr nodes → checks types → stores in ofTypeAttr, isConstAttr, isAddrAttr
// also resolves component names (CompExpr)
// also checks program has fun main():int
// runs after type constructor
public class TypeChecker implements AST.FullVisitor<Object, Object> {
    // helpers
    private boolean equiv(TYP.Type a, TYP.Type b) {
        // EQU:1
        if (a == b) return true;
        // unwrap name types - EQU:2, EQU:3
        if (a instanceof TYP.NameType na) return equiv(na.type(), b);
        if (b instanceof TYP.NameType nb) return equiv(a, nb.type());
        // EQU:4 - arrays
        if (a instanceof TYP.ArrType aa && b instanceof TYP.ArrType ab)
            return aa.numElems.equals(ab.numElems) && equiv(aa.elemType, ab.elemType);
        // EQU:5 - pointers
        if (a instanceof TYP.PtrType pa && b instanceof TYP.PtrType pb)
            return equiv(pa.baseType, pb.baseType);
        // EQU:6 - structs
        if (a instanceof TYP.StrType sa && b instanceof TYP.StrType sb) {
            if (sa.compTypes.size() != sb.compTypes.size()) return false;
            for (int i = 0; i < sa.compTypes.size(); i++)
                if (!equiv(sa.compTypes.get(i), sb.compTypes.get(i))) return false;
            return true;
        }
        // EQU:7 - unions
        if (a instanceof TYP.UniType ua && b instanceof TYP.UniType ub) {
            if (ua.compTypes.size() != ub.compTypes.size()) return false;
            for (int i = 0; i < ua.compTypes.size(); i++)
                if (!equiv(ua.compTypes.get(i), ub.compTypes.get(i))) return false;
            return true;
        }
        // EQU:8 - functions
        if (a instanceof TYP.FunType fa && b instanceof TYP.FunType fb) {
            if (fa.parTypes.size() != fb.parTypes.size()) return false;
            for (int i = 0; i < fa.parTypes.size(); i++)
                if (!equiv(fa.parTypes.get(i), fb.parTypes.get(i))) return false;
            return equiv(fa.resType, fb.resType);
        }
        return false;
    }
    // per TYP:25 - τ' ∈ {int, char, bool} ∪ {ptr, fun}
    private boolean isComparable(TYP.Type t) {
        TYP.Type actual = t.actualType();
        return actual instanceof TYP.IntType
                || actual instanceof TYP.CharType
                || actual instanceof TYP.BoolType
                || actual instanceof TYP.PtrType
                || actual instanceof TYP.FunType;
    }
    /** Constructs a new TypeChecke+r. */
    public TypeChecker() {
    }

    @Override
    public Object visit(AST.AtomExpr expr, Object arg) {
        switch (expr.type) {
            case INT -> {
                SemAn.ofTypeAttr.put(expr, TYP.IntType.type);
                SemAn.isConstAttr.put(expr, true);
                SemAn.isAddrAttr.put(expr, false);
            }
            case CHAR -> {
                SemAn.ofTypeAttr.put(expr, TYP.CharType.type);
                SemAn.isConstAttr.put(expr, true);
                SemAn.isAddrAttr.put(expr, false);
            }
            case BOOL -> {
                SemAn.ofTypeAttr.put(expr, TYP.BoolType.type);
                SemAn.isConstAttr.put(expr, true);
                SemAn.isAddrAttr.put(expr, false);
            }
            // string
            case AST.AtomExpr.Type.STR -> {
                TYP.Type type = new TYP.PtrType(TYP.CharType.type);
                SemAn.ofTypeAttr.put(expr, type);
                SemAn.isConstAttr.put(expr, true);
                SemAn.isAddrAttr.put(expr, false);
            }
            // nil
            case AST.AtomExpr.Type.PTR -> {
                TYP.Type type = new TYP.PtrType(TYP.VoidType.type);
                SemAn.ofTypeAttr.put(expr, type);
                SemAn.isConstAttr.put(expr, true);
                SemAn.isAddrAttr.put(expr, false);
            }

            case VOID -> {
                SemAn.ofTypeAttr.put(expr, TYP.VoidType.type);
                SemAn.isConstAttr.put(expr, true);
                SemAn.isAddrAttr.put(expr, false);
            }
        }
        return null;
    }

    @Override
    public Object visit(AST.PfxExpr expr, Object arg) {
        expr.subExpr.accept(this, arg);
        TYP.Type subType = SemAn.ofTypeAttr.get(expr.subExpr);
        switch (expr.oper) {
            case ADD, SUB -> {
                if (!equiv(subType,TYP.IntType.type)) {
                    throw new Report.Error(expr, "Unary +/- requires int operand.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.IntType.type);
                SemAn.isConstAttr.put(expr, SemAn.isConstAttr.get(expr.subExpr));
                SemAn.isAddrAttr.put(expr, false);
            }
            case NOT -> {
                if (!equiv(subType,TYP.BoolType.type)) {
                    throw new Report.Error(expr, "Unary not requires boolean operand.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.BoolType.type);
                SemAn.isConstAttr.put(expr, SemAn.isConstAttr.get(expr.subExpr));
                SemAn.isAddrAttr.put(expr, false);
            }
            case PTR -> {
                if (SemAn.isAddrAttr.get(expr)) {
                    throw new Report.Error(expr, "Cannot take address of non-addresable expression.");
                }
                if (equiv(subType,TYP.VoidType.type)) {
                    throw new Report.Error(expr, "Cannot take address of void expression..");
                }
                TYP.Type type = new TYP.PtrType(subType);
                SemAn.ofTypeAttr.put(expr, type);
                SemAn.isConstAttr.put(expr, false);
                SemAn.isAddrAttr.put(expr, false);
            }
        }
        return null;
    }
    @Override
    public Object visit(AST.BinExpr expr, Object arg) {
        expr.fstExpr.accept(this, arg);
        expr.sndExpr.accept(this, arg);
        TYP.Type fType = SemAn.ofTypeAttr.get(expr.fstExpr);
        TYP.Type sType = SemAn.ofTypeAttr.get(expr.sndExpr);
        switch (expr.oper) {
            case AND, OR -> {
                if (!(equiv(fType, TYP.BoolType.type))) {
                    throw new Report.Error(expr, "Left operand of AND/OR must be bool.");
                }
                if (!(equiv(sType, TYP.BoolType.type))) {
                    throw new Report.Error(expr, "Right operand of AND/OR must be bool.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.BoolType.type);
                SemAn.isConstAttr.put(expr, SemAn.isConstAttr.get(expr.fstExpr) && SemAn.isConstAttr.get(expr.sndExpr));
                SemAn.isAddrAttr.put(expr, false);
            }
            case MUL, DIV, MOD, ADD, SUB -> {
                if (!(equiv(fType, TYP.IntType.type))) {
                    throw new Report.Error(expr, "Left operand of arithmetic operator must be int.");
                }
                if (!equiv(sType, TYP.IntType.type)) {
                    throw new Report.Error(expr, "Right operand of arithmetic operator must be int.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.IntType.type);
                SemAn.isConstAttr.put(expr, SemAn.isConstAttr.get(expr.fstExpr) && SemAn.isConstAttr.get(expr.sndExpr));
                SemAn.isAddrAttr.put(expr, false);
            }
            case EQU, NEQ, GTH, LTH, GEQ, LEQ -> {
                if (!(equiv(fType, sType))) {
                    throw new Report.Error(expr, "Comparison requires equal types of operands.");
                }
            }
        }
        return null;
    }
}
