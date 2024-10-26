package search;

import org.w3c.dom.Node;
import queue.Queue;

public class SequentialST<Key, Value> {

    private Node first;
    private int n;

    private class Node {
        private Key key;
        private Value value;
        private Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public SequentialST() { }

    public void put(Key key, Value value) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        if (value == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = value;
                return;
            }
        }

        first = new Node(key, value, first);
        n++;
    }

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException(" argument is null");
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key))
                return x.value;
        }

        return null;
    }

    public void delete(Key key) {
        if (key == null) throw new IllegalArgumentException("argument is null");
        first = delete(first, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) return null;
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }

        x.next = delete(x.next, key);
        return x;
    }

    public boolean contains(Key key) {
        if (key == null) throw new IllegalArgumentException(" argument is null");
        return get(key) != null;
    }

    public boolean isEmpty() { return size() == 0; }

    public int size() { return n; }

    public Iterable<Key> keys() {
        Queue<Key> queue = new Queue<>();
        for (Node x = first; x != null; x = x.next)
            queue.enqueue(x.key);
        return queue;
    }
}
