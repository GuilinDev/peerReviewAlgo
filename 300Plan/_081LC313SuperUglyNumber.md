DP或者小顶堆

```python
class Solution:
    def nthSuperUglyNumber(self, n: int, primes: List[int]) -> int:
        pool = [(p, p, 1) for p in primes] # 记录相乘后的丑数序列
        heapq.heapify(pool)
        ugly = [1]
        for _ in range(n-1):
            ugly.append(pool[0][0])
            while pool[0][0] == ugly[-1]:
                x, p, i = heapq.heappop(pool)
                heapq.heappush(pool, (p * ugly[i], p, i + 1))
        return ugly[-1]
```
