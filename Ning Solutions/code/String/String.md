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

// 709	To Lower Case
class Solution {
    public String toLowerCase(String str) {
        if (str == null || str.length() == 0) return "";
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i ++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z'){
                int loc = chars[i];
                chars[i] = (char)(loc + 32);
            //    chars[i] = (char)(chars[i] - 'A' + 'a');
            //  charStr[i] += 'a' - 'A';    
            }
        }
        return new String(chars);
    }
}

// 58. Length of Last Word
class Solution {
    public int lengthOfLastWord(String s) {
        if (s==null || s.length() == 0) return 0;
        String s1 = s.trim(); // 要去头尾
       String[] charArray = s1.split(" ");
        return charArray[charArray.length - 1].length();
        
    }
}

// 771. Jewels and Stones
class Solution {
    public int numJewelsInStones(String J, String S) {
        Set<Character> set = new HashSet<Character>();
        for (char c : J.toCharArray()){
            set.add(c);
        }
        int count = 0;
        for (int i = 0; i < S.length(); i ++) {
            if(set.contains(S.charAt(i))){
                count++;
            }
        }
        return count;
    }
}

// 387. First Unique Character in a String
class Solution {
    public int firstUniqChar(String s) {
        if (s == null || s.length() == 0) return -1;
        int[] table = new int[26];
       for (char c : s.toCharArray()){
            table[c - 'a']++; // c - 'a' and i - 'a';
          }
        for (int i = 0; i < s.length(); i ++) { // 第二次是用字符窜扫描表
            if(table[s.charAt(i) - 'a'] == 1){ // table[s.charAt(i) - 'a'] not table[i] or table[s.charAt[i] - 'a']
               return i;
            }
        }
        return -1;
    }
}

//14. Longest Common Prefix

class Solution {
public String longestCommonPrefix(String[] strs) {
   if (strs.length == 0) return "";
   String prefix = strs[0];
   for (int i = 1; i < strs.length; i++)
       while (strs[i].indexOf(prefix) != 0) { // String.indexOf
           prefix = prefix.substring(0, prefix.length() - 1);
           if (prefix.isEmpty()) return "";
       }        
   return prefix;
    }
}

// 344. Reverse String
class Solution {
    public void reverseString(char[] s) {
        if (s == null || s.length == 0) return;
        int left = 0; 
        int right = s.length - 1;
        while(left <= right){ 
            char temp = s[left];
            s[left] = s[right];
            s[right] = temp;
            left ++;
            right --;
        }  
    }
}

// 	541	Reverse String II   
class Solution {
     public String reverseStr(String s, int k) {
        char[] a = s.toCharArray();
        for (int start = 0; start < a.length; start += 2 * k) {
            int i = start, j = Math.min(start + k - 1, a.length - 1); // need think about case of k > s.length();
            while (i < j) {
                char tmp = a[i];
                a[i++] = a[j];
                a[j--] = tmp;
            }
        }
        return new String(a);
    }
}

//151. Reverse Words in a String

class Solution {
    public String reverseWords(String s) {
        if (s == null || s.length() == 0) return "";
        String[] sArray = s.trim().split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = sArray.length - 1; i >= 0; i --) {
            String l = sArray[i].trim(); 
            if (i != 0 && !l.isEmpty()){ // need to add string is not empty. since " ".trim()= "";
                sb.append(l).append(" ");
            } else {
                sb.append(l);
            }
        }
        
        return sb.toString();
    }
}
public class Solution {
    public String reverseWords(String s) {
        String[] words = s.trim().split(" +");//用空格符来分隔每一个单词，而不是用正则
        Collections.reverse(Arrays.asList(words));
        return String.join(" ", words);
    }
}

// 917. Reverse Only Letters
class Solution {
     public String reverseOnlyLetters(String S) {
        Stack<Character> letters = new Stack();
        for (char c: S.toCharArray())
            if (Character.isLetter(c))
                letters.push(c);

        StringBuilder ans = new StringBuilder();
        for (char c: S.toCharArray()) {
            if (Character.isLetter(c))
                ans.append(letters.pop());
            else
                ans.append(c);
        }

        return ans.toString();
    }
}

//557. Reverse Words in a String III

class Solution {
    public String reverseWords(String s) {
       if (s == null || s.length() == 0) return "";
       String[] sArray = s.trim().split(" ");
       StringBuilder sb = new StringBuilder();
       for (int i = 0; i < sArray.length; i ++) {
           if ( i != sArray.length - 1) { // last one need to be care.
               sb.append(reverse(sArray[i])).append(" ");
           } else {
               sb.append(reverse(sArray[i]));
           }
       } 
        return sb.toString();
    }
    
    public String reverse(String s) {
            int left = 0;
            int right = s.length() - 1;
            char[] c = s.toCharArray();
            while(left <= right){
                char temp = c[left];
                c[left ++] = c[right];
                c[right --] = temp;
            }
            return new String(c);
    }
}

// 	5	 Longest Palindromic Substring    
class Solution {
    public String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return "";
        int left = 0; int right = 0;
        for (int i = 0; i < s.length(); i ++) {
            int len1 = extendPalindrome(s, i, i);
            int len2 = extendPalindrome(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > right - left){ // don't forget this;
                  left = i - (len - 1)/2;
                 right = i + len/2;
            }
        }
        return s.substring(left, right + 1);   
    }
    
    public int extendPalindrome(String s, int left, int right) {
        int L = left; int R = right;
        while (L >=0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R-L-1;
    }
}

// 125. Valid Palindrome
class Solution {
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;
        while (left < right) {
            // 双指针嵌套WHILE的写法
            while(left < right && !Character.isLetterOrDigit(s.charAt(left))) left ++;
            while(left < right && ! Character.isLetterOrDigit(s.charAt(right))) right --;
            if(Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
            left ++;
            right--;
        }
       return true;
    }
}


// 680. Valid Palindrome II
class Solution {
   public boolean validPalindrome(String s) {
    for(int i = 0, j = s.length()-1; i < j ; i++, j--){ // the way to write i and j
        if(s.charAt(i) != s.charAt(j)){
            //分两种情况，一是右边减一，二是左边加一
            return isPalindrome(s,i,j - 1) || isPalindrome(s, i + 1, j);
        }
    }
    return true;
}
//使用双指针
//1、如果左右指针的值相等，则移动左右指针
//2、如果左右指针的值不相等，则在不相等的区间内去掉左指针的值或者去掉右指针的值再判断是否是回文数

public boolean isPalindrome(String s, int i, int j) {
    while (i < j) {
        if (s.charAt(i++) != s.charAt(j--)) {
            return false;
        }
    }
    return true;
 }
}
}

// 3. Longest Substring Without Repeating Characters
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> map = new HashMap<>();
      
        int left = 0, right = 0; int count = 0;
        int max = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            map.put(c, map.getOrDefault(c,0) + 1);
            if (map.get(c) > 1){
                count ++;
            }   
            right ++;
            while (count > 0) {
                char l = s.charAt(left);
                if(map.get(l) > 1) count --;
                map.put(l, map.get(l) - 1);
                left ++;
            }
            max = Math.max(max, right - left); //not right - left + 1;   
        }
        return max;
        }
}

class Solution {
    public int lengthOfLongestSubstring(String s) {
       if (s == null || s.length() == 0) return 0;
       char[] chars = s.toCharArray();
       HashMap<Character, Integer> map = new HashMap<>(); 
       int maxLen = 0; 
       for (int right = 0, left = 0; right < s.length(); right++) {
           char c = s.charAt(right);
           if(map.containsKey(c)){
               left = Math.max(left, map.get(c) + 1);
           //    maxLen = Math.max(maxLen, right - left + 1);
           } 
           map.put(c,right); // not right + 1; it store exactly that char location
           maxLen = Math.max(maxLen, right - left + 1);// don't miss this step and need put outside
       }
       return maxLen;   
    }
}
//用一个HashMap来记录窗口内的字符和这些字符最后出现的位置，如果窗口右侧移动后发现有重复的字符，
//那就将left索引指向HashMap里面保存的该字符的位置的下一位，窗口右侧继续移动，同时保持len的最长的值；

//20. Valid Parentheses
class Solution {
    public boolean isValid(String s) {
        if(s.isEmpty()) return true;
        Stack<Character> st = new Stack<>();
        for (char c : s.toCharArray()){
            if(c == '(' || c == '[' || c == '{') {
                st.push(c);
            }
            if (st.isEmpty()) return false; // need check empty before check right side parentheses
            if((c == ')' && st.pop()!= '(') ||
                (c == ']' && st.pop()!= '[') ||
               (c == '}' && st.pop()!= '{')){
                return false;
            }  
        }
        if (!st.isEmpty()) { // check still have left side parentheses
           return false; 
        }
        return true;
    }
}

```
