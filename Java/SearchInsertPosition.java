// Given a sorted array and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.

// You may assume no duplicates in the array.

// Here are few examples.
// [1,3,5,6], 5 → 2
// [1,3,5,6], 2 → 1
// [1,3,5,6], 7 → 4
// [1,3,5,6], 0 → 0

// Made a mistake by checking '<=' on right right boundary
public class Solution {
    public int searchInsert(int[] A, int target) {
        if (A == null || A.length == 0)
            return 0;
        int l = 0, r = A.length - 1;
        while (l <= r) {
            if (A[l] > target)
                return l;
            else if (A[r] < target)
                return r + 1;
            int m = l + (r - l) / 2;
            if (A[m] == target)
                return m;
            else if (A[m] > target)
                r = m - 1;
            else
                l = m + 1;
        }
        return l;
    }
}