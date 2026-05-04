package prev26lang.phase.livean;

import prev26lang.common.report.Report;

import java.util.HashSet;


public class Graph {

    int nodecount=0;
    HashSet<Node> mynodes = new HashSet<>();
    public HashSet<Node>  nodes() {
        return mynodes;
    }

    public Node newNode() {
        return new Node(this);
    }
    // ali vozlisce n vsebuje ta graph v svojem atributu
    void check(Node n) {
        if (n.mygraph != this)
            throw new Report.Error("Graph.addEdge using nodes from the wrong graph");
    }

    static boolean inList(Node a, HashSet<Node>  l) {
        if (l.contains(a)) {
            return true;
        }
        return false;
    }

    // ustvari povezavo med from in to
    public void addEdge(Node from, Node to) {
        check(from); check(to);
        // ce je "to" v succ[from], ze obstaja
        if (from.goesTo(to)) return;
        to.preds.add(from);
        from.succs.add(to);
    }
    // odstrani element a iz mnozice l
    HashSet<Node>  delete(Node a, HashSet<Node>  l) {
        HashSet<Node>  d = new HashSet<>(l);
        d.remove(a);
        return d;
    }
    // odstrani povezavo med from in to
    public void rmEdge(Node from, Node to) {
        to.preds.remove(from);
        from.succs.remove(to);
    }

    /**
     * Print a human-readable dump for debugging.
     */
    public void show() {
        for (Node n : nodes()) {
            System.out.print(n.toString() + ": ");
            for (Node s : n.succ()) {
                System.out.print(s.toString() + " ");
            }
            System.out.println();
        }
    }
}