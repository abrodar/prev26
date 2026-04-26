package prev26lang.phase.asmgen;

import prev26lang.phase.memory.MEM;

import java.util.Vector;

public class MOVE extends Instr{
    public MEM.Temp dst;
    public MEM.Temp src;

    public MOVE(String a, MEM.Temp src, MEM.Temp dst){
        assem = a;
        this.dst = dst;
        this.src = src;
    }

    public Vector<MEM.Temp> use() {
        Vector<MEM.Temp> v = new Vector<>();
        v.add(src);
        return v;
    }

    public Vector<MEM.Temp> def() {
        Vector<MEM.Temp> v = new Vector<>();
        v.add(dst);
        return v;
    }

    public Targets jumps() {
        return null;
    }

    public String format() {
        return "";
    }
}
