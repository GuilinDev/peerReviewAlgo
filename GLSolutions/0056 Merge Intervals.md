## Solution 1
> 先排序再比较头尾

```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1) {
            return intervals;
        }

        // Ascending order
        Arrays.sort(intervals, (i0, i1) -> Integer.compare(i0[0], i1[0]));

        List<int[]> result = new ArrayList<>();
        int[] newInterval = intervals[0]; // 把第一个记录来初始化,记录当前已经合并的区间
        result.add(newInterval);

        // 遍历每一个二维数组，比较已经合并的区间的尾和新遍历到的头
        for (int[] interval : intervals) {
            if (interval[0] <= newInterval[1]) { // 当前遍历到的区间的头和已经合并的区间的尾
                newInterval[1] = Math.max(newInterval[1], interval[1]); // 更新已合并的区间的尾
            } else { // 断了
                newInterval = interval;
                result.add(newInterval);
            }
        }
        return result.toArray(new int[result.size()][]);
    }
}
```