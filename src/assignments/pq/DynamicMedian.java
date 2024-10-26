package assignments.pq;

import heap.MaxPQ;
import heap.MinPQ;

import java.util.NoSuchElementException;

public class DynamicMedian {
    private MaxPQ<Integer> maxPQ;
    private MinPQ<Integer> minPQ;
    private int n;

    public DynamicMedian() {
        maxPQ = new MaxPQ();
        minPQ = new MinPQ();
    }

    public int size() { return n; }

    public boolean isEmpty() { return n == 0; }

    public void insert(int key) {
        if (maxPQ.isEmpty() || key <= maxPQ.max()) maxPQ.insert(key);
        else minPQ.insert(key);
        // Balance the heap if necessary.
        if (maxPQ.size() > minPQ.size() + 1) minPQ.insert(maxPQ.delMax());
        else if (minPQ.size() > maxPQ.size()) maxPQ.insert(minPQ.delMin());
    }

    public int findMedian() {
        if (maxPQ.isEmpty()) throw new NoSuchElementException();
        return maxPQ.max();
    }

    public int removeMedian() {
        if (maxPQ.isEmpty()) throw new NoSuchElementException();
        int median = maxPQ.delMax();

        // Balance the heap if necessary.
        if (minPQ.size() > maxPQ.size()) maxPQ.insert(minPQ.delMin());
        return median;
    }
}
