## Solution 1
> Binary Search
```java
class Solution {
    public int mySqrt(int x) {
        assert x >= 0 : "x should be non-negative";
        while (x <= 1) {
            return x;
        }
        int left = 0;
        int right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int temp = x / mid;
            if (temp >= mid) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return right; // left <= right的条件后，right在左边，刚好符合地板除法
    }
}
```

## Solution 2
> 牛顿迭代法

```java

```