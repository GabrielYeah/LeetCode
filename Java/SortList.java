// Sort a linked list in O(n log n) time using constant space complexity.

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
// First attempt
// Divide and Conquer
// The basic idea is to calculate the length of current level list,
// get the first half and second half, execute the recursive call, 
// get and combine the sorted sub parts.
public class Solution {
    public ListNode sortList(ListNode head) {
        return divideAndSort(head, size(head));
    }
    
    public ListNode divideAndSort(ListNode head, int length) {
        if (length < 1)
            return null;
        if (length == 1)
            return head;
        ListNode subHead = divideList(head, length / 2);
        ListNode a = divideAndSort(head, length / 2);
        ListNode b = divideAndSort(subHead, length - length / 2);
        ListNode result = new ListNode(0);
        ListNode current = result;
        while (a != null && b != null) {
            if (a.val < b.val) {
                current.next = a;
                a = a.next;
            } else {
                current.next = b;
                b = b.next;
            }
            current = current.next;
            current.next = null;
        }
        if (a != null) current.next = a;
        if (b != null) current.next = b;
        
        return result.next;
    }
    
    public int size(ListNode head) {
        if (head == null)
            return 0;
        int count = 1;
        ListNode current = head.next;
        while (current != null) {
            count++;
            current = current.next;
        }
        return count;
    }
    
    public ListNode divideList(ListNode head, int length) {
        if (head == null)
            return null;
        ListNode subHead = head;
        for (int i = 0; i < length - 1; ++i)
            subHead = subHead.next;
        ListNode tail = subHead;
        subHead = tail.next;
        tail.next = null;
        return subHead;
    }
}

// Final version, much simpler
// Next time, when you want to get the middle node of a list, use a fast/slow pair.
public class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        
        ListNode fast = head, slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next; 
            slow = slow.next;
        }
        fast = slow;
        slow = slow.next;
        fast.next = null;
        
        ListNode a = sortList(head);
        ListNode b = sortList(slow);
        return mergeSortedList(a, b);
    }
    
    public ListNode mergeSortedList(ListNode a, ListNode b) {
        ListNode fakeHead = new ListNode(0);
        
        // A very useful for loop form, that can actually replace while loop.
        for (ListNode current = fakeHead; a != null || b != null; current = current.next) {
            int valA = a != null ? a.val : Integer.MAX_VALUE;
            int valB = b != null ? b.val : Integer.MAX_VALUE;
            if (valA < valB) {
                current.next = a;
                a = a.next;
            } else {
                current.next = b;
                b = b.next;
            }
        }
        return fakeHead.next;
    }
}