// Given n non-negative integers representing an elevation map where the width of each bar is 1, compute how much water it is able to trap after raining.

// For example, 
// Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

// The above elevation map is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped. Thanks Marcos for contributing this image!

// First attempt. Use a stack to record the min points.
public class Solution {
    public int trap(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        Stack<Integer> stack = new Stack<Integer>();
        int i = 0, result = 0;
        while (i < A.length) {
            if (stack.isEmpty() || A[i] <= A[stack.peek()]) {
                // If stack empty or peek larger or equal than curr, push to stack
                stack.push(i);
                i = i + 1;
            } else if (A[i] > A[stack.peek()]) {
                // Else, get stack peek
                int minIndex = stack.pop();
                // Find the earliest point with the same value with the stack peek
                while (!stack.isEmpty() && A[minIndex] == A[stack.peek()])
                    stack.pop();
                if (stack.isEmpty()) {
                    // If has no left boundary, just push curr
                    stack.push(i);
                    i = i + 1;
                } else {
                    // Calculate the amount with left boundary
                    int maxIndex = A[stack.peek()] < A[i] ? stack.peek() : i;
                    result += (i - stack.peek() - 1) * (A[maxIndex] - A[minIndex]);
                }
            }
        }
        return result;
    }
}

// Final version. Use the same idea as candy. For each bar, the amount of water is determined
// by the largest on its right and left. Scan twice.
public class Solution {
    public int trap(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int[] left = new int[A.length], right = new int[A.length];
        int max = 0, result = 0;
        left[0] = 0;
        for (int i = 1; i < A.length; i++) {
            max = Math.max(A[i - 1], max);
            left[i] = max;
        }
        max = 0;
        right[A.length - 1] = 0;
        for (int i = A.length - 2; i >= 0; i--) {
            max = Math.max(A[i + 1], max);
            right[i] = max;
        }
        for (int i = 0; i < A.length; i++)
            result += Math.max(0, Math.min(left[i], right[i]) - A[i]);
            
        return result;
    }
}