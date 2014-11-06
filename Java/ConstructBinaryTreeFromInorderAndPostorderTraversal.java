Given inorder and postorder traversal of a tree, construct the binary tree.

Note:
You may assume that duplicates do not exist in the tree.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Aced.
public class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (inorder == null || postorder == null || 
            inorder.length == 0 || inorder.length != postorder.length)
            return null;
        return buildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
    }
    
    private TreeNode buildTree(int[] inorder, int[] postorder, int is, int ie, int ps, int pe) {
        if (is > ie)
            return null;
        int split = postorder[pe], im = is;
        while (inorder[im] != split) { im++; }
        TreeNode root = new TreeNode(split);
        root.left = buildTree(inorder, postorder, is, im - 1, ps, ps + im - is - 1);
        root.right = buildTree(inorder, postorder, im + 1, ie, ps + im - is, pe - 1);
        return root;
    }
}