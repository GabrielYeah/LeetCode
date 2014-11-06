// Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right, then right to left for the next level and alternate between).

// For example:
// Given binary tree {3,9,20,#,#,15,7},
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its zigzag level order traversal as:
// [
//   [3],
//   [20,9],
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
// Made a little mistake about stack.
public class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;
        Stack<TreeNode> nextLevel = new Stack<TreeNode>();
        nextLevel.push(root);
        boolean leftToRight = false;
        while (!nextLevel.isEmpty()) {
            List<Integer> list = new ArrayList<Integer>();
            Stack<TreeNode> currLevel = nextLevel;
            nextLevel = new Stack<TreeNode>();
            while (!currLevel.isEmpty()) {
                TreeNode node = currLevel.pop();
                list.add(node.val);
                if (leftToRight) {
                    if (node.right != null)
                        nextLevel.push(node.right);
                    if (node.left != null)
                        nextLevel.push(node.left);
                } else {
                    if (node.left != null)
                        nextLevel.push(node.left);
                    if (node.right != null)
                        nextLevel.push(node.right);
                }
            }
            result.add(list);
            leftToRight = !leftToRight;
        }
        return result;
    }
}