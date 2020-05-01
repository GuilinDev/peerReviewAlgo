## Solution 1
> 滑动窗口的简单实现

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        int result = 0, left = 0, right = 0;
        if (s.isEmpty()) {
            return result;
        }
        HashSet<Character> ch = new HashSet<>();
        while (right < s.length()) {
            if (ch.add(s.charAt(right))) {
                right++;
                result = Math.max(result, ch.size()); //ch.size() equal to right - left
            } else {
                ch.remove(s.charAt(left));
                left++;
            }
        }
        return result;
    }
}
```