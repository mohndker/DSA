package graphs;

public class CC {
    private boolean[] visited;
    private int[] id;
    private int[] size;
    private int count;

    public CC(Graph G) {
        visited = new boolean[G.V()];
        id = new int[G.V()];
        size = new int[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) {
                dfs(G, v);
                count++;
            }
        }
    }

    private void dfs(Graph G, int v) {
        visited[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : G.adj(v)) {
            if (!visited[w])
                dfs(G, w);
        }
    }

    public int id(int v) { return id[v]; }

    public int size(int v) { return size[v]; }

    public int count() { return count; }

    public boolean connected(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        return id[v] == id[w];
    }
    private void validateVertex(int v) {
        if (v < 0 || v >= visited.length) throw new IllegalArgumentException();
    }
}
