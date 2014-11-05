// Given an array where elements are sorted in ascending order, convert it to a height balanced BST.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Just like doing binary search. Divide the array,
// and construct the tree top down.
public class Solution {
    public TreeNode sortedArrayToBST(int[] num) {
        if (num == null || num.length == 0)
            return null;
        return sortedArrayToBST(num, 0, num.length - 1);
    }
    
    private TreeNode sortedArrayToBST(int[] num, int s, int e) {
        if (s > e)
            return null;
        int mid = (s + e) / 2;
        TreeNode curr = new TreeNode(num[mid]);
        TreeNode left = sortedArrayToBST(num, s, mid - 1);
        TreeNode right = sortedArrayToBST(num, mid + 1, e);
        curr.left = left; curr.right = right;
        return curr;
    }
}