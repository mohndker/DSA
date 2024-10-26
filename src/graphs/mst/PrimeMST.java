package graphs.mst;

import graphs.Edge;
import graphs.EdgeWeightedGraph;
import heap.IndexMinPQ;
import queue.Queue;

public class PrimeMST {
    private static final double FLOATING_POINT_EPSILON = 1.0E-12;

    private Edge[] edgeTo;
    private double[] disTo;
    private boolean[] visited;
    private IndexMinPQ<Double> pq;

    public PrimeMST(EdgeWeightedGraph G) {
        edgeTo = new Edge[G.V()];
        disTo = new double[G.V()];
        visited = new boolean[G.V()];
        pq = new IndexMinPQ<>(G.V());
        for (int v = 0; v < G.V(); v++)
            disTo[v] = Double.POSITIVE_INFINITY;

        for (int v = 0; v < G.V(); v++)
            if (!visited[v]) prime(G, v);
    }

    private void prime(EdgeWeightedGraph G, int s) {
        disTo[s] = 0.0;
        pq.insert(s, disTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            scan(G, v);
        }
    }

    private void scan(EdgeWeightedGraph G, int v) {
        visited[v] = true;
        for (Edge e : G.adj(v)) {
            int w = e.other(v);
            if (visited[w]) continue;
            if (e.weight() < disTo[w]) {
                edgeTo[w] = e;
                if (pq.contains(w)) pq.decreaseKey(w, disTo[w]);
                else                pq.insert(w, disTo[w]);
            }
        }
    }

    public Iterable<Edge> edges() {
        Queue<Edge> mst = new Queue<>();
        for (int v = 0; v < edgeTo.length; v++) {
            Edge e = edgeTo[v];
            if (e != null)
                mst.enqueue(e);
        }
        return mst;
    }

    public double weight() {
        double weight = 0.0;
        for (Edge e : edges())
            weight += e.weight();
        return weight;
    }
}
