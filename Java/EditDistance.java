// Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

// You have the following 3 operations permitted on a word:

// a) Insert a character
// b) Delete a character
// c) Replace a character

// DP. Made a little mistake by setting wrong initial values.
public class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null)
            return 0;
        int len1 = word1.length(), len2 = word2.length();
        if (len1 == 0)
            return len2;
        if (len2 == 0)
            return len1;
        
        int[][] opt = new int[len1 + 1][len2 + 1];
        opt[0][0] = 0;
        for (int i = 1; i <= len1; ++i)
            opt[i][0] = opt[i - 1][0] + 1;
        for (int j = 1; j <= len2; ++j)
            opt[0][j] = opt[0][j - 1] + 1;
        for (int i = 1; i <= len1; ++i) {
            for (int j = 1; j <= len2; ++j) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    opt[i][j] = opt[i - 1][j - 1];
                } else {
                    int insert = opt[i][j - 1] + 1;
                    int delete = opt[i - 1][j] + 1;
                    int replace = opt[i - 1][j - 1] + 1;
                    opt[i][j] = Math.min(insert, Math.min(delete, replace));
                }
            }
        }
        return opt[len1][len2];
    }
}