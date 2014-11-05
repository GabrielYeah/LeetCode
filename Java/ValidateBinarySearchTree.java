// Given a binary tree, determine if it is a valid binary search tree (BST).

// Assume a BST is defined as follows:

// The left subtree of a node contains only nodes with keys less than the node's key.
// The right subtree of a node contains only nodes with keys greater than the node's key.
// Both the left and right subtrees must also be binary search trees.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Aced. Simple one.
public class Solution {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Integer.MAX_VALUE, Integer.MIN_VALUE);
    }
    
    private boolean isValidBST(TreeNode root, int max, int min) {
        if (root == null)
            return true;
        if (max <= root.val || min >= root.val)
            return false;
        boolean left = isValidBST(root.left, Math.min(max, root.val), min);
        boolean right = isValidBST(root.right, max, Math.max(min, root.val));
        return left && right;
    }
}