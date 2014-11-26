// Given an array S of n integers, find three integers in S such that the sum is closest to a given number, target. Return the sum of the three integers. You may assume that each input would have exactly one solution.

//     For example, given array S = {-1 2 1 -4}, and target = 1.

//     The sum that is closest to the target is 2. (-1 + 2 + 1 = 2).

public class Solution {
    public int threeSumClosest(int[] num, int target) {
        if (num == null || num.length < 3)
            return 0;
        Arrays.sort(num);
        boolean valueSet = false;
        int result = 0;
        for (int i = 0; i < num.length; i++) {
            int start = i + 1, end = num.length - 1;
            while (start < end) {
                int tmp = num[start] + num[end] + num[i];
                if (!valueSet || Math.abs(target - tmp) < Math.abs(target - result)) {
                    valueSet = true;
                    result = tmp;
                }
                if (tmp < target) {
                    start++;
                    while (start < end && num[start] == num[start - 1])
                        start++;
                } else {
                    end--;
                    while (start < end && num[end] == num[end + 1])
                        end--;  
                }
            }
            while (i < num.length - 2 && num[i] == num[i + 1])
                i++;
        }
        return result;
    }
}