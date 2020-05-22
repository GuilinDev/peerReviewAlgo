## Solution 1
> 维护快慢两个索引，利用ASCII创建数组来查找字符是否出现
```java
class Solution {
    public String minWindow(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }
        int countToBeContained = t.length(); // t在s的子字符串中还剩多少个字符没有包含
        int minLen = Integer.MAX_VALUE; // 返回结果
        int startIndex = -1; // 记录s中是否有子字符串包括所有t的字符

        int[] letters = new int[128];
        // t的字符在ASCII数组中的出现**次数**
        for (char ch : t.toCharArray()) {
            letters[ch]++;
        }

        // 维护两个索引，fast负责增加字符，slow负责删掉字符
        int fast = 0;
        int slow = 0;
        while (fast < s.length()) {
            if (letters[s.charAt(fast)] > 0) { // 在s中右边fast指向的字符是在t中出现的字符
                countToBeContained--; // t中待查的字符总数减1
            }
            letters[s.charAt(fast)]--; // ASCII数组中当前字符的出现次数减1，s中出现而t中未出现的字符不可能>=0
            fast++;

            while (countToBeContained == 0) { // 找到一个s中的子字符串，包含所有t中的字符
                if (letters[s.charAt(slow)] == 0) { // slow永远在fast左边，fast之前遍历到而slow现在遍历到：s中出现而t中未出现的字符不可能>=0
                    countToBeContained++; // t中待查的字符总数加1，因为删掉了；不进入此判断的s中的非t中字符直接跳过而countToBeContained不增加
                }
                letters[s.charAt(slow)]++; // ASCII数组中当前slow指向的字符的出现次数加1
                slow++;

                if (fast - slow + 1 < minLen) {
                    startIndex = slow - 1;
                    minLen = fast - slow + 1;
                }
            }
        }
        return startIndex == -1 ? "" : s.substring(startIndex, startIndex + minLen);
    }
}
```