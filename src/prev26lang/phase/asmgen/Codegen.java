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

    }

}
