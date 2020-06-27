
Java
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Ascending order
        Arrays.sort(intervals, (i0, i1) -> Integer.compare(i0[0], i1[0]));

        List<int[]> result = new ArrayList<>();
        int[] newMergingInterval = intervals[0]; // 把第一个记录来初始化,记录当前已经合并的区间
        result.add(newMergingInterval);

        // 遍历每一个二维数组，比较已经合并的区间的尾和新遍历到的头
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) { // 当前遍历到的区间的头和已经合并的区间的尾
                newMergingInterval[1] = Math.max(newMergingInterval[1], interval[1]); // 更新已合并的区间的尾
            } else { // 断了
                newMergingInterval = interval;
                result.add(newMergingInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
```

Python
```python
class Solution:
    def merge(self, intervals: List[List[int]]) -> List[List[int]]:
        intervals = sorted(intervals) #原地排序
        result = []
        
        for interval in intervals:
            if len(result) == 0 or interval[0] > result[-1][1]:
                result.append(interval)
            else:
                result[-1][1] = max(result[-1][1], interval[1])
                
        return result
```
