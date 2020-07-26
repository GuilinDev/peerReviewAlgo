LC1480，练习前缀和
```python
class Solution:
    def runningSum(self, nums: List[int]) -> List[int]:
        if not nums:
            return []
        preSum = [None] * len(nums)
        preSum[0] = nums[0]
        for i in range(1, len(nums)):
            preSum[i] = preSum[i - 1] + nums[i]
            
        return preSum
```

直接调用native方法
```python
class Solution:
    def runningSum(self, nums: List[int]) -> List[int]:
        if not nums:
            return []
        # return itertools.accumulate(nums)
        return accumulate(nums)
```
