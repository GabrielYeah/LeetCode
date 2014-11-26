// Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? Find all unique quadruplets in the array which gives the sum of target.

// Note:
// Elements in a quadruplet (a,b,c,d) must be in non-descending order. (ie, a ≤ b ≤ c ≤ d)
// The solution set must not contain duplicate quadruplets.
//     For example, given array S = {1 0 -1 0 -2 2}, and target = 0.

//     A solution set is:
//     (-1,  0, 0, 1)
//     (-2, -1, 1, 2)
//     (-2,  0, 0, 2)

public class Solution {
    public List<List<Integer>> fourSum(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length < 4)
            return result;
        Arrays.sort(num);
        for (int i = 0; i < num.length - 3; i++) {
            for (int j = i + 1; j < num.length - 2; j++) {
                int start = j + 1, end = num.length - 1;
                while (start < end) {
                    int val = num[start] + num[end] + num[i] + num[j];
                    if (val == target) {
                        result.add(Arrays.asList(num[i], num[j], num[start], num[end]));
                        start++;
                        end--;
                        while (start < end && num[start] == num[start - 1])
                            start++;
                        while (start < end && num[end] == num[end + 1])
                            end--;
                    } else if (val > target) {
                        end--;
                    } else {
                        start++;
                    }
                }
                while (j < num.length - 2 && num[j + 1] == num[j])
                    j++;
            }
            while (i < num.length - 3 && num[i + 1] == num[i])
                i++;
        }
        return result;
    }
}