package leetcode.linkedlist;

public class DeleteDuplicates {
    private static class Node {
        int val;
        Node next;
    }

    private static Node deleteDuplicates(Node head) {
        if (head == null) return head;

        Node first = new Node();
        first.next = head;
        Node prev = first;
        Node curr = head;

        while (curr != null) {

            while (curr.next != null && curr.val == curr.next.val)
                curr = curr.next;

            if (prev.next == curr)
                prev = prev.next;
            else prev.next = curr.next;

            curr = curr.next;
        }

        return first.next;
    }

    public static void main(String[] args) {

    }
}
