// The count-and-say sequence is the sequence of integers beginning as follows:
// 1, 11, 21, 1211, 111221, ...

// 1 is read off as "one 1" or 11.
// 11 is read off as "two 1s" or 21.
// 21 is read off as "one 2, then one 1" or 1211.
// Given an integer n, generate the nth sequence.

// Note: The sequence of integers will be represented as a string.

// Scan and count the same chars.
public class Solution {
    public String countAndSay(int n) {
        if (n <= 0)
            return "";
        if (n == 1)
            return "1";
        String result = "11";
        for (int k = 3; k <= n; ++k) {
            char[] a = Arrays.copyOf(result.toCharArray(), result.length() + 1);
            StringBuilder sb = new StringBuilder();
            for (int count = 1, i = 1; i < a.length; ++i) {
                if (a[i] == a[i - 1]) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append((char)a[i - 1]);
                    count = 1;
                }
            }
            result = sb.toString();
        }
        return result;
    }
}