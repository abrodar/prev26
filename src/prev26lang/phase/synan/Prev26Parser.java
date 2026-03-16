// Generated from Prev26Parser.g4 by ANTLR 4.13.2

    package prev26lang.phase.synan;
    import java.util.*;
    import prev26lang.common.report.*;
    import prev26lang.phase.lexan.*;
    import prev26lang.phase.abstr.*;
    import prev26lang.phase.abstr.AST.*;


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
		RULE_funcDecl = 5, RULE_paramList = 6, RULE_param = 7, RULE_compList = 8, 
		RULE_comp = 9, RULE_typeList = 10, RULE_type = 11, RULE_exprList = 12, 
		RULE_expr = 13, RULE_assignExpr = 14, RULE_castExpr = 15, RULE_boolORExpr = 16, 
		RULE_boolANDExpr = 17, RULE_compExpr = 18, RULE_addExpr = 19, RULE_mulExpr = 20, 
		RULE_prefixExpr = 21, RULE_postfixExpr = 22, RULE_basicExpr = 23;
	private static String[] makeRuleNames() {
		return new String[] {
			"source", "prog", "defn", "typeDecl", "varDecl", "funcDecl", "paramList", 
			"param", "compList", "comp", "typeList", "type", "exprList", "expr", 
			"assignExpr", "castExpr", "boolORExpr", "boolANDExpr", "compExpr", "addExpr", 
			"mulExpr", "prefixExpr", "postfixExpr", "basicExpr"
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
		public AST.Nodes<AST.FullDefn> ast;
		public ProgContext prog;
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
			setState(48);
			((SourceContext)_localctx).prog = prog();
			setState(49);
			match(EOF);
			 ((SourceContext)_localctx).ast =  ((SourceContext)_localctx).prog.ast; 
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
		public AST.Nodes<AST.FullDefn> ast;
		public DefnContext defn;
		public List<DefnContext> ds = new ArrayList<DefnContext>();
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
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52);
				((ProgContext)_localctx).defn = defn();
				((ProgContext)_localctx).ds.add(((ProgContext)_localctx).defn);
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & 211123412402176L) != 0) );

			            List<AST.FullDefn> list = new ArrayList<>();
			            for (Prev26Parser.DefnContext dctx : ((ProgContext)_localctx).ds) {
			                list.add(dctx.ast);
			            }
			            ((ProgContext)_localctx).ast =  new AST.Nodes<>(list);
			            Abstr.locAttr.put(_localctx.ast, loc(((ProgContext)_localctx).ds.get(0).ast, ((ProgContext)_localctx).ds.get(((ProgContext)_localctx).ds.size()-1).ast));
			        
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
		public AST.FullDefn ast;
		public TypeDeclContext td;
		public VarDeclContext vd;
		public FuncDeclContext fd;
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
			setState(68);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TYP:
				enterOuterAlt(_localctx, 1);
				{
				setState(59);
				((DefnContext)_localctx).td = typeDecl();
				 ((DefnContext)_localctx).ast =  ((DefnContext)_localctx).td.ast; 
				}
				break;
			case VAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				((DefnContext)_localctx).vd = varDecl();
				 ((DefnContext)_localctx).ast =  ((DefnContext)_localctx).vd.ast; 
				}
				break;
			case FUN:
				enterOuterAlt(_localctx, 3);
				{
				setState(65);
				((DefnContext)_localctx).fd = funcDecl();
				 ((DefnContext)_localctx).ast =  ((DefnContext)_localctx).fd.ast; 
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
		public AST.TypDefn ast;
		public Token TYP;
		public Token id;
		public TypeContext t;
		public TerminalNode TYP() { return getToken(Prev26Parser.TYP, 0); }
		public TerminalNode ASSIGN() { return getToken(Prev26Parser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
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
			setState(70);
			((TypeDeclContext)_localctx).TYP = match(TYP);
			setState(71);
			((TypeDeclContext)_localctx).id = match(ID);
			setState(72);
			match(ASSIGN);
			setState(73);
			((TypeDeclContext)_localctx).t = type();

			        ((TypeDeclContext)_localctx).ast =  new TypDefn((((TypeDeclContext)_localctx).id!=null?((TypeDeclContext)_localctx).id.getText():null), ((TypeDeclContext)_localctx).t.ast);
			        Abstr.locAttr.put(_localctx.ast, loc(((TypeDeclContext)_localctx).TYP, ((TypeDeclContext)_localctx).t.ast));
			        
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
		public AST.VarDefn ast;
		public Token VAR;
		public Token id;
		public TypeContext t;
		public TerminalNode VAR() { return getToken(Prev26Parser.VAR, 0); }
		public TerminalNode COLON() { return getToken(Prev26Parser.COLON, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
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
			setState(76);
			((VarDeclContext)_localctx).VAR = match(VAR);
			setState(77);
			((VarDeclContext)_localctx).id = match(ID);
			setState(78);
			match(COLON);
			setState(79);
			((VarDeclContext)_localctx).t = type();

			        ((VarDeclContext)_localctx).ast =  new VarDefn((((VarDeclContext)_localctx).id!=null?((VarDeclContext)_localctx).id.getText():null), ((VarDeclContext)_localctx).t.ast);
			        Abstr.locAttr.put(_localctx.ast, loc(((VarDeclContext)_localctx).VAR, ((VarDeclContext)_localctx).t.ast));
			        
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
		public AST.FunDefn ast;
		public Token FUN;
		public Token id1;
		public Token LPAREN;
		public ParamListContext pars1;
		public Token RPAREN;
		public TypeContext t1;
		public ExprListContext es;
		public Token id2;
		public ParamListContext pars2;
		public TypeContext t2;
		public TerminalNode FUN() { return getToken(Prev26Parser.FUN, 0); }
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public TerminalNode COLON() { return getToken(Prev26Parser.COLON, 0); }
		public TerminalNode ASSIGN() { return getToken(Prev26Parser.ASSIGN, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public ParamListContext paramList() {
			return getRuleContext(ParamListContext.class,0);
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
			setState(106);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,4,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(82);
				((FuncDeclContext)_localctx).FUN = match(FUN);
				setState(83);
				((FuncDeclContext)_localctx).id1 = match(ID);
				setState(84);
				((FuncDeclContext)_localctx).LPAREN = match(LPAREN);
				setState(86);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(85);
					((FuncDeclContext)_localctx).pars1 = paramList();
					}
				}

				setState(88);
				((FuncDeclContext)_localctx).RPAREN = match(RPAREN);
				setState(89);
				match(COLON);
				setState(90);
				((FuncDeclContext)_localctx).t1 = type();
				setState(91);
				match(ASSIGN);
				setState(92);
				((FuncDeclContext)_localctx).es = exprList();

				        AST.Nodes<AST.ParDefn> p1;
				        try {
				            p1 = ((FuncDeclContext)_localctx).pars1.ast;
				        } catch (NullPointerException e) {
				            p1 = new AST.Nodes<>();
				            Abstr.locAttr.put(p1, loc(((FuncDeclContext)_localctx).LPAREN, ((FuncDeclContext)_localctx).RPAREN));
				        }
				        Exprs body = new Exprs(((FuncDeclContext)_localctx).es.ast);
				        Abstr.locAttr.put(body, loc(((FuncDeclContext)_localctx).es.ast));
				        ((FuncDeclContext)_localctx).ast =  new DefFunDefn((((FuncDeclContext)_localctx).id1!=null?((FuncDeclContext)_localctx).id1.getText():null),  p1, ((FuncDeclContext)_localctx).t1.ast, body);
				        Abstr.locAttr.put(_localctx.ast, loc(((FuncDeclContext)_localctx).FUN, ((FuncDeclContext)_localctx).es.ast));
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(95);
				((FuncDeclContext)_localctx).FUN = match(FUN);
				setState(96);
				((FuncDeclContext)_localctx).id2 = match(ID);
				setState(97);
				((FuncDeclContext)_localctx).LPAREN = match(LPAREN);
				setState(99);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(98);
					((FuncDeclContext)_localctx).pars2 = paramList();
					}
				}

				setState(101);
				((FuncDeclContext)_localctx).RPAREN = match(RPAREN);
				setState(102);
				match(COLON);
				setState(103);
				((FuncDeclContext)_localctx).t2 = type();

				        AST.Nodes<AST.ParDefn> p2;
				        try {
				            p2 = ((FuncDeclContext)_localctx).pars2.ast;
				        } catch (NullPointerException e) {
				            p2 = new AST.Nodes<>();
				            Abstr.locAttr.put(p2, loc(((FuncDeclContext)_localctx).LPAREN, ((FuncDeclContext)_localctx).RPAREN));
				        }
				        ((FuncDeclContext)_localctx).ast =  new ExtFunDefn((((FuncDeclContext)_localctx).id2!=null?((FuncDeclContext)_localctx).id2.getText():null), p2, ((FuncDeclContext)_localctx).t2.ast);
				        Abstr.locAttr.put(_localctx.ast, loc(((FuncDeclContext)_localctx).FUN, ((FuncDeclContext)_localctx).t2.ast));
				        
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
	public static class ParamListContext extends ParserRuleContext {
		public AST.Nodes<AST.ParDefn> ast;
		public ParamContext param;
		public List<ParamContext> ps = new ArrayList<ParamContext>();
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
			setState(108);
			((ParamListContext)_localctx).param = param();
			((ParamListContext)_localctx).ps.add(((ParamListContext)_localctx).param);
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(109);
				match(COMMA);
				setState(110);
				((ParamListContext)_localctx).param = param();
				((ParamListContext)_localctx).ps.add(((ParamListContext)_localctx).param);
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			            List<AST.ParDefn> list = new ArrayList<>();
			            for (Prev26Parser.ParamContext pctx : ((ParamListContext)_localctx).ps) {
			                list.add(pctx.ast);
			            }
			            ((ParamListContext)_localctx).ast =  new AST.Nodes<>(list);
			            Abstr.locAttr.put(_localctx.ast, loc(((ParamListContext)_localctx).ps.get(0).ast, ((ParamListContext)_localctx).ps.get(((ParamListContext)_localctx).ps.size()-1).ast));
			            
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
		public AST.ParDefn ast;
		public Token id;
		public TypeContext t;
		public TerminalNode COLON() { return getToken(Prev26Parser.COLON, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
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
			setState(118);
			((ParamContext)_localctx).id = match(ID);
			setState(119);
			match(COLON);
			setState(120);
			((ParamContext)_localctx).t = type();

			        ((ParamContext)_localctx).ast =  new ParDefn((((ParamContext)_localctx).id!=null?((ParamContext)_localctx).id.getText():null), ((ParamContext)_localctx).t.ast);
			        Abstr.locAttr.put(_localctx.ast, loc(((ParamContext)_localctx).id, ((ParamContext)_localctx).t.ast));
			        
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
	public static class CompListContext extends ParserRuleContext {
		public AST.Nodes<AST.CompDefn> ast;
		public CompContext comp;
		public List<CompContext> cs = new ArrayList<CompContext>();
		public List<CompContext> comp() {
			return getRuleContexts(CompContext.class);
		}
		public CompContext comp(int i) {
			return getRuleContext(CompContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(Prev26Parser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(Prev26Parser.COMMA, i);
		}
		public CompListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_compList; }
	}

	public final CompListContext compList() throws RecognitionException {
		CompListContext _localctx = new CompListContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_compList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
			((CompListContext)_localctx).comp = comp();
			((CompListContext)_localctx).cs.add(((CompListContext)_localctx).comp);
			setState(128);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(124);
				match(COMMA);
				setState(125);
				((CompListContext)_localctx).comp = comp();
				((CompListContext)_localctx).cs.add(((CompListContext)_localctx).comp);
				}
				}
				setState(130);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			            List<AST.CompDefn> list = new ArrayList<>();
			            for (Prev26Parser.CompContext cctx : ((CompListContext)_localctx).cs) {
			                list.add(cctx.ast);
			            }
			            ((CompListContext)_localctx).ast =  new AST.Nodes<>(list);
			            Abstr.locAttr.put(_localctx.ast, loc(((CompListContext)_localctx).cs.get(0).ast, ((CompListContext)_localctx).cs.get(((CompListContext)_localctx).cs.size()-1).ast));
			            
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
	public static class CompContext extends ParserRuleContext {
		public AST.CompDefn ast;
		public Token id;
		public TypeContext t;
		public TerminalNode COLON() { return getToken(Prev26Parser.COLON, 0); }
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public CompContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comp; }
	}

	public final CompContext comp() throws RecognitionException {
		CompContext _localctx = new CompContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_comp);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(133);
			((CompContext)_localctx).id = match(ID);
			setState(134);
			match(COLON);
			setState(135);
			((CompContext)_localctx).t = type();

			        ((CompContext)_localctx).ast =  new CompDefn((((CompContext)_localctx).id!=null?((CompContext)_localctx).id.getText():null), ((CompContext)_localctx).t.ast);
			        Abstr.locAttr.put(_localctx.ast, loc(((CompContext)_localctx).id, ((CompContext)_localctx).t.ast));
			        
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
		public AST.Nodes<AST.Type> ast;
		public TypeContext type;
		public List<TypeContext> ts = new ArrayList<TypeContext>();
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
		enterRule(_localctx, 20, RULE_typeList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1407513709248512L) != 0)) {
				{
				setState(138);
				((TypeListContext)_localctx).type = type();
				((TypeListContext)_localctx).ts.add(((TypeListContext)_localctx).type);
				setState(143);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(139);
					match(COMMA);
					setState(140);
					((TypeListContext)_localctx).type = type();
					((TypeListContext)_localctx).ts.add(((TypeListContext)_localctx).type);
					}
					}
					setState(145);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}


			        List<AST.Type> list = new ArrayList<>();
			        if (((TypeListContext)_localctx).ts != null) {
			            for (Prev26Parser.TypeContext tctx : ((TypeListContext)_localctx).ts) {
			                list.add(tctx.ast);
			            }
			        }
			        ((TypeListContext)_localctx).ast =  new AST.Nodes<>(list);
			        if (!list.isEmpty()) {
			            Abstr.locAttr.put(_localctx.ast, loc(((TypeListContext)_localctx).ts.get(0).ast, ((TypeListContext)_localctx).ts.get(((TypeListContext)_localctx).ts.size()-1).ast));
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
		public AST.Type ast;
		public Token INT;
		public Token CHAR;
		public Token BOOL;
		public Token VOID;
		public Token LBRACK;
		public Token ic;
		public TypeContext t;
		public Token POW;
		public Token LPAREN;
		public CompListContext cl1;
		public Token RPAREN;
		public Token LBRACE;
		public CompListContext cl2;
		public Token RBRACE;
		public TypeListContext tl;
		public Token id;
		public TerminalNode INT() { return getToken(Prev26Parser.INT, 0); }
		public TerminalNode CHAR() { return getToken(Prev26Parser.CHAR, 0); }
		public TerminalNode BOOL() { return getToken(Prev26Parser.BOOL, 0); }
		public TerminalNode VOID() { return getToken(Prev26Parser.VOID, 0); }
		public TerminalNode LBRACK() { return getToken(Prev26Parser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(Prev26Parser.RBRACK, 0); }
		public TerminalNode INT_CONST() { return getToken(Prev26Parser.INT_CONST, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode POW() { return getToken(Prev26Parser.POW, 0); }
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public CompListContext compList() {
			return getRuleContext(CompListContext.class,0);
		}
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
		enterRule(_localctx, 22, RULE_type);
		int _la;
		try {
			setState(194);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(150);
				((TypeContext)_localctx).INT = match(INT);

				        ((TypeContext)_localctx).ast =  new AtomType(AST.AtomType.Type.INT);
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).INT));
				        
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(152);
				((TypeContext)_localctx).CHAR = match(CHAR);

				        ((TypeContext)_localctx).ast =  new AtomType(AST.AtomType.Type.CHAR);
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).CHAR));
				        
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(154);
				((TypeContext)_localctx).BOOL = match(BOOL);

				        ((TypeContext)_localctx).ast =  new AtomType(AST.AtomType.Type.BOOL);
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).BOOL));
				        
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(156);
				((TypeContext)_localctx).VOID = match(VOID);

				        ((TypeContext)_localctx).ast =  new AtomType(AST.AtomType.Type.VOID);
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).VOID));
				        
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(158);
				((TypeContext)_localctx).LBRACK = match(LBRACK);
				setState(159);
				((TypeContext)_localctx).ic = match(INT_CONST);
				setState(160);
				match(RBRACK);
				setState(161);
				((TypeContext)_localctx).t = type();

				        ((TypeContext)_localctx).ast =  new ArrType(((TypeContext)_localctx).t.ast, (((TypeContext)_localctx).ic!=null?((TypeContext)_localctx).ic.getText():null));
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).LBRACK, ((TypeContext)_localctx).t.ast));
				        
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(164);
				((TypeContext)_localctx).POW = match(POW);
				setState(165);
				((TypeContext)_localctx).t = type();

				        ((TypeContext)_localctx).ast =  new PtrType(((TypeContext)_localctx).t.ast);
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).POW, ((TypeContext)_localctx).t.ast));
				        
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(168);
				((TypeContext)_localctx).LPAREN = match(LPAREN);
				setState(169);
				((TypeContext)_localctx).cl1 = compList();
				setState(170);
				((TypeContext)_localctx).RPAREN = match(RPAREN);

				        ((TypeContext)_localctx).ast =  new StrType(((TypeContext)_localctx).cl1.ast);
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).LPAREN, ((TypeContext)_localctx).RPAREN));
				        
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(173);
				((TypeContext)_localctx).LBRACE = match(LBRACE);
				setState(175);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(174);
					((TypeContext)_localctx).cl2 = compList();
					}
				}

				setState(177);
				((TypeContext)_localctx).RBRACE = match(RBRACE);

				        AST.Nodes<AST.CompDefn> comps;
				        try {
				            comps = ((TypeContext)_localctx).cl2.ast;
				        } catch (NullPointerException e) {
				            comps = new AST.Nodes<>();
				            Abstr.locAttr.put(comps, loc(((TypeContext)_localctx).LBRACE, ((TypeContext)_localctx).RBRACE));
				        }
				        ((TypeContext)_localctx).ast =  new UniType(comps);
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).LBRACE, ((TypeContext)_localctx).RBRACE));
				        
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(179);
				((TypeContext)_localctx).LPAREN = match(LPAREN);
				setState(180);
				match(COLON);
				setState(181);
				((TypeContext)_localctx).tl = typeList();
				setState(182);
				match(COLON);
				setState(183);
				((TypeContext)_localctx).t = type();
				setState(184);
				((TypeContext)_localctx).RPAREN = match(RPAREN);

				        ((TypeContext)_localctx).ast =  new FunType(((TypeContext)_localctx).tl.ast, ((TypeContext)_localctx).t.ast);
				        Abstr.locAttr.put(((TypeContext)_localctx).tl.ast, loc(((TypeContext)_localctx).LPAREN, ((TypeContext)_localctx).RPAREN));
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).LPAREN, ((TypeContext)_localctx).RPAREN));
				        
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(187);
				match(LPAREN);
				setState(188);
				((TypeContext)_localctx).t = type();
				setState(189);
				match(RPAREN);

				        ((TypeContext)_localctx).ast =  ((TypeContext)_localctx).t.ast;
				        
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(192);
				((TypeContext)_localctx).id = match(ID);

				        ((TypeContext)_localctx).ast =  new NameType((((TypeContext)_localctx).id!=null?((TypeContext)_localctx).id.getText():null));
				        Abstr.locAttr.put(_localctx.ast, loc(((TypeContext)_localctx).id));
				        
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
		public AST.Nodes<AST.Expr> ast;
		public ExprContext expr;
		public List<ExprContext> es = new ArrayList<ExprContext>();
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
		enterRule(_localctx, 24, RULE_exprList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(196);
			((ExprListContext)_localctx).expr = expr();
			((ExprListContext)_localctx).es.add(((ExprListContext)_localctx).expr);
			setState(201);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==COMMA) {
				{
				{
				setState(197);
				match(COMMA);
				setState(198);
				((ExprListContext)_localctx).expr = expr();
				((ExprListContext)_localctx).es.add(((ExprListContext)_localctx).expr);
				}
				}
				setState(203);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}

			        List<AST.Expr> list = new ArrayList<>();
			        for (Prev26Parser.ExprContext ectx : ((ExprListContext)_localctx).es) {
			            list.add(ectx.ast);
			        }
			        ((ExprListContext)_localctx).ast =  new AST.Nodes<>(list);
			        Abstr.locAttr.put(_localctx.ast, loc(((ExprListContext)_localctx).es.get(0).ast, ((ExprListContext)_localctx).es.get(((ExprListContext)_localctx).es.size()-1).ast));
			        
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
		public AST.Expr ast;
		public AssignExprContext as;
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
		enterRule(_localctx, 26, RULE_expr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			((ExprContext)_localctx).as = assignExpr();
			 ((ExprContext)_localctx).ast =  ((ExprContext)_localctx).as.ast; 
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
		public AST.Expr ast;
		public CastExprContext ce1;
		public CastExprContext ce2;
		public CastExprContext ce3;
		public TerminalNode ASSIGN() { return getToken(Prev26Parser.ASSIGN, 0); }
		public List<CastExprContext> castExpr() {
			return getRuleContexts(CastExprContext.class);
		}
		public CastExprContext castExpr(int i) {
			return getRuleContext(CastExprContext.class,i);
		}
		public AssignExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignExpr; }
	}

	public final AssignExprContext assignExpr() throws RecognitionException {
		AssignExprContext _localctx = new AssignExprContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_assignExpr);
		try {
			setState(217);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(209);
				((AssignExprContext)_localctx).ce1 = castExpr(0);
				setState(210);
				match(ASSIGN);
				setState(211);
				((AssignExprContext)_localctx).ce2 = castExpr(0);

				                ((AssignExprContext)_localctx).ast =  new AsgnExpr(((AssignExprContext)_localctx).ce1.ast, ((AssignExprContext)_localctx).ce2.ast);
				                Abstr.locAttr.put(_localctx.ast, loc(((AssignExprContext)_localctx).ce1.ast, ((AssignExprContext)_localctx).ce2.ast));
				                
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(214);
				((AssignExprContext)_localctx).ce3 = castExpr(0);

				                ((AssignExprContext)_localctx).ast =  ((AssignExprContext)_localctx).ce3.ast;
				                
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
		public AST.Expr ast;
		public CastExprContext ce;
		public BoolORExprContext b;
		public TypeContext t;
		public BoolORExprContext boolORExpr() {
			return getRuleContext(BoolORExprContext.class,0);
		}
		public TerminalNode AS() { return getToken(Prev26Parser.AS, 0); }
		public CastExprContext castExpr() {
			return getRuleContext(CastExprContext.class,0);
		}
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
		int _startState = 30;
		enterRecursionRule(_localctx, 30, RULE_castExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(220);
			((CastExprContext)_localctx).b = boolORExpr(0);
			 ((CastExprContext)_localctx).ast =  ((CastExprContext)_localctx).b.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(230);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new CastExprContext(_parentctx, _parentState);
					_localctx.ce = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_castExpr);
					setState(223);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(224);
					match(AS);
					setState(225);
					((CastExprContext)_localctx).t = type();

					                          ((CastExprContext)_localctx).ast =  new CastExpr(((CastExprContext)_localctx).t.ast, ((CastExprContext)_localctx).ce.ast);
					                          Abstr.locAttr.put(_localctx.ast, loc(((CastExprContext)_localctx).ce.ast, ((CastExprContext)_localctx).t.ast));
					                          
					}
					} 
				}
				setState(232);
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
	public static class BoolORExprContext extends ParserRuleContext {
		public AST.Expr ast;
		public BoolORExprContext b1;
		public BoolANDExprContext b3;
		public Token op;
		public BoolANDExprContext b2;
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
		int _startState = 32;
		enterRecursionRule(_localctx, 32, RULE_boolORExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(234);
			((BoolORExprContext)_localctx).b3 = boolANDExpr(0);
			 ((BoolORExprContext)_localctx).ast =  ((BoolORExprContext)_localctx).b3.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(244);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BoolORExprContext(_parentctx, _parentState);
					_localctx.b1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_boolORExpr);
					setState(237);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(238);
					((BoolORExprContext)_localctx).op = match(OR);
					setState(239);
					((BoolORExprContext)_localctx).b2 = boolANDExpr(0);

					                          ((BoolORExprContext)_localctx).ast =  new BinExpr(AST.BinExpr.Oper.OR,((BoolORExprContext)_localctx).b1.ast,((BoolORExprContext)_localctx).b2.ast);
					                          Abstr.locAttr.put(_localctx.ast, loc(((BoolORExprContext)_localctx).b1.ast, ((BoolORExprContext)_localctx).b2.ast));
					                          
					}
					} 
				}
				setState(246);
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
	public static class BoolANDExprContext extends ParserRuleContext {
		public AST.Expr ast;
		public BoolANDExprContext b1;
		public CompExprContext ce2;
		public CompExprContext ce;
		public CompExprContext compExpr() {
			return getRuleContext(CompExprContext.class,0);
		}
		public TerminalNode AND() { return getToken(Prev26Parser.AND, 0); }
		public BoolANDExprContext boolANDExpr() {
			return getRuleContext(BoolANDExprContext.class,0);
		}
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
		int _startState = 34;
		enterRecursionRule(_localctx, 34, RULE_boolANDExpr, _p);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(248);
			((BoolANDExprContext)_localctx).ce2 = compExpr();
			 ((BoolANDExprContext)_localctx).ast =  ((BoolANDExprContext)_localctx).ce2.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(258);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,15,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new BoolANDExprContext(_parentctx, _parentState);
					_localctx.b1 = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_boolANDExpr);
					setState(251);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(252);
					match(AND);
					setState(253);
					((BoolANDExprContext)_localctx).ce = compExpr();

					                          ((BoolANDExprContext)_localctx).ast =  new BinExpr(AST.BinExpr.Oper.AND, ((BoolANDExprContext)_localctx).b1.ast, ((BoolANDExprContext)_localctx).ce.ast);
					                          Abstr.locAttr.put(_localctx.ast, loc(((BoolANDExprContext)_localctx).b1.ast, ((BoolANDExprContext)_localctx).ce.ast));
					                          
					}
					} 
				}
				setState(260);
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
	public static class CompExprContext extends ParserRuleContext {
		public AST.Expr ast;
		public AddExprContext a1;
		public Token op;
		public AddExprContext a2;
		public AddExprContext ae;
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
		enterRule(_localctx, 36, RULE_compExpr);
		int _la;
		try {
			setState(269);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,16,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(261);
				((CompExprContext)_localctx).a1 = addExpr(0);
				setState(262);
				((CompExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 516096L) != 0)) ) {
					((CompExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(263);
				((CompExprContext)_localctx).a2 = addExpr(0);

				                AST.BinExpr.Oper oper = switch((((CompExprContext)_localctx).op!=null?((CompExprContext)_localctx).op.getType():0)) {
				                    case Prev26Parser.EQU -> AST.BinExpr.Oper.EQU;
				                    case Prev26Parser.NEQ -> AST.BinExpr.Oper.NEQ;
				                    case Prev26Parser.LT -> AST.BinExpr.Oper.LTH;
				                    case Prev26Parser.GT -> AST.BinExpr.Oper.GTH;
				                    case Prev26Parser.LE -> AST.BinExpr.Oper.LEQ;
				                    case Prev26Parser.GE -> AST.BinExpr.Oper.GEQ;
				                    default -> throw new Report.InternalError();
				                };
				                ((CompExprContext)_localctx).ast =  new BinExpr(oper, ((CompExprContext)_localctx).a1.ast, ((CompExprContext)_localctx).a2.ast);
				                Abstr.locAttr.put(_localctx.ast, loc(((CompExprContext)_localctx).a1.ast, ((CompExprContext)_localctx).a2.ast));
				            
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(266);
				((CompExprContext)_localctx).ae = addExpr(0);

				                ((CompExprContext)_localctx).ast =  ((CompExprContext)_localctx).ae.ast;
				            
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
		public AST.Expr ast;
		public AddExprContext a;
		public MulExprContext m2;
		public Token op;
		public MulExprContext m1;
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
		int _startState = 38;
		enterRecursionRule(_localctx, 38, RULE_addExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(272);
			((AddExprContext)_localctx).m2 = mulExpr(0);
			 ((AddExprContext)_localctx).ast =  ((AddExprContext)_localctx).m2.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(282);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new AddExprContext(_parentctx, _parentState);
					_localctx.a = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_addExpr);
					setState(275);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(276);
					((AddExprContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !(_la==ADD || _la==SUB) ) {
						((AddExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(277);
					((AddExprContext)_localctx).m1 = mulExpr(0);

					                          AST.BinExpr.Oper oper = switch((((AddExprContext)_localctx).op!=null?((AddExprContext)_localctx).op.getType():0)) {
					                              case Prev26Parser.ADD -> AST.BinExpr.Oper.ADD;
					                              case Prev26Parser.SUB -> AST.BinExpr.Oper.SUB;
					                              default -> throw new Report.InternalError();
					                          };
					                          ((AddExprContext)_localctx).ast =  new BinExpr(oper, ((AddExprContext)_localctx).a.ast, ((AddExprContext)_localctx).m1.ast);
					                          Abstr.locAttr.put(_localctx.ast, loc(((AddExprContext)_localctx).a.ast, ((AddExprContext)_localctx).m1.ast));
					                      
					}
					} 
				}
				setState(284);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,17,_ctx);
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
		public AST.Expr ast;
		public MulExprContext me;
		public PrefixExprContext pe2;
		public Token op;
		public PrefixExprContext pe1;
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
		int _startState = 40;
		enterRecursionRule(_localctx, 40, RULE_mulExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(286);
			((MulExprContext)_localctx).pe2 = prefixExpr();
			 ((MulExprContext)_localctx).ast =  ((MulExprContext)_localctx).pe2.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(296);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					{
					_localctx = new MulExprContext(_parentctx, _parentState);
					_localctx.me = _prevctx;
					pushNewRecursionContext(_localctx, _startState, RULE_mulExpr);
					setState(289);
					if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
					setState(290);
					((MulExprContext)_localctx).op = _input.LT(1);
					_la = _input.LA(1);
					if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 4864L) != 0)) ) {
						((MulExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(291);
					((MulExprContext)_localctx).pe1 = prefixExpr();

					                          AST.BinExpr.Oper oper = switch((((MulExprContext)_localctx).op!=null?((MulExprContext)_localctx).op.getType():0)) {
					                              case Prev26Parser.MUL -> AST.BinExpr.Oper.MUL;
					                              case Prev26Parser.DIV -> AST.BinExpr.Oper.DIV;
					                              case Prev26Parser.MOD -> AST.BinExpr.Oper.MOD;
					                              default -> throw new Report.InternalError();
					                          };
					                          ((MulExprContext)_localctx).ast =  new BinExpr(oper, ((MulExprContext)_localctx).me.ast, ((MulExprContext)_localctx).pe1.ast);
					                          Abstr.locAttr.put(_localctx.ast, loc(((MulExprContext)_localctx).me.ast, ((MulExprContext)_localctx).pe1.ast));
					                      
					}
					} 
				}
				setState(298);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
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
		public AST.Expr ast;
		public Token op;
		public PrefixExprContext pe;
		public PostfixExprContext po;
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
		enterRule(_localctx, 42, RULE_prefixExpr);
		int _la;
		try {
			setState(306);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case ADD:
			case SUB:
			case POW:
			case NOT:
				enterOuterAlt(_localctx, 1);
				{
				setState(299);
				((PrefixExprContext)_localctx).op = _input.LT(1);
				_la = _input.LA(1);
				if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & 2199056813056L) != 0)) ) {
					((PrefixExprContext)_localctx).op = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(300);
				((PrefixExprContext)_localctx).pe = prefixExpr();

				                    AST.PfxExpr.Oper oper = switch((((PrefixExprContext)_localctx).op!=null?((PrefixExprContext)_localctx).op.getType():0)) {
				                        case Prev26Parser.NOT -> AST.PfxExpr.Oper.NOT;
				                        case Prev26Parser.ADD -> AST.PfxExpr.Oper.ADD;
				                        case Prev26Parser.SUB -> AST.PfxExpr.Oper.SUB;
				                        case Prev26Parser.POW -> AST.PfxExpr.Oper.PTR;
				                        default -> throw new Report.InternalError();
				                    };
				                    ((PrefixExprContext)_localctx).ast =  new PfxExpr(oper, ((PrefixExprContext)_localctx).pe.ast);
				                    Abstr.locAttr.put(_localctx.ast, loc(((PrefixExprContext)_localctx).op, ((PrefixExprContext)_localctx).pe.ast));
				                
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
				setState(303);
				((PrefixExprContext)_localctx).po = postfixExpr(0);
				 ((PrefixExprContext)_localctx).ast =  ((PrefixExprContext)_localctx).po.ast; 
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
		public AST.Expr ast;
		public PostfixExprContext po;
		public PostfixExprContext po1;
		public PostfixExprContext po2;
		public PostfixExprContext po3;
		public BasicExprContext b;
		public Token POW;
		public Token LPAREN;
		public ExprListContext el;
		public Token RPAREN;
		public ExprContext e;
		public Token RBRACK;
		public Token id;
		public BasicExprContext basicExpr() {
			return getRuleContext(BasicExprContext.class,0);
		}
		public TerminalNode POW() { return getToken(Prev26Parser.POW, 0); }
		public PostfixExprContext postfixExpr() {
			return getRuleContext(PostfixExprContext.class,0);
		}
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public ExprListContext exprList() {
			return getRuleContext(ExprListContext.class,0);
		}
		public TerminalNode LBRACK() { return getToken(Prev26Parser.LBRACK, 0); }
		public TerminalNode RBRACK() { return getToken(Prev26Parser.RBRACK, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
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
		int _startState = 44;
		enterRecursionRule(_localctx, 44, RULE_postfixExpr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(309);
			((PostfixExprContext)_localctx).b = basicExpr();
			 ((PostfixExprContext)_localctx).ast =  ((PostfixExprContext)_localctx).b.ast; 
			}
			_ctx.stop = _input.LT(-1);
			setState(334);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(332);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,21,_ctx) ) {
					case 1:
						{
						_localctx = new PostfixExprContext(_parentctx, _parentState);
						_localctx.po = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpr);
						setState(312);
						if (!(precpred(_ctx, 5))) throw new FailedPredicateException(this, "precpred(_ctx, 5)");
						setState(313);
						((PostfixExprContext)_localctx).POW = match(POW);

						                          ((PostfixExprContext)_localctx).ast =  new SfxExpr(AST.SfxExpr.Oper.PTR, ((PostfixExprContext)_localctx).po.ast);
						                          Abstr.locAttr.put(_localctx.ast, loc(((PostfixExprContext)_localctx).po.ast, ((PostfixExprContext)_localctx).POW));
						                          
						}
						break;
					case 2:
						{
						_localctx = new PostfixExprContext(_parentctx, _parentState);
						_localctx.po1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpr);
						setState(315);
						if (!(precpred(_ctx, 4))) throw new FailedPredicateException(this, "precpred(_ctx, 4)");
						setState(316);
						((PostfixExprContext)_localctx).LPAREN = match(LPAREN);
						setState(318);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if ((((_la) & ~0x3f) == 0 && ((1L << _la) & 1736996477733902L) != 0)) {
							{
							setState(317);
							((PostfixExprContext)_localctx).el = exprList();
							}
						}

						setState(320);
						((PostfixExprContext)_localctx).RPAREN = match(RPAREN);

						                           AST.Nodes<AST.Expr> args;
						                           try {
						                              args = ((PostfixExprContext)_localctx).el.ast;
						                           } catch (NullPointerException e) {
						                              args = new AST.Nodes<>();
						                              Abstr.locAttr.put(args, loc(((PostfixExprContext)_localctx).LPAREN, ((PostfixExprContext)_localctx).RPAREN));
						                           }
						                          ((PostfixExprContext)_localctx).ast =  new CallExpr(((PostfixExprContext)_localctx).po1.ast, args);
						                          Abstr.locAttr.put(_localctx.ast, loc(((PostfixExprContext)_localctx).po1.ast, ((PostfixExprContext)_localctx).RPAREN));
						                          
						}
						break;
					case 3:
						{
						_localctx = new PostfixExprContext(_parentctx, _parentState);
						_localctx.po2 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpr);
						setState(322);
						if (!(precpred(_ctx, 3))) throw new FailedPredicateException(this, "precpred(_ctx, 3)");
						setState(323);
						match(LBRACK);
						setState(324);
						((PostfixExprContext)_localctx).e = expr();
						setState(325);
						((PostfixExprContext)_localctx).RBRACK = match(RBRACK);

						                          ((PostfixExprContext)_localctx).ast =  new ArrExpr(((PostfixExprContext)_localctx).po2.ast, ((PostfixExprContext)_localctx).e.ast);
						                          Abstr.locAttr.put(_localctx.ast, loc(((PostfixExprContext)_localctx).po2.ast, ((PostfixExprContext)_localctx).RBRACK));
						                          
						}
						break;
					case 4:
						{
						_localctx = new PostfixExprContext(_parentctx, _parentState);
						_localctx.po3 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_postfixExpr);
						setState(328);
						if (!(precpred(_ctx, 2))) throw new FailedPredicateException(this, "precpred(_ctx, 2)");
						setState(329);
						match(DOT);
						setState(330);
						((PostfixExprContext)_localctx).id = match(ID);

						                          ((PostfixExprContext)_localctx).ast =  new CompExpr(((PostfixExprContext)_localctx).po3.ast, (((PostfixExprContext)_localctx).id!=null?((PostfixExprContext)_localctx).id.getText():null));
						                          Abstr.locAttr.put(_localctx.ast, loc(((PostfixExprContext)_localctx).po3.ast, ((PostfixExprContext)_localctx).id));
						                          
						}
						break;
					}
					} 
				}
				setState(336);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
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
		public AST.Expr ast;
		public Token ic;
		public Token cc;
		public Token sc;
		public Token bct;
		public Token bcf;
		public Token vc;
		public Token nc;
		public Token LPAREN;
		public ExprListContext es;
		public Token RPAREN;
		public Token SIZEOF;
		public TypeContext t;
		public Token IF;
		public ExprContext e2;
		public ExprListContext es2;
		public Token END;
		public ExprContext e3;
		public ExprListContext es31;
		public ExprListContext es32;
		public Token WHILE;
		public ExprContext e4;
		public ExprListContext es4;
		public Token LET;
		public ProgContext pg;
		public ExprListContext es5;
		public Token id;
		public TerminalNode INT_CONST() { return getToken(Prev26Parser.INT_CONST, 0); }
		public TerminalNode CHAR_CONST() { return getToken(Prev26Parser.CHAR_CONST, 0); }
		public TerminalNode STRING_CONST() { return getToken(Prev26Parser.STRING_CONST, 0); }
		public TerminalNode TRUE() { return getToken(Prev26Parser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(Prev26Parser.FALSE, 0); }
		public TerminalNode NONE() { return getToken(Prev26Parser.NONE, 0); }
		public TerminalNode NIL() { return getToken(Prev26Parser.NIL, 0); }
		public TerminalNode LPAREN() { return getToken(Prev26Parser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(Prev26Parser.RPAREN, 0); }
		public List<ExprListContext> exprList() {
			return getRuleContexts(ExprListContext.class);
		}
		public ExprListContext exprList(int i) {
			return getRuleContext(ExprListContext.class,i);
		}
		public TerminalNode SIZEOF() { return getToken(Prev26Parser.SIZEOF, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IF() { return getToken(Prev26Parser.IF, 0); }
		public TerminalNode THEN() { return getToken(Prev26Parser.THEN, 0); }
		public TerminalNode END() { return getToken(Prev26Parser.END, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public TerminalNode ELSE() { return getToken(Prev26Parser.ELSE, 0); }
		public TerminalNode WHILE() { return getToken(Prev26Parser.WHILE, 0); }
		public TerminalNode DO() { return getToken(Prev26Parser.DO, 0); }
		public TerminalNode LET() { return getToken(Prev26Parser.LET, 0); }
		public TerminalNode IN() { return getToken(Prev26Parser.IN, 0); }
		public ProgContext prog() {
			return getRuleContext(ProgContext.class,0);
		}
		public TerminalNode ID() { return getToken(Prev26Parser.ID, 0); }
		public BasicExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_basicExpr; }
	}

	public final BasicExprContext basicExpr() throws RecognitionException {
		BasicExprContext _localctx = new BasicExprContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_basicExpr);
		try {
			setState(392);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(337);
				((BasicExprContext)_localctx).ic = match(INT_CONST);

				             ((BasicExprContext)_localctx).ast =  new AtomExpr(AST.AtomExpr.Type.INT, (((BasicExprContext)_localctx).ic!=null?((BasicExprContext)_localctx).ic.getText():null));
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).ic));
				             
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(339);
				((BasicExprContext)_localctx).cc = match(CHAR_CONST);

				             ((BasicExprContext)_localctx).ast =  new AtomExpr(AST.AtomExpr.Type.CHAR, (((BasicExprContext)_localctx).cc!=null?((BasicExprContext)_localctx).cc.getText():null));
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).cc));
				             
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(341);
				((BasicExprContext)_localctx).sc = match(STRING_CONST);

				             ((BasicExprContext)_localctx).ast =  new AtomExpr(AST.AtomExpr.Type.STR, (((BasicExprContext)_localctx).sc!=null?((BasicExprContext)_localctx).sc.getText():null));
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).sc));
				             
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(343);
				((BasicExprContext)_localctx).bct = match(TRUE);

				             ((BasicExprContext)_localctx).ast =  new AtomExpr(AST.AtomExpr.Type.BOOL, (((BasicExprContext)_localctx).bct!=null?((BasicExprContext)_localctx).bct.getText():null));
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).bct));
				             
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(345);
				((BasicExprContext)_localctx).bcf = match(FALSE);

				             ((BasicExprContext)_localctx).ast =  new AtomExpr(AST.AtomExpr.Type.BOOL, (((BasicExprContext)_localctx).bcf!=null?((BasicExprContext)_localctx).bcf.getText():null));
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).bcf));
				             
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(347);
				((BasicExprContext)_localctx).vc = match(NONE);

				             ((BasicExprContext)_localctx).ast =  new AtomExpr(AST.AtomExpr.Type.VOID, (((BasicExprContext)_localctx).vc!=null?((BasicExprContext)_localctx).vc.getText():null));
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).vc));
				             
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(349);
				((BasicExprContext)_localctx).nc = match(NIL);

				             ((BasicExprContext)_localctx).ast =  new AtomExpr(AST.AtomExpr.Type.PTR, (((BasicExprContext)_localctx).nc!=null?((BasicExprContext)_localctx).nc.getText():null));
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).nc));
				             
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(351);
				((BasicExprContext)_localctx).LPAREN = match(LPAREN);
				setState(352);
				((BasicExprContext)_localctx).es = exprList();
				setState(353);
				((BasicExprContext)_localctx).RPAREN = match(RPAREN);

				             ((BasicExprContext)_localctx).ast =  new Exprs(((BasicExprContext)_localctx).es.ast);
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).LPAREN, ((BasicExprContext)_localctx).RPAREN));
				             
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(356);
				((BasicExprContext)_localctx).SIZEOF = match(SIZEOF);
				setState(357);
				((BasicExprContext)_localctx).t = type();

				             ((BasicExprContext)_localctx).ast =  new SizeExpr(((BasicExprContext)_localctx).t.ast);
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).SIZEOF, ((BasicExprContext)_localctx).t.ast));
				             
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(360);
				((BasicExprContext)_localctx).IF = match(IF);
				setState(361);
				((BasicExprContext)_localctx).e2 = expr();
				setState(362);
				match(THEN);
				setState(363);
				((BasicExprContext)_localctx).es2 = exprList();
				setState(364);
				((BasicExprContext)_localctx).END = match(END);

				             Exprs body = new Exprs(((BasicExprContext)_localctx).es2.ast);
				             Abstr.locAttr.put(body, loc(((BasicExprContext)_localctx).es2.ast));
				             ((BasicExprContext)_localctx).ast =  new IfThenExpr(((BasicExprContext)_localctx).e2.ast, body);
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).IF, ((BasicExprContext)_localctx).END));
				             
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(367);
				((BasicExprContext)_localctx).IF = match(IF);
				setState(368);
				((BasicExprContext)_localctx).e3 = expr();
				setState(369);
				match(THEN);
				setState(370);
				((BasicExprContext)_localctx).es31 = exprList();
				setState(371);
				match(ELSE);
				setState(372);
				((BasicExprContext)_localctx).es32 = exprList();
				setState(373);
				((BasicExprContext)_localctx).END = match(END);

				             Exprs body31 = new Exprs(((BasicExprContext)_localctx).es31.ast);
				             Abstr.locAttr.put(body31, loc(((BasicExprContext)_localctx).es31.ast));
				             Exprs body32 = new Exprs(((BasicExprContext)_localctx).es32.ast);
				             Abstr.locAttr.put(body32, loc(((BasicExprContext)_localctx).es32.ast));
				             ((BasicExprContext)_localctx).ast =  new IfThenElseExpr(((BasicExprContext)_localctx).e3.ast, body31, body32);
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).IF, ((BasicExprContext)_localctx).END));
				             
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(376);
				((BasicExprContext)_localctx).WHILE = match(WHILE);
				setState(377);
				((BasicExprContext)_localctx).e4 = expr();
				setState(378);
				match(DO);
				setState(379);
				((BasicExprContext)_localctx).es4 = exprList();
				setState(380);
				((BasicExprContext)_localctx).END = match(END);

				             Exprs body = new Exprs(((BasicExprContext)_localctx).es4.ast);
				             Abstr.locAttr.put(body, loc(((BasicExprContext)_localctx).es4.ast));
				             ((BasicExprContext)_localctx).ast =  new WhileExpr(((BasicExprContext)_localctx).e4.ast, body);
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).WHILE, ((BasicExprContext)_localctx).END));
				             
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(383);
				((BasicExprContext)_localctx).LET = match(LET);
				setState(384);
				((BasicExprContext)_localctx).pg = prog();
				setState(385);
				match(IN);
				setState(386);
				((BasicExprContext)_localctx).es5 = exprList();
				setState(387);
				((BasicExprContext)_localctx).END = match(END);

				             Exprs body = new Exprs(((BasicExprContext)_localctx).es5.ast);
				             Abstr.locAttr.put(body, loc(((BasicExprContext)_localctx).es5.ast));
				             ((BasicExprContext)_localctx).ast =  new LetExpr(((BasicExprContext)_localctx).pg.ast, body);
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).LET, ((BasicExprContext)_localctx).END));
				             
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(390);
				((BasicExprContext)_localctx).id = match(ID);

				             ((BasicExprContext)_localctx).ast =  new NameExpr((((BasicExprContext)_localctx).id!=null?((BasicExprContext)_localctx).id.getText():null));
				             Abstr.locAttr.put(_localctx.ast, loc(((BasicExprContext)_localctx).id));
				             
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
		case 15:
			return castExpr_sempred((CastExprContext)_localctx, predIndex);
		case 16:
			return boolORExpr_sempred((BoolORExprContext)_localctx, predIndex);
		case 17:
			return boolANDExpr_sempred((BoolANDExprContext)_localctx, predIndex);
		case 19:
			return addExpr_sempred((AddExprContext)_localctx, predIndex);
		case 20:
			return mulExpr_sempred((MulExprContext)_localctx, predIndex);
		case 22:
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
		"\u0004\u00014\u018b\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004\u0007\u0004\u0002"+
		"\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007\u0007\u0007\u0002"+
		"\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b\u0007\u000b\u0002"+
		"\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002\u000f\u0007\u000f"+
		"\u0002\u0010\u0007\u0010\u0002\u0011\u0007\u0011\u0002\u0012\u0007\u0012"+
		"\u0002\u0013\u0007\u0013\u0002\u0014\u0007\u0014\u0002\u0015\u0007\u0015"+
		"\u0002\u0016\u0007\u0016\u0002\u0017\u0007\u0017\u0001\u0000\u0001\u0000"+
		"\u0001\u0000\u0001\u0000\u0001\u0001\u0004\u00016\b\u0001\u000b\u0001"+
		"\f\u00017\u0001\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002"+
		"\u0003\u0002E\b\u0002\u0001\u0003\u0001\u0003\u0001\u0003\u0001\u0003"+
		"\u0001\u0003\u0001\u0003\u0001\u0004\u0001\u0004\u0001\u0004\u0001\u0004"+
		"\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0003\u0005W\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0003\u0005d\b\u0005\u0001\u0005\u0001\u0005\u0001\u0005"+
		"\u0001\u0005\u0001\u0005\u0003\u0005k\b\u0005\u0001\u0006\u0001\u0006"+
		"\u0001\u0006\u0005\u0006p\b\u0006\n\u0006\f\u0006s\t\u0006\u0001\u0006"+
		"\u0001\u0006\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007\u0001\u0007"+
		"\u0001\b\u0001\b\u0001\b\u0005\b\u007f\b\b\n\b\f\b\u0082\t\b\u0001\b\u0001"+
		"\b\u0001\t\u0001\t\u0001\t\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0005"+
		"\n\u008e\b\n\n\n\f\n\u0091\t\n\u0003\n\u0093\b\n\u0001\n\u0001\n\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0003\u000b\u00b0\b\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001\u000b\u0001"+
		"\u000b\u0001\u000b\u0003\u000b\u00c3\b\u000b\u0001\f\u0001\f\u0001\f\u0005"+
		"\f\u00c8\b\f\n\f\f\f\u00cb\t\f\u0001\f\u0001\f\u0001\r\u0001\r\u0001\r"+
		"\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e\u0001\u000e"+
		"\u0001\u000e\u0001\u000e\u0003\u000e\u00da\b\u000e\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f\u0001\u000f"+
		"\u0001\u000f\u0005\u000f\u00e5\b\u000f\n\u000f\f\u000f\u00e8\t\u000f\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001\u0010\u0001"+
		"\u0010\u0001\u0010\u0001\u0010\u0005\u0010\u00f3\b\u0010\n\u0010\f\u0010"+
		"\u00f6\t\u0010\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011"+
		"\u0001\u0011\u0001\u0011\u0001\u0011\u0001\u0011\u0005\u0011\u0101\b\u0011"+
		"\n\u0011\f\u0011\u0104\t\u0011\u0001\u0012\u0001\u0012\u0001\u0012\u0001"+
		"\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0001\u0012\u0003\u0012\u010e"+
		"\b\u0012\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0001"+
		"\u0013\u0001\u0013\u0001\u0013\u0001\u0013\u0005\u0013\u0119\b\u0013\n"+
		"\u0013\f\u0013\u011c\t\u0013\u0001\u0014\u0001\u0014\u0001\u0014\u0001"+
		"\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0001\u0014\u0005"+
		"\u0014\u0127\b\u0014\n\u0014\f\u0014\u012a\t\u0014\u0001\u0015\u0001\u0015"+
		"\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0001\u0015\u0003\u0015"+
		"\u0133\b\u0015\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0003\u0016"+
		"\u013f\b\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016\u0001\u0016"+
		"\u0001\u0016\u0005\u0016\u014d\b\u0016\n\u0016\f\u0016\u0150\t\u0016\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001\u0017\u0001"+
		"\u0017\u0003\u0017\u0189\b\u0017\u0001\u0017\u0000\u0006\u001e \"&(,\u0018"+
		"\u0000\u0002\u0004\u0006\b\n\f\u000e\u0010\u0012\u0014\u0016\u0018\u001a"+
		"\u001c\u001e \"$&(*,.\u0000\u0004\u0001\u0000\r\u0012\u0001\u0000\n\u000b"+
		"\u0002\u0000\b\t\f\f\u0003\u0000\n\u000b\u0019\u0019))\u01a2\u00000\u0001"+
		"\u0000\u0000\u0000\u00025\u0001\u0000\u0000\u0000\u0004D\u0001\u0000\u0000"+
		"\u0000\u0006F\u0001\u0000\u0000\u0000\bL\u0001\u0000\u0000\u0000\nj\u0001"+
		"\u0000\u0000\u0000\fl\u0001\u0000\u0000\u0000\u000ev\u0001\u0000\u0000"+
		"\u0000\u0010{\u0001\u0000\u0000\u0000\u0012\u0085\u0001\u0000\u0000\u0000"+
		"\u0014\u0092\u0001\u0000\u0000\u0000\u0016\u00c2\u0001\u0000\u0000\u0000"+
		"\u0018\u00c4\u0001\u0000\u0000\u0000\u001a\u00ce\u0001\u0000\u0000\u0000"+
		"\u001c\u00d9\u0001\u0000\u0000\u0000\u001e\u00db\u0001\u0000\u0000\u0000"+
		" \u00e9\u0001\u0000\u0000\u0000\"\u00f7\u0001\u0000\u0000\u0000$\u010d"+
		"\u0001\u0000\u0000\u0000&\u010f\u0001\u0000\u0000\u0000(\u011d\u0001\u0000"+
		"\u0000\u0000*\u0132\u0001\u0000\u0000\u0000,\u0134\u0001\u0000\u0000\u0000"+
		".\u0188\u0001\u0000\u0000\u000001\u0003\u0002\u0001\u000012\u0005\u0000"+
		"\u0000\u000123\u0006\u0000\uffff\uffff\u00003\u0001\u0001\u0000\u0000"+
		"\u000046\u0003\u0004\u0002\u000054\u0001\u0000\u0000\u000067\u0001\u0000"+
		"\u0000\u000075\u0001\u0000\u0000\u000078\u0001\u0000\u0000\u000089\u0001"+
		"\u0000\u0000\u00009:\u0006\u0001\uffff\uffff\u0000:\u0003\u0001\u0000"+
		"\u0000\u0000;<\u0003\u0006\u0003\u0000<=\u0006\u0002\uffff\uffff\u0000"+
		"=E\u0001\u0000\u0000\u0000>?\u0003\b\u0004\u0000?@\u0006\u0002\uffff\uffff"+
		"\u0000@E\u0001\u0000\u0000\u0000AB\u0003\n\u0005\u0000BC\u0006\u0002\uffff"+
		"\uffff\u0000CE\u0001\u0000\u0000\u0000D;\u0001\u0000\u0000\u0000D>\u0001"+
		"\u0000\u0000\u0000DA\u0001\u0000\u0000\u0000E\u0005\u0001\u0000\u0000"+
		"\u0000FG\u0005.\u0000\u0000GH\u00052\u0000\u0000HI\u0005\u0007\u0000\u0000"+
		"IJ\u0003\u0016\u000b\u0000JK\u0006\u0003\uffff\uffff\u0000K\u0007\u0001"+
		"\u0000\u0000\u0000LM\u0005/\u0000\u0000MN\u00052\u0000\u0000NO\u0005\u0006"+
		"\u0000\u0000OP\u0003\u0016\u000b\u0000PQ\u0006\u0004\uffff\uffff\u0000"+
		"Q\t\u0001\u0000\u0000\u0000RS\u0005\"\u0000\u0000ST\u00052\u0000\u0000"+
		"TV\u0005\u0013\u0000\u0000UW\u0003\f\u0006\u0000VU\u0001\u0000\u0000\u0000"+
		"VW\u0001\u0000\u0000\u0000WX\u0001\u0000\u0000\u0000XY\u0005\u0014\u0000"+
		"\u0000YZ\u0005\u0006\u0000\u0000Z[\u0003\u0016\u000b\u0000[\\\u0005\u0007"+
		"\u0000\u0000\\]\u0003\u0018\f\u0000]^\u0006\u0005\uffff\uffff\u0000^k"+
		"\u0001\u0000\u0000\u0000_`\u0005\"\u0000\u0000`a\u00052\u0000\u0000ac"+
		"\u0005\u0013\u0000\u0000bd\u0003\f\u0006\u0000cb\u0001\u0000\u0000\u0000"+
		"cd\u0001\u0000\u0000\u0000de\u0001\u0000\u0000\u0000ef\u0005\u0014\u0000"+
		"\u0000fg\u0005\u0006\u0000\u0000gh\u0003\u0016\u000b\u0000hi\u0006\u0005"+
		"\uffff\uffff\u0000ik\u0001\u0000\u0000\u0000jR\u0001\u0000\u0000\u0000"+
		"j_\u0001\u0000\u0000\u0000k\u000b\u0001\u0000\u0000\u0000lq\u0003\u000e"+
		"\u0007\u0000mn\u0005\u0005\u0000\u0000np\u0003\u000e\u0007\u0000om\u0001"+
		"\u0000\u0000\u0000ps\u0001\u0000\u0000\u0000qo\u0001\u0000\u0000\u0000"+
		"qr\u0001\u0000\u0000\u0000rt\u0001\u0000\u0000\u0000sq\u0001\u0000\u0000"+
		"\u0000tu\u0006\u0006\uffff\uffff\u0000u\r\u0001\u0000\u0000\u0000vw\u0005"+
		"2\u0000\u0000wx\u0005\u0006\u0000\u0000xy\u0003\u0016\u000b\u0000yz\u0006"+
		"\u0007\uffff\uffff\u0000z\u000f\u0001\u0000\u0000\u0000{\u0080\u0003\u0012"+
		"\t\u0000|}\u0005\u0005\u0000\u0000}\u007f\u0003\u0012\t\u0000~|\u0001"+
		"\u0000\u0000\u0000\u007f\u0082\u0001\u0000\u0000\u0000\u0080~\u0001\u0000"+
		"\u0000\u0000\u0080\u0081\u0001\u0000\u0000\u0000\u0081\u0083\u0001\u0000"+
		"\u0000\u0000\u0082\u0080\u0001\u0000\u0000\u0000\u0083\u0084\u0006\b\uffff"+
		"\uffff\u0000\u0084\u0011\u0001\u0000\u0000\u0000\u0085\u0086\u00052\u0000"+
		"\u0000\u0086\u0087\u0005\u0006\u0000\u0000\u0087\u0088\u0003\u0016\u000b"+
		"\u0000\u0088\u0089\u0006\t\uffff\uffff\u0000\u0089\u0013\u0001\u0000\u0000"+
		"\u0000\u008a\u008f\u0003\u0016\u000b\u0000\u008b\u008c\u0005\u0005\u0000"+
		"\u0000\u008c\u008e\u0003\u0016\u000b\u0000\u008d\u008b\u0001\u0000\u0000"+
		"\u0000\u008e\u0091\u0001\u0000\u0000\u0000\u008f\u008d\u0001\u0000\u0000"+
		"\u0000\u008f\u0090\u0001\u0000\u0000\u0000\u0090\u0093\u0001\u0000\u0000"+
		"\u0000\u0091\u008f\u0001\u0000\u0000\u0000\u0092\u008a\u0001\u0000\u0000"+
		"\u0000\u0092\u0093\u0001\u0000\u0000\u0000\u0093\u0094\u0001\u0000\u0000"+
		"\u0000\u0094\u0095\u0006\n\uffff\uffff\u0000\u0095\u0015\u0001\u0000\u0000"+
		"\u0000\u0096\u0097\u0005%\u0000\u0000\u0097\u00c3\u0006\u000b\uffff\uffff"+
		"\u0000\u0098\u0099\u0005\u001e\u0000\u0000\u0099\u00c3\u0006\u000b\uffff"+
		"\uffff\u0000\u009a\u009b\u0005\u001c\u0000\u0000\u009b\u00c3\u0006\u000b"+
		"\uffff\uffff\u0000\u009c\u009d\u00050\u0000\u0000\u009d\u00c3\u0006\u000b"+
		"\uffff\uffff\u0000\u009e\u009f\u0005\u0017\u0000\u0000\u009f\u00a0\u0005"+
		"\u0001\u0000\u0000\u00a0\u00a1\u0005\u0018\u0000\u0000\u00a1\u00a2\u0003"+
		"\u0016\u000b\u0000\u00a2\u00a3\u0006\u000b\uffff\uffff\u0000\u00a3\u00c3"+
		"\u0001\u0000\u0000\u0000\u00a4\u00a5\u0005\u0019\u0000\u0000\u00a5\u00a6"+
		"\u0003\u0016\u000b\u0000\u00a6\u00a7\u0006\u000b\uffff\uffff\u0000\u00a7"+
		"\u00c3\u0001\u0000\u0000\u0000\u00a8\u00a9\u0005\u0013\u0000\u0000\u00a9"+
		"\u00aa\u0003\u0010\b\u0000\u00aa\u00ab\u0005\u0014\u0000\u0000\u00ab\u00ac"+
		"\u0006\u000b\uffff\uffff\u0000\u00ac\u00c3\u0001\u0000\u0000\u0000\u00ad"+
		"\u00af\u0005\u0015\u0000\u0000\u00ae\u00b0\u0003\u0010\b\u0000\u00af\u00ae"+
		"\u0001\u0000\u0000\u0000\u00af\u00b0\u0001\u0000\u0000\u0000\u00b0\u00b1"+
		"\u0001\u0000\u0000\u0000\u00b1\u00b2\u0005\u0016\u0000\u0000\u00b2\u00c3"+
		"\u0006\u000b\uffff\uffff\u0000\u00b3\u00b4\u0005\u0013\u0000\u0000\u00b4"+
		"\u00b5\u0005\u0006\u0000\u0000\u00b5\u00b6\u0003\u0014\n\u0000\u00b6\u00b7"+
		"\u0005\u0006\u0000\u0000\u00b7\u00b8\u0003\u0016\u000b\u0000\u00b8\u00b9"+
		"\u0005\u0014\u0000\u0000\u00b9\u00ba\u0006\u000b\uffff\uffff\u0000\u00ba"+
		"\u00c3\u0001\u0000\u0000\u0000\u00bb\u00bc\u0005\u0013\u0000\u0000\u00bc"+
		"\u00bd\u0003\u0016\u000b\u0000\u00bd\u00be\u0005\u0014\u0000\u0000\u00be"+
		"\u00bf\u0006\u000b\uffff\uffff\u0000\u00bf\u00c3\u0001\u0000\u0000\u0000"+
		"\u00c0\u00c1\u00052\u0000\u0000\u00c1\u00c3\u0006\u000b\uffff\uffff\u0000"+
		"\u00c2\u0096\u0001\u0000\u0000\u0000\u00c2\u0098\u0001\u0000\u0000\u0000"+
		"\u00c2\u009a\u0001\u0000\u0000\u0000\u00c2\u009c\u0001\u0000\u0000\u0000"+
		"\u00c2\u009e\u0001\u0000\u0000\u0000\u00c2\u00a4\u0001\u0000\u0000\u0000"+
		"\u00c2\u00a8\u0001\u0000\u0000\u0000\u00c2\u00ad\u0001\u0000\u0000\u0000"+
		"\u00c2\u00b3\u0001\u0000\u0000\u0000\u00c2\u00bb\u0001\u0000\u0000\u0000"+
		"\u00c2\u00c0\u0001\u0000\u0000\u0000\u00c3\u0017\u0001\u0000\u0000\u0000"+
		"\u00c4\u00c9\u0003\u001a\r\u0000\u00c5\u00c6\u0005\u0005\u0000\u0000\u00c6"+
		"\u00c8\u0003\u001a\r\u0000\u00c7\u00c5\u0001\u0000\u0000\u0000\u00c8\u00cb"+
		"\u0001\u0000\u0000\u0000\u00c9\u00c7\u0001\u0000\u0000\u0000\u00c9\u00ca"+
		"\u0001\u0000\u0000\u0000\u00ca\u00cc\u0001\u0000\u0000\u0000\u00cb\u00c9"+
		"\u0001\u0000\u0000\u0000\u00cc\u00cd\u0006\f\uffff\uffff\u0000\u00cd\u0019"+
		"\u0001\u0000\u0000\u0000\u00ce\u00cf\u0003\u001c\u000e\u0000\u00cf\u00d0"+
		"\u0006\r\uffff\uffff\u0000\u00d0\u001b\u0001\u0000\u0000\u0000\u00d1\u00d2"+
		"\u0003\u001e\u000f\u0000\u00d2\u00d3\u0005\u0007\u0000\u0000\u00d3\u00d4"+
		"\u0003\u001e\u000f\u0000\u00d4\u00d5\u0006\u000e\uffff\uffff\u0000\u00d5"+
		"\u00da\u0001\u0000\u0000\u0000\u00d6\u00d7\u0003\u001e\u000f\u0000\u00d7"+
		"\u00d8\u0006\u000e\uffff\uffff\u0000\u00d8\u00da\u0001\u0000\u0000\u0000"+
		"\u00d9\u00d1\u0001\u0000\u0000\u0000\u00d9\u00d6\u0001\u0000\u0000\u0000"+
		"\u00da\u001d\u0001\u0000\u0000\u0000\u00db\u00dc\u0006\u000f\uffff\uffff"+
		"\u0000\u00dc\u00dd\u0003 \u0010\u0000\u00dd\u00de\u0006\u000f\uffff\uffff"+
		"\u0000\u00de\u00e6\u0001\u0000\u0000\u0000\u00df\u00e0\n\u0002\u0000\u0000"+
		"\u00e0\u00e1\u0005\u001b\u0000\u0000\u00e1\u00e2\u0003\u0016\u000b\u0000"+
		"\u00e2\u00e3\u0006\u000f\uffff\uffff\u0000\u00e3\u00e5\u0001\u0000\u0000"+
		"\u0000\u00e4\u00df\u0001\u0000\u0000\u0000\u00e5\u00e8\u0001\u0000\u0000"+
		"\u0000\u00e6\u00e4\u0001\u0000\u0000\u0000\u00e6\u00e7\u0001\u0000\u0000"+
		"\u0000\u00e7\u001f\u0001\u0000\u0000\u0000\u00e8\u00e6\u0001\u0000\u0000"+
		"\u0000\u00e9\u00ea\u0006\u0010\uffff\uffff\u0000\u00ea\u00eb\u0003\"\u0011"+
		"\u0000\u00eb\u00ec\u0006\u0010\uffff\uffff\u0000\u00ec\u00f4\u0001\u0000"+
		"\u0000\u0000\u00ed\u00ee\n\u0002\u0000\u0000\u00ee\u00ef\u0005*\u0000"+
		"\u0000\u00ef\u00f0\u0003\"\u0011\u0000\u00f0\u00f1\u0006\u0010\uffff\uffff"+
		"\u0000\u00f1\u00f3\u0001\u0000\u0000\u0000\u00f2\u00ed\u0001\u0000\u0000"+
		"\u0000\u00f3\u00f6\u0001\u0000\u0000\u0000\u00f4\u00f2\u0001\u0000\u0000"+
		"\u0000\u00f4\u00f5\u0001\u0000\u0000\u0000\u00f5!\u0001\u0000\u0000\u0000"+
		"\u00f6\u00f4\u0001\u0000\u0000\u0000\u00f7\u00f8\u0006\u0011\uffff\uffff"+
		"\u0000\u00f8\u00f9\u0003$\u0012\u0000\u00f9\u00fa\u0006\u0011\uffff\uffff"+
		"\u0000\u00fa\u0102\u0001\u0000\u0000\u0000\u00fb\u00fc\n\u0002\u0000\u0000"+
		"\u00fc\u00fd\u0005\u001a\u0000\u0000\u00fd\u00fe\u0003$\u0012\u0000\u00fe"+
		"\u00ff\u0006\u0011\uffff\uffff\u0000\u00ff\u0101\u0001\u0000\u0000\u0000"+
		"\u0100\u00fb\u0001\u0000\u0000\u0000\u0101\u0104\u0001\u0000\u0000\u0000"+
		"\u0102\u0100\u0001\u0000\u0000\u0000\u0102\u0103\u0001\u0000\u0000\u0000"+
		"\u0103#\u0001\u0000\u0000\u0000\u0104\u0102\u0001\u0000\u0000\u0000\u0105"+
		"\u0106\u0003&\u0013\u0000\u0106\u0107\u0007\u0000\u0000\u0000\u0107\u0108"+
		"\u0003&\u0013\u0000\u0108\u0109\u0006\u0012\uffff\uffff\u0000\u0109\u010e"+
		"\u0001\u0000\u0000\u0000\u010a\u010b\u0003&\u0013\u0000\u010b\u010c\u0006"+
		"\u0012\uffff\uffff\u0000\u010c\u010e\u0001\u0000\u0000\u0000\u010d\u0105"+
		"\u0001\u0000\u0000\u0000\u010d\u010a\u0001\u0000\u0000\u0000\u010e%\u0001"+
		"\u0000\u0000\u0000\u010f\u0110\u0006\u0013\uffff\uffff\u0000\u0110\u0111"+
		"\u0003(\u0014\u0000\u0111\u0112\u0006\u0013\uffff\uffff\u0000\u0112\u011a"+
		"\u0001\u0000\u0000\u0000\u0113\u0114\n\u0002\u0000\u0000\u0114\u0115\u0007"+
		"\u0001\u0000\u0000\u0115\u0116\u0003(\u0014\u0000\u0116\u0117\u0006\u0013"+
		"\uffff\uffff\u0000\u0117\u0119\u0001\u0000\u0000\u0000\u0118\u0113\u0001"+
		"\u0000\u0000\u0000\u0119\u011c\u0001\u0000\u0000\u0000\u011a\u0118\u0001"+
		"\u0000\u0000\u0000\u011a\u011b\u0001\u0000\u0000\u0000\u011b\'\u0001\u0000"+
		"\u0000\u0000\u011c\u011a\u0001\u0000\u0000\u0000\u011d\u011e\u0006\u0014"+
		"\uffff\uffff\u0000\u011e\u011f\u0003*\u0015\u0000\u011f\u0120\u0006\u0014"+
		"\uffff\uffff\u0000\u0120\u0128\u0001\u0000\u0000\u0000\u0121\u0122\n\u0002"+
		"\u0000\u0000\u0122\u0123\u0007\u0002\u0000\u0000\u0123\u0124\u0003*\u0015"+
		"\u0000\u0124\u0125\u0006\u0014\uffff\uffff\u0000\u0125\u0127\u0001\u0000"+
		"\u0000\u0000\u0126\u0121\u0001\u0000\u0000\u0000\u0127\u012a\u0001\u0000"+
		"\u0000\u0000\u0128\u0126\u0001\u0000\u0000\u0000\u0128\u0129\u0001\u0000"+
		"\u0000\u0000\u0129)\u0001\u0000\u0000\u0000\u012a\u0128\u0001\u0000\u0000"+
		"\u0000\u012b\u012c\u0007\u0003\u0000\u0000\u012c\u012d\u0003*\u0015\u0000"+
		"\u012d\u012e\u0006\u0015\uffff\uffff\u0000\u012e\u0133\u0001\u0000\u0000"+
		"\u0000\u012f\u0130\u0003,\u0016\u0000\u0130\u0131\u0006\u0015\uffff\uffff"+
		"\u0000\u0131\u0133\u0001\u0000\u0000\u0000\u0132\u012b\u0001\u0000\u0000"+
		"\u0000\u0132\u012f\u0001\u0000\u0000\u0000\u0133+\u0001\u0000\u0000\u0000"+
		"\u0134\u0135\u0006\u0016\uffff\uffff\u0000\u0135\u0136\u0003.\u0017\u0000"+
		"\u0136\u0137\u0006\u0016\uffff\uffff\u0000\u0137\u014e\u0001\u0000\u0000"+
		"\u0000\u0138\u0139\n\u0005\u0000\u0000\u0139\u013a\u0005\u0019\u0000\u0000"+
		"\u013a\u014d\u0006\u0016\uffff\uffff\u0000\u013b\u013c\n\u0004\u0000\u0000"+
		"\u013c\u013e\u0005\u0013\u0000\u0000\u013d\u013f\u0003\u0018\f\u0000\u013e"+
		"\u013d\u0001\u0000\u0000\u0000\u013e\u013f\u0001\u0000\u0000\u0000\u013f"+
		"\u0140\u0001\u0000\u0000\u0000\u0140\u0141\u0005\u0014\u0000\u0000\u0141"+
		"\u014d\u0006\u0016\uffff\uffff\u0000\u0142\u0143\n\u0003\u0000\u0000\u0143"+
		"\u0144\u0005\u0017\u0000\u0000\u0144\u0145\u0003\u001a\r\u0000\u0145\u0146"+
		"\u0005\u0018\u0000\u0000\u0146\u0147\u0006\u0016\uffff\uffff\u0000\u0147"+
		"\u014d\u0001\u0000\u0000\u0000\u0148\u0149\n\u0002\u0000\u0000\u0149\u014a"+
		"\u0005\u0004\u0000\u0000\u014a\u014b\u00052\u0000\u0000\u014b\u014d\u0006"+
		"\u0016\uffff\uffff\u0000\u014c\u0138\u0001\u0000\u0000\u0000\u014c\u013b"+
		"\u0001\u0000\u0000\u0000\u014c\u0142\u0001\u0000\u0000\u0000\u014c\u0148"+
		"\u0001\u0000\u0000\u0000\u014d\u0150\u0001\u0000\u0000\u0000\u014e\u014c"+
		"\u0001\u0000\u0000\u0000\u014e\u014f\u0001\u0000\u0000\u0000\u014f-\u0001"+
		"\u0000\u0000\u0000\u0150\u014e\u0001\u0000\u0000\u0000\u0151\u0152\u0005"+
		"\u0001\u0000\u0000\u0152\u0189\u0006\u0017\uffff\uffff\u0000\u0153\u0154"+
		"\u0005\u0002\u0000\u0000\u0154\u0189\u0006\u0017\uffff\uffff\u0000\u0155"+
		"\u0156\u0005\u0003\u0000\u0000\u0156\u0189\u0006\u0017\uffff\uffff\u0000"+
		"\u0157\u0158\u0005-\u0000\u0000\u0158\u0189\u0006\u0017\uffff\uffff\u0000"+
		"\u0159\u015a\u0005!\u0000\u0000\u015a\u0189\u0006\u0017\uffff\uffff\u0000"+
		"\u015b\u015c\u0005(\u0000\u0000\u015c\u0189\u0006\u0017\uffff\uffff\u0000"+
		"\u015d\u015e\u0005\'\u0000\u0000\u015e\u0189\u0006\u0017\uffff\uffff\u0000"+
		"\u015f\u0160\u0005\u0013\u0000\u0000\u0160\u0161\u0003\u0018\f\u0000\u0161"+
		"\u0162\u0005\u0014\u0000\u0000\u0162\u0163\u0006\u0017\uffff\uffff\u0000"+
		"\u0163\u0189\u0001\u0000\u0000\u0000\u0164\u0165\u0005+\u0000\u0000\u0165"+
		"\u0166\u0003\u0016\u000b\u0000\u0166\u0167\u0006\u0017\uffff\uffff\u0000"+
		"\u0167\u0189\u0001\u0000\u0000\u0000\u0168\u0169\u0005#\u0000\u0000\u0169"+
		"\u016a\u0003\u001a\r\u0000\u016a\u016b\u0005,\u0000\u0000\u016b\u016c"+
		"\u0003\u0018\f\u0000\u016c\u016d\u0005 \u0000\u0000\u016d\u016e\u0006"+
		"\u0017\uffff\uffff\u0000\u016e\u0189\u0001\u0000\u0000\u0000\u016f\u0170"+
		"\u0005#\u0000\u0000\u0170\u0171\u0003\u001a\r\u0000\u0171\u0172\u0005"+
		",\u0000\u0000\u0172\u0173\u0003\u0018\f\u0000\u0173\u0174\u0005\u001f"+
		"\u0000\u0000\u0174\u0175\u0003\u0018\f\u0000\u0175\u0176\u0005 \u0000"+
		"\u0000\u0176\u0177\u0006\u0017\uffff\uffff\u0000\u0177\u0189\u0001\u0000"+
		"\u0000\u0000\u0178\u0179\u00051\u0000\u0000\u0179\u017a\u0003\u001a\r"+
		"\u0000\u017a\u017b\u0005\u001d\u0000\u0000\u017b\u017c\u0003\u0018\f\u0000"+
		"\u017c\u017d\u0005 \u0000\u0000\u017d\u017e\u0006\u0017\uffff\uffff\u0000"+
		"\u017e\u0189\u0001\u0000\u0000\u0000\u017f\u0180\u0005&\u0000\u0000\u0180"+
		"\u0181\u0003\u0002\u0001\u0000\u0181\u0182\u0005$\u0000\u0000\u0182\u0183"+
		"\u0003\u0018\f\u0000\u0183\u0184\u0005 \u0000\u0000\u0184\u0185\u0006"+
		"\u0017\uffff\uffff\u0000\u0185\u0189\u0001\u0000\u0000\u0000\u0186\u0187"+
		"\u00052\u0000\u0000\u0187\u0189\u0006\u0017\uffff\uffff\u0000\u0188\u0151"+
		"\u0001\u0000\u0000\u0000\u0188\u0153\u0001\u0000\u0000\u0000\u0188\u0155"+
		"\u0001\u0000\u0000\u0000\u0188\u0157\u0001\u0000\u0000\u0000\u0188\u0159"+
		"\u0001\u0000\u0000\u0000\u0188\u015b\u0001\u0000\u0000\u0000\u0188\u015d"+
		"\u0001\u0000\u0000\u0000\u0188\u015f\u0001\u0000\u0000\u0000\u0188\u0164"+
		"\u0001\u0000\u0000\u0000\u0188\u0168\u0001\u0000\u0000\u0000\u0188\u016f"+
		"\u0001\u0000\u0000\u0000\u0188\u0178\u0001\u0000\u0000\u0000\u0188\u017f"+
		"\u0001\u0000\u0000\u0000\u0188\u0186\u0001\u0000\u0000\u0000\u0189/\u0001"+
		"\u0000\u0000\u0000\u00187DVcjq\u0080\u008f\u0092\u00af\u00c2\u00c9\u00d9"+
		"\u00e6\u00f4\u0102\u010d\u011a\u0128\u0132\u013e\u014c\u014e\u0188";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}