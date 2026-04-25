    package prev26lang.phase.seman;
    import prev26lang.common.report.Report;
    import prev26lang.phase.abstr.*;

    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.List;

    // handles AST.Type nodes → builds TYP.Type objects → stores in isTypeAttr
    // also handles TypDefn, VarDefn, FunDefn type signatures
    // runs before typechecker
    public class TypeConstructor implements AST.FullVisitor<Object, Object>  {
        /** Constructs a new name resolver. */
        public TypeConstructor() {
        }
        // za hranjenje funTypes za vsak unDefn
        public final HashMap<AST.FunDefn, TYP.FunType> funTypes = new HashMap<>();
        // za razresevanje uni/str komponent
        public final HashMap<TYP.StrType, AST.StrType> strTypeMap = new HashMap<>();
        public final HashMap<TYP.UniType, AST.UniType> uniTypeMap = new HashMap<>();
        // helper metode
        private void checkFiniteSize(TYP.Type type, java.util.HashSet<TYP.NameType> visited) {
            if (type instanceof TYP.NameType nt) {
                if (!visited.add(nt)) // ce je ze v hashsetu vrzemo error
                    throw new Report.InternalError(); // cycle = infinite size
                checkFiniteSize(nt.type(), visited);
            } else if (type instanceof TYP.ArrType at) {
                checkFiniteSize(at.elemType, visited);
            } else if (type instanceof TYP.StrType st) {
                for (TYP.Type c : st.compTypes)
                    checkFiniteSize(c, new java.util.HashSet<>(visited)); // vsaka komp. je neodvisna zato vsaka dobi svojo (trenutno kopijo visited)
            } else if (type instanceof TYP.UniType ut) {
                for (TYP.Type c : ut.compTypes)
                    checkFiniteSize(c, new java.util.HashSet<>(visited)); // vsaka komp. je neodvisna zato vsaka dobi svojo (trenutno kopijo visited)
            }
            // PtrType, FunType, atomics — always finite, stop here
        }
        private boolean equiv(TYP.Type a, TYP.Type b) {
            // EQU:1
            if (a == b) return true;
            // unwrap name types - EQU:2, EQU:3
            if (a instanceof TYP.NameType na) return equiv(na.type(), b);
            if (b instanceof TYP.NameType nb) return equiv(a, nb.type());
            // EQU:4 - arrays
            if (a instanceof TYP.ArrType aa && b instanceof TYP.ArrType ab)
                return aa.numElems.equals(ab.numElems) && equiv(aa.elemType, ab.elemType);
            // EQU:5 - pointers
            if (a instanceof TYP.PtrType pa && b instanceof TYP.PtrType pb)
                return equiv(pa.baseType, pb.baseType);
            // EQU:6 - structs
            if (a instanceof TYP.StrType sa && b instanceof TYP.StrType sb) {
                if (sa.compTypes.size() != sb.compTypes.size()) return false;
                for (int i = 0; i < sa.compTypes.size(); i++)
                    if (!equiv(sa.compTypes.get(i), sb.compTypes.get(i))) return false;
                return true;
            }
            // EQU:7 - unions
            if (a instanceof TYP.UniType ua && b instanceof TYP.UniType ub) {
                if (ua.compTypes.size() != ub.compTypes.size()) return false;
                for (int i = 0; i < ua.compTypes.size(); i++)
                    if (!equiv(ua.compTypes.get(i), ub.compTypes.get(i))) return false;
                return true;
            }
            // EQU:8 - functions
            if (a instanceof TYP.FunType fa && b instanceof TYP.FunType fb) {
                if (fa.parTypes.size() != fb.parTypes.size()) return false;
                for (int i = 0; i < fa.parTypes.size(); i++)
                    if (!equiv(fa.parTypes.get(i), fb.parTypes.get(i))) return false;
                return equiv(fa.resType, fb.resType);
            }
            return false;
        }
        public boolean isValidParType(TYP.Type type) {
            TYP.Type actual = type;
            if (actual instanceof TYP.NameType) {
                actual = type.actualType();
            }
            return actual instanceof TYP.IntType
                    || actual instanceof TYP.CharType
                    || actual instanceof TYP.BoolType
                    || actual instanceof TYP.PtrType
                    || actual instanceof TYP.FunType;
        }
        public boolean isValidResType(TYP.Type type) {
            TYP.Type actual = type;
            if (actual instanceof TYP.NameType) {
                actual = type.actualType();
            }
            return actual instanceof TYP.IntType
                    || actual instanceof TYP.CharType
                    || actual instanceof TYP.BoolType
                    || actual instanceof TYP.PtrType
                    || actual instanceof TYP.FunType
                    || actual instanceof TYP.VoidType;
        }
        private void visitFunSignature(AST.FunDefn funDefn, Object arg) {
            // razresi tipe parametrov in rezultata funkcije
            funDefn.type.accept(this, arg);
            TYP.Type resType = SemAn.isTypeAttr.get(funDefn.type);
            if (!isValidResType(resType)) {
                throw new Report.Error(funDefn, "Invalid result type " +resType+" for fun "+ funDefn.name);
            }
            List<TYP.Type> parTypes = new ArrayList<>();
            for (AST.ParDefn par: funDefn.pars) {
                par.accept(this, arg);
                TYP.Type parType = SemAn.isTypeAttr.get(par.type);
                if (!isValidParType(parType)) {
                    throw new Report.Error(funDefn, "Invalid par type " + parType + " for fun " + funDefn.name);
                }
                parTypes.add(parType);
                // parametri atributa; ni valid node za isconst in isaddresable
                SemAn.ofTypeAttr.put(par, parType);
            }
            TYP.FunType funType = new TYP.FunType(parTypes, resType);
            funTypes.put(funDefn, funType);
            // TYP:4 — definicija funkcije je void
            SemAn.ofTypeAttr.put(funDefn, TYP.VoidType.type);
        }
        // 5 prehodov, najprej tipi 2x, 1x vars, 2x funkcije
        @Override
        public Object visit(AST.Nodes<? extends AST.Node> nodes, Object arg) {
            // prehod 1: shells za vse typdefn
            for (AST.Node node : nodes) {
                if (node instanceof AST.TypDefn typDefn) {
                    TYP.NameType shell = new TYP.NameType(typDefn.name);
                    SemAn.isTypeAttr.put(typDefn, shell);
                }
            }
            // prehod 2: tipi
            for (AST.Node node : nodes) {
                if (node instanceof AST.TypDefn typDefn) {
                    node.accept(this, arg);
                }
            }
            // cycle check — only AFTER all shells are filled
            for (AST.Node node : nodes) {
                if (node instanceof AST.TypDefn typDefn) {
                    TYP.NameType shell = (TYP.NameType) SemAn.isTypeAttr.get(typDefn);
                    try {
                        shell.actualType();
                    } catch (Report.InternalError e) {
                        throw new Report.Error(typDefn, "Cyclic type definition: '" + typDefn.name + "'.");
                    }
                    try {
                        checkFiniteSize(shell, new java.util.HashSet<>());
                    } catch (Report.InternalError e) {
                        throw new Report.Error(typDefn, "Type '" + typDefn.name + "' has infinite size.");
                    }
                }
            }
            // pass 3 — vars
            for (AST.Node node : nodes)
                if (node instanceof AST.VarDefn)
                    node.accept(this, arg);

            // pass 4 — function signatures only (pars + return type, no body)
            for (AST.Node node : nodes)
                if (node instanceof AST.FunDefn funDefn)
                    visitFunSignature((AST.FunDefn)node, arg);

            // pass 5 — function bodies
            for (AST.Node node : nodes)
                if (node instanceof AST.DefFunDefn defFunDefn) {
                    defFunDefn.expr.accept(this, arg);
                }
            return null;
        }
        // kot na vstopni tocki 5 prehodov
        @Override
        public Object visit(AST.LetExpr letExpr, Object arg) {
            // prehod 1: shells za vse typdefn
            for (AST.FullDefn defn : letExpr.defns) {
                if (defn instanceof AST.TypDefn typDefn) {
                    TYP.NameType shell = new TYP.NameType(typDefn.name);
                    SemAn.isTypeAttr.put(typDefn, shell);
                }
            }
            // prehod 2: tipi telesa
            for (AST.FullDefn defn : letExpr.defns) {
                if (defn instanceof AST.TypDefn) {
                    defn.accept(this, arg);
                }
            }
            // cycle check — only AFTER all shells are filled
            for (AST.Node node : letExpr.defns) {
                if (node instanceof AST.TypDefn typDefn) {
                    TYP.NameType shell = (TYP.NameType) SemAn.isTypeAttr.get(typDefn);
                    try {
                        shell.actualType();
                    } catch (Report.InternalError e) {
                        throw new Report.Error(typDefn, "Cyclic type definition: '" + typDefn.name + "'.");
                    }
                }
            }
            // pass 3 — vars
            for (AST.FullDefn defn : letExpr.defns)
                if (defn instanceof AST.VarDefn)
                    defn.accept(this, arg);

            // pass 4 — function signatures only (pars + return type, no body)
            for (AST.FullDefn defn : letExpr.defns)
                if (defn instanceof AST.FunDefn)
                    visitFunSignature((AST.FunDefn)defn, arg);

            // pass 5 — function bodies
            for (AST.FullDefn defn : letExpr.defns)
                if (defn instanceof AST.DefFunDefn defFunDefn)
                    defFunDefn.expr.accept(this, arg);
            letExpr.expr.accept(this, arg); // se telo let-a
            return null;
        }
        // definicija tipa; NE SME IMETI OFTYPE!
        @Override
        public Object visit(AST.TypDefn typDefn, Object arg) {
            // shell iz prehoda 1
            TYP.NameType shell = (TYP.NameType) SemAn.isTypeAttr.get(typDefn);
            // 2. prehod; telo
            typDefn.type.accept(this, arg);
            TYP.Type actual = SemAn.isTypeAttr.get(typDefn.type);
            shell.setActType(actual);
            return null;
        }
        // definicija spremenljivke
        @Override
        public Object visit(AST.VarDefn varDefn, Object arg) {
            // razresi tip spremenljivke
            varDefn.type.accept(this, arg);
            TYP.Type varType = SemAn.isTypeAttr.get(varDefn.type);
            if (equiv(varType, TYP.VoidType.type)) {
                throw new Report.Error(varDefn, "Variable cannot be void type.");
            }
            SemAn.ofTypeAttr.put(varDefn, TYP.VoidType.type);
            return null;
        }
        // definicija funkcije; 2 prehod
        @Override
        public Object visit(AST.DefFunDefn defFunDefn, Object arg) {
            // telo funkcije ; podpis ze v 1. prehodu
            defFunDefn.expr.accept(this, arg);
            return null;
        }
        @Override
        public Object visit(AST.ExtFunDefn extFunDefn, Object arg) {
            // ze v 1. prehodu
            return null;
        }

        // doloci tip atomarnim tipom (int, char, bool, void)
        @Override
        public Object visit(AST.AtomType atomType, Object arg) {
            switch (atomType.type) {
                case INT : SemAn.isTypeAttr.put(atomType, TYP.IntType.type); break;
                case CHAR: SemAn.isTypeAttr.put(atomType, TYP.CharType.type); break;
                case BOOL: SemAn.isTypeAttr.put(atomType, TYP.BoolType.type); break;
                case VOID: SemAn.isTypeAttr.put(atomType, TYP.VoidType.type);  break;
            }
            return null;
        }
        // doloci tip kazalca
        @Override
        public Object visit(AST.PtrType ptrType, Object arg) {
            // razresi base type
            ptrType.baseType.accept(this, arg);
            // poisci base type
            TYP.Type base = SemAn.isTypeAttr.get(ptrType.baseType);
            // void check odstranjen - zakasnjen do type checkerja (sicer crasha na rek. tipih)
            // ustvari dejanski nov ptrtype
            TYP.PtrType ptr = new TYP.PtrType(base);
            SemAn.isTypeAttr.put(ptrType, ptr);
            return null;
        }
        // doloci tip tabele
        @Override
        public  Object visit(AST.ArrType arrType, Object arg) {
            // razresi elemType
            arrType.elemType.accept(this, arg);
            // poisci elemTYPE
            TYP.Type elem = SemAn.isTypeAttr.get(arrType.elemType);
            Long n;
            try {
                n = Long.parseLong(arrType.numElems);
                // in range check 0 < n < 2^63
                if (n <= 0) {
                    throw new Report.Error(arrType, "Array size must be > 0.");
                }
            } catch (NumberFormatException e) {
                throw new Report.Error(arrType, "The number of elements field: '" + arrType.numElems + "' for this array is invalid.");
            }
            // ustvari dejanski nov arrtype
            TYP.ArrType arr = new TYP.ArrType(elem, n);
            SemAn.isTypeAttr.put(arrType, arr);
            return null;
        }
        //doloci tip komponent zapisa (struct/union)
        @Override
        public Object visit(AST.CompDefn compDefn, Object arg) {
            // obisci tip komponente
            compDefn.type.accept(this, arg);
            // ne nastavi tipa na vozlisce, ker je CompDefn prepovedan za istype
            TYP.Type compType = SemAn.isTypeAttr.get(compDefn.type);
            SemAn.ofTypeAttr.put(compDefn, compType);
            return null;
        }
        // doloci tip strukture
        @Override
        public Object visit(AST.StrType strType, Object arg) {
            // Iterate manually — do NOT call strType.comps.accept(this, arg)
            // because that would dispatch to the overridden 5-pass visit(Nodes<?>)
            // which ignores CompDefn entirely.
            // razresi tipe komponent
            List<TYP.Type> compTypes = new ArrayList<>();
            for (AST.CompDefn comp : strType.comps) {
                comp.accept(this, arg);
                TYP.Type compType =  SemAn.isTypeAttr.get(comp.type);
                compTypes.add(compType);
            }
            TYP.StrType str = new TYP.StrType(compTypes);
            SemAn.isTypeAttr.put(strType, str);
            strTypeMap.put(str, strType);
            return null;
        }
        // doloci tip unije
        @Override
        public Object visit(AST.UniType uniType, Object arg) {
            // Iterate manually — do NOT call strType.comps.accept(this, arg)
            // because that would dispatch to the overridden 5-pass visit(Nodes<?>)
            // which ignores CompDefn entirely.
            // razresi tipe komponent
            List<TYP.Type> compTypes = new ArrayList<>();
            for (AST.CompDefn comp : uniType.comps) {
                comp.accept(this, arg);
                TYP.Type compType =  SemAn.isTypeAttr.get(comp.type);
                compTypes.add(compType);
            }
            TYP.UniType uni = new TYP.UniType(compTypes);
            SemAn.isTypeAttr.put(uniType, uni);
            uniTypeMap.put(uni, uniType);
            return null;
        }
        // doloci tip funkcije
        @Override
        public Object visit(AST.FunType funType, Object arg) {
            // do not call  funType.parTypes.accept(this, arg);!
            // razresi tipe parametrov in rezultata funkcije
            funType.resType.accept(this, arg);
            TYP.Type resType = SemAn.isTypeAttr.get(funType.resType);
            List<TYP.Type> parTypes = new ArrayList<>();
            for (AST.Type par: funType.parTypes) {
                par.accept(this, arg);
                TYP.Type parType = SemAn.isTypeAttr.get(par);
                parTypes.add(parType);
            }
            TYP.FunType fun = new TYP.FunType(parTypes, resType);
            SemAn.isTypeAttr.put(funType, fun);
            return null;
        }
        // doloci poimenovan tip
        @Override
        public Object visit(AST.NameType nameType, Object arg) {
            AST.TypDefn typDefn = (AST.TypDefn) SemAn.defAtAttr.get(nameType);
            TYP.NameType shell = (TYP.NameType) SemAn.isTypeAttr.get(typDefn);
            // shell gotovo obstaja zaradi 1. prehoda, ki je ze bil pognan
            SemAn.isTypeAttr.put(nameType, shell);
            return null;
        }

        // need a manual check because of generic nodes visit AB
        @Override
        public Object visit(AST.Exprs exprs, Object arg) {
            for (AST.Expr expr : exprs.exprs) {
                expr.accept(this, arg);
            }
            return null;
        }

        @Override
        public Object visit(AST.CallExpr callExpr, Object arg) {
            // obisci izraz funkcije in argumente
            callExpr.funExpr.accept(this, arg);
            for (AST.Expr argExpr : callExpr.argExprs) {
                argExpr.accept(this, arg);
            }
            return null;
        }

        @Override
        public Object visit(AST.CastExpr castExpr, Object arg) {
            castExpr.type.accept(this, arg);
            castExpr.expr.accept(this, arg);
            return null;
        }
    }
