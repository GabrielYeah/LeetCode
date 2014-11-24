// Implement wildcard pattern matching with support for '?' and '*'.

// '?' Matches any single character.
// '*' Matches any sequence of characters (including the empty sequence).

// The matching should cover the entire input string (not partial).

// The function prototype should be:
// bool isMatch(const char *s, const char *p)

// Some examples:
// isMatch("aa","a") → false
// isMatch("aa","aa") → true
// isMatch("aaa","aa") → false
// isMatch("aa", "*") → true
// isMatch("aa", "a*") → true
// isMatch("ab", "?*") → true
// isMatch("aab", "c*a*b") → false

public class Solution {
    public boolean isMatch(String s, String p) {
        int sp = 0, pp = 0, star = -1, lastMatch = 0;
        char[] sarray = s.toCharArray(), parray = p.toCharArray();
        while (sp < sarray.length) {
            // If the sp matches with pp, increase
            if (pp < parray.length && (sarray[sp] == parray[pp] || parray[pp] == '?')) {
                sp = sp + 1; 
                pp = pp + 1;
            } else if (pp < parray.length && parray[pp] == '*') {
                // Else if pp is a star, record star's position, and try to match sp with the next pp
                lastMatch = sp;
                star = pp;
                pp = pp + 1;
            } else if (star != -1) {
                // Else if a star has appeared, try to match the sp next to last matched to the pp
                // on the right of the latest star
                lastMatch = lastMatch + 1;
                sp = lastMatch;
                pp = star + 1;
            } else {
                return false;
            }
        }
        // If there are characters in pattern that has not been matched, return false
        // otherwise, return true
        while (pp < parray.length && parray[pp] == '*')
            pp = pp + 1;
        return pp == parray.length;
    }
}