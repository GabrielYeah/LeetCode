// Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right which minimizes the sum of all numbers along its path.

// Note: You can only move either down or right at any point in time.

// First attempt. TLE. 
public class Solution {
    int minValue;
    public int minPathSum(int[][] grid) {
        minValue = 0;
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return minValue;
        search(grid, 0, 0, 0);
        return minValue;
    }
    
    private void search(int[][] grid, int x, int y, int path) {
        path += grid[x][y];
        if (x == grid.length && y == grid[0].length) {
            minValue = Math.min(minValue, path);
            return;
        }
        if (x < grid.length - 1)
            search(grid, x + 1, y, path);
        if (y < grid[0].length - 1)
            search(grid, x, y + 1, path);
    }
}

// Final version. DP.
public class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0)
            return 0;
        int row = grid.length, col = grid[0].length;
        int[][] opt = new int[row][col];
        opt[0][0] = grid[0][0];
        for (int i = 1; i < row; ++i)
            opt[i][0] = opt[i - 1][0] + grid[i][0];
        for (int j = 1; j < col; ++j)
            opt[0][j] = opt[0][j - 1] + grid[0][j];
        for (int i = 1; i < row; ++i)
            for (int j = 1; j < col; ++j)
                opt[i][j] = Math.min(opt[i - 1][j] + grid[i][j], opt[i][j - 1] + grid[i][j]);
        
        return opt[row - 1][col - 1];
    }
}