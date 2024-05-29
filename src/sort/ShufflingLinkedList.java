package sort;

import java.util.Random;

public class ShufflingLinkedList<Item> {

    private static final Random random = new Random();
    private static class Node<Item> {
        Item item;
        Node<Item> next;

        private Node() { }

        private Node(Item item) {
            this.item = item;
        }
    }

    private Node<Item> shuffle(Node<Item> head) {
        if (head == null || head.next == null) return head;

        Node<Item> mid = getMid(head);
        Node<Item> rightHalf = mid.next;
        mid.next = null;

        Node<Item> left = shuffle(head);
        Node<Item> right = shuffle(rightHalf);

        return merge(left, right);
    }

    private Node<Item> merge(Node<Item> left, Node<Item> right) {
        Node<Item> dummy = new Node<>();
        Node<Item> current = dummy;

        while (left != null && right != null) {
            if (random.nextBoolean()) {
                current.next = left;
                left = left.next;
            } else {
                current.next = right;
                right = right.next;
            }
            current = current.next;
        }

        if (left != null) current.next = left;
        if (right != null) current.next = right;

        return dummy.next;
    }

    private Node<Item> getMid(Node<Item> head) {
        if ( head == null) return head;

        Node<Item> slow = head, fast = head.next;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    private void printList(Node<Item> head) {
        while (head != null) {
            System.out.print(head.item + " ");
            head = head.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ShufflingLinkedList<Integer> list = new ShufflingLinkedList<>();
        Node<Integer> head = new Node<>(1);
        head.next = new Node<>(2);
        head.next.next = new Node<>(3);
        head.next.next.next = new Node<>(4);
        head.next.next.next.next = new Node<>(5);
        head.next.next.next.next.next = new Node<>(6);

        System.out.println("Original List:");
        list.printList(head);

        head = list.shuffle(head);

        System.out.println("Shuffled List:");
        list.printList(head);
    }
}
