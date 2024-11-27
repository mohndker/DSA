package leetcode.binarytree;

public class MaxDepthBT {
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
     * Time O(n), n number of nodes.
     * Space O(log(n)) log base 2, height of the tree
     * Use recursive stack as Depth First Search.
     */
    private int maxDepth(TreeNode root) {
        if (root == null) return 0;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {

    }
}
