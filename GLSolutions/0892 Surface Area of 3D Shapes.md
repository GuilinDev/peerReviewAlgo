## Solution 1
> 题意不太清楚，题目给出的坐标如 [[1,2],[3,4]]指的不是[1,2]或者[3,4]坐标位置，数字代表【某一位置】上的正方体个数，而位置是根据以下默认规则确定：

> 首先出现的[1,2]默认表示网格第一行，后面出现的[3,4]默认表示网格第二行，每组数字的行号依此类推。。。

> 第一行[1,2]中数字1代表在网格第一行第一个方格中有1个立方体，数字2代表在网格第一行第二个方格中有2个立方体，每个数字默认的位置依此类推。。。

```java
    class Solution {
        public int surfaceArea(int[][] grid) {
            //立方体个数
            int cubes = 0;
            // 立方体重叠次数
            int overlaps = 0;

            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    int current = grid[i][j];

                    cubes += current;

                    // 上下重叠
                    if (current > 1) {
                        overlaps += current - 1;
                    }

                    // 与上、左两个位置立方体重叠的次数（右下不用算，有的话下一轮算）
                    if (current > 0) {
                        //上
                        if (i > 0 && grid[i - 1][j] > 0) {
                            overlaps += Math.min(current, grid[i - 1][j]);
                        }
                        //左
                        if (j > 0 && grid[i][j - 1] > 0) {
                            overlaps += Math.min(current, grid[i][j - 1]);
                        }
                    }
                }
            }
            return 6 * cubes - overlaps * 2;
        }
    }
```
