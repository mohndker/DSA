package leetcode.linkedlist;

public class PartitionList {
    private static class Node {
        int val;
        Node next;
    }

    private static Node partitionList(Node head, int x) {
        Node left = new Node();
        Node right = new Node();
        Node leftScan = left;
        Node rightScan = right;

        while (head != null) {
            if (head.val < x) {
                leftScan.next = head;
                leftScan = leftScan.next;
            } else {
                rightScan.next = head;
                rightScan = rightScan.next;
            }
            head = head.next;
        }

        rightScan.next = null;
        leftScan.next = right.next;

        return left.next;
    }

    public static void main(String[] args) {

    }
}
