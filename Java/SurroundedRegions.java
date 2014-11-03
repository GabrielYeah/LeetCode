// Given a 2D board containing 'X' and 'O', capture all regions surrounded by 'X'.

// A region is captured by flipping all 'O's into 'X's in that surrounded region.

// For example,
// X X X X
// X O O X
// X X O X
// X O X X
// After running your function, the board should be:

// X X X X
// X X X X
// X X X X
// X O X X

// First attempt. StackOverflow runtime error.
public class Solution1 {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int rowLen = board.length, colLen = board[0].length;
        for (int i = 0; i < rowLen; ++i) {
            dfs(board, i, 0);
            dfs(board, i, colLen - 1);
        }
        for (int i = 1; i < colLen - 1; ++i) {
            dfs(board, 0, i);
            dfs(board, rowLen - 1, i);
        }
        for (int i = 0; i < rowLen; ++i)
            for (int j = 0; j < colLen; ++j)
                board[i][j] = board[i][j] == 'T' ? 'O' : 'X';
    }
    
    private void dfs(char[][] board, int x, int y) {
        int rowLen = board.length, colLen = board[0].length;
        if (x >= rowLen || x < 0 || y >= colLen || y < 0 || board[x][y] == 'T' || board[x][y] == 'X')
            return;
        board[x][y] = 'T';
        dfs(board, x + 1, y);
        dfs(board, x - 1, y);
        dfs(board, x, y + 1);
        dfs(board, x, y - 1);
    } 
}

// Aced on second method. Use BFS instead of DFS.
public class Solution {
    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0)
            return;
        int rowLen = board.length, colLen = board[0].length;
        Queue<Integer> xs = new LinkedList<Integer>();
        Queue<Integer> ys = new LinkedList<Integer>();
        for (int i = 0; i < rowLen; ++i) {
            if (board[i][0] == 'O') {
                xs.add(i); ys.add(0);
            }
            if (board[i][colLen - 1] == 'O') {
                xs.add(i); ys.add(colLen - 1);
            }
        }
        for (int i = 1; i < colLen - 1; ++i) {
            if (board[0][i] == 'O') {
                xs.add(0); ys.add(i);
            }
            if (board[rowLen - 1][i] == 'O') {
                xs.add(rowLen - 1); ys.add(i);
            }
        }
        // Search all the outter edges and explore all the nodes that can be reached
        while (!xs.isEmpty()) {
            int x = xs.poll(), y = ys.poll();
            if (x >= rowLen || x < 0 || y >= colLen || y < 0 || board[x][y] == 'T' || board[x][y] == 'X')
                continue;
            // Set the note to 'R' to note it needs to be remained
            board[x][y] = 'R';
            xs.add(x + 1); ys.add(y);
            xs.add(x - 1); ys.add(y);
            xs.add(x); ys.add(y + 1);
            xs.add(x); ys.add(y - 1);
        }
        
        for (int i = 0; i < rowLen; ++i)
            for (int j = 0; j < colLen; ++j)
                board[i][j] = board[i][j] == 'R' ? 'O' : 'X';
            
    }
}