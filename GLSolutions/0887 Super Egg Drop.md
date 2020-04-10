## Solution 1
> DP
```java
class Solution {
    public int superEggDrop(int K, int N) {
        int[][] dp = new int[K + 1][N + 1]; 

        for (int steps = 1; steps <= N; steps++) {
            dp[0][steps] = 0; // 初始化
            for (int eggs = 1; eggs <= K; eggs++) {
                //在某一层，当前情况=（丢下去碎了eggs-1，消耗一步能确定的层数steps-1 + 丢下去没碎eggs，消耗一步能确定的层数，steps - 1）+ 当前这一层1
                dp[eggs][steps] = dp[eggs][steps - 1] + dp[eggs - 1][steps - 1] + 1;
                if (dp[eggs][steps] >= N) { // N 层楼梯最多只需要进行 N 次便一定可以确定 F，即 F 最大只能是最高层 N
                    return steps;
                }
            }
        }
        return N; // 最后没找到中间的，就返回最高的层
    }
}
```

> dp + Binary Search

## Solution 2
> 决策单调性

```java

```

## Solution 2
> 数学法

```java

```