// Given two sorted integer arrays A and B, merge B into A as one sorted array.

// Note:
// You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.

public class Solution {
    public void merge(int A[], int m, int B[], int n) {
        // Scan from the tail
        // Put the larger value to A's tail
        for (int i = m + n - 1, j = m - 1, k = n - 1; i >= 0; i--) {
            int a = j >= 0 ? A[j] : Integer.MIN_VALUE;
            int b = k >= 0 ? B[k] : Integer.MIN_VALUE;
            A[i] = a > b ? a : b;
            j = a > b ? j - 1 : j;
            k = a > b ? k : k - 1;
        }
    }
}