// Write a program to solve a Sudoku puzzle by filling the empty cells.

// Empty cells are indicated by the character '.'.

// You may assume that there will be only one unique solution.

// Use boolean array
public class Solution {
    public void solveSudoku(char[][] board) {
        if (board == null || board.length != 9 || board[0].length != 9)
            return;
        List<boolean[]> grids = new ArrayList<boolean[]>();
        List<boolean[]> rows = new ArrayList<boolean[]>();
        List<boolean[]> cols = new ArrayList<boolean[]>();
        for (int i = 0; i < 9; ++i) {
            boolean[] gridSet = new boolean[9], rowSet = new boolean[9], colSet = new boolean[9];
            grids.add(gridSet);
            rows.add(rowSet);
            cols.add(colSet);
        }
        for (int i = 0; i < 81; ++i) {
            int x = i / 9, y = i % 9;
            char c = board[x][y];
            if (c == '.')
                continue;
            grids.get(x / 3 * 3 + y / 3)[c - '1'] = true;
            rows.get(x)[c - '1'] = true;
            cols.get(y)[c - '1'] = true;
        }
        search(board, grids, rows, cols, 0);
    }
    
    private boolean search(char[][] board, List<boolean[]> grids, List<boolean[]> rows, List<boolean[]> cols, int index) {
        while (index < 81 && board[index / 9][index % 9] != '.')
            index++;
        if (index >= 81)
            return true;
        
        int x = index / 9, y = index % 9;
        boolean[] gridSet = grids.get(x / 3 * 3 + y / 3), rowSet = rows.get(x), colSet = cols.get(y);
        for (int i = 0; i < 9; ++i) {
            if (gridSet[i] || rowSet[i] || colSet[i])
                continue;
            gridSet[i] = true; rowSet[i] = true; colSet[i] = true;
            board[x][y] = (char)('1' + i);
            if (search(board, grids, rows, cols, index + 1))
                return true;
            gridSet[i] = false; rowSet[i] = false; colSet[i] = false;
            board[x][y] = '.';
        }
        
        return false;
    }
}