## Solution 1
> 判断有没有重合区域，如果有重合就计算重合区域

```java
    class Solution {
        public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
            int area1 = Math.abs(A - C) * Math.abs(B - D);
            int area2 = Math.abs(E - G) * Math.abs(F - H);
            
            int common = overlap(A, C, E, G) * overlap(B, D, F, H); //横坐标相差 * 纵坐标相差
            
            return area1 + area2 - common;
        }
        private int overlap(int lowerLeft1, int upperRight1, int lowerLeft2, int upperRight2) {
            if (lowerLeft1 >= upperRight2 || lowerLeft2 >= upperRight1) {
                return 0;
            }
            //两个lowerleft中的较大者在上面，两个upperRight中的较小者在下面，以此计算中间的区域
            return Math.abs(Math.max(lowerLeft1, lowerLeft2) - Math.min(upperRight1, upperRight2));
        }
    }
```