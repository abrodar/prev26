package prev26lang.phase.seman;

import prev26lang.common.report.Report;
import prev26lang.phase.abstr.*;

import java.util.ArrayList;
import java.util.List;


// handles AST.Expr nodes → checks types → stores in ofTypeAttr, isConstAttr, isAddrAttr
// also resolves component names (CompExpr)
// also checks program has fun main():int
// runs after type constructor
public class TypeChecker implements AST.FullVisitor<Object, Object> {
    private final TypeConstructor tc;
    /** Constructs a new TypeChecke+r. */
    public TypeChecker(TypeConstructor tc) {
        this.tc = tc;
    }
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
        TYP.Type actual = t;
        if (actual instanceof TYP.NameType) {
            actual = actual.actualType();
        }
        return actual instanceof TYP.IntType
                || actual instanceof TYP.CharType
                || actual instanceof TYP.BoolType
                || actual instanceof TYP.PtrType
                || actual instanceof TYP.FunType;
    }
    // per TYP: 36
    private boolean isAssignable(TYP.Type type) {
        TYP.Type actual = type;
        if (actual instanceof TYP.NameType) {
            actual = actual.actualType();
        }
        return actual instanceof TYP.IntType ||
                actual instanceof TYP.CharType ||
                actual instanceof TYP.BoolType ||
                actual instanceof TYP.PtrType ||
                actual instanceof TYP.FunType;
    }
    public boolean isValidParType(TYP.Type type) {
        TYP.Type actual = type;
        if (actual instanceof TYP.NameType) {
            actual = type.actualType();
        }
        return actual instanceof TYP.IntType
                || actual instanceof TYP.CharType
                || actual instanceof TYP.BoolType
                || actual instanceof TYP.PtrType
                || actual instanceof TYP.FunType;
    }
    public boolean isValidResType(TYP.Type type) {
        TYP.Type actual = type;
        if (actual instanceof TYP.NameType) {
            actual = type.actualType();
        }
        return actual instanceof TYP.IntType
                || actual instanceof TYP.CharType
                || actual instanceof TYP.BoolType
                || actual instanceof TYP.PtrType
                || actual instanceof TYP.FunType
                || actual instanceof TYP.VoidType;
    }

    public void checkMain(AST.Nodes<? extends AST.Node> program) {
        for (AST.Node node : program) {
            if (node instanceof AST.DefFunDefn dfd && dfd.name.equals("main")) {
                TYP.Type retType = SemAn.isTypeAttr.get(dfd.type);
                if (dfd.pars.size() == 0 && equiv(retType, TYP.IntType.type))
                    return;
            }
        }
        throw new Report.Error("Program must define 'fun main() : int'.");
    }

    @Override
    public Object visit(AST.AtomExpr expr, Object arg) {
        switch (expr.type) {
            case INT -> {
                boolean underNegation = Boolean.TRUE.equals(arg);
                try {
                    if (underNegation) {
                        Long.parseLong("-" + expr.value);  // allows 2^63
                    } else {
                        Long.parseLong(expr.value);         // only allows up to 2^63-1
                    }
                } catch (NumberFormatException e) {
                    throw new Report.Error(expr, "Integer constant out of range.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.IntType.type);
                SemAn.isConstAttr.put(expr, true);
                SemAn.isAddrAttr.put(expr, false);
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
    public Object visit(AST.NameExpr nameExpr, Object arg) {
        AST.Defn defn = SemAn.defAtAttr.get(nameExpr);
        TYP.Type type;
        if (defn instanceof AST.VarDefn || defn instanceof AST.ParDefn) {
            type = SemAn.isTypeAttr.get(defn.type);
            SemAn.ofTypeAttr.put(nameExpr, type);
            SemAn.isConstAttr.put(nameExpr, false);
            SemAn.isAddrAttr.put(nameExpr, true);
        } else if (defn instanceof AST.FunDefn) {
            type = tc.funTypes.get(defn);
            SemAn.ofTypeAttr.put(nameExpr, type);
            SemAn.isConstAttr.put(nameExpr, false);
            SemAn.isAddrAttr.put(nameExpr, false);
        }
        return null;
    }

    @Override
    public Object visit(AST.PfxExpr expr, Object arg) {
        switch (expr.oper) {
            case ADD -> {
                expr.subExpr.accept(this, Boolean.FALSE);
                TYP.Type subType = SemAn.ofTypeAttr.get(expr.subExpr);
                if (!equiv(subType,TYP.IntType.type)) {
                    throw new Report.Error(expr, "Unary + requires int operand.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.IntType.type);
                SemAn.isConstAttr.put(expr, SemAn.isConstAttr.get(expr.subExpr));
                SemAn.isAddrAttr.put(expr, false);
            }
            case SUB -> {
                expr.subExpr.accept(this, Boolean.TRUE); // signal negation to AtomExpr for int size check
                TYP.Type subType = SemAn.ofTypeAttr.get(expr.subExpr);
                if (!equiv(subType,TYP.IntType.type)) {
                    throw new Report.Error(expr, "Unary +/- requires int operand.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.IntType.type);
                SemAn.isConstAttr.put(expr, SemAn.isConstAttr.get(expr.subExpr));
                SemAn.isAddrAttr.put(expr, false);
            }
            case NOT -> {
                expr.subExpr.accept(this, arg);
                TYP.Type subType = SemAn.ofTypeAttr.get(expr.subExpr);
                if (!equiv(subType,TYP.BoolType.type)) {
                    throw new Report.Error(expr, "Unary not requires boolean operand.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.BoolType.type);
                SemAn.isConstAttr.put(expr, SemAn.isConstAttr.get(expr.subExpr));
                SemAn.isAddrAttr.put(expr, false);
            }
            case PTR -> {
                expr.subExpr.accept(this, arg);
                TYP.Type subType = SemAn.ofTypeAttr.get(expr.subExpr);
                if (!SemAn.isAddrAttr.get(expr.subExpr)) {
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
                if (!(isComparable(fType))) {
                    throw new Report.Error(expr, "Operand type is not comparable. Comparison is allowed only on int, char, bool, ptr or fun types.");
                }
                SemAn.ofTypeAttr.put(expr, TYP.BoolType.type);
                SemAn.isConstAttr.put(expr, SemAn.isConstAttr.get(expr.fstExpr) && SemAn.isConstAttr.get(expr.sndExpr));
                SemAn.isAddrAttr.put(expr, false);
            }
        }
        return null;
    }
    @Override
    public Object visit(AST.ArrExpr expr, Object arg) {
        expr.arrExpr.accept(this, arg);
        expr.idx.accept(this, arg);
        TYP.Type aType = SemAn.ofTypeAttr.get(expr.arrExpr);
        TYP.Type iType = SemAn.ofTypeAttr.get(expr.idx);
        if (!(equiv(iType, TYP.IntType.type))) {
            throw new Report.Error(expr, "Array index must be of type int.");
        }
        if (!(SemAn.isAddrAttr.get(expr.arrExpr))) {
            throw new Report.Error(expr, "Array must be addressable.");
        }
        TYP.Type actual = aType;
        if (aType instanceof TYP.NameType) {
            actual = actual.actualType();
        }
        if (!(actual instanceof TYP.ArrType)) {
            throw new Report.Error(expr, "Expression is not an array, got: " + aType);
        }
        SemAn.ofTypeAttr.put(expr, ((TYP.ArrType) actual).elemType);
        SemAn.isConstAttr.put(expr, false);
        SemAn.isAddrAttr.put(expr, true);
        return null;
    }

    @Override
    public Object visit(AST.SfxExpr sfxExpr, Object arg) {
        sfxExpr.subExpr.accept(this, arg);
        TYP.Type sType =  SemAn.ofTypeAttr.get(sfxExpr.subExpr);
        // mora biti kazalec
        TYP.Type actual = sType;
        if (actual instanceof TYP.NameType) {
            actual = actual.actualType();
        }
        if (!(actual instanceof TYP.PtrType)) {
            throw new Report.Error(sfxExpr, "Cannot dereference a non-pointer type.");
        }
        TYP.Type baseType = ((TYP.PtrType) actual).baseType;
        // baza ne sme biti void
        if (baseType instanceof TYP.NameType) {
            baseType =  baseType.actualType();
        }
        if (baseType instanceof TYP.VoidType) {
            throw new Report.Error(sfxExpr, "Cannot dereference a pointer to void.");
        }
        // izraz ne sme biti konstanta
        if (SemAn.isConstAttr.get(sfxExpr.subExpr)) {
            throw new Report.Error(sfxExpr, "Cannot dereference a constant.");
        }

        SemAn.ofTypeAttr.put(sfxExpr, baseType);
        SemAn.isAddrAttr.put(sfxExpr, true);
        SemAn.isConstAttr.put(sfxExpr, false);
        return null;
    }

    @Override
    public Object visit(AST.CallExpr callExpr, Object arg) {
        // obisci izraz funkcije in argumente
        callExpr.funExpr.accept(this, arg);
        for (AST.Expr argExpr : callExpr.argExprs) {
            argExpr.accept(this, arg);
        }
        // funkcija mora biti tipa fun
        TYP.Type fType = SemAn.ofTypeAttr.get(callExpr.funExpr);
        TYP.Type actual = fType;
        if (actual instanceof TYP.NameType) {
            actual = actual.actualType();
        }
        if (!(actual instanceof TYP.FunType funType)) {
            throw new Report.Error(callExpr, "Expression is not callable.");
        }
        // prestej argumente
        if (funType.parTypes.size() != callExpr.argExprs.size()) {
            throw new Report.Error(callExpr, "Wrong number of arguments: expected "
                    + funType.parTypes.size() + ", got " + callExpr.argExprs.size() + ".");
        }
        // preglej ujemanje tipov argumentov
        for (int i = 0; i < funType.parTypes.size(); i++) {
            TYP.Type expected =  funType.parTypes.get(i);
            TYP.Type got = SemAn.ofTypeAttr.get(callExpr.argExprs.get(i));
            if (!(equiv(expected, got))) {
                throw new Report.Error(callExpr, "Argument no. " + (i+1) + " type mismatch. Expected: "+expected+", got: "+got+".");
            }
        }

        SemAn.ofTypeAttr.put(callExpr, funType.resType);
        SemAn.isConstAttr.put(callExpr, false);
        SemAn.isAddrAttr.put(callExpr, false);
        return null;
    }

    @Override
    public Object visit(AST.SizeExpr sizeExpr, Object arg) {
        sizeExpr.type.accept(this, arg);
        TYP.Type sType =  SemAn.isTypeAttr.get(sizeExpr.type); // Should be SemAn.isTypeAttr.get(sizeExpr.type).
        // ne sme biti void
        TYP.Type actual = sType;
        if (actual instanceof TYP.NameType) {
            actual = actual.actualType();
        }
        if (actual instanceof TYP.VoidType) {
            throw new Report.Error(sizeExpr, "Cannot take size of void type.");
        }
        SemAn.ofTypeAttr.put(sizeExpr, TYP.IntType.type);
        SemAn.isAddrAttr.put(sizeExpr, false);
        SemAn.isConstAttr.put(sizeExpr, true);
        return null;
    }

    @Override
    public Object visit(AST.CastExpr castExpr, Object arg) {
        System.out.println("TC checker castExpr.type identity: " + System.identityHashCode(castExpr.type));
        castExpr.expr.accept(this, arg);
        castExpr.type.accept(this, arg);
        TYP.Type eType =  SemAn.ofTypeAttr.get(castExpr.expr);
        TYP.Type tType = SemAn.isTypeAttr.get(castExpr.type);
        // oba ne smeta biti void
        TYP.Type eActual = eType;
        if (eActual instanceof TYP.NameType) {
            eActual = eActual.actualType();
        }
        if (eActual instanceof TYP.VoidType) {
            throw new Report.Error(castExpr, "Cannot cast void expression.");
        }
        TYP.Type tActual = tType;
        if (tActual instanceof TYP.NameType) {
            tActual = tActual.actualType();
        }
        if (tActual instanceof TYP.VoidType) {
            throw new Report.Error(castExpr, "Cannot cast expression to void.");
        }
        System.out.println("tType: " + tType + " tActual: " + tActual);
        SemAn.ofTypeAttr.put(castExpr, tActual);
        SemAn.isAddrAttr.put(castExpr, SemAn.isAddrAttr.get(castExpr.expr));
        SemAn.isConstAttr.put(castExpr, SemAn.isConstAttr.get(castExpr.expr));
        return null;
    }

    @Override
    public Object visit(AST.AsgnExpr asgnExpr, Object arg) {
        asgnExpr.fstExpr.accept(this, arg);
        asgnExpr.sndExpr.accept(this, arg);
        TYP.Type fType =  SemAn.ofTypeAttr.get(asgnExpr.fstExpr);
        TYP.Type sType = SemAn.ofTypeAttr.get(asgnExpr.sndExpr);
        if (!equiv(fType, sType)) {
            throw new Report.Error(asgnExpr, "Cannot assign non-equivalent types.");
        }
        if (!isAssignable(fType)) {
            throw new Report.Error(asgnExpr, "First expression is not assignable. Its type is: '"+fType+"' .");
        }
        if (!isAssignable(sType)) {
            throw new Report.Error(asgnExpr, "Second expression is not assignable. Its type is: '"+sType+"' .");
        }
        // prvi mora biti naslovljiv
        if (!SemAn.isAddrAttr.get(asgnExpr.fstExpr)) {
            throw new Report.Error(asgnExpr, "Cannot assign to non-addr type.");
        }
        SemAn.ofTypeAttr.put(asgnExpr, TYP.VoidType.type);
        SemAn.isAddrAttr.put(asgnExpr, false);
        SemAn.isConstAttr.put(asgnExpr, false);
        return null;
    }

    @Override
    public Object visit(AST.CompExpr compExpr, Object arg) {
        compExpr.recExpr.accept(this, arg);
        TYP.Type recType = SemAn.ofTypeAttr.get(compExpr.recExpr);

        if (!SemAn.isAddrAttr.get(compExpr.recExpr)) {
            throw new Report.Error(compExpr, "Record expression must be addressable.");
        }
        // unwrap
        TYP.Type actual = recType;
        if (actual instanceof TYP.NameType) {
            actual = actual.actualType();
        }
        // razresi imena komponent
        if (actual instanceof TYP.StrType strType) {
            AST.StrType astStr = tc.strTypeMap.get(strType);
            if (astStr == null) {
                throw new Report.Error(compExpr, "Cannot resolve struct components.");
            }
            for (int i = 0; i < astStr.comps.size(); i++) {
                AST.CompDefn comp = astStr.comps.get(i);
                if (comp.name.equals(compExpr.name)) {
                    //SemAn.defAtAttr.put(compExpr, comp); causes internal error due to keychecker isvaliddefat
                    SemAn.ofTypeAttr.put(compExpr, strType.compTypes.get(i));
                    SemAn.isConstAttr.put(compExpr, false);
                    SemAn.isAddrAttr.put(compExpr, true);
                    return null;
                }
            }
            throw new Report.Error(compExpr, "Component '"+compExpr.name+"' not found.");
        } else if (actual instanceof TYP.UniType uniType) {
            AST.UniType astUni = tc.uniTypeMap.get(uniType);
            if (astUni == null) {
                throw new Report.Error(compExpr, "Cannot resolve union components.");
            }
            for (int i = 0; i < astUni.comps.size(); i++) {
                AST.CompDefn comp = astUni.comps.get(i);
                if (comp.name.equals(compExpr.name)) {
                    //SemAn.defAtAttr.put(compExpr, comp);
                    SemAn.ofTypeAttr.put(compExpr, uniType.compTypes.get(i));
                    SemAn.isConstAttr.put(compExpr, false);
                    SemAn.isAddrAttr.put(compExpr, true);
                    return null;
                }
            }
            throw new  Report.Error(compExpr, "Component '"+compExpr.name+"' not found.");
        } else {
            throw new Report.Error(compExpr, "Expression is not a struct or union got: "+actual+".");
        }
    }

    @Override
    public Object visit(AST.Exprs exprs, Object arg) {
        boolean isconst = true;
        for (AST.Expr expr : exprs.exprs) {
            expr.accept(this, arg);
            TYP.Type eType = SemAn.ofTypeAttr.get(expr);
            if (isconst && !SemAn.isConstAttr.get(expr)) {
                isconst = false;
            }
        }
        AST.Expr lastExpr = exprs.exprs.get(exprs.exprs.size() - 1);
        TYP.Type lastType = SemAn.ofTypeAttr.get(lastExpr);
        SemAn.ofTypeAttr.put(exprs, lastType);
        SemAn.isAddrAttr.put(exprs, SemAn.isAddrAttr.get(lastExpr));
        if (isconst) {
            SemAn.isConstAttr.put(exprs, true);
        } else {
            SemAn.isConstAttr.put(exprs, false);
        }
        return null;
    }

    @Override
    public Object visit(AST.WhileExpr whileExpr, Object arg) {
        whileExpr.expr.accept(this, arg);
        whileExpr.condExpr.accept(this, arg);
        TYP.Type condType = SemAn.ofTypeAttr.get(whileExpr.condExpr);
        if (!equiv(condType, TYP.BoolType.type)) {
            throw new Report.Error(whileExpr, "While condition is not bool.");
        }
        // telo mora imeti veljaven tip (∈ T) — preverimo da ni null
        TYP.Type bodyType = SemAn.ofTypeAttr.get(whileExpr.expr);
        if (bodyType == null)
            throw new Report.Error(whileExpr, "While body has invalid type.");
        SemAn.ofTypeAttr.put(whileExpr, TYP.VoidType.type);
        SemAn.isConstAttr.put(whileExpr, false);
        SemAn.isAddrAttr.put(whileExpr, false);
        return null;
    }

    @Override
    public Object visit(AST.IfThenExpr ifThenExpr, Object arg) {
        ifThenExpr.thenExpr.accept(this, arg);
        ifThenExpr.condExpr.accept(this, arg);
        TYP.Type condType = SemAn.ofTypeAttr.get(ifThenExpr.condExpr);
        if (!equiv(condType, TYP.BoolType.type)) {
            throw new Report.Error(ifThenExpr, "If condition is not bool.");
        }
        TYP.Type bodyType = SemAn.ofTypeAttr.get(ifThenExpr.thenExpr);
        if (bodyType == null)
            throw new Report.Error(ifThenExpr, "If body has invalid type.");
        SemAn.ofTypeAttr.put(ifThenExpr, TYP.VoidType.type);
        SemAn.isConstAttr.put(ifThenExpr, false);
        SemAn.isAddrAttr.put(ifThenExpr, false);
        return null;
    }
    @Override
    public Object visit(AST.IfThenElseExpr ifThenElseExpr, Object arg) {
        ifThenElseExpr.thenExpr.accept(this, arg);
        ifThenElseExpr.condExpr.accept(this, arg);
        ifThenElseExpr.elseExpr.accept(this, arg);
        TYP.Type condType = SemAn.ofTypeAttr.get(ifThenElseExpr.condExpr);
        if (!equiv(condType, TYP.BoolType.type)) {
            throw new Report.Error(ifThenElseExpr, "If condition is not bool.");
        }
        TYP.Type thenType =  SemAn.ofTypeAttr.get(ifThenElseExpr.thenExpr);
        if (thenType == null)
            throw new Report.Error(ifThenElseExpr, "If's then part has invalid type. Check if null.");
        TYP.Type elseType = SemAn.ofTypeAttr.get(ifThenElseExpr.elseExpr);
        if (elseType == null)
            throw new Report.Error(ifThenElseExpr, "If's else part has invalid type. Check if null.");
        SemAn.ofTypeAttr.put(ifThenElseExpr, TYP.VoidType.type);
        SemAn.isConstAttr.put(ifThenElseExpr, false);
        SemAn.isAddrAttr.put(ifThenElseExpr, false);
        return null;
    }
    @Override
    public Object visit(AST.LetExpr letExpr, Object arg) {
        // definicije so ze obdelane v TypeConstructor tj. visit za letexpr
        // obiski definicij za TypeChecker
        for (AST.FullDefn defn : letExpr.defns) {
            defn.accept(this, arg);
        }
        letExpr.expr.accept(this, arg);
        // tip je zadnji izraz
        TYP.Type exprType =  SemAn.ofTypeAttr.get(letExpr.expr);
        if (exprType == null) {
            throw new Report.Error(letExpr, "Let body has invalid type.");
        }
        SemAn.ofTypeAttr.put(letExpr, exprType);
        SemAn.isConstAttr.put(letExpr, false);
        SemAn.isAddrAttr.put(letExpr, false);
        return null;
    }
    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        // najprej obisci parametre in telo in return type
        defFunDefn.type.accept(this, arg);
        for (AST.ParDefn par : defFunDefn.pars)
            par.accept(this, arg);
        defFunDefn.expr.accept(this, arg);

        // TYP:4 — tip zadnjega izraza mora biti ekvivalenten tipu rezultata
        // this check must be performed in typechecker!
        TYP.Type bodyType = SemAn.ofTypeAttr.get(defFunDefn.expr);
        TYP.FunType funType = tc.funTypes.get(defFunDefn);
        if (!equiv(funType.resType, bodyType))
            throw new Report.Error(defFunDefn, "Function '" + defFunDefn.name
                    + "' return type mismatch: declared " + funType.resType
                    + " but body returns " + bodyType + ".");
        return null;
    }
    @Override
    public Object visit(AST.PtrType ptrType, Object arg) {
        ptrType.baseType.accept(this, arg);
        TYP.Type baseType = SemAn.isTypeAttr.get(ptrType.baseType);
        // probably needs clarification
        if (baseType instanceof TYP.VoidType) {
            throw new Report.Error(ptrType, "Void type cannot be (directly) void.");
        }
        return null;
    }
    @Override
    public Object visit(AST.ArrType arrType, Object arg) {
        // typecheck elemType
        arrType.elemType.accept(this, arg);
        TYP.Type elemType = SemAn.isTypeAttr.get(arrType.elemType);
        if (equiv(elemType, TYP.VoidType.type)) {
            throw new Report.Error(arrType, "Array cannot have elements of type void.");
        }
        return null;
    }
    @Override
    public Object visit(AST.StrType strType, Object arg) {
        // preveri tipe komponent
        for (AST.CompDefn comp : strType.comps) {
            comp.accept(this, arg);
            TYP.Type compType =  SemAn.isTypeAttr.get(comp.type);
            // komponente strukture ne smejo biti void
            TYP.Type actual = compType.actualType();
            if (actual instanceof TYP.NameType) {
                actual = actual.actualType();
            }
            if (actual instanceof TYP.VoidType) {
                throw new Report.Error(comp, "Struct cannot have components of type void.");
            }
        }
        return null;
    }
    @Override
    public Object visit(AST.UniType uniType, Object arg) {
        // preveri tipe komponent
        for (AST.CompDefn comp : uniType.comps) {
            comp.accept(this, arg);
            TYP.Type compType =  SemAn.isTypeAttr.get(comp.type);
            // komponente uniona ne smejo biti void
            TYP.Type actual = compType;
            if (actual instanceof TYP.NameType) {
                actual = actual.actualType();
            }
            if (actual instanceof TYP.VoidType) {
                throw new Report.Error(comp, "Union cannot have components of type void.");
            }
        }
        return null;
    }
    @Override
    public Object visit(AST.FunType funType, Object arg) {
        funType.resType.accept(this, arg);
        TYP.Type resType = SemAn.isTypeAttr.get(funType.resType);
        if (!isValidResType(resType)) {
            throw new Report.Error(funType, "Invalid function result type "+resType+".");
        }
        for (AST.Type par: funType.parTypes) {
            par.accept(this, arg);
            TYP.Type parType = SemAn.isTypeAttr.get(par);
            if (!isValidParType(parType)) {
                throw new Report.Error(funType, "Invalid function parameter type " + parType + ".");
            }
        }
        return null;
    }
}
