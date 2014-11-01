// Given two words (start and end), and a dictionary, find the length of shortest transformation sequence from start to end, such that:

// Only one letter can be changed at a time
// Each intermediate word must exist in the dictionary
// For example,

// Given:
// start = "hit"
// end = "cog"
// dict = ["hot","dot","dog","lot","log"]
// As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
// return its length 5.

// Note:
// Return 0 if there is no such transformation sequence.
// All words have the same length.
// All words contain only lowercase alphabetic characters.

public class Solution1 {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null || start == null || end == null)
            return 0;
        Set<String> neighbors = new HashSet<String>();
        return ladderLength(start, end, dict, 2, neighbors);
    }
    
    private int ladderLength(String start, String end, Set<String> dict, int depth, Set<String> neighbors) {
        if (isNext(start, end, neighbors))
            return depth;
        if (dict.isEmpty())
            return 0;
        int min = Integer.MAX_VALUE;
        Set<String> dictForNext = new HashSet<String>(dict);
        for (String next : dict) {
            if (isNext(start, next, neighbors)) {
                dictForNext.remove(next);
                int len = ladderLength(next, end, dictForNext, depth + 1, neighbors);
                if (len != 0)
                    min = Math.min(min, len);
                dictForNext.add(next);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }
    
    private boolean isNext(String a, String b, Set<String> neighbors) {
        if (neighbors.contains(combine(a, b)))
            return true;
        boolean diff = false;
        for (int i = 0; i < a.length(); ++i) {
            if (a.charAt(i) != b.charAt(i)) {
                if (diff)
                    return false;
                else
                    diff = true;
            }
        }
        if (diff)
            neighbors.add(combine(a, b));
        return diff;
    }
    
    private String combine(String a, String b) {
        return a.compareTo(b) == -1 ? a + b : b + a;
    }
}

// Final version. 
// View this problem as a search problem.
// Finding shortst path can be solved ideally by BFS.
// Another technique is that when doing BFS, no need to use two queues.
// Before each level, record the current size n and iterate n times.
public class Solution {
    public int ladderLength(String start, String end, Set<String> dict) {
        if (dict == null || start == null || end == null)
            return 0;
        Queue<String> queue = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();
        queue.offer(start);
        visited.add(start);
        int len = 1;
        while (!queue.isEmpty()) {
            int num = queue.size();
            for (int k = 0; k < num; ++k) {
                String curr = queue.poll();
                // Iterate all the words possible neighbors,
                // and check whether it is in the dict.
                for (int i = 0; i < curr.length(); ++i) {
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == curr.charAt(i))
                            continue;
                        String next = curr.substring(0, i) + c + curr.substring(i + 1, curr.length());
                        // Reached the end, return.
                        if (next.equals(end))
                            return len + 1;
                        // Did not reached the end, and not visited, add to queue.
                        if (dict.contains(next) && !visited.contains(next)) {
                            queue.offer(next);
                            visited.add(next);
                        }
                    }
                }
            }
            len++;
        }
        return 0;
    }
}