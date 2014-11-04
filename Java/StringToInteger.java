// Implement atoi to convert a string to an integer.

// Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

// Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.

// Requirements for atoi:
// The function first discards as many whitespace characters as necessary until the first non-whitespace character is found. Then, starting from this character, takes an optional initial plus or minus sign followed by as many numerical digits as possible, and interprets them as a numerical value.

// The string can contain additional characters after those that form the integral number, which are ignored and have no effect on the behavior of this function.

// If the first sequence of non-whitespace characters in str is not a valid integral number, or if no such sequence exists because either str is empty or it contains only whitespace characters, no conversion is performed.

// If no valid conversion could be performed, a zero value is returned. If the correct value is out of the range of representable values, INT_MAX (2147483647) or INT_MIN (-2147483648) is returned.

// Detail implementation.
public class Solution {
    public int atoi(String str) {
        if (str == null || str.length() == 0)
            return 0;
        str = str.trim();
        boolean minus = false, canMark = true;
        int result = 0;
        for (char c : str.toCharArray()) {
            if (!Character.isDigit(c)) {
                // Handle the situation when new char is not digit
                if ((c == '+' || c == '-') && canMark) {
                    canMark = false;
                    minus = c == '-' ? true : false;
                } else {
                    return minus ? -result : result;
                }
            } else {
                int digit = c - '0';
                // Check whether appending a new digit will cause
                // the result integer overflow
                if ((result > Integer.MAX_VALUE / 10) || 
                    (result == Integer.MAX_VALUE / 10 && digit > Integer.MAX_VALUE % 10)) {
                    return minus ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                } else {
                    result = result * 10 + digit;
                }
            }
        }
        return minus ? -result : result;
    }
}