// Merge k sorted linked lists and return it as one sorted list. Analyze and describe its complexity.

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
// First attempt. TLE.
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        ListNode curr = null;
        for (ListNode l : lists) {
            if (curr == null)
                curr = l;
            else
                curr = mergeLists(curr, l);
        }
        return curr;
    }
    
    private ListNode mergeLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int num2 = l2 == null ? Integer.MAX_VALUE : l2.val;
            if (num1 < num2) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
            curr.next = null;
        }
        return dummy.next;
    }
}

// Second attempt. Aced.
public class Solution {
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.size() == 0)
            return null;
        return mergeSubLists(lists);
    }
    
    private ListNode mergeSubLists(List<ListNode> lists) {
        if (lists.size() == 1)
            return lists.get(0);
        if (lists.size() == 2)
            return mergeLists(lists.get(0), lists.get(1));
        ListNode l1 = mergeSubLists(lists.subList(0, lists.size() / 2));
        ListNode l2 = mergeSubLists(lists.subList(lists.size() / 2, lists.size()));
        return mergeLists(l1, l2);
    }
    
    private ListNode mergeLists(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null)
            return null;
        ListNode dummy = new ListNode(0);
        ListNode curr = dummy;
        while (l1 != null || l2 != null) {
            int num1 = l1 == null ? Integer.MAX_VALUE : l1.val;
            int num2 = l2 == null ? Integer.MAX_VALUE : l2.val;
            if (num1 < num2) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
            curr.next = null;
        }
        return dummy.next;
    }
}