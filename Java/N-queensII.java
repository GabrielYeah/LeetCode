// Follow up for N-Queens problem.

// Now, instead outputting board configurations, return the total number of distinct solutions.

// Made a mistake by assigning wrong dict[] value.
public class Solution {
    int count = 0;
    
    public int totalNQueens(int n) {
        if (n <= 0)
            return 0;
        count = 0;
        dfs(new int[n], 0);
        return count;
    }
    
    private void dfs(int[] dict, int depth) {
        if (depth == dict.length) {
            count++;
            return;
        }
        for (int i = 0; i < dict.length; ++i) {
            if (isValid(dict, depth, i)) {
                dict[depth] = i;
                dfs(dict, depth + 1);
            }
        }
    }
    
    private boolean isValid(int[] dict, int depth, int val) {
        for (int i = 0; i < depth; ++i)
            if (dict[i] == val || Math.abs(val - dict[i]) == depth - i)
                return false;
        return true;
    }
}