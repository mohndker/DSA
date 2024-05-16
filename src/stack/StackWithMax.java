package stack;

import java.util.NoSuchElementException;

public class StackWithMax<T extends Comparable<T>> {
    private Stack<T> stack;
    private Stack<T> maxStack;
    private int n;

    public StackWithMax() {
        stack = new Stack<>();
        maxStack = new Stack<>();
        n = 0;
    }

    public boolean isEmpty() {
        return stack.isEmpty() && maxStack.isEmpty();
    }

    public int size() {
        return n;
    }

    public void push(T item) {
        stack.push(item);
        if (maxStack.isEmpty() || item.compareTo(maxStack.peek()) >= 0) maxStack.push(item);
        else maxStack.push(maxStack.peek());
        n++;
    }

    public T pop() {
        if (stack.isEmpty()) throw new NoSuchElementException("Stack underflow");
        maxStack.pop();
        T item = stack.pop();
        n--;
        return item;
    }

    public T max() {
        if (maxStack.isEmpty()) throw new NoSuchElementException();
        return maxStack.peek();
    }
}
