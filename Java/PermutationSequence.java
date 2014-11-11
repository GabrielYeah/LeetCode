// The set [1,2,3,…,n] contains a total of n! unique permutations.

// By listing and labeling all of the permutations in order,
// We get the following sequence (ie, for n = 3):

// "123"
// "132"
// "213"
// "231"
// "312"
// "321"
// Given n and k, return the kth permutation sequence.

// Note: Given n will be between 1 and 9 inclusive.

// A trick math problem.
public class Solution {
    public String getPermutation(int n, int k) {
        if (k <= 0)
            return "";
        StringBuilder str = new StringBuilder();
        List<Integer> nums = new ArrayList<Integer>();
        int all = 1;
        for (int i = 1; i <= n; ++i) {
            nums.add(i);
            all *= i;
        }
        k = k - 1;
        for (int i = 0; i < n; i++) {
            all = all / (n - i);
            int select = k / all;
            str.append(nums.get(select));
            nums.remove(select);
            k = k % all;
        }
        return str.toString();
    }
}