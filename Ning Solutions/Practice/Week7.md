```java
// 232. Implement Queue using Stacks
class MyQueue {
    /** Initialize your data structure here. */
    Stack<Integer> inStack;
    Stack<Integer> outStack;    
    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>(); 
    } 
    /** Push element x to the back of queue. */
    public void push(int x) {
        inStack.push(x);
    }  
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()){
               outStack.push(inStack.pop());  
            }
        }
      return outStack.pop();
    } 
    /** Get the front element. */
    public int peek() {
         if (!outStack.isEmpty()) {
            return outStack.peek();
        } else {
            while (!inStack.isEmpty())
                outStack.push(inStack.pop());
        }
        return outStack.peek();
    }    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }
}

// 8. String to Integer (atoi)

public class Solution {
    public int myAtoi(String str) {
        char[] chars = str.toCharArray();
        int n = chars.length;
        int idx = 0;
        while (idx < n && chars[idx] == ' ') {
            // 去掉前导空格
            idx++;
        }
        if (idx == n) {
            //去掉前导空格以后到了末尾了
            return 0;
        }
        boolean negative = false;
        if (chars[idx] == '-') {
            //遇到负号
            negative = true;
            idx++;
        } else if (chars[idx] == '+') {
            // 遇到正号
            idx++;
        } else if (!Character.isDigit(chars[idx])) {
            // 其他符号
            return 0;
        }
        int ans = 0;
        while (idx < n && Character.isDigit(chars[idx])) {
            int digit = chars[idx] - '0';
            if (ans > (Integer.MAX_VALUE - digit) / 10) {
                // 本来应该是 ans * 10 + digit > Integer.MAX_VALUE
                // 但是 *10 和 + digit 都有可能越界，所有都移动到右边去就可以了。
                return negative? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            ans = ans * 10 + digit;
            idx++;
        }
        return negative? -ans : ans;
    }
}

//63. Unique Paths II
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        boolean rowflag = true; // boolean not int
        for (int i = 0; i < row; i ++) {
            if(obstacleGrid[i][0] == 1) {
                rowflag = false; // 利用了int[][] 默认为零的特质
            }
            if(rowflag){
                dp[i][0] = 1;
            }
        }
 
        boolean colflag = true; //
        for (int i = 0; i < col; i ++) {
            if(obstacleGrid[0][i] == 1) {
                colflag = false;
            }
            if(colflag){
                dp[0][i] = 1;
            }
        }
        
        for (int i = 1; i < row; i ++){
            for (int j = 1; j < col; j ++) {
                if (obstacleGrid[i][j] == 1){
                    dp[i][j] = 0;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
                
            }
        }
            
        return dp[row - 1][col - 1];
        
        
    }
}
// 压缩空间的做法
class Solution {
public int uniquePathsWithObstacles(int[][] obstacleGrid) {
    int width = obstacleGrid[0].length;
    int[] dp = new int[width];
    dp[0] = 1;
    for (int[] row : obstacleGrid) {
        for (int j = 0; j < width; j++) {
            if (row[j] == 1)
                dp[j] = 0;
            else if (j > 0)
                dp[j] += dp[j - 1];
        }
    }
    return dp[width - 1];
}
}
// 62. Unique Paths
class Solution {
    public int uniquePaths(int m, int n) {
        int[][] grid = new int[m][n];
        grid[0][0] = 1;
        for (int i = 1; i < m; i ++) {
            grid[i][0] = 1;
        }
        
        for (int j = 1; j < n; j ++) {
            grid[0][j] = 1;
        }

        for (int i = 1; i < m; i ++){
            for (int j  = 1; j < n; j ++){
                grid[i][j] = grid[i - 1][j] + grid[i][j - 1];
            }
        }
        return grid[m - 1][n - 1];
    }
}


```
