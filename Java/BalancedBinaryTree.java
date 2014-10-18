// Given a binary tree, determine if it is height-balanced.

// For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// First attempt
public class Solution1 {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        boolean currentBalanced =  Math.abs(height(root.left) - height(root.right)) < 2;
        return currentBalanced && isBalanced(root.left) && isBalanced(root.right);
    }
    
    public int height(TreeNode root) {
        if (root == null)
            return 0;
        return Math.max(height(root.left) + 1, height(root.right) + 1);
    }
}

// Final version
public class Solution {
    public boolean isBalanced(TreeNode root) {
        if (root == null)
            return true;
        return height(root) >= 0;
    }
    
    // Analyze the passing condition, each of subtree is balanced and height diff < 1.
    // Can use -1 to represent unbalanced because if a subtree is unbalanced, there is
    // no need to calculate the the height of the rest subtrees.
    public int height(TreeNode root) {
        if (root == null)
            return 0;
        int left = height(root.left);
        int right = height(root.right);
        if (left == -1 || right == -1 || Math.abs(left - right) > 1)
            return -1;
        return Math.max(left, right) + 1;
    }
}