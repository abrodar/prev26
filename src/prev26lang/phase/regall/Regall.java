package prev26lang.phase.regall;

import prev26lang.phase.asmgen.Codegen;
import prev26lang.phase.asmgen.Instr;
import prev26lang.phase.asmgen.LABEL;
import prev26lang.phase.asmgen.MOVE;
import prev26lang.phase.imrlin.LIN;
import prev26lang.phase.livean.AssemFlowGraph;
import prev26lang.phase.livean.Liveness;
import prev26lang.phase.livean.Node;
import prev26lang.phase.memory.MEM;

import java.util.*;

import static prev26lang.Compiler.formatTemps;

public class Regall {
    public static int K = 26; // number of phy. regs for coloring; can be overwritten by the following flag: --num-regs=K'
    // Node work-lists, sets and stacks. The following lists and sets are always disjoint and every node is always in
    // exactly one of the sets or lists.
    HashSet<Node> precolored = new HashSet<>(); // machine registers, preassigned a color
    //static HashSet<Node> initial; // temporary registers, not precolored and not yet processed
    HashSet<Node> simplifyWorklist = new HashSet<>(); // list of low-degree non-move related nodes
    HashSet<Node> freezeWorklist = new HashSet<>(); // low-degree move-related nodes
    HashSet<Node> spillWorklist = new HashSet<>(); // high-degree nodes
    HashSet<Node> spilledNodes = new HashSet<>(); // nodes marked for spilling during this round; initially empty
    HashSet<Node> coalescedNodes = new HashSet<>(); // registers that have been coalesced; when u <- v is coalesced, v is added to this set and u put back on some worklist (could be vice-versa).
    HashSet<Node> coloredNodes = new HashSet<>(); // nodes successfully colored
    Stack<Node> selectStack = new Stack<>(); // stack containing nodes/temporaries removed from the graph
    // Move sets. These are five sets of move instructions, and every move is in
    // exactly one of these sets (after build to the end of main).
    HashSet<MOVE> coalescedMoves = new HashSet<>(); // moves that have been coalesced
    HashSet<MOVE> constrainedMoves = new HashSet<>(); // moves whose source and target interfere
    HashSet<MOVE> frozenMoves = new HashSet<>(); // moves that will no longer be considered for coalescing
    HashSet<MOVE> worklistMoves = new HashSet<>(); // moves enabled for possible coalescing
    HashSet<MOVE> activeMoves = new HashSet<>(); // moves not yet ready for coalescing

    // Other data-structures
    //adjSet
    //adjList
    int degree[]; // an array containing the current degree of each node
    HashMap<Node, HashSet<MOVE>> moveList = new HashMap<>(); // a mapping from a node to the list of moves it is associated with.
    //alias
    HashMap<Node, Node> alias; // when a move (u, v) has been coalesced, and v put in coalescedNodes, then alias(v) = u.
    //color

    private HashSet<MOVE> nodeMoves(Node node) {
        HashSet<MOVE> intersection = new HashSet<>(moveList.get(node));
        HashSet<MOVE> union = new HashSet<>(activeMoves);
        union.addAll(worklistMoves);
        intersection.retainAll(union);
        return intersection;
    }

    private boolean moveRelated(Node node) {
        return !nodeMoves(node).isEmpty();
    }

    public void makeWorklist(Liveness ig) {
        for (Node node : ig.nodes()) {
            int deg = degree[node.name()];
            if (deg >= K) {
                spillWorklist.add(node);
            } else if (moveRelated(node)) {
                freezeWorklist.add(node);
            } else {
                simplifyWorklist.add(node);
            }
        }
        System.out.println("spillWorklist: " + spillWorklist);
        System.out.println("freezeWorklist: " + freezeWorklist);
        System.out.println("simplifyWorklist: " + simplifyWorklist);
    }
    public void enableMoves(HashSet<Node> nodes) {
        for (Node n : nodes) {
            for (MOVE mv : nodeMoves(n)) {
                if (activeMoves.contains(mv)) {
                    activeMoves.remove(mv);
                    worklistMoves.add(mv);
                }
            }
        }
    }
    public HashSet<Node> adjacent(Node n) {
        HashSet<Node> neighbours = new HashSet<>(n.adj());
        neighbours.retainAll(coalescedNodes);
        neighbours.retainAll(selectStack); // yes, it does work!
        return neighbours;
    }
    public  void decrementDegree(Node m) {
        int d = degree[m.name()];
        degree[m.name()]--;
        if (d == K) {
            HashSet<Node> par = new HashSet<>(Set.of(m));
            par.addAll(adjacent(m));
            enableMoves(par);
            spillWorklist.remove(m);
            if (moveRelated(m)) {
                freezeWorklist.add(m);
            } else {
                simplifyWorklist.add(m);
            }
        }
    }
    public void simplify() {
        Node n = simplifyWorklist.iterator().next(); // grab any element from the set
        simplifyWorklist.remove(n);
        selectStack.push(n);
        for (Node m: adjacent(n)) {
            decrementDegree(m);
        }
    }
    public Node getAlias(Node n) {
        if (coalescedNodes.contains(n)) {
            getAlias(alias.get(n));
        }
        return n;
    }
    /*public void addWorkList(Node u) {
        if (Codegen.specialRegs.contains()) {

        }
    }*/
    public void coalesce(Liveness ig) {
        MOVE m = worklistMoves.iterator().next(); // grab any element from the set
        Node x = getAlias(ig.tempToNode.get(m.def));
        Node y = getAlias(ig.tempToNode.get(m.use));
        Node u, v;
        // so either you have u, v both precolored, both not precolored, or only the first ie u is precolored.
        // maters for the 2nd and 3rd clause especially later on
        if (Codegen.specialRegs.contains(ig.nodeToTemp.get(y))) {
            u = y;
            v = x;
        } else {
            u = x;
            v = y;
        }
        worklistMoves.remove(m);
        if (u.name() == v.name()) {
            coalescedMoves.add(m);
            //addWorkList(u);
            //addWorkList(v);
        }
    }

    public void main(Vector<Instr> instrs, MEM.Frame frame) {
        // 1. liveness analysis
        AssemFlowGraph cfg = new AssemFlowGraph(instrs);
        Liveness ig = new Liveness(cfg, frame);
        for (Instr instr : instrs) {
            Node n = cfg.instrMap.get(instr);
            System.out.println("{ " + formatTemps(ig.in.get(n), frame) + " }");
            if (instr instanceof LABEL) {
                System.out.println(instr.format(frame)+ "            # def: {" + formatTemps(instr.def() != null ? new HashSet<>(instr.def()) : null, frame) + "}    use: {" + formatTemps(instr.use() != null ? new HashSet<>(instr.use()) : null, frame) + "}      jumps: {" + instr.jumps()+"}");
            } else {
                System.out.println("    " + instr.format(frame) + "               # def: {" + formatTemps(instr.def() != null ? new HashSet<>(instr.def()) : null, frame) + "}    use: {" + formatTemps(instr.use() != null ? new HashSet<>(instr.use()) : null, frame) + "}      jumps: {" + instr.jumps()+"}");
            }
        }
        ig.showWithTemp(frame); // debug print, TBDeleted
        // initialize degree array, moveList, precolored
        degree = new int[ig.nodes().size()];
        for (Node n : ig.nodes()) {
            degree[n.name()] = n.degree() / 2;
            moveList.put(n, new HashSet<>());
            if (Codegen.specialRegs.contains(ig.nodeToTemp.get(n))) {
                precolored.add(n);
                System.out.println("I precolored: " + n.name() + "-"+ig.nodeToTemp.get(n));
            }
        }
        // 2. Build
        for (Instr instr : instrs) {
            if (instr instanceof MOVE mv) {
                System.out.println(instr.format(frame));
                worklistMoves.add(mv);
                //initialize moveList
                for (MEM.Temp t : mv.def()) {
                    HashSet<MOVE> ml;
                    if ((ml = moveList.get(ig.tempToNode.get(t))) != null) // could be RV (or precolored) and then it's null!
                        ml.add(mv);
                }
                for (MEM.Temp t : mv.use()) {
                    HashSet<MOVE> ml;
                    if ((ml = moveList.get(ig.tempToNode.get(t))) != null) // could be RV (or precolored) and then it's null!
                        ml.add(mv);
                }
            }
        }
        // 3. MakeWorklist
        makeWorklist(ig);
        // 4. the loop
        while (!simplifyWorklist.isEmpty() && !worklistMoves.isEmpty() && !freezeWorklist.isEmpty() && !spillWorklist.isEmpty()) {
            if (!simplifyWorklist.isEmpty()) {
                simplify();
            } else if (!worklistMoves.isEmpty()) {
                coalesce(ig);
            } else if (!freezeWorklist.isEmpty()) {
                //freeze();
            } else if (!spillWorklist.isEmpty()) {
                //selectSpill();
            }
        }
        // 5. assigne colors
    }
}
