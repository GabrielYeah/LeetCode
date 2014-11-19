// Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

// You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

// Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

// For the last line of text, it should be left justified and no extra space is inserted between words.

// For example,
// words: ["This", "is", "an", "example", "of", "text", "justification."]
// L: 16.

// Return the formatted lines as:
// [
//    "This    is    an",
//    "example  of text",
//    "justification.  "
// ]
// Note: Each word is guaranteed not to exceed L in length.

// click to show corner cases.

// Corner Cases:
// A line other than the last line might contain only one word. What should you do in this case?
// In this case, that line should be left-justified.

// First attempt. Detail realization. First scan split points. Then distribute the spaces.
public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> result = new ArrayList<String>();
        if (words == null || words.length == 0)
            return result; 
        // Record the endpoint (exclusive) of each list
        List<Integer> splits = new ArrayList<Integer>();
        for (int i = 1, len = words[0].length(); i < words.length; ++i) {
            if (len + words[i].length() + 1 <= L) {
                len += words[i].length() + 1;
            } else {
                splits.add(i);
                len = words[i].length();
            }
        }
        int start = 0;
        for (int end : splits) {
            StringBuilder sb = new StringBuilder();
            sb.append(words[start]);
            if (end - start == 1) {
                while (sb.length() < L)
                    sb.append(' ');
            } else {
                // Count the number of spaces
                int spaceNum = L;
                for (int i = start; i < end; ++i)
                    spaceNum -= words[i].length();
                // Record the number of spaces in each gap
                int round = 0;
                int[] spaces = new int[end - start - 1];
                while (spaceNum-- > 0)
                    spaces[round++ % (end - start - 1)]++;
                // Append strings and spaces
                for (int i = start + 1; i <= end - 1; ++i) {
                    while (spaces[i - start - 1]-- > 0)
                        sb.append(' ');
                    sb.append(words[i]);
                }
            }
            result.add(sb.toString());
            start = end;
        }
        StringBuilder sb = new StringBuilder(words[start]);
        while (++start < words.length)
            sb.append(" " + words[start]);
        while (sb.length() < L)
            sb.append(' ');
        result.add(sb.toString());
        return result;
    }
}