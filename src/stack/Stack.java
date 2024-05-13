package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Stack<T> implements Iterable<T> {
    private Node<T> first;
    private int n;

    private static class Node<T> {
        private T item;
        private Node<T> next;
    }

    public Stack() {
        first = null;
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void push(T item) {
        Node<T> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");

        T item = first.item;
        first = first.next;
        n--;

        return item;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");

        return first.item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();

        for (T item : this) {
            s.append(item);
            s.append(' ');
        }

        return s.toString();
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
