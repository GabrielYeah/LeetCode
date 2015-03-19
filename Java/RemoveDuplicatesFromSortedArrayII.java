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

// A clearer version.
public class Solution {
    public int removeDuplicates(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int l = 0, r = 0, count = 0;
        while (r < A.length) {
            if (r == 0 || A[r - 1] != A[r]) {
                A[l++] = A[r++];
                count = 0;
            } else {
                if (count < 1)
                    A[l++] = A[r];
                count++;
                r++;
            }
        }
        return l;
    }
}