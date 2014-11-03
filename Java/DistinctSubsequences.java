// Given a string S and a string T, count the number of distinct subsequences of T in S.

// A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).

// Here is an example:
// S = "rabbbit", T = "rabbit"

// Return 3.

// First attempt. Naive DFS. TLE.
public class Solution1 {
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() < T.length())
            return 0;
        if (S.length() == T.length())
            return S.equals(T) ? 1 : 0;
        int count = 0;
        for (int i = 0; i < S.length(); ++i) {
            String tmp = S.substring(0, i) + S.substring(i + 1, S.length());
            count += numDistinct(tmp, T);
        }
        return count;
    }
}

// Second attempt. Had some improvements. Still TLE.
public class Solution2 {
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() < T.length())
            return 0;
        if (S.length() == T.length())
            return S.equals(T) ? 1 : 0;
        return numDistinct(S, T, 0, 0);
    }
    
    public int numDistinct(String s, String t, int start, int depth) {
        if (depth >= t.length())
            return 1;
        int count = 0;
        int remain = t.length() - depth - 1;
        for (int i = start; i < s.length() - remain; ++i) {
            if (s.charAt(i) == t.charAt(depth))
                count += numDistinct(s, t, i + 1, depth + 1);
        }
        return count;
    }
}

// Third attempt. TLE.
public class Solution3 {
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() < T.length())
            return 0;
        if (S.length() == T.length())
            return S.equals(T) ? 1 : 0;
        Map<Character, List<Integer>> pos = new HashMap<Character, List<Integer>>();
        for (int i = 0; i < S.length(); ++i) {
            char c = S.charAt(i);
            if (pos.containsKey(c)) {
                pos.get(c).add(i);
            } else {
                List<Integer> list = new ArrayList<Integer>(Arrays.asList(i));
                pos.put(c, list);
            }
        }

        return numDistinct(S, T, pos, 0, 0);
    }
    
    public int numDistinct(String s, String t, Map<Character, List<Integer>> pos, int start, int depth) {
        if (depth >= t.length())
            return 1;
        char c = t.charAt(depth);
        if (!pos.containsKey(c))
            return 0;
        List<Integer> list = pos.get(c);
        int count = 0;
        for (Integer i : list) {
            if (i > start)
                count += numDistinct(s, t, pos, i + 1, depth + 1);
        }
        return count;
    }
}

// DP version. Very tricky to find the recursion function.
public class Solution4 {
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() < T.length())
            return 0;
        if (S.length() == T.length())
            return S.equals(T) ? 1 : 0;
        
        // opt[i][j] means the number of distinct subsequences of T(0, j) in S(0, i)
        // if S[i] == T[j], we can either use S[i] to match T[j] or not
        // if S[i] == T[j], we cannot match S[i] to T[j]
        // opt[i][j] = opt[i - 1][j] if S[i] != T[j]
        //           = opt[i - 1][j - 1] + opt[i - 1][j] if S[i] == T[j]
        
        int[][] opt = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i <= S.length(); ++i)
            opt[i][0] = 1;
        for (int j = 1; j <= T.length(); ++j)
            opt[0][j] = 0;
        for (int i = 1; i <= S.length(); ++i) {
            for (int j = 1; j <= T.length(); ++j) {
                if (S.charAt(i - 1) == T.charAt(j - 1))
                    opt[i][j] = opt[i - 1][j - 1] + opt[i - 1][j];
                else
                    opt[i][j] = opt[i - 1][j];
            }
        }
        
        return opt[S.length()][T.length()];
    }
}

// DP version. The final version. Be extremely careful when handling DP's corner case
public class Solution {
    public int numDistinct(String S, String T) {
        if (S == null || T == null || S.length() < T.length())
            return 0;
        if (S.length() == T.length())
            return S.equals(T) ? 1 : 0;
        
        // opt[i] array stands for T[0..i]'s match numbers
        // Iterate S.length() times will get the answer
        int[] opt = new int[T.length() + 1];
        opt[0] = 1;
        for (int i = 1; i <= T.length(); ++i)
            opt[i] = 0;

        // Use rolling array to reduce the memory dimension
        // When using 1 dimension array, we need to iterate from
        // back to front because data may be overwritten if 
        // iterating from front
        for (int i = 1; i <= S.length(); ++i)
            for (int j = T.length(); j >= 1; --j)
                if (S.charAt(i - 1) == T.charAt(j - 1))
                    opt[j] += opt[j - 1];

        return opt[T.length()];
    }
}