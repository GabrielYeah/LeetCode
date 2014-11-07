// Follow up for "Remove Duplicates":
// What if duplicates are allowed at most twice?

// For example,
// Given sorted array A = [1,1,1,2,2,3],

// Your function should return length = 5, and A is now [1,1,2,2,3].

// Add a dup flag to check whether an element has appeared for once
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int prev = 0, curr = 1;
        boolean dup = false;
        while (curr < A.length) {
            if (A[curr] == A[prev]) {
                if (!dup) {
                    prev++;
                    A[prev] = A[curr];
                }
                curr++;
                dup = true;
            } else {
                prev++;
                A[prev] = A[curr];
                curr++;
                dup = false;
            }
        }
        return prev + 1;
    }
}