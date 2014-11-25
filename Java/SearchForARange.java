// Given a sorted array of integers, find the starting and ending position of a given target value.

// Your algorithm's runtime complexity must be in the order of O(log n).

// If the target is not found in the array, return [-1, -1].

// For example,
// Given [5, 7, 7, 8, 8, 10] and target value 8,
// return [3, 4].

// Aced. Simple one.
public class Solution {
    public int[] searchRange(int[] A, int target) {
        int[] result = new int[2];
        result[0] = -1; result[1] = -1;
        if (A == null || A.length == 0)
            return result;
        binarySearch(A, 0, A.length - 1, target, result);
        return result;
    }
    
    private void binarySearch(int[] A, int l, int r, int target, int[] result) {
        if (l > r)
            return;
        int m = l + (r - l) / 2;
        if (A[m] == target) {
            if (result[0] == -1) {
                result[0] = m;
                result[1] = m;
            }
            if (result[0] >= m) {
                result[0] = m;
                binarySearch(A, l, m - 1, target, result);
            }
            if (result[1] <= m) {
                result[1] = m;
                binarySearch(A, m + 1, r, target, result);
            }
        } else if (A[m] > target) {
            binarySearch(A, l, m - 1, target, result);
        } else {
            binarySearch(A, m + 1, r, target, result);
        }
        
    }
}