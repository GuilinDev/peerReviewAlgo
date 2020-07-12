package main.java.ningSL;

import java.util.*;

class WordLadder {
    public static void main(String[] args) {
        WordLadder wl = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> ls = new ArrayList<>();
        ls.add("hot");
        ls.add("dot");
        ls.add("dog");
        ls.add("lot");
        ls.add("log");
        ls.add("cog");
       System.out.println(wl.ladderLength(beginWord,endWord,ls));

    }
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visted = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visted.add(beginWord);
        int level = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i< size; i++){
                String str = queue.poll();
                if(str.equals(endWord)) return level;
                for (int k = 0; k < str.length(); k ++){
                    char[] chars = str.toCharArray();
                    for (char c = 'a'; c <= 'z'; c++){
                        chars[k] = c;
                        String cur = new String(chars);
                        if(wordSet.contains(cur)){
                            queue.offer(cur);
                            visted.add(cur);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
