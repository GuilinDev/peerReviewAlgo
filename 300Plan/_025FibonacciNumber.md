Fibanacci数的递归解法，使用记忆化搜索
```python
class Solution:
    memo = {}
    def fib(self, N: int) -> int:
        if N < 2: 
            return N
        if N - 1 not in self.memo: 
            self.memo[N - 1] = self.fib(N - 1)
        if N - 2 not in self.memo:
            self.memo[N - 2] = self.fib(N - 2)
            
        return self.memo[N - 1] + self.memo[N - 2]
        
```

Python语言特性可以使用注解LRU cache来缓存重复子问题的结果
```python
from functools import lru_cache
class Solution:
    @lru_cache
    def fib(self, N: int) -> int:
        if N < 2: 
            return N
            
        return self.fib(N - 1) + self.fib(N - 2)
```
