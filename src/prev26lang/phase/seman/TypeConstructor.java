    package prev26lang.phase.seman;

    import prev26lang.common.report.Report;
    import prev26lang.phase.abstr.*;

    import java.util.ArrayList;
    import java.util.List;

    // handles AST.Type nodes → builds TYP.Type objects → stores in isTypeAttr
    // also handles TypDefn, VarDefn, FunDefn type signatures
    // runs before typechecker
    public class TypeConstructor implements AST.FullVisitor<Object, Object>  {
        // helper metode
        private void visitFunSignature(AST.FunDefn funDefn, Object arg) {
            // razresi tipe parametrov in rezultata funkcije
            funDefn.type.accept(this, arg);
            TYP.Type resType = SemAn.isTypeAttr.get(funDefn.type);
            List<TYP.Type> parTypes = new ArrayList<>();
            for (AST.ParDefn par: funDefn.pars) {
                par.accept(this, arg);
                TYP.Type parType = SemAn.isTypeAttr.get(par.type);
                parTypes.add(parType);
                // parametri atributa
                SemAn.ofTypeAttr.put(par, parType);
                SemAn.isConstAttr.put(par, false);
                SemAn.isAddrAttr.put(par, true);
            }
            TYP.FunType fun = new TYP.FunType(parTypes, resType);
            SemAn.ofTypeAttr.put(funDefn, fun);
            SemAn.isConstAttr.put(funDefn, false);
            SemAn.isAddrAttr.put(funDefn, false);
        }
        /** Constructs a new name resolver. */
        public TypeConstructor() {
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
            // prehod 2: tipi telesa
            for (AST.Node node : nodes) {
                if (node instanceof AST.TypDefn typDefn) {
                    node.accept(this, arg);
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
                if (node instanceof AST.DefFunDefn defFunDefn)
                    defFunDefn.expr.accept(this, arg);
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
                if (defn instanceof AST.TypDefn typDefn) {
                    defn.accept(this, arg);
                }
            }
            // pass 3 — vars
            for (AST.FullDefn defn : letExpr.defns)
                if (defn instanceof AST.VarDefn)
                    defn.accept(this, arg);

            // pass 4 — function signatures only (pars + return type, no body)
            for (AST.FullDefn defn : letExpr.defns)
                if (defn instanceof AST.FunDefn funDefn)
                    visitFunSignature((AST.FunDefn)defn, arg);

            // pass 5 — function bodies
            for (AST.FullDefn defn : letExpr.defns)
                if (defn instanceof AST.DefFunDefn defFunDefn)
                    defFunDefn.expr.accept(this, arg);
            letExpr.expr.accept(this, arg); // se telo let-a
            return null;
        }
        // definicija tipa
        @Override
        public Object visit(AST.TypDefn typDefn, Object arg) {
            // shell iz prehoda 1
            TYP.NameType shell = (TYP.NameType) SemAn.isTypeAttr.get(typDefn);
            // 2. prehod; telo
            typDefn.type.accept(this, arg);
            TYP.Type actual = SemAn.isTypeAttr.get(typDefn.type);
            shell.setActType(actual);
            // nastavi tudi oftype
            SemAn.ofTypeAttr.put(typDefn, TYP.VoidType.type);
            return null;
        }
        // definicija spremenljivke
        @Override
        public Object visit(AST.VarDefn varDefn, Object arg) {
            // razresi tip spremenljivke
            varDefn.type.accept(this, arg);
            TYP.Type type = SemAn.isTypeAttr.get(varDefn.type);
            SemAn.ofTypeAttr.put(varDefn, type);
            SemAn.isConstAttr.put(varDefn, false);
            SemAn.isAddrAttr.put(varDefn, true);
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
            return null;
        }
        // doloci tip strukture
        @Override
        public Object visit(AST.StrType strType, Object arg) {
            // razresi tipe komponent
            strType.comps.accept(this, arg);
            List<TYP.Type> compTypes = new ArrayList<>();
            for (AST.CompDefn comp : strType.comps) {
                TYP.Type compType =  SemAn.isTypeAttr.get(comp.type);
                compTypes.add(compType);
            }
            TYP.StrType str = new TYP.StrType(compTypes);
            SemAn.isTypeAttr.put(strType, str);
            return null;
        }
        // doloci tip unije
        @Override
        public Object visit(AST.UniType uniType, Object arg) {
            // razresi tipe komponent
            uniType.comps.accept(this, arg);
            List<TYP.Type> compTypes = new ArrayList<>();
            for (AST.CompDefn comp : uniType.comps) {
                TYP.Type compType =  SemAn.isTypeAttr.get(comp.type);
                compTypes.add(compType);
            }
            TYP.UniType str = new TYP.UniType(compTypes);
            SemAn.isTypeAttr.put(uniType, str);
            return null;
        }
        // doloci tip funkcije
        @Override
        public Object visit(AST.FunType funType, Object arg) {
            // razresi tipe parametrov in rezultata funkcije
            funType.parTypes.accept(this, arg);
            funType.resType.accept(this, arg);
            TYP.Type resType = SemAn.isTypeAttr.get(funType.resType);
            List<TYP.Type> parTypes = new ArrayList<>();
            for (AST.Type par: funType.parTypes) {
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
    }
