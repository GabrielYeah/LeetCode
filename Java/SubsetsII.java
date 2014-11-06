// Given a collection of integers that might contain duplicates, S, return all possible subsets.

// Note:
// Elements in a subset must be in non-descending order.
// The solution set must not contain duplicate subsets.
// For example,
// If S = [1,2,2], a solution is:

// [
//   [2],
//   [1],
//   [1,2,2],
//   [2,2],
//   [1,2],
//   []
// ]

public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        result.add(new ArrayList<Integer>());
        if (num == null || num.length == 0)
            return result;
        Arrays.sort(num);
        int start = 0;
        for (int i = 0; i < num.length; ++i) {
            int len = result.size();
            for (int j = start; j < len; ++j) {
                List<Integer> list = new ArrayList<Integer>(result.get(j));
                list.add(num[i]);
                result.add(list);
            }
            // Start is to record the starting index for next layer.
            // If next num is equal to the current, set the start to current
            // before the newly added elements so that next element will
            // only be added to sets that are generated in current layer.
            start = i < num.length - 1 && num[i] == num[i + 1] ? len : 0;
        }
        return result;
    }
}