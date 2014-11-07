// Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.

// You should preserve the original relative order of the nodes in each of the two partitions.

// For example,
// Given 1->4->3->2->5->2 and x = 3,
// return 1->2->2->4->3->5.

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
// Made a mistake by updating curr's next using prev before curr is updated.
public class Solution {
    public ListNode partition(ListNode head, int x) {
        if (head == null)
            return null;
        // Create two lists. One is to store elements less than x,
        // the other one is to store elements larger than or equal to x
        ListNode lhead = new ListNode(0), rhead = new ListNode(0);
        ListNode lprev = lhead, rprev = rhead;
        ListNode curr = head;
        while (curr != null) {
            // Append the element accordingly
            if (curr.val < x) {
                lprev.next = curr;
                lprev = curr;
                curr = curr.next;
                lprev.next = null;
            } else {
                rprev.next = curr;
                rprev = curr;
                curr = curr.next;
                rprev.next = null;
            }
        }
        lprev.next = rhead.next;
        return lhead.next;
    }
}