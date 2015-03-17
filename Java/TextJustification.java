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

// A more readable version. Split the words into lists, and build strings accordingly.
public class Solution {
    public List<String> fullJustify(String[] words, int L) {
        List<String> res = new ArrayList<String>();
        if (words == null || words.length == 0)
            return res;
        List<List<String>> lists = splitWords(words, L);
        for (int i = 0; i < lists.size(); i++) {
            List<String> list = lists.get(i);
            int spaceNum = getSpaceNum(list, L);
            String str;
            if (list.size() == 1)
                str = getString(list, 0, 0, spaceNum);
            else if (i < lists.size() - 1)
                str = getString(list, spaceNum / (list.size() - 1), spaceNum % (list.size() - 1), 0);
            else
                str = getString(list, 1, 0, spaceNum - list.size() + 1);
            res.add(str);
        }
        return res;
    }
    
    private List<List<String>> splitWords(String[] words, int L) {
        List<String> currlist = new ArrayList<String>(Arrays.asList(words[0]));
        List<List<String>> lists = new ArrayList<List<String>>(Arrays.asList(currlist));
        int remaining = L - words[0].length();
        for (int i = 1; i < words.length; i++) {
            String str = words[i];
            if (remaining >= str.length() + 1) {
                currlist.add(str);
                remaining -= str.length() + 1;
            } else {
                currlist = new ArrayList<String>(Arrays.asList(str));
                lists.add(currlist);
                remaining = L - str.length();
            }
        }
        return lists;
    }
    
    private int getSpaceNum(List<String> list, int L) {
        int spaceNum = L;
        for (String str : list)
            spaceNum -= str.length();
        return spaceNum;
    }
    
    private String getString(List<String> list, int normal, int extra, int tailing) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size() - 1; i++) {
            sb.append(list.get(i));
            for (int k = normal; k > 0; k--)
                sb.append(' ');
            if (extra-- > 0)
                sb.append(' ');
        }
        sb.append(list.get(list.size() - 1));
        while (tailing-- > 0)
            sb.append(' ');
        return sb.toString();
    }
}