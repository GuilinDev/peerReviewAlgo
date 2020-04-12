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
// 1143. Longest Common Subsequence
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for(int i = 0; i < text1.length(); i ++) {
            for (int j = 0; j < text2.length(); j ++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;  // 两个中有一个空字符串情况
                }
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else { // if else 
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]); // 这里不能加一 
                }
            }
        }
        return dp[text1.length()][text2.length()];
        
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

// recursion way for coin change
class Solution {
    int[] memo;
    public int coinChange(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        memo = new int[amount];

        return findWay(coins,amount);
    }
    // memo[n] 表示钱币n可以被换取的最少的硬币数，不能换取就为-1
    // findWay函数的目的是为了找到 amount数量的零钱可以兑换的最少硬币数量，返回其值int
    public int findWay(int[] coins,int amount){
        if(amount < 0){
            return -1;
        }
        if(amount == 0){
            return 0;
        }
        // 记忆化的处理，memo[n]用赋予了值，就不用继续下面的循环
        // 直接的返回memo[n] 的最优值
        if(memo[amount-1] != 0){
            return memo[amount-1];
        }
        int min = Integer.MAX_VALUE;
        for(int i = 0;i < coins.length;i++){
            int res = findWay(coins,amount-coins[i]);
            if(res >= 0 && res < min){
                min = res + 1; // 加1，是为了加上得到res结果的那个步骤中，兑换的一个硬币
            }
        }
        memo[amount-1] = (min == Integer.MAX_VALUE ? -1 : min);
        return memo[amount-1];
    }
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
// 265 paint house II
class Solution {
  public int minCostII(int[][] costs) {
    if (costs == null || costs.length == 0) return 0;
        
    int n = costs.length, k = costs[0].length;
    // min1 is the index of the 1st-smallest cost till previous house
    // min2 is the index of the 2nd-smallest cost till previous house
    int min1 = -1, min2 = -1;
    
    for (int i = 0; i < n; i++) {
        int last1 = min1, last2 = min2;
        min1 = -1; min2 = -1;
        
        for (int j = 0; j < k; j++) {
            if (j != last1) {
                // current color j is different to last min1
                costs[i][j] += last1 < 0 ? 0 : costs[i - 1][last1];
            } else {
                costs[i][j] += last2 < 0 ? 0 : costs[i - 1][last2];
            }       
            // find the indices of 1st and 2nd smallest cost of painting current house i
            if (min1 < 0 || costs[i][j] < costs[i][min1]) {
                min2 = min1; min1 = j;
            } else if (min2 < 0 || costs[i][j] < costs[i][min2]) {
                min2 = j;
            }
        }
    }
    
    return costs[n - 1][min1];
}
}
// 198. House Robber
class Solution {
  int rob(int[] nums) {
    int n = nums.length;
    // dp[i] = x 表示：
    // 从第 i 间房子开始抢劫，最多能抢到的钱为 x
    // base case: dp[n] = 0
    int[] dp = new int[n + 2];
    for (int i = n - 1; i >= 0; i--) {
        dp[i] = Math.max(dp[i + 1], nums[i] + dp[i + 2]);
    }
    return dp[0];
}
//save space
int rob2(int[] nums) {
    int n = nums.length;
   //记录 dp[i+1] 和 dp[i+2]
    int dp_i_1 = 0, dp_i_2 = 0;
    // 记录 dp[i]
    int dp_i = 0; 
    for (int i = n - 1; i >= 0; i--) {
        dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
        dp_i_2 = dp_i_1;
       dp_i_1 = dp_i;
    }
    return dp_i;
}
}

//213. House Robber II
class Solution {
    public int rob(int[] nums) {
    int n = nums.length;
    if (n == 1) return nums[0];
    return Math.max(robRange(nums, 0, n - 2), 
                    robRange(nums, 1, n - 1));
}

// 仅计算闭区间 [start,end] 的最优结果
int robRange(int[] nums, int start, int end) {
    int n = nums.length;
    int dp_i_1 = 0, dp_i_2 = 0;
    int dp_i = 0;
    for (int i = end; i >= start; i--) {
        dp_i = Math.max(dp_i_1, nums[i] + dp_i_2);
        dp_i_2 = dp_i_1;
        dp_i_1 = dp_i;
    }
    return dp_i;
}
}

// 62. Unique Paths
class Solution {
    public int uniquePaths(int m, int n) {
        if (m == 0 || n == 0) return 0;
        int[][] dp = new int[m][n];
        for (int i = 0; i < m; i ++) {
            for (int j = 0; j < n; j ++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 1;   
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];     
                }
            }
        }
        return dp[m - 1][n - 1]; // need m - 1 not m.
        
    }
}
// 63. Unique Paths II
class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int row = obstacleGrid.length;
        int col = obstacleGrid[0].length;
        int[][] dp = new int[row][col];
        boolean rowflag = true; // boolean not int
        for (int i = 0; i < row; i ++) {
            if(obstacleGrid[i][0] == 1) {
                rowflag = false;
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

//64. Minimum Path Sum
class Solution {
    public int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int row = grid.length;
        int col = grid[0].length;
        int[][] sum = new int[row][col];
        sum[0][0] = grid[0][0];
        for (int i = 0; i < row; i ++) {
             for (int j = 0; j < col; j ++){
                 if (i == 0 && j > 0){
                     sum[i][j] = sum[i][j - 1] + grid[i][j]; 
                 }
                 else if (j == 0 && i > 0){
                     sum[i][j] =  sum[i - 1][j] + grid[i][j];
                 } else if (i > 0 && j > 0){ // use if else if else if structure
                    sum[i][j] = Math.min(sum[i][j - 1], sum[i - 1][j]) + grid[i][j];   
                 }   
            }    
        }
        return sum[row - 1][col - 1]; // not sum[row][col]
    }
}

/*划分性
• 要求将一个序列或字符串划分成若干满足要求的的片段
• 解决方法：最后一步--》最后一段
• 枚举最后一段起点
• f[i]表示前i个元素分段后的可行性/最值，可行性，方式数： Perfect Squares, Palindrome Partition II*/

//279. Perfect Squares
class Solution {
public int numSquares(int n) {
 	int[] dp = new int[n + 1];
 	Arrays.fill(dp, Integer.MAX_VALUE);
 	dp[0] = 0;
 	for(int i = 1; i <= n; ++i) {
 		int min = Integer.MAX_VALUE;
 		int j = 1;
 		while(i - j*j >= 0) {
 			min = Math.min(min, dp[i - j*j] + 1);
 			++j;
 		}
 		dp[i] = min;
 	}		
 	return dp[n];
 }
}

//416. Partition Equal Subset Sum (0 -1 背包)
class Solution {
    public boolean canPartition(int[] nums) {
    int sum = 0;
    
    for (int num : nums) {
        sum += num;
    }
    
    if ((sum & 1) == 1) {
        return false;
    }
    sum /= 2;
    
    int n = nums.length;
    boolean[] dp = new boolean[sum+1];
    Arrays.fill(dp, false);
    dp[0] = true;
    
    for (int num : nums) {
        for (int i = sum; i > 0; i--) {
            if (i >= num) {
                dp[i] = dp[i] || dp[i-num];
            }
        }
    } 
    return dp[sum];
}

// 300. Longest Increasing Subsequence
// dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。们的最终结果（子序列的最大长度）应该是 dp 数组中的最大值。
class Solution {
   public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length];
    // dp 数组全都初始化为 1
    Arrays.fill(dp, 1);
    for (int i = 0; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) 
                dp[i] = Math.max(dp[i], dp[j] + 1);
        }
    }

    int res = 0;
    for (int i = 0; i < dp.length; i++) {
        res = Math.max(res, dp[i]);
    }
    return res;
}
}

}





```
