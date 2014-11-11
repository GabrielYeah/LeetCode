// Find the contiguous subarray within an array (containing at least one number) which has the largest sum.

// For example, given the array [−2,1,−3,4,−1,2,1,−5,4],
// the contiguous subarray [4,−1,2,1] has the largest sum = 6.

// More practice:
// If you have figured out the O(n) solution, try coding another solution using the divide and conquer approach, which is more subtle.

// One dimension DP.
public class Solution {
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int localMax = A[0], max = A[0];
        for (int i = 1; i < A.length; ++i) {
            localMax = Math.max(A[i], A[i] + localMax);
            max = Math.max(localMax, max);
        }
        return max;
    }
}

// Divide and conquer version. Though the time complexity can be higher.
public class Solution {
    public int maxSubArray(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        return divideAndMerge(A, 0, A.length - 1);
    }
    
    private int divideAndMerge(int[] A, int l, int r) {
        if (l > r)
            return Integer.MIN_VALUE;
        int m = (l + r) / 2;
        int left = divideAndMerge(A, l, m - 1);
        int right = divideAndMerge(A, m + 1, r);
        int localMax = Math.max(left, right);
        int sum = 0; left = 0;
        for (int i = m - 1; i >= l; --i) {
            sum += A[i];
            left = Math.max(sum, left);
        }
        sum = 0; right = 0;
        for (int i = m + 1; i <= r; ++i) {
            sum += A[i];
            right = Math.max(sum, right);
        }
        localMax = Math.max(localMax, left + A[m] + right);
        return localMax;
    }
}