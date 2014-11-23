// You are given a string, S, and a list of words, L, that are all of the same length. Find all starting indices of substring(s) in S that is a concatenation of each word in L exactly once and without any intervening characters.

// For example, given:
// S: "barfoothefoobarman"
// L: ["foo", "bar"]

// You should return the indices: [0,9].
// (order does not matter).

public class Solution {
    public List<Integer> findSubstring(String S, String[] L) {
        List<Integer> result = new ArrayList<Integer>();
        if (S == null || S.length() == 0 || L == null || L.length == 0)
            return result;
        Map<String, Integer> dict = new HashMap<String, Integer>();
        for (String str : L) {
            if (dict.containsKey(str))
                dict.put(str, dict.get(str) + 1);
            else
                dict.put(str, 1);
        }
        int len = L[0].length(), window = len * L.length;
        for (int i = 0; i < len; i++) {
            Map<String, Integer> copy = new HashMap<String, Integer>(dict);
            int start = i, count = 0;
            for (int j = i; j <= S.length() - len && start + window <= S.length(); j = j + len) {
                String str = S.substring(j, j + len);
                if (copy.containsKey(str) && copy.get(str) > 0) {
                    copy.put(str, copy.get(str) - 1);
                    count++;
                } else if (copy.containsKey(str)) {
                    while (copy.get(str) == 0) {
                        String tmp = S.substring(start, start + len);
                        copy.put(tmp, copy.get(tmp) + 1);
                        start += len;
                        count--;
                    }
                    copy.put(str, copy.get (str) - 1);
                    count++;
                } else {
                    while (start < j) {
                        String tmp = S.substring(start, start + len);
                        copy.put(tmp, copy.get(tmp) + 1);
                        start += len;
                        count--;
                    }
                    start = j + len;
                }
                if (count == L.length)
                    result.add(start);
            }
        }
        return result;
    }
}