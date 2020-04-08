## Solution 1
> 给出n，左括号和右括号都是n，遇到题目想想递归，总有50%的机会是这个解法
```java
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> result = new ArrayList<>();
            recursive(n, n, result, "");
            return result;
        }
        private void recursive(int left, int right, List<String> result, String parens) {
            if (left == 0 && right == 0) {
                result.add(parens);
                return; // 左括号和右括号都没剩了，完成一种组合并返回
            }
            if (left > 0) { // 左括号还有剩，可以继续加
                recursive(left - 1, right, result, parens + "(");
            }
            if (right > 0 && right > left) { // right > left，剩下的右括号必须比左括号多，防止右括号先加出现‘)(’这样的情况
                recursive(left, right - 1, result, parens + ")");
            }
        }
    }
```

## Solution 2
> CCI上给出的一种解法
```java
```