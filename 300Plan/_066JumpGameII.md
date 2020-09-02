Greedy
```python
class Solution:
    def jump(self, nums: List[int]) -> int:
        if not nums:
            return 0
        currEnd = 0 # 当前数能达到的最远距离
        currFar = 0 # 当前数和之前一起考虑所能达到的最远距离
        steps = 0 # 返回结果
        
        for x in range(len(nums)):            
            if x > currEnd: # 上个数贡献的跳力已尽（边界已到），需要再跳一次去当前能达到的最远边界currFar
                currEnd = currFar
                steps += 1
                
            currFar = max(currFar, nums[x] + x) # 更新局部最远
                
        return steps
```
