package prev26lang.phase.seman;

import prev26lang.phase.abstr.*;

// handles AST.Expr nodes → checks types → stores in ofTypeAttr, isConstAttr, isAddrAttr
// also resolves component names (CompExpr)
// also checks program has fun main():int
// runs after type constructor
public class TypeChecker implements AST.FullVisitor<Object, Object> {
}
