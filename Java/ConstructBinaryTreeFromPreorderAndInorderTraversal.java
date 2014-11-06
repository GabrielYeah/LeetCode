// Given preorder and inorder traversal of a tree, construct the binary tree.

// Note:
// You may assume that duplicates do not exist in the tree.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Made a little mistake on the index calculation
public class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null ||
            preorder.length == 0 || preorder.length != inorder.length)
            return null;
        return buildTree(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
    }
    
    private TreeNode buildTree(int[] preorder, int[] inorder, int ps, int pe, int is, int ie) {
        if (is > ie)
            return null;
        int split = preorder[ps], im = is;
        while (inorder[im] != split) { im++; }
        TreeNode root = new TreeNode(split);
        root.left = buildTree(preorder, inorder, ps + 1, ps + im - is, is, im - 1);
        root.right = buildTree(preorder, inorder, ps + im - is + 1, pe, im + 1, ie);
        return root;
    }
}