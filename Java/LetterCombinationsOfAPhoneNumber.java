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