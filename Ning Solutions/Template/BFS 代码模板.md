```
# Python 
def BFS(graph, start, end): 
    visited = set() 
	queue = []  
	queue.append([start])  
	while queue:  
		node = queue.pop()  
		visited.add(node) 
		process(node)  
		nodes = generate_related_nodes(node)  
		queue.push(nodes) 
	# other processing work  
	... 
```
```java
//Java 
public class TreeNode { 
    int val; 
    TreeNode left; 
    TreeNode right; 
    TreeNode(int x) { 
        val = x; 
    } 
} 
class Solution {
public List<List<Integer>> levelOrder(TreeNode root) { 
    List<List<Integer>> allResults = new ArrayList<>(); 
    if (root == null) { 
        return allResults; 
    } 
    Queue<TreeNode> nodes = new LinkedList<>(); 
    nodes.add(root); 
    while (!nodes.isEmpty()) { 
        int size = nodes.size(); 
        List<Integer> results = new ArrayList<>(); 
        for (int i = 0; i < size; i++) { 
            TreeNode node = nodes.poll(); 
            results.add(node.val); 
            if (node.left != null) { 
                nodes.add(node.left); 
            } 
            if (node.right != null) { 
                nodes.add(node.right); 
            } 
        } 
        allResults.add(results); 
    } 
    return allResults; 
} 
}
```
```c++
// C/C++ 
void bfs(Node* root) { 
  map<int, int> visited; 
  if(!root) return ; 
  queue<Node*> queueNode; 
  queueNode.push(root); 
  while (!queueNode.empty()) { 
    Node* node = queueNode.top(); 
    queueNode.pop(); 
    if (visited.count(node->val)) continue; 
    visited[node->val] = 1; 
    for (int i = 0; i < node->children.size(); ++i) { 
        queueNode.push(node->children[i]); 
    } 
  } 
  return ; 
} 
```
```javascript
//JavaScript 
const bfs = (root) => { 
  let result = [], queue = [root] 
  while (queue.length > 0) { 
    let level = [], n = queue.length 
    for (let i = 0; i < n; i++) { 
      let node = queue.pop() 
      level.push(node.val)  
      if (node.left) queue.unshift(node.left) 
      if (node.right) queue.unshift(node.right) 
    } 
    result.push(level) 
  } 
  return result 
}; 
```
