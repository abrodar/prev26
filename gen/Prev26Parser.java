// Generated from /home/aljaz/Desktop/FRI_PORTAL/SEM2/P/prev26/src/prev26lang/phase/synan/Prev26Parser.g4 by ANTLR 4.13.2


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

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
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
		RULE_typeList = 9, RULE_type = 10, RULE_exprList = 11, RULE_exprDEL = 12, 
		RULE_expr = 13, RULE_assignExpr = 14, RULE_castExpr = 15, RULE_boolExpr = 16, 
		RULE_compExpr = 17, RULE_addExpr = 18, RULE_mulExpr = 19, RULE_prefixExpr = 20, 
		RULE_postfixExpr = 21;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "prog", "defnList", "defn", "typeDecl", "varDecl", "funcDecl", 
			"paramList", "param", "typeList", "type", "exprList", "exprDEL", "expr", 
			"assignExpr", "castExpr", "boolExpr", "compExpr", "addExpr", "mulExpr", 
			"prefixExpr", "postfixExpr"
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterSource(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitSource(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitSource(this);
			else return visitor.visitChildren(this);
		}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterProg(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitProg(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitProg(this);
			else return visitor.visitChildren(this);
		}
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterDefnList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitDefnList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitDefnList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefnListContext defnList() throws RecognitionException {
		DefnListContext _localctx = new DefnListContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_defnList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(52);
			defn();
			setState(57);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(53);
				match(COMMA);
				setState(54);
				defn();
				}
				}
				setState(59);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterDefn(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitDefn(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitDefn(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DefnContext defn() throws RecognitionException {
		DefnContext _localctx = new DefnContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_defn);
		try {
			setState(63);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(60);
				typeDecl();
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(61);
				varDecl();
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(62);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterTypeDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitTypeDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitTypeDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDeclContext typeDecl() throws RecognitionException {
		TypeDeclContext _localctx = new TypeDeclContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_typeDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			match(TYP);
			setState(66);
			match(ID);
			setState(67);
			match(ASSIGN);
			setState(68);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterVarDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitVarDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitVarDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarDeclContext varDecl() throws RecognitionException {
		VarDeclContext _localctx = new VarDeclContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_varDecl);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(70);
			match(VAR);
			setState(71);
			match(ID);
			setState(72);
			match(COLON);
			setState(73);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterFuncDecl(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitFuncDecl(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitFuncDecl(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDeclContext funcDecl() throws RecognitionException {
		FuncDeclContext _localctx = new FuncDeclContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_funcDecl);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(FUN);
			setState(76);
			match(ID);
			setState(77);
			match(LPAREN);
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ID) {
				{
				setState(78);
				paramList();
				}
			}

			setState(81);
			match(RPAREN);
			setState(82);
			match(COLON);
			setState(83);
			type();
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(84);
				match(ASSIGN);
				setState(85);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterParamList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitParamList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitParamList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamListContext paramList() throws RecognitionException {
		ParamListContext _localctx = new ParamListContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_paramList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			param();
			setState(93);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(89);
				match(COMMA);
				setState(90);
				param();
				}
				}
				setState(95);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(ID);
			setState(97);
			match(COLON);
			setState(98);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterTypeList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitTypeList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitTypeList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeListContext typeList() throws RecognitionException {
		TypeListContext _localctx = new TypeListContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1407513709248512L) != 0)) {
				{
				setState(100);
				type();
				setState(105);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(101);
					match(COMMA);
					setState(102);
					type();
					}
					}
					setState(107);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_type);
		try {
			setState(140);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,8,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(110);
				match(INT);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(111);
				match(CHAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(112);
				match(BOOL);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(113);
				match(VOID);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(114);
				match(ID);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(115);
				match(LBRACK);
				setState(116);
				match(INT_CONST);
				setState(117);
				match(RBRACK);
				setState(118);
				type();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(119);
				match(POW);
				setState(120);
				type();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(121);
				match(LPAREN);
				setState(122);
				paramList();
				setState(123);
				match(RPAREN);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(125);
				match(LBRACE);
				setState(126);
				paramList();
				setState(127);
				match(RBRACE);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(129);
				match(LPAREN);
				setState(130);
				match(COLON);
				setState(131);
				typeList();
				setState(132);
				match(COLON);
				setState(133);
				type();
				setState(134);
				match(RPAREN);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(136);
				match(LPAREN);
				setState(137);
				type();
				setState(138);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterExprList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitExprList(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitExprList(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprListContext exprList() throws RecognitionException {
		ExprListContext _localctx = new ExprListContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_exprList);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			expr();
			setState(147);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(143);
					match(COMMA);
					setState(144);
					expr();
					}
					} 
				}
				setState(149);
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
	public static class ExprDELContext extends ParserRuleContext {
		public TerminalNode INT_CONST() { return getToken(Prev26Parser.INT_CONST, 0); }
		public TerminalNode CHAR_CONST() { return getToken(Prev26Parser.CHAR_CONST, 0); }
		public TerminalNode STRING_CONST() { return getToken(Prev26Parser.STRING_CONST, 0); }
		public TerminalNode TRUE() { return getToken(Prev26Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Prev26Parser.FALSE, 0); }
		public TerminalNode NONE() { return getToken(Prev26Parser.NONE, 0); }
		public TerminalNode NIL() { return getToken(Prev26Parser.NIL, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public TerminalNode POW() { return getToken(Prev26Parser.POW, 0); }
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public List<ExprListContext> exprList() {
			return getRuleContexts(ExprListContext.class);
		}
		public ExprListContext exprList(int i) {
			return getRuleContext(ExprListContext.class,i);
		}
		public TerminalNode LBRACK() { return getToken(Prev26Parser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(Prev26Parser.RBRACK, 0); }
		public TerminalNode DOT() { return getToken(Prev26Parser.DOT, 0); }
		public TerminalNode NOT() { return getToken(Prev26Parser.NOT, 0); }
		public TerminalNode ADD() { return getToken(Prev26Parser.ADD, 0); }
		public TerminalNode SUB() { return getToken(Prev26Parser.SUB, 0); }
		public TerminalNode MUL() { return getToken(Prev26Parser.MUL, 0); }
		public TerminalNode DIV() { return getToken(Prev26Parser.DIV, 0); }
		public TerminalNode MOD() { return getToken(Prev26Parser.MOD, 0); }
		public TerminalNode AND() { return getToken(Prev26Parser.AND, 0); }
		public TerminalNode OR() { return getToken(Prev26Parser.OR, 0); }
		public TerminalNode AS() { return getToken(Prev26Parser.AS, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode SIZEOF() { return getToken(Prev26Parser.SIZEOF, 0); }
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
		public ExprDELContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exprDEL; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterExprDEL(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitExprDEL(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitExprDEL(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprDELContext exprDEL() throws RecognitionException {
		ExprDELContext _localctx = new ExprDELContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_exprDEL);
		try {
			setState(250);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,11,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				match(INT_CONST);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(151);
				match(CHAR_CONST);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(152);
				match(STRING_CONST);
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(153);
				match(TRUE);
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(154);
				match(FALSE);
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(155);
				match(NONE);
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(156);
				match(NIL);
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(157);
				match(ID);
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(158);
				expr();
				setState(159);
				match(POW);
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(161);
				expr();
				setState(162);
				match(LPAREN);
				setState(164);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
				case 1:
					{
					setState(163);
					exprList();
					}
					break;
				}
				setState(166);
				match(RPAREN);
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(168);
				expr();
				setState(169);
				match(LBRACK);
				setState(170);
				expr();
				setState(171);
				match(RBRACK);
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(173);
				expr();
				setState(174);
				match(DOT);
				setState(175);
				match(ID);
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(177);
				match(NOT);
				setState(178);
				expr();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(179);
				match(ADD);
				setState(180);
				expr();
				}
				break;
			case 15:
				enterOuterAlt(_localctx, 15);
				{
				setState(181);
				match(SUB);
				setState(182);
				expr();
				}
				break;
			case 16:
				enterOuterAlt(_localctx, 16);
				{
				setState(183);
				match(POW);
				setState(184);
				expr();
				}
				break;
			case 17:
				enterOuterAlt(_localctx, 17);
				{
				setState(185);
				expr();
				setState(186);
				match(MUL);
				setState(187);
				expr();
				}
				break;
			case 18:
				enterOuterAlt(_localctx, 18);
				{
				setState(189);
				expr();
				setState(190);
				match(DIV);
				setState(191);
				expr();
				}
				break;
			case 19:
				enterOuterAlt(_localctx, 19);
				{
				setState(193);
				expr();
				setState(194);
				match(MOD);
				setState(195);
				expr();
				}
				break;
			case 20:
				enterOuterAlt(_localctx, 20);
				{
				setState(197);
				expr();
				setState(198);
				match(ADD);
				setState(199);
				expr();
				}
				break;
			case 21:
				enterOuterAlt(_localctx, 21);
				{
				setState(201);
				expr();
				setState(202);
				match(SUB);
				setState(203);
				expr();
				}
				break;
			case 22:
				enterOuterAlt(_localctx, 22);
				{
				}
				break;
			case 23:
				enterOuterAlt(_localctx, 23);
				{
				setState(206);
				expr();
				setState(207);
				match(AND);
				setState(208);
				expr();
				}
				break;
			case 24:
				enterOuterAlt(_localctx, 24);
				{
				setState(210);
				expr();
				setState(211);
				match(OR);
				setState(212);
				expr();
				}
				break;
			case 25:
				enterOuterAlt(_localctx, 25);
				{
				setState(214);
				expr();
				setState(215);
				match(AS);
				setState(216);
				type();
				}
				break;
			case 26:
				enterOuterAlt(_localctx, 26);
				{
				setState(218);
				match(LPAREN);
				setState(219);
				exprList();
				setState(220);
				match(RPAREN);
				}
				break;
			case 27:
				enterOuterAlt(_localctx, 27);
				{
				setState(222);
				match(SIZEOF);
				setState(223);
				type();
				}
				break;
			case 28:
				enterOuterAlt(_localctx, 28);
				{
				setState(224);
				match(IF);
				setState(225);
				expr();
				setState(226);
				match(THEN);
				setState(227);
				exprList();
				setState(228);
				match(END);
				}
				break;
			case 29:
				enterOuterAlt(_localctx, 29);
				{
				setState(230);
				match(IF);
				setState(231);
				expr();
				setState(232);
				match(THEN);
				setState(233);
				exprList();
				setState(234);
				match(ELSE);
				setState(235);
				exprList();
				setState(236);
				match(END);
				}
				break;
			case 30:
				enterOuterAlt(_localctx, 30);
				{
				setState(238);
				match(WHILE);
				setState(239);
				expr();
				setState(240);
				match(DO);
				setState(241);
				exprList();
				setState(242);
				match(END);
				}
				break;
			case 31:
				enterOuterAlt(_localctx, 31);
				{
				setState(244);
				match(LET);
				setState(245);
				defnList();
				setState(246);
				match(IN);
				setState(247);
				exprList();
				setState(248);
				match(END);
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
	public static class ExprContext extends ParserRuleContext {
		public AssignExprContext assignExpr() {
			return getRuleContext(AssignExprContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		ExprContext _localctx = new ExprContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(252);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterAssignExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitAssignExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitAssignExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AssignExprContext assignExpr() throws RecognitionException {
		AssignExprContext _localctx = new AssignExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_assignExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(254);
			castExpr(0);
			setState(257);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==ASSIGN) {
				{
				setState(255);
				match(ASSIGN);
				setState(256);
				castExpr(0);
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
	public static class CastExprContext extends ParserRuleContext {
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterCastExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitCastExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitCastExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CastExprContext castExpr() throws RecognitionException {
		return castExpr(0);
	}

	private CastExprContext castExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		CastExprContext _localctx = new CastExprContext(_ctx, _parentState);
		CastExprContext _prevctx = _localctx;
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_castExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(260);
			boolExpr(0);
			}
			_ctx.stop = _input.LT(-1);
			setState(267);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CastExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_castExpr);
					setState(262);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(263);
					match(AS);
					setState(264);
					type();
					}
					} 
				}
				setState(269);
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
	public static class BoolExprContext extends ParserRuleContext {
		public CompExprContext compExpr() {
			return getRuleContext(CompExprContext.class,0);
		}
		public BoolExprContext boolExpr() {
			return getRuleContext(BoolExprContext.class,0);
		}
		public TerminalNode AND() { return getToken(Prev26Parser.AND, 0); }
		public TerminalNode OR() { return getToken(Prev26Parser.OR, 0); }
		public BoolExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_boolExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterBoolExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitBoolExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitBoolExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BoolExprContext boolExpr() throws RecognitionException {
		return boolExpr(0);
	}

	private BoolExprContext boolExpr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		BoolExprContext _localctx = new BoolExprContext(_ctx, _parentState);
		BoolExprContext _prevctx = _localctx;
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_boolExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(271);
			compExpr();
			}
			_ctx.stop = _input.LT(-1);
			setState(278);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BoolExprContext(_parentctx, _parentState);
					pushNewRecursionContext(_localctx, _startState, RULE_boolExpr);
					setState(273);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(274);
					_la = _input.LA(1);
					if ( !(_la==AND || _la==OR) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(275);
					compExpr();
					}
					} 
				}
				setState(280);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterCompExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitCompExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitCompExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CompExprContext compExpr() throws RecognitionException {
		CompExprContext _localctx = new CompExprContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_compExpr);
		int _la;
		try {
			setState(286);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(281);
				addExpr();
				setState(282);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 516096L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(283);
				addExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(285);
				addExpr();
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
		public List<MulExprContext> mulExpr() {
			return getRuleContexts(MulExprContext.class);
		}
		public MulExprContext mulExpr(int i) {
			return getRuleContext(MulExprContext.class,i);
		}
		public TerminalNode MUL() { return getToken(Prev26Parser.MUL, 0); }
		public TerminalNode DIV() { return getToken(Prev26Parser.DIV, 0); }
		public TerminalNode MOD() { return getToken(Prev26Parser.MOD, 0); }
		public AddExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_addExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterAddExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitAddExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitAddExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AddExprContext addExpr() throws RecognitionException {
		AddExprContext _localctx = new AddExprContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_addExpr);
		int _la;
		try {
			setState(293);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(288);
				mulExpr();
				setState(289);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4864L) != 0)) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(290);
				mulExpr();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(292);
				mulExpr();
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
	public static class MulExprContext extends ParserRuleContext {
		public MulExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_mulExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterMulExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitMulExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitMulExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MulExprContext mulExpr() throws RecognitionException {
		MulExprContext _localctx = new MulExprContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_mulExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
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
	public static class PrefixExprContext extends ParserRuleContext {
		public PrefixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prefixExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterPrefixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitPrefixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitPrefixExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PrefixExprContext prefixExpr() throws RecognitionException {
		PrefixExprContext _localctx = new PrefixExprContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_prefixExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
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
		public PostfixExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_postfixExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).enterPostfixExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof Prev26ParserListener ) ((Prev26ParserListener)listener).exitPostfixExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof Prev26ParserVisitor ) return ((Prev26ParserVisitor<? extends T>)visitor).visitPostfixExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PostfixExprContext postfixExpr() throws RecognitionException {
		PostfixExprContext _localctx = new PostfixExprContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_postfixExpr);
		try {
			enterOuterAlt(_localctx, 1);
			{
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
		case 15:
			return castExpr_sempred((CastExprContext)_localctx, predIndex);
		case 16:
			return boolExpr_sempred((BoolExprContext)_localctx, predIndex);
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
	private boolean boolExpr_sempred(BoolExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 1:
			return precpred(_ctx, 2);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u00014\u012e\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0001\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u00011\b\u0001"+
		"\u000b\u0001\f\u00012\u0001\u0002\u0001\u0002\u0001\u0002\u0005\u0002"+
		"8\b\u0002\n\u0002\f\u0002;\t\u0002\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0003\u0003@\b\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006P\b\u0006"+
		"\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0001\u0006\u0003\u0006"+
		"W\b\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0005\u0007\\\b\u0007\n"+
		"\u0007\f\u0007_\t\u0007\u0001\b\u0001\b\u0001\b\u0001\b\u0001\t\u0001"+
		"\t\u0001\t\u0005\th\b\t\n\t\f\tk\t\t\u0003\tm\b\t\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0003\n\u008d\b\n\u0001\u000b\u0001\u000b\u0001\u000b\u0005\u000b\u0092"+
		"\b\u000b\n\u000b\f\u000b\u0095\t\u000b\u0001\f\u0001\f\u0001\f\u0001\f"+
		"\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0003\f\u00a5\b\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001"+
		"\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0001\f\u0003\f\u00fb\b\f\u0001"+
		"\r\u0001\r\u0001\u000e\u0001\u000e\u0001\u000e\u0003\u000e\u0102\b\u000e"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0005\u000f\u010a\b\u000f\n\u000f\f\u000f\u010d\t\u000f\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u0115"+
		"\b\u0010\n\u0010\f\u0010\u0118\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0003\u0011\u011f\b\u0011\u0001\u0012\u0001\u0012"+
		"\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u0126\b\u0012\u0001\u0013"+
		"\u0001\u0013\u0001\u0014\u0001\u0014\u0001\u0015\u0001\u0015\u0001\u0015"+
		"\u0000\u0002\u001e \u0016\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012"+
		"\u0014\u0016\u0018\u001a\u001c\u001e \"$&(*\u0000\u0003\u0002\u0000\u001a"+
		"\u001a**\u0001\u0000\r\u0012\u0002\u0000\b\t\f\f\u014f\u0000,\u0001\u0000"+
		"\u0000\u0000\u00020\u0001\u0000\u0000\u0000\u00044\u0001\u0000\u0000\u0000"+
		"\u0006?\u0001\u0000\u0000\u0000\bA\u0001\u0000\u0000\u0000\nF\u0001\u0000"+
		"\u0000\u0000\fK\u0001\u0000\u0000\u0000\u000eX\u0001\u0000\u0000\u0000"+
		"\u0010`\u0001\u0000\u0000\u0000\u0012l\u0001\u0000\u0000\u0000\u0014\u008c"+
		"\u0001\u0000\u0000\u0000\u0016\u008e\u0001\u0000\u0000\u0000\u0018\u00fa"+
		"\u0001\u0000\u0000\u0000\u001a\u00fc\u0001\u0000\u0000\u0000\u001c\u00fe"+
		"\u0001\u0000\u0000\u0000\u001e\u0103\u0001\u0000\u0000\u0000 \u010e\u0001"+
		"\u0000\u0000\u0000\"\u011e\u0001\u0000\u0000\u0000$\u0125\u0001\u0000"+
		"\u0000\u0000&\u0127\u0001\u0000\u0000\u0000(\u0129\u0001\u0000\u0000\u0000"+
		"*\u012b\u0001\u0000\u0000\u0000,-\u0003\u0002\u0001\u0000-.\u0005\u0000"+
		"\u0000\u0001.\u0001\u0001\u0000\u0000\u0000/1\u0003\u0006\u0003\u0000"+
		"0/\u0001\u0000\u0000\u000012\u0001\u0000\u0000\u000020\u0001\u0000\u0000"+
		"\u000023\u0001\u0000\u0000\u00003\u0003\u0001\u0000\u0000\u000049\u0003"+
		"\u0006\u0003\u000056\u0005\u0005\u0000\u000068\u0003\u0006\u0003\u0000"+
		"75\u0001\u0000\u0000\u00008;\u0001\u0000\u0000\u000097\u0001\u0000\u0000"+
		"\u00009:\u0001\u0000\u0000\u0000:\u0005\u0001\u0000\u0000\u0000;9\u0001"+
		"\u0000\u0000\u0000<@\u0003\b\u0004\u0000=@\u0003\n\u0005\u0000>@\u0003"+
		"\f\u0006\u0000?<\u0001\u0000\u0000\u0000?=\u0001\u0000\u0000\u0000?>\u0001"+
		"\u0000\u0000\u0000@\u0007\u0001\u0000\u0000\u0000AB\u0005.\u0000\u0000"+
		"BC\u00052\u0000\u0000CD\u0005\u0007\u0000\u0000DE\u0003\u0014\n\u0000"+
		"E\t\u0001\u0000\u0000\u0000FG\u0005/\u0000\u0000GH\u00052\u0000\u0000"+
		"HI\u0005\u0006\u0000\u0000IJ\u0003\u0014\n\u0000J\u000b\u0001\u0000\u0000"+
		"\u0000KL\u0005\"\u0000\u0000LM\u00052\u0000\u0000MO\u0005\u0013\u0000"+
		"\u0000NP\u0003\u000e\u0007\u0000ON\u0001\u0000\u0000\u0000OP\u0001\u0000"+
		"\u0000\u0000PQ\u0001\u0000\u0000\u0000QR\u0005\u0014\u0000\u0000RS\u0005"+
		"\u0006\u0000\u0000SV\u0003\u0014\n\u0000TU\u0005\u0007\u0000\u0000UW\u0003"+
		"\u0016\u000b\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000"+
		"W\r\u0001\u0000\u0000\u0000X]\u0003\u0010\b\u0000YZ\u0005\u0005\u0000"+
		"\u0000Z\\\u0003\u0010\b\u0000[Y\u0001\u0000\u0000\u0000\\_\u0001\u0000"+
		"\u0000\u0000][\u0001\u0000\u0000\u0000]^\u0001\u0000\u0000\u0000^\u000f"+
		"\u0001\u0000\u0000\u0000_]\u0001\u0000\u0000\u0000`a\u00052\u0000\u0000"+
		"ab\u0005\u0006\u0000\u0000bc\u0003\u0014\n\u0000c\u0011\u0001\u0000\u0000"+
		"\u0000di\u0003\u0014\n\u0000ef\u0005\u0005\u0000\u0000fh\u0003\u0014\n"+
		"\u0000ge\u0001\u0000\u0000\u0000hk\u0001\u0000\u0000\u0000ig\u0001\u0000"+
		"\u0000\u0000ij\u0001\u0000\u0000\u0000jm\u0001\u0000\u0000\u0000ki\u0001"+
		"\u0000\u0000\u0000ld\u0001\u0000\u0000\u0000lm\u0001\u0000\u0000\u0000"+
		"m\u0013\u0001\u0000\u0000\u0000n\u008d\u0005%\u0000\u0000o\u008d\u0005"+
		"\u001e\u0000\u0000p\u008d\u0005\u001c\u0000\u0000q\u008d\u00050\u0000"+
		"\u0000r\u008d\u00052\u0000\u0000st\u0005\u0017\u0000\u0000tu\u0005\u0001"+
		"\u0000\u0000uv\u0005\u0018\u0000\u0000v\u008d\u0003\u0014\n\u0000wx\u0005"+
		"\u0019\u0000\u0000x\u008d\u0003\u0014\n\u0000yz\u0005\u0013\u0000\u0000"+
		"z{\u0003\u000e\u0007\u0000{|\u0005\u0014\u0000\u0000|\u008d\u0001\u0000"+
		"\u0000\u0000}~\u0005\u0015\u0000\u0000~\u007f\u0003\u000e\u0007\u0000"+
		"\u007f\u0080\u0005\u0016\u0000\u0000\u0080\u008d\u0001\u0000\u0000\u0000"+
		"\u0081\u0082\u0005\u0013\u0000\u0000\u0082\u0083\u0005\u0006\u0000\u0000"+
		"\u0083\u0084\u0003\u0012\t\u0000\u0084\u0085\u0005\u0006\u0000\u0000\u0085"+
		"\u0086\u0003\u0014\n\u0000\u0086\u0087\u0005\u0014\u0000\u0000\u0087\u008d"+
		"\u0001\u0000\u0000\u0000\u0088\u0089\u0005\u0013\u0000\u0000\u0089\u008a"+
		"\u0003\u0014\n\u0000\u008a\u008b\u0005\u0014\u0000\u0000\u008b\u008d\u0001"+
		"\u0000\u0000\u0000\u008cn\u0001\u0000\u0000\u0000\u008co\u0001\u0000\u0000"+
		"\u0000\u008cp\u0001\u0000\u0000\u0000\u008cq\u0001\u0000\u0000\u0000\u008c"+
		"r\u0001\u0000\u0000\u0000\u008cs\u0001\u0000\u0000\u0000\u008cw\u0001"+
		"\u0000\u0000\u0000\u008cy\u0001\u0000\u0000\u0000\u008c}\u0001\u0000\u0000"+
		"\u0000\u008c\u0081\u0001\u0000\u0000\u0000\u008c\u0088\u0001\u0000\u0000"+
		"\u0000\u008d\u0015\u0001\u0000\u0000\u0000\u008e\u0093\u0003\u001a\r\u0000"+
		"\u008f\u0090\u0005\u0005\u0000\u0000\u0090\u0092\u0003\u001a\r\u0000\u0091"+
		"\u008f\u0001\u0000\u0000\u0000\u0092\u0095\u0001\u0000\u0000\u0000\u0093"+
		"\u0091\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000\u0000\u0094"+
		"\u0017\u0001\u0000\u0000\u0000\u0095\u0093\u0001\u0000\u0000\u0000\u0096"+
		"\u00fb\u0005\u0001\u0000\u0000\u0097\u00fb\u0005\u0002\u0000\u0000\u0098"+
		"\u00fb\u0005\u0003\u0000\u0000\u0099\u00fb\u0005-\u0000\u0000\u009a\u00fb"+
		"\u0005!\u0000\u0000\u009b\u00fb\u0005(\u0000\u0000\u009c\u00fb\u0005\'"+
		"\u0000\u0000\u009d\u00fb\u00052\u0000\u0000\u009e\u009f\u0003\u001a\r"+
		"\u0000\u009f\u00a0\u0005\u0019\u0000\u0000\u00a0\u00fb\u0001\u0000\u0000"+
		"\u0000\u00a1\u00a2\u0003\u001a\r\u0000\u00a2\u00a4\u0005\u0013\u0000\u0000"+
		"\u00a3\u00a5\u0003\u0016\u000b\u0000\u00a4\u00a3\u0001\u0000\u0000\u0000"+
		"\u00a4\u00a5\u0001\u0000\u0000\u0000\u00a5\u00a6\u0001\u0000\u0000\u0000"+
		"\u00a6\u00a7\u0005\u0014\u0000\u0000\u00a7\u00fb\u0001\u0000\u0000\u0000"+
		"\u00a8\u00a9\u0003\u001a\r\u0000\u00a9\u00aa\u0005\u0017\u0000\u0000\u00aa"+
		"\u00ab\u0003\u001a\r\u0000\u00ab\u00ac\u0005\u0018\u0000\u0000\u00ac\u00fb"+
		"\u0001\u0000\u0000\u0000\u00ad\u00ae\u0003\u001a\r\u0000\u00ae\u00af\u0005"+
		"\u0004\u0000\u0000\u00af\u00b0\u00052\u0000\u0000\u00b0\u00fb\u0001\u0000"+
		"\u0000\u0000\u00b1\u00b2\u0005)\u0000\u0000\u00b2\u00fb\u0003\u001a\r"+
		"\u0000\u00b3\u00b4\u0005\n\u0000\u0000\u00b4\u00fb\u0003\u001a\r\u0000"+
		"\u00b5\u00b6\u0005\u000b\u0000\u0000\u00b6\u00fb\u0003\u001a\r\u0000\u00b7"+
		"\u00b8\u0005\u0019\u0000\u0000\u00b8\u00fb\u0003\u001a\r\u0000\u00b9\u00ba"+
		"\u0003\u001a\r\u0000\u00ba\u00bb\u0005\b\u0000\u0000\u00bb\u00bc\u0003"+
		"\u001a\r\u0000\u00bc\u00fb\u0001\u0000\u0000\u0000\u00bd\u00be\u0003\u001a"+
		"\r\u0000\u00be\u00bf\u0005\t\u0000\u0000\u00bf\u00c0\u0003\u001a\r\u0000"+
		"\u00c0\u00fb\u0001\u0000\u0000\u0000\u00c1\u00c2\u0003\u001a\r\u0000\u00c2"+
		"\u00c3\u0005\f\u0000\u0000\u00c3\u00c4\u0003\u001a\r\u0000\u00c4\u00fb"+
		"\u0001\u0000\u0000\u0000\u00c5\u00c6\u0003\u001a\r\u0000\u00c6\u00c7\u0005"+
		"\n\u0000\u0000\u00c7\u00c8\u0003\u001a\r\u0000\u00c8\u00fb\u0001\u0000"+
		"\u0000\u0000\u00c9\u00ca\u0003\u001a\r\u0000\u00ca\u00cb\u0005\u000b\u0000"+
		"\u0000\u00cb\u00cc\u0003\u001a\r\u0000\u00cc\u00fb\u0001\u0000\u0000\u0000"+
		"\u00cd\u00fb\u0001\u0000\u0000\u0000\u00ce\u00cf\u0003\u001a\r\u0000\u00cf"+
		"\u00d0\u0005\u001a\u0000\u0000\u00d0\u00d1\u0003\u001a\r\u0000\u00d1\u00fb"+
		"\u0001\u0000\u0000\u0000\u00d2\u00d3\u0003\u001a\r\u0000\u00d3\u00d4\u0005"+
		"*\u0000\u0000\u00d4\u00d5\u0003\u001a\r\u0000\u00d5\u00fb\u0001\u0000"+
		"\u0000\u0000\u00d6\u00d7\u0003\u001a\r\u0000\u00d7\u00d8\u0005\u001b\u0000"+
		"\u0000\u00d8\u00d9\u0003\u0014\n\u0000\u00d9\u00fb\u0001\u0000\u0000\u0000"+
		"\u00da\u00db\u0005\u0013\u0000\u0000\u00db\u00dc\u0003\u0016\u000b\u0000"+
		"\u00dc\u00dd\u0005\u0014\u0000\u0000\u00dd\u00fb\u0001\u0000\u0000\u0000"+
		"\u00de\u00df\u0005+\u0000\u0000\u00df\u00fb\u0003\u0014\n\u0000\u00e0"+
		"\u00e1\u0005#\u0000\u0000\u00e1\u00e2\u0003\u001a\r\u0000\u00e2\u00e3"+
		"\u0005,\u0000\u0000\u00e3\u00e4\u0003\u0016\u000b\u0000\u00e4\u00e5\u0005"+
		" \u0000\u0000\u00e5\u00fb\u0001\u0000\u0000\u0000\u00e6\u00e7\u0005#\u0000"+
		"\u0000\u00e7\u00e8\u0003\u001a\r\u0000\u00e8\u00e9\u0005,\u0000\u0000"+
		"\u00e9\u00ea\u0003\u0016\u000b\u0000\u00ea\u00eb\u0005\u001f\u0000\u0000"+
		"\u00eb\u00ec\u0003\u0016\u000b\u0000\u00ec\u00ed\u0005 \u0000\u0000\u00ed"+
		"\u00fb\u0001\u0000\u0000\u0000\u00ee\u00ef\u00051\u0000\u0000\u00ef\u00f0"+
		"\u0003\u001a\r\u0000\u00f0\u00f1\u0005\u001d\u0000\u0000\u00f1\u00f2\u0003"+
		"\u0016\u000b\u0000\u00f2\u00f3\u0005 \u0000\u0000\u00f3\u00fb\u0001\u0000"+
		"\u0000\u0000\u00f4\u00f5\u0005&\u0000\u0000\u00f5\u00f6\u0003\u0004\u0002"+
		"\u0000\u00f6\u00f7\u0005$\u0000\u0000\u00f7\u00f8\u0003\u0016\u000b\u0000"+
		"\u00f8\u00f9\u0005 \u0000\u0000\u00f9\u00fb\u0001\u0000\u0000\u0000\u00fa"+
		"\u0096\u0001\u0000\u0000\u0000\u00fa\u0097\u0001\u0000\u0000\u0000\u00fa"+
		"\u0098\u0001\u0000\u0000\u0000\u00fa\u0099\u0001\u0000\u0000\u0000\u00fa"+
		"\u009a\u0001\u0000\u0000\u0000\u00fa\u009b\u0001\u0000\u0000\u0000\u00fa"+
		"\u009c\u0001\u0000\u0000\u0000\u00fa\u009d\u0001\u0000\u0000\u0000\u00fa"+
		"\u009e\u0001\u0000\u0000\u0000\u00fa\u00a1\u0001\u0000\u0000\u0000\u00fa"+
		"\u00a8\u0001\u0000\u0000\u0000\u00fa\u00ad\u0001\u0000\u0000\u0000\u00fa"+
		"\u00b1\u0001\u0000\u0000\u0000\u00fa\u00b3\u0001\u0000\u0000\u0000\u00fa"+
		"\u00b5\u0001\u0000\u0000\u0000\u00fa\u00b7\u0001\u0000\u0000\u0000\u00fa"+
		"\u00b9\u0001\u0000\u0000\u0000\u00fa\u00bd\u0001\u0000\u0000\u0000\u00fa"+
		"\u00c1\u0001\u0000\u0000\u0000\u00fa\u00c5\u0001\u0000\u0000\u0000\u00fa"+
		"\u00c9\u0001\u0000\u0000\u0000\u00fa\u00cd\u0001\u0000\u0000\u0000\u00fa"+
		"\u00ce\u0001\u0000\u0000\u0000\u00fa\u00d2\u0001\u0000\u0000\u0000\u00fa"+
		"\u00d6\u0001\u0000\u0000\u0000\u00fa\u00da\u0001\u0000\u0000\u0000\u00fa"+
		"\u00de\u0001\u0000\u0000\u0000\u00fa\u00e0\u0001\u0000\u0000\u0000\u00fa"+
		"\u00e6\u0001\u0000\u0000\u0000\u00fa\u00ee\u0001\u0000\u0000\u0000\u00fa"+
		"\u00f4\u0001\u0000\u0000\u0000\u00fb\u0019\u0001\u0000\u0000\u0000\u00fc"+
		"\u00fd\u0003\u001c\u000e\u0000\u00fd\u001b\u0001\u0000\u0000\u0000\u00fe"+
		"\u0101\u0003\u001e\u000f\u0000\u00ff\u0100\u0005\u0007\u0000\u0000\u0100"+
		"\u0102\u0003\u001e\u000f\u0000\u0101\u00ff\u0001\u0000\u0000\u0000\u0101"+
		"\u0102\u0001\u0000\u0000\u0000\u0102\u001d\u0001\u0000\u0000\u0000\u0103"+
		"\u0104\u0006\u000f\uffff\uffff\u0000\u0104\u0105\u0003 \u0010\u0000\u0105"+
		"\u010b\u0001\u0000\u0000\u0000\u0106\u0107\n\u0002\u0000\u0000\u0107\u0108"+
		"\u0005\u001b\u0000\u0000\u0108\u010a\u0003\u0014\n\u0000\u0109\u0106\u0001"+
		"\u0000\u0000\u0000\u010a\u010d\u0001\u0000\u0000\u0000\u010b\u0109\u0001"+
		"\u0000\u0000\u0000\u010b\u010c\u0001\u0000\u0000\u0000\u010c\u001f\u0001"+
		"\u0000\u0000\u0000\u010d\u010b\u0001\u0000\u0000\u0000\u010e\u010f\u0006"+
		"\u0010\uffff\uffff\u0000\u010f\u0110\u0003\"\u0011\u0000\u0110\u0116\u0001"+
		"\u0000\u0000\u0000\u0111\u0112\n\u0002\u0000\u0000\u0112\u0113\u0007\u0000"+
		"\u0000\u0000\u0113\u0115\u0003\"\u0011\u0000\u0114\u0111\u0001\u0000\u0000"+
		"\u0000\u0115\u0118\u0001\u0000\u0000\u0000\u0116\u0114\u0001\u0000\u0000"+
		"\u0000\u0116\u0117\u0001\u0000\u0000\u0000\u0117!\u0001\u0000\u0000\u0000"+
		"\u0118\u0116\u0001\u0000\u0000\u0000\u0119\u011a\u0003$\u0012\u0000\u011a"+
		"\u011b\u0007\u0001\u0000\u0000\u011b\u011c\u0003$\u0012\u0000\u011c\u011f"+
		"\u0001\u0000\u0000\u0000\u011d\u011f\u0003$\u0012\u0000\u011e\u0119\u0001"+
		"\u0000\u0000\u0000\u011e\u011d\u0001\u0000\u0000\u0000\u011f#\u0001\u0000"+
		"\u0000\u0000\u0120\u0121\u0003&\u0013\u0000\u0121\u0122\u0007\u0002\u0000"+
		"\u0000\u0122\u0123\u0003&\u0013\u0000\u0123\u0126\u0001\u0000\u0000\u0000"+
		"\u0124\u0126\u0003&\u0013\u0000\u0125\u0120\u0001\u0000\u0000\u0000\u0125"+
		"\u0124\u0001\u0000\u0000\u0000\u0126%\u0001\u0000\u0000\u0000\u0127\u0128"+
		"\u0001\u0000\u0000\u0000\u0128\'\u0001\u0000\u0000\u0000\u0129\u012a\u0001"+
		"\u0000\u0000\u0000\u012a)\u0001\u0000\u0000\u0000\u012b\u012c\u0001\u0000"+
		"\u0000\u0000\u012c+\u0001\u0000\u0000\u0000\u001129?OV]il\u008c\u0093"+
		"\u00a4\u00fa\u0101\u010b\u0116\u011e\u0125";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}