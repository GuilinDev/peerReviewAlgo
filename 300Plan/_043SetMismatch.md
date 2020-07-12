1. 用额外数据结构存储，统计次数或者是否出现，时间和空间复杂度O(n) + O(n)
2. 排序，然后判断前后数值的差别，O(nlogn) + O(n)
3. 改为负值，重复的数负负得正，缺失的数根据重复的数来找，O(n) + O(1)
4. 位操作的交换律和结合律，类似做法3，O(n) + O(1)
5. 数学法，用set()去重，set底部的实现是散列表，所以O(n) + O(n)

解法5
```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        """
        :type nums: List[int]
        :rtype: List[int]
        """
        n = len(nums)
        s = n * (n + 1) // 2 # 把1...n的数全部加起来
        miss = s - sum(set(nums)) # set()去重，重复数只加一次，这样找出缺失值
        duplicate = sum(nums) + miss - s # 根据缺失值找出重复值
        return [duplicate, miss]
```
简单为一行
```python
class Solution:
    def findErrorNums(self, nums: List[int]) -> List[int]:
        return [sum(nums) - sum(set(nums)), sum(range(1, len(nums)+1)) - sum(set(nums))]
```
解法3
```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        for (int num : nums) {
            if (nums[Math.abs(num) - 1] < 0) {
                // 已经转过一次负数的数，表明重复
                result[0] = Math.abs(num);
            } else {
                // 将所有元素转为负数
                nums[Math.abs(num)-1] *= -1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            // 剩下那个没有被转成负数的数
            if (nums[i] > 0) {
                result[1] = i + 1;
            }
        }
        return result;
    }
}
```
解法4
```java
class Solution {
    public int[] findErrorNums(int[] nums) {
        int[] result = new int[2];
        for(int i = 0; i < nums.length; i++) {
            int val = Math.abs(nums[i]);
            result[1] ^= (i + 1) ^ val; // 与[1, n]异或
            if (nums[val - 1] < 0) { //最终，异或三次的是重复数，不会变为正数
                result[0] = val;
            }
            else {
                nums[val - 1] = -nums[val - 1]; //正常数改变两次变为正数
            }
        }
        result[1] ^= result[0];
        return result;
    }
}
```
