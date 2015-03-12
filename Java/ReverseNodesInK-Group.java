// Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.

// If the number of nodes is not a multiple of k then left-out nodes in the end should remain as it is.

// You may not alter the values in the nodes, only nodes itself may be changed.

// Only constant memory is allowed.

// For example,
// Given this linked list: 1->2->3->4->5

// For k = 2, you should return: 2->1->4->3->5

// For k = 3, you should return: 3->2->1->4->5

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
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // Slow stands for the last sublist's tail
        // Fast stands for next sublist's head
        ListNode slow = dummy, fast = head;
        while (fast != null) {
            int count = k;
            while (fast != null && count-- > 0)
                fast = fast.next;
            if (count > 0)
                break;
            // curr stores the current node
            // tail stores the current sublist's tail, to link the next sublist
            // tmphead is used for the reversing process
            ListNode curr = slow.next, tail = slow.next, tmphead = new ListNode(0);
            tmphead.next = fast;
            count = k;
            while (count-- > 0) {
                slow.next = curr.next;
                curr.next = tmphead.next;
                tmphead.next = curr;
                curr = slow.next;
            }
            slow.next = tmphead.next;
            slow = tail;
        }
        return dummy.next;
    }
}

// A better and cleaner version. The thought here is to split the basic operations
// into a function, so that it can be easily iterated and reused.
public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null)
            return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy;
        for (int i = 1; head != null; head = head.next, i++) {
            if (i % k == 0) {
                prev = reverse(prev, head.next);
                head = prev;
            }
        }
        return dummy.next;
    }
    
    private ListNode reverse(ListNode prev, ListNode nextHead) {
        ListNode last = prev.next;
        ListNode curr = last.next;
        while (curr != nextHead) {
            last.next = curr.next;
            curr.next = prev.next;
            prev.next = curr;
            curr = last.next;
        }
        return last;
    }
}