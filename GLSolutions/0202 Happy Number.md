## Solution 1
> 直接算是否等于1或者出现循环
```java
class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        Set<Integer> sums = new HashSet<>();
        while (n != 1) {
            int temp = 0;
            while (n != 0) {
                temp += Math.pow(n % 10, 2); //位数和会大于Integer.MAX_VALUE吗
                n /= 10;
            }
            n = temp;
            if (sums.contains(n)) {
                break;
            } else {
                sums.add(temp);
            }
        }
        return n == 1;
    }
}
```
> 如果不是快乐数会出现4，同时检查4，少一个break操作
```java
class Solution {
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        while (n != 1 && n != 4) {//是happy number一定会出现1，不是则一定会出现4
            int temp = 0;
            while (n != 0) {
                temp += Math.pow(n % 10, 2);
                n /= 10;
            }
            n = temp;
        }
        return n == 1;
    }
}
```

## Solution 2
> 尾递归
```java
class Solution {
    HashSet<Integer> set;
    public boolean isHappy(int n) {
        if (n <= 0) {
            return false;
        }
        set = new HashSet<>();
        return recursion(set, n);
    }
    private boolean recursion(HashSet<Integer> set, int n) {
        if (n == 1) {
            return true;
        }
        int temp = 0;
        while (n != 0) {
            temp += Math.pow(n % 10, 2);
            n /= 10;
        }
        if (!set.add(temp)) {
            return false;
        }  
        return recursion(set, temp);
    }
}
```
