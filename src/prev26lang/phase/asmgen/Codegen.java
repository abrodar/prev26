package prev26lang.phase.asmgen;

import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.memory.MEM;

import java.util.HashMap;
import java.util.Vector;

public class Codegen {
    MEM.Frame frame;
    private Vector<Instr> iList;

    public Codegen(MEM.Frame frame) {
        this.frame = frame;
    }
    private Vector<MEM.Temp> toVector(MEM.Temp... temps) {
        Vector<MEM.Temp> v = new Vector<>();
        for (MEM.Temp t : temps) v.add(t);
        return v;
    }
    public void emit(Instr inst) {
        iList.add(inst);
    }

    public Vector<Instr> codegen(IMR.Stmt s) {
        munchStm(s);
        return iList;
    }

    MEM.Temp munchExp(IMR.Expr e) {
        if (e instanceof IMR.MEM8 m8) {
            if (m8.addr instanceof IMR.BINOP bin && bin.oper == IMR.BINOP.Oper.ADD) {
                if (bin.fstExpr instanceof IMR.CONST c) {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("ld `d0, "+c.value+"(`s0)", toVector(munchExp(bin.sndExpr)), toVector(r)));
                    return r;
                } else if (bin.sndExpr instanceof IMR.CONST c) {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("ld `d0, "+c.value+"(`s0)", toVector(munchExp(bin.fstExpr)), toVector(r)));
                    return r;
                } else {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("ld `d0, 0(`s0)", toVector(munchExp(m8.addr)), toVector(r)));
                    return r;
                }
            }  else if (m8.addr instanceof IMR.CONST c) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("li `d0, "+c.value+"", null, toVector(r)));
                emit(new OPER("ld `d0, 0(`s0)", toVector(r), toVector(r)));
                return r;
            } else if(m8.addr instanceof IMR.NAME n) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("la `d0, "+n.label.name, null, toVector(r)));
                emit(new OPER("ld `d0, 0(`s0)", toVector(r), toVector(r)));
                return r;
            } else {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("ld `d0, 0(`s0)", toVector(munchExp(m8.addr)), toVector(r)));
                return r;
            }
        } else if (e instanceof IMR.MEM1 m1) {
            if (m1.addr instanceof IMR.BINOP bin && bin.oper == IMR.BINOP.Oper.ADD) {
                if (bin.fstExpr instanceof IMR.CONST c) {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("lb `d0, "+c.value+"(`s0)", toVector(munchExp(bin.sndExpr)), toVector(r)));
                    return r;
                } else if (bin.sndExpr instanceof IMR.CONST c) {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("lb `d0, "+c.value+"(`s0)", toVector(munchExp(bin.fstExpr)), toVector(r)));
                    return r;
                } else {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("lb `d0, 0(`s0)", toVector(munchExp(m1.addr)), toVector(r)));
                    return r;
                }
            }  else if (m1.addr instanceof IMR.CONST c) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("li `d0, "+c.value+"", null, toVector(r)));
                emit(new OPER("lb `d0, 0(`s0)", toVector(r), toVector(r)));
                return r;
            } else if(m1.addr instanceof IMR.NAME n) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("la `d0, "+n.label.name, null, toVector(r)));
                emit(new OPER("lb `d0, 0(`s0)", toVector(r), toVector(r)));
                return r;
            } else {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("lb `d0, 0(`s0)", toVector(munchExp(m1.addr)), toVector(r)));
                return r;
            }
        } else if (e instanceof IMR.BINOP bin) {
            if (bin.oper == IMR.BINOP.Oper.ADD) {
                if (bin.fstExpr instanceof IMR.CONST c) {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("addi `d0, `s0, "+c.value, toVector(munchExp(bin.sndExpr)), toVector(r)));
                    return r;
                } else if (bin.sndExpr instanceof IMR.CONST c) {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("addi `d0, `s0, "+c.value, toVector(munchExp(bin.fstExpr)), toVector(r)));
                    return r;
                } else {
                    MEM.Temp r = new MEM.Temp();
                    emit(new OPER("add `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                    return r;
                }
            } else if (bin.oper == IMR.BINOP.Oper.MUL) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("mul `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.DIV) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("div `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
            } else if (bin.oper == IMR.BINOP.Oper.MOD) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("rem `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.SUB) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("sub `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.AND) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("and `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.OR) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("or `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.LTH) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("slt `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.EQU) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("xor `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                emit(new OPER("seqz `d0, `s1", toVector(r), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.NEQ) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("xor `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                emit(new OPER("snez `d0, `s1", toVector(r), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.GTH) {
                MEM.Temp r = new MEM.Temp();
                MEM.Temp s0 = new MEM.Temp();
                MEM.Temp s1 = new MEM.Temp();
                emit(new OPER("neg `d0, `s0", toVector(munchExp(bin.fstExpr)), toVector(s0)));
                emit(new OPER("neg `d0, `s1", toVector(munchExp(bin.sndExpr)), toVector(s1)));
                emit(new OPER("slt `d0, `s0, `s1", toVector(s0, s1), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.LEQ) {
                MEM.Temp r = new MEM.Temp();
                MEM.Temp s0 = new MEM.Temp();
                MEM.Temp s1 = new MEM.Temp();
                emit(new OPER("neg `d0, `s0", toVector(munchExp(bin.fstExpr)), toVector(s0)));
                emit(new OPER("neg `d0, `s1", toVector(munchExp(bin.sndExpr)), toVector(s1)));
                emit(new OPER("slt `d0, `s0, `s1", toVector(s0, s1), toVector(r)));
                emit(new OPER("seqz `d0, `s1", toVector(r), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.GEQ) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("slt `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                emit(new OPER("seqz `d0, `s1", toVector(r), toVector(r)));
                return r;
            }
        } else if (e instanceof IMR.CONST c) {
            MEM.Temp r = new MEM.Temp();
            emit(new OPER("li `d0, "+c.value, null, toVector(r)));
            return r;
        } else if (e instanceof IMR.TEMP t) {
            return t.temp;
        }
        return null;
    }

    void munchStm(IMR.Stmt s) {
        if (s instanceof IMR.STMTS stmts) {
            for (IMR.Stmt stmt : stmts.stmts) {
                munchStm(stmt);
            }
        } else if (s instanceof IMR.MOVE move) {
            if (move.dst instanceof IMR.MEM8 m8) {
                if (m8.addr instanceof IMR.BINOP bin && bin.oper == IMR.BINOP.Oper.ADD) {
                    if (bin.fstExpr instanceof IMR.CONST fc) {
                        emit(new OPER("sd `s0, "+fc.value+"(`s1)", toVector(munchExp(move.src), munchExp(bin.sndExpr)), null));
                    } else if (bin.sndExpr instanceof IMR.CONST sc) {
                        emit(new OPER("sd `s0, "+sc.value+"(`s1)", toVector(munchExp(move.src), munchExp(bin.fstExpr)), null));
                    } else {
                        emit(new OPER("sd `s0, 0(`s1)", toVector(munchExp(move.src), munchExp(m8.addr)), null));
                    }
                } else {
                    emit(new OPER("sd `s0, 0(`s1)", toVector(munchExp(move.src), munchExp(m8.addr)), null));
                }
            } else if (move.dst instanceof IMR.MEM1 m1) {
                if (m1.addr instanceof IMR.BINOP bin && bin.oper == IMR.BINOP.Oper.ADD) {
                    if (bin.fstExpr instanceof IMR.CONST fc) {
                        emit(new OPER("sb `s0, "+fc.value+"(`s1)", toVector(munchExp(move.src), munchExp(bin.sndExpr)), null));
                    } else if (bin.sndExpr instanceof IMR.CONST sc) {
                        emit(new OPER("sb `s0, "+sc.value+"(`s1)", toVector(munchExp(move.src), munchExp(bin.fstExpr)), null));
                    } else {
                        emit(new OPER("sb `s0, 0(`s1)", toVector(munchExp(move.src), munchExp(m1.addr)), null));
                    }
                } else {
                    emit(new OPER("sb `s0, 0(`s1)", toVector(munchExp(move.src), munchExp(m1.addr)), null));
                }
            } else if (move.dst instanceof IMR.TEMP temp) {
                if (move.src instanceof IMR.TEMP temp_src) {
                    emit(new MOVE("mv `d0, `s0", temp_src.temp, temp.temp));
                } else {
                    emit(new OPER("mv `d0, `s0", toVector(munchExp(move.src)), toVector(temp.temp)));
                }
            }
        } else if (s instanceof IMR.LABEL lab) {
            emit(new LABEL(lab+":\n", lab.label));
        }
    }

}
