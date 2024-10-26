package search;

import queue.Queue;

import java.util.Arrays;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class BinaryST<Key extends Comparable<Key>, Value> {

    private static final int INIT_CAPACITY = 2;
    private Key[] keys;
    private Value[] values;
    private int n;

    public BinaryST() {
        this(INIT_CAPACITY);
    }

    public BinaryST(int capacity) {
        keys = (Key[]) new Comparable[capacity];
        values = (Value[]) new Object[capacity];
    }

    public int size() { return n; }

    public boolean isEmpty() { return n == 0; }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        if (isEmpty()) return null;
        int i = rank(key);
        if (i < n && keys[i].compareTo(key) == 0) return values[i];
        return null;
    }

    public int rank(Key key) {
        if (key == null) throw new IllegalArgumentException("argument is null");

        int lo = 0, hi = n - 1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int cmp = key.compareTo(keys[mid]);
            if (cmp < 0) hi = mid - 1;
            else if (cmp > 0) lo = mid + 1;
            else return mid;
        }

        return lo;
    }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("first argument is null");

        if (value == null) {
            delete(key);
            return;
        }

        int i = rank(key);

        if (i < n && keys[i].compareTo(key) == 0) {
            values[i] = value;
            return;
        }

        if (n == keys.length) resize(2 * keys.length);

        for (int j = n; j > i; j--) {
            keys[j] = keys[j - 1];
            values[j] = values[j - 1];
        }
        keys[i] = key;
        values[i] = value;
        n++;

        assert check();
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        if (isEmpty()) return;

        int i = rank(key);

        if (i == n || keys[i].compareTo(key) != 0) {
            return;
        }

        for (int j = i; j < n - 1; j++) {
            keys[j] = keys[j + 1];
            values[j] = values[j + 1];
        }

        n--;
        keys[n] = null;
        values[n] = null;

        if (n > 0 && n == keys.length / 4) resize(keys.length / 2);

        assert check();
    }

    public void deleteMin() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        delete(min());
    }

    public void deleteMax() {
        if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
        delete(max());
    }

    private void resize(int capacity) {
        assert capacity >= n;

        Key[] tempK = (Key[]) new Comparable[capacity];
        Value[] tempV = (Value[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tempK[i] = keys[i];
            tempV[i] = values[i];
        }

        keys = tempK;
        values = tempV;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Table is empty");
        return keys[0];
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("Table is empty");
        return keys[n - 1];
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            throw new NoSuchElementException("argument is invalid");
        }
        return keys[k];
    }

    public Key floor(Key key) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        int i = rank(key);
        if (i == n) throw new NoSuchElementException("argument is too small");
        else return keys[i - 1];
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        int i = rank(key);
        if (i == n) throw new NoSuchElementException("argument is too large");
        else return keys[i];
    }

    public int size(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument is null");
        if (hi == null) throw new IllegalArgumentException("second argument is null");

        if (lo.compareTo(hi) > 0) return 0;
        if (contains(hi)) return rank(hi) - rank(lo) + 1;
        else              return rank(hi) - rank(lo);
    }

    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        if (lo == null) throw new IllegalArgumentException("first argument is null");
        if (hi == null) throw new IllegalArgumentException("second argument is null");

        Queue<Key> queue = new Queue<>();
        if (lo.compareTo(hi) > 0) return queue;
        for (int i = rank(lo); i < rank(hi); i++) {
            queue.enqueue(keys[i]);
        }
        if (contains(hi)) queue.enqueue(keys[rank(hi)]);
        return queue;
    }

    private boolean check() {
        return isSorted() && rankCheck();
    }

    private boolean isSorted() {
        for (int i = 1; i < size(); i++)
            if (keys[i].compareTo(keys[i - 1]) < 0) return false;
        return true;
    }

    private boolean rankCheck() {
        for (int i = 0; i < size(); i++)
            if (i != rank(select(i))) return false;
        for (int i = 0; i < size(); i++)
            if (keys[i].compareTo(select(rank(keys[i]))) != 0) return false;

        return true;
    }

    public static void main(String[] args) {
        int[] nums = {0, 0, 1, 1, 1, 2, 2, 2, 3, 3, 3, 4};


    }
}
