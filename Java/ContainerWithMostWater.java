// Given n non-negative integers a1, a2, ..., an, where each represents a point at coordinate (i, ai). n vertical lines are drawn such that the two endpoints of line i is at (i, ai) and (i, 0). Find two lines, which together with x-axis forms a container, such that the container contains the most water.

// Note: You may not slant the container.

// Two pointers. Scan from two sides.
public class Solution {
    public int maxArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int max = 0, l = 0, r = height.length - 1;
        while (l < r) {
            max = Math.max(max, Math.min(height[l], height[r]) * (r - l));
            // If height[r] is smaller, moving r forward will not be worse
            if (height[l] > height[r])
                r--;
            else
                l++;
        }
        return max;
    }
}