## 题目
Implement a method to perform basic string compression using the counts of repeated characters. For example, the string aabcccccaaa would become a2blc5a3. If the "compressed" string would not become smaller than the original string, your method should return the original string. You can assume the string has only uppercase and lowercase letters (a - z).

Example 1:

Input: "aabcccccaaa"


Output: "a2b1c5a3"


Example 2:

Input: "abbccd"


Output: "abbccd"


Explanation: 


The compressed string is "a1b2c2d1", which is longer than the original string.
 

Note:

0 <= S.length <= 50000

## Solution 1

```java
    class Solution {
    public String compressString(String S) {
        if (S.isEmpty()) {
            return S;
        }
        char[] arrS = S.toCharArray();
        int len = arrS.length;
        StringBuilder str = new StringBuilder();
        int index = 1;
        int count = 1;
        for (int i = 1; i < len; i++) {
            if (arrS[i] == arrS[i - 1]) {
                count++;
            } else {
                str.append(arrS[i - 1] + "" + count);
                count = 1;
            }
        }
        //for循环在最后一个字符处只增加count而不会append，所以这里要加上（i=len的时候跳出循环，但arrS[len - 1]这时候还没加上）
        str.append(arrS[len - 1] + "" + count);
        return str.length() < S.length() ? str.toString() : S;
    }
}
```