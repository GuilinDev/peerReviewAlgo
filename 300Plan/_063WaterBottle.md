LC 1518

递归或者迭代都行，主要是numBottles//numExchange表示当前轮有多少个瓶子可以下一轮装满水（算这一轮的数量），numBottles%numExchange表示当前轮有多少个瓶子不能装水但可以累计到下一轮增加空瓶数准备装水

```python
class Solution:
    def numWaterBottles(self, numBottles: int, numExchange: int) -> int:
        if numBottles < numExchange:
            return numBottles
        
        remain = numBottles % numExchange        
        # 这一轮的瓶子 + 后面轮的瓶子
        return (numBottles - remain) + self.numWaterBottles(numBottles // numExchange + remain, numExchange)
```
