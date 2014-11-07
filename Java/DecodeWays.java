// A message containing letters from A-Z is being encoded to numbers using the following mapping:

// 'A' -> 1
// 'B' -> 2
// ...
// 'Z' -> 26
// Given an encoded message containing digits, determine the total number of ways to decode it.

// For example,
// Given encoded message "12", it could be decoded as "AB" (1 2) or "L" (12).

// The number of ways decoding "12" is 2.

// Made a lot of mistakes. Headache...
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        int[] nums = new int[s.length() + 1];
        nums[0] = 1; nums[1] = 1;
        for (int i = 1; i < s.length(); ++i) {
            int curr = s.charAt(i) - '0', prev = s.charAt(i - 1) - '0';
            // If prev + curr >= 30, then return 0
            if (curr == 0 && prev != 1 && prev != 2)
                return 0;
            // Set current nums to previous
            nums[i + 1] = nums[i];
            if (curr == 0) {
                // If curr is 0, then no chance to add more
                continue;
            } else if (i == s.length() - 1) {
                // If curr is last, and can be combined with previous, add more
                if (valid(prev, curr))
                    nums[i + 1] += nums[i - 1];
            } else {
                // If curr is not last, but do not need to combine with next, add more
                if (valid(prev, curr) && s.charAt(i + 1) != '0')
                    nums[i + 1] += nums[i - 1];
            }
        }
        return nums[s.length()];
    }
    
    private boolean valid(int prev, int curr) {
        if (prev == 1)
            return true;
        if (prev == 2)
            return curr <= 6;
        return false;
    }
}

// Consider the process of checking and assigning values
public class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0 || s.charAt(0) == '0')
            return 0;
        if (s.length() == 1)
            return check(s.charAt(0)) ? 1 : 0;
        // Use n2 to represent current value
        // Use n1 n0 to represent previous two values
        int n0 = 0, n1 = 1, n2 = 0;
        // Set initial value
        if (check(s.charAt(0)))
            n2 = 1;
        for (int i = 1; i < s.length(); ++i) {
            n0 = n1; n1 = n2; n2 = 0;
            // If current is not zero, set to previous
            if (check(s.charAt(i)))
                n2 = n1;
            // If current can combine with previous, add previous
            if (check(s.charAt(i - 1), s.charAt(i)))
                n2 += n0;
            if (n2 == 0)
                return 0;
        }
        
        return n2;
    }
    
    private boolean check(char c) {
        return c != '0';
    }
    
    private boolean check(char p, char c) {
        return (p == '1') || (p == '2' && c <= '6');
    }
}