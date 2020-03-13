## Solution 1
> 用stack
```java
    class Solution {
    public int calculate(String s) {
        assert(!s.isEmpty()) : "The input string is not valid.";
        int len = s.length();
        int result = 0;
        Stack<Integer> stack = new Stack<>();
        int sign = 1;
        
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                int num = 0;
                while (i < len && Character.isDigit(s.charAt(i))) {
                    num = num * 10 + (s.charAt(i) - '0');
                    i++;
                }
                result += sign * num;
                i--; //跳出while循环的时候i多加了一位，所以要退回来一位
            } else if (ch == '+') {
                sign = 1;
            } else if (ch == '-') {
                sign = -1;
            } else if (ch == '(') {//遇到左括号先把之前的计算结果和sign存入stack
                stack.push(result);
                stack.push(sign);
                result = 0;
                sign = 1;
            } else if (ch == ')') {//遇到右括号将之前的左括号时存入的结果和符号弹出来计算
                result *= stack.pop();
                result += stack.pop();
            }
        }
        return result;
    }
}
```

## Solution 2
> 优化下空间

## Solution 3
> 根据括号做递归