// A peak element is an element that is greater than its neighbors.

// Given an input array where num[i] ≠ num[i+1], find a peak element and return its index.

// The array may contain multiple peaks, in that case return the index to any one of the peaks is fine.

// You may imagine that num[-1] = num[n] = -∞.

// For example, in array [1, 2, 3, 1], 3 is a peak element and your function should return the index number 2.

// Note:
// Your solution should be in logarithmic complexity.

public class Solution {
    public int findPeakElement(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        int l = 0, r = num.length - 1;
        while (l <= r) {
            if (l == r)
                return l;
            int m = l + (r - l) / 2;
            int leftValue = m == 0 ? Integer.MIN_VALUE : num[m - 1];
            int rightValue = m == num.length - 1 ? Integer.MIN_VALUE : num[m + 1];
            if (num[m] > leftValue && num[m] > rightValue)
                return m;
            else if (num[m] >= leftValue)
                l = m + 1;
            else
                r = m - 1;
        }
        return l;
    }
}