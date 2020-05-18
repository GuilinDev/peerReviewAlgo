## Solution 1
> 暴力法就是先查整个字符串是不是回文，不是的话就枚举删除每个字符来判断，O(O^2)；优化就是使用双指针，遇到不相等的字符就检查删除左边的和删除右边的，O(n)

```java
class Solution {
    public boolean validPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int left = 0; 
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) == s.charAt(right)) {
                left++;
                right--;
            } else {// 不相等的时候分为左边和右边分别检查
                boolean leftIsPalindrome = true;
                boolean rightIsPlindrome = true;
                // 删掉右边元素
                for (int i = left, j = right - 1; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        leftIsPalindrome = false;
                        break;
                    }
                }
                // 删掉左边元素
                for (int i = left + 1, j = right; i < j; i++, j--) {
                    if (s.charAt(i) != s.charAt(j)) {
                        rightIsPlindrome = false;
                        break;
                    }
                }

                // 当这两个子串中至少有一个是回文串时，就说明原始字符串删除一个字符之后就以成为回文串。
                // 因为还是要查从left + 1到right，和left到right - 1，或者删掉左边，或者删掉右边
                return leftIsPalindrome || rightIsPlindrome;                
            }
        }
        return true;
    }
}
```

简洁一点的写法
```java
class Solution {
    public boolean validPalindrome(String s) {
        if (s.isEmpty()) {
            return true;
        }
        int left = 0;
        int right = s.length() - 1;

        // 找到不相等的地方
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        // 检查删除左边和右边
        return palindrome(left + 1, right, s) || palindrome(left, right - 1, s);
    }

    // 判断回文
    private boolean palindrome(int left, int right, String s) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```