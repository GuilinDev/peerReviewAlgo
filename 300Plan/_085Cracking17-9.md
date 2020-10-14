Cracking Coding Interview 17.09

三个指针，队列
```java

```

三个指针，DP
```java
class Solution {
    public int getKthMagicNumber(int k) {
        if (k < 0) {
            return 0;
        }
        int[] dp = new int[k];
        int p3 = 0, p5 = 0, p7 = 0;
        dp[0] = 1;

        for (int i = 1; i < k; i++) {
            // 找三者最小值，保持顺序
            dp[i] = Math.min(dp[p3] * 3, Math.min(dp[p5] * 5, dp[p7] * 7));
            if (dp[i] == dp[p3] * 3) {
                p3++;
            }
            if (dp[i] == dp[p5] * 5) {
                p5++;
            }
            if (dp[i] == dp[p7] * 7) {
                p7++;
            }
        }
        return dp[k - 1];
    }
}
```
