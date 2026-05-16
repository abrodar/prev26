package prev26lang.phase.regall;

import prev26lang.phase.asmgen.*;
import prev26lang.phase.livean.AssemFlowGraph;
import prev26lang.phase.livean.Liveness;
import prev26lang.phase.livean.Node;
import prev26lang.phase.memory.MEM;

import java.util.*;

import static prev26lang.Compiler.formatTemps;

public class Regall {

    public static LinkedHashSet<String> allRegs = new LinkedHashSet<>(List.of("x5", "x6", "x7", "x9", "x11",
            "x12", "x13", "x14", "x15", "x16", "x17", "x18", "x19", "x20", "x21", "x22", "x23", "x24", "x25", "x26", "x27", "x28", "x29", "x30", "x31")); // that can be colors
    public static LinkedHashSet<String> okColors = new LinkedHashSet<>(); // initially empty bec it gets set depending on K, which can vary due to user flag num-regs
    public static int K = 25; // number of phy. regs for coloring; can be overwritten by the following flag: --num-regs=K'
    MEM.Frame frame;
    // Node work-lists, sets and stacks. The following lists and sets are always disjoint and every node is always in
    // exactly one of the sets or lists.
    HashSet<Node> precolored = new HashSet<>(); // machine registers, preassigned a color
    //HashSet<Node> initial = new HashSet<>(); // temporary registers, not precolored and not yet processed
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
    HashSet<MOVE> activeMoves = new HashSet<>(); // moves not yet ready for coalescing; na zacetku prazen, ker so vsi v worklistMoves, vendar se polni ko v coalesce() ugotovis da ga se ni mozno zdruzit
    // Other data-structures
    //adjSet
    //adjList
    int degree[]; // an array containing the current degree of each node
    HashMap<Node, HashSet<MOVE>> moveList = new HashMap<>(); // a mapping from a node to the list of moves it is associated with.
    HashMap<Node, Node> alias = new HashMap<>(); // when a move (u, v) has been coalesced, and v put in coalescedNodes, then alias(v) = u.
    HashMap<Node, String> color = new HashMap<>(); // the color chosen by the algorithm for a node; for a precolored nodes this is initialized to the given color
    HashMap<MEM.Temp, Long> spilledTemps = new HashMap<>(); //offset on the stack

    private void moveSetPrint(HashSet<MOVE> mvs) {
        for (MOVE mv : mvs) {
            System.out.print(mv.format(frame)+" ");
        }
        System.out.println();
    }
    public HashSet<String> usedPhysRegs() {
        HashSet<String> used = new HashSet<>();
        for (String c : color.values()) {
            if (allRegs.contains(c)) {
                used.add(c);
            }
        }
        return used;
    }
    public void status() {
        System.out.println("");
        System.out.println("===================== DEBUG PRINT: =====================");
        System.out.println("Current stack: " + selectStack);
        System.out.println("Degree: ");
        for (int i = 0; i < degree.length; i++) {
            System.out.print(degree[i] + " ");
        }
        System.out.println();
        System.out.println("spillWorklist: "+spillWorklist);
        System.out.println("freezeWorklist: "+freezeWorklist);
        System.out.println("simplifyWorklist: "+simplifyWorklist);
        System.out.println("spilledNodes: "+spilledNodes);
        System.out.println("coalescedNodes: "+coalescedNodes);

        System.out.print("coalescedMoves: ");
        moveSetPrint(coalescedMoves);
        System.out.print("constrainedMoves: ");
        moveSetPrint(constrainedMoves);
        System.out.print("activeMoves: ");
        moveSetPrint(activeMoves);
        System.out.println("worklistMoves: ");
        moveSetPrint(worklistMoves);
        System.out.println("alias: ");
        for (Node key : alias.keySet()) {
            System.out.println(key+ " = "+getAlias(key));
        }
    }
    private static Vector<MEM.Temp> toVector(MEM.Temp head, Vector<MEM.Temp> tail) {
        Vector<MEM.Temp> v = new Vector<>();
        v.add(head);
        v.addAll(tail);
        return v;
    }
    private static  <T> Vector<T> toVector(T... args) {
        Vector<T> v = new Vector<>();
        for (T t : args) v.add(t);
        return v;
    }
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
        for (Node node : ig.nodes()) {// initial - temp regs not precolored and not yet processed
            if (!precolored.contains(node)) {
                int deg = degree[node.name()];
                if (deg >= K) {
                    spillWorklist.add(node);
                } else if (moveRelated(node)) {
                    freezeWorklist.add(node);
                } else {
                    simplifyWorklist.add(node);
                }
            }
        }/*
        System.out.println("Initial 3 worklist setup: ");
        System.out.println("spillWorklist: " + spillWorklist);
        System.out.println("freezeWorklist: " + freezeWorklist);
        System.out.println("simplifyWorklist: " + simplifyWorklist);*/
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
        neighbours.removeAll(coalescedNodes);
        neighbours.removeAll(selectStack); // yes, it does work!
        return neighbours;
    }
    public  void decrementDegree(Node m) {
        int d = degree[m.name()];
        degree[m.name()]--;
        if (d == K) { // moves that wwere blocked with too many sign. deg. neigh. might now be enabled for coalescing when m went from sign. to insign. deg.!
            HashSet<Node> par = new HashSet<>(Set.of(m));
            par.addAll(adjacent(m));
            enableMoves(par); // therefore they are added to worklist
            if (!precolored.contains(m)) {
                spillWorklist.remove(m);
                if (moveRelated(m)) {
                    freezeWorklist.add(m);
                } else {
                    simplifyWorklist.add(m);
                }
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
            return getAlias(alias.get(n));
        }
        return n;
    }
    public void addWorkList(Node u) {
        if (!precolored.contains(u) && !moveRelated(u) && degree[u.name()] < K) {
            freezeWorklist.remove(u);
            simplifyWorklist.add(u);
        }
    }
    public boolean georgeCheck(Node u, Node v) {
        for (Node t : adjacent(v)) {
            if (degree[t.name()] >= K && !precolored.contains(t) && !t.adj(u)) {
                return false;
            }
        }
        return true;
    }
    public boolean conservative(HashSet<Node> nodes) {
        int k = 0;
        for (Node n : nodes) {
            if (degree[n.name()] >= K) {
                k++;
            }
        }
        return k < K;
    }
    public void combine(Liveness ig, Node u, Node v) {
        if (freezeWorklist.contains(v)) { // ker v postane irelevanten ko ga "zdruzis" v u (kjer na koncu ostane le u)
            freezeWorklist.remove(v);
        } else {
            spillWorklist.remove(v);
        }
        coalescedNodes.add(v);
        alias.put(v, u);
        HashSet<MOVE> uv_ml = moveList.get(u);
        HashSet<MOVE> v_ml = moveList.get(v);
        uv_ml.addAll(v_ml);
        moveList.put(u, uv_ml); // vse move-e od v dodaj movelist od u (oz. uv tj. novi node!)
        enableMoves(new HashSet<>(Set.of(v))); // vse activeMoves od "v" dodaj v worklist, ker so potencialno coalesceble
        for (Node t : adjacent(v)) {
            boolean isNew = !t.adj(u);
            ig.addEdge(t, u);
            ig.addEdge(u, t);   // interference graph is undirected
            if (isNew) {
                if (!precolored.contains(t))  // bec precolored nodes have inf. degree and it would mess up decrementDeg etc otherwise
                    degree[t.name()]++; // gains a new neighbour u
                if (!precolored.contains(u))
                    degree[u.name()]++; // gains a new neighbour t
            }
            decrementDegree(t); // t loses v as a neighbor
        }
        if (degree[u.name()] >= K && freezeWorklist.contains(u)) {
            freezeWorklist.remove(u);
            spillWorklist.add(u);
        }
    }
    public void coalesce(Liveness ig) {
        MOVE m = worklistMoves.iterator().next(); // grab any element from the set
        Node x = getAlias(ig.tempToNode.get(m.def));
        Node y = getAlias(ig.tempToNode.get(m.use));
        Node u, v;
        // so either you have u, v both precolored, both not precolored, or only the first, ie u is precolored.
        // maters for the 2nd and 3rd clause especially later on
        if (precolored.contains(y)) {
            u = y;
            v = x;
        } else {
            u = x;
            v = y;
        }
        worklistMoves.remove(m);
        //get neighbours of a joined node; for a param in conservative()
        HashSet<Node> joinedAdj = new HashSet<>(adjacent(u));
        joinedAdj.addAll(adjacent(v));
        if (u.name() == v.name()) {
            coalescedMoves.add(m);
            addWorkList(u);
        } else if (precolored.contains(v) || u.adj(v)) { // scenarij ko oba u in v precolored (zagotovo ce ta clause true po delu u el. precolored !)
            constrainedMoves.add(m);
            addWorkList(u);
            addWorkList(v);
        } else if (precolored.contains(u) && georgeCheck(u, v) || !precolored.contains(u) && conservative(joinedAdj)) {
            coalescedMoves.add(m);
            combine(ig, u, v);
            addWorkList(u);
        } else {
            activeMoves.add(m);
        }
    }
    public void freezeMoves(Liveness ig, Node u) {
        for (MOVE m : nodeMoves(u)) {
            Node v;
            if (getAlias(ig.tempToNode.get(m.def)) == getAlias(u)) {
                v = getAlias(ig.tempToNode.get(m.use));
            } else {
                v = getAlias(ig.tempToNode.get(m.def));
            }
            activeMoves.remove(m);  // m is guaranted to be in activeMoves, bec its a intersection of u's moves with (activeMoves and worklistMoves={})
            frozenMoves.add(m);
            // check if the other node in mv is also to be simplified
            if (!precolored.contains(v) && nodeMoves(v).isEmpty() && degree[v.name()] < K) {
                freezeWorklist.remove(v);
                simplifyWorklist.add(v);
            }
        }
    }

    public void freeze(Liveness ig) {
        Node u = freezeWorklist.iterator().next();
        freezeWorklist.remove(u);
        simplifyWorklist.add(u);
        freezeMoves(ig, u);
    }
    public void selectSpill(Liveness ig) {
        Node m = spillWorklist.iterator().next();
        spillWorklist.remove(m);
        simplifyWorklist.add(m);
        freezeMoves(ig, m);
    }
    public void assignColors() {
        while (!selectStack.isEmpty()) {
            Node n = selectStack.pop();
            HashSet<String> candidateColors = new HashSet<>(okColors);
            for (Node w : n.adj()) {
                HashSet<Node> union = new HashSet<>(coloredNodes);
                union.addAll(precolored);
                if (union.contains(getAlias(w))) {
                    candidateColors.remove(color.get(getAlias(w)));
                }
            }
            if (candidateColors.isEmpty()) {
                spilledNodes.add(n);
            } else {
                coloredNodes.add(n);
                String c = candidateColors.iterator().next();
                color.put(n, c);
            }
        }
        for (Node n : coalescedNodes) {
            color.put(n, color.get(getAlias(n)));
        }
    }
    public long newSpillOffset() {
        frame.tempsSize += 8;
        return -(frame.tempsSize+ frame.locsSize + 8 + 8); // + 8 +8 za return address in star fp, ki se v klicnem zapisu nahaja med locs in temps vmes!
    }
    public Vector<Instr> rewriteProgram(Liveness ig, Vector<Instr> instrs) {
        HashSet<Node> newTemps = new HashSet<>();
        // 1. allocate memory locations for each spilled node
        for (Node v : spilledNodes) {
            spilledTemps.put(ig.nodeToTemp.get(v), newSpillOffset());
        }
        Vector<Instr> newInstrs = new Vector<>();
        for (Instr instr : instrs) {
            //2. a fetch before each use
            Vector<MEM.Temp> use = instr.use();
            if (use != null) {
                for (int i = 0; i < use.size(); i++) {
                    Node use_node = ig.tempToNode.get(use.get(i));
                    if (spilledNodes.contains(use_node)) {
                        MEM.Temp newTemp = new MEM.Temp();
                        newTemps.add(ig.tnode(newTemp));
                        Instr loadInstr = new OPER("ld `d0, "+spilledTemps.get(use.get(i))+"(`s0)", toVector(Codegen.FP), toVector(newTemp));
                        use.set(i, newTemp);
                        newInstrs.add(loadInstr);
                    }
                }
            }
            newInstrs.add(instr);
            // 3. a store after each definition
            Vector<MEM.Temp> def = instr.def();
            if (def != null) {
                for (int i = 0; i < def.size(); i++) {
                    Node def_node = ig.tempToNode.get(def.get(i));
                    if (spilledNodes.contains(def_node)) {
                        MEM.Temp newTemp = new MEM.Temp();
                        newTemps.add(ig.tnode(newTemp));
                        Instr storInstr = new OPER("sd `s0, "+spilledTemps.get(def.get(i))+"(`s1)", toVector(newTemp, Codegen.FP), null);
                        def.set(i, newTemp);
                        newInstrs.add(storInstr);
                    }
                }
            }
        }
        // 4. put all the new temps into their own set newTemps
        spilledNodes.clear();
        HashSet<Node> union = new  HashSet<>(coloredNodes);
        //union.addAll(coalescedNodes);
        //union.addAll(newTemps);
        //initial = union;
        coloredNodes.clear();
        coalescedNodes.clear();
        return newInstrs;
    }
    public Vector<String> main(Vector<Instr> instrs, MEM.Frame frame) {
        // to be handled in constructor
        this.frame = frame;
        // Reset all state for this round
        precolored.clear();
        //initial.clear();
        simplifyWorklist.clear();
        freezeWorklist.clear();
        spillWorklist.clear();
        spilledNodes.clear();
        coalescedNodes.clear();
        coloredNodes.clear();
        selectStack.clear();
        coalescedMoves.clear();
        constrainedMoves.clear();
        frozenMoves.clear();
        worklistMoves.clear();
        activeMoves.clear();
        moveList.clear();
        alias.clear();
        color.clear();
        // spilledTemps intentionally NOT cleared — offsets accumulate across rounds
        // 1. liveness analysis
        //build the cfg
        AssemFlowGraph cfg = new AssemFlowGraph(instrs);
        // build the ig
        Liveness ig = new Liveness(cfg, frame);
        /*
        for (Instr instr : instrs) {
            Node n = cfg.instrMap.get(instr);
            System.out.println("{ " + formatTemps(ig.in.get(n), frame) + " }");
            if (instr instanceof LABEL) {
                System.out.println(instr.format(frame)+ "            # def: {" + formatTemps(instr.def() != null ? new HashSet<>(instr.def()) : null, frame) + "}    use: {" + formatTemps(instr.use() != null ? new HashSet<>(instr.use()) : null, frame) + "}      jumps: {" + instr.jumps()+"}");
            } else {
                System.out.println("    " + instr.format(frame) + "               # def: {" + formatTemps(instr.def() != null ? new HashSet<>(instr.def()) : null, frame) + "}    use: {" + formatTemps(instr.use() != null ? new HashSet<>(instr.use()) : null, frame) + "}      jumps: {" + instr.jumps()+"}");
            }
        }*/
        //ig.showWithTemp(frame); // debug print, TBDeleted
        // initialize degree array, moveList(with empty hs), precolored
        degree = new int[ig.nodes().size()];
        for (Node n : ig.nodes()) {
            degree[n.name()] = n.degree() / 2;
            moveList.put(n, new HashSet<>());
            MEM.Temp temp_n = ig.nodeToTemp.get(n);
            if (Codegen.specialRegs.contains(temp_n) || temp_n == frame.FP || temp_n == frame.RV) {
                precolored.add(n);
                String precolor = frame.tempMap(temp_n);
                if (precolor != null) {
                    color.put(n,  precolor);
                }
                //System.out.println("I precolored: " + n.name() + "-"+ig.nodeToTemp.get(n) + " with "+color.get(n));
            }
        }
        /*System.out.println("Degree array current state: ");
        for (int i = 0; i < degree.length; i++) {
            System.out.print(degree[i] + " ");
        }
        System.out.println();*/
        // 2. Build, since ig is already created, we just add move related nodes to worklistmoves and movelist map
        for (Instr instr : instrs) {
            if (instr instanceof MOVE mv) {
                worklistMoves.add(mv);
                //initialize moveList
                for (MEM.Temp t : mv.def()) {
                    HashSet<MOVE> ml;
                    if ((ml = moveList.get(ig.tempToNode.get(t))) != null)
                        ml.add(mv);
                }
                for (MEM.Temp t : mv.use()) {
                    HashSet<MOVE> ml;
                    if ((ml = moveList.get(ig.tempToNode.get(t))) != null)
                        ml.add(mv);
                }
            }
        }/*
        System.out.println("worklistMoves: ");
        moveSetPrint(worklistMoves);
        System.out.println();
        System.out.println("MoveList current state: ");
        for (Node key : moveList.keySet()) {
            System.out.print(key.name()+ " mv's:  ");
            for (MOVE m : moveList.get(key)) {
                System.out.print(m.format(frame) + ",   ");
            }
            System.out.println();
        }*/
        // 3. MakeWorklist
        makeWorklist(ig);
        // 4. the loop

        while (!simplifyWorklist.isEmpty() || !worklistMoves.isEmpty() || !freezeWorklist.isEmpty() || !spillWorklist.isEmpty()) {
            //status();
            if (!simplifyWorklist.isEmpty()) {
                simplify();
            } else if (!worklistMoves.isEmpty()) {
                coalesce(ig);
            } else if (!freezeWorklist.isEmpty()) {
                freeze(ig);
            } else if (!spillWorklist.isEmpty()) {
                selectSpill(ig);
            }
        }
        //status();
        // 5. assigned colors
        assignColors();
        if (!spilledNodes.isEmpty()) {
            instrs = rewriteProgram(ig, instrs);
            main(instrs, frame);
        }

        //final print
        /*
        System.out.println("Apparently colored? ");
        for (var key : color.keySet()) {
            System.out.println(key.name()+ " -> " +color.get(key));
        }*/
        Vector<String> result = new Vector<>();
        for (Instr instr : instrs) {
            //System.out.println(instr.formatWithColor(frame, color, ig));
            result.add(instr.formatWithColor(frame, color, ig));
        }
        return result;
    }
}
