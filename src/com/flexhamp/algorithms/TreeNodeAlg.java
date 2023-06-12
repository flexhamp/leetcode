package com.flexhamp.algorithms;

import com.flexhamp.util.TreeNode;

public class TreeNodeAlg {
    public static void main(String[] args) {
        final TreeNode treeNode = new TreeNode(1, new TreeNode(4, new TreeNode(2), new TreeNode(3, new TreeNode(2))),
                new TreeNode(7, new TreeNode(5), new TreeNode(4)));

        System.out.println(maxPathSum(treeNode));
    }

    /*
    O(n) - сложность по времени
    O(n) - сложность по памяти

    */
    public static int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int maxLeftPath = maxPathSum(root.left);
        int maxRightPath = maxPathSum(root.right);
        return Math.max(maxLeftPath, maxRightPath) + root.val;
    }

}
