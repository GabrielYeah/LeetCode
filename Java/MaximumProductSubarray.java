// Find the contiguous subarray within an array (containing at least one number) which has the largest product.

// For example, given the array [2,3,-2,4],
// the contiguous subarray [2,3] has the largest product = 6.

// First attempt, TLE
public class Solution1 {
    public int maxProduct(int[] A) {
        if (A == null || A.length == 0)
            return 0;
        
        int[] curr = new int[A.length];
        int[] prev = new int[A.length];
        int max = Integer.MIN_VALUE;
        
        for (int i = 0; i < A.length; ++i) {
            prev[i] = A[i];
            max = Math.max(prev[i], max);
        }
            
        for (int k = 1; k < A.length; ++k) {
            for (int i = 0; i < A.length - k; ++i) {
                curr[i] = prev[i] * A[i + k];
                max = Math.max(curr[i], max);
            }
            prev = curr;
        }
        
        return max;
    }
}

// Second attempt, finally AC
public class Solution2 {
    public int maxProduct(int[] A) {
        if (A == null || A.length == 0)
            return 0;
            
        ArrayList<Integer> zeroList = new ArrayList<Integer>();
        zeroList.add(-1);
        for (int i = 0; i < A.length; ++i)
            if (A[i] == 0)
                zeroList.add(i);
        zeroList.add(A.length);
        int max = zeroList.size() > 2 ? 0 : Integer.MIN_VALUE;
        for (int i = 0; i < zeroList.size() - 1; ++i) {
            int start = zeroList.get(i) + 1;
            int end = zeroList.get(i + 1) - 1;
            max = Math.max(max, maxProductWithoutZero(subArray(A, start, end)));
        }
        
        return max;
    }
    
    public int[] subArray(int[] A, int start, int end) {
        if (start > end)
            return null;
        return Arrays.copyOfRange(A, start, end + 1);
    }
    
    public int maxProductWithoutZero(int[] A) {
        if (A == null || A.length == 0)
            return Integer.MIN_VALUE;
        
        ArrayList<Integer> minusList = new ArrayList<Integer>();
        for (int i = 0; i < A.length; ++i)
            if (A[i] < 0)
                minusList.add(i);

        if (minusList.size() % 2 == 0) {
            return production(A, 0, A.length - 1);
        } else if (minusList.size() == 1) {
            return largerPart(A, minusList.get(0));
        } else {
            int last = minusList.size() - 1;
            return Math.max(largerPart(A, minusList.get(0)), largerPart(A, minusList.get(last)));
        }
    }
    
    public int largerPart(int[] A, int anchorPoint) {
        int partA = production(A, 0, anchorPoint - 1);
        int partB = production(A, anchorPoint + 1, A.length - 1);
        int max = Math.max(partA, partB);
        return Math.max(max, A[anchorPoint]);
    }
    
    public int production(int[] A, int start, int end) {
        if (start > end)
            return Integer.MIN_VALUE;
        
        int result = 1;
        for (int i = start; i <= end; ++i)
            result *= A[i];
        return result;
    }
}