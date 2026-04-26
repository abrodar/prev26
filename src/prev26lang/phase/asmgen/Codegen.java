package prev26lang.phase.asmgen;

import prev26lang.phase.imrgen.IMR;
import prev26lang.phase.memory.MEM;

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
