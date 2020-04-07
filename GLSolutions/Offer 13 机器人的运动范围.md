## Solution 1
> BFS, O(mn)
```java

```

## Solution 2
> DFS, 向下和向右两个方向
```java
    class Solution {
        public int movingCount(int m, int n, int k) {
            int[][] visited = new int[m][n];
            return dfs(0, 0, m, n, visited, k);
        }
        private int dfs(int i, int j, int m, int n, int[][] visited, int k) {
            if (add(i, j) > k || i > m - 1|| j > n - 1 || visited[i][j] == 1) {
                return 0;
            }
            visited[i][j] = 1; //标记已经访问过了，否则无限走重复的路
            // 1+ 表示当前位置已经访问到，然后向右和向下移动
            return 1 + dfs(i + 1, j, m, n, visited, k) + dfs(i, j + 1, m, n, visited, k);
        }

        // 计算横纵坐标的数位和
        private int add(int a, int b) {
            int result = 0;
            while (a != 0) {
                result += a % 10;
                a /= 10;
            }
            while (b != 0) {
                result += b % 10;
                b /= 10;
            }
            return result;
        }
    }
```