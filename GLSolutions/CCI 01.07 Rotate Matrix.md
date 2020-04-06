## Solution 1
> 先按左上右下的轴进行两个半块的翻转，然后按照横轴进行左右翻转

```java
    class Solution {
        public void rotate(int[][] matrix) {
            int n = matrix.length;
            // 先左上右下为轴进行翻转，[i,j]和[j,i]对调
            // 实现的时候只用翻转右上半块
            for (int i = 0; i < n - 1; i++) { // 最后一行已经翻转过或者在轴上，不用再遍历一次
                for (int j = i + 1; j < n; j++) { // j = i + 1表示右上半块i + 1之前的元素在左上右下的轴左边或者轴上已被翻转过
                    matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                    matrix[j][i] = matrix[i][j] ^ matrix[j][i];
                    matrix[i][j] = matrix[i][j] ^ matrix[j][i];
                }
            }

            // 然后按照横轴的中心左右翻转，奇偶不影响
            int mid = n >> 1;
            for (int i = 0; i < n; i++) { // 每一行
                for (int j = 0; j < mid; j++) { // 一半的列
                    matrix[i][j] = matrix[i][j] ^ matrix[i][n - 1 - j];
                    matrix[i][n - 1 - j] = matrix[i][j] ^ matrix[i][n - 1 - j];
                    matrix[i][j] = matrix[i][j] ^ matrix[i][n - 1 - j];
                }
            }
        }
    }
```