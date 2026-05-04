package prev26lang.phase.livean;

import java.util.HashSet;

public class Node {
    Graph mygraph;
    private Node(){}
    int mykey;
    public Node(Graph g) {
        mygraph=g;
        mykey= g.nodecount++;
        g.mynodes.add(this);
    }
    HashSet<Node> succs = new HashSet<Node>();
    HashSet<Node> preds = new HashSet<Node>();
    public HashSet<Node> succ() {
        return succs;
    }
    public HashSet<Node> pred() {
        return preds;
    }

    HashSet<Node>  cat(HashSet<Node>  a, HashSet<Node>  b) {
        HashSet<Node> combined = new HashSet<>(a);
        combined.addAll(b);
        return combined;
    }

    public HashSet<Node>  adj() {
        HashSet<Node> combined = new HashSet<>(succ());
        combined.addAll(pred());
        return combined;
    }

    int len(HashSet<Node> l) {
        return l.size();
    }

    public int inDegree() {
        return len(pred());
    }
    public int outDegree() {
        return len(succ());
    }
    public int degree() {
        return inDegree()+outDegree();
    }
    // if n is succ of current node (this)
    public boolean goesTo(Node n) {
        return Graph.inList(n, succ());
    }
    // if n is a pred of current node (this)
    public boolean comesFrom(Node n) {
        return Graph.inList(n, pred());
    }

    public boolean adj(Node n) {
        return goesTo(n) || comesFrom(n);
    }

    public String toString() {
        return String.valueOf(mykey);
    }
}