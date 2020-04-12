## Solution 1
![题意](./imgs/Offer57-II.png)
> 直接的做法

```java
class Solution {
    public int[][] findContinuousSequence(int target) {
        assert(target > 0) : "The input should be a positive number";
        List<int[]> resultsList = new ArrayList<>();
        int left = 1;
        int right = -1;//刚开始不希望滑动窗口里面有任何元素
        int count = 0;
        while (right <= target / 2 + 1) {
            if (count == target) { //[left, right]
                int[] oneRecord = new int[right - left + 1];
                for (int i = left; i <= right; i++) {
                    oneRecord[i - left] = i;
                }
                resultsList.add(oneRecord);
                //先挪左边界
                count -= left;
                left++;
            } else if (count < target) {
                right++;
                count += right;
            } else { // count > target
                count -= left;
                left++;
            }
        }
        
        return resultsList.toArray(new int[resultsList.size()][]);
    }
}
```