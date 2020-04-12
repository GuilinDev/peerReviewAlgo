## Solution 1
> BFS
```java
class Solution {
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;

        int layers = 0;
        int fresh = 0;
        Queue<int[]> queue = new LinkedList<>();

        // 统计所有烂番茄的坐标 - 接下来会用到这个坐标进行BFS，并计算新鲜番茄的个数
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    //将烂番茄的坐标存入到队列中
                    queue.offer(new int[]{i, j});
                } else if (grid[i][j] == 1) {
                    fresh++;
                }
            }
        }

        if (fresh == 0) {
            return 0;
        }

        //根据每个烂番茄的坐标进行BFS
        int[][] dirs = {{1, 0},{-1, 0},{0, 1},{0, -1}};//上左右下四个方向
        while(!queue.isEmpty()) {
            layers++; //往外扩散一层
            int size = queue.size(); //BFS将烂番茄的坐标去除并计算四周
            for (int i = 0; i < size; i++) {
                int[] position = queue.poll();
                for (int[] dir : dirs) { // 遍历四个方向
                    int nearby_x = position[0] + dir[0];
                    int nearby_y = position[1] + dir[1];

                    if (nearby_x < 0 || nearby_x >= rows || nearby_y < 0 || nearby_y >= cols || //越界
                        grid[nearby_x][nearby_y] == 2 || //往外扩散的时候，本身就是烂番茄
                        grid[nearby_x][nearby_y] == 0) { //空格子
                            continue;
                        }
                    grid[nearby_x][nearby_y] = 2; //将新鲜番茄改成烂番茄

                    queue.offer(new int[]{nearby_x, nearby_y});//将新产生的烂番茄放入队列中

                    fresh--; //新鲜番茄减少
                }
            }
        }

        return fresh == 0 ? layers - 1 : -1; // layers - 1是因为比如共两层但只扩散了一天
    }
}
```