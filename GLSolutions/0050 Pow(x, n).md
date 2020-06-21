## Solution 1
> 做个循环来线性相乘不行，利用递归来对n每次做二分，Divide and Reduce，分为偶数和奇数（奇数要区分正负）复杂度为O(n)
```java
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) { // 基线条件
            return 1;
        }
        double half = myPow(x, n / 2); // 先递归到基线条件

        if (n % 2 == 0) { // 递归过程中，n若是为偶数，那x为正数或负数都没关系
            return half * half;
        } else if (n > 0) { // n为奇数，且大于0
            return half * half * x;
        } else { // n为奇数，且小于0
            return half * half / x;
        }
    }
}
```

```java
class Solution {
    public double myPow(double x, int n) {
        if (n == 0) {
            return 1;
        }
        // -2147483648,或者把n变成long
        if (n == Integer.MIN_VALUE) {
            x *= x;
            n /= 2;
        }
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }
        return (n % 2 == 0) ? myPow(x * x, n / 2) : x * myPow(x * x, n / 2);
    }
}
```
