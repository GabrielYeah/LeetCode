// Say you have an array for which the ith element is the price of a given stock on day i.

// Design an algorithm to find the maximum profit. You may complete as many transactions as you like (ie, buy one and sell one share of the stock multiple times). However, you may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

// Final version. Made a couple of small mistakes.
public class Solution {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length == 0)
            return 0;
        int sum = 0, currentSum = 0;
        for (int i = 1; i < prices.length; ++i) {
            if (prices[i] > prices[i - 1]) {
                // If the stock keeps rising, 
                // add the offset to current profit
                currentSum += prices[i] - prices[i - 1];
            } else {
                // Sell the stock when the stock drops
                sum += currentSum;
                currentSum = 0;
            }
        }
        // If the array ends in a rise, add the final profit
        sum += currentSum;
        return sum;
    }
}