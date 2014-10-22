// Given a binary tree, return the bottom-up level order traversal of its nodes' values. 
// (ie, from left to right, level by level from leaf to root).

// For example:
// Given binary tree {3,9,20,#,#,15,7},
//     3
//    / \
//   9  20
//     /  \
//    15   7
// return its bottom-up level order traversal as:
// [
//   [15,7],
//   [9,20],
//   [3]
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
// First attempt
public class Solution {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (root == null)
            return result;
        
        Stack stack = new Stack();
        List<TreeNode> nextLevel = new ArrayList<TreeNode>(Arrays.asList(root));
        while (nextLevel.size() > 0) {
            List<TreeNode> currentLevel = nextLevel;
            List<Integer> currentResult = new ArrayList<Integer>();
            nextLevel = new ArrayList<TreeNode>();
            for (TreeNode i : currentLevel) {
                currentResult.add(i.val);
                if (i.left != null)
                    nextLevel.add(i.left);
                if (i.right != null)
                    nextLevel.add(i.right);
            }
            stack.push(currentResult);
        }
        
        while(!stack.empty())
            result.add((List<Integer>)stack.pop());
            
        return result;
    }
}