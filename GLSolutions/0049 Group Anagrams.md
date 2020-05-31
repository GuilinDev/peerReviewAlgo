## Solution 1
> 判断每个字符串是否是异位词，用同一个hashmap的key,O(NKlogK)
```java
class Solution {
    /**
    Java解法1，直观的解法，判断每个字符串是否是异位词，时间复杂度O(NKlogK)，空间复杂度O(NK)
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List> map = new HashMap<>();
        for (String str : strs) {
            char[] chs = str.toCharArray();
            Arrays.sort(chs); //这里可以用一个K长度的Array或者HashMap来判断是否是异位词，但复杂度一样
            String key = String.valueOf(chs);
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            map.get(key).add(str);
        }
        return new ArrayList(map.values());
    }
}
```

## Solution 1
> 把得到hashmap的key的过程从KlogK优化到K，利用特殊字符，如果直接计算字符对应的整数值然后相加相乘容易“冲突”，所以用个特殊的“哈希function”
```java
class Solution {
    /**
    Java解法2，将HashMap的key用特殊的值来代替，把KlogK优化到K
    */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (strs.length == 0) {
            return new ArrayList();
        }
        Map<String, List> map = new HashMap<>();
        int[] count = new int[26]; // 都是小写字母
        for (String str : strs) {
            Arrays.fill(count, 0); // 每个str重新default
            for (char c : str.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }
            String key = sb.toString(); // 终于拿到unique的key了
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList());
            }
            map.get(key).add(str);
        }
        return new ArrayList(map.values());
    }
}
```