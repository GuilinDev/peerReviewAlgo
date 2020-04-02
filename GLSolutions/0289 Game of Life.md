## Solution 1
> 状态机

```java
    class Solution {
        final int[][] dirs = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}}; 
        public void gameOfLife(int[][] board) {
            // live: 1
            // death: 0
            // death -> live: -1
            // live -> death: 2
            // two passes, the first pass changes all cell's state for memorization, the second pass change back
            if (board == null || board.length == 0) {
                return;
            }
            int rows = board.length;
            int cols = board[0].length;
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (board[i][j] == 1) { // 活细胞
                        int livesNearby = calLives(board, i, j);
                        if (livesNearby < 2 || livesNearby > 3) {
                            board[i][j] = 2;
                        }
                    } 
                    if (board[i][j] == 0) { //死细胞
                        int livesNearby = calLives(board, i, j);
                        if (livesNearby == 3) {
                            board[i][j] = -1;
                        }
                    }
                }
            }
            update(board);
        }
        private int calLives(int[][] board, int row, int col) {
            int livesNearbyCell = 0;
            for (int[] dir : dirs) {
                int checkingRow = row + dir[0];
                int checkingCol = col + dir[1];
                if (checkingRow >= 0 &&
                    checkingRow < board.length &&
                    checkingCol >= 0 &&
                    checkingCol < board[0].length &&
                    (board[checkingRow][checkingCol] == 1 || board[checkingRow][checkingCol] == 2)) {
                        livesNearbyCell++;
                    }
            }
            return livesNearbyCell;
        }

        private void update(int[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    if (board[i][j] == -1) {
                        board[i][j] = 1;
                    } else if (board[i][j] == 2) {
                        board[i][j] = 0;
                    }
                }
            }
        }
    }
```