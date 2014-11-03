// Two elements of a binary search tree (BST) are swapped by mistake.

// Recover the tree without changing its structure.

// Note:
// A solution using O(n) space is pretty straight forward. Could you devise a constant space solution?

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// When dealing with binary, it's important to relate
// to travesal. Inorder travesal is directly related to
// Binary Search Tree. Key of finding swapped elements is 
// first inorder traverse the tree, and then find the
// pair of values that the prev is larger than the current
public class Solution1 {
    TreeNode node1 = null;
    TreeNode node2 = null;
    TreeNode prev = null;
    public void recoverTree(TreeNode root) {
        inorder(root);
        if (node1 != null && node2 != null) {
            int tmp = node1.val;
            node1.val = node2.val;
            node2.val = tmp;
        }
    }
    
    private void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        if (prev != null) {
            if (prev.val > root.val) {
                if (node1 == null) {
                    // First time meet the pair
                    // Record both nodes because they may
                    // be the target nodes
                    node1 = prev;
                    node2 = root;
                } else {
                    // Misplaced happened again
                    // Only one of the previous recorded 
                    // is misplaced
                    node2 = root;
                    return;
                }
            }
        }
        prev = root;
        inorder(root.right);
    }
}