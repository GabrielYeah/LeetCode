// Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

// For example, given
// s = "leetcode",
// dict = ["leet", "code"].

// Return true because "leetcode" can be segmented as "leet code".

// opt[i] stands for whether a sub string from 0 - i
// can be broken into works in dict
// opt[i] = opt[k] && substring(k + 1, i + 1) in dict
public class Solution {
    public boolean wordBreak(String s, Set<String> dict) {
        int length = s.length();
        if (length == 0)
            return false;
        boolean[] opt = new boolean[length + 1];
        String tmp = "#" + s;
        opt[0] = true;
        for (int i = 1; i < length + 1; ++i) {
            for (int k = 0; k < i; ++k) {
                opt[i] = opt[k] && dict.contains(tmp.substring(k + 1, i + 1));
                if (opt[i]) break;
            }
        }
        return opt[length];
    }
}