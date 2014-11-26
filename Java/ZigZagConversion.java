// The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)

// P   A   H   N
// A P L S I I G
// Y   I   R
// And then read line by line: "PAHNAPLSIIGYIR"
// Write the code that will take a string and make this conversion given a number of rows:

// string convert(string text, int nRows);
// convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR".

// First version. Has O(n) space complexity.
public class Solution {
    public String convert(String s, int nRows) {
        if (s == null || s.length() <= nRows || nRows == 1)
            return s;
        List<List<Character>> cache = new ArrayList<List<Character>>();
        for (int i = 0; i < nRows; i++)
            cache.add(new ArrayList<Character>());
        int pt = 0, diff = 1;
        for (char c : s.toCharArray()) {
            cache.get(pt).add(c);
            if (pt == nRows - 1 && diff == 1)
                diff = -1;
            else if (pt == 0 && diff == -1)
                diff = 1;
            pt += diff;
        }
        StringBuilder sb = new StringBuilder();
        for (List<Character> l : cache)
            for (Character c : l) 
                sb.append(c);
        return sb.toString();
    }
}

// Final version. O(1) space complexity.
public class Solution {
    public String convert(String s, int nRows) {
        if (s == null || s.length() <= nRows || nRows == 1)
            return s;
        StringBuilder sb = new StringBuilder();
        int size = 2 * nRows - 2;
        for (int i = 0; i < nRows; i++) {
            for (int j = i; j < s.length(); j += size) {
                sb.append(s.charAt(j));
                if (i != 0 && i != nRows - 1 && j + size - 2 * i < s.length())
                    sb.append(s.charAt(j + size - 2 * i));
            }    
        }
        return sb.toString();
    }
}