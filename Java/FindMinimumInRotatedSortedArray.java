// Suppose a sorted array is rotated at some pivot unknown to you beforehand.

// (i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

// Find the minimum element.

// You may assume no duplicate exists in the array.

// First version 
public class Solution1 {
    public int findMin(int[] num) {
        int start = 0;
        int end = num.length - 1;
        int mid = end / 2;
        while (end > start) {
            if (mid == start || mid == end)
                return Math.min(num[start], num[end]);
            if (num[start] > num[mid])
                end = mid;
            else if (num[mid] > num[end])
                start = mid;
            else if (num[mid] > num[start] && num[mid] < num[end])
                return num[start];
                
            mid = (start + end) / 2;
        }
        return num[start];
    }
}

// Final version
// Simplify the process of thinking
// Think it in this way:
// If the sub part is sorted, return the first element
// Otherwise, get the middle and search the part with the answer
public class Solution {
    public int findMin(int[] num) {
        int start = 0;
        int end = num.length - 1;
        while (start < end) {
            if (num[start] < num[end])
                return num[start];
            int mid = (start + end) / 2;
            
            // Note that the target of the problem is to find minimum
            // We can safely discard middle when we are sure it is 
            // at least larger than or equal to another one
            if (num[mid] >= num[start])
                start = mid + 1;
            else
                end = mid;
        }
        return num[start];
    }
}