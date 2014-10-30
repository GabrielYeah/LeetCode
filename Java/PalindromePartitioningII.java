// Given a string s, partition s such that every substring of the partition is a palindrome.

// Return the minimum cuts needed for a palindrome partitioning of s.

// For example, given s = "aab",
// Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

public class Solution {
    public int minCut(String s) {
        if (s == null && s.length() == 0)
            return 0;
        int len = s.length();
        boolean[][] opt = new boolean[len][len];
        for (int i = 0; i < len; ++i)
            opt[i][i] = true;
        for (int i = 0; i < len - 1; ++i)
            opt[i][i + 1] = s.charAt(i) == s.charAt(i + 1);
        for (int k = 2; k < len; ++k)
            for (int i = 0; i < len - k; ++i)
                opt[i][i + k] = opt[i + 1][i + k - 1] && s.charAt(i) == s.charAt(i + k);
                
        // minCount[i] = the min cut of elements from i to len - 1
        int[] minCount = new int[len + 1];
        Arrays.fill(minCount, Integer.MAX_VALUE);
        minCount[len] = -1;
        for (int i = len - 1; i >= 0; --i)
            for (int j = i; j >= 0; --j)
                if (opt[j][i])
                    minCount[j] = Math.min(minCount[j], minCount[i + 1] + 1);

        return minCount[0];
    }
}