// Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in the array which gives the sum of zero.

// Note:
// Elements in a triplet (a,b,c) must be in non-descending order. (ie, a ≤ b ≤ c)
// The solution set must not contain duplicate triplets.
//     For example, given array S = {-1 0 1 2 -1 -4},

//     A solution set is:
//     (-1, 0, 1)
//     (-1, -1, 2)

// Scan from two sides. 
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length < 3)
            return result;
        Arrays.sort(num);
        for (int i = 0; i < num.length - 2 && num[i] <= 0; i++) {
            while (i > 0 && i < num.length - 2 && num[i - 1] == num[i])
                i++;
            for (int j = num.length - 1; j > i + 1 && num[j] >= 0; j--) {
                while (j < num.length - 1 && j > i + 1 && num[j + 1] == num[j])
                    j--;
                for (int k = i + 1; k < j; k++) {
                    if (num[i] + num[j] + num[k] == 0) {
                        result.add(new ArrayList<Integer>(Arrays.asList(num[i], num[k], num[j])));
                        break;
                    }
                }
            }
        }
        return result;
    }
}

// Final version. Use a cleaner thought. Made a mistake on line 51 & 53 by comparing with wrong elements.
public class Solution {
    public List<List<Integer>> threeSum(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length < 3)
            return result;
        Arrays.sort(num);
        for (int i = 0; i < num.length; i++) {
            int l = i + 1, r = num.length - 1, target = 0 - num[i];
            while (l < r) {
                if (num[l] + num[r] == target) {
                    result.add(new ArrayList<Integer>(Arrays.asList(num[i], num[l], num[r])));
                    l++;
                    r--;
                    while (l < r && num[l] == num[l - 1])
                        l++;
                    while (r > l && num[r] == num[r + 1])
                        r--;
                } else if (num[l] + num[r] > target) {
                    r--;
                } else {
                    l++;
                }
            }
            while (i < num.length - 1 && num[i] == num[i + 1])
                i++;
        }
        return result;
    }
}