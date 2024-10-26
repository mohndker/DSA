package graphs.mst;

import graphs.Edge;
import graphs.EdgeWeightedGraph;
import heap.MinPQ;
import queue.Queue;

public class LazyPrimMST {
    private static final double FLOATING_POINT_EPSILON = 1.0E-12;

    private double weight;
    private Queue<Edge> mst;
    private boolean[] visited;
    private MinPQ<Edge> pq;

    public LazyPrimMST(EdgeWeightedGraph G) {
        mst = new Queue<>();
        pq = new MinPQ<>();
        visited = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++) {
            if (!visited[v]) prim(G, v);
        }
    }

    private void prim(EdgeWeightedGraph G, int s) {
        scan(G, s);
        while (!pq.isEmpty()) {
            Edge e = pq.min();
            int v = e.either(), w = e.other(v);
            assert visited[v] || visited[w];
            if (visited[v] && visited[w]) continue;
            mst.enqueue(e);
            weight += e.weight();
            if (!visited[v]) scan(G, v);
            if (!visited[w]) scan(G, w);
        }
    }

    private void scan(EdgeWeightedGraph G, int v) {
        assert !visited[v];
        visited[v] = true;
        for (Edge e : G.adj(v))
            if (!visited[e.other(v)]) pq.insert(e);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public double weight() {
        return weight;
    }

    public static void main(String[] args) {
    }
}
