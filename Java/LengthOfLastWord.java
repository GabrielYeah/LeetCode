// Given a string s consists of upper/lower-case alphabets and empty space characters ' ', return the length of last word in the string.

// If the last word does not exist, return 0.

// Note: A word is defined as a character sequence consists of non-space characters only.

// For example, 
// Given s = "Hello World",
// return 5.

// Cheat using split..
public class Solution1 {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)
            return 0;
        String[] strs = s.split(" ");
        if (strs.length == 0)
            return 0;
        String str = strs[strs.length - 1];
        return str.length();
    }
}

// Normal way to solve this
public class Solution {
    public int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0)
            return 0;
        char[] array = s.toCharArray();
        int curr = 0, prev = 0;
        for (char c : array) {
            if (c == ' ') {
                prev = curr == 0 ? prev : curr;
                curr = 0;
            } else {
                curr++;
            }
        }
        return curr == 0 ? prev : curr;
    }
}