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

// Corrected version. Fix the overflow issue when Integer.MIN_VALUE / -1.
public class Solution {
    public int divide(int dividend, int divisor) {
        boolean pos = dividend > 0 && divisor > 0 || dividend < 0 && divisor < 0;
        if (divisor == 0)
            return Integer.MAX_VALUE;
        if (divisor == Integer.MIN_VALUE)
            return dividend == Integer.MIN_VALUE ? 1 : 0;
        int res = 0;
        divisor = Math.abs(divisor);
        if (dividend == Integer.MIN_VALUE) {
            dividend += divisor;
            res = 1;
        }
        dividend = Math.abs(dividend);
        int tmpDivisor = divisor, tmpRes = 1;
        while (dividend >= divisor) {
            if (res >= Integer.MAX_VALUE - tmpRes)
                return pos ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            if (dividend >= tmpDivisor) {
                dividend -= tmpDivisor;
                res += tmpRes;
                tmpDivisor = tmpDivisor << 1;
                tmpRes = tmpRes << 1;
            } else {
                tmpDivisor = divisor;
                tmpRes = 1;
            }
        }
        return pos ? res : -res;
    }
}