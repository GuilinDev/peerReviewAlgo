## Solution 1
> 使用比较直接的想法，为了找到两个字符串的最大共约数，先找到较短的字符串，然后从大到小找最大的可以组成两个字符串的前缀
```java
    class Solution {
        public String gcdOfStrings(String str1, String str2) {
            if (str1.isEmpty() || str2.isEmpty()) {
                return "";
            }
            int len1 = str1.length();
            int len2 = str2.length();

            for (int i = Math.min(len1, len2); i >= 1; i--) { //从最大的字串开始找
                if (len1 % i == 0 && len2 % i == 0) {
                    String prefix = str1.substring(0, i);
                    if (check(prefix, str1) && check(prefix, str2)) {
                        return prefix;
                    }
                }
            }
            return "";
        }
        private boolean check(String prefix, String str) {
            int divider = str.length() / prefix.length();
            String ans = "";
            for (int i = 0; i < divider; i++) {
                ans += prefix;
            }
            return ans.equals(str);
        }
    }
```

## Solution 2 
> 上面方法的优化
```java

```

## Solution 3 
> 数学的解法
```java
class Solution {
    public String gcdOfStrings(String str1, String str2) {
        // 假设str1是N个x，str2是M个x，那么str1+str2肯定是等于str2+str1的。
        if (!(str1 + str2).equals(str2 + str1)) {
            return "";
        }
        // 辗转相除法求gcd。
        return str1.substring(0, gcd(str1.length(), str2.length()));
    }

    private int gcd(int a, int b) {
        return b == 0 ? a: gcd(b, a % b);
    }
}
```