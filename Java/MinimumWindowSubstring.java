// Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

// For example,
// S = "ADOBECODEBANC"
// T = "ABC"
// Minimum window is "BANC".

// Note:
// If there is no such window in S that covers all characters in T, return the emtpy string "".

// If there are multiple such windows, you are guaranteed that there will always be only one unique minimum window in S.

public class Solution {
    public String minWindow(String S, String T) {
        if (S == null || T == null || S.length() < T.length())
            return "";
        // Record the times of characters in T
        int[] desired = new int[256];
        // Record the times of desired characters in S
        int[] appeared = new int[256];
        for (char c : T.toCharArray())
            desired[c]++;
        int count = 0, start = 0, minLength = S.length() + 1, minStart = -1;
        for (int end = 0; end < S.length(); ++end) {
            char c = S.charAt(end); 
            // Skip if not in T
            if (desired[c] == 0)
                continue;
            appeared[c]++;
            // If should be necessary in the window, count++
            if (appeared[c] <= desired[c])
                count++;
            if (count == T.length()) {
                // The window now has contained all the chars in T
                while (start < end) {
                    // Remove unnecessary characters
                    char n = S.charAt(start);
                    if (desired[n] == 0 || appeared[n]-- > desired[n])
                        start++;
                    else
                        break;
                }
                // If window is smaller, record current start point and length
                if (minLength > end - start + 1) {
                    minStart = start;
                    minLength = end - start + 1;
                }
                // count--
                count--;
                start++;
            }
        }
        return minStart == -1 ? "" : S.substring(minStart, minStart + minLength);
    }
}