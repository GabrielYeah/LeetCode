// Given a binary tree, return the inorder traversal of its nodes' values.

// For example:
// Given binary tree {1,#,2,3},
//    1
//     \
//      2
//     /
//    3
// return [1,3,2].

// Note: Recursive solution is trivial, could you do it iteratively?

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Iteractive form is tricky.
public class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        Stack<TreeNode> visit = new Stack<TreeNode>();
        TreeNode p = root;
        // Here use a node p to indicate where we at
        // Previously when I did not use the p, I have no idea
        // whether should I pop a node.
        while (!visit.isEmpty() || p != null) {
            if (p != null) {
                // Should not pop because it has left child
                visit.push(p);
                p = p.left;
            } else {
                // Should pop and process the value
                // Set p to right child because it has higher priority
                // A tricky point here is never set p to nodes that
                // already exist in the stack.
                // P is used to indicate the children nodes of the nodes
                // in the stack!
                TreeNode node = visit.pop();
                result.add(node.val);
                p = node.right;
            }
        }
            
        return result;
    }
}