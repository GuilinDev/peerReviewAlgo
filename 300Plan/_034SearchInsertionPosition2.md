插入到后面，并且允许重复值
```python
from typing import List


class Solution:
    def searchInsert(self, nums: List[int], target: int) -> int:
        return self.searchInsertHelper(nums, target, 0, len(nums) - 1)

    def searchInsertHelper(self, nums: List[int], target: int, left: int, right: int) -> int:
        if left > right:
            return left
        mid = left + (right - left) // 2
        if target == nums[mid]:
            if mid == len(nums) - 1 or target != nums[mid + 1]:
                return mid + 1
            else:
                return self.searchInsertHelper(nums, target, mid + 1, right)
        elif target < nums[mid]:
            return self.searchInsertHelper(nums, target, left, mid - 1)
        else:
            return self.searchInsertHelper(nums, target, mid + 1, right)


test = Solution()
list = [1, 3, 5, 5, 5, 5]
target = 5
print(test.searchInsert(list, target))

```
