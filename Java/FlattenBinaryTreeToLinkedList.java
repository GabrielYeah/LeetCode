// Given a binary tree, flatten it to a linked list in-place.

// For example,
// Given

//          1
//         / \
//        2   5
//       / \   \
//      3   4   6
// The flattened tree should look like:
//    1
//     \
//      2
//       \
//        3
//         \
//          4
//           \
//            5
//             \
//              6
// click to show hints.

// Hints:
// If you notice carefully in the flattened tree, each node's right child points to the next node of a pre-order traversal.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// First attempt. TLE.
public class Solution {
    public void flatten(TreeNode root) {
        toList(root);
    }
    
    private TreeNode toList(TreeNode root) {
        if (root == null)
            return null;
        TreeNode rhead = toList(root.right);
        if (root.left != null) {
            TreeNode lhead = toList(root.left);
            TreeNode p = lhead;
            while (p.right != null)
                p = p.right;
            p.right = rhead;
            root.right = lhead;
        }
        return root;
    }   
}

// Second attempt. Get accepted. Can be improved.
public class Solution {
    public void flatten(TreeNode root) {
        if (root == null)
            return;
        toList(root);
    }
    
    private TreeNode toList(TreeNode root) {
        if (root.left == null && root.right == null)
            return root;
        TreeNode rtail = null, ltail = null;
        // Get the tails of flattened left subtree and right subtree
        if (root.right != null)
            rtail = toList(root.right);
        if (root.left != null)
            ltail = toList(root.left);
        // If the left tail is not null, update right subtree to left tail
        if (ltail != null) {
            ltail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return rtail != null ? rtail : ltail;
    }
    
}