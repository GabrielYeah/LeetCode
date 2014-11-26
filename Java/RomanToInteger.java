// Given a roman numeral, convert it to an integer.

// Input is guaranteed to be within the range from 1 to 3999.

// Aced.
public class Solution {
    public int romanToInt(String s) {
        int result = 0;
        s = new StringBuilder(s).reverse().toString();
        int prev = 0;
        for (char c : s.toCharArray()) {
            int curr = value(c);
            if (curr < prev)
                result -= curr;
            else
                result += curr;
            prev = curr;
        }
        return result;
    }
    
    private int value(char c) {
        int res = 0;
        switch (c) {
            case 'I': res = 1; break;
            case 'V': res = 5; break;
            case 'X': res = 10; break;
            case 'L': res = 50; break;
            case 'C': res = 100; break;
            case 'D': res = 500; break;
            case 'M': res = 1000; break;
            default: res = 0;
        }
        return res;
    }
}