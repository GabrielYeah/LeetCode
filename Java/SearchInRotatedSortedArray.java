// Suppose a sorted array is rotated at some pivot unknown to you beforehand.

// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

// You are given a target value to search. If found in the array return its index, otherwise return -1.

// You may assume no duplicate exists in the array.

// First attempt. Use recursion.
public class Solution {
    public int search(int[] A, int target) {
        if (A == null || A.length == 0)
            return -1;
        return search(A, target, 0, A.length - 1);
    }
    
    private int search(int[] A, int target, int start, int end) {
        if (start > end)
            return -1;
        if (start == end)
            return A[start] == target ? start : -1;
        int mid = (start + end) / 2;
        if (A[mid] == target)
            return mid;
        if (A[start] < A[end]) {
            if (A[mid] < target)
                return search(A, target, mid + 1, end);
            else
                return search(A, target, start, mid - 1);
        }
        int result = search(A, target, mid + 1, end);
        if (result == -1)
            result = search(A, target, start, mid - 1);
        return result;
    }
}

