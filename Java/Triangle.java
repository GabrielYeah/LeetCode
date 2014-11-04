// Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

// For example, given the following triangle
// [
//      [2],
//     [3,4],
//    [6,5,7],
//   [4,1,8,3]
// ]
// The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).

// Note:
// Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.

public class Solution {
    public int minimumTotal(List<List<Integer>> triangle) {
        if (triangle == null || triangle.size() == 0 || triangle.get(0).size() == 0)
            return 0;
        int len = triangle.size();
        // Use bottom up iteration.
        int[] opt = new int[len];
        for (int i = 0; i < len; ++i)
            opt[i] = triangle.get(len - 1).get(i);
        for (int i = len - 2; i >= 0; --i)
            for (int j = 0; j <= i ; ++j)
                opt[j] = Math.min(opt[j], opt[j + 1]) + triangle.get(i).get(j);
                
        return opt[0];
    }
}