package priorityqueue;

public class UnsortedArrayPQ<key extends Comparable<key>> {
    private key[] pq;
    private int n;

    public UnsortedArrayPQ(int capacity) {
        pq = (key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void insert(key x) {
        pq[n++] = x;
    }

    public key deleteMax() {
        int max = 0;
        for (int i = 1; i < n; i++)
            if (less(max, i)) max = i;
        exch(max, n - 1);

        return pq[--n];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exch(int i, int j) {
        key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    public static void main(String[] args) {
        UnsortedArrayPQ<String> pq = new UnsortedArrayPQ<>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            System.out.println(pq.deleteMax());
    }
}
