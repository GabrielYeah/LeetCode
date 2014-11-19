// Given a string containing just the characters '(', ')', '{', '}', '[' and ']', determine if the input string is valid.

// The brackets must close in the correct order, "()" and "()[]{}" are all valid but "(]" and "([)]" are not.

// Always check stack before pop
public class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0)
            return true;
        Stack<Character> stack = new Stack<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
                continue;
            }
            if (stack.size() == 0)
                return false;
            char p = stack.pop();
            if (!((p == '(' && c == ')') || (p == '{' && c == '}') || (p == '[' && c == ']')))
                return false;
        }
        return stack.size() == 0;
    }
}