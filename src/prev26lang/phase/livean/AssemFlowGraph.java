package prev26lang.phase.livean;

import prev26lang.phase.asmgen.*;
import prev26lang.phase.memory.MEM;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Vector;

public class AssemFlowGraph extends FlowGraph {
    public HashMap<MEM.Label, Node> labelMap = new HashMap<>();
    public HashMap<Node, Instr> nodeMap = new HashMap<Node, Instr>();
    public HashMap<Instr, Node> instrMap = new HashMap<Instr, Node>();
    public Vector<Node> nodes = new Vector<>();

    public AssemFlowGraph(Vector<Instr> instrs) {
        // 1st pass, create nodes, and map them to instr, also create label map
        // all this extra maps because we want to make graph modular for other phases!
        for (Instr instr : instrs) {
            Node n = new Node(this);
            if (instr instanceof LABEL l) {
                labelMap.put(l.label, n);
            }
            nodeMap.put(n, instr);
            instrMap.put(instr, n);
            nodes.add(n);
        }
        // 2nd pass
        for (int i = 0; i < nodes.size(); i++) {
            Node n = nodes.get(i);
            Instr instr = nodeMap.get(n);
            if (instr instanceof OPER oper) {
                Targets jumps = oper.jumps();
                // branch - connect to all jump targets
                if (jumps != null) {
                    for (MEM.Label label : jumps.labels) {
                        Node target = labelMap.get(label);
                        if (target != null) {
                            addEdge(n, labelMap.get(label));
                        }
                    }
                } else { // connect to the next one if exists
                    if (i + 1 < nodes.size()) {
                        addEdge(n, nodes.get(i + 1));
                    }
                }
            } else if (instr instanceof MOVE move) {
                if (i + 1 < nodes.size()) {
                    addEdge(n, nodes.get(i + 1));
                }
            } else { // it must be a label at this point
                if (i + 1 < nodes.size()) {
                    addEdge(n, nodes.get(i + 1));
                }
            }
        }
    }

    @Override
    public HashSet<MEM.Temp> def(Node node) {
        Instr instr = nodeMap.get(node);
        if (instr.def() != null) {
            HashSet<MEM.Temp> def_set = new HashSet<>();
            for (MEM.Temp temp : instr.def()) {
                def_set.add(temp);
            }
            return def_set;
        } else {
            return new HashSet<MEM.Temp>();
        }
    }

    @Override
    public HashSet<MEM.Temp> use(Node node) {
        Instr instr =  nodeMap.get(node);
        if (instr.use() != null) {
            HashSet<MEM.Temp> use_set = new HashSet<>();
            for (MEM.Temp temp : instr.use()) {
                use_set.add(temp);
            }
            return use_set;
        } else {
            return new HashSet<MEM.Temp>();
        }
    }

    @Override
    public boolean isMove(Node node) {
        return  nodeMap.get(node) instanceof MOVE;
    }
    /**
     * Print a human-readable dump for debugging.
     */
    public void show(Vector<Instr> instrs) {
        for (Instr instr : instrs) {
            Node n = instrMap.get(instr);
            System.out.print(n +" ("+nodeMap.get(n).assem+") ");
            for (MEM.Temp t : def(n)) {
                System.out.print(t + " ");
            }
            System.out.print(isMove(n) ? "<= " : "<- ");
            for (MEM.Temp t : use(n)) {
                System.out.print(t + " ");
            }
            System.out.print("; goto ");
            for (Node s : n.succ()) {
                System.out.print(s +" ("+nodeMap.get(s).assem+") ");
            }
            System.out.println();
        }
    }
}
