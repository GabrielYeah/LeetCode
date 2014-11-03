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

// Use Morris Inorder Binary Tree Traversal to control
// the space complexity in constant
public class Solution {
    public void recoverTree(TreeNode root) {
        TreeNode prev = null, curr = root;
        TreeNode node1 = null, node2 = null;
        TreeNode ahead = null;
        while (curr != null) {
            if (curr.left == null) {
                // When curr's left is null, output and set curr to curr.right
                if (ahead != null && ahead.val > curr.val) {
                    if (node1 == null) {
                        node1 = ahead;
                        node2 = curr;
                    } else {
                        node2 = curr;
                    }
                }
                ahead = curr;
                curr = curr.right;
            } else {
                // Find curr's inorder prev node
                prev = curr.left;
                while (prev.right != null && prev.right != curr)
                    prev = prev.right;
                if (prev.right == null) {
                    // If prev's right is null, set prev's right to curr
                    prev.right = curr;
                    curr = curr.left;
                } else {
                    // If prev's right is curr, set prev's right back to null
                    // Set curr to curr's right
                    if (ahead.val > curr.val) {
                        if (node1 == null) {
                            node1 = ahead;
                            node2 = curr;
                        } else {
                            node2 = curr;
                        }
                    }
                    ahead = curr;
                    prev.right = null;
                    curr = curr.right;
                }
            }
        }
        if (node1 != null && node2 != null) {
            int tmp = node1.val;
            node1.val = node2.val;
            node2.val = tmp;
        }
    }

}