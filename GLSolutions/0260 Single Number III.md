## Solution 1
> 与136，137，645一起练习的效果比较好
> 如果用排序或hashset则不满足时间O(n)空间O(1)的要求

```java
class Solution {
    public int[] singleNumbers(int[] nums) {
        int diff = 0;
        
        //找到两个只出现一次的数字的xor后的结果
        for (int num : nums) {
            diff ^= num;
        }
        
        diff &= -diff;//与自己的补码进行与操作，得到最后一个bit，0或者1（自己与自己负数-补码，因为负数的补码在末位加了1，所以只是最后一位相同，别的31位都不同）
        
        int[] result = new int[2];
        for (int num : nums) {
            if ((diff & num) == 0) {//末位的bit与diff不都为1，1-0，0-1，0-0
                result[0] ^= num; //用xor是因为要把diff &= -diff这步得到的bit相同的num彼此消掉（别的num出现两次）
            } else {//末位的bit与diff都为1，1-1
                result[1] ^= num; //用xor是因为要把diff &= -diff这步得到的bit不同的num彼此消掉（别的num出现两次）
            }
        }
        
        return result;
    }
}
```

## Solution 2
> 异或还可以用到二分查找，非常清楚明白
[详细解释](https://leetcode-cn.com/problems/shu-zu-zhong-shu-zi-chu-xian-de-ci-shu-lcof/solution/shi-yao-zhe-ti-huan-ke-yi-yong-er-fen-cha-zhao-bi-/)

```java
class Solution {
    public int[] singleNumbers(int[] nums) {
        int sum = 0, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, zeroCount = 0;
        for (int num : nums) {
            if (num == 0) {
                zeroCount += 1;
            }
            min = Math.min(min, num);
            max = Math.max(max, num);
            sum ^= num;
        }
        // 需要特判一下某个数是0的情况。
        if (zeroCount == 1) {
            return new int[]{sum, 0};
        }
        int lo = min, hi = max;
        while (lo <= hi) {
            // 根据 lo 的正负性来判断二分位置怎么写，防止越界。
            int mid = lo < 0 ? lo + hi >> 1 : lo + (hi - lo) / 2;
            int loSum = 0, hiSum = 0;
            for (int num : nums) {
                if (num <= mid) {
                    loSum ^= num;
                } else {
                    hiSum ^= num;
                }
            }
            if (loSum != 0 && hiSum != 0) {
                // 两个都不为0，说明 p 和 q 分别落到2个数组里了。
                return new int[]{loSum, hiSum};
            }
            if (loSum == 0) {
                // 说明 p 和 q 都比 mid 大，所以比 mid 小的数的异或和变为0了。
                lo = mid;
            } else {
                // 说明 p 和 q 都不超过 mid
                hi = mid;
            }
        }
        // 其实如果输入是符合要求的，程序不会执行到这里，为了防止compile error加一下
        return null;
    }
}
```