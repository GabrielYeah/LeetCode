// Given an array of non-negative integers, you are initially positioned at the first index of the array.

// Each element in the array represents your maximum jump length at that position.

// Determine if you are able to reach the last index.

// For example:
// A = [2,3,1,1,4], return true.

// A = [3,2,1,0,4], return false.

// First attempt. TLE.
public class Solution {
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0)
            return false;
        boolean[] reachable = new boolean[A.length];
        reachable[0] = true;
        for (int i = 0; i < A.length; ++i)
            if (reachable[i] && A[0] > 0)
                for (int j = i + 1; j <= A[0] + i && j < A.length; ++j)
                    reachable[j] = true;
        return reachable[A.length - 1];
    }
}

// Simple one. 
public class Solution {
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0)
            return false;
        int canReachIndex = 0;
        for (int i = 0; i < A.length; ++i)
            if (A[i] > 0)
                canReachIndex = Math.max(A[i] + i, canReachIndex); 
            else if (canReachIndex <= i)
                break;
        return canReachIndex >= A.length - 1;
    }
}