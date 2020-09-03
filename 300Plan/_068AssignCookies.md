LC 455 
Sort + Greedy

```python
class Solution:
    def findContentChildren(self, g: List[int], s: List[int]) -> int:
        g.sort()
        s.sort()
        gIndex, sIndex = 0, 0
        
        while gIndex < len(g) and sIndex < len(s):
            if g[gIndex] <= s[sIndex]:
                gIndex += 1
                sIndex += 1
            else: # 跳过当前饼干，尝试更大的饼干
                sIndex += 1
                
        return gIndex;
```
