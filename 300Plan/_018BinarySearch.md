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

built-in方法，index()是线性查找
```python
class Solution:
    def search(self, nums, target):
        try:
            return nums.index(target)
        except ValueError:
            return -1
```
bisect标准库是对二分查找的实现，接收有序数组作为参数
```python
class Solution:
    def search(self, nums, target):
        result = bisect.bisect_left(nums, target)
        
        # 返回值为left所在的索引，所以在没找到的情况下需要判断一下是否和target相等
        if result != len(nums) and target == nums[result]:
            return result
        
        return -1
```
