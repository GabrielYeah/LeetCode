// Related to question Excel Sheet Column Title

// Given a column title as appear in an Excel sheet, return its corresponding column number.

// For example:

//     A -> 1
//     B -> 2
//     C -> 3
//     ...
//     Z -> 26
//     AA -> 27
//     AB -> 28 

// Aced.
public class Solution {
    public int titleToNumber(String s) {
        StringBuilder sb = new StringBuilder(s);
        char[] array = sb.reverse().toString().toCharArray();
        int res = 0;
        int base = 1;
        for (int i = 0; i < array.length; i++) {
            int digit = array[i] - 'A' + 1;
            res += base * digit;
            base = base * 26;
        }
        return res;
    }
}