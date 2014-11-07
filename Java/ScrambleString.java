// Given a string s1, we may represent it as a binary tree by partitioning it to two non-empty substrings recursively.

// Below is one possible representation of s1 = "great":

//     great
//    /    \
//   gr    eat
//  / \    /  \
// g   r  e   at
//            / \
//           a   t
// To scramble the string, we may choose any non-leaf node and swap its two children.

// For example, if we choose the node "gr" and swap its two children, it produces a scrambled string "rgeat".

//     rgeat
//    /    \
//   rg    eat
//  / \    /  \
// r   g  e   at
//            / \
//           a   t
// We say that "rgeat" is a scrambled string of "great".

// Similarly, if we continue to swap the children of nodes "eat" and "at", it produces a scrambled string "rgtae".

//     rgtae
//    /    \
//   rg    tae
//  / \    /  \
// r   g  ta  e
//        / \
//       t   a
// We say that "rgtae" is a scrambled string of "great".

// Given two strings s1 and s2 of the same length, determine if s2 is a scrambled string of s1.

// First attempt. TLE.
public class Solution1 {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null)
            return true;
        if (s1.length() == 0 && s2.length() == 0)
            return true;
        for (int i = 0; i < s1.length() - 1; ++i) {
            String a = s1.substring(0, i + 1);
            String b = s2.substring(i + 1, s2.length());
            String c = s1.substring(i + 1, s1.length());
            String d = s2.substring(0, i + 1);
            if (isSubScramble(a, d) && isSubScramble(b, c))
                return true;
        }
        return false;
    }
    
    private boolean isSubScramble(String s1, String s2) {
        if (s1.length() == 1)
            return s1.charAt(0) == s2.charAt(0);
        StringBuilder left1 = new StringBuilder(""), right1 = new StringBuilder(s1);
        StringBuilder left2 = new StringBuilder(""), right2 = new StringBuilder(s2);
        StringBuilder left3 = new StringBuilder(s2), right3 = new StringBuilder("");
        for (int i = 0; i < s1.length() - 1; ++i) {
            left1.append(right1.charAt(0)); right1.deleteCharAt(0);
            left2.append(right2.charAt(0)); right2.deleteCharAt(0);
            right3.insert(0, left3.charAt(left3.length() - 1));
            left3.setLength(left3.length() - 1);
            if (isSubScramble(left1.toString(), left2.toString()) 
                && isSubScramble(right1.toString(), right2.toString()))
                return true;
            if (isSubScramble(left1.toString(), right3.toString()) 
                && isSubScramble(right1.toString(), left3.toString()))
                return true;
        }
        return false;
    }
}

// Cutting edges
public class Solution2 {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null)
            return true;
        if (s1.equals(s2))
            return true;
        // Improve the performace by cutting edges
        // If does not contain the same characters, return false
        int len = s1.length();
        int[] check = new int[26];
        for (int i = 0; i < len; ++i) {
            check[s1.charAt(i) - 'a']++;
            check[s2.charAt(i) - 'a']--;
        }
        for (int i : check) {
            if (i != 0) {
                return false;
            }
        }
                
        // Check subproblems
        for (int i = 1; i < len; ++i) {
            if (isScramble(s1.substring(0, i), s2.substring(0, i)) 
                && isScramble(s1.substring(i, len), s2.substring(i, len)))
                return true;
            if (isScramble(s1.substring(len - i, len), s2.substring(0, i)) 
                && isScramble(s1.substring(0, len - i), s2.substring(i, len)))
                return true;
        }
        return false;
    }
}

// Final version. 3 dimension DP.
public class Solution {
    public boolean isScramble(String s1, String s2) {
        if (s1 == null || s2 == null)
            return true;
        if (s1.equals(s2))
            return true;
        int len = s1.length();
        // opt[k][i][j] stands for whether s2[j..j + k] is a 
        // scrambled string of s1[i..i + k]
        boolean[][][] opt = new boolean[len][len][len];
        for (int i = 0; i < len; ++i)
            for (int j = 0; j < len; ++j)
                opt[0][i][j] = s1.charAt(i) == s2.charAt(j);
        for (int k = 2; k <= len; ++k) {
            for (int i = len - k; i >= 0; --i) {
                for (int j = len - k; j >= 0; --j) {
                    boolean canScramble = false;
                    for (int m = 1; m < k; ++m) {
                        canScramble = (opt[m - 1][i][j] && opt[k - m - 1][i + m][j + m]) ||
                                        (opt[m - 1][i][j + k - m] && opt[k - m - 1][i + m][j]);
                        if (canScramble)
                            break;
                    }
                    opt[k - 1][i][j] = canScramble;
                }
            }
        }
        return opt[len - 1][0][0];
    }
}