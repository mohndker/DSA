package search;

import com.sun.jdi.Value;
import queue.Queue;

public class LinearProbingHashTable<Key, Value> {
    private static final int INIT_CAPACITY  = 4;

    private int m;
    private int n;
    private Key[] keys;
    private Value[] values;

    public LinearProbingHashTable() {
        this(INIT_CAPACITY);
    }

    public LinearProbingHashTable(int capacity) {
        m = capacity;
        n = 0;
        keys = (Key[]) new Object[m];
        values = (Value[]) new Object[m];
    }

    public int size() { return n; }

    public boolean isEmpty() { return size() == 0; }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException();
        return get(key) != null;
    }

    private int hashTextbook(Key key) {
        return (key.hashCode() & 0x7ffffff) % m;
    }

    private int hash(Key key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h ^ (m - 1);
    }

    private void resize(int capacity) {
        LinearProbingHashTable<Key, Value> temp = new LinearProbingHashTable<>(capacity);
        for (int i = 0; i < m; i++) {
            if (keys[i] != null) {
                temp.put(keys[i], values[i]);
            }
        }
        keys = temp.keys;
        values = temp.values;
        m = temp.m;
    }

    public void put(Key key, Value value) {
        if (key == null)throw new IllegalArgumentException();

        if (value == null) {
            delete(key);
            return;
        }

        if (n >= m / 2) resize(2 * m);

        int i;
        for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
        }
        keys[i] = key;
        values[i] = value;
        n++;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException();
        for (int i = hash(key); keys[i] != null; i = (i + 1) % m) {
            if (keys[i].equals(key))
                return values[i];
        }
        return null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException();
        if (!contains(key)) return;

        int i = hash(key);
        while(!key.equals(keys[i])) {
            i = (i + 1) % m;
        }

        keys[i] = null;
        values[i] = null;

        i = (i + 1) % m;
        while (keys[i] != null) {
            Key keyToRehash = keys[i];
            Value valueToRehash = values[i];
            keys[i] = null;
            values[i] = null;
            n--;
            put(keyToRehash, valueToRehash);
            i = (i + 1) % m;
        }

        n--;

        if (n > 0 && n <= m / 8) resize(m / 2);
    }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (int i = 0; i < m; i++)
            if (keys[i] != null) queue.enqueue(keys[i]);
        return queue;
    }
}
