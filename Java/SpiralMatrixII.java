// Given an integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

// For example,
// Given n = 3,

// You should return the following matrix:
// [
//  [ 1, 2, 3 ],
//  [ 8, 9, 4 ],
//  [ 7, 6, 5 ]
// ]

// Aced. Simple one.
public class Solution {
    public int[][] generateMatrix(int n) {
        if (n <= 0)
            return new int[0][0];
        int[][] result = new int[n][n];
        int count = 1, total = n * n;
        int l = 0, r = n - 1, t = 0, b = n - 1;
        while (count <= total) {
            for (int i = l; i <= r; ++i)
                result[t][i] = count++;
            if (++t > b)
                break;
            for (int i = t; i <= b; ++i)
                result[i][r] = count++;
            if (--r < l)
                break;
            for (int i = r; i >= l; --i)
                result[b][i] = count++;
            if (--b < t)
                break;
            for (int i = b; i >= t; --i)
                result[i][l] = count++;
            if (++l > r)
                break;
        }
        return result;
    }
}