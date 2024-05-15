package stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ResizingArrayStack<T> implements Iterable<T>{
    private static final int INIT_CAPACITY = 8;
    private T[] stack;
    private int n;

    public ResizingArrayStack() {
        stack = (T[]) new Object[INIT_CAPACITY];
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void resize(int capacity) {
        assert capacity >= n;

        T[] stackCopy = (T[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            stackCopy[i] = stack[i];
        }
        stack = stackCopy;
    }

    public T pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        T item = stack[n - 1];
        stack[n - 1] = null;     // to avoid loitering
        n--;
        if (n > 0 && n == stack.length / 4) resize(stack.length / 2);
        return item;
    }

    public void push(T item) {
        if (n == stack.length) resize(2 * stack.length);
        stack[n++] = item;
    }

    public T peek() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow");
        return stack[n - 1];
    }

    public Iterator<T> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<T> {
        private int i;

        public ReverseArrayIterator() {
            i = n - 1;
        }

        public boolean hasNext() {
            return i >= 0;
        }

        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return stack[i--];
        }
    }
}
