// There are two sorted arrays A and B of size m and n respectively. Find the median of the two sorted arrays. The overall run time complexity should be O(log (m+n)).

// Do binary search recursively.
public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int m = A.length, n = B.length;
        if ((m + n) % 2 == 0)
            return (findMedian(A, 0, m, B, 0, n, (m + n) / 2) + 
                    findMedian(A, 0, m, B, 0, n, (m + n) / 2 + 1)) / 2.0;
        else
            return findMedian(A, 0, m, B, 0, n, (m + n) / 2 + 1);
    }
    
    private double findMedian(int A[], int as, int ae, int B[], int bs, int be, int k) {
        // Here, k always stands for count, not index
        // ae and be are exclusive.
        int m = ae - as, n = be - bs;
        int am = as + (ae - as) / 2, bm = bs + (be - bs) / 2;
        if (m == 0)
            return B[bs + k - 1];
        if (n == 0)
            return A[as + k - 1];
        if (k == 1)
            return Math.min(A[as], B[bs]);
        // Need to throw away the middle element.
        // If not, there may be cases that no elements are throwed.
        if (A[am] > B[bm]) {
            if (m / 2 + n / 2 + 1 >= k)
                return findMedian(A, as, am, B, bs, be, k);
            else
                return findMedian(A, as, ae, B, bm + 1, be, k - n / 2 - 1);
        } else {
            if (m / 2 + n / 2 + 1 >= k)
                return findMedian(A, as, ae, B, bs, bm, k);
            else
                return findMedian(A, am + 1, ae, B, bs, be, k - m / 2 - 1);
        }
    }
}