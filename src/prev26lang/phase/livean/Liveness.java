package prev26lang.phase.livean;

import prev26lang.Compiler;
import prev26lang.phase.memory.MEM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;


// produces an interference graph from a flow graph
public class Liveness extends InterferenceGraph{
    // live-in set
    public HashMap<Node, HashSet<MEM.Temp>> in = new HashMap<>();
    // live-out set
    public HashMap<Node, HashSet<MEM.Temp>> out = new HashMap<>();
    // helper maps for constructing the graph
    public HashMap<Node, MEM.Temp> nodeToTemp = new HashMap<>();
    public HashMap<MEM.Temp, Node> tempToNode = new HashMap<>();
    // computes liveness
    // rule 1: in[n] = use[n] U (out[n] - def[n])
    // rule 2: out[n] = U_{s el succ[n]} in[s]
    public Liveness(FlowGraph flow, MEM.Frame frame) {
        for (Node n : flow.nodes()) {
            in.put(n, new HashSet<MEM.Temp>());
            out.put(n, new HashSet<MEM.Temp>());
        }
        HashMap<Node, HashSet<MEM.Temp>> in_copy;
        HashMap<Node, HashSet<MEM.Temp>> out_copy;
        do {
            in_copy = new HashMap<>();
            for (var entry : in.entrySet()) {
                in_copy.put(entry.getKey(), new HashSet<>(entry.getValue())); // deep copy
            }
            out_copy = new HashMap<>();
            for (var entry : out.entrySet()) {
                out_copy.put(entry.getKey(), new HashSet<>(entry.getValue())); // deep copy
            }
            for (Node n : flow.nodes()) {
                HashSet<MEM.Temp> use_n = flow.use(n);
                HashSet<MEM.Temp> def_n = flow.def(n);
                HashSet<MEM.Temp> out_n = out.get(n);
                HashSet<MEM.Temp> in_n = in.get(n);
                // complement for in
                HashSet<MEM.Temp> diff = new HashSet<>(out_n);
                diff.removeAll(def_n);
                // perform all the unions on in[n]
                in_n.addAll(use_n);
                in_n.addAll(diff);
                // onto rule 2 for out[n]
                for (Node s : n.succ()) {
                    HashSet<MEM.Temp> in_s = in.get(s);
                    out_n.addAll(in_s);
                }
                //System.out.println("in: "+in + " in': "+in_copy);
            }

        } while (!(in_copy.equals(in) && out_copy.equals(out)));
        //System.out.println("livein: " +in);
        //System.out.println("liveout: " + out);

        for (Node n : flow.nodes()) {
            for (MEM.Temp d : flow.def(n)) {
                //if (frame.tempMap(d) == null)
                    tnode(d);
                //else
                    //continue;
                for (MEM.Temp t : out.get(n)) {
                    if (!d.equals(t)) {//&& frame.tempMap(t) == null) { // no self-edges
                        // undirected — add both directions
                        addEdge(tnode(d), tnode(t));
                        addEdge(tnode(t), tnode(d));
                    }
                }
            }
            // also add nodes for uses so special regs like s0 get precolored
            for (MEM.Temp u : flow.use(n)) {
                tnode(u);
            }
        }
    }

    //relates a temp to a node
    @Override
    public Node tnode(MEM.Temp temp) {
        if (!tempToNode.containsKey(temp)) {
            Node n = new Node(this);
            tempToNode.put(temp, n);
            nodeToTemp.put(n, temp);
        }
        return tempToNode.get(temp);
    }
    // relates a node to a temp
    @Override
    public MEM.Temp gtemp(Node node) {
        return nodeToTemp.get(node);
    }

    @Override
    public Vector<Node> moves() {
        return null;
    }

    public void showWithTemp(MEM.Frame frame) {
        for (Node n : nodes()) {
            System.out.print(n.toString() + "-("+ Compiler.formatTemps(new HashSet<>(Set.of(nodeToTemp.get(n))), frame)+" - "+nodeToTemp.get(n)+"):     ");
            for (Node s : n.succ()) {
                System.out.print(s.toString() + "-("+nodeToTemp.get(s)+")       ");
            }
            System.out.println();
        }
    }
}
