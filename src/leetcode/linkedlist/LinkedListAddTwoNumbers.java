package leetcode.linkedlist;

public class LinkedListAddTwoNumbers {
    private static class ListNode {
        int val;
        ListNode next;

        public ListNode() { };

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    private static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        ListNode ans = new ListNode(-1);
        ListNode num1 = l1;
        ListNode num2 = l2;
        int carry = 0;
        int sum = 0;

        while (num1 != null && num2 != null) {
            sum = num1.val + num2.val + carry;
            if (sum < 10) {
                addNewNode(sum, ans);
                carry = 0;
            } else {
                carry = sum / 10;
                sum = sum % 10;
                addNewNode(sum, ans);
            }
            num1 = num1.next;
            num2 = num2.next;
        }

        while (num1 != null) {
            sum = num1.val + carry;
            if (sum < 10) {
                addNewNode(sum, ans);
                carry = 0;
            } else {
                carry = sum / 10;
                sum = sum % 10;
                addNewNode(sum, ans);
            }

            num1 = num1.next;
        }

        while (num2 != null) {
            sum = num2.val + carry;
            if (sum < 10) {
                addNewNode(sum, ans);
                carry = 0;
            } else {
                carry = sum / 10;
                sum = sum % 10;
                addNewNode(sum, ans);
            }

            num2 = num2.next;
        }

        if (carry > 0) addNewNode(carry, ans);

        return ans;
    }

    private static void addNewNode(int num, ListNode node) {
        if (node.val == -1) node.val = num;
        else {
            while (node.next != null) node = node.next;
            ListNode newNode = new ListNode();
            node.next = newNode;
            newNode.val = num;
        }
    }

    private static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode tail = head;

        int sum = 0;
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int l1Digit = (l1 != null) ? l1.val : 0;
            int l2Digit = (l2 != null) ? l2.val : 0;

            sum = l1Digit + l2Digit + carry;
            int digit = sum % 10;
            carry = sum / 10;

            ListNode newNode = new ListNode(digit);
            tail.next = newNode;
            tail = tail.next;

            l1 = (l1 != null) ? l1.next : null;
            l2 = (l2 != null) ? l2.next : null;
        }

        ListNode result = head.next;
        head.next = null;

        return result;
    }

    public static void main(String[] args) {
        ListNode node1 = new ListNode(2);
        node1.next = new ListNode(4);
        node1.next.next = new ListNode(3);

        ListNode node2 = new ListNode(5);
        node2.next = new ListNode(6);
        node2.next.next = new ListNode(4);

        ListNode result = addTwoNumbers(node1, node2);

        System.out.print("[");
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print(", ");
            result = result.next;
        }
        System.out.print("]");
    }
}
