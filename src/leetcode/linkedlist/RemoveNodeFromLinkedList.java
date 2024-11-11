package leetcode.linkedlist;

public class RemoveNodeFromLinkedList {
    private static class ListNode {
        ListNode next;
    }

    private static ListNode removeNode(ListNode head, int n) {
        // [1, 2, 3, 4, 5, 6, 7] n = 6
        // [1, 2, 3] n = 2
        // [1] n = 1

        ListNode first = head;
        int count = 0;
        while (first != null) {
            count++;
            first = first.next;
        }

        first = head;

        if (count == n) {
            first = head.next;
            head.next = null;   // avoid loitering
            return first;
        }

        for (int i = 1; i < count - n; i++) {
            first = first.next;
        }

        ListNode del = first.next;
        first.next = del.next;
        del.next = null;    //avoid loitering

        return head;
    }

    public static void main(String[] args) {

    }
}
