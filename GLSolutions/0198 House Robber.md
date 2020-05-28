## Solution 1
> DP

```java
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int[] dp = new int[len + 1]; // dp[i] 表示选中i个元素时的最大值
        dp[0] = 0;
        dp[1] = nums[0];

        for (int i = 2; i <= len; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i - 1]);
        }
        return dp[len];
    }
}
```
优化空间
```java
class Solution {
    public int rob(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int len = nums.length;
        int prev2 = 0;
        int curr1 = nums[0];

        for (int i = 2; i <= len; i++) {
            int temp = Math.max(curr1, prev2 + nums[i - 1]);
            prev2 = curr1;
            curr1 = temp;
        }
        return curr1;
    }
}
```
记忆化搜索
```java
class Solution {
    Integer[] cache; // 使用Integer而不是int，这样可以和null比较，而不是预设值0
    int len;
    public int rob(int[] nums) {
        len = nums.length;
        cache = new Integer[len]; // 记忆化
        return Math.max( // 从nums中位置0和位置1分别递归寻找最大值
            maxRobbedMoney(nums, 0), 
            maxRobbedMoney(nums, 1) 
        );
    }

    public int maxRobbedMoney(int[] nums, int i) {
        if ( i >= len ) { return 0; } // 基线条件
        
        if ( cache[i] == null ) { // defualt未被改变再计算
            if ( i >= len - 2 ) { // 只有两个元素的情况下
                cache[i] = nums[i];
            } else {
                int max1 = maxRobbedMoney(nums, i + 2);
                int max2 = maxRobbedMoney(nums, i + 3);
                cache[i] = nums[i] + Math.max(max1, max2);
            }
        }
        
        return cache[i];
    } 
}
```