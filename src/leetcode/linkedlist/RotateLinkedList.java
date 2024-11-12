package leetcode.linkedlist;

public class RotateLinkedList {
    private static class Node {
        int val;
        Node next;
    }

    private static Node rotateList(Node head, int k) {
        if (head == null || head.next == null || k == 0) return head;

        Node last = head;
        int count = 1;
        while (last.next != null) {
            last = last.next;
            count++;
        }

        last.next = head;
        k = k % count;
        k = count - k;

        while (k-- > 0)
            last = last.next;

        head = last.next;
        last.next = null;

        return head;
    }

    public static void main(String[] args) {

    }
}
