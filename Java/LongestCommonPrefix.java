// Write a function to find the longest common prefix string amongst an array of strings.

// Aced.
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        String longest = strs[0];
        for (int i = 1; i < strs.length; i++)
            longest = findPrefix(longest, strs[i]);
        return longest;
    }
    
    private String findPrefix(String a, String b) {
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < a.length() && i < b.length(); i++) {
            if (a.charAt(i) == b.charAt(i))
                sb.append(a.charAt(i));
            else
                break;
        }
        return sb.toString();
    }
}

// Aced. Each time match a char. Can cut some branches in previous solution.
public class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0)
            return "";
        StringBuilder sb = new StringBuilder("");
        for (char c : strs[0].toCharArray()) {
            int index = sb.length();
            for (int i = 1; i < strs.length; i++)
                if (index >= strs[i].length() || strs[i].charAt(index) != c)
                    return sb.toString();
            sb.append(c);
        }
        return sb.toString();
    }
}