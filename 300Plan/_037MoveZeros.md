leetcode 283， 从左到右扫描，用一个索引记录最开始非0的位置（一直查找直到遇到非0），另外一个索引如果遇到非零的元素，就和第一个索引交换元素
```python
class Solution:
    def moveZeroes(self, nums: List[int]) -> None:
        """
        Do not return anything, modify nums in-place instead.
        """
        zeroIndex = 0
        for i in range(len(nums)):
            if nums[i] != 0:
                if i != zeroIndex: # 相同元素不用交换
                    nums[i], nums[zeroIndex] = nums[zeroIndex], nums[i]
                zeroIndex += 1
```
