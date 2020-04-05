```java
// 49. Group Anagrams
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
       
        if (strs == null || strs.length == 0) return new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            int[] table = new int[26];
            for (char c : str.toCharArray()){
                table[c - 'a']++; // c - 'a' and i - 'a';
            }
            StringBuilder ckey = new StringBuilder();
            for (int i = 0; i < table.length; i ++){
                if (table[i] != 0){
                    ckey.append(i -'a').append(table[i]); 
                }
            }
            String key = ckey.toString();
            if (map.containsKey(key)){
                map.get(key).add(str);
            } else {
                List<String> value = new ArrayList<>(); 
                value.add(str);
                map.put(key, value);
            }
        }
        return new ArrayList<>(map.values()); // map.values
        
    }

public List<List<String>> groupAnagrams2(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars);
            String key = String.valueOf(chars);
            if (map.containsKey(key)) {
                map.get(key).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(key, list);
            }
        }
        return new ArrayList<List<String>>(map.values());
}
}








```
