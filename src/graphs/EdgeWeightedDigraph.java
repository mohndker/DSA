package graphs;

import bag.Bag;
import stack.Stack;

public class EdgeWeightedDigraph {
    private static final String NEWLINE = System.lineSeparator();

    private final int V;
    private int E;
    private Bag<DirectedEdge>[] adj;
    private int[] indegree;

    public EdgeWeightedDigraph(int v) {
        if (v < 0) throw new IllegalArgumentException("vertex must be non-negative");
        this.V = v;
        E = 0;
        this.indegree = new int[V];
        adj = (Bag<DirectedEdge>[]) new Bag[V];
        for (int i = 0; i < V; i++)
            adj[i] = new Bag<>();
    }

    public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
        this(G.V());
        this.E = G.E();
        for (int v = 0; v < G.V(); v++)
            this.indegree[v] = G.indegree[v];
        for (int v = 0; v < G.V(); v++) {
            Stack<DirectedEdge> reverse = new Stack<>();
            for (DirectedEdge e : G.adj[v])
                reverse.push(e);
            for (DirectedEdge e : reverse)
                adj[v].add(e);
        }
    }

    public int V() { return V; }

    public int E() { return E; }

    private void validateVertex(int v) {
        if (v < 0 || v >= V) throw new IllegalArgumentException("vertex out of bounds");
    }

    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    public Iterable<DirectedEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int outDegree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public int inDegree(int v) {
        validateVertex(v);
        return indegree[v];
    }

    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<>();
        for (int v = 0; v < V; v++)
            for (DirectedEdge e : adj(v))
                list.add(e);
        return list;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" ").append(E).append(NEWLINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e).append("  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
