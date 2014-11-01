// Given a binary tree, find the maximum path sum.

// The path may start and end at any node in the tree.

// For example:
// Given the below binary tree,

//        1
//       / \
//      2   3
// Return 6.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// The key point is to keep the max value during the progress.
// Because the recursion will reach to the leaf node, then sum up,
// so the max value may have been appeared when recursing up.
// When the max value is recorded, it will not be replaced later.
public class Solution {
    public int maxPathSum(TreeNode root) {
        int[] max = new int[1];
        max[0] = Integer.MIN_VALUE;
        maxPathSum(root, max);
        return max[0];
    }
    
    private int maxPathSum(TreeNode root, int[] max) {
        if (root == null)
            return 0;
        int left = maxPathSum(root.left, max);
        int right = maxPathSum(root.right, max);
        int currentMax = Math.max(root.val, Math.max(root.val + left, root.val + right));
        max[0] = Math.max(max[0], Math.max(currentMax, root.val + left + right));
        return currentMax;
    }
}