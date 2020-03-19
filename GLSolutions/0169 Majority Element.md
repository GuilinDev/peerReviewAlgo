## Solution 1
> 投票算法
```java
    class Solution {
        public int majorityElement(int[] nums) {
            assert(nums != null && nums.length > 0) : "the input should be a valid array";
            int count = 0;
            int result = 0;
            for (int num : nums) {
                if (num == result) {
                    count++;
                } else {
                    count--;
                }
                if (count < 1) {
                    result = num;
                    count = 1;
                }
            }
            return result;
        }
    }
```