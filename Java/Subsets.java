// Given a set of distinct integers, S, return all possible subsets.

// Note:
// Elements in a subset must be in non-descending order.
// The solution set must not contain duplicate subsets.
// For example,
// If S = [1,2,3], a solution is:

// [
//   [3],
//   [1],
//   [2],
//   [1,2,3],
//   [1,3],
//   [2,3],
//   [1,2],
//   []
// ]

// Forget to sort. Otherwise, aced.
public class Solution {
    public List<List<Integer>> subsets(int[] S) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        if (S == null || S.length == 0)
            return result;
        Arrays.sort(S);
        for (int i = 0; i < S.length; ++i) {
            for (int j = result.size() - 1; j >= 0; --j) {
                List<Integer> curr = new ArrayList<Integer>(result.get(j));
                curr.add(S[i]);
                result.add(curr);
            }
        }
        return result;
    }
}