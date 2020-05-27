## Solution 1
> 编译原理的简化应用
> 递归
```java
class Solution {
    String src;
    int ptr;

    public String decodeString(String s) {
        src = s;
        ptr = 0;
        return getString();
    }

    public String getString() {
        if (ptr == src.length() || src.charAt(ptr) == ']') {
            // String -> EPS
            return "";
        }

        char cur = src.charAt(ptr);
        int repTime = 1;
        String ret = "";

        if (Character.isDigit(cur)) {
            // String -> Digits [ String ] String
            // 解析 Digits
            repTime = getDigits(); 
            // 过滤左括号
            ++ptr;
            // 解析 String
            String str = getString(); 
            // 过滤右括号
            ++ptr;
            // 构造字符串
            while (repTime-- > 0) {
                ret += str;
            }
        } else if (Character.isLetter(cur)) {
            // String -> Char String
            // 解析 Char
            ret = String.valueOf(src.charAt(ptr++));
        }
        
        return ret + getString();
    }

    public int getDigits() {
        int ret = 0;
        while (ptr < src.length() && Character.isDigit(src.charAt(ptr))) {
            ret = ret * 10 + src.charAt(ptr++) - '0';
        }
        return ret;
    }
}
```

> 用Stack模拟递归
```java
class Solution {
    int ptr;

    public String decodeString(String s) {
        LinkedList<String> stk = new LinkedList<String>();
        ptr = 0;

        while (ptr < s.length()) {
            char cur = s.charAt(ptr);
            if (Character.isDigit(cur)) {
                // 获取一个数字并进栈
                String digits = getDigits(s);
                stk.addLast(digits);
            } else if (Character.isLetter(cur) || cur == '[') {
                // 获取一个字母并进栈
                stk.addLast(String.valueOf(s.charAt(ptr++))); 
            } else {
                ++ptr;
                LinkedList<String> sub = new LinkedList<String>();
                while (!"[".equals(stk.peekLast())) {
                    sub.addLast(stk.removeLast());
                }
                Collections.reverse(sub);
                // 左括号出栈
                stk.removeLast();
                // 此时栈顶为当前 sub 对应的字符串应该出现的次数
                int repTime = Integer.parseInt(stk.removeLast());
                StringBuffer t = new StringBuffer();
                String o = getString(sub);
                // 构造字符串
                while (repTime-- > 0) {
                    t.append(o);
                }
                // 将构造好的字符串入栈
                stk.addLast(t.toString());
            }
        }

        return getString(stk);
    }

    public String getDigits(String s) {
        StringBuffer ret = new StringBuffer();
        while (Character.isDigit(s.charAt(ptr))) {
            ret.append(s.charAt(ptr++));
        }
        return ret.toString();
    }

    public String getString(LinkedList<String> v) {
        StringBuffer ret = new StringBuffer();
        for (String s : v) {
            ret.append(s);
        }
        return ret.toString();
    }
}
```

# Solution2
> 迭代
```java
class Solution {
    /**
     * 直观解法
    ① 找到第一对匹配的括号开始和结束的索引位置
    ② 从"["括号的开始位置向前扫描，找到数字出现的起始坐标
    ③ 把出现在数字之前的字符复制到StringBuilder中
    ④ 复制括号中的字符到StringBuilder中
    ⑤ 复制"]"括号后的字符到StringBuilder中
    ⑥ s赋值为StringBuilder中的字符串，并将StringBuilder清空，重复上述过程，指导s字符串中找不到括号为止；
    */
    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        while (s.contains("[")) {
            //第一对括号的起始位置和结束位置
            int startEqual = 0, endEqual = 0;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == '[') startEqual = i;
                if (s.charAt(i) == ']') {endEqual = i; break;}
            }

            //括号中的字符串sub
            String sub = s.substring(startEqual + 1, endEqual);

            //向后扫描，找到数字开始的位置;
            int numIndex = startEqual - 1;
            while (numIndex >= 0 && Character.isDigit(s.charAt(numIndex))) numIndex--;

            //需要重复的次数
            int times = Integer.parseInt(s.substring(numIndex + 1, startEqual));

            //将"["括号数字之前的字符添加到sb中
            sb.append(s, 0, numIndex + 1);
            while (times-- > 0)
                sb.append(sub);
            //将"]"右括号之后的字符添加到sb中
            sb.append(s, endEqual + 1, s.length());

            s = sb.toString();
            sb = new StringBuilder();
        }
        return s;
    }
}
```