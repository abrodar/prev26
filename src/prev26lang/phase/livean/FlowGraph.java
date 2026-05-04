package prev26lang.phase.livean;

import prev26lang.phase.memory.MEM;

import java.util.HashSet;
import java.util.Vector;

/**
 * A control flow graph is a directed graph in which each edge
 * indicates a possible flow of control.  Also, each node in
 * the graph defines a set of temporaries; each node uses a set of
 * temporaries; and each node is, or is not, a <strong>move</strong>
 * instruction.
 *
 *
 */

public abstract class FlowGraph extends Graph {
    /**
     * The set of temporaries defined by this instruction or block
     */
    public abstract HashSet<MEM.Temp> def(Node node);

    /**
     * The set of temporaries used by this instruction or block
     */
    public abstract HashSet<MEM.Temp> use(Node node);

    /**
     * True if this node represents a <strong>move</strong> instruction,
     * i.e. one that can be deleted if def=use.
     */
    public abstract boolean isMove(Node node);

    /**
     * Print a human-readable dump for debugging.
     */
    public void show() {
        for (Node n : nodes()) {
            System.out.print(n + ": ");
            for (MEM.Temp t : def(n)) {
                System.out.print(t + " ");
            }
            System.out.print(isMove(n) ? "<= " : "<- ");
            for (MEM.Temp t : use(n)) {
                System.out.print(t + " ");
            }
            System.out.print("; goto ");
            for (Node s : n.succ()) {
                System.out.print(s +" ");
            }
            System.out.println();
        }
    }

}

