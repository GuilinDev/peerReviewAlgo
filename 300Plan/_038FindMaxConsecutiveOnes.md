用一个变量统计目前找到的连续1的个数
```python
class Solution:
    def findMaxConsecutiveOnes(self, nums: List[int]) -> int:
        count = 0
        result = 0
        for i in range(len(nums)):
            if nums[i] == 1:
                count += 1
                result = max(result, count)
            else:
                count = 0
        return result
```
