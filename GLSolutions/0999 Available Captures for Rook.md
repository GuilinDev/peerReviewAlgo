## Solution 1
> 强行把这个题写成DFS的方式，只是注意每次DFS的时候只深度搜索一个方向而不是四个方向

```java
    class Solution {
        int result;
        //四个方向
        int[] dx = {-1, 0 ,1, 0};
        int[] dy = {0, -1, 0, 1};
        public int numRookCaptures(char[][] board) {
            result = 0;
            outer:
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (board[i][j] == 'R') {
                        // 准备检查四个方向
                        for (int k = 0; k < 4; k++) {
                            dfs(board, i, j, dx[k], dy[k]);
                        }
                        break outer;
                    }
                }
            }
            return result;
        }
        //这个dfs方向每次只深度搜索一个方向
        private void dfs(char[][] board, int i, int j, int dirX, int dirY) {
            if (i < 0 || i >= 8 || j < 0 || j >= 8 || board[i][j] == 'B') {
                return;
            }
            if (board[i][j] == 'p') {
                result++;
                return;
            }
            // 往四个方向一直找，直到return，条件判断是因为只走一个方向，而不是普通dfs每个结点都要检查四个方向
            if (dirX == -1 && dirY == 0) { // 上
                dfs(board, i - 1, j, dirX, dirY);
            } else if (dirX == 0 && dirY == -1) { // 左
                dfs(board, i, j - 1, dirX, dirY);
            } else if (dirX == 1 && dirY == 0) { // 下
                dfs(board, i + 1, j, dirX, dirY);
            } else if (dirX == 0 && dirY == 1) { // 右
                dfs(board, i, j + 1, dirX, dirY);
            }
        }
    }
```