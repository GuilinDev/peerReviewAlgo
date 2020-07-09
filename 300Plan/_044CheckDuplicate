leetcode 217 本来解法，用额外数据结构除重然后比较长度
```python
class Solution:
    def containsDuplicate(self, nums: List[int]) -> bool:
        return len(nums) != len(set(nums))
```

leetcode 217 加一个条件元素范围在[1...n]中，做标记的办法
```python
def containsDuplicate(nums) -> bool:
    for i in range(len(nums)):
        if nums[abs(nums[i]) - 1] < 0:
            return True
        else:
            nums[abs(nums[i]) - 1] *= -1

    return False


print(containsDuplicate(nums)
```

高斯公式
```python
def containsDuplicate(nums) -> bool:
    n = len(nums)
    return n * (n + 1) // 2 != sum(nums)
```
