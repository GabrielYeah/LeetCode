// Divide two integers without using multiplication, division and mod operator.

// First attempt. Use a long to prevent overflow.
public class Solution {
    public int divide(int dividend, int divisor) {
        boolean neg = dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0;
        long first = dividend < 0 ? -(long)dividend : (long)dividend;
        long second = divisor < 0 ? -(long)divisor : (long)divisor;
        long tmp = second, count = 1, total = 0;
        while (first >= second) {
            if (first >= tmp) {
                first -= tmp;
                total += count;
                count += count;
                tmp += tmp;
            } else {
                tmp = second;
                count = 1;
            }
        }
        total = neg ? -total : total;
        return (int)total;
    }
}

// Final version. Check to prevent overflow at the beginning.
public class Solution {
    public int divide(int dividend, int divisor) {
        boolean neg = dividend < 0 && divisor > 0 || dividend > 0 && divisor < 0;
        int total = 0;
        if (dividend == Integer.MIN_VALUE) {
            total = 1;
            dividend += Math.abs(divisor);
        }
        if (divisor == Integer.MIN_VALUE) {
            return total;
        }
        dividend = dividend < 0 ? -dividend : dividend;
        divisor = divisor < 0 ? -divisor : divisor;
        int tmp = divisor, count = 1;
        while (dividend >= divisor) {
            if (dividend >= tmp) {
                dividend -= tmp;
                total += count;
                count += count;
                tmp += tmp;
            } else {
                tmp = divisor;
                count = 1;
            }
        }
        total = neg ? -total : total;
        return total;
    }
}