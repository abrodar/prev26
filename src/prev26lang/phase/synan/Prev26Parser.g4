/*
ANTLR pravila splošno:
 - DVOUMNOST : razreši z dodelitvijo prednosti prvemu specificiranemu pravilu (implictna prednost)
 - ASSOCIATIVNOST : privzeto levo-asociativen (*, + ...); lahko ročno: expr : expr '^' <assoc=right> expr;
 - LEVA REKURZIJA: v4 lahko obravnava le DIREKTNO levo rekurzijo (torej pravila se morajo na RHS takoj sklicati sama nase;
   ni dovoljeno, da pravilo referencira drugo pravilo, ki potem nazaj referencira prvo ne da bi prej ujel token)
*/
/*
expr : addExpr ;
addExpr : multExpr ('+' multExpr)* ;
multExpr: atom ('*' atom)* ;
atom : INT | (expr);

(nedvoumna, desno-rek.)
E   -> TE'
E' -> eps | +TE'
T -> FT'
T' -> eps | *FT'
F -> id | num | (E)
*/
parser grammar Prev26Parser;

@header {
    package prev26lang.phase.synan;
    import java.util.*;
    import prev26lang.common.report.*;
    import prev26lang.phase.lexan.*;
    import prev26lang.phase.abstr.*;
    import prev26lang.phase.abstr.AST.*;

}

@members {
    private Location loc(final Token tok) { return new Location((LexAn.LocLogToken)tok); }
    private Location loc(final Locatable loc) { return new Location(loc); }
    private Location loc(final Token tok1, final Token tok2) { return new Location((LexAn.LocLogToken)tok1, (LexAn.LocLogToken)tok2); }
    private Location loc(final Token tok1, final Locatable loc2) { if (loc2 == null) return null; return new Location((LexAn.LocLogToken)tok1, loc2); }
    private Location loc(final Locatable loc1, final Token tok2) { if (loc1 == null) return null; return new Location(loc1, (LexAn.LocLogToken)tok2); }
    private Location loc(final Locatable loc1, final Locatable loc2) { if ((loc1 == null) || (loc2 == null)) return null; return new Location(loc1, loc2); }

}
// Ker imamo lexer in parser v različnih datotekah uporabimo tokenVocab.
// Tako so potem leksikalni simboli sinhronizirani.
options{
    tokenVocab=Prev26Lexer;
}

source  returns [AST.Nodes<AST.FullDefn> ast]
        :
        prog EOF { $ast = $prog.ast; }
        ;
prog returns [AST.Nodes<AST.FullDefn> ast]
        :
        ds+=defn+
        {
            List<AST.FullDefn> list = new ArrayList<>();
            for (Prev26Parser.DefnContext dctx : $ds) {
                list.add(dctx.ast);
            }
            $ast = new AST.Nodes<>(list);
        }
        ;
defn returns [AST.FullDefn ast]
        : td=typeDecl { $ast = $td.ast; }
        | vd=varDecl { $ast = $vd.ast; }
        | fd=funcDecl { $ast = $fd.ast; }
        ;

typeDecl returns [AST.TypDefn ast]
        :
        TYP id=ID ASSIGN t=type { $ast = new TypDefn($id.text, $t.ast); }
        ;

varDecl returns [AST.VarDefn ast]
        :
        VAR id=ID COLON t=type { $ast = new VarDefn($id.text, $t.ast); }
        ;

funcDecl returns [AST.FunDefn ast]
        :
        FUN id1=ID LPAREN pars1=paramList? RPAREN COLON t1=type ASSIGN es=exprList
        { $ast = new DefFunDefn($id1.text,  $pars1.ast != null ? $pars1.ast : new AST.Nodes<AST.ParDefn>(), $t1.ast, new Exprs($es.ast)); }
        | FUN id2=ID LPAREN pars2=paramList? RPAREN COLON t2=type
        { $ast = new ExtFunDefn($id2.text,  $pars2.ast != null ? $pars2.ast : new AST.Nodes<AST.ParDefn>(), $t2.ast); }
        ;

paramList returns [AST.Nodes<AST.ParDefn> ast]
            :
            ps+=param (COMMA ps+=param)*
            {
            List<AST.ParDefn> list = new ArrayList<>();
            for (Prev26Parser.ParamContext pctx : $ps) {
                list.add(pctx.ast);
            }
            $ast = new AST.Nodes<>(list);
            }
            ;
param   returns [AST.ParDefn ast]
        :
        id=ID COLON t=type { $ast = new ParDefn($id.text, $t.ast); }
        ;

compList returns [AST.Nodes<AST.CompDefn> ast]
            :
            cs+=comp (COMMA cs+=comp)*
            {
            List<AST.CompDefn> list = new ArrayList<>();
            for (Prev26Parser.CompContext cctx : $cs) {
                list.add(cctx.ast);
            }
            $ast = new AST.Nodes<>(list);
            }
            ;
comp   returns [AST.CompDefn ast]
        :
        id=ID COLON t=type { $ast = new CompDefn($id.text, $t.ast); }
        ;

typeList returns [AST.Nodes<Type> ast]
        :
        (ts+=type (COMMA ts+=type)*)?
        {
        List<AST.Type> list = new ArrayList<>();
        if ($ts != null) {
            for (Prev26Parser.TypeContext tctx : $ts) {
                list.add(tctx.ast);
            }
        }
        $ast = new Nodes(list);
        }
        ;

type returns [AST.Type ast]
        :
        INT { $ast = new AtomType(AST.AtomType.Type.INT); } // atomarni tip
        | CHAR { $ast = new AtomType(AST.AtomType.Type.CHAR); } // atomarni tip
        | BOOL { $ast = new AtomType(AST.AtomType.Type.BOOL); } // atomarni tip
        | VOID { $ast = new AtomType(AST.AtomType.Type.VOID); } // atomarni tip
        | LBRACK ic=INT_CONST RBRACK t=type { $ast = new ArrType($t.ast, $ic.text); } // tip tabele
        | POW t=type { $ast = new PtrType($t.ast); } // tip kazalca
        | LPAREN cl1=compList RPAREN { $ast = new UniType($cl1.ast); } // tip ??? union, struct, n-tuple???
        | LBRACE cl2=compList? RBRACE { $ast = new StrType($cl2.ast == null ? new AST.Nodes<>() : $cl2.ast); } // tip ???
        | LPAREN COLON tl=typeList COLON t=type RPAREN { $ast = new FunType($tl.ast, $t.ast); }// tip funkcije
        | LPAREN t=type RPAREN { $ast = $t.ast; } // rekurzivni tip
        | id=ID { $ast = new NameType($id.text); } // ID je tip
        ;

exprList returns [AST.Nodes<AST.Expr> ast]
        :
        es+=expr (COMMA es+=expr)*
        {
        List<AST.Expr> list = new ArrayList<>();
        for (Prev26Parser.ExprContext ectx : $es) {
            list.add(ectx.ast);
        }
        $ast = new AST.Nodes<>(list);
        }
        ;

expr returns [AST.Expr ast]
    :
    as=assignExpr
    { $ast = $as.ast; }
    ;
assignExpr  returns [AST.Expr ast]
                :
                ce1=castExpr ASSIGN ce2=castExpr // ne-asociativen
                { $ast = new AsgnExpr($ce1.ast, $ce2.ast);  }
                | ce3=castExpr
                { $ast = $ce3.ast; }
                ;
castExpr    returns [AST.Expr ast]
                :
                ce=castExpr AS t=type    // levo-asociativen
                { $ast = new CastExpr($t.ast, $ce.ast); }
                | b=boolORExpr
                { $ast = $b.ast; }
                ;
boolORExpr  returns [AST.Expr ast]
                :
                b1=boolORExpr op=OR b2=boolANDExpr  // levo-asociativen
                { $ast = new BinExpr(AST.BinExpr.Oper.OR,$b1.ast,$b2.ast); }
                | b3=boolANDExpr
                { $ast = $b3.ast; }
                ;
boolANDExpr returns [AST.Expr ast]
                :
                b1=boolANDExpr AND ce=compExpr  // levo-asociativen
                { $ast = new BinExpr(AST.BinExpr.Oper.AND, $b1.ast, $ce.ast); }
                | ce2=compExpr
                { $ast = $ce2.ast; }
                ;
compExpr returns [AST.Expr ast]
            :
            a1=addExpr op=(EQU|NEQ|LT|GT|LE|GE) a2=addExpr   // ne-asociativen
            {
                AST.BinExpr.Oper oper = switch($op.type) {
                    case Prev26Parser.EQU -> AST.BinExpr.Oper.EQU;
                    case Prev26Parser.NEQ -> AST.BinExpr.Oper.NEQ;
                    case Prev26Parser.LT -> AST.BinExpr.Oper.LTH;
                    case Prev26Parser.GT -> AST.BinExpr.Oper.GTH;
                    case Prev26Parser.LE -> AST.BinExpr.Oper.LEQ;
                    case Prev26Parser.GE -> AST.BinExpr.Oper.GEQ;
                    default -> throw new Report.InternalError();
                };
                $ast = new BinExpr(oper, $a1.ast, $a2.ast);
            }
            | ae=addExpr
            {
                $ast = $ae.ast;
            }
            ;
addExpr returns [AST.Expr ast]
            :
            a=addExpr op=(ADD|SUB) m1=mulExpr   // levo-asociativen
            {
                AST.BinExpr.Oper oper = switch($op.type) {
                    case Prev26Parser.ADD -> AST.BinExpr.Oper.ADD;
                    case Prev26Parser.SUB -> AST.BinExpr.Oper.SUB;
                    default -> throw new Report.InternalError();
                };
                $ast = new BinExpr(oper, $a.ast, $m1.ast);
            }
            | m2= mulExpr
            { $ast = $m2.ast; }
        ;
mulExpr returns [AST.Expr ast]
            :
            me=mulExpr op=(MUL|DIV|MOD) pe1=prefixExpr // levo-asociativen
            {
                AST.BinExpr.Oper oper = switch($op.type) {
                    case Prev26Parser.MUL -> AST.BinExpr.Oper.MUL;
                    case Prev26Parser.DIV -> AST.BinExpr.Oper.DIV;
                    case Prev26Parser.MOD -> AST.BinExpr.Oper.MOD;
                    default -> throw new Report.InternalError();
                };
                $ast = new BinExpr(oper, $me.ast, $pe1.ast);
            }
            | pe2=prefixExpr
            { $ast = $pe2.ast; }
            ;
prefixExpr returns [AST.Expr ast]
                :
                op=(NOT|ADD|SUB|POW) pe=prefixExpr // desno-asociativen
                {
                    AST.PfxExpr.Oper oper = switch($op.type) {
                        case Prev26Parser.NOT -> AST.PfxExpr.Oper.NOT;
                        case Prev26Parser.ADD -> AST.PfxExpr.Oper.ADD;
                        case Prev26Parser.SUB -> AST.PfxExpr.Oper.SUB;
                        case Prev26Parser.POW -> AST.PfxExpr.Oper.PTR;
                        default -> throw new Report.InternalError();
                    };
                    $ast = new PfxExpr(oper, $pe.ast);
                }
                | po=postfixExpr
                { $ast = $po.ast; }
                ;
postfixExpr returns [AST.Expr ast]
                :   po=postfixExpr POW
                { $ast = new SfxExpr(AST.SfxExpr.Oper.PTR, $po.ast); }
                | po1=postfixExpr LPAREN el=exprList? RPAREN
                { $ast = new CallExpr($po1.ast, $el.ast == null ? new AST.Nodes<>() : $el.ast); }
                | po2=postfixExpr LBRACK e=expr RBRACK
                { $ast = new ArrExpr($po2.ast, $e.ast); }
                | po3=postfixExpr DOT id=ID
                { $ast = new CompExpr($po3.ast, $id.text); }
                | b=basicExpr
                { $ast = $b.ast; }
                ;
basicExpr returns [AST.Expr ast]
             :
             ic=INT_CONST
             { $ast = new AtomExpr(AST.AtomExpr.Type.INT, $ic.text); }
             | cc=CHAR_CONST
             { $ast = new AtomExpr(AST.AtomExpr.Type.CHAR, $cc.text); }
             | sc=STRING_CONST
             { $ast = new AtomExpr(AST.AtomExpr.Type.STR, $sc.text); }
             | bct=TRUE  // bool
             { $ast = new AtomExpr(AST.AtomExpr.Type.BOOL, $bct.text); }
             | bcf=FALSE // bool
             { $ast = new AtomExpr(AST.AtomExpr.Type.BOOL, $bcf.text); }
             | vc=NONE  // void
             { $ast = new AtomExpr(AST.AtomExpr.Type.VOID, $vc.text); }
             | nc=NIL   // ptr
             { $ast = new AtomExpr(AST.AtomExpr.Type.PTR, $nc.text); }
             | LPAREN es=exprList RPAREN
             { $ast = new Exprs($es.ast); }
             | SIZEOF t=type
             { $ast = new SizeExpr($t.ast); }
             | IF e2=expr THEN es2=exprList END
             { $ast = new IfThenExpr($e2.ast, new Exprs($es2.ast)); }
             | IF e3=expr THEN es31=exprList ELSE es32= exprList END
             { $ast = new IfThenElseExpr($e3.ast, new Exprs($es31.ast), new Exprs($es32.ast)); }
             | WHILE e4=expr DO es4=exprList END
             { $ast = new WhileExpr($e4.ast, new Exprs($es4.ast)); }
             | LET pg=prog IN es5=exprList END
             { $ast = new LetExpr($pg.ast, new Exprs($es5.ast));  }
             | id=ID
             { $ast = new NameExpr($id.text); }
          ;




