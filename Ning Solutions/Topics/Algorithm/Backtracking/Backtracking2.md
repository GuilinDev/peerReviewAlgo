```java

//22: Generate Parentheses
class Solution {
//Input: n = 3
//Output: ["((()))","(()())","(())()","()(())","()()()"]
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        StringBuilder sb = new StringBuilder();
        if(n > 0){
           generateHelper(0,0,res,sb,n);
        }
        return res;
    }
    
    public void generateHelper(int left, int right,List<String> res, StringBuilder sb, int n) {
        if (left + right == 2*n){
            res.add(new String(sb));
            return;
        }
        if(left < n){
           sb.append("(");
         generateHelper(left + 1, right, res, sb, n);    
         sb.deleteCharAt(sb.length() - 1);   
        }
       
        if (right < left){
              sb.append(")");
        generateHelper(left, right + 1, res, sb, n); 
        sb.deleteCharAt(sb.length() - 1);
        }
    }
}

// 解法二 直接用String 每一层新的String
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new LinkedList<>();
        if(n > 0){
           generateHelper(0,0,res,"",n);
        }
        return res;
    }
    
    public void generateHelper(int left, int right,List<String> res, String sb, int n) {
        if (left + right == 2*n){
            res.add(new String(sb));
            return;
        }
   //     if(left < right || left > n || right > n) return;
        if (left < n){
         generateHelper(left + 1, right, res, sb + "(", n);    
        }
        if (right < left) {
            generateHelper(left, right + 1, res, sb + ")", n); 
        }
    }
}
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

//526. Beautiful Arrangement
/*Suppose you have n integers labeled 1 through n. A permutation of those n integers
 perm (1-indexed) is considered a beautiful arrangement if for every i (1 <= i <= n), 
either of the following is true:
perm[i] is divisible by i.
i is divisible by perm[i].
Given an integer n, return the number of the beautiful arrangements 
that you can construct.
Example 1:
Input: n = 2
Output: 2
Explanation: 
The first beautiful arrangement is [1,2]:
    - perm[1] = 1 is divisible by i = 1
    - perm[2] = 2 is divisible by i = 2
The second beautiful arrangement is [2,1]:
    - perm[1] = 2 is divisible by i = 1
    - i = 2 is divisible by perm[2] = 1*/
class Solution {
    int count = 0;
    public int countArrangement(int n) {
         dfs(n, 1, new boolean[n + 1]);
        return count;
    }

    private void dfs(int n, int i, boolean[] visited) {
        if (i > n) {
            count ++;
            return;
        }
        for (int num = 1; num <= n; num++) {
            if (!visited[num] && (num % i == 0 || i % num == 0)) {  
               // // either condition is true;
                visited[num] = true;
                dfs(n, i + 1, visited);
                visited[num] = false;
            }
        }
    }
}



```
