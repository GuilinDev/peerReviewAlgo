需要注意二分查找后left最后的位置，刚好是需要插入的位置
```python
class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        return self.searchInsertHelper(nums, target, 0, len(nums) - 1)
    def searchInsertHelper(self, nums: List[int], target: int, left: int, right: int) -> int:
        if left > right:
            return left
        mid = left + (right - left) // 2
        if target == nums[mid]:
            return mid
        elif target < nums[mid]:
            return self.searchInsertHelper(nums, target, left, mid - 1)
        else:
            return self.searchInsertHelper(nums, target, mid + 1, right)
```
