package prev26lang.phase.asmgen;

import prev26lang.phase.memory.MEM;

import java.util.Vector;

public class Targets {
    public Vector<MEM.Label> labels;
    public Targets(Vector<MEM.Label> l) {
        labels = l;
    }
}
