## Solution 1
> 用两个额外数组分别并保存i元素（不包括i自己）前后的的乘积
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] front = new int[len];
        int[] back = new int[len];
        int[] result = new int[len];

        front[0] = 1;
        back[len - 1] = 1;

        for (int i = 1; i < len; i++) {
            front[i] = front[i - 1] * nums[i - 1];
        }
        for (int i = len - 2; i >= 0; i--) {
            back[i] = back[i + 1] * nums[i + 1];
        }

        for (int i = 0; i < len; i++) {
            result[i] = front[i] * back[i];
        }

        return result;
    }
}
```

> 优化空间
```java
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int[] result = new int[len];

        int back = 1;

        result[0] = 1;
        //第一次遍历计算所有i元素前面的乘积
        for (int i = 1; i < len; i++) {
            result[i] = result[i - 1] * nums[i - 1];
        }

        //第二次遍历从后面开始计算，逐渐累积back变量
        for (int i = len - 1; i >= 0; i--) {
            result[i] *= back;
            back *= nums[i];
        }
        return result;
    }
}
```