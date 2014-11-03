// Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.

// An example is the root-to-leaf path 1->2->3 which represents the number 123.

// Find the total sum of all root-to-leaf numbers.

// For example,

//     1
//    / \
//   2   3
// The root-to-leaf path 1->2 represents the number 12.
// The root-to-leaf path 1->3 represents the number 13.

// Return the sum = 12 + 13 = 25.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Aced. Simple DFS. When reached leaf nodes, add current sum to total sum
public class Solution {
    int sum = 0;
    public int sumNumbers(TreeNode root) {
        if (root == null)
            return 0;
        sumNumbers(root, 0);
        return sum;
    }
    
    private void sumNumbers(TreeNode root, int currentSum) {
        currentSum = currentSum * 10 + root.val;
        if (root.left == null && root.right == null)
            sum += currentSum;
        if (root.left != null)
            sumNumbers(root.left, currentSum);
        if (root.right != null)
            sumNumbers(root.right, currentSum);
    }
}