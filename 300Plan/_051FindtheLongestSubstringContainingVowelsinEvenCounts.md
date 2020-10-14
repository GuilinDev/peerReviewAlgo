lc1371
```python
class Solution:
    # 时间复杂度: O(n)
    def findTheLongestSubstring(self, s: str) -> int:
        vowels = {'a': 1, 'e': 2, 'i': 4, 'o': 8, 'u': 16}
        # d: 上一个元音的位置
        d, bit_represenation_for_vowel_count, result = {0: -1}, 0, 0
        for i, c in enumerate(s):
            if c in vowels:
                # 元音都为偶数时，为0
                bit_represenation_for_vowel_count ^= vowels[c]
            # 元音组合为偶数且之前没见过
            if bit_represenation_for_vowel_count not in d:  
                # 存最近一个成为偶数原因的索引
                d[bit_represenation_for_vowel_count] = i
            else:
                # 如果子字符串再次遇到，result = current_index - last_seen_index_for_this_combination (d[n]
                result = max(result, i - d[bit_represenation_for_vowel_count])
        return result
        
```
