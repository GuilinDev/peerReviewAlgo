## Solution 1
> DP记录从左到右子数组的最大值，左边的负值抛弃
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len + 1];
        dp[0] = nums[0];
        int maxSum = nums[0];

        for (int i = 1; i < len; i++) {
            dp[i] = nums[i] + (dp[i - 1] > 0 ? dp[i - 1] : 0); // 转移方程
            maxSum = Math.max(maxSum, dp[i]);
        }
        return maxSum;
    }
}
```
同样进行空间优化，没有必要每个位置的最大值都存储，用一个变量来迭代记录每个位置的最大值
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int len = nums.length;
        int maxSum = nums[0];
        int tempSum = nums[0];

        for (int i = 1; i < len; i++) {
            tempSum = Math.max(tempSum + nums[i], nums[i]); // 遍历到i时，看是否需要把前面的和加上
            maxSum = Math.max(maxSum, tempSum);
        }
        return maxSum;
    }
}
```