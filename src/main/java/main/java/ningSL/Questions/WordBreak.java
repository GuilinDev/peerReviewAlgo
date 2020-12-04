package main.java.ningSL.Questions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak {
    public static void main(String[] args) {
       String  s = "leetcode";
       List<String> ls = new ArrayList<>();
       ls.add("leet");
       ls.add("code");
       System.out.println(wordBreak(s,ls));

    }
        public static boolean wordBreak(String s, List<String> wordDict) {
            boolean[] dp = new boolean[s.length() + 1];
            Set<String> set = new HashSet<>(wordDict);
            dp[0] = true;
            for (int i = 1; i <= s.length(); i++) {
                for (int j = 0; j < i; j++) {
                    if (dp[j] && set.contains(s.substring(j, i))){
                        dp[i] = true;
                        break;
                    }
                }
            }
            return dp[s.length()];
        }
}
