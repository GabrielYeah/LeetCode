// Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.

// Do not allocate extra space for another array, you must do this in place with constant memory.

// For example,
// Given input array A = [1,1,2],

// Your function should return length = 2, and A is now [1,2].

// First attempt. TLE.
public class Solution1 {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int deleted = 0;
        for (int i = 1; i < A.length; i++) {
            int prev = i;
            while (i < A.length && A[i] == A[i - 1])
                i++;
            if (prev == i)
                continue;
            deleted += i - prev;
            int curr = i;
            while (curr < A.length) {
                A[prev] = A[curr];
                curr++; 
                prev++;
            }
        }
        return A.length - deleted;
    }
}

// Final version. Consider index rather than elements.
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int prev = 0, curr = 1;
        while (curr < A.length) {
            if (A[curr] == A[prev]) {
                curr++;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
            }
        }
        return prev + 1;
    }
}