// Given a singly linked list where elements are sorted in ascending order, convert it to a height balanced BST.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; next = null; }
 * }
 */
/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
// Bottom Up version. A very tricky idea when handling nodes
// according to ascending order. Build the nodes bottom up, 
// and only create a new node when it's time in the ascending order.

// A very important idea here is to set a start/end boundary
// for a sub dfs search.
public class Solution {
    ListNode curr = null;
    public TreeNode sortedListToBST(ListNode head) {
        if (head == null)
            return null;
        curr = head;
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return sortedListToBST(0, len - 1);
    }
    
    private TreeNode sortedListToBST(int s, int e) {
        if (s > e)
            return null;
        int mid = (s + e) / 2;
        TreeNode left = sortedListToBST(s, mid - 1);
        TreeNode root = new TreeNode(curr.val);
        curr = curr.next;
        TreeNode right = sortedListToBST(mid + 1, e);
        root.left = left;
        root.right = right;
        return root;
    }
}