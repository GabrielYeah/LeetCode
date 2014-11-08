// Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.

// For example,
// If n = 4 and k = 2, a solution is:

// [
//   [2,4],
//   [3,4],
//   [2,3],
//   [1,2],
//   [1,3],
//   [1,4],
// ]

// DFS. Aced!
public class Solution {
    public List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (n < 1 || k < 1)
            return result;
        combine(n, k, 1, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void combine(int n, int k, int curr, List<Integer> path, List<List<Integer>> result) {
        if (k == 0) {
            result.add(new ArrayList<Integer>(path));
            return;
        } else if (curr > n) {
            return;
        }
        
        for (int i = curr; i <= n; ++i) {
            path.add(i);
            combine(n, k - 1, i + 1, path, result);
            path.remove(path.size() - 1);
        }
    }
}