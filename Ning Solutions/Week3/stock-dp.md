```java
//121. Best Time to Buy and Sell Stock
//122. Best Time to Buy and Sell Stock II
//123. Best Time to Buy and Sell Stock III
//188. Best Time to Buy and Sell Stock IV
//309. Best Time to Buy and Sell Stock with Cooldown
//714. Best Time to Buy and Sell Stock with Transaction Fee

/* dp[i][k][0] = max(dp[i-1][k][0], dp[i-1][k][1] + prices[i])
              max(   选择 rest  ,           选择 sell      )
解释：今天我没有持有股票，有两种可能：
要么是我昨天就没有持有，然后今天选择 rest，所以我今天还是没有持有；
要么是我昨天持有股票，但是今天我 sell 了，所以我今天没有持有股票了。
dp[i][k][1] = max(dp[i-1][k][1], dp[i-1][k-1][0] - prices[i])
              max(   选择 rest  ,           选择 buy         )
解释：今天我持有着股票，有两种可能：
要么我昨天就持有着股票，然后今天选择 rest，所以我今天还持有着股票；
要么我昨天本没有持有，但今天我选择 buy，所以今天我就持有股票了。
*/


class Solution {
 // Case I: k = 1
public int maxProfit(int[] prices) {
//T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + prices[i])
//T[i][1][1] = max(T[i-1][1][1], T[i-1][0][0] - prices[i]) = max(T[i-1][1][1], -prices[i])
//注意一下状态转移方程，新状态只和相邻的一个状态有关，其实不用整个 dp 数组，
// 只需要一个变量储存相邻的那个状态就足够了，这样可以把空间复杂度降到 O(1):
    int T_i10 = 0, T_i11 = Integer.MIN_VALUE;
    for (int price : prices) {
        T_i10 = Math.max(T_i10, T_i11 + price);
        T_i11 = Math.max(T_i11, -price);
    }        
    return T_i10;
}

//Case II: k = +Infinity
//T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i])
//T[i][k][1] = max(T[i-1][k][1], T[i-1][k-1][0] - prices[i]) = max(T[i-1][k][1], T[i-1][k][0] - prices[i])

public int maxProfit(int[] prices) {
    int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
    for (int price : prices) {
        int T_ik0_old = T_ik0;
        T_ik0 = Math.max(T_ik0, T_ik1 + price);
        T_ik1 = Math.max(T_ik1, T_ik0_old - price);
    }  
    return T_ik0;
}

// Case III: k = 2
//T[i][2][0] = max(T[i-1][2][0], T[i-1][2][1] + prices[i])
//T[i][2][1] = max(T[i-1][2][1], T[i-1][1][0] - prices[i])
//T[i][1][0] = max(T[i-1][1][0], T[i-1][1][1] + prices[i])
//T[i][1][1] = max(T[i-1][1][1], -prices[i])

public int maxProfit(int[] prices) {
    int T_i10 = 0, T_i11 = Integer.MIN_VALUE;
    int T_i20 = 0, T_i21 = Integer.MIN_VALUE; 
    for (int price : prices) {
        T_i20 = Math.max(T_i20, T_i21 + price);
        T_i21 = Math.max(T_i21, T_i10 - price);
        T_i10 = Math.max(T_i10, T_i11 + price);
        T_i11 = Math.max(T_i11, -price);
    }
    return T_i20;
}

// Case IV: k is arbitrary
public int maxProfit(int k, int[] prices) {
    if (k > prices.length / 2) { // if k = infinity, will out of space.
        int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
        for (int price : prices) {
            int T_ik0_old = T_ik0;
            T_ik0 = Math.max(T_ik0, T_ik1 + price);
            T_ik1 = Math.max(T_ik1, T_ik0_old - price);
        }  
        return T_ik0; // need return.
    }  
    int[] T_ik0 = new int[k + 1];
    int[] T_ik1 = new int[k + 1];
    Arrays.fill(T_ik1, Integer.MIN_VALUE);
    for (int price : prices) {
        for (int j = k; j > 0; j--) {
            T_ik0[j] = Math.max(T_ik0[j], T_ik1[j] + price);
            T_ik1[j] = Math.max(T_ik1[j], T_ik0[j - 1] - price);
        }
    }
        
    return T_ik0[k];
}


// Case V: k = +Infinity but with cooldown
//T[i][k][0] = max(T[i-1][k][0], T[i-1][k][1] + prices[i])
//T[i][k][1] = max(T[i-1][k][1], T[i-2][k][0] - prices[i])

public int maxProfit(int[] prices) {
    int T_ik0_pre = 0, T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
    for (int price : prices) {
        int T_ik0_old = T_ik0;
        T_ik0 = Math.max(T_ik0, T_ik1 + price);
        T_ik1 = Math.max(T_ik1, T_ik0_pre - price);
        T_ik0_pre = T_ik0_old;
    }
    return T_ik0;
}

//Case VI: k = +Infinity but with transaction fee
//Solution I -- pay the fee when buying the stock:
public int maxProfit(int[] prices, int fee) {
    int T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
    
    for (int price : prices) {
        int T_ik0_old = T_ik0;
        T_ik0 = Math.max(T_ik0, T_ik1 + price);
        T_ik1 = Math.max(T_ik1, T_ik0_old - price - fee);
    }
        
    return T_ik0;
}

//Solution II -- pay the fee when selling the stock:
public int maxProfit(int[] prices, int fee) {
    long T_ik0 = 0, T_ik1 = Integer.MIN_VALUE;
    
    for (int price : prices) {
        long T_ik0_old = T_ik0;
        T_ik0 = Math.max(T_ik0, T_ik1 + price - fee);
        T_ik1 = Math.max(T_ik1, T_ik0_old - price);
    }
        
    return (int)T_ik0;
}


}

```
