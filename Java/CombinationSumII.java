// Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

// Each number in C may only be used once in the combination.

// Note:
// All numbers (including target) will be positive integers.
// Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
// The solution set must not contain duplicate combinations.
// For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
// A solution set is: 
// [1, 7] 
// [1, 2, 5] 
// [2, 6] 
// [1, 1, 6] 

// DFS. Made a mistake by not ruling out duplicates.
public class Solution {
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0)
            return result;
        Arrays.sort(num);
        dfs(num, 0, target, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void dfs(int[] num, int start, int target, List<Integer> path, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        if (start == num.length)
            return;
        int prev = -1;
        for (int i = start; i < num.length; ++i) {
            if (num[i] > target)
                return;
            if (prev == num[i])
                continue;
            prev = num[i];
            path.add(num[i]);
            dfs(num, i + 1, target - num[i], path, result);
            path.remove(path.size() - 1);
        }
    }
}