package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayQueue<T> implements Iterable<T>{
    private static final int INIT_CAPACITY = 8;

    private T[] queue;
    private int first;
    private int last;
    private int n;

    public ResizingArrayQueue() {
        queue = (T[]) new Object[INIT_CAPACITY];
        n = 0;
        first = 0;
        last = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void resize(int capacity) {
        assert capacity >= 0;
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            copy[i] = queue[(first + i) % queue.length];
        }
        queue = copy;
        first = 0;
        last = n;
    }

    public void enqueue(T item) {
        if (n == queue.length) resize(2 * queue.length);
        queue[last++] = item;
        if (last == queue.length) last = 0;
        n++;
    }

    public T dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        T item = queue[first];
        queue[first] = null;
        n--;
        first++;
        if (first == queue.length) first = 0;
        if (n > 0 && n == queue.length / 4) resize(queue.length / 2);
        return item;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        return queue[first];
    }

    public Iterator<T> iterator() {
        return new ArrayIterator();
    }

    private class ArrayIterator implements Iterator<T> {
        private int i = 0;

        public boolean hasNext() { return i < n; }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T item = queue[(first + i) % queue.length];
            i++;
            return item;
        }
    }
}
