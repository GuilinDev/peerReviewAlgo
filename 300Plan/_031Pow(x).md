递归
```python
class Solution:
    def myPow(self, x: float, n: int) -> float:
        if n == 0:
            return 1
        if n < 0:
            return 1 / self.myPow(x, -n)
        if n % 2 == 0: # 偶次方
            return self.myPow(x * x, n // 2)
        return x * self.myPow(x * x, n // 2) # 奇次方
```
Python语言特性
```python
class Solution:
    def myPow(self, x, n):
        return x ** n
```
还有一种
```python
class Solution:
    myPow = pow
```
