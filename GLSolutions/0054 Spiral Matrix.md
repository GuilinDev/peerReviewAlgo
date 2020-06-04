## Solution 1
> 由外层向内层螺旋打印数组，只能一行一列地打印，先往右，再往下，再往左，最后往上，用四个变量来记录打印的位置，下一轮从新的打印位置开始，可以用一个同样大小的辅助矩阵来判断是否visited，用变量优化空间


```java
class Solution {
   public List<Integer> spiralOrder(int[][] matrix) {

      List<Integer> result = new ArrayList<>();
      if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return result;

      int rowBegin = 0;
      int colEnd = matrix[0].length - 1;
      int colBegin = 0;
      int rowEnd = matrix.length - 1;

      while (rowBegin <= rowEnd && colBegin <= colEnd) {
         //从左向右
         for (int i = colBegin; i <= colEnd; i++) {
            result.add(matrix[rowBegin][i]);
         }
         rowBegin++;

         //从上到下
         for (int i = rowBegin; i <= rowEnd; i++) {
            result.add(matrix[i][colEnd]);
         }
         colEnd--;

         //从右到左
         if (rowBegin <= rowEnd) {//这里检查防止行已经打印完重复打印
            for (int i = colEnd; i >= colBegin; i--) {
               result.add(matrix[rowEnd][i]);
            }
         }
         rowEnd--;

         //从下到上
         if (colBegin <= colEnd) {//这里检查防止列已经打印完重复打印
            for (int i = rowEnd; i >= rowBegin; i--) {
               result.add(matrix[i][colBegin]);
            }
         }
         colBegin++;

      }
      return result;
   }
}
```