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
// 计算从起点 start 到终点 target 的最近距离
int BFS(Node start, Node target) {
    Queue<Node> q; // 核心数据结构
    Set<Node> visited; // 避免走回头路
    q.offer(start); // 将起点加入队列
    visited.add(start);
    int step = 0; // 记录扩散的步数
    while (q not empty) {
        int sz = q.size();
    //     将当前队列中的所有节点向四周扩散 
        for (int i = 0; i < sz; i++) {
            Node cur = q.poll();
       //      划重点：这里判断是否到达终点 
            if (cur is target)
                return step;
       //      将 cur 的相邻节点加入队列 
            for (Node x : cur.adj())
                if (x not in visited) {
                    q.offer(x);
                    visited.add(x);
                }
        }
  //       划重点：更新步数在这里 
        step++;
    }
}
*/

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


class Solution {
    //22. Generate Parentheses
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

// 752 Open the Lock
String plusOne(String s, int j) {
    char[] ch = s.toCharArray();
    if (ch[j] == '9')
        ch[j] = '0';
    else
        ch[j] += 1;
    return new String(ch);
}
// 将 s[i] 向下拨动一次
String minusOne(String s, int j) {
    char[] ch = s.toCharArray();
    if (ch[j] == '0')
        ch[j] = '9';
    else
        ch[j] -= 1;
    return new String(ch);
}

int openLock(String[] deadends, String target) { // DFS
    Set<String> deads = new HashSet<>(); // 记录需要跳过的死亡密码
    for (String s : deadends) deads.add(s);
    Set<String> visited = new HashSet<>(); // 记录已经穷举过的密码，防止走回头路
    Queue<String> q = new LinkedList<>();
    int step = 0;  // 从起点开始启动广度优先搜索
    q.offer("0000");
    visited.add("0000"); // set is add not offer
    while (!q.isEmpty()) {
        int sz = q.size();
        for (int i = 0; i < sz; i++) { // //将当前队列中的所有节点向周围扩散
            String cur = q.poll();
            if (deads.contains(cur)) //  判断是否到达终点 // deadset can add here 
                continue;
            if (cur.equals(target))
                return step;
            for (int j = 0; j < 4; j++) {//  将一个节点的未遍历相邻节点加入队列 // not i but j.
                String up = plusOne(cur, j);
                if (!visited.contains(up)) { // // deadset can add here 
                    q.offer(up);
                    visited.add(up);
                }
                String down = minusOne(cur, j);
                if (!visited.contains(down)) {
                    q.offer(down);
                    visited.add(down);
                }
            }
        }
        step++; // 在这里增加步数 
    }
    // 如果穷举完都没找到目标密码，那就是找不到了
    return -1;
}

int openLock(String[] deadends, String target) { // 双向BFS
    Set<String> deads = new HashSet<>();
    for (String s : deadends) deads.add(s);
    Set<String> q1 = new HashSet<>();// 用集合不用队列，可以快速判断元素是否存在
    Set<String> q2 = new HashSet<>();
    Set<String> visited = new HashSet<>();
    int step = 0;
    q1.add("0000");
    q2.add(target);
    while (!q1.isEmpty() && !q2.isEmpty()) {
        // 哈希集合在遍历的过程中不能修改，用 temp 存储扩散结果
        Set<String> temp = new HashSet<>();
        //将 q1 中的所有节点向周围扩散 
        for (String cur : q1) {
            // 判断是否到达终点 
            if (deads.contains(cur))
                continue;
            if (q2.contains(cur))
                return step;
            visited.add(cur);
            // 将一个节点的未遍历相邻节点加入集合 
            for (int j = 0; j < 4; j++) {
                String up = plusOne(cur, j);
                if (!visited.contains(up))
                    temp.add(up);
                String down = minusOne(cur, j);
                if (!visited.contains(down))
                    temp.add(down);
            }
        }
        step++; // 在这里增加步数 
        // temp 相当于 q1
        // 这里交换 q1 q2，下一轮 while 就是扩散 q2
        q1 = q2;
        q2 = temp;
    }
    return -1;
}

// 127. Word Ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> visted = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        visted.add(beginWord);
        int level = 1;
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i< size; i++){
                String str = queue.poll();
                if(str.equals(endWord)) return level; 
                for (int k = 0; k < str.length(); k ++){
                    char[] chars = str.toCharArray(); // chars 不能放在for外面 因为每一次Z结束后不会回到原来位置
                     for (char c = 'a'; c <= 'z'; c++){ // CHAR的循环方式
                         chars[k] = c;
                         String cur = new String(chars);
                         if(!visted.contains(cur) && wordSet.contains(cur)){
                              queue.offer(cur);
                               visted.add(cur);
                         }
                 } 
                } 
            }
            
            level++;
        }
        return 0;
    }
}


// 433. Minimum Genetic Mutation
  public int minMutation(String start, String end, String[] bank) {
        if (start == null || end == null || start.length() == 0 || end.length() == 0) return 0;
        Queue<String> q = new LinkedList<>();
        Set<String> store = new HashSet<>();
        Set<String> visted = new HashSet<>();
        store.addAll(Arrays.asList(bank));
        q.offer(start);
        visted.add(start);
        char[] dirs = new char[]{'A','C','G','T'};
        int step = 0;
        while (!q.isEmpty()){
            int size = q.size();
            for (int i = 0; i < size; i ++){
                String cur = q.poll();
                if (cur.equals(end)){
                    return step;
                }
               for(int k = 0; k < cur.length(); k ++){
                    char[] chars = cur.toCharArray();
                    for(int j = 0; j < dirs.length; j ++){
                        chars[k] = dirs[j];
                        String next = new String(chars);
                        if (!visted.contains(next) && store.contains(next)){
                            q.offer(next);
                            visted.add(next); // visted 别忘记
                        }
                    }
               } 
            }
            step ++;
        }
        return -1;
    }
}
//301. Remove Invalid Parentheses
//我们做BFS，上一层level和下一层level之间的关系为：把所有上一层level中的每个元素都拿出来，
// 列举出在删除一个括号后的所有可能的情况。(不管删除以后是否合法），添加到下一个level中的元素。
//  例如：current level是 ["(()", "())"]
//  - 那么下一层level中的元素应该是:
//  1. 对 "(()" 删除一个括号的所有可能为： (), (), ((
//  2. 对 "())" 删除一个括号的所有可能为： (), )), ()
//  这六个就是下一个level的全部内容了。
//用SET判断是否类似（）（) 重复 用ISVALID判断有效括号

class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }
        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();//当前level的所有的组合   
        visited.add(s);
        queue.add(s);   
        boolean found = false;
        String temp = s;    
        while (!queue.isEmpty()) {
            temp = queue.poll();   
            if (isValid(temp)) {
                result.add(temp);
                found = true;
            }
            if (found) { // 把当前层逻辑全部执行完的一种做法,不执行下一层
                continue;
            }
            for (int i = 0; i < temp.length(); i++) {
                if (temp.charAt(i) != '(' && temp.charAt(i)!= ')') {
                    continue;
                }
                String tempSub = temp.substring(0, i) + temp.substring(i + 1);
                if (!visited.contains(tempSub)) {
                    queue.add(tempSub);
                    visited.add(tempSub);
                }
            }
        }
        return result;
    }
    
    private boolean isValid(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                count++;
            }
           if (c == ')') {
               if (count == 0)
               return false;
                 count --;
              }
        }
        return count == 0;
    }
}
//695. Max Area of Island
class Solution {
       private int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
       private int R, C;
       private int[][] grid;
       private boolean[][] visited;
       public int maxAreaOfIsland(int[][] grid){
           R = grid.length;
           C = grid[0].length;
           this.grid = grid;
           visited = new boolean[R][C];
           int res = 0;
           for(int i = 0; i < R; i ++)
               for(int j = 0; j < C; j ++)
                   if(grid[i][j] == 1 && !visited[i][j])
                       res = Math.max(res, dfs(i, j));
           return res;
       }  
       private int dfs(int x, int y){
           visited[x][y] = true;
           int res = 1;
           for(int d = 0; d < 4; d ++){
               int nextx = x + dirs[d][0], nexty = y + dirs[d][1];
               if(inArea(nextx, nexty) && grid[nextx][nexty] == 1 && !visited[nextx][nexty])
                   res += dfs(nextx, nexty);
           }
           return res;
       }
   
       private boolean inArea(int x, int y){
           return x >= 0 && x < R && y >= 0 && y < C;
       }
   
// 也可以用板来直接记录VISITED,但是这类代码让代码的可读性变弱
class Solution {
   public int maxAreaOfIsland(int[][] grid) {
      int maxArea = 0;
      for (int i = 0; i < grid.length; i++) {
         for (int j = 0; j < grid[0].length; j++) {
            if (grid[i][j] == 1) {
               maxArea = Math.max(maxArea, dfs(grid, i, j));
            }
         }
      }

      return maxArea;
   }
   private int dfs(int[][] grid, int i, int j) {
      if (i >= 0 && i < grid.length && j >= 0 && j < grid[0].length && grid[i][j] == 1) {
         grid[i][j] = 0;//计算过了
         return 1 + dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1);//记得前面的1是本身，需要加上
      }
      return 0;//当前元素为0，不是island，直接返回
   }
}
}
// 200. Number of Islands
class Solution {
    int[][] dis = new int[][]{{-1,0},{0,-1},{0,1},{1,0}};
    int R = 0;
    int C =  0;
    boolean[][] visited ;
    char[][] grid;
    public int numIslands(char[][] grid) {
      this.grid = grid;
      R = grid.length;  
      if(R == 0) return 0;
      C = grid[0].length;
      visited = new boolean[R][C];  
      int result = 0;
      for(int i = 0; i < R; i ++)
         for(int j = 0; j < C; j ++) {
            if(grid[i][j] == '1' && !visited[i][j]){
               dfs(grid,i,j);
               result ++; // 联通分量
            }
         }
      return result;
    }
     public void dfs(char[][] grid, int x, int y){
      visited[x][y] = true;
      for (int i = 0; i < 4; i ++){
         int nextx = x + dis[i][0];
         int nexty = y + dis[i][1]; // x , y 别弄混了
         if(inArea(nextx,nexty) && grid[nextx][nexty] == '1' && !visited[nextx][nexty] ){
            dfs(grid,nextx,nexty);
         }
      }
   }
   private boolean inArea(int x, int y){
     return x >= 0 && x < R && y >=0 && y < C;
   }
}
// 同样也可以省略VISITED：
class Solution {
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0){
            return 0;
        }
        int m = grid.length;
        int n = grid[0].length;
        int result = 0;
        for (int i = 0; i < m; i++) { // m , n need match;
            for (int j = 0; j < n; j++){
                if(grid[i][j] == '1'){
                    dfsIslans(grid,i,j,m,n);
                    result ++;  
                }
            }
        }
        return result;
    }
    
    private void dfsIslans(char[][] grid, int i, int j, int m, int n){
        if (i < 0|| j < 0 || i >= m || j >= n || grid[i][j] == '0'){ // i need larger than m; j larger than n;
            return;
        }
        grid[i][j] = '0';
        dfsIslans(grid, i+1, j, m, n);
        dfsIslans(grid, i-1, j, m, n);
        dfsIslans(grid, i, j+1, m, n);
        dfsIslans(grid, i, j-1, m, n);
    }
}

//1091. Shortest Path in Binary Matrix
class Solution {

    private int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1},
                            {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
    private int R, C;

    public int shortestPathBinaryMatrix(int[][] grid) {

        R = grid.length;
        C = grid[0].length;
        boolean[][] visited = new boolean[R][C];
        int[][] dis = new int[R][C];

        if(grid[0][0] == 1) return -1;
        if(R == 0 && C == 0) return 1;

        // BFS
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        visited[0][0] = true;
        dis[0][0] = 1;
        while(!queue.isEmpty()){
            int cur = queue.remove();
            int curx = cur / C, cury = cur % C;//一维转二维
            for(int d = 0; d < 8; d ++){
                int nextx = curx + dirs[d][0];
                int nexty = cury + dirs[d][1];
                if(inArea(nextx, nexty) && !visited[nextx][nexty] && grid[nextx][nexty] == 0){
                    queue.add(nextx * C + nexty);
                    visited[nextx][nexty] = true;
                    dis[nextx][nexty] = dis[curx][cury] + 1;
                    if(nextx == R - 1 && nexty == C - 1)
                        return dis[nextx][nexty];
                }
            }
        }
        return -1;
    }

    private boolean inArea(int x, int y){
        return x >= 0 && x < R && y >= 0 && y < C;
    }
}


```
