// Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.

// For example,
// Given n = 3, your program should return all 5 unique BST's shown below.

//    1         3     3      2      1
//     \       /     /      / \      \
//      3     2     1      1   3      2
//     /     /       \                 \
//    2     1         2                 3

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; left = null; right = null; }
 * }
 */
// Made a big mistake on line 34. Start index was wrong.
public class Solution {
    public List<TreeNode> generateTrees(int n) {
        return generateTrees(1, n);
    }
    
    private List<TreeNode> generateTrees(int s, int e) {
        List<TreeNode> subtrees = new ArrayList<TreeNode>();
        if (s > e) {
            subtrees.add(null);
            return subtrees;
        }
        for (int i = s; i <= e; ++i) {
            List<TreeNode> lefts = generateTrees(s, i - 1);
            List<TreeNode> rights = generateTrees(i + 1, e);
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    subtrees.add(root);
                }
            }
        }
        return subtrees;
    }
}