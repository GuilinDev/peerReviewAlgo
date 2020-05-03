## Solution 1
> DP，这道题是总是可以到达终点，把55-Jump Game的全局最优变化成第step步最优和第step-1步最优，step表示走了多少步，当走到超过第step-1步所能达到最远距离的时候，说明step-1不能到达当前步，这时候更新步数step-1

```java
class Solution {
    public int jump(int[] nums) {
        int len = nums.length;
        int previous = 0;
        int current = 0;
        int steps = 0;

        for (int i = 0; i < len; i++) {
            if (i > previous) { // step - 1不能跳到当前位置了，需要更新step - 1并且步数+1
                previous = current;
                steps++;
            }
            current = Math.max(current, i + nums[i]); //step - 1过来的步数和当前位置+当前跳力，的较大值，代表可以到达的最远位置,i + num[i]表示index+步长当前这个区间最长
        }
        return steps;
    }
}
```

```java
class Solution {
    public int jump(int[] nums) {
        int furtherPos = 0;
        int curEnd = 0;
        int steps = 0;

        for (int i = 0; i < nums.length - 1; i++) {// only check the states ahead nums.length - 1 postion
            if (i + nums[i] > furtherPos) {
                furtherPos = i + nums[i];
            }
            if (i == curEnd) {
                curEnd = furtherPos;
                steps++;
            }
        }
        return steps;
    }
}
```