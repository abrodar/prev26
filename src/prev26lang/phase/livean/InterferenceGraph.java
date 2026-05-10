package prev26lang.phase.livean;

import prev26lang.phase.memory.MEM;

import java.util.Vector;

abstract public class InterferenceGraph extends Graph {
    abstract public Node tnode(MEM.Temp temp);
    abstract public MEM.Temp gtemp(Node node);
    abstract public Vector<Node> moves();
    public int spillCost(Node node) {return 1;}
}