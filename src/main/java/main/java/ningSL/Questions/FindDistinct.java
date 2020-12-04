package main.java.ningSL.Questions;

import java.util.*;

public class FindDistinct {
    public static List<String> findSubstrings(String str, int k) {
        List<String> res = new ArrayList<>();
        Set<String> resSet = new HashSet<>();
        int left = 0; int right = 0;
        Map<Character, Integer> window = new HashMap<>();
        while (right < str.length()){
            char r = str.charAt(right);
            right ++;
            window.put(r,window.getOrDefault(r,0) + 1);
            while (right - left > k){
                char l = str.charAt(left);
                left++; // don't forget
                window.put(l, window.getOrDefault(l,0) - 1);
                if (window.get(l) == 0){
                    window.remove(l);
                }
            }
            if(right - left == k && window.size() == k - 1){
                resSet.add(str.substring(left,right));
            }
        }
        res.addAll(resSet);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(findSubstrings("wawaglknagagwunagkwkwagl",4));
    }
}
