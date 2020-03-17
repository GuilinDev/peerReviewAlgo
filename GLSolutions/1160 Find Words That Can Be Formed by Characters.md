## Solution 1
> 直接检查

```java
    class Solution {
        public int countCharacters(String[] words, String chars) {
            int result = 0;
            if (words == null || words.length == 0 || chars.isEmpty()) {
                return result;
            }
            for (int i = 0; i < words.length; i++) {
                if (check(words[i], chars)) {
                    result += words[i].length();
                }
            }
            return result;
        }
        private boolean check(String word, String chs) {
            int[] arr = new int[26];//default 0
            for (int i = 0; i < chs.length(); i++) {
                arr[chs.charAt(i) - 'a']++;
            }
            for (int i = 0; i < word.length(); i++) {
                arr[word.charAt(i) - 'a']--;
            }
            for (int num : arr) {
                if (num < 0) {
                    return false;
                }
            }
            return true;
        }
    }
```