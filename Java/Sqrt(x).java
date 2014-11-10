// Implement int sqrt(int x).

// Compute and return the square root of x.

// First attempt. TLE.
public class Solution1 {
    public int sqrt(int x) {
        if (x < 0)
            return 0;
        int l = 0, r = x; 
        while (l <= r) {
            int m = (l + r) / 2;
            if (m * m == x)
                return m;
            if (m * m > x)
                r = m - 1;
            else
                l = m + 1;
        }
        return l;
    }
}

// Better solution. All use binary search. But using * directly will
// easily cause overflow. So we use /. However, if m is possible to be 
// 0, / will not work. So first exclude the possibility of getting 0, that 
// is search from l = 1.
public class Solution {
    public int sqrt(int x) {
        if (x <= 0)
            return 0;
        int l = 1, r = x / 2 + 1; 
        while (l <= r) {
            int m = (l + r) / 2;
            if (x / m >= m && x / (m + 1) < m + 1)
                return m;
            if (x / m < m)
                r = m - 1;
            else
                l = m + 1;
        }
        return 0;
    }
}