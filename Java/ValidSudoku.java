// Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.

// The Sudoku board could be partially filled, where empty cells are filled with the character '.'.

// Note:
// A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.

public class Solution {
    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return false;
        List<boolean[]> grids = new ArrayList<boolean[]>();
        List<boolean[]> rows = new ArrayList<boolean[]>();
        List<boolean[]> cols = new ArrayList<boolean[]>();
        for (int i = 0; i < 9; ++i) {
            boolean[] grid = new boolean[9], row = new boolean[9], col = new boolean[9];
            grids.add(grid); rows.add(row); cols.add(col);
        }
        for (int i = 0; i < 81; ++i) {
            int x = i / 9, y = i % 9;
            boolean[] grid = grids.get(x / 3 * 3 + y / 3), row = rows.get(x), col = cols.get(y);
            char c = board[x][y];
            if (c == '.')
                continue;
            if (grid[c - '1'] || row[c - '1'] || col[c - '1'])
                return false;
            grid[c - '1'] = true;
            row[c - '1'] = true;
            col[c - '1'] = true;
        }
        
        return true;
    }
}