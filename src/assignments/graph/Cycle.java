package assignments.graph;

import graphs.Graph;
import stack.Stack;

public class Cycle {
    private boolean[] visited;
    private int[] edgTo;
    private Stack<Integer> cycle;

    public Cycle(Graph G) {
        if (hasParallelEdges(G)) return;

        visited = new boolean[G.V()];
        edgTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!visited[v])
                dfs(G, -1, v);
    }

    private boolean hasParallelEdges(Graph G) {
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (visited[w]) {
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(w);
                    cycle.push(v);
                    return true;
                }
                visited[w] = true;
            }
            for (int w : G.adj(v))
                visited[w] = false;
        }
        return false;
    }

    public boolean hasCycle() { return cycle != null; }

    public Iterable<Integer> cycle() { return cycle; }

    private void dfs(Graph G, int u, int v) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;
            if(!visited[w]) {
                edgTo[w] = v;
                dfs(G, v, w);
            } else if (w != u) {
                cycle = new Stack<>();
                for (int x = v; x != w; x = edgTo[x])
                    cycle.push(x);
                cycle.push(w);
                cycle.push(v);
            }
        }
    }

    private boolean hasSelfLoop(Graph G) {
        for (int v = 0; v < G.V(); v++) {
            for (int w : G.adj(v)) {
                if (v == w) {
                    cycle = new Stack<>();
                    cycle.push(v);
                    cycle.push(v);
                    return true;
                }
            }
        }
        return false;
    }
}
