// Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area of largest rectangle in the histogram.

// Above is a histogram where width of each bar is 1, given height = [2,1,5,6,2,3].

// The largest rectangle is shown in the shaded area, which has area = 10 unit.

// For example,
// Given height = [2,1,5,6,2,3],
// return 10.

// First attempt. TLE.
public class Solution {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int max = 0;
        int[] mins = new int[height.length];
        for (int k = 1; k <= height.length; ++k) {
            for (int i = 0; i <= height.length - k; ++i) {
                if (k == 1)
                    mins[i] = height[i];
                else
                    mins[i] = Math.min(mins[i], mins[i + 1]);
                max = Math.max(max, mins[i] * k);
            }
        }
        return max;
    }
}

// Second attempt. Accepted. Idea is simple, for each height, scan the left area,
// and record the max value. Including a cutting edge: don't calcuate those heights 
// that the right height is larger.
public class Solution {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int max = 0;
        for (int i = 0; i < height.length; ++i) {
            if (i + 1 < height.length && height[i] <= height[i + 1])
                continue;
            int min = height[i];
            for (int j = i; j >= 0; --j) {
                min = Math.min(height[j], min);
                max = Math.max(min * (i - j + 1), max);
            }
        }
        return max;
    }
}

// Final version. A very tricky one.
public class Solution {
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0)
            return 0;
        int[] copy = new int[height.length + 1];
        copy = Arrays.copyOf(height, height.length + 1);
        // Use a stack to store the index of increasing histograms.
        // If a rect has a lower height than the top of the stack,
        // pop the stack and calculate the total area.
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0, max = 0;
        while (i < copy.length) {
            if (stack.isEmpty() || copy[i] >= copy[stack.peek()]) {
                stack.push(i);
                i++;
            } else {
                int top = stack.pop();
                max = Math.max(max, copy[top] * (stack.isEmpty() ? i : i - stack.peek() - 1));
            }
        }
        return max;
    }
}