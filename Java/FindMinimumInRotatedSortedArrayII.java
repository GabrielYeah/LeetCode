// Follow up for "Find Minimum in Rotated Sorted Array":
// What if duplicates are allowed?

// Would this affect the run-time complexity? How and why?
// Suppose a sorted array is rotated at some pivot unknown to you beforehand.

// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

// Find the minimum element.

// The array may contain duplicates.

// Accepted at second attempt.
public class Solution {
    public int findMin(int[] num) {
        if (num == null || num.length == 0)
            return 0;
        int l = 0, r = num.length - 1;
        while (l <= r) {
            if (num[l] < num[r])
                return num[l];
            int m = (l + r) / 2;
            if (num[m] > num[r])
                // m greater than r, then min must
                // be in the right part
                l = m + 1;
            else if (num[m] < num[r]) {
                // m smaller than r, min must 
                // be in the left part
                // Keep m because it may be the min
                r = m;
            } else {
                // Duplicates exist, trend can not be determined.
                // Update the boundary to see the trend
                r--;
            }
        }
        return num[l];
    }
}