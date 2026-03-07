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
		RULE_source = 0, RULE_prog = 1, RULE_defnList = 2, RULE_defn = 3, RULE_typeDecl = 4, 
		RULE_varDecl = 5, RULE_funcDecl = 6, RULE_paramList = 7, RULE_param = 8, 
		RULE_typeList = 9, RULE_type = 10, RULE_exprList = 11, RULE_expr = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "prog", "defnList", "defn", "typeDecl", "varDecl", "funcDecl", 
			"paramList", "param", "typeList", "type", "exprList", "expr"
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
			setState(26);
			prog();
			setState(27);
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
			setState(30); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(29);
				defn();
				}
				}
				setState(32); 
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
	public static class DefnListContext extends ParserRuleContext {
		public List<DefnContext> defn() {
			return getRuleContexts(DefnContext.class);
		}
		public DefnContext defn(int i) {
			return getRuleContext(DefnContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Prev26Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev26Parser.COMMA, i);
		}
		public DefnListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_defnList; }
	}

	public final DefnListContext defnList() throws RecognitionException {
		DefnListContext _localctx = new DefnListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_defnList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
			defn();
			setState(39);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(35);
				match(COMMA);
				setState(36);
				defn();
				}
				}
				setState(41);
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
		enterRule(_localctx, 6, RULE_defn);
		try {
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(42);
				typeDecl();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(43);
				varDecl();
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(44);
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
		enterRule(_localctx, 8, RULE_typeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(TYP);
			setState(48);
			match(ID);
			setState(49);
			match(ASSIGN);
			setState(50);
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
		enterRule(_localctx, 10, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			match(VAR);
			setState(53);
			match(ID);
			setState(54);
			match(COLON);
			setState(55);
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
		enterRule(_localctx, 12, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(FUN);
			setState(58);
			match(ID);
			setState(59);
			match(LPAREN);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(60);
				paramList();
				}
			}

			setState(63);
			match(RPAREN);
			setState(64);
			match(COLON);
			setState(65);
			type();
			setState(68);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(66);
				match(ASSIGN);
				setState(67);
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
		enterRule(_localctx, 14, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			param();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(71);
				match(COMMA);
				setState(72);
				param();
				}
				}
				setState(77);
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
		enterRule(_localctx, 16, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(78);
			match(ID);
			setState(79);
			match(COLON);
			setState(80);
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
		enterRule(_localctx, 18, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(90);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1407513709248512L) != 0)) {
				{
				setState(82);
				type();
				setState(87);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(83);
					match(COMMA);
					setState(84);
					type();
					}
					}
					setState(89);
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
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
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
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type);
		try {
			setState(122);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(92);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(93);
				match(CHAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(94);
				match(BOOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(95);
				match(VOID);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(96);
				match(ID);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(97);
				match(LBRACK);
				setState(98);
				match(INT_CONST);
				setState(99);
				match(RBRACK);
				setState(100);
				type();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(101);
				match(POW);
				setState(102);
				type();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(103);
				match(LPAREN);
				setState(104);
				paramList();
				setState(105);
				match(RPAREN);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(107);
				match(LBRACE);
				setState(108);
				paramList();
				setState(109);
				match(RBRACE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(111);
				match(LPAREN);
				setState(112);
				match(COLON);
				setState(113);
				typeList();
				setState(114);
				match(COLON);
				setState(115);
				type();
				setState(116);
				match(RPAREN);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(118);
				match(LPAREN);
				setState(119);
				type();
				setState(120);
				match(RPAREN);
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
		enterRule(_localctx, 22, RULE_exprList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			expr(0);
			setState(129);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(125);
					match(COMMA);
					setState(126);
					expr(0);
					}
					} 
				}
				setState(131);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
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
		public TerminalNode INT_CONST() { return getToken(Prev26Parser.INT_CONST, 0); }
		public TerminalNode CHAR_CONST() { return getToken(Prev26Parser.CHAR_CONST, 0); }
		public TerminalNode STRING_CONST() { return getToken(Prev26Parser.STRING_CONST, 0); }
		public TerminalNode TRUE() { return getToken(Prev26Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Prev26Parser.FALSE, 0); }
		public TerminalNode NONE() { return getToken(Prev26Parser.NONE, 0); }
		public TerminalNode NIL() { return getToken(Prev26Parser.NIL, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public TerminalNode NOT() { return getToken(Prev26Parser.NOT, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode ADD() { return getToken(Prev26Parser.ADD, 0); }
		public TerminalNode SUB() { return getToken(Prev26Parser.SUB, 0); }
		public TerminalNode POW() { return getToken(Prev26Parser.POW, 0); }
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
		public TerminalNode THEN() { return getToken(Prev26Parser.THEN, 0); }
		public TerminalNode END() { return getToken(Prev26Parser.END, 0); }
		public TerminalNode ELSE() { return getToken(Prev26Parser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(Prev26Parser.WHILE, 0); }
		public TerminalNode DO() { return getToken(Prev26Parser.DO, 0); }
		public TerminalNode LET() { return getToken(Prev26Parser.LET, 0); }
		public DefnListContext defnList() {
			return getRuleContext(DefnListContext.class,0);
		}
		public TerminalNode IN() { return getToken(Prev26Parser.IN, 0); }
		public TerminalNode MUL() { return getToken(Prev26Parser.MUL, 0); }
		public TerminalNode DIV() { return getToken(Prev26Parser.DIV, 0); }
		public TerminalNode MOD() { return getToken(Prev26Parser.MOD, 0); }
		public TerminalNode AND() { return getToken(Prev26Parser.AND, 0); }
		public TerminalNode OR() { return getToken(Prev26Parser.OR, 0); }
		public TerminalNode LBRACK() { return getToken(Prev26Parser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(Prev26Parser.RBRACK, 0); }
		public TerminalNode DOT() { return getToken(Prev26Parser.DOT, 0); }
		public TerminalNode AS() { return getToken(Prev26Parser.AS, 0); }
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 24;
		enterRecursionRule(_localctx, 24, RULE_expr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				{
				setState(133);
				match(INT_CONST);
				}
				break;
			case 2:
				{
				setState(134);
				match(CHAR_CONST);
				}
				break;
			case 3:
				{
				setState(135);
				match(STRING_CONST);
				}
				break;
			case 4:
				{
				setState(136);
				match(TRUE);
				}
				break;
			case 5:
				{
				setState(137);
				match(FALSE);
				}
				break;
			case 6:
				{
				setState(138);
				match(NONE);
				}
				break;
			case 7:
				{
				setState(139);
				match(NIL);
				}
				break;
			case 8:
				{
				setState(140);
				match(ID);
				}
				break;
			case 9:
				{
				setState(141);
				match(NOT);
				setState(142);
				expr(19);
				}
				break;
			case 10:
				{
				setState(143);
				match(ADD);
				setState(144);
				expr(18);
				}
				break;
			case 11:
				{
				setState(145);
				match(SUB);
				setState(146);
				expr(17);
				}
				break;
			case 12:
				{
				setState(147);
				match(POW);
				setState(148);
				expr(16);
				}
				break;
			case 13:
				{
				}
				break;
			case 14:
				{
				setState(150);
				match(LPAREN);
				setState(151);
				exprList();
				setState(152);
				match(RPAREN);
				}
				break;
			case 15:
				{
				setState(154);
				match(SIZEOF);
				setState(155);
				type();
				}
				break;
			case 16:
				{
				setState(156);
				match(IF);
				setState(157);
				expr(0);
				setState(158);
				match(THEN);
				setState(159);
				exprList();
				setState(160);
				match(END);
				}
				break;
			case 17:
				{
				setState(162);
				match(IF);
				setState(163);
				expr(0);
				setState(164);
				match(THEN);
				setState(165);
				exprList();
				setState(166);
				match(ELSE);
				setState(167);
				exprList();
				setState(168);
				match(END);
				}
				break;
			case 18:
				{
				setState(170);
				match(WHILE);
				setState(171);
				expr(0);
				setState(172);
				match(DO);
				setState(173);
				exprList();
				setState(174);
				match(END);
				}
				break;
			case 19:
				{
				setState(176);
				match(LET);
				setState(177);
				defnList();
				setState(178);
				match(IN);
				setState(179);
				exprList();
				setState(180);
				match(END);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(226);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(224);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(184);
						if (!(precpred(_ctx, 15))) throw new FailedPredicateException(this, "precpred(_ctx, 15)");
						setState(185);
						match(MUL);
						setState(186);
						expr(16);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(187);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(188);
						match(DIV);
						setState(189);
						expr(15);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(190);
						if (!(precpred(_ctx, 13))) throw new FailedPredicateException(this, "precpred(_ctx, 13)");
						setState(191);
						match(MOD);
						setState(192);
						expr(14);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(193);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(194);
						match(ADD);
						setState(195);
						expr(13);
						}
						break;
					case 5:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(196);
						if (!(precpred(_ctx, 11))) throw new FailedPredicateException(this, "precpred(_ctx, 11)");
						setState(197);
						match(SUB);
						setState(198);
						expr(12);
						}
						break;
					case 6:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(199);
						if (!(precpred(_ctx, 9))) throw new FailedPredicateException(this, "precpred(_ctx, 9)");
						setState(200);
						match(AND);
						setState(201);
						expr(10);
						}
						break;
					case 7:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(202);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(203);
						match(OR);
						setState(204);
						expr(9);
						}
						break;
					case 8:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(205);
						if (!(precpred(_ctx, 23))) throw new FailedPredicateException(this, "precpred(_ctx, 23)");
						setState(206);
						match(POW);
						}
						break;
					case 9:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(207);
						if (!(precpred(_ctx, 22))) throw new FailedPredicateException(this, "precpred(_ctx, 22)");
						setState(208);
						match(LPAREN);
						setState(210);
						_errHandler.sync(this);
						switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
						case 1:
							{
							setState(209);
							exprList();
							}
							break;
						}
						setState(212);
						match(RPAREN);
						}
						break;
					case 10:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(213);
						if (!(precpred(_ctx, 21))) throw new FailedPredicateException(this, "precpred(_ctx, 21)");
						setState(214);
						match(LBRACK);
						setState(215);
						expr(0);
						setState(216);
						match(RBRACK);
						}
						break;
					case 11:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(218);
						if (!(precpred(_ctx, 20))) throw new FailedPredicateException(this, "precpred(_ctx, 20)");
						setState(219);
						match(DOT);
						setState(220);
						match(ID);
						}
						break;
					case 12:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(221);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(222);
						match(AS);
						setState(223);
						type();
						}
						break;
					}
					} 
				}
				setState(228);
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 12:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 15);
		case 1:
			return precpred(_ctx, 14);
		case 2:
			return precpred(_ctx, 13);
		case 3:
			return precpred(_ctx, 12);
		case 4:
			return precpred(_ctx, 11);
		case 5:
			return precpred(_ctx, 9);
		case 6:
			return precpred(_ctx, 8);
		case 7:
			return precpred(_ctx, 23);
		case 8:
			return precpred(_ctx, 22);
		case 9:
			return precpred(_ctx, 21);
		case 10:
			return precpred(_ctx, 20);
		case 11:
			return precpred(_ctx, 7);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u00e6\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u0001"+
		"\u001f\b\u0001\u000b\u0001\f\u0001 \u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0005\u0002&\b\u0002\n\u0002\f\u0002)\t\u0002\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0003\u0003.\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		">\b\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006"+
		"\u0003\u0006E\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007"+
		"J\b\u0007\n\u0007\f\u0007M\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001"+
		"\t\u0001\t\u0001\t\u0005\tV\b\t\n\t\f\tY\t\t\u0003\t[\b\t\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0003\n{\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b"+
		"\u0080\b\u000b\n\u000b\f\u000b\u0083\t\u000b\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00b7\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0003\f\u00d3\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0005\f\u00e1"+
		"\b\f\n\f\f\f\u00e4\t\f\u0001\f\u0000\u0001\u0018\r\u0000\u0002\u0004\u0006"+
		"\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u0000\u0000\u010b\u0000\u001a"+
		"\u0001\u0000\u0000\u0000\u0002\u001e\u0001\u0000\u0000\u0000\u0004\"\u0001"+
		"\u0000\u0000\u0000\u0006-\u0001\u0000\u0000\u0000\b/\u0001\u0000\u0000"+
		"\u0000\n4\u0001\u0000\u0000\u0000\f9\u0001\u0000\u0000\u0000\u000eF\u0001"+
		"\u0000\u0000\u0000\u0010N\u0001\u0000\u0000\u0000\u0012Z\u0001\u0000\u0000"+
		"\u0000\u0014z\u0001\u0000\u0000\u0000\u0016|\u0001\u0000\u0000\u0000\u0018"+
		"\u00b6\u0001\u0000\u0000\u0000\u001a\u001b\u0003\u0002\u0001\u0000\u001b"+
		"\u001c\u0005\u0000\u0000\u0001\u001c\u0001\u0001\u0000\u0000\u0000\u001d"+
		"\u001f\u0003\u0006\u0003\u0000\u001e\u001d\u0001\u0000\u0000\u0000\u001f"+
		" \u0001\u0000\u0000\u0000 \u001e\u0001\u0000\u0000\u0000 !\u0001\u0000"+
		"\u0000\u0000!\u0003\u0001\u0000\u0000\u0000\"\'\u0003\u0006\u0003\u0000"+
		"#$\u0005\u0005\u0000\u0000$&\u0003\u0006\u0003\u0000%#\u0001\u0000\u0000"+
		"\u0000&)\u0001\u0000\u0000\u0000\'%\u0001\u0000\u0000\u0000\'(\u0001\u0000"+
		"\u0000\u0000(\u0005\u0001\u0000\u0000\u0000)\'\u0001\u0000\u0000\u0000"+
		"*.\u0003\b\u0004\u0000+.\u0003\n\u0005\u0000,.\u0003\f\u0006\u0000-*\u0001"+
		"\u0000\u0000\u0000-+\u0001\u0000\u0000\u0000-,\u0001\u0000\u0000\u0000"+
		".\u0007\u0001\u0000\u0000\u0000/0\u0005.\u0000\u000001\u00052\u0000\u0000"+
		"12\u0005\u0007\u0000\u000023\u0003\u0014\n\u00003\t\u0001\u0000\u0000"+
		"\u000045\u0005/\u0000\u000056\u00052\u0000\u000067\u0005\u0006\u0000\u0000"+
		"78\u0003\u0014\n\u00008\u000b\u0001\u0000\u0000\u00009:\u0005\"\u0000"+
		"\u0000:;\u00052\u0000\u0000;=\u0005\u0013\u0000\u0000<>\u0003\u000e\u0007"+
		"\u0000=<\u0001\u0000\u0000\u0000=>\u0001\u0000\u0000\u0000>?\u0001\u0000"+
		"\u0000\u0000?@\u0005\u0014\u0000\u0000@A\u0005\u0006\u0000\u0000AD\u0003"+
		"\u0014\n\u0000BC\u0005\u0007\u0000\u0000CE\u0003\u0016\u000b\u0000DB\u0001"+
		"\u0000\u0000\u0000DE\u0001\u0000\u0000\u0000E\r\u0001\u0000\u0000\u0000"+
		"FK\u0003\u0010\b\u0000GH\u0005\u0005\u0000\u0000HJ\u0003\u0010\b\u0000"+
		"IG\u0001\u0000\u0000\u0000JM\u0001\u0000\u0000\u0000KI\u0001\u0000\u0000"+
		"\u0000KL\u0001\u0000\u0000\u0000L\u000f\u0001\u0000\u0000\u0000MK\u0001"+
		"\u0000\u0000\u0000NO\u00052\u0000\u0000OP\u0005\u0006\u0000\u0000PQ\u0003"+
		"\u0014\n\u0000Q\u0011\u0001\u0000\u0000\u0000RW\u0003\u0014\n\u0000ST"+
		"\u0005\u0005\u0000\u0000TV\u0003\u0014\n\u0000US\u0001\u0000\u0000\u0000"+
		"VY\u0001\u0000\u0000\u0000WU\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000"+
		"\u0000X[\u0001\u0000\u0000\u0000YW\u0001\u0000\u0000\u0000ZR\u0001\u0000"+
		"\u0000\u0000Z[\u0001\u0000\u0000\u0000[\u0013\u0001\u0000\u0000\u0000"+
		"\\{\u0005%\u0000\u0000]{\u0005\u001e\u0000\u0000^{\u0005\u001c\u0000\u0000"+
		"_{\u00050\u0000\u0000`{\u00052\u0000\u0000ab\u0005\u0017\u0000\u0000b"+
		"c\u0005\u0001\u0000\u0000cd\u0005\u0018\u0000\u0000d{\u0003\u0014\n\u0000"+
		"ef\u0005\u0019\u0000\u0000f{\u0003\u0014\n\u0000gh\u0005\u0013\u0000\u0000"+
		"hi\u0003\u000e\u0007\u0000ij\u0005\u0014\u0000\u0000j{\u0001\u0000\u0000"+
		"\u0000kl\u0005\u0015\u0000\u0000lm\u0003\u000e\u0007\u0000mn\u0005\u0016"+
		"\u0000\u0000n{\u0001\u0000\u0000\u0000op\u0005\u0013\u0000\u0000pq\u0005"+
		"\u0006\u0000\u0000qr\u0003\u0012\t\u0000rs\u0005\u0006\u0000\u0000st\u0003"+
		"\u0014\n\u0000tu\u0005\u0014\u0000\u0000u{\u0001\u0000\u0000\u0000vw\u0005"+
		"\u0013\u0000\u0000wx\u0003\u0014\n\u0000xy\u0005\u0014\u0000\u0000y{\u0001"+
		"\u0000\u0000\u0000z\\\u0001\u0000\u0000\u0000z]\u0001\u0000\u0000\u0000"+
		"z^\u0001\u0000\u0000\u0000z_\u0001\u0000\u0000\u0000z`\u0001\u0000\u0000"+
		"\u0000za\u0001\u0000\u0000\u0000ze\u0001\u0000\u0000\u0000zg\u0001\u0000"+
		"\u0000\u0000zk\u0001\u0000\u0000\u0000zo\u0001\u0000\u0000\u0000zv\u0001"+
		"\u0000\u0000\u0000{\u0015\u0001\u0000\u0000\u0000|\u0081\u0003\u0018\f"+
		"\u0000}~\u0005\u0005\u0000\u0000~\u0080\u0003\u0018\f\u0000\u007f}\u0001"+
		"\u0000\u0000\u0000\u0080\u0083\u0001\u0000\u0000\u0000\u0081\u007f\u0001"+
		"\u0000\u0000\u0000\u0081\u0082\u0001\u0000\u0000\u0000\u0082\u0017\u0001"+
		"\u0000\u0000\u0000\u0083\u0081\u0001\u0000\u0000\u0000\u0084\u0085\u0006"+
		"\f\uffff\uffff\u0000\u0085\u00b7\u0005\u0001\u0000\u0000\u0086\u00b7\u0005"+
		"\u0002\u0000\u0000\u0087\u00b7\u0005\u0003\u0000\u0000\u0088\u00b7\u0005"+
		"-\u0000\u0000\u0089\u00b7\u0005!\u0000\u0000\u008a\u00b7\u0005(\u0000"+
		"\u0000\u008b\u00b7\u0005\'\u0000\u0000\u008c\u00b7\u00052\u0000\u0000"+
		"\u008d\u008e\u0005)\u0000\u0000\u008e\u00b7\u0003\u0018\f\u0013\u008f"+
		"\u0090\u0005\n\u0000\u0000\u0090\u00b7\u0003\u0018\f\u0012\u0091\u0092"+
		"\u0005\u000b\u0000\u0000\u0092\u00b7\u0003\u0018\f\u0011\u0093\u0094\u0005"+
		"\u0019\u0000\u0000\u0094\u00b7\u0003\u0018\f\u0010\u0095\u00b7\u0001\u0000"+
		"\u0000\u0000\u0096\u0097\u0005\u0013\u0000\u0000\u0097\u0098\u0003\u0016"+
		"\u000b\u0000\u0098\u0099\u0005\u0014\u0000\u0000\u0099\u00b7\u0001\u0000"+
		"\u0000\u0000\u009a\u009b\u0005+\u0000\u0000\u009b\u00b7\u0003\u0014\n"+
		"\u0000\u009c\u009d\u0005#\u0000\u0000\u009d\u009e\u0003\u0018\f\u0000"+
		"\u009e\u009f\u0005,\u0000\u0000\u009f\u00a0\u0003\u0016\u000b\u0000\u00a0"+
		"\u00a1\u0005 \u0000\u0000\u00a1\u00b7\u0001\u0000\u0000\u0000\u00a2\u00a3"+
		"\u0005#\u0000\u0000\u00a3\u00a4\u0003\u0018\f\u0000\u00a4\u00a5\u0005"+
		",\u0000\u0000\u00a5\u00a6\u0003\u0016\u000b\u0000\u00a6\u00a7\u0005\u001f"+
		"\u0000\u0000\u00a7\u00a8\u0003\u0016\u000b\u0000\u00a8\u00a9\u0005 \u0000"+
		"\u0000\u00a9\u00b7\u0001\u0000\u0000\u0000\u00aa\u00ab\u00051\u0000\u0000"+
		"\u00ab\u00ac\u0003\u0018\f\u0000\u00ac\u00ad\u0005\u001d\u0000\u0000\u00ad"+
		"\u00ae\u0003\u0016\u000b\u0000\u00ae\u00af\u0005 \u0000\u0000\u00af\u00b7"+
		"\u0001\u0000\u0000\u0000\u00b0\u00b1\u0005&\u0000\u0000\u00b1\u00b2\u0003"+
		"\u0004\u0002\u0000\u00b2\u00b3\u0005$\u0000\u0000\u00b3\u00b4\u0003\u0016"+
		"\u000b\u0000\u00b4\u00b5\u0005 \u0000\u0000\u00b5\u00b7\u0001\u0000\u0000"+
		"\u0000\u00b6\u0084\u0001\u0000\u0000\u0000\u00b6\u0086\u0001\u0000\u0000"+
		"\u0000\u00b6\u0087\u0001\u0000\u0000\u0000\u00b6\u0088\u0001\u0000\u0000"+
		"\u0000\u00b6\u0089\u0001\u0000\u0000\u0000\u00b6\u008a\u0001\u0000\u0000"+
		"\u0000\u00b6\u008b\u0001\u0000\u0000\u0000\u00b6\u008c\u0001\u0000\u0000"+
		"\u0000\u00b6\u008d\u0001\u0000\u0000\u0000\u00b6\u008f\u0001\u0000\u0000"+
		"\u0000\u00b6\u0091\u0001\u0000\u0000\u0000\u00b6\u0093\u0001\u0000\u0000"+
		"\u0000\u00b6\u0095\u0001\u0000\u0000\u0000\u00b6\u0096\u0001\u0000\u0000"+
		"\u0000\u00b6\u009a\u0001\u0000\u0000\u0000\u00b6\u009c\u0001\u0000\u0000"+
		"\u0000\u00b6\u00a2\u0001\u0000\u0000\u0000\u00b6\u00aa\u0001\u0000\u0000"+
		"\u0000\u00b6\u00b0\u0001\u0000\u0000\u0000\u00b7\u00e2\u0001\u0000\u0000"+
		"\u0000\u00b8\u00b9\n\u000f\u0000\u0000\u00b9\u00ba\u0005\b\u0000\u0000"+
		"\u00ba\u00e1\u0003\u0018\f\u0010\u00bb\u00bc\n\u000e\u0000\u0000\u00bc"+
		"\u00bd\u0005\t\u0000\u0000\u00bd\u00e1\u0003\u0018\f\u000f\u00be\u00bf"+
		"\n\r\u0000\u0000\u00bf\u00c0\u0005\f\u0000\u0000\u00c0\u00e1\u0003\u0018"+
		"\f\u000e\u00c1\u00c2\n\f\u0000\u0000\u00c2\u00c3\u0005\n\u0000\u0000\u00c3"+
		"\u00e1\u0003\u0018\f\r\u00c4\u00c5\n\u000b\u0000\u0000\u00c5\u00c6\u0005"+
		"\u000b\u0000\u0000\u00c6\u00e1\u0003\u0018\f\f\u00c7\u00c8\n\t\u0000\u0000"+
		"\u00c8\u00c9\u0005\u001a\u0000\u0000\u00c9\u00e1\u0003\u0018\f\n\u00ca"+
		"\u00cb\n\b\u0000\u0000\u00cb\u00cc\u0005*\u0000\u0000\u00cc\u00e1\u0003"+
		"\u0018\f\t\u00cd\u00ce\n\u0017\u0000\u0000\u00ce\u00e1\u0005\u0019\u0000"+
		"\u0000\u00cf\u00d0\n\u0016\u0000\u0000\u00d0\u00d2\u0005\u0013\u0000\u0000"+
		"\u00d1\u00d3\u0003\u0016\u000b\u0000\u00d2\u00d1\u0001\u0000\u0000\u0000"+
		"\u00d2\u00d3\u0001\u0000\u0000\u0000\u00d3\u00d4\u0001\u0000\u0000\u0000"+
		"\u00d4\u00e1\u0005\u0014\u0000\u0000\u00d5\u00d6\n\u0015\u0000\u0000\u00d6"+
		"\u00d7\u0005\u0017\u0000\u0000\u00d7\u00d8\u0003\u0018\f\u0000\u00d8\u00d9"+
		"\u0005\u0018\u0000\u0000\u00d9\u00e1\u0001\u0000\u0000\u0000\u00da\u00db"+
		"\n\u0014\u0000\u0000\u00db\u00dc\u0005\u0004\u0000\u0000\u00dc\u00e1\u0005"+
		"2\u0000\u0000\u00dd\u00de\n\u0007\u0000\u0000\u00de\u00df\u0005\u001b"+
		"\u0000\u0000\u00df\u00e1\u0003\u0014\n\u0000\u00e0\u00b8\u0001\u0000\u0000"+
		"\u0000\u00e0\u00bb\u0001\u0000\u0000\u0000\u00e0\u00be\u0001\u0000\u0000"+
		"\u0000\u00e0\u00c1\u0001\u0000\u0000\u0000\u00e0\u00c4\u0001\u0000\u0000"+
		"\u0000\u00e0\u00c7\u0001\u0000\u0000\u0000\u00e0\u00ca\u0001\u0000\u0000"+
		"\u0000\u00e0\u00cd\u0001\u0000\u0000\u0000\u00e0\u00cf\u0001\u0000\u0000"+
		"\u0000\u00e0\u00d5\u0001\u0000\u0000\u0000\u00e0\u00da\u0001\u0000\u0000"+
		"\u0000\u00e0\u00dd\u0001\u0000\u0000\u0000\u00e1\u00e4\u0001\u0000\u0000"+
		"\u0000\u00e2\u00e0\u0001\u0000\u0000\u0000\u00e2\u00e3\u0001\u0000\u0000"+
		"\u0000\u00e3\u0019\u0001\u0000\u0000\u0000\u00e4\u00e2\u0001\u0000\u0000"+
		"\u0000\u000e \'-=DKWZz\u0081\u00b6\u00d2\u00e0\u00e2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}