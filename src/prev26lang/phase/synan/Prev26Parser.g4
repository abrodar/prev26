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

source :    prog EOF;
prog :  defn+;
defn :  typeDecl
        | varDecl
        | funcDecl
        ;

typeDecl : TYP ID ASSIGN type;
varDecl : VAR ID COLON type ;
funcDecl : FUN ID LPAREN paramList? RPAREN COLON type (ASSIGN exprList)?;

paramList :   param (COMMA param)*;
param : ID COLON type;
typeList: (type (COMMA type)*)?;

type:   INT
        | CHAR
        | BOOL
        | VOID
        | LBRACK INT_CONST RBRACK type
        | POW type // mogoče kazalec
        | LPAREN paramList RPAREN //tuple type
        | LBRACE paramList? RBRACE //field type
        | LPAREN COLON typeList COLON type RPAREN //function type
        | LPAREN type RPAREN //recursive
        | ID
        ;

exprList :  expr (COMMA expr)*;

expr : assignExpr ;
assignExpr :    castExpr ASSIGN castExpr    // ne-asociativen
                | castExpr
                ;
castExpr :      castExpr AS type    // levo-asociativen
                | boolORExpr
                ;
boolORExpr :    boolORExpr OR boolANDExpr  // levo-asociativen
                | boolANDExpr
                ;
boolANDExpr :   boolANDExpr AND compExpr  // levo-asociativen
                | compExpr
                ;
compExpr :  addExpr (EQU|NEQ|LT|GT|LE|GE) addExpr   // ne-asociativen
            | addExpr
            ;
addExpr :   addExpr (ADD|SUB) mulExpr   // levo-asociativen
            | mulExpr
        ;
mulExpr :   mulExpr (MUL|DIV|MOD) prefixExpr // levo-asociativen
            | prefixExpr
            ;
prefixExpr :    (NOT|ADD|SUB|POW) prefixExpr // desno-asociativen
                | postfixExpr;
postfixExpr :   postfixExpr POW
                | postfixExpr LPAREN exprList? RPAREN
                | postfixExpr LBRACK expr RBRACK
                | postfixExpr DOT ID
                | basicExpr
                ;
basicExpr :  INT_CONST
             | CHAR_CONST
             | STRING_CONST
             | TRUE  // bool
             | FALSE // bool
             | NONE  // void
             | NIL   // ptr
             | LPAREN exprList RPAREN
             | SIZEOF type
             | IF expr THEN exprList END
             | IF expr THEN exprList ELSE exprList END
             | WHILE expr DO exprList END
             | LET prog IN exprList END
             | ID
          ;




