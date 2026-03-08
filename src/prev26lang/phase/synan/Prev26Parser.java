// Generated from Prev26Parser.g4 by ANTLR 4.13.2


    package prev26lang.phase.synan;
    
    import java.util.*;
    import prev26lang.common.report.*;
    import prev26lang.phase.lexan.*;
    import prev26lang.phase.abstr.*;


import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"doclint:missing", "all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class Prev26Parser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		INT_CONST=1, CHAR_CONST=2, STRING_CONST=3, DOT=4, COMMA=5, COLON=6, ASSIGN=7, 
		MUL=8, DIV=9, ADD=10, SUB=11, MOD=12, EQU=13, NEQ=14, LE=15, GE=16, LT=17, 
		GT=18, LPAREN=19, RPAREN=20, LBRACE=21, RBRACE=22, LBRACK=23, RBRACK=24, 
		POW=25, AND=26, AS=27, BOOL=28, DO=29, CHAR=30, ELSE=31, END=32, FALSE=33, 
		FUN=34, IF=35, IN=36, INT=37, LET=38, NIL=39, NONE=40, NOT=41, OR=42, 
		SIZEOF=43, THEN=44, TRUE=45, TYP=46, VAR=47, VOID=48, WHILE=49, ID=50, 
		COMMENT=51, WS=52;
	public static final int
		RULE_source = 0, RULE_prog = 1, RULE_defn = 2, RULE_typeDecl = 3, RULE_varDecl = 4, 
		RULE_funcDecl = 5, RULE_paramList = 6, RULE_param = 7, RULE_typeList = 8, 
		RULE_type = 9, RULE_exprList = 10, RULE_expr = 11, RULE_assignExpr = 12, 
		RULE_castExpr = 13, RULE_boolORExpr = 14, RULE_boolANDExpr = 15, RULE_compExpr = 16, 
		RULE_addExpr = 17, RULE_mulExpr = 18, RULE_prefixExpr = 19, RULE_postfixExpr = 20, 
		RULE_basicExpr = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "prog", "defn", "typeDecl", "varDecl", "funcDecl", "paramList", 
			"param", "typeList", "type", "exprList", "expr", "assignExpr", "castExpr", 
			"boolORExpr", "boolANDExpr", "compExpr", "addExpr", "mulExpr", "prefixExpr", 
			"postfixExpr", "basicExpr"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, null, null, null, "'.'", "','", "':'", "'='", "'*'", "'/'", "'+'", 
			"'-'", "'%'", "'=='", "'!='", "'<='", "'>='", "'<'", "'>'", "'('", "')'", 
			"'{'", "'}'", "'['", "']'", "'^'", "'and'", "'as'", "'bool'", "'do'", 
			"'char'", "'else'", "'end'", "'false'", "'fun'", "'if'", "'in'", "'int'", 
			"'let'", "'nil'", "'none'", "'not'", "'or'", "'sizeof'", "'then'", "'true'", 
			"'typ'", "'var'", "'void'", "'while'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "INT_CONST", "CHAR_CONST", "STRING_CONST", "DOT", "COMMA", "COLON", 
			"ASSIGN", "MUL", "DIV", "ADD", "SUB", "MOD", "EQU", "NEQ", "LE", "GE", 
			"LT", "GT", "LPAREN", "RPAREN", "LBRACE", "RBRACE", "LBRACK", "RBRACK", 
			"POW", "AND", "AS", "BOOL", "DO", "CHAR", "ELSE", "END", "FALSE", "FUN", 
			"IF", "IN", "INT", "LET", "NIL", "NONE", "NOT", "OR", "SIZEOF", "THEN", 
			"TRUE", "TYP", "VAR", "VOID", "WHILE", "ID", "COMMENT", "WS"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Prev26Parser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }



	    private Location loc(final Token tok) { return new Location((LexAn.LocLogToken)tok); }
	    private Location loc(final Locatable loc) { return new Location(loc); }
	    private Location loc(final Token tok1, final Token tok2) { return new Location((LexAn.LocLogToken)tok1, (LexAn.LocLogToken)tok2); }
	    private Location loc(final Token tok1, final Locatable loc2) { if (loc2 == null) return null; return new Location((LexAn.LocLogToken)tok1, loc2); }
	    private Location loc(final Locatable loc1, final Token tok2) { if (loc1 == null) return null; return new Location(loc1, (LexAn.LocLogToken)tok2); }
	    private Location loc(final Locatable loc1, final Locatable loc2) { if ((loc1 == null) || (loc2 == null)) return null; return new Location(loc1, loc2); }


	public Prev26Parser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class SourceContext extends ParserRuleContext {
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public TerminalNode EOF() { return getToken(Prev26Parser.EOF, 0); }
		public SourceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_source; }
	}

	public final SourceContext source() throws RecognitionException {
		SourceContext _localctx = new SourceContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_source);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			prog();
			setState(45);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgContext extends ParserRuleContext {
		public List<DefnContext> defn() {
			return getRuleContexts(DefnContext.class);
		}
		public DefnContext defn(int i) {
			return getRuleContext(DefnContext.class,i);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(47);
				defn();
				}
				}
				setState(50); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 211123412402176L) != 0) );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class DefnContext extends ParserRuleContext {
		public TypeDeclContext typeDecl() {
			return getRuleContext(TypeDeclContext.class,0);
		}
		public VarDeclContext varDecl() {
			return getRuleContext(VarDeclContext.class,0);
		}
		public FuncDeclContext funcDecl() {
			return getRuleContext(FuncDeclContext.class,0);
		}
		public DefnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defn; }
	}

	public final DefnContext defn() throws RecognitionException {
		DefnContext _localctx = new DefnContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_defn);
		try {
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(52);
				typeDecl();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(53);
				varDecl();
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(54);
				funcDecl();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeDeclContext extends ParserRuleContext {
		public TerminalNode TYP() { return getToken(Prev26Parser.TYP, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public TerminalNode ASSIGN() { return getToken(Prev26Parser.ASSIGN, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TypeDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDecl; }
	}

	public final TypeDeclContext typeDecl() throws RecognitionException {
		TypeDeclContext _localctx = new TypeDeclContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_typeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(TYP);
			setState(58);
			match(ID);
			setState(59);
			match(ASSIGN);
			setState(60);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class VarDeclContext extends ParserRuleContext {
		public TerminalNode VAR() { return getToken(Prev26Parser.VAR, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public TerminalNode COLON() { return getToken(Prev26Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public VarDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varDecl; }
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(62);
			match(VAR);
			setState(63);
			match(ID);
			setState(64);
			match(COLON);
			setState(65);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FuncDeclContext extends ParserRuleContext {
		public TerminalNode FUN() { return getToken(Prev26Parser.FUN, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(Prev26Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TerminalNode ASSIGN() { return getToken(Prev26Parser.ASSIGN, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public FuncDeclContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDecl; }
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(FUN);
			setState(68);
			match(ID);
			setState(69);
			match(LPAREN);
			setState(71);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(70);
				paramList();
				}
			}

			setState(73);
			match(RPAREN);
			setState(74);
			match(COLON);
			setState(75);
			type();
			setState(78);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(76);
				match(ASSIGN);
				setState(77);
				exprList();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamListContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Prev26Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev26Parser.COMMA, i);
		}
		public ParamListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_paramList; }
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			param();
			setState(85);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(81);
				match(COMMA);
				setState(82);
				param();
				}
				}
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ParamContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public TerminalNode COLON() { return getToken(Prev26Parser.COLON, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(ID);
			setState(89);
			match(COLON);
			setState(90);
			type();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeListContext extends ParserRuleContext {
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Prev26Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev26Parser.COMMA, i);
		}
		public TypeListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeList; }
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(100);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1407513709248512L) != 0)) {
				{
				setState(92);
				type();
				setState(97);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(93);
					match(COMMA);
					setState(94);
					type();
					}
					}
					setState(99);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class TypeContext extends ParserRuleContext {
		public TerminalNode INT() { return getToken(Prev26Parser.INT, 0); }
		public TerminalNode CHAR() { return getToken(Prev26Parser.CHAR, 0); }
		public TerminalNode BOOL() { return getToken(Prev26Parser.BOOL, 0); }
		public TerminalNode VOID() { return getToken(Prev26Parser.VOID, 0); }
		public TerminalNode LBRACK() { return getToken(Prev26Parser.LBRACK, 0); }
		public TerminalNode INT_CONST() { return getToken(Prev26Parser.INT_CONST, 0); }
		public TerminalNode RBRACK() { return getToken(Prev26Parser.RBRACK, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode POW() { return getToken(Prev26Parser.POW, 0); }
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public TerminalNode LBRACE() { return getToken(Prev26Parser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(Prev26Parser.RBRACE, 0); }
		public List<TerminalNode> COLON() { return getTokens(Prev26Parser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(Prev26Parser.COLON, i);
		}
		public TypeListContext typeList() {
			return getRuleContext(TypeListContext.class,0);
		}
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_type);
		int _la;
		try {
			setState(133);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(102);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(103);
				match(CHAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(104);
				match(BOOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(105);
				match(VOID);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(106);
				match(LBRACK);
				setState(107);
				match(INT_CONST);
				setState(108);
				match(RBRACK);
				setState(109);
				type();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(110);
				match(POW);
				setState(111);
				type();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(112);
				match(LPAREN);
				setState(113);
				paramList();
				setState(114);
				match(RPAREN);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(116);
				match(LBRACE);
				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(117);
					paramList();
					}
				}

				setState(120);
				match(RBRACE);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(121);
				match(LPAREN);
				setState(122);
				match(COLON);
				setState(123);
				typeList();
				setState(124);
				match(COLON);
				setState(125);
				type();
				setState(126);
				match(RPAREN);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(128);
				match(LPAREN);
				setState(129);
				type();
				setState(130);
				match(RPAREN);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(132);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprListContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Prev26Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev26Parser.COMMA, i);
		}
		public ExprListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprList; }
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			expr();
			setState(140);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(136);
				match(COMMA);
				setState(137);
				expr();
				}
				}
				setState(142);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ExprContext extends ParserRuleContext {
		public AssignExprContext assignExpr() {
			return getRuleContext(AssignExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(143);
			assignExpr();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AssignExprContext extends ParserRuleContext {
		public List<CastExprContext> castExpr() {
			return getRuleContexts(CastExprContext.class);
		}
		public CastExprContext castExpr(int i) {
			return getRuleContext(CastExprContext.class,i);
		}
		public TerminalNode ASSIGN() { return getToken(Prev26Parser.ASSIGN, 0); }
		public AssignExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignExpr; }
	}

	public final AssignExprContext assignExpr() throws RecognitionException {
		AssignExprContext _localctx = new AssignExprContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_assignExpr);
		try {
			setState(150);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(145);
				castExpr(0);
				setState(146);
				match(ASSIGN);
				setState(147);
				castExpr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(149);
				castExpr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CastExprContext extends ParserRuleContext {
		public BoolORExprContext boolORExpr() {
			return getRuleContext(BoolORExprContext.class,0);
		}
		public CastExprContext castExpr() {
			return getRuleContext(CastExprContext.class,0);
		}
		public TerminalNode AS() { return getToken(Prev26Parser.AS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CastExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_castExpr; }
	}

	public final CastExprContext castExpr() throws RecognitionException {
		return castExpr(0);
	}

	private CastExprContext castExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CastExprContext _localctx = new CastExprContext(_ctx, _parentState);
		CastExprContext _prevctx = _localctx;
		int _startState = 26;
		enterRecursionRule(_localctx, 26, RULE_castExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(153);
			boolORExpr(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(160);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CastExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_castExpr);
					setState(155);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(156);
					match(AS);
					setState(157);
					type();
					}
					} 
				}
				setState(162);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolORExprContext extends ParserRuleContext {
		public BoolANDExprContext boolANDExpr() {
			return getRuleContext(BoolANDExprContext.class,0);
		}
		public BoolORExprContext boolORExpr() {
			return getRuleContext(BoolORExprContext.class,0);
		}
		public TerminalNode OR() { return getToken(Prev26Parser.OR, 0); }
		public BoolORExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolORExpr; }
	}

	public final BoolORExprContext boolORExpr() throws RecognitionException {
		return boolORExpr(0);
	}

	private BoolORExprContext boolORExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BoolORExprContext _localctx = new BoolORExprContext(_ctx, _parentState);
		BoolORExprContext _prevctx = _localctx;
		int _startState = 28;
		enterRecursionRule(_localctx, 28, RULE_boolORExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(164);
			boolANDExpr(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(171);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BoolORExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_boolORExpr);
					setState(166);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(167);
					match(OR);
					setState(168);
					boolANDExpr(0);
					}
					} 
				}
				setState(173);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BoolANDExprContext extends ParserRuleContext {
		public CompExprContext compExpr() {
			return getRuleContext(CompExprContext.class,0);
		}
		public BoolANDExprContext boolANDExpr() {
			return getRuleContext(BoolANDExprContext.class,0);
		}
		public TerminalNode AND() { return getToken(Prev26Parser.AND, 0); }
		public BoolANDExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolANDExpr; }
	}

	public final BoolANDExprContext boolANDExpr() throws RecognitionException {
		return boolANDExpr(0);
	}

	private BoolANDExprContext boolANDExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BoolANDExprContext _localctx = new BoolANDExprContext(_ctx, _parentState);
		BoolANDExprContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_boolANDExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(175);
			compExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(182);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BoolANDExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_boolANDExpr);
					setState(177);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(178);
					match(AND);
					setState(179);
					compExpr();
					}
					} 
				}
				setState(184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class CompExprContext extends ParserRuleContext {
		public List<AddExprContext> addExpr() {
			return getRuleContexts(AddExprContext.class);
		}
		public AddExprContext addExpr(int i) {
			return getRuleContext(AddExprContext.class,i);
		}
		public TerminalNode EQU() { return getToken(Prev26Parser.EQU, 0); }
		public TerminalNode NEQ() { return getToken(Prev26Parser.NEQ, 0); }
		public TerminalNode LT() { return getToken(Prev26Parser.LT, 0); }
		public TerminalNode GT() { return getToken(Prev26Parser.GT, 0); }
		public TerminalNode LE() { return getToken(Prev26Parser.LE, 0); }
		public TerminalNode GE() { return getToken(Prev26Parser.GE, 0); }
		public CompExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compExpr; }
	}

	public final CompExprContext compExpr() throws RecognitionException {
		CompExprContext _localctx = new CompExprContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_compExpr);
		int _la;
		try {
			setState(190);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(185);
				addExpr(0);
				setState(186);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 516096L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(187);
				addExpr(0);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(189);
				addExpr(0);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class AddExprContext extends ParserRuleContext {
		public MulExprContext mulExpr() {
			return getRuleContext(MulExprContext.class,0);
		}
		public AddExprContext addExpr() {
			return getRuleContext(AddExprContext.class,0);
		}
		public TerminalNode ADD() { return getToken(Prev26Parser.ADD, 0); }
		public TerminalNode SUB() { return getToken(Prev26Parser.SUB, 0); }
		public AddExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpr; }
	}

	public final AddExprContext addExpr() throws RecognitionException {
		return addExpr(0);
	}

	private AddExprContext addExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		AddExprContext _localctx = new AddExprContext(_ctx, _parentState);
		AddExprContext _prevctx = _localctx;
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_addExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(193);
			mulExpr(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(200);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AddExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_addExpr);
					setState(195);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(196);
					_la = _input.LA(1);
					if ( !(_la==ADD || _la==SUB) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(197);
					mulExpr(0);
					}
					} 
				}
				setState(202);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class MulExprContext extends ParserRuleContext {
		public PrefixExprContext prefixExpr() {
			return getRuleContext(PrefixExprContext.class,0);
		}
		public MulExprContext mulExpr() {
			return getRuleContext(MulExprContext.class,0);
		}
		public TerminalNode MUL() { return getToken(Prev26Parser.MUL, 0); }
		public TerminalNode DIV() { return getToken(Prev26Parser.DIV, 0); }
		public TerminalNode MOD() { return getToken(Prev26Parser.MOD, 0); }
		public MulExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExpr; }
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		return mulExpr(0);
	}

	private MulExprContext mulExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		MulExprContext _localctx = new MulExprContext(_ctx, _parentState);
		MulExprContext _prevctx = _localctx;
		int _startState = 36;
		enterRecursionRule(_localctx, 36, RULE_mulExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(204);
			prefixExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(211);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MulExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_mulExpr);
					setState(206);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(207);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4864L) != 0)) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(208);
					prefixExpr();
					}
					} 
				}
				setState(213);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PrefixExprContext extends ParserRuleContext {
		public PrefixExprContext prefixExpr() {
			return getRuleContext(PrefixExprContext.class,0);
		}
		public TerminalNode NOT() { return getToken(Prev26Parser.NOT, 0); }
		public TerminalNode ADD() { return getToken(Prev26Parser.ADD, 0); }
		public TerminalNode SUB() { return getToken(Prev26Parser.SUB, 0); }
		public TerminalNode POW() { return getToken(Prev26Parser.POW, 0); }
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public PrefixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixExpr; }
	}

	public final PrefixExprContext prefixExpr() throws RecognitionException {
		PrefixExprContext _localctx = new PrefixExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_prefixExpr);
		int _la;
		try {
			setState(217);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case SUB:
			case POW:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(214);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2199056813056L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(215);
				prefixExpr();
				}
				break;
			case INT_CONST:
			case CHAR_CONST:
			case STRING_CONST:
			case LPAREN:
			case FALSE:
			case IF:
			case LET:
			case NIL:
			case NONE:
			case SIZEOF:
			case TRUE:
			case WHILE:
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(216);
				postfixExpr(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class PostfixExprContext extends ParserRuleContext {
		public BasicExprContext basicExpr() {
			return getRuleContext(BasicExprContext.class,0);
		}
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public TerminalNode POW() { return getToken(Prev26Parser.POW, 0); }
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(Prev26Parser.LBRACK, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode RBRACK() { return getToken(Prev26Parser.RBRACK, 0); }
		public TerminalNode DOT() { return getToken(Prev26Parser.DOT, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public PostfixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpr; }
	}

	public final PostfixExprContext postfixExpr() throws RecognitionException {
		return postfixExpr(0);
	}

	private PostfixExprContext postfixExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		PostfixExprContext _localctx = new PostfixExprContext(_ctx, _parentState);
		PostfixExprContext _prevctx = _localctx;
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_postfixExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(220);
			basicExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(240);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(238);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
					case 1:
						{
						_localctx = new PostfixExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpr);
						setState(222);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(223);
						match(POW);
						}
						break;
					case 2:
						{
						_localctx = new PostfixExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpr);
						setState(224);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(225);
						match(LPAREN);
						setState(227);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1736996477733902L) != 0)) {
							{
							setState(226);
							exprList();
							}
						}

						setState(229);
						match(RPAREN);
						}
						break;
					case 3:
						{
						_localctx = new PostfixExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpr);
						setState(230);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(231);
						match(LBRACK);
						setState(232);
						expr();
						setState(233);
						match(RBRACK);
						}
						break;
					case 4:
						{
						_localctx = new PostfixExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpr);
						setState(235);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(236);
						match(DOT);
						setState(237);
						match(ID);
						}
						break;
					}
					} 
				}
				setState(242);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class BasicExprContext extends ParserRuleContext {
		public TerminalNode INT_CONST() { return getToken(Prev26Parser.INT_CONST, 0); }
		public TerminalNode CHAR_CONST() { return getToken(Prev26Parser.CHAR_CONST, 0); }
		public TerminalNode STRING_CONST() { return getToken(Prev26Parser.STRING_CONST, 0); }
		public TerminalNode TRUE() { return getToken(Prev26Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Prev26Parser.FALSE, 0); }
		public TerminalNode NONE() { return getToken(Prev26Parser.NONE, 0); }
		public TerminalNode NIL() { return getToken(Prev26Parser.NIL, 0); }
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public List<ExprListContext> exprList() {
			return getRuleContexts(ExprListContext.class);
		}
		public ExprListContext exprList(int i) {
			return getRuleContext(ExprListContext.class,i);
		}
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public TerminalNode SIZEOF() { return getToken(Prev26Parser.SIZEOF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IF() { return getToken(Prev26Parser.IF, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode THEN() { return getToken(Prev26Parser.THEN, 0); }
		public TerminalNode END() { return getToken(Prev26Parser.END, 0); }
		public TerminalNode ELSE() { return getToken(Prev26Parser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(Prev26Parser.WHILE, 0); }
		public TerminalNode DO() { return getToken(Prev26Parser.DO, 0); }
		public TerminalNode LET() { return getToken(Prev26Parser.LET, 0); }
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public TerminalNode IN() { return getToken(Prev26Parser.IN, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public BasicExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicExpr; }
	}

	public final BasicExprContext basicExpr() throws RecognitionException {
		BasicExprContext _localctx = new BasicExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_basicExpr);
		try {
			setState(283);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(243);
				match(INT_CONST);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(244);
				match(CHAR_CONST);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(245);
				match(STRING_CONST);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(246);
				match(TRUE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(247);
				match(FALSE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(248);
				match(NONE);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(249);
				match(NIL);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(250);
				match(LPAREN);
				setState(251);
				exprList();
				setState(252);
				match(RPAREN);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(254);
				match(SIZEOF);
				setState(255);
				type();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(256);
				match(IF);
				setState(257);
				expr();
				setState(258);
				match(THEN);
				setState(259);
				exprList();
				setState(260);
				match(END);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(262);
				match(IF);
				setState(263);
				expr();
				setState(264);
				match(THEN);
				setState(265);
				exprList();
				setState(266);
				match(ELSE);
				setState(267);
				exprList();
				setState(268);
				match(END);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(270);
				match(WHILE);
				setState(271);
				expr();
				setState(272);
				match(DO);
				setState(273);
				exprList();
				setState(274);
				match(END);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(276);
				match(LET);
				setState(277);
				prog();
				setState(278);
				match(IN);
				setState(279);
				exprList();
				setState(280);
				match(END);
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(282);
				match(ID);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 13:
			return castExpr_sempred((CastExprContext)_localctx, predIndex);
		case 14:
			return boolORExpr_sempred((BoolORExprContext)_localctx, predIndex);
		case 15:
			return boolANDExpr_sempred((BoolANDExprContext)_localctx, predIndex);
		case 17:
			return addExpr_sempred((AddExprContext)_localctx, predIndex);
		case 18:
			return mulExpr_sempred((MulExprContext)_localctx, predIndex);
		case 20:
			return postfixExpr_sempred((PostfixExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean castExpr_sempred(CastExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean boolORExpr_sempred(BoolORExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean boolANDExpr_sempred(BoolANDExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 2:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean addExpr_sempred(AddExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 3:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean mulExpr_sempred(MulExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 4:
			return precpred(_ctx, 2);
		}
		return true;
	}
	private boolean postfixExpr_sempred(PostfixExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 5:
			return precpred(_ctx, 5);
		case 6:
			return precpred(_ctx, 4);
		case 7:
			return precpred(_ctx, 3);
		case 8:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u011e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u00011\b\u0001"+
		"\u000b\u0001\f\u00012\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002"+
		"8\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005H\b\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0003\u0005O\b\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0005\u0006T\b\u0006\n\u0006\f\u0006"+
		"W\t\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\b\u0001"+
		"\b\u0001\b\u0005\b`\b\b\n\b\f\bc\t\b\u0003\be\b\b\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0003\tw\b\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001"+
		"\t\u0001\t\u0003\t\u0086\b\t\u0001\n\u0001\n\u0001\n\u0005\n\u008b\b\n"+
		"\n\n\f\n\u008e\t\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u0097\b\f\u0001\r\u0001\r\u0001\r\u0001\r\u0001\r\u0001"+
		"\r\u0005\r\u009f\b\r\n\r\f\r\u00a2\t\r\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0005\u000e\u00aa\b\u000e\n\u000e"+
		"\f\u000e\u00ad\t\u000e\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0005\u000f\u00b5\b\u000f\n\u000f\f\u000f\u00b8"+
		"\t\u000f\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0003"+
		"\u0010\u00bf\b\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001"+
		"\u0011\u0001\u0011\u0005\u0011\u00c7\b\u0011\n\u0011\f\u0011\u00ca\t\u0011"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012"+
		"\u0005\u0012\u00d2\b\u0012\n\u0012\f\u0012\u00d5\t\u0012\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0003\u0013\u00da\b\u0013\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0003"+
		"\u0014\u00e4\b\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005\u0014\u00ef"+
		"\b\u0014\n\u0014\f\u0014\u00f2\t\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0003\u0015\u011c\b\u0015\u0001\u0015\u0000\u0006\u001a\u001c"+
		"\u001e\"$(\u0016\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014"+
		"\u0016\u0018\u001a\u001c\u001e \"$&(*\u0000\u0004\u0001\u0000\r\u0012"+
		"\u0001\u0000\n\u000b\u0002\u0000\b\t\f\f\u0003\u0000\n\u000b\u0019\u0019"+
		"))\u0135\u0000,\u0001\u0000\u0000\u0000\u00020\u0001\u0000\u0000\u0000"+
		"\u00047\u0001\u0000\u0000\u0000\u00069\u0001\u0000\u0000\u0000\b>\u0001"+
		"\u0000\u0000\u0000\nC\u0001\u0000\u0000\u0000\fP\u0001\u0000\u0000\u0000"+
		"\u000eX\u0001\u0000\u0000\u0000\u0010d\u0001\u0000\u0000\u0000\u0012\u0085"+
		"\u0001\u0000\u0000\u0000\u0014\u0087\u0001\u0000\u0000\u0000\u0016\u008f"+
		"\u0001\u0000\u0000\u0000\u0018\u0096\u0001\u0000\u0000\u0000\u001a\u0098"+
		"\u0001\u0000\u0000\u0000\u001c\u00a3\u0001\u0000\u0000\u0000\u001e\u00ae"+
		"\u0001\u0000\u0000\u0000 \u00be\u0001\u0000\u0000\u0000\"\u00c0\u0001"+
		"\u0000\u0000\u0000$\u00cb\u0001\u0000\u0000\u0000&\u00d9\u0001\u0000\u0000"+
		"\u0000(\u00db\u0001\u0000\u0000\u0000*\u011b\u0001\u0000\u0000\u0000,"+
		"-\u0003\u0002\u0001\u0000-.\u0005\u0000\u0000\u0001.\u0001\u0001\u0000"+
		"\u0000\u0000/1\u0003\u0004\u0002\u00000/\u0001\u0000\u0000\u000012\u0001"+
		"\u0000\u0000\u000020\u0001\u0000\u0000\u000023\u0001\u0000\u0000\u0000"+
		"3\u0003\u0001\u0000\u0000\u000048\u0003\u0006\u0003\u000058\u0003\b\u0004"+
		"\u000068\u0003\n\u0005\u000074\u0001\u0000\u0000\u000075\u0001\u0000\u0000"+
		"\u000076\u0001\u0000\u0000\u00008\u0005\u0001\u0000\u0000\u00009:\u0005"+
		".\u0000\u0000:;\u00052\u0000\u0000;<\u0005\u0007\u0000\u0000<=\u0003\u0012"+
		"\t\u0000=\u0007\u0001\u0000\u0000\u0000>?\u0005/\u0000\u0000?@\u00052"+
		"\u0000\u0000@A\u0005\u0006\u0000\u0000AB\u0003\u0012\t\u0000B\t\u0001"+
		"\u0000\u0000\u0000CD\u0005\"\u0000\u0000DE\u00052\u0000\u0000EG\u0005"+
		"\u0013\u0000\u0000FH\u0003\f\u0006\u0000GF\u0001\u0000\u0000\u0000GH\u0001"+
		"\u0000\u0000\u0000HI\u0001\u0000\u0000\u0000IJ\u0005\u0014\u0000\u0000"+
		"JK\u0005\u0006\u0000\u0000KN\u0003\u0012\t\u0000LM\u0005\u0007\u0000\u0000"+
		"MO\u0003\u0014\n\u0000NL\u0001\u0000\u0000\u0000NO\u0001\u0000\u0000\u0000"+
		"O\u000b\u0001\u0000\u0000\u0000PU\u0003\u000e\u0007\u0000QR\u0005\u0005"+
		"\u0000\u0000RT\u0003\u000e\u0007\u0000SQ\u0001\u0000\u0000\u0000TW\u0001"+
		"\u0000\u0000\u0000US\u0001\u0000\u0000\u0000UV\u0001\u0000\u0000\u0000"+
		"V\r\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000XY\u00052\u0000\u0000"+
		"YZ\u0005\u0006\u0000\u0000Z[\u0003\u0012\t\u0000[\u000f\u0001\u0000\u0000"+
		"\u0000\\a\u0003\u0012\t\u0000]^\u0005\u0005\u0000\u0000^`\u0003\u0012"+
		"\t\u0000_]\u0001\u0000\u0000\u0000`c\u0001\u0000\u0000\u0000a_\u0001\u0000"+
		"\u0000\u0000ab\u0001\u0000\u0000\u0000be\u0001\u0000\u0000\u0000ca\u0001"+
		"\u0000\u0000\u0000d\\\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000"+
		"e\u0011\u0001\u0000\u0000\u0000f\u0086\u0005%\u0000\u0000g\u0086\u0005"+
		"\u001e\u0000\u0000h\u0086\u0005\u001c\u0000\u0000i\u0086\u00050\u0000"+
		"\u0000jk\u0005\u0017\u0000\u0000kl\u0005\u0001\u0000\u0000lm\u0005\u0018"+
		"\u0000\u0000m\u0086\u0003\u0012\t\u0000no\u0005\u0019\u0000\u0000o\u0086"+
		"\u0003\u0012\t\u0000pq\u0005\u0013\u0000\u0000qr\u0003\f\u0006\u0000r"+
		"s\u0005\u0014\u0000\u0000s\u0086\u0001\u0000\u0000\u0000tv\u0005\u0015"+
		"\u0000\u0000uw\u0003\f\u0006\u0000vu\u0001\u0000\u0000\u0000vw\u0001\u0000"+
		"\u0000\u0000wx\u0001\u0000\u0000\u0000x\u0086\u0005\u0016\u0000\u0000"+
		"yz\u0005\u0013\u0000\u0000z{\u0005\u0006\u0000\u0000{|\u0003\u0010\b\u0000"+
		"|}\u0005\u0006\u0000\u0000}~\u0003\u0012\t\u0000~\u007f\u0005\u0014\u0000"+
		"\u0000\u007f\u0086\u0001\u0000\u0000\u0000\u0080\u0081\u0005\u0013\u0000"+
		"\u0000\u0081\u0082\u0003\u0012\t\u0000\u0082\u0083\u0005\u0014\u0000\u0000"+
		"\u0083\u0086\u0001\u0000\u0000\u0000\u0084\u0086\u00052\u0000\u0000\u0085"+
		"f\u0001\u0000\u0000\u0000\u0085g\u0001\u0000\u0000\u0000\u0085h\u0001"+
		"\u0000\u0000\u0000\u0085i\u0001\u0000\u0000\u0000\u0085j\u0001\u0000\u0000"+
		"\u0000\u0085n\u0001\u0000\u0000\u0000\u0085p\u0001\u0000\u0000\u0000\u0085"+
		"t\u0001\u0000\u0000\u0000\u0085y\u0001\u0000\u0000\u0000\u0085\u0080\u0001"+
		"\u0000\u0000\u0000\u0085\u0084\u0001\u0000\u0000\u0000\u0086\u0013\u0001"+
		"\u0000\u0000\u0000\u0087\u008c\u0003\u0016\u000b\u0000\u0088\u0089\u0005"+
		"\u0005\u0000\u0000\u0089\u008b\u0003\u0016\u000b\u0000\u008a\u0088\u0001"+
		"\u0000\u0000\u0000\u008b\u008e\u0001\u0000\u0000\u0000\u008c\u008a\u0001"+
		"\u0000\u0000\u0000\u008c\u008d\u0001\u0000\u0000\u0000\u008d\u0015\u0001"+
		"\u0000\u0000\u0000\u008e\u008c\u0001\u0000\u0000\u0000\u008f\u0090\u0003"+
		"\u0018\f\u0000\u0090\u0017\u0001\u0000\u0000\u0000\u0091\u0092\u0003\u001a"+
		"\r\u0000\u0092\u0093\u0005\u0007\u0000\u0000\u0093\u0094\u0003\u001a\r"+
		"\u0000\u0094\u0097\u0001\u0000\u0000\u0000\u0095\u0097\u0003\u001a\r\u0000"+
		"\u0096\u0091\u0001\u0000\u0000\u0000\u0096\u0095\u0001\u0000\u0000\u0000"+
		"\u0097\u0019\u0001\u0000\u0000\u0000\u0098\u0099\u0006\r\uffff\uffff\u0000"+
		"\u0099\u009a\u0003\u001c\u000e\u0000\u009a\u00a0\u0001\u0000\u0000\u0000"+
		"\u009b\u009c\n\u0002\u0000\u0000\u009c\u009d\u0005\u001b\u0000\u0000\u009d"+
		"\u009f\u0003\u0012\t\u0000\u009e\u009b\u0001\u0000\u0000\u0000\u009f\u00a2"+
		"\u0001\u0000\u0000\u0000\u00a0\u009e\u0001\u0000\u0000\u0000\u00a0\u00a1"+
		"\u0001\u0000\u0000\u0000\u00a1\u001b\u0001\u0000\u0000\u0000\u00a2\u00a0"+
		"\u0001\u0000\u0000\u0000\u00a3\u00a4\u0006\u000e\uffff\uffff\u0000\u00a4"+
		"\u00a5\u0003\u001e\u000f\u0000\u00a5\u00ab\u0001\u0000\u0000\u0000\u00a6"+
		"\u00a7\n\u0002\u0000\u0000\u00a7\u00a8\u0005*\u0000\u0000\u00a8\u00aa"+
		"\u0003\u001e\u000f\u0000\u00a9\u00a6\u0001\u0000\u0000\u0000\u00aa\u00ad"+
		"\u0001\u0000\u0000\u0000\u00ab\u00a9\u0001\u0000\u0000\u0000\u00ab\u00ac"+
		"\u0001\u0000\u0000\u0000\u00ac\u001d\u0001\u0000\u0000\u0000\u00ad\u00ab"+
		"\u0001\u0000\u0000\u0000\u00ae\u00af\u0006\u000f\uffff\uffff\u0000\u00af"+
		"\u00b0\u0003 \u0010\u0000\u00b0\u00b6\u0001\u0000\u0000\u0000\u00b1\u00b2"+
		"\n\u0002\u0000\u0000\u00b2\u00b3\u0005\u001a\u0000\u0000\u00b3\u00b5\u0003"+
		" \u0010\u0000\u00b4\u00b1\u0001\u0000\u0000\u0000\u00b5\u00b8\u0001\u0000"+
		"\u0000\u0000\u00b6\u00b4\u0001\u0000\u0000\u0000\u00b6\u00b7\u0001\u0000"+
		"\u0000\u0000\u00b7\u001f\u0001\u0000\u0000\u0000\u00b8\u00b6\u0001\u0000"+
		"\u0000\u0000\u00b9\u00ba\u0003\"\u0011\u0000\u00ba\u00bb\u0007\u0000\u0000"+
		"\u0000\u00bb\u00bc\u0003\"\u0011\u0000\u00bc\u00bf\u0001\u0000\u0000\u0000"+
		"\u00bd\u00bf\u0003\"\u0011\u0000\u00be\u00b9\u0001\u0000\u0000\u0000\u00be"+
		"\u00bd\u0001\u0000\u0000\u0000\u00bf!\u0001\u0000\u0000\u0000\u00c0\u00c1"+
		"\u0006\u0011\uffff\uffff\u0000\u00c1\u00c2\u0003$\u0012\u0000\u00c2\u00c8"+
		"\u0001\u0000\u0000\u0000\u00c3\u00c4\n\u0002\u0000\u0000\u00c4\u00c5\u0007"+
		"\u0001\u0000\u0000\u00c5\u00c7\u0003$\u0012\u0000\u00c6\u00c3\u0001\u0000"+
		"\u0000\u0000\u00c7\u00ca\u0001\u0000\u0000\u0000\u00c8\u00c6\u0001\u0000"+
		"\u0000\u0000\u00c8\u00c9\u0001\u0000\u0000\u0000\u00c9#\u0001\u0000\u0000"+
		"\u0000\u00ca\u00c8\u0001\u0000\u0000\u0000\u00cb\u00cc\u0006\u0012\uffff"+
		"\uffff\u0000\u00cc\u00cd\u0003&\u0013\u0000\u00cd\u00d3\u0001\u0000\u0000"+
		"\u0000\u00ce\u00cf\n\u0002\u0000\u0000\u00cf\u00d0\u0007\u0002\u0000\u0000"+
		"\u00d0\u00d2\u0003&\u0013\u0000\u00d1\u00ce\u0001\u0000\u0000\u0000\u00d2"+
		"\u00d5\u0001\u0000\u0000\u0000\u00d3\u00d1\u0001\u0000\u0000\u0000\u00d3"+
		"\u00d4\u0001\u0000\u0000\u0000\u00d4%\u0001\u0000\u0000\u0000\u00d5\u00d3"+
		"\u0001\u0000\u0000\u0000\u00d6\u00d7\u0007\u0003\u0000\u0000\u00d7\u00da"+
		"\u0003&\u0013\u0000\u00d8\u00da\u0003(\u0014\u0000\u00d9\u00d6\u0001\u0000"+
		"\u0000\u0000\u00d9\u00d8\u0001\u0000\u0000\u0000\u00da\'\u0001\u0000\u0000"+
		"\u0000\u00db\u00dc\u0006\u0014\uffff\uffff\u0000\u00dc\u00dd\u0003*\u0015"+
		"\u0000\u00dd\u00f0\u0001\u0000\u0000\u0000\u00de\u00df\n\u0005\u0000\u0000"+
		"\u00df\u00ef\u0005\u0019\u0000\u0000\u00e0\u00e1\n\u0004\u0000\u0000\u00e1"+
		"\u00e3\u0005\u0013\u0000\u0000\u00e2\u00e4\u0003\u0014\n\u0000\u00e3\u00e2"+
		"\u0001\u0000\u0000\u0000\u00e3\u00e4\u0001\u0000\u0000\u0000\u00e4\u00e5"+
		"\u0001\u0000\u0000\u0000\u00e5\u00ef\u0005\u0014\u0000\u0000\u00e6\u00e7"+
		"\n\u0003\u0000\u0000\u00e7\u00e8\u0005\u0017\u0000\u0000\u00e8\u00e9\u0003"+
		"\u0016\u000b\u0000\u00e9\u00ea\u0005\u0018\u0000\u0000\u00ea\u00ef\u0001"+
		"\u0000\u0000\u0000\u00eb\u00ec\n\u0002\u0000\u0000\u00ec\u00ed\u0005\u0004"+
		"\u0000\u0000\u00ed\u00ef\u00052\u0000\u0000\u00ee\u00de\u0001\u0000\u0000"+
		"\u0000\u00ee\u00e0\u0001\u0000\u0000\u0000\u00ee\u00e6\u0001\u0000\u0000"+
		"\u0000\u00ee\u00eb\u0001\u0000\u0000\u0000\u00ef\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f0\u00ee\u0001\u0000\u0000\u0000\u00f0\u00f1\u0001\u0000\u0000"+
		"\u0000\u00f1)\u0001\u0000\u0000\u0000\u00f2\u00f0\u0001\u0000\u0000\u0000"+
		"\u00f3\u011c\u0005\u0001\u0000\u0000\u00f4\u011c\u0005\u0002\u0000\u0000"+
		"\u00f5\u011c\u0005\u0003\u0000\u0000\u00f6\u011c\u0005-\u0000\u0000\u00f7"+
		"\u011c\u0005!\u0000\u0000\u00f8\u011c\u0005(\u0000\u0000\u00f9\u011c\u0005"+
		"\'\u0000\u0000\u00fa\u00fb\u0005\u0013\u0000\u0000\u00fb\u00fc\u0003\u0014"+
		"\n\u0000\u00fc\u00fd\u0005\u0014\u0000\u0000\u00fd\u011c\u0001\u0000\u0000"+
		"\u0000\u00fe\u00ff\u0005+\u0000\u0000\u00ff\u011c\u0003\u0012\t\u0000"+
		"\u0100\u0101\u0005#\u0000\u0000\u0101\u0102\u0003\u0016\u000b\u0000\u0102"+
		"\u0103\u0005,\u0000\u0000\u0103\u0104\u0003\u0014\n\u0000\u0104\u0105"+
		"\u0005 \u0000\u0000\u0105\u011c\u0001\u0000\u0000\u0000\u0106\u0107\u0005"+
		"#\u0000\u0000\u0107\u0108\u0003\u0016\u000b\u0000\u0108\u0109\u0005,\u0000"+
		"\u0000\u0109\u010a\u0003\u0014\n\u0000\u010a\u010b\u0005\u001f\u0000\u0000"+
		"\u010b\u010c\u0003\u0014\n\u0000\u010c\u010d\u0005 \u0000\u0000\u010d"+
		"\u011c\u0001\u0000\u0000\u0000\u010e\u010f\u00051\u0000\u0000\u010f\u0110"+
		"\u0003\u0016\u000b\u0000\u0110\u0111\u0005\u001d\u0000\u0000\u0111\u0112"+
		"\u0003\u0014\n\u0000\u0112\u0113\u0005 \u0000\u0000\u0113\u011c\u0001"+
		"\u0000\u0000\u0000\u0114\u0115\u0005&\u0000\u0000\u0115\u0116\u0003\u0002"+
		"\u0001\u0000\u0116\u0117\u0005$\u0000\u0000\u0117\u0118\u0003\u0014\n"+
		"\u0000\u0118\u0119\u0005 \u0000\u0000\u0119\u011c\u0001\u0000\u0000\u0000"+
		"\u011a\u011c\u00052\u0000\u0000\u011b\u00f3\u0001\u0000\u0000\u0000\u011b"+
		"\u00f4\u0001\u0000\u0000\u0000\u011b\u00f5\u0001\u0000\u0000\u0000\u011b"+
		"\u00f6\u0001\u0000\u0000\u0000\u011b\u00f7\u0001\u0000\u0000\u0000\u011b"+
		"\u00f8\u0001\u0000\u0000\u0000\u011b\u00f9\u0001\u0000\u0000\u0000\u011b"+
		"\u00fa\u0001\u0000\u0000\u0000\u011b\u00fe\u0001\u0000\u0000\u0000\u011b"+
		"\u0100\u0001\u0000\u0000\u0000\u011b\u0106\u0001\u0000\u0000\u0000\u011b"+
		"\u010e\u0001\u0000\u0000\u0000\u011b\u0114\u0001\u0000\u0000\u0000\u011b"+
		"\u011a\u0001\u0000\u0000\u0000\u011c+\u0001\u0000\u0000\u0000\u001627"+
		"GNUadv\u0085\u008c\u0096\u00a0\u00ab\u00b6\u00be\u00c8\u00d3\u00d9\u00e3"+
		"\u00ee\u00f0\u011b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}