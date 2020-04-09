## Solution 1
> 直观解法，时间O(n)，空间O(n)

```java
class Solution {
    public String reverseWords(String s) {
        if (s.isEmpty()) {
            return s;
        }
        String[] strs = s.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        int len = strs.length;
        for (int i = len - 1; i >= 0; i--) {
            sb.append(strs[i]);
            sb.append(" ");
        }

        return sb.toString().trim();
    }
}
```

> 调用api的写法
```java
class Solution {
    public String reverseWords(String s) {
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);
        return String.join(" ", wordList);
    }
}
```

## Solution 2
> 利用Deque双端队列的特性，逐个把单个的单词加在头部，然后合并，O(n)和O(n)

```java
class Solution {
    public String reverseWords(String s) {
        if (s.isEmpty()) {
            return s;
        }
        int left = 0;
        int right = s.length() - 1;

        // 把单词挨个从头部进入，s前部的单词最后在右边
        Deque<String> deque = new ArrayDeque<>();
        // 用来记录每个单词
        StringBuilder sb = new StringBuilder();

        // 去除头尾的空格
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }
        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }

        while (left <= right) {
            char ch = s.charAt(left);
            
            // 一个单词结束
            if (ch == ' ' && sb.length() != 0) {
                deque.offerFirst(sb.toString());
                sb.setLength(0); // 重设装每个单词的数据结构
            } else if (ch != ' ') {
                sb.append(ch);
            }
            left++;
        }

        deque.offerFirst(sb.toString()); // 最后一个单词

        return String.join(" ", deque);
    }
}
```

## Solution 3
> 先把整个字符串翻转，然后把每个单词翻转
```java

```