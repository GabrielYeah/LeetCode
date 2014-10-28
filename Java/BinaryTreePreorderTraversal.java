// Given a binary tree, return the preorder traversal of its nodes' values.

// For example:
// Given binary tree {1,#,2,3},
//    1
//     \
//      2
//     /
//    3
// return [1,2,3].

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
// Recursive solution. Simple.
public class Solution1 {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        search(root, result);
        return result;
    }
    
    public void search(TreeNode root, List<Integer> result) {
        if (root == null)
            return;
        result.add(root.val);
        search(root.left, result);
        search(root.right, result);
    }
}

// Iterative solution, not too hard.
public class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<Integer>();
        if (root == null)
            return result;
        Stack<TreeNode> visit = new Stack<TreeNode>();
        visit.add(root);
        while (!visit.isEmpty()) {
            TreeNode curr = visit.pop();
            result.add(curr.val);
            if (curr.right != null)
                visit.push(curr.right);
            if (curr.left != null)
                visit.push(curr.left);
        }
        return result;
    }
}