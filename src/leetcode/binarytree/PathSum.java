package leetcode.binarytree;

public class PathSum {
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

    private static boolean hasPathSum(TreeNode root, int sum) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == sum;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
}
