## Solution 1
> 贪心法的思想，直接算更新最远距离，O(n)
1. 如果某一个作为 起跳点 的格子可以跳跃的距离是 3，那么表示后面 3 个格子都可以作为 起跳点。
2. 可以对每一个能作为 起跳点 的格子都尝试跳一次，把 能跳到最远的距离 不断更新。
3. 如果最远举例可以一直跳到最后，就成功了。

```java
class Solution {
    public boolean canJump(int[] nums) {
        int fartest = 0;
        for (int index = 0; index < nums.length; index++) {
            if (fartest < index) { // 最远距离不能达到遍历到的当前index
                return false;
            }
            // 根据当前的index更新最远距离
            fartest = Math.max(fartest, index + nums[index]);
        }
        return true;
    }
}
```