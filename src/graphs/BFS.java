package graphs;

import queue.Queue;
import stack.Stack;

public class BFS {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] visited;
    private int[] edgTo;
    private int[] disTo;

    public BFS(Graph G, int v) {
        visited = new boolean[G.V()];
        edgTo = new int[G.V()];
        disTo = new int[G.V()];
        validateVertex(v);
        bfs(G, v);
    }

    private void bfs(Graph g, int s) {
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < g.V(); v++)
            disTo[v] = INFINITY;
        disTo[s] = 0;
        visited[s] = true;
        queue.enqueue(s);

        while (!queue.isEmpty()) {
            int v = queue.dequeue();
            for (int w : g.adj(v)) {
                if (!visited[w]) {
                    edgTo[w] = v;
                    disTo[w] = disTo[v] + 1;
                    visited[w] = true;
                    queue.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return visited[v];
    }

    public int disTo(int v) {
        validateVertex(v);
        return disTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        int x;
        for (x = v; disTo[x] != 0; x = edgTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    private void validateVertex(int v) {
        int V = visited.length;
        if (v < 0 || v >= V) throw new IllegalArgumentException();
    }
}
