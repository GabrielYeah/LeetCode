// Given a digit string, return all possible letter combinations that the number could represent.

// A mapping of digit to letters (just like on the telephone buttons) is given below.

// Input:Digit string "23"
// Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
// Note:
// Although the above answer is in lexicographical order, your answer could be in any order you want.

// Should mind a lot of details.
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits == null) {
            return result;
        }
        if (digits.length() == 0) {
            result.add("");
            return result;
        }
        dfs(digits, 0, new StringBuilder(), result);
        return result;
    }
    
    private void dfs(String digits, int depth, StringBuilder sb, List<String> result) {
        if (digits.length() == depth) {
            result.add(sb.toString());
            return;
        }
        int num = digits.charAt(depth) - '2';
        int base = num * 3;
        if (num > '7' - '2')
            base = base + 1;
        for (int i = 0; i < 4; i++) {
            if (i == 3 && num != '7' - '2' && num != '9' - '2')
                break;
            sb.append((char)('a' + base + i));
            dfs(digits, depth + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

// Final version. With a dict, the code is much cleaner.
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<String>();
        if (digits == null)
            return result;
        String[] dict = new String[]{"abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        dfs(digits, dict, 0, new StringBuilder(""), result);
        return result;
    }
    
    private void dfs(String digits, String[] dict, int depth, StringBuilder sb, List<String> result) {
        if (digits.length() == depth) {
            result.add(sb.toString());
            return;
        }
        int num = digits.charAt(depth) - '2';
        for (int i = 0; i < dict[num].length(); i++) {
            sb.append(dict[num].charAt(i));
            dfs(digits, dict, depth + 1, sb, result);
            sb.deleteCharAt(sb.length() - 1);
        }
    }
}

// Better version
public class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<String>();
        if (digits == null)
            return res;
        search(digits, new char[digits.length()], 0, res);
        return res;
    }
    
    private void search(String digits, char[] path, int depth, List<String> res) {
        if (depth >= digits.length()) {
            res.add(String.valueOf(path));
            return;
        }
        int digit = digits.charAt(depth) - '0';
        int numOfIterations = digit == 7 || digit == 9 ? 4 : 3;
        int offset = digit > 7 ? 1 : 0;
        for (int i = 0; i < numOfIterations; i++) {
            path[depth] = (char)('a' + (digit - 2) * 3 + offset + i);
            search(digits, path, depth + 1, res);
        }
    }
}