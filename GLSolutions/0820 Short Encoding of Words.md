## Solution 1
> 每个单词逐个检查其后缀（从1开始算子字符串的后缀），用一个set来保存单词，如果检查后缀的过程当中发现等于set中的某个单词，就将这个单词从set中删除，最后便利set，加上长度

> 时间复杂度：

> O(∑w i2)

> O(∑w i2)，其中wi是 words[i] 的长度。每个单词有wi个后缀，对于每个后缀，查询其是否在集合中时需要进行 

> O(wi)O(wi) 的哈希值计算。

> 空间复杂度：

> O(∑wi)

> O(∑w i)，存储单词的空间开销

```java
    class Solution {
        public int minimumLengthEncoding(String[] words) {
            HashSet<String> set = new HashSet<>(Arrays.asList(words));
            for (String word : words) {
                for (int k = 1; k < word.length(); k++) {
                    set.remove(word.substring(k)); //逐步检查并移除后缀相同的
                }
            }
            int result = 0;
            for (String word : set) {
                result += word.length() + 1; // 需要加上一个#的长度
            }
            return result;
        }
    }
```

## Solution 2
> 用字典树Trie，建立一个字典树的类，把每个单词顺序倒过来，存入到字典树当中，目标跟方法1一样，保留所有不是其它单词后缀的单词，字典树的叶子节点（没有孩子的节点）就代表没有后缀的单词，统计叶子节点代表的单词长度加一的和即为我们要的答案。

```java

```