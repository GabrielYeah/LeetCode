// Given a m x n matrix, if an element is 0, set its entire row and column to 0. Do it in place.

// Follow up:
// Did you use extra space?
// A straight forward solution using O(mn) space is probably a bad idea.
// A simple improvement uses O(m + n) space, but still not the best solution.
// Could you devise a constant space solution?

// First attempt. Memory O(n)
public class Solution1 {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        boolean[] cols = new boolean[matrix[0].length];
        for (int i = 0; i < matrix.length; ++i) {
            boolean hasZero = false;
            for (int j = 0; j < matrix[0].length; ++j) {
                cols[j] = cols[j] || matrix[i][j] == 0;
                hasZero = hasZero || matrix[i][j] == 0;
            }
            if (!hasZero)
                continue;
            for (int j = 0; j < matrix[0].length; ++j)
                matrix[i][j] = 0;
        }
        
        for (int j = 0; j < matrix[0].length; ++j)
            for (int i = 0; i < matrix.length; ++i)
                if (cols[j])
                    matrix[i][j] = 0;
    }
}

// Use first row and column to record information. 
public class Solution {
    public void setZeroes(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;
        int row = matrix.length, col = matrix[0].length;
        boolean rowZero = false, colZero = false;
        for (int i = 0; i < row; ++i)
            rowZero = rowZero || matrix[i][0] == 0;
        for (int j = 0; j < col; ++j)
            colZero = colZero || matrix[0][j] == 0;
        for (int i = 1; i < row; ++i)
            for (int j = 1; j < col; ++j)
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
        
        for (int i = 1; i < row; ++i)
            for (int j = 1; j < col; ++j)
                if (matrix[i][0] == 0 || matrix[0][j] == 0)
                    matrix[i][j] = 0;
        
        if (rowZero)
            for (int i = 0; i < row; ++i)
                matrix[i][0] = 0;
                
        if (colZero)   
            for (int j = 0; j < col; ++j)
                matrix[0][j] = 0;
        
    }
}