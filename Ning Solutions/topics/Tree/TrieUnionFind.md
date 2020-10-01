```java
// 208. Implement Trie (Prefix Tree) 
class Trie {
    TrieNode root;
    class TrieNode{
        boolean isEnd = false;
        Map<Character, TrieNode> nextNode;
        TrieNode(){
            nextNode = new HashMap<>();
        }
    }
    /** Initialize your data structure here. */
    public Trie() {
        root = new TrieNode();
    }   
    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        if (word == null || word.trim().isEmpty()) return;
        for (char c: word.toCharArray()){
            if (!cur.nextNode.containsKey(c)){
                cur.nextNode.put(c, new TrieNode());
            }
            cur = cur.nextNode.get(c);
        }
        cur.isEnd = true;     
    }    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
         TrieNode cur = root;
        if (word == null || word.trim().isEmpty()) return false;
        for (char c: word.toCharArray()){
            if (!cur.nextNode.containsKey(c)){
                return false;
            }
            cur = cur.nextNode.get(c);
        }
        return cur.isEnd;  
    } 
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
          TrieNode cur = root;
        if (prefix == null || prefix.trim().isEmpty()) return false;
        for (char c: prefix.toCharArray()){
            if (!cur.nextNode.containsKey(c)){
                return false;
            }
            cur = cur.nextNode.get(c);
        }
        return true;       
    }


// 212 Word Search II
 public List<String> findWords(char[][] board, String[] words) {
        //构建字典树
        wordTrie myTrie=new wordTrie();
        trieNode root= myTrie.root;
        for(String s:words)
            myTrie.insert(s);
        //使用set防止重复
        Set<String> result =new HashSet<>();
        int m=board.length;
        int n=board[0].length;
        boolean [][]visited=new boolean[m][n];
        //遍历整个二维数组
        for(int i=0;i<board.length; i++){
            for (int j = 0; j < board [0].length; j++){
                find(board,visited,i,j,m,n,result,root);
            }
        }
        System.out.print(result);
        return new LinkedList<String>(result);
    }
    private void find(char [] [] board, boolean [][]visited,int i,int j,int m,int n,Set<String> result,trieNode cur){
        //边界以及是否已经访问判断
        if(i<0||i>=m||j<0||j>=n||visited[i][j])
            return;
        cur=cur.child[board[i][j]-'a'];
        visited[i][j]=true;
        if(cur==null)
        {
            //如果单词不匹配，回退
            visited[i][j]=false;
            return;
        }
        //找到单词加入
        if(cur.isLeaf)
        {
            result.add(cur.val);
            //找到单词后不能回退，因为可能是“ad” “addd”这样的单词得继续回溯
//            visited[i][j]=false;
//            return;
        }
        find(board,visited,i+1,j,m,n,result,cur);
        find(board,visited,i,j+1,m,n,result,cur);
        find(board,visited,i,j-1,m,n,result,cur);
        find(board,visited,i-1,j,m,n,result,cur);
        //最后要回退，因为下一个起点可能会用到上一个起点的字符
        visited[i][j]=false;
    }


}

//字典树
class wordTrie{
    public trieNode root=new trieNode();
    public void insert(String s){
        trieNode cur=root;
        for(char c:s.toCharArray()){
            if(cur.child[c-'a']==null){ // like containsKey
                cur.child [c-'a'] = new trieNode();
            }
            cur=cur.child [c-'a'];
        }
        cur.isLeaf=true;
        cur.val=s;
    }
}
//字典树结点
class trieNode{
    public String val;
    public trieNode[] child=new trieNode[26];
    public boolean isLeaf=false;
    trieNode(){
    }
// https://leetcode-cn.com/problems/word-search-ii/solution/java-zi-dian-shu-hui-su-ji-bai-liao-9969-de-yong-h/

}
// 并查集模板
class UnionFind { 
	private int count = 0; 
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
			parent[p] = parent[parent[p]]; // 不能合并下一步 因为要先更新数组
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
