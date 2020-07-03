Leetcode 287

#### 方法1，二分查找
二分搜索有两种形式，一是对值进行二分，二是对索引进行二分，这道题是对索引进行二分。因为所有元素值的范围是[1,n]，共有n+1个数，多一个出来。先找到mid的索引，然后遍历整个数组，找到所有比mid小于等于的元素个数，如果这个个数比mid小于等于，说明
较小的数（比mid小于等于的数）比较多（其实是多一个），那说明mid索引应该往较小的方向移动；反之，较大的数（比mid大的数）多一个，mid索引往较大的方向移动。时间复杂度O(nlogn)。最后while循环的终止条件是left <= right，left超过right的时候
恰好停在其中一个多余的数那里，想想为什么。
```python
class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        left = 0
        right = len(nums) - 1
        while (left <= right):
            mid = left + (right - left) // 2
            count = sum(i <= mid for i in nums)
            if count <= mid:
                left = mid + 1
            else:
                right = mid - 1
        return left
```

#### 方法2，带环链表的思想
类似与leetcode 141 和 142，判断和找到链表是否带环和交叉的位置，用快慢双指针，按照index来形成带环链表，以[1,3,4,2,2]为例，从index = 0开始，将该index所在的value作为下一个结点的index，以此来走到下一步；因为有n+1个元素，范围是1~n，所以不会越界
 (index=0)1(index=1) -> (index=1)3(index=3) -> (index=3)2(index=2) -> (index=2)4(index=4) -> (index=4)2(index=2) -> 这里开始形成环了，可以看到，找到环的入口，返回即是重复元素的一个。找换入口的方法就是142的快慢指针，时间复杂度O(n)。
 ```python
 class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        slow = 0
        fast = 0
        
        # slow走一步，fast走两步，直至相遇
        while True: 
            slow = nums[slow]
            fast = nums[nums[fast]]
            if slow == fast:
                break
        
        fast = 0 # 重置fast的位置
        
        # 现在开始各自走一步，直至相遇
        while (slow != fast):
            slow = nums[slow]
            fast = nums[fast]
            
        return slow # return fast
 ```
 
 #### 方法3，位操作
 巧妙的填充位
 ```python
 class Solution:
    def findDuplicate(self, nums: List[int]) -> int:
        flag = 0
        for num in nums:
            if flag & (1 << num) > 0:
                return num
            flag |= (1 << num)
 ```
