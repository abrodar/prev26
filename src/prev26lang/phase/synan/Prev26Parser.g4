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
            Abstr.locAttr.put($ast, loc($ds.get(0).ast, $ds.get($ds.size()-1).ast));
        }
        ;
defn returns [AST.FullDefn ast]
        : td=typeDecl { $ast = $td.ast; }
        | vd=varDecl { $ast = $vd.ast; }
        | fd=funcDecl { $ast = $fd.ast; }
        ;

typeDecl returns [AST.TypDefn ast]
        :
        TYP id=ID ASSIGN t=type
        {
        $ast = new TypDefn($id.text, $t.ast);
        Abstr.locAttr.put($ast, loc($TYP, $t.ast));
        }
        ;

varDecl returns [AST.VarDefn ast]
        :
        VAR id=ID COLON t=type
        {
        $ast = new VarDefn($id.text, $t.ast);
        Abstr.locAttr.put($ast, loc($VAR, $t.ast));
        }
        ;

funcDecl returns [AST.FunDefn ast]
        :
        FUN id1=ID LPAREN pars1=paramList? RPAREN COLON t1=type ASSIGN es=exprList
        {
        AST.Nodes<AST.ParDefn> p1;
        try {
            p1 = $pars1.ast;
        } catch (NullPointerException e) {
            p1 = new AST.Nodes<>();
            Abstr.locAttr.put(p1, loc($LPAREN, $RPAREN));
        }
        Exprs body = new Exprs($es.ast);
        Abstr.locAttr.put(body, loc($es.ast));
        $ast = new DefFunDefn($id1.text,  p1, $t1.ast, body);
        Abstr.locAttr.put($ast, loc($FUN, $es.ast));
        }
        | FUN id2=ID LPAREN pars2=paramList? RPAREN COLON t2=type
        {
        AST.Nodes<AST.ParDefn> p2;
        try {
            p2 = $pars2.ast;
        } catch (NullPointerException e) {
            p2 = new AST.Nodes<>();
            Abstr.locAttr.put(p2, loc($LPAREN, $RPAREN));
        }
        $ast = new ExtFunDefn($id2.text, p2, $t2.ast);
        Abstr.locAttr.put($ast, loc($FUN, $t2.ast));
        }
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
            Abstr.locAttr.put($ast, loc($ps.get(0).ast, $ps.get($ps.size()-1).ast));
            }
            ;
param   returns [AST.ParDefn ast]
        :
        id=ID COLON t=type
        {
        $ast = new ParDefn($id.text, $t.ast);
        Abstr.locAttr.put($ast, loc($id, $t.ast));
        }
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
            Abstr.locAttr.put($ast, loc($cs.get(0).ast, $cs.get($cs.size()-1).ast));
            }
            ;
comp   returns [AST.CompDefn ast]
        :
        id=ID COLON t=type
        {
        $ast = new CompDefn($id.text, $t.ast);
        Abstr.locAttr.put($ast, loc($id, $t.ast));
        }
        ;

typeList returns [AST.Nodes<AST.Type> ast]
        :
        (ts+=type (COMMA ts+=type)*)?
        {
        List<AST.Type> list = new ArrayList<>();
        if ($ts != null) {
            for (Prev26Parser.TypeContext tctx : $ts) {
                list.add(tctx.ast);
            }
        }
        $ast = new AST.Nodes<>(list);
        if (!list.isEmpty()) {
            Abstr.locAttr.put($ast, loc($ts.get(0).ast, $ts.get($ts.size()-1).ast));
        }
        }
        ;

type returns [AST.Type ast]
        :
        INT {
        $ast = new AtomType(AST.AtomType.Type.INT);
        Abstr.locAttr.put($ast, loc($INT));
        } // atomarni tip
        | CHAR {
        $ast = new AtomType(AST.AtomType.Type.CHAR);
        Abstr.locAttr.put($ast, loc($CHAR));
        } // atomarni tip
        | BOOL {
        $ast = new AtomType(AST.AtomType.Type.BOOL);
        Abstr.locAttr.put($ast, loc($BOOL));
        } // atomarni tip
        | VOID {
        $ast = new AtomType(AST.AtomType.Type.VOID);
        Abstr.locAttr.put($ast, loc($VOID));
        } // atomarni tip
        | LBRACK ic=INT_CONST RBRACK t=type
        {
        $ast = new ArrType($t.ast, $ic.text);
        Abstr.locAttr.put($ast, loc($LBRACK, $t.ast));
        } // tip tabele
        | POW t=type
        {
        $ast = new PtrType($t.ast);
        Abstr.locAttr.put($ast, loc($POW, $t.ast));
        } // tip kazalca
        | LPAREN cl1=compList RPAREN
        {
        $ast = new StrType($cl1.ast);
        Abstr.locAttr.put($ast, loc($LPAREN, $RPAREN));
        } // tip struct
        | LBRACE cl2=compList? RBRACE
        {
        AST.Nodes<AST.CompDefn> comps;
        try {
            comps = $cl2.ast;
        } catch (NullPointerException e) {
            comps = new AST.Nodes<>();
            Abstr.locAttr.put(comps, loc($LBRACE, $RBRACE));
        }
        $ast = new UniType(comps);
        Abstr.locAttr.put($ast, loc($LBRACE, $RBRACE));
        } // tip union
        | LPAREN COLON tl=typeList COLON t=type RPAREN
        {
        $ast = new FunType($tl.ast, $t.ast);
        Abstr.locAttr.put($tl.ast, loc($LPAREN, $RPAREN));
        Abstr.locAttr.put($ast, loc($LPAREN, $RPAREN));
        }// tip funkcije
        | LPAREN t=type RPAREN
        {
        $ast = $t.ast;
        Abstr.locAttr.put($ast, loc($LPAREN, $RPAREN));
        } // rekurzivni tip
        | id=ID
        {
        $ast = new NameType($id.text);
        Abstr.locAttr.put($ast, loc($id));
        } // ID je tip
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
        Abstr.locAttr.put($ast, loc($es.get(0).ast, $es.get($es.size()-1).ast));
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
                {
                $ast = new AsgnExpr($ce1.ast, $ce2.ast);
                Abstr.locAttr.put($ast, loc($ce1.ast, $ce2.ast));
                }
                | ce3=castExpr
                {
                $ast = $ce3.ast;
                }
                ;
castExpr    returns [AST.Expr ast]
                :
                ce=castExpr AS t=type    // levo-asociativen
                {
                $ast = new CastExpr($t.ast, $ce.ast);
                Abstr.locAttr.put($ast, loc($ce.ast, $t.ast));
                }
                | b=boolORExpr
                { $ast = $b.ast; }
                ;
boolORExpr  returns [AST.Expr ast]
                :
                b1=boolORExpr op=OR b2=boolANDExpr  // levo-asociativen
                {
                $ast = new BinExpr(AST.BinExpr.Oper.OR,$b1.ast,$b2.ast);
                Abstr.locAttr.put($ast, loc($b1.ast, $b2.ast));
                }
                | b3=boolANDExpr
                { $ast = $b3.ast; }
                ;
boolANDExpr returns [AST.Expr ast]
                :
                b1=boolANDExpr AND ce=compExpr  // levo-asociativen
                {
                $ast = new BinExpr(AST.BinExpr.Oper.AND, $b1.ast, $ce.ast);
                Abstr.locAttr.put($ast, loc($b1.ast, $ce.ast));
                }
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
                Abstr.locAttr.put($ast, loc($a1.ast, $a2.ast));
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
                Abstr.locAttr.put($ast, loc($a.ast, $m1.ast));
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
                Abstr.locAttr.put($ast, loc($me.ast, $pe1.ast));
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
                    Abstr.locAttr.put($ast, loc($op, $pe.ast));
                }
                | po=postfixExpr
                { $ast = $po.ast; }
                ;
postfixExpr returns [AST.Expr ast]
                :   po=postfixExpr POW
                {
                $ast = new SfxExpr(AST.SfxExpr.Oper.PTR, $po.ast);
                Abstr.locAttr.put($ast, loc($po.ast, $POW));
                }
                | po1=postfixExpr LPAREN el=exprList? RPAREN
                {
                 AST.Nodes<AST.Expr> args;
                 try {
                    args = $el.ast;
                 } catch (NullPointerException e) {
                    args = new AST.Nodes<>();
                    Abstr.locAttr.put(args, loc($LPAREN, $RPAREN));
                 }
                $ast = new CallExpr($po1.ast, args);
                Abstr.locAttr.put($ast, loc($po1.ast, $RPAREN));
                }
                | po2=postfixExpr LBRACK e=expr RBRACK
                {
                $ast = new ArrExpr($po2.ast, $e.ast);
                Abstr.locAttr.put($ast, loc($po2.ast, $RBRACK));
                }
                | po3=postfixExpr DOT id=ID
                {
                $ast = new CompExpr($po3.ast, $id.text);
                Abstr.locAttr.put($ast, loc($po3.ast, $id));
                }
                | b=basicExpr
                { $ast = $b.ast; }
                ;
basicExpr returns [AST.Expr ast]
             :
             ic=INT_CONST
             {
             $ast = new AtomExpr(AST.AtomExpr.Type.INT, $ic.text);
             Abstr.locAttr.put($ast, loc($ic));
             }
             | cc=CHAR_CONST
             {
             $ast = new AtomExpr(AST.AtomExpr.Type.CHAR, $cc.text);
             Abstr.locAttr.put($ast, loc($cc));
             }
             | sc=STRING_CONST
             {
             $ast = new AtomExpr(AST.AtomExpr.Type.STR, $sc.text);
             Abstr.locAttr.put($ast, loc($sc));
             }
             | bct=TRUE  // bool
             {
             $ast = new AtomExpr(AST.AtomExpr.Type.BOOL, $bct.text);
             Abstr.locAttr.put($ast, loc($bct));
             }
             | bcf=FALSE // bool
             {
             $ast = new AtomExpr(AST.AtomExpr.Type.BOOL, $bcf.text);
             Abstr.locAttr.put($ast, loc($bcf));
             }
             | vc=NONE  // void
             {
             $ast = new AtomExpr(AST.AtomExpr.Type.VOID, $vc.text);
             Abstr.locAttr.put($ast, loc($vc));
             }
             | nc=NIL   // ptr
             {
             $ast = new AtomExpr(AST.AtomExpr.Type.PTR, $nc.text);
             Abstr.locAttr.put($ast, loc($nc));
             }
             | LPAREN es=exprList RPAREN
             {
             $ast = new Exprs($es.ast);
             Abstr.locAttr.put($ast, loc($LPAREN, $RPAREN));
             }
             | SIZEOF t=type
             {
             $ast = new SizeExpr($t.ast);
             Abstr.locAttr.put($ast, loc($SIZEOF, $t.ast));
             }
             | IF e2=expr THEN es2=exprList END
             {
             Exprs body = new Exprs($es2.ast);
             Abstr.locAttr.put(body, loc($es2.ast));
             $ast = new IfThenExpr($e2.ast, body);
             Abstr.locAttr.put($ast, loc($IF, $END));
             }
             | IF e3=expr THEN es31=exprList ELSE es32= exprList END
             {
             Exprs body31 = new Exprs($es31.ast);
             Abstr.locAttr.put(body31, loc($es31.ast));
             Exprs body32 = new Exprs($es32.ast);
             Abstr.locAttr.put(body32, loc($es32.ast));
             $ast = new IfThenElseExpr($e3.ast, body31, body32);
             Abstr.locAttr.put($ast, loc($IF, $END));
             }
             | WHILE e4=expr DO es4=exprList END
             {
             Exprs body = new Exprs($es4.ast);
             Abstr.locAttr.put(body, loc($es4.ast));
             $ast = new WhileExpr($e4.ast, body);
             Abstr.locAttr.put($ast, loc($WHILE, $END));
             }
             | LET pg=prog IN es5=exprList END
             {
             Exprs body = new Exprs($es5.ast);
             Abstr.locAttr.put(body, loc($es5.ast));
             $ast = new LetExpr($pg.ast, body);
             Abstr.locAttr.put($ast, loc($LET, $END));
             }
             | id=ID
             {
             $ast = new NameExpr($id.text);
             Abstr.locAttr.put($ast, loc($id));
             }
          ;




