## Solution 1
> 暴力法超时，O(n ^ 2)
```java
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int N = A.length, ans = 0;
        int[] sum = new int[N+1];

        for (int i = 0; i < N; i++) {
        	sum[i+1] = sum[i] + A[i];
        }

        for (int i = 0; i < N; i++) {
        	for (int j = i+1; j <= N; j++) {
        		int res = sum[j] - sum[i];
        		if (res % K == 0) ans++; 
        	}
        }
        return ans ;
    }
}
```


## Solution 2
> 使用前缀和，（同523. Continuous Subarray Sum），O(n)
使用HashMap
[题解](./imgs/0974_1.png)
```java
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        Map<Integer, Integer> record = new HashMap<>();
        record.put(0, 1);
        int sum = 0, ans = 0;
        for (int elem: A) {
            sum += elem;
            // 注意 Java 取模的特殊性，当被除数为负数时取模结果为负数，需要纠正
            int modulus = (sum % K + K) % K;
            int same = record.getOrDefault(modulus, 0);
            ans += same;
            record.put(modulus, same + 1);
        }
        return ans;
    }
}
```

另一种解法
[题解](./imgs/0974_2.png)
```java
class Solution {
    public int subarraysDivByK(int[] A, int K) {
        int len = A.length;
        int sum = 0;
        int ans = 0;
        int[] map = new int[K];

        map[0] = 1;
        for (int i = 1; i <= len; i++) {
            sum = sum + A[i-1]; 
            int key = (sum % K + K) % K;
            ans += map[key];
            map[key]++;
        }
        
        return ans;
    }
}
```