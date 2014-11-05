// Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.

// For example:
// Given the below binary tree and sum = 22,
//               5
//              / \
//             4   8
//            /   / \
//           11  13  4
//          /  \    / \
//         7    2  5   1
// return
// [
//    [5,4,11,2],
//    [5,8,4,5]
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
// DFS and store progress.
public class Solution {
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (root == null)
            return results;
        pathSum(root, sum, results, new ArrayList<Integer>(Arrays.asList(root.val)));
        return results;
    }
    
    private void pathSum(TreeNode root, int sum, List<List<Integer>> results, List<Integer> path) {
        if (root.left == null && root.right == null) {
            if (root.val == sum)
                results.add(new ArrayList<Integer>(path));
            return;
        }
        if (root.left != null) {
            path.add(root.left.val);
            pathSum(root.left, sum - root.val, results, path);
            path.remove(path.size() - 1);
        }
        if (root.right != null) {
            path.add(root.right.val);
            pathSum(root.right, sum - root.val, results, path);
            path.remove(path.size() - 1);
        }
    }
}