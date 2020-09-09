```java
// 207. Course Schedule
class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
          List<List<Integer>> adjTable = new ArrayList<>();
          int[] indegree = new int[numCourses];
          for (int i = 0; i < numCourses; i ++) {
              adjTable.add(new ArrayList<>());
          } // 固定写法 建立一个空的临接表
          for (int[] pairs: prerequisites){
              indegree[pairs[0]]++;
              adjTable.get(pairs[1]).add(pairs[0]);
          } //填入度表 和邻接表 
          Queue<Integer> queue = new LinkedList<>();
          for (int i = 0; i < numCourses; i ++){
              if(indegree[i] == 0) {
                  queue.offer(i);
              }
          } //把入度为零的先放到队列
          while(!queue.isEmpty()) {
              int pre = queue.poll();
              numCourses --; //每一次入度为零则意味着可以完成该课程
              for (int cur : adjTable.get(pre)){
                  indegree[cur]--;
                  if(indegree[cur] == 0){
                      queue.offer(cur);
                  }
              }
          }
        return numCourses == 0;
    }
}

// 227. Basic Calculator II
class Solution {
    public int calculate(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int num = 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<>();//存符号两边的数字
        char sign = '+';
        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
             //   num = Integer.valueOf(ch);
                num = num * 10 + (ch - '0'); //n有可能是上一轮的数字 多位数的多个数字
            }
            if ((!Character.isDigit(ch) && //不是数字，是符号
                ch != ' ') || //不是空格
               i == len - 1) { //最后一个位置虽然是数字但位置特殊也要计算
                
                // 加法和减法是直接push，加正数和负数的区别
                if (sign == '+') {
                    stack.push(num); // 不能CONTINUE 因为后面还要变更符号以及重置零
                }
                if (sign == '-') {
                    stack.push(-num);
                }
                //乘法和除法是先计算两旁的数再push
                if (sign == '*') {
                    stack.push(stack.pop() * num); //stack中最顶上的数字是当前符号的前一个数字
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = ch; //更新当前sign的符号，下一次循环根据sign的值处理该sign两边的数字
                num = 0;
            }
        }
        int result = 0;
        //循环做完后，所有乘法和除法也做完并push了，目前stack里面只剩加法和减法符号
        for (int ele : stack) { //这样遍历是先进先出（顺序不影响）
            result += ele;
        }     
        return result;
    }
}
//34. Find First and Last Position of Element in Sorted Array
class Solution {
   public int[] searchRange(int[] nums, int target) {
    int[] result = new int[2];
    result[0] = findFirst(nums, target);
    result[1] = findLast(nums, target);
    return result;
}

private int findFirst(int[] nums, int target){
    int idx = -1;
    int start = 0;
    int end = nums.length - 1;
    while(start <= end){
        int mid = (start + end) / 2;
        if(nums[mid] >= target){
            end = mid - 1;
        }else{
            start = mid + 1;
        }
        if(nums[mid] == target) idx = mid;
    }
    return idx;
}

private int findLast(int[] nums, int target){
    int idx = -1;
    int start = 0;
    int end = nums.length - 1;
    while(start <= end){
        int mid = (start + end) / 2;
        if(nums[mid] <= target){
            start = mid + 1;
        }else{
            end = mid - 1;
        }
        if(nums[mid] == target) idx = mid;
    }
    return idx;
}
}
// 124. Binary Tree Maximum Path Sum
class Solution {
   int max = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        dfs(root);
        return max;
    }
    public int dfs(TreeNode root) { // 后序遍历
        if (root == null) {
            return 0;
        }
        //计算左边分支最大值，左边分支如果为负数还不如不选择
        int leftMax = Math.max(0, dfs(root.left));
        //计算右边分支最大值，右边分支如果为负数还不如不选择
        int rightMax = Math.max(0, dfs(root.right));
        //left->root->right 作为路径与历史最大值做比较  // 更新遍历在当前节点时的最大路径和
        max = Math.max(max, root.val + leftMax + rightMax);
        // 选择以当前节点为根的含有最大值的路劲，左或右；返回给上一层递归的父节点作为路径
        return root.val + Math.max(leftMax, rightMax); // 不能左右同时返回
    }
    // https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/shou-hui-tu-jie-hen-you-ya-de-yi-dao-dfsti-by-hyj8/
}

//341. Flatten Nested List Iterator
  public class NestedIterator implements Iterator<Integer> {
       private List<Integer> listIterator;
       private int index;
   
       public NestedIterator(List<NestedInteger> nestedList) {
           listIterator = getListIterator(nestedList);
           index = 0;
       }
   
       @Override
       public Integer next() {
           if (hasNext()){
               Integer val = listIterator.get(index ++);
               return val;
           }
           return null;
       }
   
       @Override
       public boolean hasNext() {
           return index  < listIterator.size();
       }
       
       public static List<Integer> getListIterator(List<NestedInteger> nestedList) {
           ArrayList<Integer> res = new ArrayList<>();
           for (NestedInteger val : nestedList) {
               if (val.isInteger()) {
                   res.add(val.getInteger());
               } else {
                   res.addAll(getListIterator(val.getList())); 
               }
           }
           return res;
       }
   }
//在初始化迭代器的时候就直接把结果遍历出来，递归遍历列表中的数据，是整数就放入List，不是则再递归遍历，代码结构简单。
// isInteger(); getInteger(); getList();
// Index and size();

// 133. Clone Graph
class Solution { // hashMap + DFS
    public HashMap<Integer, Node> map = new HashMap<>(); 
   //Node.val is unique for each node. 
    public Node cloneGraph(Node node) {
        return clone(node);
    }
    public Node clone(Node node) {
        if (node == null) return null;
        if (map.containsKey(node.val)) 
            return map.get(node.val);     
        Node newNode = new Node(node.val, new ArrayList<Node>());
        map.put(newNode.val, newNode);
        for (Node neighbor : node.neighbors) 
            newNode.neighbors.add(clone(neighbor));
        return newNode;
    }
}
// 239. Sliding Window Maximum
class Solution { // Time limit pass but still need to understand.
    public int[] maxSlidingWindow(int[] nums, int k) {
      PriorityQueue<Integer> queue = new PriorityQueue<>(k, Collections.reverseOrder()); // reverseOrder() not reverse()
      int[] result = new int[nums.length - k + 1];  
     for(int i = 0; i < k; i ++){
        queue.add(nums[i]);
      }
    result[0] = queue.peek();
    for(int i = k; i < nums.length; i ++){
        queue.remove(nums[i - k]);
        queue.add(nums[i]);
        result[i - k + 1] = queue.peek(); // not queue.poll();
    } 
    return result;
    }
} 
// 本题中每个窗口前进的时候，要添加一个数同时减少一个数，所以想在 O(1)O(1) 的时间得出新的最值，就需要「单调队列」这种特殊的数据结构来辅助了。
//「单调队列」的核心思路和「单调栈」类似。单调队列的 push 方法依然在队尾添加元素，但是要把前面比新元素小的元素都删掉：
// 如果每个元素被加入时都这样操作，最终单调队列中的元素大小就会保持一个单调递减的顺序，
// 单独看 push 操作的复杂度确实不是 O(1)O(1)，但是算法整体的复杂度依然是 O(N)O(N) 线性时间。要这样想，nums 中的每个元素最多被 push_back 和 pop_back 一次，没有任何多余操作，所以整体的复杂度还是 O(N)O(N)。
//「单调队列」和「优先级队列」实际上差别很大的。单调队列在添加元素的时候靠删除元素保持队列的单调性，相当于抽取出某个函数中单调递增（或递减）的部分；而优先级队列（二叉堆）相当于自动排序.
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k ==0 || nums.length < k) 
            return new int[0];
        Deque<Integer> cache = new LinkedList<Integer>();
        int[] result = new int[nums.length - k + 1];
        for (int r = 0; r < nums.length; r ++) {
            while (!cache.isEmpty() && nums[cache.peekLast()] < nums[r]) {
                cache.removeLast();
            }
            cache.addLast(r);
            if(cache.peekFirst() == r - k) {
                cache.removeFirst();
            }
            if(!cache.isEmpty() && r - k + 1 >= 0) {
                 result[r - k + 1] = nums[cache.peekFirst()]; // don't forget cache only store index
            }
            
        }
        return result;
        
    }
}
// Deque offerFirst(element), offerLast(element), peekLast(); peekFirst(); pollFirst() ; pollLast()

//426. Convert Binary Search Tree to Sorted Doubly Linked List
class Solution {
  public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node first = null;
        Node last = null;

        Deque<Node> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (first == null) {
                first = root;
            }
            if (last != null) {
                last.right = root;
                root.left = last;
            }
            last = root;
            root = root.right;
        }
        first.left = last;
        last.right = first;
        return first;
    }
}

//300. Longest Increasing Subsequence
class Solution {
 public int lengthOfLIS(int[] nums) {
    int[] dp = new int[nums.length]; // dp[i] 表示以 nums[i] 这个数结尾的最长递增子序列的长度。
    Arrays.fill(dp, 1);     // base case：dp 数组全都初始化为 1
    for (int i = 0; i < nums.length; i++) {
        for (int j = 0; j < i; j++) {
            if (nums[i] > nums[j]) {
            dp[i] = Math.max(dp[i], dp[j] + 1);
            // can't use break here why? see example like [ 2, 4, 1, 5] 5 is dp[2] = 1; but Dp[5] = dp[1] + 1;
         }        
        }
    }
    
    int res = 0;
    for (int i = 0; i < dp.length; i++) {
        res = Math.max(res, dp[i]);
    }
    return res;
   }
}

// 50. Pow(x, n)
class Solution {
    public double myPow(double x, int n) {
        if (n < 0) {
            x = 1/x;
            n = -n;
        }
        return myNewPow( x, n);
    } 
    public double myNewPow(double x, int n) { // double type need to keep not int.
        if (n == 0) return 1.00; // 1.00 not 1
        if (n == 1) return x;
        double half = myNewPow(x, n/2);
        if (n % 2 == 0){  
            return half * half;
        } else {
            return half * half * x;     
        }
    }
}

// 55. Jump Game
class Solution {
    public boolean canJump(int[] nums) {
	int max = 0; // 能到达的最远位置
	for (int i = 0; i < nums.length; i++)
	{
		if (i > max) return false;
		max = Math.max(max, i + nums[i]);
	}
	return true;   
    }
}
//思路：尽可能到达最远位置（贪心）。如果能到达某个位置，那一定能到达它前面的所有位置。
//方法：初始化最远位置为 0，然后遍历数组，如果当前位置能到达，并且当前位置+跳数>最远位置，就更新最远位置。最后比较最远位置和数组长度。

// 45. Jump Game II
class Solution {
   public int jump(int[] nums) {
    int end = 0;
    int maxPosition = 0; 
    int steps = 0;
    for(int i = 0; i < nums.length - 1; i++){
        //找能跳的最远的
        maxPosition = Math.max(maxPosition, nums[i] + i); 
        if( i == end){ //遇到边界，就更新边界，并且步数加一
            end = maxPosition;
            steps++;
        }
    }
    return steps;
 }
}
// 贪心算法，每次找局部最优，最后达到全局最优


// 173. Binary Search Tree Iterator
class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = null;

    public BSTIterator(TreeNode root) {
        cur = root;
    }

    /** @return the next smallest number */
    public int next() {
        int res = -1;
        while (cur != null || !stack.isEmpty()) {
            // 节点不为空一直压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; // 考虑左子树
            }
            // 节点为空，就出栈
            cur = stack.pop();
            res = cur.val;
            // 考虑右子树
            cur = cur.right;
            break; // even sometime next() is o(h) but amortized time complex is o(1); 本体O(1)的关键
        }
        return res;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}
/*只需要把 stack 和 cur 作为成员变量，然后每次调用 next 就执行一次 while 循环，并且要记录当前值，结束掉本次循环。*/

// 210. Course Schedule II
class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses == 0) {
            return new int[0];
        }
        int[] inDegrees = new int[numCourses];
        //BFS
        Queue<Integer> queue = new LinkedList<>();
        // 建立入度表
        for (int[] pre : prerequisites) {
            inDegrees[pre[0]]++;
        }    
        // 入度为0的节点的队列
        for (int i = 0; i < inDegrees.length; i++) {
            if (inDegrees[i] == 0) {
                queue.offer(i); // 入度索引
            }
        }
        int count = 0; // 可以学完的课程的数量
        int[] result = new int[numCourses];//可以学完的课程
        // 根据提供的先修课列表，删除入度为 0 的节点
        while (!queue.isEmpty()){
            int curr = queue.poll();
            result[count] = curr;   // 将可以学完的课程加入结果当中
            count++;
            
            for (int[] pre : prerequisites) {
                if (pre[1] == curr){
                    inDegrees[pre[0]]--;
                    if (inDegrees[pre[0]] == 0) {
                        queue.offer(pre[0]);
                    }
                }
            }
        }
        if (count == numCourses) { // 可以全部学完
            return result;
        }
        return new int[0];        
    }
}
// 199. Binary Tree Right Side View
class Solution {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i = 1; i <= size; i ++){
                TreeNode node = queue.poll();
                if(node.left != null){
                    queue.offer(node.left);
                }
                if(node.right != null){
                    queue.offer(node.right);
                }
                if(i == size){
                    res.add(node.val);
                }
            }
        }
        return res;
    }
}
// 212. Word Search II
class Solution {
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                res.add(word);
            }
        }
        return res;
    }

    public boolean exist(char[][] board, String word) {
        int row = board.length;
        int col = board[0].length;
        boolean[][] visited = new boolean[row][col];
        for (int i = 0; i < row; i ++) {
            for (int j = 0; j < col; j ++){
                if (board[i][j] == word.charAt(0) && findWord(board, word, i, j, 0, visited)) { // start from 0;
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean findWord(char[][] board, String word, int i, int j, int size,  boolean[][] visited){
        if(size == word.length()){// it means last run's index reach last letter; similer like node == null;
            return true;
        }
        
        if (!inArea(board, i , j) || visited[i][j] || board[i][j] != word.charAt(size)){ // need check in area before visited[i][j]
            return false;
        }
        visited[i][j] = true;
        boolean res = findWord(board, word, i - 1, j, size + 1, visited) ||
            findWord(board, word, i, j - 1, size + 1, visited) ||
            findWord(board, word, i + 1, j, size + 1, visited) ||
            findWord(board, word, i , j + 1, size + 1, visited);
         visited[i][j] = false;   
        return res;
    }
    
    boolean inArea(char[][] board,int i, int j){
        return i >= 0 && i < board.length && j >= 0 && j < board[0].length;
    }
}
// 105. Construct Binary Tree from Preorder and Inorder Traversal
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        return buildTreeHelper(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }   
        
     public TreeNode buildTreeHelper(int[] preorder, int pleft, int pright, int[] inorder, int ileft, int iright) {
         if (pleft > pright || ileft > iright ) {
             return null;
         }
         int rootIndex = 0;
         for (int i = ileft; i <= iright; i ++) {
             if (preorder[pleft] == inorder[i]){
                 rootIndex = i;
                 break;
             }
         }
         TreeNode root = new TreeNode(preorder[pleft]);
         root.left = buildTreeHelper(preorder, pleft + 1, pleft + rootIndex - ileft, inorder, ileft, rootIndex - 1); // 
         root.right =  buildTreeHelper(preorder, pleft + rootIndex - ileft + 1, pright,inorder, rootIndex + 1,iright ); 
         return root;    
     }  
}
// 211. Design Add and Search Words Data Structure



// 127. Word Ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
       Set<String> wordSet = new HashSet(wordList);
       if (!wordSet.contains(endWord)) {
           return 0;
       } 
       Set<String> visited = new HashSet<>();    
       Queue<String> queue = new LinkedList<>();
       queue.offer(beginWord);
       visited.add(beginWord); 
       int level = 0;
       while (!queue.isEmpty()) {
           int size = queue.size();
           level ++;
           for (int i = 0; i < size; i ++) {
               String cur = queue.poll();
               if (cur.equals(endWord)) return level;
               for (int k = 0; k < cur.length(); k ++){
                   char[] chars = cur.toCharArray();// 不能放外面 因为每一次只改变一个字符 不能互相影响
                   for(char c = 'a'; c <= 'z'; c ++) {
                       chars[k] = c;
                       String str = new String(chars);
                       if (!visited.contains(str) && wordSet.contains(str)){
                           queue.offer(str);
                           visited.add(str);
                       }
                   }
               }
           }
       } 
      return 0;  
    }
}


```
