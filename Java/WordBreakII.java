// Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

// Return all such possible sentences.

// For example, given
// s = "catsanddog",
// dict = ["cat", "cats", "and", "sand", "dog"].

// A solution is ["cats and dog", "cat sand dog"].

// First attempt. TLE.
public class Solution1 {
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> result = new ArrayList<String>();
        if (s.length() == 0)
            return result;
        String t = "#" + s;
        boolean[] opt = new boolean[t.length()];
        opt[0] = true;
        for (int i = 1; i < t.length(); ++i) {
            for (int k = 0; k < i; ++k) {
                opt[i] = opt[k] && dict.contains(t.substring(k + 1, i + 1));
                if (opt[i]) break;
            }
        }
        
        List<Integer> splits = new ArrayList<Integer>();
        for (int i = 0; i < opt.length; ++i) {
            if (opt[i])
                splits.add(i);
        }
        
        findPath(t, dict, splits, "", result, splits.size() - 1);
            
        return result;
    }
    
    public void findPath(String t, Set<String> dict, List<Integer> splits, String path, List<String> result, int index) {
        if (index == 0) {
            result.add(path);
            return;
        }
        int end = splits.get(index);
        for (int i = index - 1; i >= 0; --i) {
            int start = splits.get(i);
            String curr = t.substring(start + 1, end + 1);
            if (dict.contains(curr)) {
                findPath(t, dict, splits, curr + " " + path, result, i);
            }
        }
    }
}

// Accepted version. What is different from the above version is that it recorded only the
// possible splits in current selection, rather than all the split points.
// Using a map to do the recording.
public class Solution {
    public List<String> wordBreak(String s, Set<String> dict) {
        List<String> result = new ArrayList<String>();
        if (s == null || s.length() == 0)
            return result;
        String t = "#" + s;
        int len = t.length();
        // Instead of using a boolean array, use a map to hold all the possible split index
        Map<Integer, List<Integer>> splits = new HashMap<Integer, List<Integer>>();
        splits.put(0, new ArrayList<Integer>());
        for (int i = 1; i < len; ++i) {
            for (int k = 0; k < i; ++k) {
                if (splits.containsKey(k) && dict.contains(t.substring(k + 1, i + 1))) {
                    if (splits.containsKey(i)) {
                        splits.get(i).add(k);
                    } else {
                        List<Integer> l = new ArrayList<Integer>(Arrays.asList(k));
                        splits.put(i, l);
                    }
                }
            }
        }
        if (splits.containsKey(len - 1))
            dfs(t, splits, "", len - 1, result);
        
        return result;
    }
    
    // Since all the possible split points are stored into a map, it is now easy to do a dfs
    // on the possible split index.
    private void dfs(String t, Map<Integer, List<Integer>> splits, String path, int end, List<String> result) {
        if (end == 0) {
            result.add(path);
            return;
        }
        List<Integer> l = splits.get(end);
        for (Integer i : l) {
            String word = t.substring(i + 1, end + 1);
            if (path.length() == 0)
                dfs(t, splits, word, i, result);
            else
                dfs(t, splits, word + " " + path, i, result);
        }
    }
}