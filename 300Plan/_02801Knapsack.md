递归式 
```java
c[i][j] = max{c[i-1][j], c[i-1][j-w[i]]+v[i]} 
```

Top Down 递归，类似Fibonacci数的记忆化搜索
```java
public class Test {
    public static void main(String[] args) {
        int[] a1 = {100, 70, 50, 10};
        int[] a2 = {10, 4, 6, 12};
        int w = 12;
        int[][] dp = new int[a1.length + 1][a2.length + 1];
        System.out.println(knapSack_topdown(w, a2, a1, a1.length));
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int knapSack_topdown(int W, int wt[], int val[], int n) {
        // Base Case
        if (n == 0 || W == 0)
            return 0;

        // 如果第nth项物品大于剩余空间，就不能放进去，直接递归下一件物品
        if (wt[n - 1] > W)
            return knapSack_topdown(W, wt, val, n - 1);

            // 当前物品可以放进去，可以选择或不选:
            // (1) nth item included
            // (2) not included
        else
            return max(val[n - 1] + knapSack_topdown(W - wt[n - 1], wt, val, n - 1),
                    knapSack_topdown(W, wt, val, n - 1));
    }
}
```
```python
# Top Down
def knapSack(W, wt, val, n): 
  
    # Base Case 
    if n == 0 or W == 0 : 
        return 0
  
    # 第n件物品大于剩余背包空间
    if (wt[n-1] > W): 
        return knapSack(W, wt, val, n-1) 
  
    # 剩余空间足够的话，两种情况: 
    # (1) 包括nth物品 
    # (2) 不包括 
    else: 
        return max( 
            val[n-1] + knapSack( 
                W-wt[n-1], wt, val, n-1),  
                knapSack(W, wt, val, n-1)) 
  
# end of function knapSack 
  
# To test above function 
val = [60, 100, 120] 
wt = [10, 20, 30] 
W = 50
n = len(val) 
print knapSack(W, wt, val, n) 
```

Bottom up，递推
```java
public class Test {
    public static void main(String[] args) {
        int[] a1 = {100, 70, 50, 10};
        int[] a2 = {10, 4, 6, 12};
        int w = 12;
        int[][] dp = new int[a1.length + 1][a2.length + 1];
        System.out.println(knapSack_topdown(w, a2, a1, a1.length));
    }

    static int max(int a, int b) {
        return (a > b) ? a : b;
    }

    static int knapSack_topdown(int W, int wt[], int val[], int n) {
        int i, w;
        int K[][] = new int[n + 1][W + 1];

        // 按照bottom up的方式对K[][]填表
        for (i = 0; i <= n; i++) {
            for (w = 0; w <= W; w++) {
                if (i == 0 || w == 0)
                    K[i][w] = 0; // 初始化
                else if (wt[i - 1] <= w)
                    K[i][w] = max(val[i - 1] + K[i - 1][w - wt[i - 1]], K[i - 1][w]);
                else
                    K[i][w] = K[i - 1][w]; //当前物品放不下
            }
        }

        return K[n][W];
    }
}

```
```python
# Bottom up
def knapSack(W, wt, val, n): 
    K = [[0 for x in range(W + 1)] for x in range(n + 1)] 
  
    # Build table K[][] in bottom up manner 
    for i in range(n + 1): 
        for w in range(W + 1): 
            if i == 0 or w == 0: 
                K[i][w] = 0
            elif wt[i-1] <= w: 
                K[i][w] = max(val[i-1] + K[i-1][w-wt[i-1]],  K[i-1][w]) 
            else: 
                K[i][w] = K[i-1][w] 
  
    return K[n][W] 
  
# Driver program to test above function 
val = [60, 100, 120] 
wt = [10, 20, 30] 
W = 50
n = len(val) 
print(knapSack(W, wt, val, n)) 
```
如上所说，递归式为
```java
c[i][j] = max{c[i-1][j], c[i-1][j-w[i]]+v[i]} 
```
每一次c[i][j]改变的值只与c[i-1][x] {x:1...j}有关，c[i-1][x]是前一次i循环保存下来的值，因此，可以将c缩减成一维数组状态转移方程转换为
```java
c[j] = max(c[j], c[j-w[i]]+v[i]);
```

```java
public class Test {
    public static void main(String[] args) {
        int[] a1 = {100, 70, 50, 10};
        int[] a2 = {10, 4, 6, 12};
        int w = 12;
        System.out.println(knapSack_topdown(w, a2, a1, a1.length));
    }

    static int knapSack_topdown(int W, int wt[], int val[], int n) {
        int i, j;
        int c[] = new int[W + 1];

        for (i = 0; i < n; i++) { // item
            for (j = W; j >= wt[i]; j--) { // 剩余weight,j如果从小到大计算，c[j - w[i]] 一定在 c[j]之前改变了，所以应该反过来计算
                c[j] = Math.max(c[j], c[j - wt[i]] + val[i]);
            }
        }

        return c[W];
    }
}
```
