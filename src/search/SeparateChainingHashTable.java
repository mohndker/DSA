package search;

import queue.Queue;

import java.awt.*;
import java.util.*;

public class SeparateChainingHashTable<Key, Value> {
    private static final int INIT_CAPACITY = 4;

    private int n;
    private int m;
    private SequentialST<Key, Value>[] st;

    public SeparateChainingHashTable() {
        this(INIT_CAPACITY);
    }

    public SeparateChainingHashTable(int m) {
        this.m = m;
        st = (SequentialST<Key, Value>[]) new SequentialST[m];
        for (int i = 0; i < m; i++)
            st[i] = new SequentialST<>();
    }

    private void resize(int chains) {
        SeparateChainingHashTable<Key, Value> temp = new SeparateChainingHashTable<>(chains);
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                temp.put(key, st[i].get(key));
            }
        }
        this.m = temp.m;
        this.n = temp.n;
        this.st = temp.st;
    }

    private int haskTextbook(Key key) {
        return (key.hashCode() & 0x7ffffff) % m;
    }

    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (m - 1);
    }

    public int size() { return n; }

    public boolean isEmpty() { return size() == 0; }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        int i = hash(key);
        return st[i].get(key);
    }

    public void put(Key key, Value value) {
        if (key == null)throw new IllegalArgumentException();
        if (value == null) {
            delete(key);
            return;
        }

        if (n >= 10*m) resize(2 * m);

        int i = hash(key);
        if (!st[i].contains(key)) n++;
        st[i].put(key, value);
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();

        int i = hash(key);
        if (st[i].contains(key)) n--;
        st[i].delete(key);

        if (m > INIT_CAPACITY && n <= 2 * m) resize(m / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++) {
            for (Key key : st[i].keys()) {
                queue.enqueue(key);
            }
        }
        return queue;
    }
}
