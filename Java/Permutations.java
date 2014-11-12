// Given a collection of numbers, return all possible permutations.

// For example,
// [1,2,3] have the following permutations:
// [1,2,3], [1,3,2], [2,1,3], [2,3,1], [3,1,2], and [3,2,1].

public class Solution {
    public List<List<Integer>> permute(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0)
            return result;
        boolean[] visited = new boolean[num.length];
        search(num, visited, new ArrayList<Integer>(), result);
        return result;
    }
    
    private void search(int[] num, boolean[] visited, List<Integer> path, List<List<Integer>> result) {
        if (path.size() == num.length) {
            result.add(new ArrayList<Integer>(path));
            return;
        }
        for (int i = 0; i < num.length; ++i) {
            if (visited[i])
                continue;
            visited[i] = true;
            path.add(num[i]);
            search(num, visited, path, result);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}