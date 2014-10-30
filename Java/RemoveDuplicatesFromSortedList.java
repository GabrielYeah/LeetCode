// Given a sorted linked list, delete all duplicates such that each element appear only once.

// For example,
// Given 1->1->2, return 1->2.
// Given 1->1->2->3->3, return 1->2->3.

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
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null)
            return null;
        Set<Integer> existed = new HashSet<Integer>();
        existed.add(head.val);
        ListNode prev = head;
        for (ListNode curr = head.next; curr != null; curr = curr.next) {
            if (existed.contains(curr.val)) {
                prev.next = curr.next;
            } else {
                existed.add(curr.val);
                prev = curr;
            }
        }
        return head;
    }
}