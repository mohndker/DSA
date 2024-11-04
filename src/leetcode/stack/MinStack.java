package leetcode.stack;

public class MinStack {
    private Node first;

    public MinStack() { };

    public void push(int val) {
        if (first == null)
            first = new Node(val, val, null);
        else first = new Node(val, Math.min(val, first.min), first);
    }

    public void pop() {
        first = first.next;
        char c = '4';
        Integer.parseInt(String.valueOf(c));
    }

    public int top() {
        return first.item;
    }

    public int getMin() {
        return first.min;
    }

    private static class Node {
        private int item;
        private int min;
        private Node next;

        public Node(int val, int min, Node nex) {
            this.min = min;
            this.item = val;
            this.next = nex;
        }
    }
}
