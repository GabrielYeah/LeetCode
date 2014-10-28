// Sort a linked list using insertion sort.

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

// Struggled for a while. Sometimes confused with the boundary.
// Should practise more.
public class Solution1 {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fake = new ListNode(Integer.MIN_VALUE);
        fake.next = head;
        ListNode prev = head;
        ListNode curr = head.next;
        while (curr != null) {
            // In this while loop, try to find out the node that needs
            // to be inserted to another position.
            while (curr != null && curr.val >= prev.val) {
                prev = curr; curr = curr.next;
            }
            // If it reaches end, return.
            if (curr == null) {
                return fake.next;
            }
            // Take out the the node.
            prev.next = curr.next;
            curr.next = null;
            // Find the right place to insert the node.
            ListNode lp = fake, lc = fake.next;
            while (lc != null && lc.val <= curr.val) {
                lp = lc; lc = lc.next;
            }
            // Insert the node.
            lp.next = curr;
            curr.next = lc;
            // Iterate to next node.
            curr = prev.next;
        }
        
        return fake.next;
    }
}

// Next time, do not put a while loop which will affect the outer while loop.
// That will make the main logic too complicated.
// Just let the outer loop do the iteration, 
// and use if statement to test certain conditions.
public class Solution {
    public ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode fake = new ListNode(Integer.MIN_VALUE);
        fake.next = head;
        ListNode curr = head;
        while (curr != null && curr.next != null) {
            // If current node's next node is smaller, than it needs
            // to be inserted ahead of the current node.
            if (curr.val > curr.next.val) {
                ListNode prev = fake;
                ListNode small = curr.next;
                // Find a position to insert into.
                while (prev.next.val < small.val)
                    prev = prev.next;
                // Relink the nodes.
                curr.next = small.next;
                small.next = prev.next;
                prev.next = small;
            } else {
                curr = curr.next;
            }
        }
        return fake.next;
    }
}