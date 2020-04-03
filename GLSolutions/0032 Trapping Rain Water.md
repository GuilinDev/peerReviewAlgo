## Solution 1
> 动态规划

```java
    class Solution {
        public int trap(int[] height) {
            int len = height.length;
            int[] dp = new int[len]; //一维dp存储在i位置时，左右两边最高的bar的较小者

            int result = 0;
            int maxBar = 0;

            //找到当前元素左边的最大值
            for (int i = 0; i < len; i++) {
                dp[i] = maxBar;
                maxBar = Math.max(maxBar, height[i]);
            }

            maxBar = 0; // reset一下，寻找右边的最高的bar
            for (int i = len - 1; i >= 0; i--) {
                dp[i] = Math.min(dp[i], maxBar); // 这个是当前元素左右两边的最大值比较，取较小值
                maxBar = Math.max(height[i], maxBar); // 这个是找到当前元素的右边最大值，为下一轮比较做准备
                if (dp[i] - height[i] > 0) { // 左右边两个较大值中的较小者，比当前bar高，保证水不溢出
                    result += dp[i] - height[i];
                }
            }
            return result;
        }
    }
```

> 动态规划针对两次遍历的优化

```java

```

## Solution 2
> 双指针

```java


```

## Solution 3
> 还可以使用stack

```java


```