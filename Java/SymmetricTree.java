// Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).

// For example, this binary tree is symmetric:

//     1
//    / \
//   2   2
//  / \ / \
// 3  4 4  3
// But the following is not:
//     1
//    / \
//   2   2
//    \   \
//    3    3
// Note:
// Bonus points if you could solve it both recursively and iteratively.

/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Recursive way.
public class Solution1 {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        return isSymmetric(root.left, root.right);
    }
    
    boolean isSymmetric(TreeNode left, TreeNode right) {
        if (left == null)
            return right == null;
        if (right == null)
            return left == null;
        if (left.val != right.val)
            return false;
        if (!isSymmetric(left.right, right.left))
            return false;
        if (!isSymmetric(left.left, right.right))
            return false;
        return true;
    }
}

// Iterative way.
// Java's collection can hold null value, so it is simple to 
// handle two queues to match the left tree and right tree
public class Solution {
    public boolean isSymmetric(TreeNode root) {
        if (root == null)
            return true;
        Queue<TreeNode> lq = new LinkedList<TreeNode>();
        Queue<TreeNode> rq = new LinkedList<TreeNode>();
        lq.add(root.left);
        rq.add(root.right);
        while (!lq.isEmpty() && !rq.isEmpty()) {
            TreeNode l = lq.poll();
            TreeNode r = rq.poll();
            if (l == null && r == null)
                continue;
            if ((l != null && r == null) || (l == null && r != null))
                return false;
            if (l.val != r.val)
                return false;
            lq.add(l.left);
            lq.add(l.right);
            rq.add(r.right);
            rq.add(r.left);
        }
        
        return true;
    }
}