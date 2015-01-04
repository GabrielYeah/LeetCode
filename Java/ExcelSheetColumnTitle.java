// Given a positive integer, return its corresponding column title as appear in an Excel sheet.

// For example:

//     1 -> A
//     2 -> B
//     3 -> C
//     ...
//     26 -> Z
//     27 -> AA
    // 28 -> AB 

// Process from right to left. Remember to remove current level offset.
public class Solution {
    public String convertToTitle(int n) {
        StringBuilder sb = new StringBuilder("");
        while (n > 0) {
            int offset = n % 26 == 0 ? 26 : n % 26;
            sb.insert(0, (char)('A' - 1 + offset));
            n = (n - offset) / 26;
        }
        return sb.toString();
    }
}