## 

```
# Python  
class Trie(object): 
   
	def __init__(self):  
		self.root = {}  
		self.end_of_word = "#"  
  
	def insert(self, word):  
		node = self.root  
		for char in word:  
			node = node.setdefault(char, {})  
		node[self.end_of_word] = self.end_of_word  
  
	def search(self, word):  
		node = self.root  
		for char in word:  
			if char not in node:  
				return False  
			node = node[char]  
		return self.end_of_word in node  
  
	def startsWith(self, prefix):  
		node = self.root  
		for char in prefix:  
			if char not in node:  
				return False  
			node = node[char]  
		return True 
```
```c++
//C/C++ 
class Trie { 
    struct TrieNode { 
        map<char, TrieNode*>child_table; 
        int end; 
        TrieNode(): end(0) {} 
    }; 
         
public: 
    /** Initialize your data structure here. */ 
    Trie() { 
        root = new TrieNode(); 
    } 
     
    /** Inserts a word into the trie. */ 
    void insert(string word) { 
        TrieNode *curr = root; 
        for (int i = 0; i < word.size(); i++) { 
            if (curr->child_table.count(word[i]) == 0) 
                curr->child_table[word[i]] = new TrieNode(); 
                 
            curr = curr->child_table[word[i]];                 
        } 
        curr->end = 1; 
    } 
     
    /** Returns if the word is in the trie. */ 
    bool search(string word) { 
        return find(word, 1); 
    } 
     
    /** Returns if there is any word in the trie that starts with the given prefix. */ 
    bool startsWith(string prefix) { 
        return find(prefix, 0); 
    } 
private: 
    TrieNode* root; 
    bool find(string s, int exact_match) { 
        TrieNode *curr = root; 
        for (int i = 0; i < s.size(); i++) { 
            if (curr->child_table.count(s[i]) == 0) 
                return false; 
            else 
                curr = curr->child_table[s[i]]; 
        } 
         
        if (exact_match) 
            return (curr->end) ? true : false; 
        else 
            return true; 
    } 
}; 
```
```java
//Java 
class Trie { 
    private boolean isEnd; 
    private Trie[] next; 
    /** Initialize your data structure here. */ 
    public Trie() { 
        isEnd = false; 
        next = new Trie[26]; 
    } 
     
    /** Inserts a word into the trie. */ 
    public void insert(String word) { 
        if (word == null || word.length() == 0) return; 
        Trie curr = this; 
        char[] words = word.toCharArray(); 
        for (int i = 0;i < words.length;i++) { 
            int n = words[i] - 'a'; 
            if (curr.next[n] == null) curr.next[n] = new Trie(); 
            curr = curr.next[n]; 
        } 
        curr.isEnd = true; 
    } 
     
    /** Returns if the word is in the trie. */ 
    public boolean search(String word) { 
        Trie node = searchPrefix(word); 
        return node != null && node.isEnd; 
    } 
     
    /** Returns if there is any word in the trie that starts with the given prefix. */ 
    public boolean startsWith(String prefix) { 
        Trie node = searchPrefix(prefix); 
        return node != null; 
    } 
    private Trie searchPrefix(String word) { 
        Trie node = this; 
        char[] words = word.toCharArray(); 
        for (int i = 0;i < words.length;i++) { 
            node = node.next[words[i] - 'a']; 
            if (node == null) return null; 
        } 
        return node; 
    } 
} 
```

```javascript
// JavaScript 
class Trie { 
  constructor() { 
    this.root = {}; 
    this.endOfWord = "$"; 
  } 
  insert(word) { 
    let node = this.root; 
    for (let ch of word) { 
      node[ch] = node[ch] || {}; 
      node = node[ch]; 
    } 
    node[this.endOfWord] = this.endOfWord; 
  } 
  search(word) { 
    let node = this.root; 
    for (let ch of word) { 
      if (!node[ch]) return false; 
      node = node[ch]; 
    } 
    return node[this.endOfWord] === this.endOfWord; 
  } 
  startsWith(word) { 
    let node = this.root; 
    for (let ch of word) { 
      if (!node[ch]) return false; 
      node = node[ch]; 
    } 
    return true; 
  } 
} 

let trie = new Trie(); 
console.log(trie.insert("apple")); 
console.log(trie.search("apple")); // 返回 true 
console.log(trie.search("app")); // 返回 false 
console.log(trie.startsWith("app")); // 返回 true 
console.log(trie.insert("app")); 
console.log(trie.search("app")); // 返回 true 
```
