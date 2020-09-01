LC 55

DP
```python
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        # 递归方程 dp[i] = max(dp[i - 1], nums[i - 1]) - 1
        if not nums:
            return True
        prev, curr = 0, 0
        
        for x in range(1, len(nums)):
            curr = max(prev, nums[x - 1]) - 1
            
            if curr < 0:
                return False
            
            prev = curr
            
        return True
```

Greedy
```python
class Solution:
    def canJump(self, nums: List[int]) -> bool:
        if not nums:
            return True
        
        reach = 0
        l = len(nums)
        
        for x in range(l):
            if x > reach or reach >= l - 1:
                break
            # Greedy寻找当前位置最远跳力
            reach = max(reach, x + nums[x])
            
        return reach >= l - 1
```
