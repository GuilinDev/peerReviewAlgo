```java
class SlidingWindow {
//3. Longest Substring Without Repeating Characters
class Solution {
    public int lengthOfLongestSubstring(String s) {
       if (s == null || s.length() == 0) return 0;
       HashMap<Character, Integer> window = new HashMap<>(); 
       int left = 0; int right = 0; int maxLen = 0;
       while(right < s.length()){
           char s1 = s.charAt(right);
           right ++;
           window.put(s1, window.getOrDefault(s1, 0) + 1);
           while (window.get(s1) > 1){
               char s2 = s.charAt(left);
               left ++;
               window.put(s2, window.getOrDefault(s2,0) - 1);
           }
          maxLen = Math.max(maxLen, right - left); 
       }  
       return maxLen;   
    }
}
//76. Minimum Window Substring
class Solution {
    public String minWindow(String s, String t) {
        if (s == null || t == null || s.length() < t.length()) {
            return "";
        }
        HashMap<Character, Integer> window = new HashMap<>(); 
        HashMap<Character, Integer> need = new HashMap<>();
        
        for (char t1 : t.toCharArray()) {
            need.put(t1, need.getOrDefault(t1,0) + 1);
        }
        int left = 0; int right = 0; int valid = 0;
        int start = 0; int len = Integer.MAX_VALUE; // use one minRight to record
        while (right < s.length()) { // 区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素：
            char s1 = s.charAt(right);
            right ++;
            if (need.containsKey(s1)) {
                window.put(s1,window.getOrDefault(s1,0) + 1);
                if (window.get(s1).equals(need.get(s1))) valid ++;
            }
       // 右指针移动当于在寻找一个「可行解」，然后移动左指针在优化这个「可行解」，最终找到最优解
            while (valid == need.size()) {
              if (right - left < len) {
                    start = left;
                    len = right - left;
                }
                char s2 = s.charAt(left);
                left ++;
                if (need.containsKey(s2)) { // every time need containsKey not contains
                   if (window.get(s2).equals(need.get(s2))) valid --;
                   window.put(s2,window.get(s2) - 1);
                }
              
            }
        }
        if (len == Integer.MAX_VALUE) return ""; // don't miss
        return s.substring(start, start + len);  
    }
}
// 438. Find All Anagrams in a String
class Solution {
    public List<Integer> findAnagrams(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>(); 
        HashMap<Character, Integer> need = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        for (char t1 : t.toCharArray()) {
            need.put(t1, need.getOrDefault(t1,0) + 1);
        }
        int left = 0; int right = 0; int valid = 0;
        while (right < s.length()) { // 区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素：
            char s1 = s.charAt(right);
            right ++;
            if (need.containsKey(s1)) {
                window.put(s1,window.getOrDefault(s1,0) + 1);
                if (window.get(s1).equals(need.get(s1))){
                    valid ++;
                }
            }
            
    // 右指针移动当于在寻找一个「可行解」，然后移动左指针在优化这个「可行解」，最终找到最优解
            while (right - left >= t.length()) {
                 if (valid == need.size()) res.add(left);
                  char s2 = s.charAt(left);
                  left ++;
                  if (need.containsKey(s2)) { // every time need containsKey not contains
                   if (window.get(s2).equals(need.get(s2))){
                      valid --;
                   }
                    window.put(s2,window.get(s2) - 1);
                }
            }
        }
        return res;  
    }
}
// 567. Permutation in String
class Solution {
    public boolean checkInclusion(String t, String s) {

        HashMap<Character, Integer> window = new HashMap<>(); 
        HashMap<Character, Integer> need = new HashMap<>();
        
        for (char t1 : t.toCharArray()) {
            need.put(t1, need.getOrDefault(t1,0) + 1);
        }
        int left = 0; int right = 0; int valid = 0;
        while (right < s.length()) { // 区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素：
            char s1 = s.charAt(right);
            right ++;
            if (need.containsKey(s1)) {
                window.put(s1,window.getOrDefault(s1,0) + 1);
                if (window.get(s1).equals(need.get(s1))){
                    valid ++;
                }
            }
            
    // 右指针移动当于在寻找一个「可行解」，然后移动左指针在优化这个「可行解」，最终找到最优解
            while (right - left >= t.length()) {
                 if (valid == need.size()) return true;
                  char s2 = s.charAt(left);
                  left ++;
                  if (need.containsKey(s2)) { // every time need containsKey not contains
                   if (window.get(s2).equals(need.get(s2))){
                      valid --;
                   }
                    window.put(s2,window.get(s2) - 1);
                }
            }
        }
        return false;
        
    }
}

// 340. Longest Substring with At Most K Distinct Characters
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        if (k < 1 || s == null || s.length() == 0) return 0;
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0; int right = 0; int max = 0;
        while (right < s.length()){
            char r = s.charAt(right);
            right ++;
            window.put(r, window.getOrDefault(r, 0) + 1);
            while (window.size() > k){
                char l = s.charAt(left);
                left ++;
                window.put(l, window.get(l)- 1);
                if(window.get(l) == 0){
                    window.remove(l);
                }
            }
            max = Math.max(max, right - left);
        }
        return max;
    }
}

}








```
