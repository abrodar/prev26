package prev26lang.phase.asmgen;

import prev26lang.phase.memory.MEM;

import java.util.Vector;

public class LABEL extends Instr {
    public MEM.Label label;

    public LABEL(String a, MEM.Label label) {
        assem = a;
        this.label = label;
    }

    public Vector<MEM.Temp> use() {
        return null;
    }

    public Vector<MEM.Temp> def() {
        return null;
    }

    public  Targets jumps() {
        return null;
    }

}
