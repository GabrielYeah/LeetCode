// Given two binary strings, return their sum (also a binary string).

// For example,
// a = "11"
// b = "1"
// Return "100".

// First attempt. Doing char by char. Messy code.
public class Solution1 {
    public String addBinary(String a, String b) {
        if (a == null || b == null)
            return "";
        char[] as = a.length() >= b.length() ? a.toCharArray() : b.toCharArray();
        char[] bs = a.length() < b.length() ? a.toCharArray() : b.toCharArray();
        char[] rs = new char[as.length];
        boolean carry = false;
        for (int i = as.length - 1, j = bs.length - 1; j >= 0; i--, j--) {
            if (as[i] == '1' && bs[j] == '1') {
                rs[i] = carry ? '1' : '0';
                carry = true;
            } else if (as[i] == '0' && bs[j] == '0') {
                rs[i] = carry ? '1' : '0';
                carry = false;
            } else {
                rs[i] = carry ? '0' : '1';
            }
        }
        
        for (int i = as.length - bs.length - 1; i >= 0; i--) {
            if (as[i] == '1') {
                rs[i] = carry ? '0' : '1';
            } else {
                rs[i] = carry ? '1' : '0';
                carry = false;
            }
        }
        String result = new String(rs);
        if (carry)
            result = '1' + result;
        return result;
    }
}

// Final version. A more elegant way to write the code.
public class Solution {
    public String addBinary(String a, String b) {
        if (a == null || b == null)
            return "";
        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = a.length() - 1, j = b.length() - 1; i >= 0 || j >= 0; i--, j--) {
            int num1 = i >= 0 ? a.charAt(i) - '0' : 0;
            int num2 = j >= 0 ? b.charAt(j) - '0' : 0;
            int sum = num1 + num2 + carry;
            result.insert(0, sum % 2);
            carry = sum / 2;
        }
        if (carry == 1)
            result.insert(0, '1');
        return result.toString();
    }
}