// Given an array and a value, remove all instances of that value in place and return the new length.

// The order of elements can be changed. It doesn't matter what you leave beyond the new length.

// Made a mistake by returning l + 1. Be careful about index.
public class Solution {
    public int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0)
            return 0;
        int l = 0, r = 0;
        while (r < A.length) {
            if (A[r] != elem) {
                A[l] = A[r];
                l++;
            }
            r++;
        }
        return l;
    }
}