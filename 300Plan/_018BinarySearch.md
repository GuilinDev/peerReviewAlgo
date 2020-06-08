### Binary Search
> 注意边界
常规写法
```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        left, right = 0, len(nums) - 1
        while left <= right:
            mid = left + (right - left) // 2
            if nums[mid] == target:
                return mid
            elif nums[mid] < target: # target too large, on the right
                left = mid + 1
            else: # target on the left
                right = mid - 1
        
        return -1
```

递归
```python
 class Solution:
    def search(self, nums, target):
        return self.searchUtil(nums, 0, len(nums) - 1, target)

    def searchUtil(self, nums, left, right, target):
        if left > right:
            return -1

        mid = left + ((right - left) >> 1)

        if nums[mid] < target:
            return self.searchUtil(nums, mid + 1, right, target)

        if nums[mid] > target:
            return self.searchUtil(nums, left, mid - 1, target)

        return mid
```

built-in方法
```python
class Solution:
    def search(self, nums, target):
        try:
            return nums.index(target)
        except ValueError:
            return -1
```
