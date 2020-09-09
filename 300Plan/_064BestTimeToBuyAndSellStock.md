LC 122

利用股票系列的DP解法，原始状态转移方程，三个维度分别是 天数-可以交易k次-是否持有股票：

今天不持有股票： dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])

今天持有股票：   dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
            = max(dp[i-1][k][1], dp[i-1][k][0] - prices[i])

这道题数组中的 k 是无限次可交易，不会改变了，也就是说不需要记录 k 这个状态了，所以状态转移方程为：

今天不持有股票： dp[i][0] = max(dp[i-1][0], dp[i-1][1] + prices[i])

今天持有股票：   dp[i][1] = max(dp[i-1][1], dp[i-1][0] - prices[i])

```python
class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        l = len(prices)
        dp_i_0, dp_i_1 = 0, -inf
        for x in range(l):
            temp = dp_i_0
            dp_i_0 = max(dp_i_0, dp_i_1 + prices[x])
            dp_i_1 = max(dp_i_1, temp - prices[x])
            
        return dp_i_0
```
