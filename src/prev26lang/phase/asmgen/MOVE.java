package prev26lang.phase.asmgen;

import prev26lang.phase.memory.MEM;

import java.util.Vector;

public class MOVE extends Instr{
    public MEM.Temp def;
    public MEM.Temp use;

    public MOVE(String a, MEM.Temp use, MEM.Temp def){
        assem = a;
        this.use = use;
        this.def = def;
    }

    public Vector<MEM.Temp> use() {
        Vector<MEM.Temp> v = new Vector<>();
        v.add(use);
        return v;
    }

    public Vector<MEM.Temp> def() {
        Vector<MEM.Temp> v = new Vector<>();
        v.add(def);
        return v;
    }

    public Targets jumps() {
        return null;
    }

}
