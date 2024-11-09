package leetcode.linkedlist;

public class ReverseLinkedList {
    private static class Node {
        int val;
        Node next;
        public Node() { };
    }

    public static void main(String[] args) {

    }

    private static Node reverseBetween(Node head, int left, int right) {
        if (head == null || left == right) return head;

        Node first = new Node();
        first.next = head;
        Node preLeft = first;

        for (int i = 0; i < left - 1; i++)
            preLeft = preLeft.next;

        Node curr = preLeft.next;

        for (int i = 0; i < right - left; i++) {
            Node afterCurr = curr.next;
            curr.next = afterCurr.next;
            afterCurr.next = preLeft.next;
            preLeft.next = afterCurr;
        }

        return first.next;
    }
}
