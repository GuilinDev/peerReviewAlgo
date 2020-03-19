## Solution 1
> 经典DFS

```java
    class Solution {
        public int maxAreaOfIsland(int[][] grid) {
            if (grid == null || grid.length == 0) {
                return 0;
            }
            int rows = grid.length;
            int cols = grid[0].length;
            int maxArea = 0;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == 1) {
                        maxArea = Math.max(dfs(grid, i, j), maxArea);
                    }
                }
            }
            return maxArea;
        }
        private int dfs(int[][] grid, int i, int j) {
            if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
                grid[i][j] = 0;
                //当前等于1就加1并且继续dfs
                return 1 + dfs(grid, i - 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1) + dfs(grid, i + 1, j);
            }
            return 0; //当前等于0就直接返回
        }
    }
```