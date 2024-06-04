package priorityqueue;

public class SortedArrayPQ<key extends Comparable<key>>{

    private key[] pq;
    private int n;

    public SortedArrayPQ(int capacity) {
        pq = (key[]) new Comparable[capacity];
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public key deleteMax() {
        return pq[--n];
    }

    public void insert(key x) {
        int i = n - 1;
        while (i >= 0 && less(x, pq[i])) {
            pq[i + 1] = pq[i];
            i--;
        }
        pq[i + 1] = x;
        n++;
    }

    private boolean less(key v, key w) {
        return v.compareTo(w) < 0;
    }

    public static void main(String[] args) {
        SortedArrayPQ<String> pq = new SortedArrayPQ<String>(10);
        pq.insert("this");
        pq.insert("is");
        pq.insert("a");
        pq.insert("test");
        while (!pq.isEmpty())
            System.out.println(pq.deleteMax());
    }
}
