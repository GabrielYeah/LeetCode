// Given a list, rotate the list to the right by k places, where k is non-negative.

// For example:
// Given 1->2->3->4->5->NULL and k = 2,
// return 4->5->1->2->3->NULL.

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
    public ListNode rotateRight(ListNode head, int n) {
        if (head == null || n <= 0)
            return head;
        ListNode p = head;
        int len = 1;
        // Get the length of the list
        while (p.next != null) {
            p = p.next;
            len++;
        }
        // Connect the tail to the head
        p.next = head;
        // Iterate to the desired pivot
        n = len - n % len;
        while (n > 0) {
            p = p.next;
            n--;
        }
        head = p.next;
        p.next = null;
        return head;
    }
}