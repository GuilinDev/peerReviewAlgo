## Solution 1
> 首先对题目进行建模。观察题目可知，在任意一个时刻，此问题的状态可以由两个数字决定：X 壶中的水量，以及 Y 壶中的水量。

> 在任意一个时刻，我们可以且仅可以采取以下几种操作：

> 把 X 壶的水灌进 Y 壶，直至灌满或倒空；

> 把 Y 壶的水灌进 X 壶，直至灌满或倒空；

> 把 X 壶灌满；

> 把 Y 壶灌满；

> 把 X 壶倒空；

> 把 Y 壶倒空。

> 因此，本题可以使用BFS或者DFS来解决，用Queue或者Stack

```java
    class Solution {
        public boolean canMeasureWater(int x, int y, int z) {
            if (z < 0 || x + y < z) {
                return false;
            }
            Set<Integer> set = new HashSet<>();
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(0);

            while (!queue.isEmpty()) {
                int n = queue.poll();

                //检查几种状态
                if (n + x <= x + y && set.add(n + x)) {
                    queue.offer(n + x);
                }
                if (n + y <= x + y && set.add(n + y)) {
                    queue.offer(n + y);
                }

                if (n - x >= 0 && set.add(n - x)) {
                    queue.offer(n - x);
                }

                if (n - y >= 0 && set.add(n - y)) {
                    queue.offer(n - y);
                }

                if (set.contains(z)) {//找到了
                    return true;
                }
            }
            return false;
        }
    }
```


## Solution 2
> 裴祖定理，先求出两个壶的公约数，然后检查大壶是否可以被公约数整除