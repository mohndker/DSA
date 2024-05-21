package Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int INIT_CAPACITY = 8;
    private Item[] queue;
    private Random random;
    private int n;

    public RandomizedQueue() {
        queue = (Item[]) new Object[INIT_CAPACITY];
        random = new Random();
        n = 0;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public void resize(int capacity) {
        assert capacity >= 0;
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i <= n; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    public void enqueue(Item item) {
        if (item == null) throw new NoSuchElementException("null not allowed");
        if (n == queue.length) resize(2 * queue.length);
        queue[n++] = item;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        int index = random.nextInt(n);
        Item item = queue[index];
        queue[index] = queue[--n];
        queue[n] = null;
        if (n > 0 && n == (queue.length / 4)) resize(queue.length / 2);
        return item;
    }

    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException("RandomizedQueue is empty");
        return queue[random.nextInt(n)];
    }

    public Iterator<Item> iterator() {
        return new RandomQueueIterator();
    }

    private class RandomQueueIterator implements Iterator<Item> {
        private int current;
        private final Item[] queueIterator;

        public RandomQueueIterator() {
            current = 0;
            queueIterator = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                queueIterator[i] = queue[i];
            }
            shuffle(queueIterator);
        }

        private void shuffle(Item[] array) {
            for (int i = 0; i < array.length; i++) {
                int index = i + random.nextInt(array.length - i);
                Item item = array[i];
                array[i] = array[index];
                array[index] = item;
            }
        }

        public boolean hasNext() {
            return current < queueIterator.length;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return queueIterator[current++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static void main(String[] args) {
        RandomizedQueue<Integer> rq = new RandomizedQueue<Integer>();
        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);

        System.out.println("Sampled item: " + rq.sample());
        System.out.println("Removed item: " + rq.dequeue());
        System.out.println("Queue size: " + rq.size());

        System.out.println("Items in queue:");
        for (int item : rq) {
            System.out.print(item + " ");
        }
        System.out.println();
    }
}
