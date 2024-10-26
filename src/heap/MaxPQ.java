package heap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MaxPQ<key> implements Iterable<key> {

    private key[] pq;
    private int n;
    private Comparator<key> comparator;

    public MaxPQ(int initCapacity) {
        pq = (key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ() {
        this(1);
    }

    public MaxPQ(int initCapacity, Comparator<key> comparator) {
        this.comparator = comparator;
        pq = (key[]) new Object[initCapacity + 1];
        n = 0;
    }

    public MaxPQ(Comparator<key> comparator) {
        this(1, comparator);
    }

    public MaxPQ(key[] keys) {
        n = keys.length;
        pq = (key[]) new Object[keys.length + 1];
        for (int i = 0; i < n; i++)
            pq[i + 1] = keys[i];
        for (int k = n / 2; k >= 1; k--)
            sink(k);
        assert isMaxHeap();
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public key max() {
        if (isEmpty()) throw new NoSuchElementException();
        return pq[1];
    }

    public void insert(key x) {
        if (n == pq.length - 1) resize(2 * pq.length);
        pq[++n] = x;
        swim(n);
        assert isMaxHeap();
    }

    public key delMax() {
        if (isEmpty()) throw new NoSuchElementException();
        key max = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMaxHeap();
        return max;
    }

    private void resize(int capacity) {
        assert capacity > n;
        key[] temp = (key[]) new Object[capacity];
        for (int i = 1; i <= n; i++)
            temp[i] = pq[i];
        pq = temp;
    }

    private void swim(int k) {
        while (k > 1 && less(k / 2, k)) {
            exch(k / 2, k);
            k = k / 2;
        }
    }

    private void sink(int k) {
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && less(j, j + 1)) j++;
            if (!less(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    private boolean less(int i, int j) {
        if (comparator == null) {
            return ((Comparable<key>) pq[i]).compareTo(pq[j]) < 0;
        } else {
            return comparator.compare(pq[i], pq[j]) < 0;
        }
    }

    private void exch(int i, int j) {
        key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    private boolean isMaxHeap() {
        for (int i = 1; i <= n; i++) {
            if (pq[i] == null) return false;
        }
        for (int i = n + 1; i < pq.length; i++) {
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false;
        return isMaxHeapOrdered(1);
    }

    private boolean isMaxHeapOrdered(int k) {
        if (k > n) return true;
        int left = 2 * k;
        int right = 2 * k + 1;
        if (left <= n && less(k, left)) return false;
        if (right <= n && less(k, right)) return false;
        return isMaxHeapOrdered(left) && isMaxHeapOrdered(right);
    }

    public Iterator<key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<key> {
        private MaxPQ<key> copy;

        public HeapIterator() {
            if (comparator == null) copy = new MaxPQ<>(size());
            else copy = new MaxPQ<>(size(), comparator);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i]);
            }
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() { throw new UnsupportedOperationException(); }

        public key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMax();
        }
    }

    public static void main(String[] args) {
        MaxPQ<Integer> maxPQ = new MaxPQ<>();
        maxPQ.insert(5);
        maxPQ.insert(1);
        maxPQ.insert(15);
        maxPQ.insert(10);
        maxPQ.insert(4);
        System.out.println(maxPQ.delMax());
        System.out.println(maxPQ.max());
    }
}
