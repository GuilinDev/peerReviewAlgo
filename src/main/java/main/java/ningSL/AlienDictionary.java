package main.java.ningSL;

import java.util.*;

public class AlienDictionary {
    public static void main(String[] args) {
        String[] words = new String[]{"wrt",
                "wrf",
                "er",
                "ett",
                "rftt"};
       System.out.println(alienOrder(words));
    }

        public static String alienOrder(String[] words) {

            // Step 0: Create data structures and find all unique letters.
            Map<Character, List<Character>> adjList = new HashMap<>();
            Map<Character, Integer> inDegree = new HashMap<>();
            for (String word : words) {
                for (char c : word.toCharArray()) {
                    inDegree.put(c, 0);
                    adjList.put(c, new ArrayList<>());
                }
            }

            // Step 1: Find all edges.
            for (int i = 0; i < words.length - 1; i++) {
                String word1 = words[i];
                String word2 = words[i + 1];
                // Check that word2 is not a prefix of word1.
                if (word1.length() > word2.length() && word1.startsWith(word2)) {
                    return "";
                }
                // Find the first non match and insert the corresponding relation.
                for (int j = 0; j < Math.min(word1.length(), word2.length()); j++) {
                    if (word1.charAt(j) != word2.charAt(j)) {
                        adjList.get(word1.charAt(j)).add(word2.charAt(j));
                        inDegree.put(word2.charAt(j), inDegree.get(word2.charAt(j)) + 1);
                        break;
                    }
                }
            }

            // Step 2: Breadth-first search.
            StringBuilder sb = new StringBuilder();
            Queue<Character> queue = new LinkedList<>();
            for (Character c : inDegree.keySet()) {
                if (inDegree.get(c).equals(0)) {
                    queue.add(c);
                }
            }
            while (!queue.isEmpty()) {
                Character c = queue.remove();
                sb.append(c);
                for (Character next : adjList.get(c)) {
                    inDegree.put(next, inDegree.get(next) - 1);
                    if (inDegree.get(next).equals(0)) {
                        queue.add(next);
                    }
                }
            }

            if (sb.length() < inDegree.size()) {
                return "";
            }
            return sb.toString();
        }

}
