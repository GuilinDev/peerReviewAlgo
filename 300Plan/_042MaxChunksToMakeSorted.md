Leetcode 769，对于这个问题，要我们做的是找到一些分裂线，每个分裂线之间的数字排序后，连在一起整个数组是有序的，求出最大分裂线分出的子数组的数目。
做法是让每条分裂线左边的数字都小于这条线右边的数字。这个想法与快速排序非常相似。用一个额外的max数组来记录达到当前位置时最大的数字，将max[i]与当前的索引i比较，如果相等，结果+1。

遍历整个array，每一次左边的元素小于等于右边的元素，都会产生一个新的chunck。

1. 时间复杂度O(n)，空间复杂度O(n)
```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int[] max = new int[len];
        int result = 0;
        max[0] = arr[0];
        
        for (int i = 1; i < len; i++) {
            max[i] = Math.max(max[i - 1], arr[i]);
        }
        
        for (int i = 0; i < len; i++) {
            if (max[i] == i) {
                result++;
            }
        }
        return result;
    }
}
```
2. 优化下空间到O(1), 时间复杂度依然O(n)
```java
class Solution {
    public int maxChunksToSorted(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int result = 0;
        int max = 0; // 跟索引比较，所以不用Integer.MIN_VALUE
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i) {
                result++;
            }
        }
        return result;
    }
}
```

3. Python， 时间复杂度O(n)，空间复杂度O(1)

```python
class Solution:
    def maxChunksToSorted(self, arr: List[int]) -> int:
        result = 0
        preMax = 0
        for i, num in enumerate(arr):
            if num > preMax:
                preMax = num
            if preMax == i:
                result += 1
        return result

```
