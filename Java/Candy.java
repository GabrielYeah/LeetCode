// There are N children standing in a line. Each child is assigned a rating value.

// You are giving candies to these children subjected to the following requirements:

// Each child must have at least one candy.
// Children with a higher rating get more candies than their neighbors.
// What is the minimum candies you must give?

// A very tricky one.
// DP problem. If an element is affected by its neighbor, 
// scan the list twice is a good solution.
// Scan from left to right, then from right to left.
public class Solution {
    public int candy(int[] ratings) {
        if (ratings == null || ratings.length == 0)
            return 0;
        int len = ratings.length;
        int candies[] = new int[len];
        Arrays.fill(candies, 1);
        // Scan from left to right
        // Increase right value if larger than left
        for (int i = 1; i < len; ++i)
            if (ratings[i] > ratings[i - 1])
                candies[i] = candies[i - 1] + 1;
        // Scan from right to left
        // Increase left value if larger than right and should be increased
        for (int i = len - 2; i >= 0; --i)
            if (ratings[i] > ratings[i + 1] && candies[i] <= candies[i + 1])
                candies[i] = candies[i + 1] + 1;
        int result = 0;
        for (int i = 0; i < len; ++i)
            result += candies[i];
        return result;
    }
}