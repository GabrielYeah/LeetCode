// Given an input string, reverse the string word by word.

// For example,
// Given s = "the sky is blue",
// return "blue is sky the".

// Clarification:
// What constitutes a word?
// A sequence of non-space characters constitutes a word.
// Could the input string contain leading or trailing spaces?
// Yes. However, your reversed string should not contain leading or trailing spaces.
// How about multiple spaces between two words?
// Reduce them to a single space in the reversed string.

public class Solution {
    public String reverseWords(String s) {
        String result = "";
        int endPos = -1;
        for (int startPos = s.length() - 1; startPos >= 0; --startPos) {
            if (s.charAt(startPos) == ' ') {
                if (startPos <= endPos) {
                    result += " " + s.substring(startPos + 1, endPos + 1);
                    endPos = -1;
                }
            } else {
                if (startPos > endPos)
                    endPos = startPos;
                if (startPos == 0)
                    result += " " + s.substring(startPos, endPos + 1);
            }
        }
        
        if (result.length() > 0)
            result = result.substring(1, result.length());
        
        return result;
    }
}