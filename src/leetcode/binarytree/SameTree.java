package leetcode.binarytree;

public class SameTree {
    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {};

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * Time O(n)
     * Space O(min tree node)
     * Use recursive method to compare vals of nodes to left and to right.
     */
    private boolean isSame(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null || p.val != q.val) return false;

        return isSame(p.left, q.left) && isSame(p.right, q.right);
    }
}
