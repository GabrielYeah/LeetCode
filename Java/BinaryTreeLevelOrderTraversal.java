// Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

// For example:
// Given binary tree {3,9,20,#,#,15,7},
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its level order traversal as:
// [
//   [3],
//   [9,20],
//   [15,7]
// ]

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

// Quite simple one, AC on first submit
public class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (root == null)
            return results;
        
        List<TreeNode> next = new ArrayList<TreeNode>(Arrays.asList(root));
        while (next.size() > 0) {
            List<Integer> localResult = new ArrayList<Integer>();
            List<TreeNode> current = next;
            next = new ArrayList<TreeNode>();
            for (TreeNode node : current) {
                localResult.add(node.val);
                if (node.left != null)
                    next.add(node.left);
                if (node.right != null)
                    next.add(node.right);
            }
            results.add(localResult);
        }
        
        return results;
    }
}