### 方法1 - 找任意一个字符串作为HashMap的key，O(NKlogK)
```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        result = collections.defaultdict(list)
        for str in strs:
            # 这里sorted方法使用了Time Sort，根据数据采用归并排序或者插入排序，可以看作是KlogK
            result[tuple(sorted(str))].append(str) 
        return result.values()
```

### 方法2 - 创建一个HashMap的独特的 key，这里使用元组（简单相加或者相乘容易冲突），也可以手动加入特殊字符，形成#1#0#2#10#0这样的key，顺序不同所以也不会产生有冲突的key，O(NK)
```python
class Solution:
    def groupAnagrams(self, strs: List[str]) -> List[List[str]]:
        result = collections.defaultdict(list)
        for str in strs:
            count = [0] * 26
            for c in str:
                count[ord(c) - ord('a')] += 1
            result[tuple(count)].append(str) ## 使用元组来创建特殊的key，因为顺序不同，保证key不冲突
        return result.values()
```
