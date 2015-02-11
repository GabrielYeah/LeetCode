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

// Updated version.
// A quite different solution. In previous one, I am trying to pass down the updated
// window down to next level. However, the cost would be that it is extremely confusing
// when handling the index, and the count, and all those kind of stuff.
// In this version, it is much simpler. Instead of removing half of the selected array
// each time, this version is removing half of k elements in the front. Different from
// the previous version, the right side of the arrays are not taken into consideration.
// In this way, code is much more clear.
public class Solution {
    public double findMedianSortedArrays(int A[], int B[]) {
        int len = A.length + B.length;
        if (len % 2 == 1)
            return findKth(A, 0, B, 0, len / 2 + 1);
        else
            return (findKth(A, 0, B, 0, len / 2) + findKth(A, 0, B, 0, len / 2 + 1)) / 2;
    }
    
    private double findKth(int[] A, int startA, int[] B, int startB, int k) {
        if (startA >= A.length) return B[startB + k - 1];
        if (startB >= B.length) return A[startA + k - 1];
        if (k <= 1) return Math.min(A[startA], B[startB]);
        int midValA = startA + k / 2 - 1 < A.length ? A[startA + k / 2 - 1] : Integer.MAX_VALUE;
        int midValB = startB + k / 2 - 1 < B.length ? B[startB + k / 2 - 1] : Integer.MAX_VALUE;
        double res = 0;
        if (midValA < midValB)
            res = findKth(A, startA + k / 2, B, startB, k - k / 2);
        else
            res = findKth(A, startA, B, startB + k / 2, k - k / 2);
        return res;
    }
}