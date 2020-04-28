## Solution 1
> 先二分法找到peak，然后根据peak对左边升序数组进行二分查找，如果没找到再对右边的降序数组进行二分查找，最多三次二分查找

```java
/**
 * // This is MountainArray's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface MountainArray {
 *     public int get(int index) {}
 *     public int length() {}
 * }
 */
 
class Solution {
    public int findInMountainArray(int target, MountainArray mountainArr) {
        int index = -1;
        int len = mountainArr.length();
        if (len < 3) {
            return index;
        }
        int left = 0;
        int right = len - 1;
        int peak = -1;
        while (left <= right) { // find peak index
            int mid = left + (right - left) / 2;
            // cache to decreate API calls
            int midValue = mountainArr.get(mid);
            if (mid == 0 || (midValue > mountainArr.get(mid - 1) && midValue < mountainArr.get(mid + 1))) { // peak is on the right, watch the peak on the second leftmost
                left = mid + 1;
            } else if (mid == len - 1 || (midValue < mountainArr.get(mid - 1) && midValue > mountainArr.get(mid + 1))) { // peak is on the left, watch the peak on the second right most
                right = mid - 1;
            } else if (midValue > mountainArr.get(mid - 1) && midValue > mountainArr.get(mid + 1)) {// peak is mid
                peak = mid;
                break;
            } else { // < and <
                throw new IllegalArgumentException();
            }
        }

        // do binary search twice if possible
        left = 0;
        right = peak;
        while (left <= right) { // incremental array
            int mid = left + (right - left) / 2; // 不知为何这里用右移>>或者>>>，会超时
            // cache to decreate API calls
            int midValue = mountainArr.get(mid);
            if (midValue == target) {
                index = mid;
                break;
            } else if (midValue > target) { // target is on the left
                right = mid - 1;
            } else { // target is on the right
                left = mid + 1;
            }
        }

        if (index == -1) { // decremental array
            left = peak;
            right = len - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2; // 不知为何这里用右移>>或者>>>，会超时
                // cache to decreate API calls
                int midValue = mountainArr.get(mid);
                if (midValue == target) {
                    index = mid;
                    break;
                } else if (midValue > target) { // target is on the right
                    left = mid + 1;
                } else { // target is on the left
                    right = mid - 1;
                }
            }
        }
        return index;
    }
}
```