package heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQ<key> implements Iterable<key> {

    private key[] pq;
    private int n;
    private Comparator<key> comparator;

    public MinPQ(int initCapacity) {
        pq = (key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MinPQ() {
        this(1);
    }

    public MinPQ(int initCapacity, Comparator<key> comparator) {
        this(initCapacity);
        this.comparator = comparator;
    }

    public MinPQ(Comparator<key> comparator) {
        this(1, comparator);
    }

    public MinPQ(key[] keys) {
        n = keys.length;
        pq = (key[]) new Object[keys.length + 1];
        for (int i = 0; i <= n; i++) {
            pq[i + 1] = keys[i];
        }
        for (int k = n / 2; k >= 1; k--) {
            sink(k);
        }
        assert isMinHeap();
    }

    public int size() { return n; }

    public boolean isEmpty() { return n == 0; }

    public key min() {
        if (isEmpty()) throw new NoSuchElementException();
        return pq[1];
    }

    public void insert(key x) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    public key delMin() {
        if (isEmpty()) throw new NoSuchElementException();
        key min = pq[1];
        exch(1, n--);
        pq[n + 1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    private void resize(int capacity) {
        assert capacity > n;
        key[] copy = (key[]) new Object[capacity];
        for (int i = 1; i <= n; i++)
            copy[i] = pq[i];
        pq = copy;
    }

    private void swim(int k) {
        while (k > 1 && greater(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) j++;
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMinHeap() {
        for (int i = 0; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n + 1; i <= pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMinHeapOrdered(1);
    }

    private boolean isMinHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && greater(k, left)) return false;
        if (right <= n && greater(k, right)) return false;
        return isMinHeapOrdered(left) && isMinHeapOrdered(right);
    }

    public Iterator<key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<key> {

        private MinPQ<key> copy;

        public HeapIterator() {
            if (comparator == null) copy = new MinPQ<>(size());
            else copy = new MinPQ<>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() { return !copy.isEmpty(); }

        public void remove() { throw new UnsupportedOperationException(); }

        public key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }
}
