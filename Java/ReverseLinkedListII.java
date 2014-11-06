// Reverse a linked list from position m to n. Do it in-place and in one-pass.

// For example:
// Given 1->2->3->4->5->NULL, m = 2 and n = 4,

// return 1->4->3->2->5->NULL.

// Note:
// Given m, n satisfy the following condition:
// 1 ≤ m ≤ n ≤ length of list.

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
public class Solution {
    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head == null || m >= n)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
        // Find the start node
        for (int i = 1; i < m && curr != null; ++i) {
            prev = curr;
            curr = curr.next;
        }
        // Create a sub list to store the reversed part
        ListNode subtail = curr;
        ListNode subhead = new ListNode(0);
        for (int i = m; i <= n && curr != null; ++i) {
            prev.next = curr.next;
            curr.next = subhead.next;
            subhead.next = curr;
            curr = prev.next;
        }
        // Insert the reversed part to original list
        subtail.next = prev.next;
        prev.next = subhead.next;
        return dummy.next;
    }
}