## Solution 1
> DP，这题不考虑越界的问题，需要考虑负值的问题
```java
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        // dp[i], 在i位置时乘积最大和最小的子数组在，最后返回全局最大的那个位置
        // 状态方程如下
        int[] mins = new int[len];
        int[] maxs = new int[len];

        mins[0] = nums[0];
        maxs[0] = nums[0];

        int max = nums[0];

        for (int i = 1; i < len; i++) {
            // 需要考虑当前值负数的情况
            mins[i] = Math.min(Math.min(mins[i - 1] * nums[i], maxs[i - 1] * nums[i]), nums[i]); //之前的结果 vs 以现在为起始的子数组
            maxs[i] = Math.max(Math.max(mins[i - 1] * nums[i], maxs[i - 1] * nums[i]), nums[i]); //之前的结果 vs 以现在为起始的子数组
            max = Math.max(max, maxs[i]);
        }
        return max;
    }
}
```
空间优化，把前面的状态用变量来保存
```java
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int preMin = nums[0];
        int preMax = nums[0];

        int curMin, curMax;

        int max = nums[0];

        for (int i = 1; i < len; i++) {
            curMin = Math.min(Math.min(preMin * nums[i], preMax * nums[i]), nums[i]);
            curMax = Math.max(Math.max(preMin * nums[i], preMax * nums[i]), nums[i]);

            max = Math.max(max, curMax);

            preMin = curMin;
            preMax = curMax;
        }
        return max;
    }
}
```

## Solution 2
> 滚动数组，一个意思，只是遇到负数的时候判断一下最大最小并记录下来
```java
class Solution {
    public int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int maxCurrentProduct = nums[0];

        for (int i = 1, min = maxCurrentProduct, max = maxCurrentProduct; i < len; i++) {
            if (nums[i] < 0) { // 遇到负值，当前的最大值和最小值交换一下
                max = min ^ max;
                min = min ^ max;
                max = min ^ max;
            }
            // 用交换后的最大值和最小值更新最新当前的最大值和最小值
            max = Math.max(max * nums[i], nums[i]);
            min = Math.min(min * nums[i], nums[i]);

            // 记录到目前为止的最大值
            maxCurrentProduct = Math.max(maxCurrentProduct, max);
        }
        return maxCurrentProduct;
    }
}
```