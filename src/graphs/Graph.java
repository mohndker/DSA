package graphs;

import bag.Bag;
import stack.Stack;

public class Graph {
    private static final String NEWlINE = System.lineSeparator();

    private final int V;
    private int E;
    private Bag<Integer>[] adj;

    public Graph(int vtx) {
        if (vtx < 0) throw new IllegalArgumentException();
        V = vtx;
        E = 0;
        adj = (Bag<Integer>[]) new Bag[vtx];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<>();
    }

    public Graph(Graph g) {
        V = g.V;
        E = g.E;
        if (V < 0) throw new IllegalArgumentException();
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<>();
        }

        for (int v = 0; v < g.V; v++) {
            Stack<Integer> reverse = new Stack<>();
            for (int w : g.adj[v])
                reverse.push(w);

            for(int w : reverse)
                adj[v].add(w);
        }
    }

    public int V() {
        return V;
    }

    public int E() { return E; }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException();
    }

    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }

    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V).append(" vertices, ").append(E).append(" edges ").append(NEWlINE);
        for (int v = 0; v < V; v++) {
            s.append(v).append(": ");
            for (int w : adj[v]) {
                s.append(w).append(" ");
            }
            s.append(NEWlINE);
        }
        return s.toString();
    }

}
