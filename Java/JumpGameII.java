// Given an array of non-negative integers, you are initially positioned at the first index of the array.

// Each element in the array represents your maximum jump length at that position.

// Your goal is to reach the last index in the minimum number of jumps.

// For example:
// Given array A = [2,3,1,1,4]

// The minimum number of jumps to reach the last index is 2. (Jump 1 step from index 0 to 1, then 3 steps to the last index.)

// First attempt. TLE.
public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        int[] mins = new int[A.length];
        Arrays.fill(mins, Integer.MAX_VALUE);
        mins[0] = 1;
        int farthest = A[0];
        for (int i = 0; i < A.length; ++i) {
            if (farthest < i)
                return 0;
            farthest = Math.max(farthest, A[i] + i);
            for (int j = i + 1; j <= i + A[i] && j < A.length; ++j)
                mins[j] = Math.min(mins[j], mins[i] + 1);
        }
        return mins[A.length - 1];
    }
}

// Final version. Greedy: In each step, select a farthest point that can be reached.  
public class Solution {
    public int jump(int[] A) {
        if (A == null || A.length <= 1)
            return 0;
        int count = 0, farthest = 0, i = 0;
        while (i < A.length) {
            if (farthest >= A.length - 1)
                break;
            int tmp = farthest;
            while (i <= tmp) {
                farthest = Math.max(farthest, A[i] + i);
                i++;
            }
            count++;
        }
        return count;
    }
}