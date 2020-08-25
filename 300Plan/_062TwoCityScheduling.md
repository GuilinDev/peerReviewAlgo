[返还金额的做法](https://leetcode.com/problems/two-city-scheduling/discuss/667786/Java-or-C%2B%2B-or-Python3-or-With-detailed-explanation)

贪心法的思想，先假设把所有人都送去城市A面试，计算所有的费用，然后现在需要把一般人N送去城市B面试，需要获取refund来减少开支，refund[i] = cost[i][1] - cost[i][0]，这个refund值如果为正说明需要额外付钱，
如果为负则可以获得refund，对这个refund进行排序，前面的值为可能的refund（为负），加上即为最小开销。


```python
class Solution:
    def twoCitySchedCost(self, costs: List[List[int]]) -> int:
        N = len(costs) // 2 # N个人
        refund = []
        minCost = 0;
        
        for A, B in costs: # 计算每个人的refund
            refund.append(B - A)
            minCost += A
            
        refund.sort()
        
        for x in range(N): # B城市N个人
            minCost += refund[x]
            
        return minCost
```



```java
class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int N = costs.length / 2; // N个人
        int[] refund = new int[N * 2];
        
        int minCost = 0, index = 0;
        for (int[] cost : costs) {
            refund[index] = cost[1] - cost[0];
            index++;
            minCost += cost[0]; // 全部人送去A城市面试
        }
        
        Arrays.sort(refund); // 根据refund升序排列，前面的是送去A城市代价大的
        
        for (int i = 0; i < N; i++) {
            minCost += refund[i];
        }
        
        return minCost;
    }
}
```
