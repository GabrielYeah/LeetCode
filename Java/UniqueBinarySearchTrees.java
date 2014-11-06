// Given n, how many structurally unique BST's (binary search trees) that store values 1...n?

// For example,
// Given n = 3, there are a total of 5 unique BST's.

//    1         3     3      2      1
//     \       /     /      / \      \
//      3     2     1      1   3      2
//     /     /       \                 \
//    2     1         2                 3

// Made a mistake handling the index
public class Solution {
    public int numTrees(int n) {
        if (n <= 0)
            return 0;
        if (n <= 2)
            return n;
        int[] nums = new int[n];
        nums[0] = 1; nums[1] = 2;
        for (int i = 2; i < n; ++i) {
            for (int j = 0; j <= i; ++j) {
                int left = j > 0 ? nums[j - 1] : 1;
                int right = j < i ? nums[i - j - 1] : 1;
                nums[i] += left * right;
            }
        }
        return nums[n - 1];
    }
}

// A cleaner way to write the code
public class Solution {
    public int numTrees(int n) {
        if (n <= 0)
            return 0;
        int[] nums = new int[n + 1];
        nums[0] = 1; nums[1] = 1;
        for (int i = 2; i <= n; ++i)
            for (int j = 0; j < i; ++j)
                nums[i] += nums[j] * nums[i - j - 1];
        return nums[n];
    }
}