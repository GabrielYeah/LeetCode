// Evaluate the value of an arithmetic expression in Reverse Polish Notation.

// Valid operators are +, -, *, /. Each operand may be an integer or another expression.

// Some examples:
//   ["2", "1", "+", "3", "*"] -> ((2 + 1) * 3) -> 9
//   ["4", "13", "5", "/", "+"] -> (4 + (13 / 5)) -> 6

public class Solution {
    public int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0)
            return Integer.MIN_VALUE;
            
        Stack stack = new Stack();
        for (String str : tokens) {
            if (isOperator(str))
                stack.push(calculate((Integer)stack.pop(), (Integer)stack.pop(), str.charAt(0)));
            else
                stack.push(Integer.parseInt(str));
        }
        return (Integer)stack.pop();
    }
    
    public boolean isOperator(String str) {
        if (str.length() > 1 || str.length() == 0)
            return false;
        char c = str.charAt(0);
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    public Integer calculate(Integer b, Integer a, char c) {
        Integer result = 0;
        switch (c) {
            case '+' : result = a + b; break;
            case '-' : result = a - b; break;
            case '*' : result = a * b; break;
            case '/' : result = a / b; break;
            default: break;
        }
        return result;
    }
}