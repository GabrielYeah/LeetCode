// Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.

// For example, given n = 3, a solution set is:

// "((()))", "(()())", "(())()", "()(())", "()()()"

// Aced.
public class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<String>();
        int[] counts = new int[2];
        counts[0] = n; counts[1] = n;
        dfs(result, counts, new StringBuilder(), n);
        return result;
    }
    
    private void dfs(List<String> result, int[] counts, StringBuilder sb, int n) {
        if (sb.length() >= n * 2) {
            result.add(sb.toString());
            return;
        }
        if (counts[0] > 0) {
            counts[0]--;
            sb.append('(');
            dfs(result, counts, sb, n);
            counts[0]++;
            sb.deleteCharAt(sb.length() - 1);
        }
        if (counts[1] > counts[0]) {
            counts[1]--;
            sb.append(')');
            dfs(result, counts, sb, n);
            counts[1]++;
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}