// Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end, such that:

// Only one letter can be changed at a time
// Each intermediate word must exist in the dictionary
// For example,

// Given:
// start = "hit"
// end = "cog"
// dict = ["hot","dot","dog","lot","log"]
// Return
//   [
//     ["hit","hot","dot","dog","cog"],
//     ["hit","hot","lot","log","cog"]
//   ]
// Note:
// All words have the same length.
// All words contain only lowercase alphabetic characters.

// TLE. 
public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (start == null || end == null || dict == null || dict.isEmpty())
            return result;
        Map<String, List<String>> preDict = new HashMap<String, List<String>>();
        Queue<String> queue = new LinkedList<String>();
        Set<String> visited = new HashSet<String>();
        queue.offer(start); 
        visited.add(start);
        boolean reached = false;
        while (!queue.isEmpty()) {
            for (int num = queue.size(); num > 0; --num) {
                String curr = queue.poll();
                for (int i = 0; i < curr.length(); ++i) {
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == curr.charAt(i))
                            continue;
                        String next = curr.substring(0, i) + c + curr.substring(i + 1, curr.length());
                        if (next.equals(end)) {
                            reached = true;
                            addPre(curr, next, preDict);
                        } else if (dict.contains(next) && !visited.contains(next)) {
                            queue.offer(next);
                            addPre(curr, next, preDict);
                        }
                    } 
                } 
            }
            if (reached) {
                dfs(preDict, new ArrayList<String>(Arrays.asList(end)), start, end, result);
                break;
            }
            for (String i : queue)
                visited.add(i);
        }
        
        return result;
    }
    
    private void addPre(String curr, String next, Map<String, List<String>> preDict) {
        List<String> preList = preDict.containsKey(next) ? preDict.get(next) : new ArrayList<String>();
        preList.add(curr);
        preDict.put(next, preList);
    } 
    
    private void dfs(Map<String, List<String>> preDict, List<String> path, String s, String e, List<List<String>> result) {
        if (e.equals(s)) {
            List<String> tmp = new ArrayList<String>(path);
            Collections.reverse(tmp);
            result.add(tmp);
            return;
        }
        List<String> list = preDict.get(e);
        for (String p : list) {
            path.add(p);
            dfs(preDict, path, s, p, result);
            path.remove(path.size() - 1);
        }
    }
}

// Final version. Exhausted...
// Very hard. Spent most of the time solving TLE.
public class Solution {
    public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        List<List<String>> result = new ArrayList<List<String>>();
        if (start == null || end == null || dict == null || dict.isEmpty())
            return result;
        // Use a dict to record very word's pre word
        Map<String, List<String>> preDict = new HashMap<String, List<String>>();
        // Use a queue to record word to expand
        Queue<String> queue = new LinkedList<String>(Arrays.asList(start));
        // Use a set to record visited word
        // Note: A word is only been labeled as visited when actually visited, 
        // not when added to queue. If added to visited when added, some words be missed.
        Set<String> visited = new HashSet<String>();
        boolean reached = false;
        while (!queue.isEmpty()) {
            // Get current level words
            List<String> currentLevel = new ArrayList<String>();
            for (int i = queue.size(); i > 0; --i) {
                String curr = queue.poll();
                // This is the time to mark the word as visited
                if (!visited.contains(curr)) {
                    visited.add(curr);
                    currentLevel.add(curr);
                }
            }
            for (String curr : currentLevel) {
            // Don't understand why the following iterating way will TLE
            // for (int m = queue.size(); m > 0; --m) {
            //     String curr = queue.poll();
            //     if (visited.contains(curr))
            //         continue;
            //     visited.add(curr);
                
                for (int i = 0; i < curr.length(); ++i) {
                    for (char c = 'a'; c <= 'z'; ++c) {
                        if (c == curr.charAt(i))
                            continue;
                        String next = curr.substring(0, i) + c + curr.substring(i + 1, curr.length());
                        if (next.equals(end)) {
                            reached = true;
                            addPre(curr, end, preDict);
                        } else if (dict.contains(next) && !visited.contains(next)) {
                            queue.offer(next);
                            addPre(curr, next, preDict);
                        }
                    } 
                } 
            }
            // If reached the end, build the path and jump out the loop
            if (reached) {
                buildPath(preDict, new ArrayList<String>(Arrays.asList(end)), start, end, result);
                break;
            }
        }
        
        return result;
    }
    
    // Function for recording the pre-relation for the words
    private void addPre(String curr, String next, Map<String, List<String>> preDict) {
        List<String> preList = null;
        if (preDict.containsKey(next)) 
            preList = preDict.get(next);
        else
            preList = new ArrayList<String>();
        preList.add(curr);
        preDict.put(next, preList);
    } 
    
    // DFS way to build the path
    private void buildPath(Map<String, List<String>> map, List<String> path, String s, String e, List<List<String>> result) {
        if (e.equals(s)) {
            List<String> tmp = new ArrayList<String>(path);
            Collections.reverse(tmp);
            result.add(tmp);
            return;
        }
        List<String> list = map.get(e);
        for (String p : list) {
            path.add(p);
            buildPath(map, path, s, p, result);
            path.remove(path.size() - 1);
        }
    }
}