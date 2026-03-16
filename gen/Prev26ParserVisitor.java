// Generated from /home/aljaz/Desktop/FRI_PORTAL/SEM2/P/prev26/src/prev26lang/phase/synan/Prev26Parser.g4 by ANTLR 4.13.2

    package prev26lang.phase.synan;
    import java.util.*;
    import prev26lang.common.report.*;
    import prev26lang.phase.lexan.*;
    import prev26lang.phase.abstr.*;
    import prev26lang.phase.abstr.AST.*;


import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link Prev26Parser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface Prev26ParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#source}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSource(Prev26Parser.SourceContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#prog}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProg(Prev26Parser.ProgContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#defn}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDefn(Prev26Parser.DefnContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#typeDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeDecl(Prev26Parser.TypeDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#varDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVarDecl(Prev26Parser.VarDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#funcDecl}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncDecl(Prev26Parser.FuncDeclContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#paramList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParamList(Prev26Parser.ParamListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#param}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParam(Prev26Parser.ParamContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#compList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompList(Prev26Parser.CompListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#comp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComp(Prev26Parser.CompContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#typeList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTypeList(Prev26Parser.TypeListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#type}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitType(Prev26Parser.TypeContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#exprList}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExprList(Prev26Parser.ExprListContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExpr(Prev26Parser.ExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#assignExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(Prev26Parser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#castExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCastExpr(Prev26Parser.CastExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#boolORExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolORExpr(Prev26Parser.BoolORExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#boolANDExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBoolANDExpr(Prev26Parser.BoolANDExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#compExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCompExpr(Prev26Parser.CompExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#addExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAddExpr(Prev26Parser.AddExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#mulExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMulExpr(Prev26Parser.MulExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#prefixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrefixExpr(Prev26Parser.PrefixExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#postfixExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPostfixExpr(Prev26Parser.PostfixExprContext ctx);
	/**
	 * Visit a parse tree produced by {@link Prev26Parser#basicExpr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBasicExpr(Prev26Parser.BasicExprContext ctx);
}