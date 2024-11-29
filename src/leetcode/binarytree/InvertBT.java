package leetcode.binarytree;

public class InvertBT {
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
     *
     */
    private static TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = invertTree(right);
        root.right = invertTree(left);

        return root;
    }
}
