// Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:

// Integers in each row are sorted from left to right.
// The first integer of each row is greater than the last integer of the previous row.
// For example,

// Consider the following matrix:

// [
//   [1,   3,  5,  7],
//   [10, 11, 16, 20],
//   [23, 30, 34, 50]
// ]
// Given target = 3, return true.

// Binary search. Aced!
public class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return false;
        int t = 0, b = matrix.length - 1, x = -1;
        while (t <= b) {
            int m = (t + b) / 2;
            if (matrix[m][0] > target) {
                b = m - 1;
            } else if (matrix[m][matrix[0].length - 1] < target) {
                t = m + 1;
            } else {
                x = m;
                break;
            }
        }
        if (x == -1)
            return false;
        int l = 0, r = matrix[x].length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (matrix[x][m] == target)
                return true;
            if (matrix[x][m] < target)
                l = m + 1;
            else
                r = m - 1;
        }
        return false;
    }
}