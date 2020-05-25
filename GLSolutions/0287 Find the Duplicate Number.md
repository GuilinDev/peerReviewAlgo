## Solution 1
> 索引的二分查找，利用数组的元素的值在区间[1, n]的特点进行搜索，首先求出中间的索引mid，然后遍历整个数组，统计所有小于等于索引mid的元素的个数，如果元素个数大于mid索引，则说明重复值在[mid+1, n]这些索引之间，因为“较小的数比较多”，反之，重复值应在[1, mid-1]之间（“较大的数比较多”），然后依次类推，直到搜索完成，此时的low就是我们要求的重复值；

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int low = 1; //数字从1到n，因此index/value至少为1
        int high = nums.length - 1; //数字从1到n，n+1个数字，最大值（index）为n
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int count = 0;

            // 循环统计出比mid小的数字总数
            for (int num : nums) {
                if (num <= mid) { //如果等于mid，说明index和value在左边是1：1，重复值依然在[1, mid-1]之间（“较大的数多一个”）
                    count++;
                }
            }

            if (count <= mid) { //比mid小的数太少，重复数应该在[mid + 1, n]之间
                low = mid + 1;
            } else { //比mid小的数太多，重复数应该在[1, mid-1]
                high = mid - 1;
            }
        }
        return low;
    }
}
```

## Solution 2
> 双指针，数组元素的范围是[1, n]，利用数组元素和坐标的转换来形成一个闭环，利用快慢指针找到重复的值，这个做法如同142题带环链表一样，第一次快指针走两步慢指针走一步，第二次快慢指针都走一步，相遇的时候即为重复的元素。

```java
class Solution {
    public int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;

        // slow 等于数字中的元素，fast 等于数组中利用当前元素为index，二次找到的数字，相当于多走了一步
        while (true) {
            slow = nums[slow];
            fast = nums[nums[fast]];
            if (slow == fast) {
                break;
            }
        }

        fast = 0;
        // 各走一步直至相遇
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow; // return fast;
    }
}
```