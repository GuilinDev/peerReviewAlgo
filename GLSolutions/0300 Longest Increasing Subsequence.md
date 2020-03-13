## Solution 1
> DP的做法，经典的递推, O(n<sup>2</sup>)

```java
    class Solution {
    public int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len + 1]; //dp[i]表示在第i个位置上为止，最长的上升子序列,dp[0]是没有字符的初始状态
        Arrays.fill(dp, 1); // 每个位置的最长上升子序列至少为自身，长度1
        for (int i = 1; i < len; i++) {
            for (int j = 0; j < i; j++) {//这里不算i自己，因为后面的dp[j] + 1代表j位置的最长上升子序列加上1（i自己）是否为更大
                if (nums[j] < nums[i]) { //之前的元素是否小于当前元素，若是，则“可能”可以加入到最长子序列中
                    if (dp[j] + 1 > dp[i]) { //j位置的最长上升子序列加上1（i自己）是否比当前的值更大
                        dp[i] = dp[j] + 1; // 将当前位置的最长上升子序列的长度更新为刚刚计算的最长
                    }
                }
            }
        }

        // so far,dp中每个元素都代表了该位置最长的上升子序列长度
        int result = 0;
        for (int localLongest : dp) {
            result = Math.max(result, localLongest);
        }
        return result;
    }
}
```

## Solution 2
> Binary Search, O(nLogn)