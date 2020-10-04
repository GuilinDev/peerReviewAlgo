```java
//================================Trie=======================================
public class Trie {
    private boolean isEnd;
    private Trie[] next;
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
                curr.next[n] = new Trie();
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

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i ++) {
            node = node.next[words[i] - 'a'];
            if(node == null) return null;
        }
        return node;
    }
}
//================================Union Find=======================================
public class UnionFind {
        public static int count = 0;
        private int[] parent;
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int p) {
            while (p != parent[p]) {
                parent[p] = parent[parent[p]];
                p = parent[p];
            }
            return p;
        }
        public void union(int p, int q) {
            int rootP = find(p);
            int rootQ = find(q);
            if (rootP == rootQ) return;
            parent[rootP] = rootQ;
            count--;
        }
}






```
 