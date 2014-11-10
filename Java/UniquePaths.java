// A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).

// The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).

// How many possible unique paths are there?

// First attempt. Aced. But memory can be improved.
public class Solution1 {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0)
            return 0;
        int[][] opt = new int[m][n];
        for (int i = 0; i < n; ++i)
            opt[0][i] = 1;
        for (int i = 0; i < m; ++i)
            opt[i][0] = 1;
        for (int i = 1; i < m; ++i)
            for (int j = 1; j < n; ++j)
                opt[i][j] = opt[i - 1][j] + opt[i][j - 1];
        return opt[m - 1][n - 1];
    }
}

// Use one dimension dp.
public class Solution {
    public int uniquePaths(int m, int n) {
        if (m <= 0 || n <= 0)
            return 0;
        int[] opt = new int[n];
        opt[0] = 1;
        for (int i = 0; i < m; ++i)
            for (int j = 1; j < n; ++j)
                opt[j] += opt[j - 1];
        return opt[n - 1];
    }
}