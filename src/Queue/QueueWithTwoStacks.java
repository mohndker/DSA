package Queue;

import stack.Stack;

import java.util.NoSuchElementException;

public class QueueWithTwoStacks<T> {
    private Stack<T> stack1;
    private Stack<T> stack2;
    private int n;

    public QueueWithTwoStacks() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
        n = 0;
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return stack1.isEmpty() && stack2.isEmpty();
    }

    public void enqueue(T item) {
        stack1.push(item);
        n++;
    }

    public T dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        if (stack2.isEmpty()) throw new NoSuchElementException("Stack underflow");
        T item = stack2.pop();
        n--;
        return item;
    }

    public T peek() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.peek());
            }
        }
        if (stack2.isEmpty()) throw new NoSuchElementException("Stack underflow");
        return stack2.peek();
    }
}
