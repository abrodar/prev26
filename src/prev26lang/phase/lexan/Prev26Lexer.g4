lexer grammar Prev26Lexer;

@header {
	package prev26lang.phase.lexan;
	import prev26lang.common.report.*;
}

@members {
    @Override
	public LexAn.LocLogToken nextToken() {
		return (LexAn.LocLogToken) super.nextToken();
	}
}
// LEKSIKALNA PRAVILA

// Konstante
INT_CONST : '0' | [1-9]DIGIT* ;
CHAR_CONST : '\'' (PCHAR_C | ESC ('\'' | ESC) | '\\x' HEXDIGIT HEXDIGIT) '\'' ;
STRING_CONST : '"' (PCHAR_S | ESC ('"' | ESC) | '\\x' HEXDIGIT HEXDIGIT)* '"' ;
// Simboli
DOT : '.' ;
COMMA : ',' ;
COLON : ':' ;
ASSIGN : '=' ;
MUL : '*' ;
DIV : '/' ;
ADD : '+' ;
SUB : '-' ;
MOD : '%' ;
EQU : '==';
NEQ : '!=' ;
LE : '<=' ;
GE : '>=' ;
LT : '<' ;
GT : '>' ;
LPAREN : '(' ;
RPAREN : ')' ;
LBRACE : '{' ;
RBRACE : '}' ;
LBRACK : '[' ;
RBRACK : ']' ;
POW : '^' ;
// Rezervirane besede
AND : 'and' ;
AS : 'as' ;
BOOL : 'bool' ;
DO : 'do' ;
CHAR : 'char' ;
ELSE : 'else' ;
END : 'end' ;
FALSE : 'false' ;
FUN : 'fun' ;
IF : 'if' ;
IN : 'in' ;
INT : 'int' ;
LET : 'let' ;
NIL : 'nil' ;
NONE : 'none' ;
NOT : 'not' ;
OR : 'or' ;
SIZEOF : 'sizeof' ;
THEN : 'then' ;
TRUE :  'true' ;
TYP : 'typ' ;
VAR : 'var' ;
VOID : 'void' ;
WHILE : 'while' ;
// Imena
ID : [a-zA-Z_][a-zA-Z0-9_]* ;
// Komentarji
COMMENT : '//' [\u0000-\u0009\u000b-\u007f]* '\n' -> skip ;
// Belo besedilo
WS : [ \t\r\n]+ -> skip ;
// Fragmenti - leksikalna pravila uporabljena v leksikalnih pravilih, ki sama po sebi niso žetoni
fragment
ASCII : [\u0000-\u007F] ;
fragment
DIGIT : [0-9] ;
fragment
HEXDIGIT : [0-9A-F] ;
fragment
PCHAR_C : [\u0020-\u0026\u0028-\u005b\u005d-\u007e] ; // izpisljivi znaki od ASCII [32 - 126], BREZ ' in \
fragment
PCHAR_S : [\u0020-\u0021\u0023-\u005b\u005d-\u007e] ; // izpisljivi znaki od ASCII [32 - 126], BREZ " in \
fragment
ESC : '\\' ;