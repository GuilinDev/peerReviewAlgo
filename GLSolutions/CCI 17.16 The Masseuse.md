[The Masseuse](https://leetcode-cn.com/problems/the-masseuse-lcci/)
## Solution 1
> 二维动态规划

```java
    class Solution {
        public int massage(int[] nums) {
            /*
            dp[i]表示在第i个位置时最长预约时长，然后发现还有一个可以或者不可以预约的选项，
            所以这里还需要一个维度来记录是否可以预约，时长+可否预约，共两个维度;
            dp[i][0]表示在第i个位置，不选i的最长时长；dp[i][1]表示在第i个位置，选i的最长时长;

            dp[i][0] = max(dp[i - 1][0], dp[i - 1][1])；不选i，这时候有两种选择，i - 1的位置可以选可以不选
            dp[i][1] = dp[i - 1][0] + nums[i];选择i，这时候只有一种选择，i - 1的位置必须不能选
            */
            int result = 0;
            if (nums == null || nums.length == 0) {
                return result;
            }
            int len = nums.length;
            int[][] dp = new int[len][2];
            dp[0][0] = 0;
            dp[0][1] = nums[0];
            for (int i = 1; i < len; i++) {
                dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1]); //不选当前的nums[i]
                dp[i][1] = dp[i - 1][0] + nums[i]; //选择当前的nums[i]
            }
            return Math.max(dp[len - 1][0], dp[len - 1][1]);
        }
    }
```

## Solution 2
> 上面使用一个数组来存储状态，可以优化一下用两个变量来存储

```java
    class Solution {
        public int massage(int[] nums) {
            int result = 0;
            if (nums == null || nums.length == 0) {
                return result;
            }
            int len = nums.length;

            //初始化
            int dp0 = 0;
            int dp1 = nums[0];

            for (int i = 1; i < len; i++) {
                int temp_dp0 = Math.max(dp0, dp1); //临时计算dp[i][0]
                int temp_dp1 = dp0 + nums[i]; //临时计算dp[i][1]

                dp0 = temp_dp0;
                dp1 = temp_dp1;
            }
            return Math.max(dp0, dp1);
        }
    }
```

## Solution 3
> 自顶向下的备忘录法