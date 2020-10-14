## Solution 1
> 具体的思路就是使用DP做前缀和，记录相同状态时的最大长度，元音出现的状态数共有2^5 = 32个，即每个元音的奇偶情况。状态压缩中使用异或来翻转特定位。dp[i]表示状态i最早出现的位置。
![状态压缩+DP](./imgs/1371_1.png)
![状态压缩+DP](./imgs/1371_2.png)

```java
class Solution {
    public int findTheLongestSubstring(String s) {
        int result = 0;
        if (s.isEmpty()) {
            return result;
        }
        int len = s.length();
        int[] dp = new int[1 << 5]; //int[] dp = new int[32];
        Arrays.fill(dp, -1);

        int status = 0;

        dp[0] = 0;
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            switch (ch) {
                case 'a':
                    status ^= (1 << 0);
                    break;
                case 'e':
                    status ^= (1 << 1);
                    break;
                case 'i':
                    status ^= (1 << 2);
                    break;
                case 'o':
                    status ^= (1 << 3);
                    break;
                case 'u':
                    status ^= (1 << 4);
                    break;
            }
            if (dp[status] >= 0) {
                result = Math.max(result, i + 1 - dp[status]);
            } else {
                dp[status] = i + 1;
            }
        }
        return result;
    }
}
```

## Solution 2
> 哈希表的做法

cur records the count of "aeiou"
cur & 1 = the records of a % 2
cur & 2 = the records of e % 2
cur & 4 = the records of i % 2
cur & 8 = the records of o % 2
cur & 16 = the records of u % 2
seen note the index of first occurrence of cur

Note that we don't really need the exact count number,
we only need to know if it's odd or even.

If it's one of aeiou,
'aeiou'.find(c) can find the index of vowel,
cur ^= 1 << 'aeiou'.find(c) will toggle the count of vowel.

But for no vowel characters,
'aeiou'.find(c) will return -1,
that's reason that we do 1 << ('aeiou'.find(c) + 1) >> 1.

```java
class Solution {
    public int findTheLongestSubstring(String s) {
        int res = 0 , cur = 0, n = s.length();
        HashMap<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);
        for (int i = 0; i < n; ++i) {
            cur ^= 1 << ("aeiou".indexOf(s.charAt(i)) + 1 ) >> 1;
            seen.putIfAbsent(cur, i);
            res = Math.max(res, i - seen.get(cur));
        }
        return res;
    }
}
```

也可以使用5位来表示元音出现次数的奇偶性。例如，我们可以将0/1用于偶数/奇数，那么如果我们有4a，3e，2i，1o，0u，则表示形式将为01010。当我们扫描数组时，可以更新O(1)通过使用XOR操作进行计时，然后将索引存储在每个不同表示形式首次出现的位置。当我们遇到一个表示形式时，再次在索引j处说01010，我们可以回头看01010首次出现的索引i，我们知道从i到j的子字符串必须是有效字符串，并且它是最长的有效子字符串，结束于j。

```python
class Solution:
    # "Time: O(n), Space: O(number of unique vowel count sequences)"
    def findTheLongestSubstring(self, s: str) -> int:
        vowels = {'a': 1, 'e': 2, 'i': 4, 'o': 8, 'u': 16}
        # "d: last_seen_index_for_vowel_sequence_count_map"
        d, bit_represenation_for_vowel_count, result = {0: -1}, 0, 0
        for i, c in enumerate(s):
            if c in vowels:
                # "n would be 0 if count is even"
                bit_represenation_for_vowel_count ^= vowels[c]
            # "any combination of vowels that gives odd count & have not been seen before"
            if bit_represenation_for_vowel_count not in d:  
                # "stores the oldest index for which a particular combination of vowels resulted into odd count"
                d[bit_represenation_for_vowel_count] = i
            else:
                # "if a combination is seen again, result = current_index - last_seen_index_for_this_combination (d[n])"
                # "example: s = "aepqraeae" (odd vowel count for s[:2] is seen again at s[0:]"
                # "not considering character at last seen index will make count even"
                result = max(result, i - d[bit_represenation_for_vowel_count])
        return result
        
```
