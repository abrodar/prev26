package prev26lang.phase.asmgen;

import prev26lang.phase.memory.MEM;

import java.util.Vector;

public class OPER extends Instr {
    public Vector<MEM.Temp> dst; // seznam operandnih registrov; lahko null
    public Vector<MEM.Temp> src; // seznam registrov z rezultati; lahko null
    public Targets jump; // ciljne labele skokov; lahko null

    public OPER(String a, Vector<MEM.Temp> src, Vector<MEM.Temp> dst, Vector<MEM.Label> jump) {
        assem = a;
        this.src = src;
        this.dst = dst;
        this.jump = new  Targets(jump);
    }

    public OPER(String a, Vector<MEM.Temp> src, Vector<MEM.Temp> dst) {
        assem = a;
        this.src = src;
        this.dst = dst;
        jump = null;
    }

    public Vector<MEM.Temp> use() {
        return src;
    }

    public Vector<MEM.Temp> def() {
        return dst;
    }

    public Targets jumps() {
        return jump;
    }
    //TODO
    public String format() {
        return "";
    }
}
