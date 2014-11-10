// Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

// For example,
// Given the following matrix:

// [
//  [ 1, 2, 3 ],
//  [ 4, 5, 6 ],
//  [ 7, 8, 9 ]
// ]
// You should return [1,2,3,6,9,8,7,4,5].

// Made a little mistake by not checking condition after
// each row or column is updated
public class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return result;
        int row = matrix.length, col = matrix[0].length;
        int t = 0, b = row - 1, l = 0, r = col - 1;
        while (t <= b && l <= r) {
            for (int i = l; i <= r; ++i)
                result.add(matrix[t][i]);
            if (++t > b)
                break;
            for (int i = t; i <= b; ++i)
                result.add(matrix[i][r]);
            if (l > --r)
                break;
            for (int i = r; i >= l; --i)
                result.add(matrix[b][i]);
            if (t > --b)
                break;
            for (int i = b; i >= t; --i)
                result.add(matrix[i][l]);
            if (++l > r)
                break;
        }
        return result;
    }
}