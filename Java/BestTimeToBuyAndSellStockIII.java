// Say you have an array for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete at most two transactions.

// Note:
// You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

// First attempt. TLE. 
public class Solution1 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int[][] opt = new int[prices.length + 1][prices.length + 1];
        for (int i = 0; i <= prices.length; ++i) {
            opt[0][i] = maxProfit(prices, 0, i);
            opt[i][prices.length] = maxProfit(prices, i, prices.length);
        }
        int max = 0;
        for (int i = 0; i <= prices.length; ++i)
            max = Math.max(max, opt[0][i] + opt[i][prices.length]);
        return max;
    }
    
    private int maxProfit(int[] prices, int s, int e) {
        if (s >= e)
            return 0;
        int min = prices[s], sum = 0;
        for (int i = s; i < e; ++i) {
            min = Math.min(min, prices[i]);
            sum = Math.max(sum, prices[i] - min);
        }
        return sum;
    }
}

// Final version. If a value is calculated by iterating, then the
// progress should be recorded.
public class Solution {
    // Key is to divide the array into two, so that we can do one
    // transcation on one array.
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        // Use two arrays to record the progress
        // When finding max profit of a subarray, we will need to
        // iterate the array. During the iteration, we can record
        // the current max profit so that there's no need to redo
        // the calculation
        int[] left = new int[prices.length];
        int[] right = new int[prices.length];
        left[0] = 0;
        int min = prices[0];
        for (int i = 1; i < prices.length; ++i) {
            min = Math.min(prices[i], min);
            left[i] = Math.max(prices[i] - min, left[i - 1]);
        }
        right[prices.length - 1] = 0;
        int max = prices[prices.length - 1];
        for (int i = prices.length - 2; i >= 0; --i) {
            max = Math.max(prices[i], max);
            right[i] = Math.max(max - prices[i], right[i + 1]);
        }
        int sum = 0;
        for (int i = 0; i < prices.length; ++i)
            sum = Math.max(left[i] + right[i], sum);
        return sum;
    }
}