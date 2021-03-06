// Given a string S, find the longest palindromic substring in S. You may assume that the maximum length of S is 1000, and there exists one unique longest palindromic substring.

// First attempt. DP and has O(n^2) time and space complexity.
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;
        boolean[][] valid = new boolean[s.length()][s.length()];
        int index = 0, len = 1;
        for (int i = 0; i < s.length(); i++)
            valid[i][i] = true;

        for (int k = 2; k <= s.length(); k++) {
            for (int i = 0; i <= s.length() - k; i++) {
                int end = i + k - 1;
                if (s.charAt(i) == s.charAt(end)) 
                    valid[i][end] = i == end - 1 || valid[i + 1][end - 1];
                if (valid[i][end]) {
                    index = i; 
                    len = k;
                }
            }
        }
        return s.substring(index, index + len);
    }
}

// Second version. Improved the space complexity to O(1).
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) {
            String tmp = search(s, i, i);
            if (tmp.length() > longest.length())
                longest = tmp;
            tmp = search(s, i, i + 1);
            if (tmp.length() > longest.length())
                longest = tmp;
        }
        return longest;
    }
    
    private String search(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            end++;
            start--;
        }
        return s.substring(start + 1, end);
    }
}

// Third attempt. Instead of returning string on every search, return length.
public class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() <= 1)
            return s;
        int maxLen = 1, start = 0, end = 0;
        for (int i = 0; i < s.length() - 1; i++) {
            int len = findLen(s, i - 1, i + 1);
            if (len * 2 + 1 > maxLen) {
                maxLen = len * 2 + 1;
                start = i - len;
                end = i + len;
            }
            if (s.charAt(i) == s.charAt(i + 1)) {
                len = findLen(s, i - 1, i + 2);
                if (len * 2 + 2 > maxLen) {
                    maxLen = len * 2 + 2;
                    start = i - len;
                    end = i + 1 + len;
                }
            }
        }
        return s.substring(start, end + 1);
    }
    
    private int findLen(String s, int l, int r) {
        int len = 0;
        while (l >= 0 && r < s.length() && s.charAt(l--) == s.charAt(r++))
            len++;
        return len;
    }
}

// Best solution. O(n) time complexity.
// https://www.evernote.com/shard/s86/nl/9223575/31aae83f-e697-490d-93aa-a46f5edfa88e/