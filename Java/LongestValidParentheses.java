// Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

// For "(()", the longest valid parentheses substring is "()", which has length = 2.

// Another example is ")()())", where the longest valid parentheses substring is "()()", which has length = 4.

// First attempt. TLE.
public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        int len = s.length(), max = 0;
        char[] array = s.toCharArray();
        boolean[][] valid = new boolean[len][len];
        for (int i = 0; i < len - 1; ++i) {
            valid[i][i + 1] = array[i] == '(' && array[i + 1] == ')';
            max = valid[i][i + 1] ? 2 : 0;
        }
        for (int k = 4; k < len; k = k + 2) {
            for (int i = 0; i <= len - k; ) {
                boolean v = array[i] == '(' && array[i + k - 1] == ')' && valid[i + 1][i + k - 2];
                v = v || array[i] == '(' && array[i + 1] == ')' && valid[i + 2][i + k - 1];
                v = v || array[i + k - 2] == '(' && array[i + k - 1] == ')' && valid[i][i + k - 3];
                valid[i][i + k - 1] = v;
                if (v) {
                    max = k;
                    i = i + k;
                } else {
                    i = i + 1;
                }
            }
        }
        return max;
    }
}

public class Solution {
    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0)
            return 0;
        // Maintain a stack to store all the possible right left boundary
        Stack<Integer> stack = new Stack<Integer>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ')' && !stack.isEmpty() && s.charAt(stack.peek()) == '(') {
                // If there is a match, pop the matched from stack
                stack.pop();
                if (stack.isEmpty())
                    // Stack is empty, then matches to the head of the string
                    max = i + 1;
                else
                    // Else, to the last unmatched character
                    max = Math.max(i - stack.peek(), max);
            } else {
                // If a character cannot match with others, push to stack
                stack.push(i);
            }
        }
        return max;
    }
}