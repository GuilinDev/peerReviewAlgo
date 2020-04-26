## Solution 1
> 二分法的变种，同样是分为几种情况分别处理

```java
class Solution {
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] <= nums[right]) { // 右半截不受rotate影响
                if (target > nums[mid] && target <= nums[right]) { //target在mid到right有序的这部分 (两个条件是因为确保target的确在有序这部分)
                    left = mid + 1;
                } else { //target在left到mid乱序的这部分
                    right = mid - 1;
                }
            } else { // 左半截不受rotate影响
                if (target < nums[mid] && target >= nums[left]) { //target在left到mid之间有序的这部分 (两个条件是因为确保target的确在有序这部分)
                    right = mid - 1;
                } else { //target在mid到right之间乱序的部分
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
```