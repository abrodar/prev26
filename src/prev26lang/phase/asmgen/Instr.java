package prev26lang.phase.asmgen;

import prev26lang.phase.memory.MEM;

import java.util.Vector;

public abstract class Instr {
    public String assem;
    public abstract Vector<MEM.Temp> use();
    public abstract Vector<MEM.Temp> def();
    public abstract Targets jumps();
    public abstract String format();
}
