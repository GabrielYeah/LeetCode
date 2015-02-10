// Given a string, find the length of the longest substring without repeating characters. For example, the longest substring without repeating letters for "abcabcbb" is "abc", which the length is 3. For "bbbbb" the longest substring is "b", with the length of 1.

// Maintain a moving window. Made a little mistake by not updating dict.
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Set<Character> dict = new HashSet<Character>();
        int l = 0, r = 0, res = 1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!dict.contains(c)) {
                dict.add(c);
            } else {
                while (s.charAt(l) != c)
                    dict.remove(s.charAt(l++));
                l = l + 1;
            }
            r = r + 1;
            res = Math.max(r - l, res);
        }
        return res;
    }
}

// Second try. A different idea. Instead of constantly changing the elements in the set,
// just keep updating the left boundary.
// Keep in mind that it is necessary to set a meaning to each variable.
// Here, l is a boundary, which protects the min window and keep the repeated chars out.
// So this l should never be back when moving forward.
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0)
            return 0;
        Map<Character, Integer> dict = new HashMap<Character, Integer>();
        char[] a = s.toCharArray();
        int maxLen = 0, l = 0;
        for (int i = 0; i < s.length(); i++) {
            if (dict.containsKey(a[i]))
                l = Math.max(dict.get(a[i]) + 1, l);
            dict.put(a[i], i);
            maxLen = Math.max(maxLen, i - l + 1);
        }
        return maxLen;
    }
}