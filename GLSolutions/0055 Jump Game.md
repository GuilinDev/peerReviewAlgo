## Solution 1
> DP, 维护一个一位数组dp，其中dp[i]表示达到i位置时剩余的步数，到达当前位置跟上一个位置（不是前一个位置）的剩余步数和数字（能达到的最远位置）有关，下一个位置的剩余步数（dp值）就等于当前的这个较大值减去1，因为需要花一个跳力到达下一个位置，所以状态转移方程：dp[i] = max(dp[i - 1], nums[i - 1]) - 1，如果当某一个时刻dp数组的值为负了，说明无法抵达当前位置，则直接返回false，最后判断数组最后一位是否为非负数即可知道是否能抵达该位置。

```java
class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];

        // dp[0] = 0; // already 0
        for (int i = 1; i < len; i++) {
            // 上一个状态和上一个当前跳力之间选一个较大的，可以跳更远
            dp[i] = Math.max(dp[i - 1], nums[i - 1]) - 1; // 从上一步到下一步需要1个跳力，当前的这个较大值减去1
            if (dp[i] < 0) {
                return false;
            }
        }
        return true;
    }
}
```
同样的空间优化
```java
class Solution {
    public boolean canJump(int[] nums) {
        int len = nums.length;
        int previous = 0;
        int current = 0;

        for (int i = 1; i < len; i++) {
            // 上一个状态和上一个当前跳力之间选一个较大的，可以跳更远
            current = Math.max(previous, nums[i - 1]) - 1; // 从上一步到下一步需要1个跳力，当前的这个较大值减去1
            if (current < 0) {
                return false;
            }
            previous = current; //为下一次记录
        }
        return true;
    }
}
```

## Solution 2
> 贪心法的思想对这道题更优，直接算更新最远距离，O(n)，其实也可以看作DP方法的空间优化
1. 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
2. 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
3. 如果最远举例可以一直跳到最后，就成功了。

```java
class Solution {
    public boolean canJump(int[] nums) {
        int fartest = 0;
        for (int index = 0; index < nums.length; index++) {
            if (fartest < index) { // 最远距离也不能达到遍历到的当前index
                return false;
            }
            // 根据当前的index更新最远距离
            fartest = Math.max(fartest, index + nums[index]);
        }
        return true;
    }
}
```