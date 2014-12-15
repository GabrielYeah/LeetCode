// Given an unsorted array, find the maximum difference between the successive elements in its sorted form.

// Try to solve it in linear time/space.

// Return 0 if the array contains less than 2 elements.

// You may assume all elements in the array are non-negative integers and fit in the 32-bit signed integer range.

// Bucket Sort
public class Solution {
    public int maximumGap(int[] num) {
        if (num == null || num.length < 2)
            return 0;
        int max = num[0];
        int min = num[0];
        for (int i : num) {
            max = Math.max(max, i);
            min = Math.min(min, i);
        }
        int gap = (int)Math.ceil((double)(max - min) / (double)(num.length - 1));
        int[] maxBucket = new int[num.length - 1];
        int[] minBucket = new int[num.length - 1];
        boolean[] filled = new boolean[num.length - 1];
        for (int i : num) {
            if (i == min || i == max)
                continue;
            int index = (i - min) / gap;
            if (filled[index]) {
                maxBucket[index] = Math.max(maxBucket[index], i);
                minBucket[index] = Math.min(minBucket[index], i);
            } else {
                filled[index] = true;
                maxBucket[index] = i;
                minBucket[index] = i;
            }
        }
        int res = 0;
        int pre = min;
        for (int i = 0; i < num.length - 1; i++) {
            if (!filled[i])
                continue;
            res = Math.max(minBucket[i] - pre, res);
            pre = maxBucket[i];
        }
        res = Math.max(max - pre, res);
        return res;
    }
}