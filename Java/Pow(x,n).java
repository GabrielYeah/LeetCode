// Implement pow(x, n).

// Iterative solution.
public class Solution {
    public double pow(double x, int n) {
        if (n == 0)
            return 1;
        boolean flag = n < 0;
        n = n > 0 ? n : -n;
        int count = 1;
        double result = 1, prev = x;
        while (n > 0) {
            result *= prev;
            n -= count;
            if (n > count * 2) {
                prev = prev * prev;
                count = count + count;
            } else {
                prev = x;
                count = 1;
            }
        }
        if (flag)
            result = 1 / result;
        return result;
    }
}

// Divide and conquer.
public class Solution {
    public double pow(double x, int n) {
        if (n > 0)
            return divideAndConquer(x, n);
        else
            return 1.0 / divideAndConquer(x, -n);
    }
    
    private double divideAndConquer(double x, int n) {
        if (n == 0)
            return 1;
        double val = divideAndConquer(x, n / 2);
        if (n % 2 == 1)
            return val * val * x;
        else
            return val * val;
            
    }
}