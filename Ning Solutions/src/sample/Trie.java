package sample;

import java.util.HashMap;
import java.util.Map;

public class Trie {
    public static void main(String[] args) {
        Trie tre = new Trie();
        tre.insert("abcd");
        System.out.println(tre.search("abd"));
        System.out.println(tre.startsWith("abc"));
    }
        TrieNode head;
        /** Initialize your data structure here. */
        public Trie() {
            head = new TrieNode();
        }

        /** Inserts a word into the trie. */
        public void insert(String word) {
            if(word == null)
                return;
            TrieNode node = head;
            for(char ch : word.toCharArray()) {
                if(!node.charToNode.containsKey(ch)){
                    node.charToNode.put(ch, new TrieNode());
                }
                node = node.charToNode.get(ch);
            }
            node.isEnd = true;
        }

        /** Returns if the word is in the trie. */
        public boolean search(String word) {
            if(word == null)
                return false;
            TrieNode node = head;
            for(char ch : word.toCharArray()) {
                if(!node.charToNode.containsKey(ch)){
                    return false;
                }
                node = node.charToNode.get(ch);

            }
            return node.isEnd;

        }

        /** Returns if there is any word in the trie that starts with the given prefix. */
        public boolean startsWith(String prefix) {
            if(prefix == null)
                return false;
            TrieNode node = head;
            for(char ch : prefix.toCharArray()) {
                if(!node.charToNode.containsKey(ch)){
                    return false;
                }
                node = node.charToNode.get(ch);

            }
            return true;

        }

        class TrieNode{
            Map<Character, TrieNode> charToNode;
            boolean isEnd = false;
            public TrieNode(){
                charToNode = new HashMap();
            }
        }

}
