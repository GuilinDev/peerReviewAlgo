```java

// 79. Word Search
class Solution { // DFS
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) return false;
        int m = board.length; int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        int index = 0;
        for (int i = 0; i < m; i ++)
            for (int j = 0; j < n; j ++){
                if(board[i][j] == word.charAt(0) && searchWord(board,word,index,visited,i,j)){ 
                    return true;
                }
            }
        return false;
    }
    public boolean search(int i, int j, int index,char[][] board, String word, boolean[][] visited){
        if (index == word.length()){ // not word.length - 1. because we still need to check if last char is matched. like node == null 
            return true;
        }
        
        if(i< 0 || i >= board.length || j < 0 || j >= board[0].length) return false; 
        // need check in area before visited[i][j] 
        // equals case also need to excluded
        if (visited[i][j] || board[i][j] != word.charAt(index)) return false;
        visited[i][j] = true; // like backtracking
        boolean isMatch = search(i - 1, j, index + 1, board, word, visited) ||
                          search(i , j - 1, index + 1, board, word, visited) ||
                          search(i + 1, j, index + 1, board,word, visited) ||
                          search(i, j + 1, index + 1, board,word, visited);
        visited[i][j] = false;
        return isMatch;
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
// 560. Subarray Sum Equals K
class Solution {
    public int subarraySum(int[] nums, int k) {
        int sum = 0; int res = 0; // sum[i, j] = sum[0, j] - sum[0, i - 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,1); // don't forget this, we have one presum equals 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            if (map.containsKey(sum - k)){
                res += map.get(sum - k); // may be has more than one presum equals same number.
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return res;
    }
}

// 46. Permutations
class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new LinkedList<>();
        if (nums == null || nums.length == 0) return res;
        Deque<Integer> temp = new LinkedList<>();
        permuteHelper(res, temp, nums, 0);
        return res;
    }
    
    public void permuteHelper( List<List<Integer>> res, Deque<Integer> temp, int[] nums, int start){
        if (temp.size() == nums.length){
            res.add(new LinkedList(temp));
            return;
        }
        
        for (int i = 0; i < nums.length; i ++) {
            if (temp.contains(nums[i])) continue;
            temp.addLast(nums[i]);
            permuteHelper(res, temp, nums, i);
            temp.removeLast();
        } 
    }
}

//103. Binary Tree Zigzag Level Order Traversal
class Solution {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean isFromLeft = true;
        while (!queue.isEmpty()){
            int len = queue.size();
            LinkedList<Integer> oneLevel = new LinkedList<>(); 
            //to use addFirst(), don't use List<> = new LinkedList<>();
            for (int i = 0; i < len; i ++){
                TreeNode curNode = queue.poll();
                int value = curNode.val;
                if (isFromLeft){
                    oneLevel.add(value);
                } else {
                    oneLevel.addFirst(value);
                }
                if (curNode.left != null)   queue.offer(curNode.left);
                if (curNode.right != null)  queue.offer(curNode.right);
            }
            res.add(oneLevel);
            isFromLeft = !isFromLeft; // 取反
        }
        return res;
    }
}
//215. Kth Largest Element in an Array
class Solution {
    public int findKthLargest(int[] nums, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();// default size will resize automatically
    for(int val : nums) {
        pq.offer(val);
        if(pq.size() > k) {
            pq.poll();
        }
    }
    return pq.peek();
  }
}
class Solution {
    public int findKthLargest(int[] nums, int k) {
        //传入的参数nums.length - k表示找到nums.length - k最小的
        return findK(nums, nums.length - k, 0, nums.length - 1);
    }

    private int findK(int[] nums, int k, int left, int right) {
        if (left >= right) {//递归的终止条件，表示已经递归找完整个数组
            return nums[left];
        }

        int i = partition(nums, left, right);
        if (i == k) {//恰好是第k大的数
            return nums[i];
        } else if (i < k) {//在选定的pivot右边
            return findK(nums, k, i + 1, right);
        } else {//左边
            return findK(nums, k, left, i - 1);
        }
    }

    //只partition一次
    private int partition(int[] nums, int start, int end) {
        int pivot = nums[start];//基准
        int slow = start;//开始
        int fast = start + 1;//从start位置后面一位元素准备开始swap

        while (fast <= end) {//到最后一个元素前
            if (nums[fast] < pivot) {//将小的元素挪到pivot前面
                slow++;//这里需要先让指向pivot索引后移一位
                swap (nums, slow, fast);
            }
            fast++;//可能的交换结束后，fast继续右移
        }
        swap (nums, start, slow);//最后需要交换一下初始位置元素和pivot索引的元素
        return slow;//pivot所处的位置
    }

    private void swap (int[] nums, int i, int j) {
        // 这里用三次异或不行
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
// 49. Group Anagrams
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        if(strs == null || strs.length == 0) return res;
        HashMap<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            int[] temp = new int[26];
            for (char c : chars){
                temp[c - 'a']++;
            }
            StringBuilder sb = new StringBuilder();
           for(int i = 0; i < 26; i++){
               if(temp[i] != 0){
                   sb.append(temp[i]).append('a' + i); //it will like 197296: 97 is letters assii code position
               }
           }
           String key = sb.toString(); 
           if(map.containsKey(key)){
               map.get(key).add(str);
           } else {
               ArrayList<String> ls = new ArrayList<>();
               ls.add(str);
               map.put(key,ls);
           }
            
        }
        for (String key : map.keySet()){
            res.add(map.get(key));
        }
        return res;
    }
}
//31. Next Permutation


// 10. Regular Expression Matching
class Solution {
public boolean isMatch(String s,String p){
            if (s == null || p == null) {
                return false;
            }
            boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
    //初始化dp[0][0]=true,dp[0][1]和dp[1][0]~dp[s.length][0]默认值为false所以不需要显式初始化
            dp[0][0] = true;//dp[i][j] 表示 s 的前 i 个是否能被 p 的前 j 个匹配
    
       for (int k=2;k<=p.length();k++){
        //p字符串的第2个字符是否等于'*',此时j元素需要0个，所以s不变p减除两个字符
        dp[0][k]=p.charAt(k-1)=='*'&&dp[0][k-2];
    }

            for (int i = 0; i < s.length(); i++) {
                for (int j = 0; j < p.length(); j++) {
                    if (p.charAt(j) == '.' || p.charAt(j) == s.charAt(i)) {//如果是任意元素 或者是对于元素匹配
                        dp[i + 1][j + 1] = dp[i][j];
                    }
                    if (p.charAt(j) == '*') {
                        if (p.charAt(j - 1) != s.charAt(i) && p.charAt(j - 1) != '.') {//如果前一个元素不匹配 且不为任意元素
                            dp[i + 1][j + 1] = dp[i + 1][j - 1];
                        } else {
                            dp[i + 1][j + 1] = (dp[i + 1][j] || dp[i][j + 1] || dp[i + 1][j - 1]);
                            /*
                            dp[i][j] = dp[i-1][j] // 多个字符匹配的情况	
                            or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
                            or dp[i][j] = dp[i][j-2] // 没有匹配的情况
                             */
                            
                        }
                    }
                }
            }
            return dp[s.length()][p.length()];
        }
}
/*状态
首先状态 dp 一定能自己想出来。
dp[i][j] 表示 s 的前 ii 个是否能被 p 的前 jj 个匹配

转移方程
怎么想转移方程？首先想的时候从已经求出了 dp[i-1][j-1] 入手，再加上已知 s[i]、p[j]，要想的问题就是怎么去求 dp[i][j]。

已知 dp[i-1][j-1] 意思就是前面子串都匹配上了，不知道新的一位的情况。
那就分情况考虑，所以对于新的一位 p[j] s[i] 的值不同，要分情况讨论：

考虑最简单的 p[j] == s[i] : dp[i][j] = dp[i-1][j-1]
然后从 p[j] 可能的情况来考虑，让 p[j]=各种能等于的东西。

p[j] == "." : dp[i][j] = dp[i-1][j-1]

p[j] ==" * ":

第一个难想出来的点：怎么区分 *∗ 的两种讨论情况
首先给了 *，明白 * 的含义是 匹配零个或多个前面的那一个元素，所以要考虑他前面的元素 p[j-1]。* 跟着他前一个字符走，前一个能匹配上 s[i]，* 才能有用，前一个都不能匹配上 s[i]，* 也无能为力，只能让前一个字符消失，也就是匹配 00 次前一个字符。
所以按照 p[j-1] 和 s[i] 是否相等，我们分为两种情况：

3.1 p[j-1] != s[i] : dp[i][j] = dp[i][j-2]
这就是刚才说的那种前一个字符匹配不上的情况。
比如(ab, abc * )。遇到 * 往前看两个，发现前面 s[i] 的 ab 对 p[j-2] 的 ab 能匹配，虽然后面是 c*，但是可以看做匹配 00 次 c，相当于直接去掉 c *，所以也是 True。注意 (ab, abc**) 是 False。
3.2 p[j-1] == s[i] or p[j-1] == "."：
* 前面那个字符，能匹配 s[i]，或者 * 前面那个字符是万能的 .
因为 . * 就相当于 . .，那就只要看前面可不可以匹配就行。
比如 (##b , ###b *)，或者 ( ##b , ### . * ) 只看 ### 后面一定是能够匹配上的。
所以要看 b 和 b * 前面那部分 ## 的地方匹不匹配。
第二个难想出来的点：怎么判断前面是否匹配

dp[i][j] = dp[i-1][j] // 多个字符匹配的情况	
or dp[i][j] = dp[i][j-1] // 单个字符匹配的情况
or dp[i][j] = dp[i][j-2] // 没有匹配的情况	
看 ### 匹不匹配，不是直接只看 ### 匹不匹配，要综合后面的 b b* 来分析
这三种情况是 oror 的关系，满足任意一种都可以匹配上，同时是最难以理解的地方：

dp[i-1][j] 就是看 s 里 b 多不多， ### 和 ###b * 是否匹配，一旦匹配，s 后面再添个 b 也不影响，因为有 * 在，也就是 ###b 和 ###b *也会匹配。

dp[i][j-1] 就是去掉 * 的那部分，###b 和 ###b 是否匹配，比如 qqb qqb

dp[i][j-2] 就是 去掉多余的 b *，p 本身之前的能否匹配，###b 和 ### 是否匹配，比如 qqb qqbb* 之前的 qqb qqb 就可以匹配，那多了的 b * 也无所谓，因为 b * 可以是匹配 00 次 b，相当于 b * 可以直接去掉了。

三种满足一种就能匹配上。

为什么没有 dp[i-1][j-2] 的情况？ 就是 ### 和 ### 是否匹配？因为这种情况已经是 dp[i][j-1] 的子问题。也就是 s[i]==p[j-1]，则 dp[i-1][j-2]=dp[i][j-1]。

最后来个归纳：
如果 p.charAt(j) == s.charAt(i) : dp[i][j] = dp[i-1][j-1]；
如果 p.charAt(j) == '.' : dp[i][j] = dp[i-1][j-1]；
如果 p.charAt(j) == '*'：
如果 p.charAt(j-1) != s.charAt(i) : dp[i][j] = dp[i][j-2] //in this case, a* only counts as empty
如果 p.charAt(i-1) == s.charAt(i) or p.charAt(i-1) == '.'：
dp[i][j] = dp[i-1][j] //in this case, a* counts as multiple a
or dp[i][j] = dp[i][j-1] // in this case, a* counts as single a
or dp[i][j] = dp[i][j-2] // in this case, a* counts as empty*/

//91. Decode Ways
class Solution {
    public int numDecodings(String s) {
        if (s == null || s.length() == 0) return 0;
        int n = s.length();
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;
        for (int i = 2; i <= n; i++) {
            int step1 = Integer.valueOf(s.substring(i-1, i)); // like climbing stairs.
            int step2 = Integer.valueOf(s.substring(i-2, i)); 
            if(step1 >= 1){
                dp[i] += dp[i - 1];
            }
            if(step2 >= 10 && step2 <= 26) { 
                dp[i] += dp[i - 2];
            }
        }
        return dp[n];
    }
}

// 127. Word Ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        int step = 0;
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        queue.offer(beginWord);
        visited.add(beginWord);
        while (!queue.isEmpty()){
            step ++;
            int size = queue.size();
            for (int i = 0; i < size; i ++){
                String cur = queue.poll();
                if (cur.equals(endWord)) return step;
                for (int j = 0; j < cur.length(); j ++) {
                      char[] curChar = cur.toCharArray(); // can't put out side, since every time only one letter allow to change    
                    for (char c = 'a'; c <= 'z'; c ++){ // remember this way to loop through a to z;
                        curChar[j] = c;
                        String str = new String(curChar);
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
//139. Word Break
//295. Find Median from Data Stream


//100. Same Tree
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
       if (p == null && q == null) return true;
  //     if (p != null && q == null) return false; 
 //      if (p == null && q != null) return false;
         if (p == null || q == null) return false;
       if (p.val != q.val) return false;
       boolean left = isSameTree(p.left, q.left);
       boolean right = isSameTree(p.right, q.right); 
       return left && right;
        
    }
}

// 98. Validate Binary Search Tree
class Solution {
    public boolean isValidBST(TreeNode root) {
        if (root == null) return true;
        TreeNode cur = root;
        Stack<TreeNode> stack = new Stack<>();
       // int preValue = Integer.MIN_VALUE;
      //  long preValue = Long.MIN_VALUE; //Integer.MIN_VALUE will not pass in this case 
        TreeNode pre = null;
        while (cur != null || !stack.isEmpty()){ // need or condition
            while (cur!=null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            if (pre!= null && node.val <= pre.val) return false; // need less equals
            pre = node;
            cur = node.right;
        }
        return true;
    }
}

// 621. Task Scheduler -- 桶思想

class Solution {
       public int leastInterval(char[] tasks, int n) {
        if (tasks.length <= 1 || n < 1) return tasks.length;
        //步骤1
        int[] counts = new int[26];
        for (int i = 0; i < tasks.length; i++) {
            counts[tasks[i] - 'A']++;
        }
        //步骤2
        Arrays.sort(counts);
        int maxCount = counts[25];
        int retCount = (maxCount - 1) * (n + 1) + 1;
        int i = 24;
        //步骤3
        while (i >= 0 && counts[i] == maxCount) {
            retCount++;
            i--;
        }
        //步骤4
        return Math.max(retCount, tasks.length); 
         // 如果按照最长的排完之后，后面还有剩下的没有排的，比如字符串序列式AAABBBCCCD，
        // 然后n=2的话，那拍好就是ABCABCABCD，按照公式计算出来的结果是(3-1)*(3)+1+2=9，
        // 但是实际的序列应该是ABCABCABCD，应该是10，所以通过求max来补充掉这个正好全排列但是还有多出去的情况
    }
}

// 283. Move Zeroes
class Solution {
    public void moveZeroes(int[] nums) {
        int index = 0; 
        for (int i = 0; i < nums.length; i ++) {
            if (nums[i] != 0){
                nums[index++] = nums[i];
            }
        }
        while (index < nums.length){
            nums[index ++] = 0;
        }  
    }
}

// 986. Interval List Intersections
class Solution {
  public int[][] intervalIntersection(int[][] A, int[][] B) {
    List<int[]> ans = new ArrayList();
    int i = 0, j = 0;
    while (i < A.length && j < B.length) {
       int a1 = A[i][0], a2 = A[i][1];
       int b1 = B[j][0], b2 = B[j][1];
       if(b1 <= a2 && b2 >= a1){
           ans.add(new int[]{Math.max(a1,b1),Math.min(a2,b2)});
       }
        if(a2 < b2){
            i++;
        } else {
            j++;
        }
    }
    return ans.toArray(new int[0][]);
// can also use   int[][] res = new int[al + bl][2]; and return Arrays.copyOf(res, k);
  }
}
/*1:if b2 < a1 or a2 < b1:
    [a1,a2] 和 [b1,b2] 无交集
2:   # 不等号取反，or 也要变成 and
 if b2 >= a1 and a2 >= b1:
    [a1,a2] 和 [b1,b2] 存在交集
3:如果交集区间是 [c1,c2]，那么 c1=max(a1,b1)，c2=min(a2,b2)
4: i j move 只取决于 a2 和 b2 的大小关系*/

// 39. Combination Sum
class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> temp = new LinkedList<>();
        combinationSumHelper(temp, res, candidates, target, 0);
        return res;
        
    } 
    public void combinationSumHelper(List<Integer> temp, List<List<Integer>> res, int[] candidates, int target, int start ){
        if (target < 0) return;
        if (target == 0){
            res.add(new LinkedList(temp));
            return;
        }
        for (int i = start; i < candidates.length; i ++) { // it is start in here for purmutation it will i = 0;
            temp.add(candidates[i]);
            combinationSumHelper(temp, res, candidates, target - candidates[i], i); // not i + 1 because we can reuse same elements
            temp.remove(temp.size() - 1);
        }
    }
}


```
