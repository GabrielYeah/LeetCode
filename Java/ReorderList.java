// Given a singly linked list L: L0→L1→…→Ln-1→Ln,
// reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…

// You must do this in-place without altering the nodes' values.

// For example,
// Given {1,2,3,4}, reorder it to {1,4,2,3}.

/**
 * Definition for singly-linked list.
 * class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) {
 *         val = x;
 *         next = null;
 *     }
 * }
 */
// Not too hard.
public class Solution {
    public void reorderList(ListNode head) {
        if (head == null || head.next == null || head.next.next == null)
            return;
        // Use fast and slow to find the middle of the linked list
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next; slow = slow.next;
        }
        // Break the link into two
        fast = slow.next;
        slow.next = null;
        
        // Reconstruct the second link by reversing
        ListNode subHead = fast;
        fast = fast.next;
        subHead.next = null;
        while (fast != null) {
            ListNode tmp = fast.next;
            fast.next = subHead;
            subHead = fast;
            fast = tmp;
        }
        
        // Insert the node in second link into first one by one
        slow = head; 
        fast = subHead;
        while (fast != null && slow != null) {
            ListNode tmp1 = slow.next;
            ListNode tmp2 = fast.next;
            slow.next = fast;
            fast.next = tmp1;
            slow = tmp1;
            fast = tmp2;
        }
    }
}