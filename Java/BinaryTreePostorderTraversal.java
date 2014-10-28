// Given a binary tree, return the postorder traversal of its nodes' values.

// For example:
// Given binary tree {1,#,2,3},
//    1
//     \
//      2
//     /
//    3
// return [3,2,1].

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
// Using two stacks.
// Add nodes to the first stack in a reversed way
// so that the earliest nodes entering the second stack will be output in the last
public class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        Stack<TreeNode> visit = new Stack<TreeNode>();
        Stack<TreeNode> output = new Stack<TreeNode>();
        visit.add(root);
        while (!visit.isEmpty()) {
            TreeNode p = visit.pop();
            if (p.left != null)
                visit.add(p.left);
            if (p.right != null)
                visit.add(p.right);
            output.push(p);
        }
        while (!output.isEmpty()) {
            TreeNode p = output.pop();
            result.add(p.val);
        }
        return result;
    }
}

// A more straightforward way to solve the problem.
// Have a prev node to indicator the previous visited node.
public class Solution1 {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        Stack<TreeNode> s = new Stack<TreeNode>();
        s.add(root);
        TreeNode prev = null;
        while (!s.isEmpty()) {
            TreeNode curr = s.peek();
            if (prev == null || prev.left == curr || prev.right == curr) {
                // Go down the tree
                // Output if it is leaf node
                if (curr.left != null) {
                    s.push(curr.left);
                } else if (curr.right != null) {
                    s.push(curr.right);
                } else {
                    s.pop();
                    result.add(curr.val);
                }
            } else if (prev == curr.left) {
                // Go up the tree from left
                // Output if there is no child on the right 
                if (curr.right != null) {
                    s.push(curr.right);
                } else {
                    s.pop();
                    result.add(curr.val);
                }
            } else if (prev == curr.right) {
                // Go up the tree from the right
                // Output the current node cuz already going up from right
                s.pop();
                result.add(curr.val);
            }
            prev = curr;
        }
        return result;
    }
}