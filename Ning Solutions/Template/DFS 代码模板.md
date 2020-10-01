## 递归写法 

```python
#Python 
visited = set()  
def dfs(node, visited): 
    if node in visited: # terminator 
    	# already visited  
    	return  
	visited.add(node)  
	# process current node here.  
	... 
	for next_node in node.children():  
		if next_node not in visited:  
			dfs(next_node, visited) 
```

```c++
//C/C++ 
//递归写法： 
map<int, int> visited; 
void dfs(Node* root) { 
  // terminator 
  if (!root) return ; 
  if (visited.count(root->val)) { 
    // already visited 
    return ; 
  } 
  visited[root->val] = 1; 
  // process current node here.  
  // ... 
  for (int i = 0; i < root->children.size(); ++i) { 
    dfs(root->children[i]); 
  } 
  return ; 
} 
```

```java
//Java 
class Solution {
    public List<List<Integer>> levelOrder(TreeNode root) { 
        List<List<Integer>> allResults = new ArrayList<>(); 
        if(root==null){ 
            return allResults; 
        } 
        travel(root,0,allResults); 
        return allResults; 
    } 

    private void travel(TreeNode root,int level,List<List<Integer>> results){ 
        if(results.size()==level){ 
            results.add(new ArrayList<>()); 
        } 
        results.get(level).add(root.val); 
        if(root.left!=null){ 
            travel(root.left,level+1,results); 
        } 
        if(root.right!=null){ 
            travel(root.right,level+1,results); 
        } 
    } 
}
```

```javascript
//JavaScript 
const visited = new Set() 
const dfs = node => { 
  if (visited.has(node)) return 
  visited.add(node) 
  dfs(node.left) 
  dfs(node.right) 
} 
```
## 非递归写法 

```python
#Python 
def DFS(self, tree):  
	if tree.root is None:  
		return []  
	visited, stack = [], [tree.root] 
	while stack:  
		node = stack.pop()  
		visited.add(node) 
		process (node)  
		nodes = generate_related_nodes(node)  
		stack.push(nodes)  
	# other processing work  
	... 
```

```c++
//C/C++ 
//非递归写法： 
void dfs(Node* root) { 
  map<int, int> visited; 
  if(!root) return ; 
  stack<Node*> stackNode; 
  stackNode.push(root); 
  while (!stackNode.empty()) { 
    Node* node = stackNode.top(); 
    stackNode.pop(); 
    if (visited.count(node->val)) continue; 
    visited[node->val] = 1; 

    for (int i = node->children.size() - 1; i >= 0; --i) { 
        stackNode.push(node->children[i]); 
    } 
  } 
  return ; 
} 
```
