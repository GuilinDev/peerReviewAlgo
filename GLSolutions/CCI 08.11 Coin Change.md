## Solution 1
> 二维DP
* dp[i][j]=dp[i-1][j]+dp[i][j-coins[i]]
* 其中dp[i][j] 为遍历到当下这个硬币时，组成金额 j 的方法数目
* 有两种可能性（1）当前这个硬币没有取，dp[i][j]=dp[i-1][j]；（2）当前这个硬币取了，dp[i][j]=dp[i][j-coins[i]]。最后的结果是两者的和
* 将状态转移方程翻译成代码，并处理边界条件

```java
class Solution {
    public int waysToChange(int n) {
        int[][] dp = new int[4][n + 1];
        int[] coins = {1, 5, 10, 25};

        // 第一排第一列的初始化, 0枚硬币和0金额的情况
        for (int i = 0; i < n + 1; i++) {
            dp[0][i] = 1;
        }
        for (int i = 0; i < 4; i++) {
            dp[i][0] = 1;
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j >= coins[i]) {
                    dp[i][j] = (dp[i - 1][j] + dp[i][j - coins[i]]) % 1000000007;
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[3][n]; //最后一个
    }
}
```

## Solution 2
> 一维DP
* 从上面的状态转移方程可以看出，dp[i][j]只与dp[i-1][j]和dp[i][j-coins[i]]有关，所以完全可以把第一个维度除掉，只用一个一维数组存储

```java
class Solution {
    public int waysToChange(int n) {
        int[] dp = new int[n + 1];
        int[] coins = {1, 5, 10, 25};

        for (int i = 0; i < n + 1; i++) {
            dp[i] = 1;
        }

        for (int i = 1; i < 4; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (j >= coins[i]) {
                    dp[j] = (dp[j] + dp[j - coins[i]]) % 1000000007;
                }
            }
        }
        return dp[n]; //最后一个
    }
}
```