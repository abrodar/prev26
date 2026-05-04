package prev26lang.phase.asmgen;

import prev26lang.common.report.Report;
import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.memory.MEM;

import java.util.Vector;

public class Codegen {
    MEM.Frame frame;
    private Vector<Instr> iList;
    //regs
    public static final MEM.Temp
        ZERO = new MEM.Temp(), // x0
        RA = new MEM.Temp(), // x1 - return address
        SP = new MEM.Temp(), // x2 - stack pointer
        FP = new MEM.Temp(), // x0 / so - frame pointer
        GP = new MEM.Temp(), // GP - global pointer
        TP = new MEM.Temp(), // TP - thread pointer
        // argument/return registers - caller saved
        A0 = new MEM.Temp(), A1 = new MEM.Temp(),
        A2 = new MEM.Temp(), A3 = new MEM.Temp(),
        A4 = new MEM.Temp(), A5 = new MEM.Temp(),
        A6 = new MEM.Temp(), A7 = new MEM.Temp(),
        // caller saved temporaries
        T0 = new MEM.Temp(), T1 = new MEM.Temp(),
        T2 = new MEM.Temp(), T3 = new MEM.Temp(),
        T4 = new MEM.Temp(), T5 = new MEM.Temp(),
        T6 = new MEM.Temp(),
        // calle saved
        S1  = new MEM.Temp(), S2  = new MEM.Temp(),
        S3  = new MEM.Temp(), S4  = new MEM.Temp(),
        S5  = new MEM.Temp(), S6  = new MEM.Temp(),
        S7  = new MEM.Temp(), S8  = new MEM.Temp(),
        S9  = new MEM.Temp(), S10 = new MEM.Temp(),
        S11 = new MEM.Temp();
    private static Vector<MEM.Temp> callerSaves = toVector(RA, T0, T1, T2, T3, T4, T5, T6, A0, A1, A2, A3, A4, A5, A6); // 15 total
    private static Vector<MEM.Temp> calleeSaves = toVector(SP, FP, S1, S2, S3, S4, S5, S6, S7, S8, S9, S10, S11); // 13 total
    private static Vector<MEM.Temp> argRegs = toVector(A0, A1, A2, A3, A4, A5, A6, A7);
    private static Vector<MEM.Temp> specialRegs = toVector(SP, FP, RA, GP, TP);

    public Codegen(MEM.Frame frame) {
        this.frame = frame;
    }
    private static  <T> Vector<T> toVector(T... args) {
        Vector<T> v = new Vector<>();
        for (T t : args) v.add(t);
        return v;
    }
    private static Vector<MEM.Temp> toVector(MEM.Temp head, Vector<MEM.Temp> tail) {
        Vector<MEM.Temp> v = new Vector<>();
        v.add(head);
        v.addAll(tail);
        return v;
    }

    public void emit(Instr inst) {
        iList.add(inst);
    }

    public Vector<Instr> codegen(IMR.Stmt s) {
        iList = new Vector<>(); //initialize to avoid NPE
        munchStm(s);
        return iList;
    }
    //processes the args list and moves each into the right position, returning the temps:
    private Vector<MEM.Temp> munchArgs(Vector<IMR.Expr> args) {
        Vector<MEM.Temp> temps = new Vector<>();
        for (IMR.Expr arg : args) {
            temps.add(munchExp(arg));
        }
        return temps;
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
                return r;
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
                emit(new OPER("seqz `d0, `s0", toVector(r), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.NEQ) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("xor `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                emit(new OPER("snez `d0, `s0", toVector(r), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.GTH) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("slt `d0, `s1, `s0", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.LEQ) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("slt `d0, `s0, `s1", toVector(munchExp(bin.sndExpr), munchExp(bin.fstExpr)), toVector(r)));
                emit(new OPER("xori `d0, `s0, 1", toVector(r), toVector(r)));
                return r;
            } else if (bin.oper == IMR.BINOP.Oper.GEQ) {
                MEM.Temp r = new MEM.Temp();
                emit(new OPER("slt `d0, `s0, `s1", toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), toVector(r)));
                emit(new OPER("xori `d0, `s0, 1", toVector(r), toVector(r)));
                return r;
            }
        } else if (e instanceof IMR.NAME n) {
            MEM.Temp r = new MEM.Temp();
            emit(new OPER("la `d0, "+n.label.name, null, toVector(r)));
            return r;
        } else if (e instanceof IMR.CONST c) {
            MEM.Temp r = new MEM.Temp();
            emit(new OPER("li `d0, "+c.value, null, toVector(r)));
            return r;
        } else if (e instanceof IMR.UNOP u) {
            MEM.Temp r = new MEM.Temp();
            MEM.Temp sub = munchExp(u.subExpr);
            if (u.oper == IMR.UNOP.Oper.NEG) {
                emit(new OPER("neg `d0, `s0", toVector(sub), toVector(r)));
            } else if (u.oper == IMR.UNOP.Oper.NOT) {
                emit(new OPER("seqz `d0, `s0", toVector(sub), toVector(r)));
            }
            return r;
        } else if (e instanceof IMR.TEMP t) {
            return t.temp; // already a temp, no instruction needed
        }
        throw new Report.Error("munchExp: unhandled expression type: " + e.getClass().getSimpleName() + " → " + e);
       // return null;
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
                if (move.src instanceof IMR.CALL call) {
                    MEM.Temp addr = munchExp(call.addr);
                    Vector<MEM.Temp> l = munchArgs(call.args);
                    for (int i = 0; i < l.size(); i++) {
                        Long offset = call.offs.get(i);
                        emit(new OPER("sd `s0, "+offset+"(`s1)", toVector(l.get(i), SP),null));
                    }
                    emit(new OPER("jalr ra, 0(`s0)", toVector(addr), toVector(temp.temp)));
                    // return value is in a0 by convention?
                    emit(new MOVE("mv `d0, `s0", A0, frame.RV));
                } else  if (move.src instanceof IMR.TEMP temp_src) {
                    emit(new MOVE("mv `d0, `s0", temp_src.temp, temp.temp));
                } else {
                    emit(new MOVE("mv `d0, `s0", munchExp(move.src), temp.temp));
                }
            }
        } else if (s instanceof IMR.LABEL lab) {
            emit(new LABEL(lab.label.name +":", lab.label));
        } else if (s instanceof IMR.JUMP jump) {
            if (jump.addr instanceof IMR.NAME n) {
                emit(new OPER("j "+n.label.name, null, null, toVector(n.label)));
            } else {
                throw new Report.Error("Can only JUMP to labels.");
            }
        } else if (s instanceof IMR.CJUMP cjump) {
            MEM.Label posLab = ((IMR.NAME) cjump.posAddr).label;
            MEM.Label negLab = ((IMR.NAME) cjump.negAddr).label;
            if (cjump.cond instanceof IMR.BINOP bin) {
                if (bin.oper == IMR.BINOP.Oper.EQU) {
                    emit(new OPER("beq `s0, `s1, "+posLab.name, toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), null, toVector(posLab, negLab)));
                } else if (bin.oper == IMR.BINOP.Oper.NEQ) {
                    emit(new OPER("bne `s0, `s1, "+posLab.name, toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), null, toVector(posLab, negLab)));
                } else if (bin.oper == IMR.BINOP.Oper.LTH) {
                    emit(new OPER("blt `s0, `s1, "+posLab.name, toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), null, toVector(posLab, negLab)));
                } else if (bin.oper == IMR.BINOP.Oper.GTH) {
                    emit(new OPER("bgt `s0, `s1, "+posLab.name, toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), null, toVector(posLab, negLab)));
                } else if (bin.oper == IMR.BINOP.Oper.LEQ) {
                    emit(new OPER("ble `s0, `s1, "+posLab.name, toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), null, toVector(posLab, negLab)));
                } else if (bin.oper == IMR.BINOP.Oper.GEQ) {
                    emit(new OPER("bge `s0, `s1, "+posLab.name, toVector(munchExp(bin.fstExpr), munchExp(bin.sndExpr)), null, toVector(posLab, negLab)));
                } else{
                    emit(new OPER("bnez `s0, "+posLab.name, toVector(munchExp(cjump.cond)), null, toVector(posLab, negLab)));
                }
            } else if (cjump.cond instanceof IMR.UNOP unop) {
                if (unop.oper == IMR.UNOP.Oper.NOT) {
                    emit(new OPER("beqz `s0, "+posLab.name, toVector(munchExp(unop.subExpr)), null, toVector(posLab, negLab)));
                } else {
                    throw new Report.Error("Double-check your unary operator in jump condition. Perhaps it should be NOT.");
                }
            } else {
                emit(new OPER("bnez `s0, "+posLab.name, toVector(munchExp(cjump.cond)), null, toVector(posLab, negLab)));
            }
        } else if (s instanceof IMR.ESTMT estmt && estmt.expr instanceof IMR.CALL call) {
            MEM.Temp addr = munchExp(call.addr);
            Vector<MEM.Temp> l = munchArgs(call.args);
            for (int i = 0; i < l.size(); i++) {
                Long offset = call.offs.get(i);
                emit(new OPER("sd `s0, "+offset+"(`s1)", toVector(l.get(i), SP),null));
            }
            emit(new OPER("jalr ra, 0(`s0)", toVector(addr), toVector(RA)));
        }
    }
}
