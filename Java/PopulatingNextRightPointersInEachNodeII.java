// Follow up for problem "Populating Next Right Pointers in Each Node".

// What if the given tree could be any binary tree? Would your previous solution still work?

// Note:

// You may only use constant extra space.
// For example,
// Given the following binary tree,
//          1
//        /  \
//       2    3
//      / \    \
//     4   5    7
// After calling your function, the tree should look like:
//          1 -> NULL
//        /  \
//       2 -> 3 -> NULL
//      / \    \
//     4-> 5 -> 7 -> NULL

/**
 * Definition for binary tree with next pointer.
 * public class TreeLinkNode {
 *     int val;
 *     TreeLinkNode left, right, next;
 *     TreeLinkNode(int x) { val = x; }
 * }
 */
// Search from right to left, because connections may not be established
public class Solution1 {
    public void connect(TreeLinkNode root) {
        if (root == null || root.left == null && root.right == null)
            return;
        if (root.left != null && root.right != null)
            root.left.next = root.right;
        
        TreeLinkNode cur = root.right != null ? root.right : root.left;
        TreeLinkNode next = root.next;
        while (next != null) {
            if (next.left == null && next.right == null) {
                next = next.next;
                continue;
            }
            cur.next = next.left != null ? next.left : next.right;
            break; 
        }
        
        connect(root.right);
        connect(root.left);
    }
}

// Iterative version. Since previous layer is linked, we can iterate
// the previous layer by next pointer.
public class Solution {
    public void connect(TreeLinkNode root) {
        if (root == null)
            return;
        TreeLinkNode preHead = root;
        while (preHead != null) {
            TreeLinkNode prev = null, head = null;
            TreeLinkNode p = preHead;
            while (p != null) {
                if (p.left != null && p.right != null) {
                    p.left.next = p.right;
                    if (prev == null)
                        head = p.left;
                    else
                        prev.next = p.left;
                    prev = p.right;
                } else if (p.left != null || p.right != null) {
                    TreeLinkNode node = p.left != null ? p.left : p.right;
                    if (prev == null)
                        head = node;
                    else
                        prev.next = node;
                    prev = node;
                }
                p = p.next;
            }
            preHead = head;
        }
    }
}