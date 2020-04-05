## Solution 1
> DP，dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数

> 当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；

> 当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1

> 其中，dp[i-1][j-1] 表示替换操作，dp[i-1][j] 表示删除操作，dp[i][j-1] 表示插入操作。

> 同样，针对第一行，第一列要单独考虑， 第一行，是 word1 为空变成 word2 最少步数，就是插入操作， 第一列，是 word2 为空，需要的最少步数，就是删除操作

> 自顶向下，记忆化搜索
```java

```

> 自底向上，递推
```java
    class Solution {
        public int minDistance(String word1, String word2) {
            int len1 = word1.length();
            int len2 = word2.length();
            int[][] dp = new int[len1 + 1][len2 + 1];
            for (int i = 0; i <= len1; i++) {
                dp[i][0] = i;
            }
            for (int j = 0; j <= len2; j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= len1; i++) {
                for (int j = 1; j <= len2; j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 1 + Math.min(Math.min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]);
                    }
                }
            }
            return dp[len1][len2];
        }
    }
```