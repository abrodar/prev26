    package prev26lang.phase.seman;

    import org.antlr.v4.codegen.model.chunk.RulePropertyRef_ctx;
    import prev26lang.common.report.Report;
    import prev26lang.phase.abstr.*;

    import java.util.ArrayList;
    import java.util.List;

    // handles AST.Type nodes → builds TYP.Type objects → stores in isTypeAttr
    // also handles TypDefn, VarDefn, FunDefn type signatures
    // runs before typechecker
    public class TypeConstructor implements AST.FullVisitor<Object, Object>  {
        /** Constructs a new name resolver. */
        public TypeConstructor() {
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
            // preveri da basetype ni void
            if (base instanceof TYP.VoidType) {
                throw new Report.Error(ptrType, "Cannot create pointer to void type");
            }
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
            // nastavi tip vozliscu compDefn
            TYP.Type type = SemAn.isTypeAttr.get(compDefn.type);
            SemAn.isTypeAttr.put(compDefn, type);
            return null;
        }
        // doloci tip strukture
        @Override
        public Object visit(AST.StrType strType, Object arg) {
            // razresi tipe komponent
            strType.comps.accept(this, arg);
            List<TYP.Type> compTypes = new ArrayList<>();
            for (AST.CompDefn comp : strType.comps) {
                TYP.Type compType =  SemAn.isTypeAttr.get(comp);
                if (compType instanceof TYP.VoidType) {
                    throw new Report.Error(comp, "Struct components can not have void type.");
                }
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
                TYP.Type compType =  SemAn.isTypeAttr.get(comp);
                if (compType instanceof TYP.VoidType) {
                    throw new Report.Error(comp, "Union components can not have void type.");
                }
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
            TYP.Type resType = SemAn.isTypeAttr.get(funType.resType).actualType(); // type could be names so take actual
            if (!(resType instanceof TYP.IntType || resType instanceof TYP.CharType ||
                    resType instanceof TYP.BoolType || resType instanceof TYP.PtrType || resType instanceof TYP.FunType || resType instanceof TYP.VoidType)) {
                throw new Report.Error(funType.resType, "Function has invalid return type '" + resType + "'.");
            }
            List<TYP.Type> parTypes = new ArrayList<>();
            for (AST.Type par: funType.parTypes) {
                TYP.Type parType = SemAn.isTypeAttr.get(par).actualType(); // type could be names so take actual
                if (!(parType instanceof TYP.IntType || parType instanceof TYP.CharType ||
                        parType instanceof TYP.BoolType || parType instanceof TYP.PtrType || parType instanceof TYP.FunType)) {
                        throw new Report.Error(par, "Function has invalid parameter type '" + parType + "'.");
                }
                parTypes.add(parType);
            }
            TYP.FunType fun = new TYP.FunType(parTypes, resType);
            SemAn.isTypeAttr.put(funType, fun);
            return null;
        }
        // doloci poimenovan tip
        @Override
        public Object visit(AST.NameType nameType, Object arg) {
            // poisci definicijo za to uporabo
            AST.TypDefn typDef = (AST.TypDefn) SemAn.defAtAttr.get(nameType);
            // razresi tip definicije
            typDef.type.accept(this, arg);
            // poisci definiran tip
            TYP.Type typ = SemAn.isTypeAttr.get(typDef.type);
            // ustvari nov poimenovan tip
            TYP.NameType namedType = new TYP.NameType(nameType.name);
            namedType.setActType(typ);
            SemAn.isTypeAttr.put(nameType, namedType);
            return null;
        }
    }
