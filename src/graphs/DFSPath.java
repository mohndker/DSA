package graphs;

import stack.Stack;

public class DFSPath {
    private boolean[] visited;
    private int[] edgTo;
    private final int s;

    public DFSPath(Graph G, int v) {
        visited = new boolean[G.V()];
        edgTo = new int[G.V()];
        validateVertex(v);
        s = v;
        dfs(G, v);
    }

    public void dfs(Graph G, int v) {
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                edgTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        validateVertex(v);
        return visited[v];
    }

    public Iterable<Integer> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<>();
        for (int x = v; x != s; x = edgTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    private void validateVertex(int v) {
        int V = visited.length;
        if (v < 0 || v >= V) throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        Graph G = new Graph(10);
        G.addEdge(0, 1);
        G.addEdge(0, 3);
        G.addEdge(1, 5);
        G.addEdge(2, 3);
        G.addEdge(2, 1);
        G.addEdge(4, 6);
        G.addEdge(4, 8);
        G.addEdge(5, 6);
        G.addEdge(7, 9);
        G.addEdge(4, 5);
        G.addEdge(6, 7);
        DFSPath dfs = new DFSPath(G, 3);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                System.out.printf("%d to %d: ", 3, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == 3) System.out.print(x);
                    else System.out.print("-" + x);
                }
                System.out.println();
            } else System.out.printf("%d to %d: not connected\n", 3, v);
        }

    }
}
