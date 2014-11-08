// Given a 2D board and a word, find if the word exists in the grid.

// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

// For example,
// Given board =

// [
//   ["ABCE"],
//   ["SFCS"],
//   ["ADEE"]
// ]
// word = "ABCCED", -> returns true,
// word = "SEE", -> returns true,
// word = "ABCB", -> returns false.

public class Solution {
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return false;
        if (word == null)
            return true;
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[0].length; ++j)
                if (exist(board, word, 0, i, j))
                    return true;
        return false;
    }
    
    private boolean exist(char[][] board, String word, int start, int x, int y) {
        char c = word.charAt(start);
        if (board[x][y] != c)
            return false;
        if (start == word.length() - 1)
            return true;
        boolean result = false;
        board[x][y] -= 'a';
        if (x + 1 < board.length)
            result = result || exist(board, word, start + 1, x + 1, y);
        if (!result && x - 1 >= 0)
            result = result || exist(board, word, start + 1, x - 1, y);
        if (!result && y + 1 < board[0].length)
            result = result || exist(board, word, start + 1, x, y + 1);
        if (!result && y - 1 >= 0)
            result = result || exist(board, word, start + 1, x, y - 1);
        board[x][y] += 'a';
        return result;
    }
}