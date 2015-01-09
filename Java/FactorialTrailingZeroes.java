// Given an integer n, return the number of trailing zeroes in n!.

// Note: Your solution should be in logarithmic time complexity.

// The number of zero is determined by 2s and 5s.
// It can be proved that there are always more 2s, so just count 5s.
// n / x means the count of multiple of x from 1...n
public class Solution {
    public int trailingZeroes(int n) {
        int res = 0;
        while (n > 0) {
            res += n / 5;
            n /= 5;
        }
        return res;
    }
}