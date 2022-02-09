package Pratice;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class Trie {
    public boolean isEnd;
    public Trie[] next;
    public Trie() {
        isEnd = false;
        next = new Trie[26];

    }

    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i ++) {
            int n = words[i] - 'a';
            if(curr.next[n] == null) {
                curr.next[n] = new Trie(); // not new Trie[26];
            }
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node!=null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    public Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i ++) {
            node = node.next[words[i] - 'a'];
            if(node == null) return null;
        }
        return node;
    }
}

public class Auto_CompleteOne {
    Trie root;
    public Auto_CompleteOne(List<String> words){
        this.root = new Trie();
        for (String word: words) {
            root.insert(word);
        }
    }
    public List<String> find(String prefix){
        List<String> res = new ArrayList<>();
        Trie cur = root;
        Trie pRoot = cur.searchPrefix(prefix);
        helper(res, pRoot,prefix);
        return res;
    }
    public void helper(List<String> res, Trie pRoot, String cur){
        if (pRoot == null){
            return;
        }
        if (pRoot.isEnd){
            res.add(cur);
        }
        for (int i = 0; i < 26; i++){
            char c = (char)('a' + i);
            helper(res, pRoot.next[i], cur + c);
        }
    }
    public static void main(String[] args) {
        List<String> words = new ArrayList<>();
        words.add("ab");
        words.add("a");
        words.add("de");
        words.add("abde");
        words.add("cdf");
        words.add("cde");
        words.add("cdk");
        words.add("cdbb");

        Auto_CompleteOne test = new Auto_CompleteOne(words);
        String prefix = "c";
        List<String> res = test.find(prefix);
        System.out.println(res);


    }

    class Solution {
        public int solve(int[][] graph, int target) {
            if (graph[target].length == 0) {
                return -1;
            }

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[graph.length];
            queue.offer(target);

            int res = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int i=0; i<size; i++) {
                    int v = queue.poll();
                    for (int cur : graph[v]) {
                        if (!visited[cur]) {
                            if (cur == target) {
                                return res + 1;
                            }
                            visited[cur] = true;
                            queue.offer(cur);
                        }
                    }
                }
                res++;
            }

            return -1;
        }
    }

}
