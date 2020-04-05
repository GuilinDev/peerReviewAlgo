```java

/*DFS 代码模板
递归写法
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

非递归写法
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
BFS 代码模板
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
*/


//22. Generate Parentheses
class Solution {
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<String>();
         generateParenthesisHelper(0, 0, n, res,"");
        return res;
        
    }
   public static void generateParenthesisHelper(int left, int right, int n, List<String> res, String s) {
       if (n == 0) return;
        if(left + right == 2*n){
            res.add(s);
            return; // 推荐写上 养成习惯
        }
        if(left < n){
        generateParenthesisHelper(left + 1, right, n, res, s + "("); // current logic is s1 = s+ "("; and pass s1 to next level
        }
        if(right < left){
        generateParenthesisHelper(left, right + 1, n, res, s + ")");// current logic is s2 = s + ")"; and pass s2 to next level
        };
    }
}

//17. Letter Combinations of a Phone Number
class Solution {
     String[] phone = new String[]{" ","","abc","def","ghi", "jkl","mno","pqrs","tuv","wxyz"};
      public List<String> letterCombinations(String digits) {
      List<String> res = new ArrayList<>();
      if (digits.length() != 0) { // not equal 0
          LetterHelper(res,"",digits);
      }  
      return res;    
    } 
    public void LetterHelper(List<String> res, String combine, String digits){
        if (digits.length() == 0){
            res.add(combine);
        } else {
            String letters = phone[Integer.valueOf(digits.substring(0,1))];
            for (int i = 0; i < letters.length(); i ++) {
                String letter = letters.substring(i, i + 1);
                LetterHelper(res, combine + letter, digits.substring(1));
            }
        }
    }
}






```
