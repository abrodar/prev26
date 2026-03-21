package prev26lang.phase.seman;

import java.util.*;

import prev26lang.Compiler;
import prev26lang.common.report.*;
import prev26lang.phase.abstr.*;

/**
 * Name resolver.
 * 
 * The name resolver connects each node of an abstract syntax tree where a name
 * is used with the node where it is defined. The only exceptions are struct and
 * union component names which are connected with their definitions by the type
 * resolver. The results of the name resolver are stored in
 * {@link SemAn#defAtAttr}.
 * 
 * @author bostjan.slivnik@fri.uni-lj.si
 */
public class NameResolver implements AST.FullVisitor<Object, Object> {

	/** Constructs a new name resolver. */
	public NameResolver() {
	}

	/** The symbol table. */
	private SymbTable symbTable = new SymbTable();

    // Helper functions (by AB)
    private void insDefn(String name, AST.Defn defn) {
        try {
            symbTable.ins(name, defn);
        } catch (SymbTable.CannotInsNameException e) {
            throw new Report.Error(defn, "Name '" + name + "' already defined in this scope.");
        }
    }

    private AST.Defn fndDefn(String name, Locatable loc) {
        try {
            return symbTable.fnd(name);
        } catch (SymbTable.CannotFndNameException e) {
            throw new Report.Error(loc, "Name '" + name + "' is not defined.");
        }
    }
	// ===== SYMBOL TABLE =====

	public class SymbTable {
		/**
		 * A symbol table record denoting a definition of a name within a certain scope.
		 */
		private class ScopedDefn {

			/** The depth of the scope the definition belongs to. */
			public final int depth;

			/** The definition. */
			public final AST.Defn defn;

			/**
			 * Constructs a new record denoting a definition of a name within a certain
			 * scope.
			 * 
			 * @param depth The depth of the scope the definition belongs to.
			 * @param defn  The definition.
			 */
			public ScopedDefn(int depth, AST.Defn defn) {
				this.depth = depth;
				this.defn = defn;
			}
		}

		/**
		 * A mapping of names into lists of records denoting definitions at different
		 * scopes. At each moment during the lifetime of a symbol table, the definition
		 * list corresponding to a particular name contains all definitions that live
		 * within currently active scopes: the definition at the innermost scope is the
		 * first in the list and is visible, the other definitions are hidden.
		 */
		private final HashMap<String, LinkedList<ScopedDefn>> allDefnsOfAllNames;

		/**
		 * The list of scopes. Each scope is represented by a list of names defined
		 * within it.
		 */
		private final LinkedList<LinkedList<String>> scopes;

		/** The depth of the currently active scope. */
		private int currDepth;

		/** Whether the symbol table can no longer be modified or not. */
		private boolean lock;

		/**
		 * Constructs a new symbol table.
		 */
		public SymbTable() {
			allDefnsOfAllNames = new HashMap<String, LinkedList<ScopedDefn>>();
			scopes = new LinkedList<LinkedList<String>>();
			currDepth = 0;
			lock = false;
			newScope();
		}

		/**
		 * Returns the depth of the currently active scope.
		 * 
		 * @return The depth of the currently active scope.
		 */
		public int currDepth() {
			return currDepth;
		}

		/**
		 * Inserts a new definition of a name within the currently active scope or
		 * throws an exception if this name has already been defined within this scope.
		 * Once the symbol table is locked, any attempt to insert further definitions
		 * results in an internal error.
		 * 
		 * @param name The name.
		 * @param defn The definition.
		 * @throws CannotInsNameException Thrown if this name has already been defined
		 *                                within the currently active scope.
		 */
		public void ins(String name, AST.Defn defn) throws CannotInsNameException {
			if (lock)
				throw new Report.InternalError();

			LinkedList<ScopedDefn> allDefnsOfName = allDefnsOfAllNames.get(name);
			if (allDefnsOfName == null) {
				allDefnsOfName = new LinkedList<ScopedDefn>();
				allDefnsOfAllNames.put(name, allDefnsOfName);
			}

			if (!allDefnsOfName.isEmpty()) {
				ScopedDefn defnOfName = allDefnsOfName.getFirst();
				if (defnOfName.depth == currDepth)
					throw new CannotInsNameException();
			}

			allDefnsOfName.addFirst(new ScopedDefn(currDepth, defn)); // doda curr ime in doseg v horizontalen ll, kjer so vse def. z istim imenom na razl. dosegih
			scopes.getFirst().addFirst(name); //v ze ustvarjen ll dosega dodaj to ime (za hitrejsi delete kasneje)
		}

		/**
		 * Returns the currently visible definition of the specified name. If no
		 * definition of the name exists within these scopes, an exception is thrown.
		 * 
		 * @param name The name.
		 * @return The definition.
		 * @throws CannotFndNameException Thrown if the name is not defined within the
		 *                                currently active scope or any scope enclosing
		 *                                it.
		 */
		public AST.Defn fnd(String name) throws CannotFndNameException {
			LinkedList<ScopedDefn> allDefnsOfName = allDefnsOfAllNames.get(name);
			if (allDefnsOfName == null)
				throw new CannotFndNameException();

			if (allDefnsOfName.isEmpty())
				throw new CannotFndNameException();

			return allDefnsOfName.getFirst().defn;
		}

		/** Used for selecting the range of scopes. */
		public enum XScopeSelector {
			/** All live scopes. */
			ALL,
			/** Currently active scope. */
			ACT,
		}

		/**
		 * Constructs a new scope within the currently active scope. The newly
		 * constructed scope becomes the currently active scope.
		 */
		public void newScope() {
			if (lock)
				throw new Report.InternalError();

			currDepth++;
			scopes.addFirst(new LinkedList<String>());
		}

		/**
		 * Destroys the currently active scope by removing all definitions belonging to
		 * it from the symbol table. Makes the enclosing scope the currently active
		 * scope.
		 */
		public void oldScope() {
			if (lock)
				throw new Report.InternalError();

			if (currDepth == 0)
				throw new Report.InternalError();

			for (String name : scopes.getFirst()) {
				allDefnsOfAllNames.get(name).removeFirst();
			}
			scopes.removeFirst();
			currDepth--;
		}

		/**
		 * Prevents further modifications of this symbol table.
		 */
		public void lock() {
			lock = true;
		}

		/**
		 * An exception thrown when the name cannot be inserted into a symbol table.
		 */
		@SuppressWarnings("serial")
		public class CannotInsNameException extends Exception {

			/**
			 * Constructs a new exception.
			 */
			private CannotInsNameException() {
			}

		}

		/**
		 * An exception thrown when the name cannot be found in the symbol table.
		 */
		@SuppressWarnings("serial")
		public class CannotFndNameException extends Exception {

			/**
			 * Constructs a new exception.
			 */
			private CannotFndNameException() {
			}

		}

	}

    // Vstopna točka (top-level)
    @Override
    public Object visit(AST.Nodes<? extends AST.Node> nodes, Object arg) {
        symbTable.newScope(); //program ustvari doseg
        // 1. prehod
        for (AST.Node node : nodes) {
            if (node instanceof AST.FullDefn defn) {
                insDefn(defn.name, defn);
            }
        }
        // 2. prehod
        for (AST.Node node : nodes)
            node.accept(this, arg);
        // konec dosega
        symbTable.oldScope();
        return null;
    }
    // Definicije
    @Override
    public Object visit(AST.TypDefn typDefn, Object arg) {
        typDefn.type.accept(this, arg);
        return null;
    }

    @Override
    public Object visit(AST.VarDefn varDefn, Object arg) {
        varDefn.type.accept(this, arg);
        return null;
    }

    // Definicija funkcije ustvari nov doseg, v katerem so parameteri in morebitno telo funkcije.
    // Ime funkcije, tipi parametrov in tip rezultata niso del novo ustvarjenega dosega.
    @Override
    public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
        // tip rezultata ni del novega obsega
        defFunDefn.type.accept(this, arg);
        // nov doseg funkcije s telesom
        symbTable.newScope();
        // 1. prehod, vstavi in obisci parametre (v 1. prehodu, ker tip v params ne more biti definiran)
        for (AST.ParDefn par: defFunDefn.pars) {
            insDefn(par.name, par);
            par.type.accept(this, arg);
        }
        // 2. prehod, obisci telo funkcije
        defFunDefn.expr.accept(this, arg);
        // konec dosega
        symbTable.oldScope();
        return null;
    }

    @Override
    public Object visit(AST.ExtFunDefn extFunDefn, Object arg) {
        // tip rezultata funkcije
        extFunDefn.type.accept(this, arg);
        for (AST.ParDefn par: extFunDefn.pars) {
            // tokrat samo pogledamo ali so te uporabe bile definirane, saj extFun nima telesa -> ni novega dosega in ins()!
            par.type.accept(this, arg);
        }
        return null;
    }

    @Override
    public Object visit(AST.ParDefn parDefn, Object arg) {
        parDefn.type.accept(this, arg);
        return null;
    }

    @Override
    public Object visit(AST.CompDefn compDefn, Object arg) {
        if ((compDefn.type != null) || (!Compiler.devMode()))
            compDefn.type.accept(this, arg);
        return null;
    }
}
