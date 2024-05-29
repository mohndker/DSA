/* *****************************************************************************
 *  Name: Mohanad Kher
 *  Date: 5/16/2024
 *  Description: Deque, is a double ended queue or deque (pronounced "deck")
 *  is a generalization of a stack and a queue that supports adding and removing
 *  items from either the front or the back of the data structure.
 **************************************************************************** */

package queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Dequeue<Item> implements Iterable<Item> {
    private Node<Item> first;
    private Node<Item> last;
    private int n;

    private static class Node<Item> {
        Item item;
        Node<Item> next;
        Node<Item> prev;
    }

    public Dequeue() {
        first = null;
        last = null;
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException("Item null not allowed");
        Node<Item> oldFirst = first;
        first = new Node<>();
        first.item = item;
        first.next = oldFirst;
        if (isEmpty()) last = first;
        else oldFirst.prev = first;
        n++;
    }

    public void addLast(Item item) {
        if (isEmpty()) throw new IllegalArgumentException("Item null not allowed");
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.prev = oldLast;
        if (isEmpty()) first = last;
        else oldLast.next = last;
        n++;
    }

    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;
        else first.prev = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException("Dequeue underflow");
        Item item = last.item;
        last = last.prev;
        n--;
        if (isEmpty()) first = null;
        else last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current;

        public DequeIterator() {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (isEmpty()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    // main for testing
    public static void main(String[] args) {
        Dequeue<Integer> dequeue = new Dequeue<Integer>();

        dequeue.addFirst(1);
        dequeue.addLast(2);
        dequeue.addFirst(0);
        dequeue.addLast(3);

        System.out.println("Size : " + dequeue.size());    // Output: 4

        for (int item : dequeue) {
            System.out.print(item + " ");                // Output: 0 1 2 3
        }
        System.out.println();

        System.out.println("Is empty: " + dequeue.isEmpty());  // Output: false

        System.out.println(dequeue.removeFirst());         // Output: 0
        System.out.println(dequeue.removeLast());          // Output: 3
        System.out.println(dequeue.removeFirst());         // Output: 1
        System.out.println(dequeue.removeLast());          // Output:2

        System.out.println("Is empty: " + dequeue.isEmpty());  // Output: true
    }
}
