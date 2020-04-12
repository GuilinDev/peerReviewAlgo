## Solution 1
> 只能买卖一次，DP的简化版，只用遍历并记录每个时间点最大的利润值，最后返回
```java
class Solution {
    public int maxProfit(int[] prices) {
        int minBuy = Integer.MAX_VALUE;
        int maxProfit = 0;

        for (int price : prices) {
            minBuy = Math.min(minBuy, price);
            maxProfit = Math.max(maxProfit, price - minBuy);
        }

        return maxProfit;
    }
}
```