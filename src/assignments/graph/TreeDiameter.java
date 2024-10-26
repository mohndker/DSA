package assignments.graph;

import graphs.Graph;
import queue.Queue;

public class TreeDiameter {
    private static final int INFINITY = Integer.MAX_VALUE;

    private boolean[] visited;
    private int[] edgeTo;
    private int[] disTo;

    public TreeDiameter(Graph G, int v) {
        visited = new boolean[G.V()];
        edgeTo = new int[G.V()];
        disTo = new int[G.V()];

        bfs(G, v);
    }

    private void bfs(Graph G, int s) {
        validateVertex(s);
        Queue<Integer> queue = new Queue<>();
        for (int v = 0; v < G.V(); v++)
            disTo[v] = INFINITY;
        disTo[s] = 0;
        visited[s] = true;
        queue.enqueue(s);
        while (!queue.isEmpty()) {
            int vertex = queue.dequeue();
            for (int vtx : G.adj(vertex)) {
                if (!visited[vtx]) {
                    visited[vtx] = true;
                    disTo[vtx] = disTo[vertex] + 1;
                    edgeTo[vtx] = vertex;
                    queue.enqueue(vtx);
                }
            }
        }

    }

    private void validateVertex(int v) {
        if (v < 0 || v >= visited.length) throw new IllegalArgumentException();
    }
}
