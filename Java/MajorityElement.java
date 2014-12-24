// Given an array of size n, find the majority element. The majority element is the element that appears more than ⌊ n/2 ⌋ times.

// You may assume that the array is non-empty and the majority element always exist in the array.

public class Solution {
    public int majorityElement(int[] num) {
        int candidate = num[0];
        int count = 1;
        for (int i = 1; i < num.length; i++) {
            if (candidate == num[i]) {
                count++;
            } else if (count == 1) {
                candidate = num[i];
            } else {
                count--;
            }
        }
        return candidate;
    }
}