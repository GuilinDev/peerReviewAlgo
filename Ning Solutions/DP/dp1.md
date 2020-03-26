```java

/*动态规划组成部分一：确定状态
• 状态在动态规划中的作用属于定海神针
• 简单的说，解动态规划的时候需要开一个数组，数组的每个元素f[i]或者f[i][j]代表什么
– 类似于解数学题中，X，Y，Z代表什么
• 确定状态需要两个意识：– 最后一步 – 子问题
动态规划组成部分二：转移方程
比如： 对于任意X, f[X] = min{f[X-2]+1, f[X-5]+1, f[X-7]+1}
动态规划组成部分三：初始条件和边界情况
动态规划组成部分四：计算顺序：比如： 初始条件：f[0] = 0 • 然后计算f[1], f[2], …, f[27]
 • 当我们计算到f[X]时，f[X-2], f[X-5], f[X-7]都已经得到结果了 • 利用之前的计算结果
常见动态规划类型
– 坐标型动态规划 (20%)
– 序列型动态规划 (20%)
– 划分型动态规划 (20%)
– 区间型动态规划 (15%)
– 背包型动态规划 (10%)
– 拓扑型动态规划 (5%)
– 博弈型动态规划 (5%)
– 综合性动态规划 (5%)
*/

//256. Paint House
class Solution {
    public int minCost(int[][] costs) {
         if(costs==null||costs.length==0){
        return 0;
    }
    for(int i=1; i<costs.length; i++){
        costs[i][0] += Math.min(costs[i-1][1],costs[i-1][2]); // need add last costs it self
        costs[i][1] += Math.min(costs[i-1][0],costs[i-1][2]);
        costs[i][2] += Math.min(costs[i-1][1],costs[i-1][0]);
    }
    int n = costs.length-1;
    return Math.min(Math.min(costs[n][0], costs[n][1]), costs[n][2]);
    }
}

// 72. Edit Distance
class Solution {
    public int minDistance(String word1, String word2) {
        if (word1 == null || word2 == null) return 0;
        int m = word1.length();
        int n = word2.length();
        int[][] grid = new int[m + 1][n + 1];
        for (int j = 0; j <=m; j ++){
            grid[j][0] = j;
        }
        for (int i = 0; i <= n; i ++){
            grid[0][i] = i;
        }
        for (int i = 0; i < m; i ++){
            for (int j = 0; j < n; j ++){
               if (word1.charAt(i) == word2.charAt(j)){ // charAt(i) not charAt[i]
                   grid[i + 1][j + 1] = grid[i][j];
               } else {
                   grid[i + 1][j + 1] = getMin(grid[i][j],grid[i + 1][j], grid[i][j + 1] ) + 1; 
                  // grid[i + 1][j] not grid[i - 1][j]
               } 
            }
        }
        return grid[m][n];     
    }
    
    public static int getMin(int a, int b, int c){
        return Math.min(a, Math.min(b,c)); // Math.min not Math.min
    }
}

// 322. Coin Change
class Solution {
    public int coinChange(int[] coins, int amount) {
     int max = amount + 1; // can not be Integer.MAX_VALUE;
     int[] dp = new int[amount + 1]; // need amount + 1
     Arrays.fill(dp, max);
     dp[0] = 0;
     for (int i = 1; i <= amount; i++) {
       for (int j = 0; j < coins.length; j++) {
         if (coins[j] <= i) {
           dp[i] = Math.min(dp[i], dp[i - coins[j]] + 1);
        }
      }
    }   
    return dp[amount] > amount ? -1 : dp[amount]; //边界情况
  }
}

// 55. Jump Game
class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int [] dp = new int[len];
        Arrays.fill(dp, 0);
        for (int i = 1; i < len; i++) {
            dp[i] = Math.max(dp[i - 1], nums[i - 1]) - 1;
            if (dp[i] < 0) {
                return false;
            }
        }
        return dp[len - 1] >= 0;
    }
}






```
