## Solution 1
> 暴力解法，，以每个字符为中心检查最长的字串，需要区分奇偶，O(n^2)
```java
class Solution {
    private int lo, maxLen;

    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)
            return s;

        for (int i = 0; i < len-1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i+1); //assume even length.
        }
        return s.substring(lo, lo + maxLen); // 前闭后开
    }

    private void extendPalindrome(String s, int left, int right) {//字符串和两个指针
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //注意上面while循环是在左右不等才停止的，当前的maxLen是成员变量，需要维持奇偶中大的一个（较小的不进循环）
        if (maxLen < right - left - 1) {
            lo = left + 1;//回文子字符串的下标
            maxLen = right - left - 1;//回文子字符串的上标
        }
    }
}
```

## Solution 2
> DP，dp[i][j]表示字符串区间[i,j]是否为回文串；
> 当i == j时，只有一个字符，肯定是回文串；
> 如果j = i + 1，那说嘛二者是相邻字符，此时判断s[i]是否等于s[j]；
> 如果i和j不相邻，j - i >= 2的时候，除了判断s[i]和s[j]这两个两端的位置相等，还得保证dp[i+1][j-1]也得为真，才是回文串，所以递推式为
```java
dp[i, j] = 1                                   if i == j为回文串
         = s[i] == s[j]                        if j = i + 1
         = s[i] == s[j] && dp[i+1][j-1]    if j > i + 1  
```
   
```java
class Solution {
    public String longestPalindrome(String s) {
        String result = "";
        if (s.isEmpty()) {
            return result;
        }
        int len = s.length();

        // default false
        boolean[][] dp = new boolean[len][len];

        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j < len; j++) {
                // 两端相等，要么是i==j/相邻（j - i <= 2），要么是不相邻但要保证dp[i+1][j-1]
                dp[i][j] = (s.charAt(i) == s.charAt(j)) && (j - i <= 2 || dp[i+1][j-1]);

                // 如果当前是回文，检查是否需要更新最长子字符串
                if (dp[i][j] && (result == "" || j - i + 1 > result.length())) {
                    result = s.substring(i, j + 1); //前闭后开
                }
            }
        }
        return result;
    }
}
```

## Solution 1
> Manacher's 算法，O(n),不掌握了
```java

```