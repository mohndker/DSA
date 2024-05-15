package Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<T> implements Iterable<T>{
    private Node<T> first;
    private Node<T> last;
    private int n;

    private static class Node<T> {
        private T item;
        private Node<T> next;
    }

    public Queue() {
        first = null;
        last = null;
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(T item) {
        Node<T> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return first.item;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        T item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    public Iterator<T> iterator() {
        return new LinkedIterator(first);
    }

    private class LinkedIterator implements Iterator<T> {
        private Node<T> current;

        public LinkedIterator(Node<T> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
    }
}
