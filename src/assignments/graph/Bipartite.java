package assignments.graph;

import graphs.Graph;
import stack.Stack;

public class Bipartite {
    private boolean isBipartite;
    private boolean[] color;
    private boolean[] visited;
    private int[] edgTo;
    private Stack<Integer> cycle;

    public Bipartite(Graph G) {
        isBipartite = true;
        color = new boolean[G.V()];
        visited = new boolean[G.V()];
        edgTo = new int[G.V()];

        for (int v = 0; v < G.V(); v++) {
            if (!visited[v])
                dfs(G, v);
        }
    }

    private void dfs(Graph G, int v) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (cycle != null) return;
            if (!visited[w]) {
                edgTo[w] = v;
                color[w] = !color[v];
                dfs(G, w);
            } else if (color[w] == color[v]) {
                isBipartite = false;
                cycle = new Stack<>();
                cycle.push(w);
                for (int x = v; x != w; x = edgTo[x])
                    cycle.push(x);
                cycle.push(w);
            }
        }
    }

    private boolean isBipartite() { return isBipartite; }
}
