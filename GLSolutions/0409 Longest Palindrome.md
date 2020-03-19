## Solution 1
> 用hashmap或者int[128]/int[256]来统计个数

```java
    class Solution {
        public int longestPalindrome(String s) {
            int result = 0;
            if (s.isEmpty()) {
                return result;
            }
            HashMap<Character, Integer> map = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char ch = s.charAt(i);
                if (map.containsKey(ch)) {
                    map.put(ch, map.get(ch) + 1);
                } else {
                    map.put(ch, 1);
                }
            }
            for (Map.Entry<Character, Integer> entry : map.entrySet()) {
                result += entry.getValue() / 2 * 2;
                if (entry.getValue() % 2 != 0 && result % 2 == 0) {//这样做检查单数可以让result只加一次，别的时候只用检查
                    result++;
                }
            }
            return result;
        }
    }
```