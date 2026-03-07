// Generated from /home/aljaz/Desktop/FRI_PORTAL/SEM2/P/prev26/src/prev26lang/phase/synan/Prev26Parser.g4 by ANTLR 4.13.2


    package prev26lang.phase.synan;
    
    import java.util.*;
    import prev26lang.common.report.*;
    import prev26lang.phase.lexan.*;
    import prev26lang.phase.abstr.*;


import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link Prev26Parser}.
 */
public interface Prev26ParserListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#source}.
	 * @param ctx the parse tree
	 */
	void enterSource(Prev26Parser.SourceContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#source}.
	 * @param ctx the parse tree
	 */
	void exitSource(Prev26Parser.SourceContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(Prev26Parser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(Prev26Parser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#defnList}.
	 * @param ctx the parse tree
	 */
	void enterDefnList(Prev26Parser.DefnListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#defnList}.
	 * @param ctx the parse tree
	 */
	void exitDefnList(Prev26Parser.DefnListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#defn}.
	 * @param ctx the parse tree
	 */
	void enterDefn(Prev26Parser.DefnContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#defn}.
	 * @param ctx the parse tree
	 */
	void exitDefn(Prev26Parser.DefnContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void enterTypeDecl(Prev26Parser.TypeDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#typeDecl}.
	 * @param ctx the parse tree
	 */
	void exitTypeDecl(Prev26Parser.TypeDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#varDecl}.
	 * @param ctx the parse tree
	 */
	void enterVarDecl(Prev26Parser.VarDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#varDecl}.
	 * @param ctx the parse tree
	 */
	void exitVarDecl(Prev26Parser.VarDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void enterFuncDecl(Prev26Parser.FuncDeclContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#funcDecl}.
	 * @param ctx the parse tree
	 */
	void exitFuncDecl(Prev26Parser.FuncDeclContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#paramList}.
	 * @param ctx the parse tree
	 */
	void enterParamList(Prev26Parser.ParamListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#paramList}.
	 * @param ctx the parse tree
	 */
	void exitParamList(Prev26Parser.ParamListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#param}.
	 * @param ctx the parse tree
	 */
	void enterParam(Prev26Parser.ParamContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#param}.
	 * @param ctx the parse tree
	 */
	void exitParam(Prev26Parser.ParamContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#typeList}.
	 * @param ctx the parse tree
	 */
	void enterTypeList(Prev26Parser.TypeListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#typeList}.
	 * @param ctx the parse tree
	 */
	void exitTypeList(Prev26Parser.TypeListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#type}.
	 * @param ctx the parse tree
	 */
	void enterType(Prev26Parser.TypeContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#type}.
	 * @param ctx the parse tree
	 */
	void exitType(Prev26Parser.TypeContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#exprList}.
	 * @param ctx the parse tree
	 */
	void enterExprList(Prev26Parser.ExprListContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#exprList}.
	 * @param ctx the parse tree
	 */
	void exitExprList(Prev26Parser.ExprListContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#exprDEL}.
	 * @param ctx the parse tree
	 */
	void enterExprDEL(Prev26Parser.ExprDELContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#exprDEL}.
	 * @param ctx the parse tree
	 */
	void exitExprDEL(Prev26Parser.ExprDELContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(Prev26Parser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(Prev26Parser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#assignExpr}.
	 * @param ctx the parse tree
	 */
	void enterAssignExpr(Prev26Parser.AssignExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#assignExpr}.
	 * @param ctx the parse tree
	 */
	void exitAssignExpr(Prev26Parser.AssignExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#castExpr}.
	 * @param ctx the parse tree
	 */
	void enterCastExpr(Prev26Parser.CastExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#castExpr}.
	 * @param ctx the parse tree
	 */
	void exitCastExpr(Prev26Parser.CastExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void enterBoolExpr(Prev26Parser.BoolExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#boolExpr}.
	 * @param ctx the parse tree
	 */
	void exitBoolExpr(Prev26Parser.BoolExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#compExpr}.
	 * @param ctx the parse tree
	 */
	void enterCompExpr(Prev26Parser.CompExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#compExpr}.
	 * @param ctx the parse tree
	 */
	void exitCompExpr(Prev26Parser.CompExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#addExpr}.
	 * @param ctx the parse tree
	 */
	void enterAddExpr(Prev26Parser.AddExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#addExpr}.
	 * @param ctx the parse tree
	 */
	void exitAddExpr(Prev26Parser.AddExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void enterMulExpr(Prev26Parser.MulExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#mulExpr}.
	 * @param ctx the parse tree
	 */
	void exitMulExpr(Prev26Parser.MulExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPrefixExpr(Prev26Parser.PrefixExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#prefixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPrefixExpr(Prev26Parser.PrefixExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link Prev26Parser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void enterPostfixExpr(Prev26Parser.PostfixExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link Prev26Parser#postfixExpr}.
	 * @param ctx the parse tree
	 */
	void exitPostfixExpr(Prev26Parser.PostfixExprContext ctx);
}