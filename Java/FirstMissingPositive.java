// Given an unsorted integer array, find the first missing positive integer.

// For example,
// Given [1,2,0] return 3,
// and [3,4,-1,1] return 2.

// Your algorithm should run in O(n) time and uses constant space.

// Use index to reflect the desired value for that position.
public class Solution {
    public int firstMissingPositive(int[] A) {
        if (A == null || A.length == 0)
            return 1;
        int i = 0;
        while (i < A.length) {
            if (A[i] > A.length || A[i] <= 0 || A[i] == i + 1 || A[i] == A[A[i] - 1]) {
                i++;
            } else {
                int tmp = A[A[i] - 1];
                A[A[i] - 1] = A[i];
                A[i] = tmp;
            }
        }
        for (i = 0; i < A.length; ++i)
            if (A[i] != i + 1)
                return i + 1;
        return A.length + 1;
    }
}