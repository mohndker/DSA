package graphs;

public class DFS {
    private boolean[] visited;
    private int count;

    public DFS(Graph G, int s) {
        visited = new boolean[G.V()];
        validateVertex(s);
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        count ++;
        visited[v] = true;
        for (int w : G.adj(v)) {
            if (!visited[w]) {
                dfs(G, w);
            }
        }
    }

    public boolean visited(int v) {
        validateVertex(v);
        return visited[v];
    }

    public int count() { return count; }


    private void validateVertex(int s) {
        int V = visited.length;
        if (s < 0 || s >= V) throw new IllegalArgumentException();
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
        DFS dfs = new DFS(G, 3);
        for (int v = 0; v < G.V(); v++) {
            if (dfs.visited(v))
                System.out.print(v + " ");
        }
        System.out.println();

        if (dfs.count() != G.V()) System.out.println("Not connected");
        else System.out.println(" Connected");
    }
}
