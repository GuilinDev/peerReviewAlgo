```java
// 79. Word Search
class Solution {
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
        
        if(i< 0 || i >= board.length || j < 0 || j >= board[0].length) return false; //  equals case also need to excluded
        if (visited[i][j] || board[i][j] != word.charAt(index)) return false;
        visited[i][j] = true;
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
        List<String> results = new ArrayList<>();
        for (String word : words) {
            if (exist(board, word)) {
                results.add(word);
            }
        }
        return results;
    }
    
    // 下面的代码是把79题的代码抄过来，判断每个单词是否能够被生成
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return false;
        }
        int rows = board.length;
        int cols = board[0].length;
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (dfs(board, i, j, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean dfs(char[][] board, int i, int j, String word, int index) {
        if (index == word.length()) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        if (i < 0 || i >= m || j < 0 || j >= n || board[i][j] != word.charAt(index)) {
            return false;
        }
        char temp = board[i][j];
        board[i][j] = '#';
        boolean result = dfs(board, i + 1, j, word, index + 1) ||
            dfs(board, i - 1, j, word, index + 1) ||
            dfs(board, i, j + 1, word, index + 1) ||
            dfs(board, i, j - 1, word, index + 1);
        board[i][j] = temp; // 这步不能忘记，检查完当前起点为止的字符串后要恢复访问记录, 
        // 为以后单词重新扫描提供方便  并且不能是BOARD[I][J],只能在‘#’ 前记录
        return result;
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




```
