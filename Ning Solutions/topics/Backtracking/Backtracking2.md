```java

//51. N-Queens
public class Solution {
    public List<List<String>> solveNQueens(int n) {
        char[][] board = new char[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) // board[row_index][column_index]
                board[i][j] = '.';
        List<List<String>> res = new ArrayList<List<String>>();
        backTracking(board, 0, res); 
        return res;
    }  
    private void backTracking(char[][] board, int colIndex, List<List<String>> res) {
        if(colIndex == board.length) {
            res.add(construct(board)); // 
            return;
        }
        for(int i = 0; i < board.length; i++) {
            if(validate(board, i, colIndex)) {
                board[i][colIndex] = 'Q';
                backTracking(board, colIndex + 1, res);
                board[i][colIndex] = '.';
            }
        }
    }  
    private boolean validate(char[][] board, int x, int y) {
        for(int i = 0; i < board.length; i++) {
            for(int j = 0; j < y; j++) {
                if(board[i][j] == 'Q' && (x + j == y + i || x + y == i + j || x == i))
                    return false;
            }
        }
        return true;
    }
    
    private List<String> construct(char[][] board) {
        List<String> res = new LinkedList<String>();
        for(int i = 0; i < board.length; i++) {
            String s = new String(board[i]);
            res.add(s);
        }
        return res;
    }
}

// 37. Sudoku Solver
class Solution {
    public void solveSudoku(char[][] board) {
        if(board == null || board.length == 0) 
            return;
        solve(board);
    }
    
    public boolean solve(char[][] board){
        for(int i = 0; i <board.length; i ++){
            for(int j = 0; j <board[0].length; j++){
                if(board[i][j] == '.'){
                    for(char c = '1'; c <= '9'; c++){
                        if(isValid(board,i,j,c)){
                            board[i][j]= c;
                            if(solve(board))
                                return true;
                            else 
                                board[i][j] = '.';
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean isValid(char[][] board, int row, int col, char c) {
        for (int i = 0; i < 9; i ++) {
            if(board[i][col] != '.' && board[i][col] == c) return false; // check row
            if(board[row][i]!= '.' && board[row][i]== c) return false; // check column
            if(board[3 * (row/3) + i/3][3*(col/3) + i % 3] != '.' && board[3 * (row/3) + i/3][3*(col/3) + i%3] == c)                    return false; // check 3*3 block
        }
        return true;
    }
}



```
