package prev26lang.phase.asmgen;

import prev26lang.phase.memory.MEM;

import java.util.Vector;

public class OPER extends Instr {
    public Vector<MEM.Temp> def; // seznam operandnih registrov; lahko null
    public Vector<MEM.Temp> use; // seznam registrov z rezultati; lahko null
    public Targets jump; // ciljne labele skokov; lahko null

    public OPER(String a, Vector<MEM.Temp> use, Vector<MEM.Temp> def, Vector<MEM.Label> jump) {
        assem = a;
        this.use = use;
        this.def = def;
        this.jump = new  Targets(jump);
    }

    public OPER(String a, Vector<MEM.Temp> use, Vector<MEM.Temp> def) {
        assem = a;
        //same order as for args
        this.use = use; //a list of operand registers
        this.def = def; //a list of result registers
        jump = null;
    }

    public Vector<MEM.Temp> use() {
        return use;
    }

    public Vector<MEM.Temp> def() {
        return def;
    }

    public Targets jumps() {
        return jump;
    }
}
