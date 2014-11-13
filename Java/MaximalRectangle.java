// Given a 2D binary matrix filled with 0's and 1's, find the largest rectangle containing all ones and return its area.

// Made mistakes using wrong variable names.
public class Solution {
    public int maximalRectangle(char[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;
        int row = matrix.length, col = matrix[0].length;
        int[][] heights = new int[row][col];
        // For each row, calculate a the height for each element.
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                int num = matrix[i][j] - '0';
                if (i == 0)
                    heights[i][j] = num;
                else
                    heights[i][j] = num == 0 ? 0 : num + heights[i - 1][j];
            }
        }
        int max = 0;
        for (int i = 0; i < row; ++i)
            max = Math.max(max, maximalArea(heights[i]));
        return max;
    }

    // The same solution with largest rect problem.
    private int maximalArea(int[] height) {
        int[] copy = new int[height.length + 1];
        copy = Arrays.copyOf(height, height.length + 1);
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0, max = 0;
        while (i < copy.length) {
            if (stack.isEmpty() || copy[stack.peek()] <= copy[i]) {
                stack.push(i++);
            } else {
                int t = stack.pop();
                max = Math.max(max, copy[t] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return max;
    }
}