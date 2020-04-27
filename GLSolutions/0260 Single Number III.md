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
            if ((diff & num) == 0) {
                result[0] ^= num; //用xor是因为要把diff &= -diff这步得到的bit相同的num彼此消掉（别的num出现两次）
            } else {
                result[1] ^= num; //用xor是因为要把diff &= -diff这步得到的bit不同的num彼此消掉（别的num出现两次）
            }
        }
        
        return result;
    }
}
```