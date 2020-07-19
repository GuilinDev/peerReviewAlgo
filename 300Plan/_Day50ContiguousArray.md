其实也是前缀和的思想
```python
class Solution:
    def findMaxLength(self, nums: List[int]) -> int:
        count = 0
        maxLen = 0
        hashmap = {0: -1} # 初始化，未开始之前count为0
        for index, num in enumerate(nums):
            if num == 0:
                count -= 1
            else:
                count += 1
                
            if count in hashmap:
                maxLen = max(maxLen, index - hashmap[count])
            else:
                hashmap[count] = index
        
        return maxLen
```
