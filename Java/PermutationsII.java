// Given a collection of numbers that might contain duplicates, return all possible unique permutations.

// For example,
// [1,1,2] have the following unique permutations:
// [1,1,2], [1,2,1], and [2,1,1].

// Aced. Use a hash set to record expanded numbers at each level.
public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
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
        Set<Integer> expanded = new HashSet<Integer>();
        for (int i = 0; i < num.length; ++i) {
            if (visited[i] || expanded.contains(num[i]))
                continue;
            expanded.add(num[i]);
            visited[i] = true;
            path.add(num[i]);
            search(num, visited, path, result);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}

// Improved version. No need to declare hash set to save the states.
// Sort the array at first. Then skip the same elements and only visit the last one
// of the continuous same elements.
public class Solution {
    public List<List<Integer>> permuteUnique(int[] num) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        if (num == null || num.length == 0)
            return result;
        Arrays.sort(num);
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
            if (i > 0 && num[i] == num[i - 1] && !visited[i - 1])
                continue;
            visited[i] = true;
            path.add(num[i]);
            search(num, visited, path, result);
            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}