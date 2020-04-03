## Solution 1
> 主要是考虑一下特殊情况：前面的空格，正负号，位数是否是数字，是否整数越界这些条件，正常的计算就是result = result * 10 + digit

```java
    class Solution {
        public int myAtoi(String str) {
            if (str.isEmpty()) {
                return 0;
            }
            char[] charArr = str.toCharArray();
            int len = charArr.length;
            int index = 0;

            // 去除前导空格
            while (index < len && charArr[index] == ' ') {
                index++;
            }
            // 全部由空格组成
            if (index == len) {
                return 0;
            }

            // 判断正负数
            boolean negative = false;
            if (charArr[index] == '-') {
                negative = true;
                index++;
            } else if (charArr[index] == '+') {
                index++;
            } else if (!Character.isDigit(charArr[index])) { //其它符号在前面，不合法
                return 0;
            }

            int result = 0;
            while (index < len && Character.isDigit(charArr[index])) {
                int digit = charArr[index] - '0'; // 计算目前位数的整数值
                if (result > (Integer.MAX_VALUE - digit) / 10) {
                    // (Integer.MAX_VALUE - digit) / 10，是result = result * 10 + digit的反过来，这里用除法判断是否越界，以免出错
                    return negative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
                }
                result = result * 10 + digit;
                index++;
            }

            return negative ? -result : result;
        }
    }
```