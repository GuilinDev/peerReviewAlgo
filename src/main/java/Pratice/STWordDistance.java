package Pratice;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class STWordDistance {
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return 0;
        }
        int index1 = -1, index2 = -1;
        int distance = Integer.MAX_VALUE;
        for (int i = 0; i < words.length; i++) {
            if (words[i].equals(word1)) {
                index1 = i;
            }
            if (words[i].equals(word2)) {
                index2 = i;
            }
            if (index1 != -1 && index2 != -1) {
                distance = Math.min(distance, Math.abs(index1 - index2));
            }
        }
        return distance;
    }

        private Map<String, List<Integer>> map;

        public STWordDistance(String[] words) {
            // 构造器中先把单词（可能有重复）的各自下标进行预处理
            map = new HashMap<String, List<Integer>>();
            for(int i = 0; i < words.length; i++) {
                String word = words[i];
                if(map.containsKey(word)) {
                    map.get(word).add(i);
                } else {
                    List<Integer> list = new ArrayList<Integer>();
                    list.add(i);
                    map.put(word, list);
                }
            }
        }

        public int shortest(String word1, String word2) {
            // 对比两个list之间各元素的最小差值
            List<Integer> list1 = map.get(word1);
            List<Integer> list2 = map.get(word2);
            int distance = Integer.MAX_VALUE;
            for(int i = 0, j = 0; i < list1.size() && j < list2.size(); ) {
                int index1 = list1.get(i), index2 = list2.get(j);
                //distance = Math.min(distance, Math.abs(index1 - index2));
                if(index1 < index2) {
                    distance = Math.min(distance, index2 - index1);
                    i++;
                } else {
                    distance = Math.min(distance, index1 - index2);
                    j++;
                }
            }
            return distance;
        }

}
