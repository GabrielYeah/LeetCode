// Given a string s, partition s such that every substring of the partition is a palindrome.

// Return all possible palindrome partitioning of s.

// For example, given s = "aab",
// Return

//   [
//     ["aa","b"],
//     ["a","a","b"]
//   ]

// Dynamic Programming. Accepted on second attempt.
public class Solution {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (s == null || s.length() == 0)
            return result;
        int len = s.length();
        boolean[][] opt = new boolean[len][len];
        for (int i = 0; i < len; ++i)
            opt[i][i] = true;
        for (int i = 0; i < len - 1; ++i)
            opt[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        for (int k = 2; k < len; ++k)
            for (int i = 0; i < len - k; ++i)
                opt[i][i + k] = opt[i + 1][i + k - 1] && s.charAt(i) == s.charAt(i + k);
        
        List<String> path = new ArrayList<String>();
        dfs(s, opt, 0, path, result);
        return result;
    }
    
    private void dfs(String s, boolean[][] opt, int start, List<String> path, List<List<String>> result) {
        if (start >= opt.length) {
            result.add(new ArrayList<String>(path)); 
            return;
        }
        for (int i = 0; i < opt.length - start; ++i) {
            if (opt[start][start + i]) {
                path.add(s.substring(start, start + i + 1));
                dfs(s, opt, start + i + 1, path, result);
                path.remove(path.size() - 1);
            }
        }
    }
}