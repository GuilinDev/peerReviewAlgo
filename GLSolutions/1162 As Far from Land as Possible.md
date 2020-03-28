## Solution 1
> BFS，逐层扫描
```java
    class Solution {
        public int maxDistance(int[][] grid) {
            int len = grid.length;

            Queue<int[]> queue = new ArrayDeque<>();

            // 将所有陆地加入队列
            for (int i = 0; i < len; i++) {
                for (int j = 0; j < len; j++) {
                    if (grid[i][j] == 1) {
                        queue.offer(new int[]{i, j});
                    }
                }
            }

            // 如果给定的grid全是陆地和海洋
            if (queue.isEmpty() || queue.size() == len * len) {
                return -1;
            }

            int depth = -1;
            while (!queue.isEmpty()) {
                depth++; // 看BFS有多少层
                int levelLen = queue.size();
                // 这里先取出levelLen个结点，作为BFS层序遍历的一层
                for (int i = 0; i < levelLen; i++) {
                    int[] cell = queue.poll();
                    int row = cell[0];
                    int col = cell[1];

                    // 遍历上方单元格
                    if (row - 1 >= 0 && grid[row - 1][col] == 0) {
                        grid[row - 1][col] = 2; // 改变海洋的值
                        queue.offer(new int[]{row - 1, col});
                    }

                    // 遍历下方单元格
                    if (row + 1 < len && grid[row + 1][col] == 0) {
                        grid[row + 1][col] = 2;
                        queue.offer(new int[]{row + 1, col});
                    }

                    // 遍历左方单元格
                    if (col - 1 >= 0 && grid[row][col - 1] == 0) {
                        grid[row][col - 1] = 2;
                        queue.offer(new int[]{row, col - 1});
                    }

                    // 遍历右方单元格
                    if (col + 1 < len && grid[row][col + 1] == 0) {
                        grid[row][col + 1] = 2;
                        queue.offer(new int[]{row, col + 1});
                    }
                }
            }

            return depth;
        }
    }
```

## Solution 2
> DP