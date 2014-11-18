// The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.

// Given an integer n, return all distinct solutions to the n-queens puzzle.

// Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space respectively.

// For example,
// There exist two distinct solutions to the 4-queens puzzle:

// [
//  [".Q..",  // Solution 1
//   "...Q",
//   "Q...",
//   "..Q."],

//  ["..Q.",  // Solution 2
//   "Q...",
//   "...Q",
//   ".Q.."]
// ]

// First attempt. TLE at 8.
public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<String[]>();
        if (n <= 0)
            return result;
        dfs(new String[n], new boolean[n][n], 0, result);
        return result;
    }
    
    private void dfs(String[] path, boolean[][] dict, int depth, List<String[]> result) {
        if (depth == path.length) {
            result.add(Arrays.copyOf(path, path.length));
            return;
        }
        int n = path.length;
        char[] curr = new char[n];
        for (int i = 0; i < n; ++i)
            curr[i] = '.';
        for (int i = 0; i < n; ++i) {
            if (dict[depth][i])
                continue;
            curr[i] = 'Q';
            updateConstraint(depth, n, i, dict, true);
            path[depth] = String.valueOf(curr);
            dfs(path, dict, depth + 1, result);
            curr[i] = '.';
            updateConstraint(depth, n, i, dict, false);
        }
    }
    
    private void updateConstraint(int depth, int n, int i, boolean[][] dict, boolean val) {
        for (int j = depth + 1; j < n; ++j) {
            int left = i - j - depth, right = i + j - depth;
            dict[j][i] = val;
            if (left >= 0)
                dict[j][left] = val;
            if (right < n)
                dict[j][right] = val;
        }
    }
}

// Second attempt. TLE at 9.
public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<String[]>();
        if (n <= 0)
            return result;
        dfs(new String[n], new int[n], 0, result);
        return result;
    }
    
    private void dfs(String[] path, int[] dict, int depth, List<String[]> result) {
        if (depth == path.length) {
            result.add(Arrays.copyOf(path, path.length));
            return;
        }
        int n = path.length;
        char[] curr = new char[n];
        for (int i = 0; i < n; ++i)
            curr[i] = '.';
        for (int i = 0; i < n; ++i) {
            if (!isValid(dict, i, depth))
                continue;
            curr[i] = 'Q';
            dict[depth] = i + 1;
            path[depth] = String.valueOf(curr);
            dfs(path, dict, depth + 1, result);
            curr[i] = '.';
            dict[depth] = 0;
        }
    }
    
    private boolean isValid(int[] dict, int val, int depth) {
        for (int i = 0; i < depth; ++i)
            if (dict[i] == val || dict[i] == val - (depth - i) || dict[i] == val + (depth - i))
                return false;
        return true;
    }
}

// Final version. Use one dimension array to store the info.
// Only build path when it reaches the bottom level.
public class Solution {
    public List<String[]> solveNQueens(int n) {
        List<String[]> result = new ArrayList<String[]>();
        if (n <= 0)
            return result;
        dfs(new int[n], 0, result);
        return result;
    }
    
    private void dfs(int[] dict, int depth, List<String[]> result) {
        if (depth == dict.length) {
            result.add(buildPath(dict, depth));
            return;
        }
        for (int i = 0; i < dict.length; ++i) {
            if (isValid(dict, i, depth)) {
                dict[depth] = i;
                dfs(dict, depth + 1, result);
            }
        }
    }
    
    private boolean isValid(int[] dict, int val, int depth) {
        for (int i = 0; i < depth; ++i)
            if (dict[i] == val || dict[i] == val - (depth - i) || dict[i] == val + (depth - i))
                return false;
        return true;
    }
    
    private String[] buildPath(int[] dict, int depth) {
        String[] array = new String[depth];
        for (int i = 0; i < depth; ++i) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < depth; ++j) {
                char c = dict[i] == j ? 'Q' : '.';
                sb.append(c);
            }
            array[i] = sb.toString();
        }
        return array;
    }
}