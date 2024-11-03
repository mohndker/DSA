package leetcode;

public class MergeTwoSortedLinkedLists {
    private static class ListNode {
        int val;
        ListNode next;
        ListNode() { };
    }

    public static void main(String[] args) {

    }

    private static ListNode recursiveMerge(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;

        if (l1.val < l2.val) {
            l1.next = recursiveMerge(l1.next, l2);
            return l1;
        } else {
            l2.next = recursiveMerge(l1, l2.next);
            return l2;
        }
    }

    private static ListNode merge(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode current = head;

        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        current.next = l1 == null ? l2 : l1;

        return head.next;
    }
}
