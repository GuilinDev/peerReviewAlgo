```java

//226. Invert Binary Tree
class Solution {
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return root;
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }
}
public class Solution {
    public TreeNode invertTree(TreeNode root) {
        if(root == null) return null;
        TreeNode tmp = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(tmp);
        return root;
    }
}

// 104. Maximum Depth of Binary Tree
class Solution {
    public int maxDepth(TreeNode root) {
       if (root == null) return 0;
       return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));   
    }
}
// 617. Merge Two Binary Trees
class Solution {
    public TreeNode mergeTrees(TreeNode t1, TreeNode t2) {
        if (t1 == null || t2 == null) {
            return (t1 == null) ? t2 : t1;
        }
        TreeNode root = new TreeNode(t1.val + t2.val);
        root.left = mergeTrees(t1.left, t2.left);
        root.right = mergeTrees(t1.right, t2.right);
        return root;      
    }
}


//111. Minimum Depth of Binary Tree
public class Solution { 
    public int minDepth(TreeNode root) {
        if(root == null) return 0;
        int left = minDepth(root.left);
        int right = minDepth(root.right);//假如一边是零 那意味着比较时候会选零做较小数 但是那样是错误的。
        return (left == 0 || right == 0) ? left + right + 1: Math.min(left,right) + 1;
       
    }
}
class Solution {
  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }
    if ((root.left == null) && (root.right == null)) {
      return 1;
    }
    int min_depth = Integer.MAX_VALUE;
    if (root.left != null) {
      min_depth = Math.min(minDepth(root.left), min_depth);
    }
    if (root.right != null) {
      min_depth = Math.min(minDepth(root.right), min_depth);
    }
    return min_depth + 1;
  }

int minDepth(TreeNode root) {
    if (root == null) return 0;
    Queue<TreeNode> q = new LinkedList<>();
    q.offer(root);
    // root 本身就是一层，depth 初始化为 1
    int depth = 1;
    while (!q.isEmpty()) {
        int sz = q.size();
        /* 将当前队列中的所有节点向四周扩散 */
        for (int i = 0; i < sz; i++) {
            TreeNode cur = q.poll();
            /* 判断是否到达终点 */
            if (cur.left == null && cur.right == null) 
                return depth;
            /* 将 cur 的相邻节点加入队列 */
            if (cur.left != null)
                q.offer(cur.left);
            if (cur.right != null) 
                q.offer(cur.right);
        }
        /* 这里增加步数 */
        depth++;
    }
    return depth;
}
//DFS 实际上是靠递归的堆栈记录走过的路径，你要找到最短路径，肯定得把二叉树中所有树杈都探索完才能对比出最短的路径有多长
//而 BFS 借助队列做到一次一步「齐头并进」，是可以在不遍历完整棵树的条件下找到最短距离的。
//BFS 可以找到最短距离，但是空间复杂度高，而 DFS 的空间复杂度较低。
//DFS 最坏情况下顶多就是树的高度，也就是 O(logN) BFS最坏情况下空间复杂度应该是树的最底层节点的数量，也就是 N/2，
// 用 Big O 表示的话也就是 O(N)。
}

// 112. Path Sum
public class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false; // root to leaf
        if(root.left == null && root.right == null && sum - root.val == 0) return true;
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
//层层递进 但是每一层值减少 而且每次只能选一边。
    }
}

// 113 path sum II
public class Solution {
public List<List<Integer>> pathSum(TreeNode root, int sum){
	List<List<Integer>> result  = new LinkedList<List<Integer>>();
	List<Integer> currentResult  = new LinkedList<Integer>();
	pathSum(root,sum,currentResult,result);
	return result;
}

public void pathSum(TreeNode root, int sum, List<Integer> currentResult, List<List<Integer>> result) {
	if (root == null)
		return;
	currentResult.add(new Integer(root.val));
	if (root.left == null && root.right == null && sum == root.val) {
		result.add(new LinkedList(currentResult));
		currentResult.remove(currentResult.size() - 1);//don't forget to remove the last integer
		return;
	} else {
		pathSum(root.left, sum - root.val, currentResult, result);
		pathSum(root.right, sum - root.val, currentResult, result);
	}
	currentResult.remove(currentResult.size() - 1);
}
}

// 257. Binary Tree Paths
public class Solution {
//Recursion
public List<String> binaryTreePaths(TreeNode root) {
    List<String> sList=new LinkedList<String>();
    //String s=new String();
    if (root==null) return sList;
    if (root.left==null && root.right==null) {
        sList.add(Integer.toString(root.val));
        return sList;
    }
    for (String s: binaryTreePaths(root.left)) {
        sList.add(Integer.toString(root.val)+"->"+s);
    }
    for (String s: binaryTreePaths(root.right)) {
        sList.add(Integer.toString(root.val)+"->"+s);
    }
    return sList;
}
}

public class Solution {
//DFS - Stack
public List<String> binaryTreePaths(TreeNode root) {
    List<String> list=new ArrayList<String>();
    Stack<TreeNode> sNode=new Stack<TreeNode>();
    Stack<String> sStr=new Stack<String>();
    if(root==null) return list;
    sNode.push(root);
    sStr.push("");
    while(!sNode.isEmpty()) {
        TreeNode curNode=sNode.pop();
        String curStr=sStr.pop();      
        if(curNode.left==null && curNode.right==null) list.add(curStr+curNode.val);
        if(curNode.left!=null) {
            sNode.push(curNode.left);
            sStr.push(curStr+curNode.val+"->");
        }
        if(curNode.right!=null) {
            sNode.push(curNode.right);
            sStr.push(curStr+curNode.val+"->");
        }
    }
    return list;
}
}

// 108. Convert Sorted Array to Binary Search Tree
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return BSTHelper(nums, 0, nums.length - 1);
    }
    
    public TreeNode BSTHelper(int[] nums, int left, int right) {
        if (left > right) return null;
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = BSTHelper(nums, left, mid - 1);
        root.right = BSTHelper(nums, mid + 1, right);
        return root;
        
    } 
    
}

//297. Serialize and Deserialize Binary Tree
public class Codec {

    // Encodes a tree to a single string.
    public static final String spliter = ",";
    public static final String nullval = "X";
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if (root == null) return sb.append(nullval).toString();
        serializeHelper(root,sb);
        return sb.toString();
    }
    
    public void serializeHelper(TreeNode root, StringBuilder sb){
        if (root == null){
            sb.append(nullval).append(spliter);
            return;
        } 
        sb.append(root.val).append(spliter); // put val infront of spliter
        serializeHelper(root.left, sb); 
        serializeHelper(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>(); 
        queue.addAll(Arrays.asList(data.split(spliter))); // remember how to convert array to list
        return deserializeHelper(queue);
        
    }
    
    public TreeNode deserializeHelper(Queue<String> queue){
        String rootVal = queue.poll();
        if (rootVal.equals(nullval)){ //don't forget this way to return null
            return null;
        } else {
            TreeNode root = new TreeNode(Integer.valueOf(rootVal));
            root.left = deserializeHelper(queue);
            root.right = deserializeHelper(queue);                               
            return root;
        }  
    }
}

// 100. Same Tree
class Solution {
    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left,q.left) && isSameTree(p.right,q.right);
    }
}

// 654. Maximum Binary Tree
class Solution {
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) return null;
        return constructHelper(nums, 0 , nums.length - 1);
    }
    
    public TreeNode constructHelper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int indexMax = left; // not 0; 
        for (int i = left + 1; i <= right; i ++) { // i start with left + 1
            if (nums[indexMax] < nums[i]){
                indexMax = i;
            }
        }
        TreeNode root = new TreeNode(nums[indexMax]);
        root.left = constructHelper(nums, left, indexMax - 1);
        root.right = constructHelper(nums, indexMax + 1, right);
        return root;
    }
}

// 124. Binary Tree Maximum Path Sum
// https://leetcode-cn.com/problems/binary-tree-maximum-path-sum/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-30/
class Solution {
   int max = Integer.MIN_VALUE;
public int maxPathSum(TreeNode root) {
    helper(root);
    return max;
} 
int helper(TreeNode root) {
    if (root == null) return 0;
    int left = Math.max(helper(root.left), 0);
    int right = Math.max(helper(root.right), 0);
    //求的过程中考虑包含当前根节点的最大路径
    max = Math.max(max, root.val + left + right);
    //只返回包含当前根节点和左子树或者右子树的路径
    return root.val + Math.max(left, right);
}
//这道题最妙的地方就是在递归中利用全局变量，来更新最大路径的值
//不断更新max为val+left+right. 但每次返回的时候只返回 root.val + max(left,right).
/* 假如包含了根的情况：
      8
    /  \
   -3   7
 /    \
1      4
 \    / \    
  3  2   6

考虑左子树 -3 的路径的时候，我们有左子树 1 和右子树 4 的选择，但我们不能同时选择
如果同时选了，路径就是 ... -> 1 -> -3 -> 4 -> ... 就无法通过根节点 8 了
所以我们只能去求左子树能返回的最大值，右子树能返回的最大值，选一个较大的*/

//95. Unique Binary Search Trees II
//所以如果求 1...n 的所有可能。 
// 我们只需要把 1 作为根节点，[ ] 空作为左子树，[ 2 ... n ] 的所有可能作为右子树。 
// 2 作为根节点，[ 1 ] 作为左子树，[ 3...n ] 的所有可能作为右子树。
// 3 作为根节点，[ 1 2 ] 的所有可能作为左子树，[ 4 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
// 4 作为根节点，[ 1 2 3 ] 的所有可能作为左子树，[ 5 ... n ] 的所有可能作为右子树，然后左子树和右子树两两组合。
// ...
// n 作为根节点，[ 1... n ] 的所有可能作为左子树，[ ] 作为右子树。

class Solution {
  public LinkedList<TreeNode> generate_trees(int start, int end) {
    LinkedList<TreeNode> all_trees = new LinkedList<TreeNode>();
    if (start > end) {
      all_trees.add(null);
      return all_trees;
    }
    // pick up a root
    for (int i = start; i <= end; i++) {
      // all possible left subtrees if i is choosen to be a root
      LinkedList<TreeNode> left_trees = generate_trees(start, i - 1);
      // all possible right subtrees if i is choosen to be a root
      LinkedList<TreeNode> right_trees = generate_trees(i + 1, end);
      // connect left and right trees to the root i
      for (TreeNode l : left_trees) {
        for (TreeNode r : right_trees) {
          TreeNode current_tree = new TreeNode(i);
          current_tree.left = l;
          current_tree.right = r;
          all_trees.add(current_tree);
        }
      }
    }
    return all_trees;
  }

  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new LinkedList<TreeNode>();
    }
    return generate_trees(1, n);
  }
}
}
// 96. Unique Binary Search Trees
class Solution {
public int numTrees(int n) { 
    if (n == 0) {
        return 0;
    }
    return getAns(1, n);

} 
private int getAns(int start, int end) { 
    int ans = 0;
    //此时没有数字，只有一个数字,返回 1
    if (start >= end) { 
        return 1;
    } 
    //尝试每个数字作为根节点
    for (int i = start; i <= end; i++) {
        //得到所有可能的左子树
        int leftTreesNum = getAns(start, i - 1);
        //得到所有可能的右子树
        int rightTreesNum  = getAns(i + 1, end);
        //左子树右子树两两组合
        ans+=leftTreesNum * rightTreesNum;
    }
    return ans;
}
}

// 110. Balanced Binary Tree
class Solution {
public boolean isBalanced(TreeNode root) {
    //它是一棵空树
    if (root == null) {
        return true;
    }
    //它的左右两个子树的高度差的绝对值不超过1
    int leftDepth = getTreeDepth(root.left);
    int rightDepth = getTreeDepth(root.right);
    if (Math.abs(leftDepth - rightDepth) > 1) {
        return false;
    }
    //左右两个子树都是一棵平衡二叉树
    return isBalanced(root.left) && isBalanced(root.right);
}

private int getTreeDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int leftDepth = getTreeDepth(root.left);
    int rightDepth = getTreeDepth(root.right);
    return Math.max(leftDepth, rightDepth) + 1;
}

//solution2:
public boolean isBalanced(TreeNode root) {
    return getTreeDepth(root) != -1;
}

private int getTreeDepth(TreeNode root) {
    if (root == null) {
        return 0;
    }
    int leftDepth = getTreeDepth(root.left);
    if (leftDepth == -1) {
        return -1;
    }
    int rightDepth = getTreeDepth(root.right);
    if (rightDepth == -1) {
        return -1;
    }
    if (Math.abs(leftDepth - rightDepth) > 1) {
        return -1;
    }
    return Math.max(leftDepth, rightDepth) + 1;
}

}

// 	99 Recover Binary Search Tree
/*我们判断是否是一个合法的二分查找树是使用到了中序遍历。原因就是二分查找树的一个性质，左孩子小于根节点，根节点小于右孩子
所以做一次中序遍历，产生的序列就是从小到大排列的有序序列。
回到这道题，题目交换了两个数字，其实就是在有序序列中交换了两个数字。而我们只需要把它还原。
交换的位置的话就是两种情况。
相邻的两个数字交换
[ 1 2 3 4 5 ] 中 2 和 3 进行交换，[ 1 3 2 4 5 ]，这样的话只产生一组逆序的数字（正常情况是从小到大排序，交换后产生了从大到小），3 2。
我们只需要遍历数组，找到后，把这一组的两个数字进行交换即可。
不相邻的两个数字交换
[ 1 2 3 4 5 ] 中 2 和 5 进行交换，[ 1 5 3 4 2 ]，这样的话其实就是产生了两组逆序的数字对。5 3 和 4 2。
所以我们只需要遍历数组，然后找到这两组逆序对，然后把第一组前一个数字和第二组后一个数字进行交换即完成了还原。
所以在中序遍历中，只需要利用一个 pre 节点和当前节点比较，如果 pre 节点的值大于当前节点的值，那么就是我们要找的逆序的数字。
分别用两个指针 first 和 second 保存即可。
如果找到第二组逆序的数字，我们就把 second 更新为当前节点。最后把 first 和 second 两个的数字交换即可。*/
class Solution {
TreeNode first = null;
TreeNode second = null;
public void recoverTree(TreeNode root) {
    inorderTraversal(root);
    int temp = first.val;
    first.val = second.val;
    second.val = temp;
}
TreeNode pre = null;
private void inorderTraversal(TreeNode root) {
    if (root == null) {
        return;
    }
    inorderTraversal(root.left); 
    /*******************************************************/
    if(pre != null && root.val < pre.val) {
        //第一次遇到逆序对
        if(first==null){
            first = pre;
            second = root;
        //第二次遇到逆序对
        }else{
            second = root;
        }
    }
    pre = root; 
    /*******************************************************/
    inorderTraversal(root.right);
}
}

//use stack:
class Solution {
TreeNode first = null;
TreeNode second = null;
public void recoverTree(TreeNode root) {
    inorderTraversal(root);
    int temp = first.val;
    first.val = second.val;
    second.val = temp;
}
public void inorderTraversal(TreeNode root) {
    if (root == null)
        return;
    Stack<TreeNode> stack = new Stack<>();
    TreeNode pre = null;
    while (root != null || !stack.isEmpty()) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        root = stack.pop();
        /*******************************************************/
        if (pre != null && root.val < pre.val) {
            if (first == null) {
                first = pre;
                second = root;
            } else {
                second = root;
            }
        }
        pre = root;
        /*******************************************************/
        root = root.right;
    }
}
}
//669. Trim a Binary Search Tree
class Solution {
    public TreeNode trimBST(TreeNode root, int L, int R) {
         if (root == null) return root;
        //太大 修剪，包括右子树全部砍掉
        if (root.val > R) return trimBST(root.left, L, R);
        //太小 修剪 ，包括左子树全部砍掉
        if (root.val < L) return trimBST(root.right, L, R);
        //没问题的修剪左子树
        root.left = trimBST(root.left, L, R);
        //没问题的修剪右子树
        root.right = trimBST(root.right, L, R);
        return root;
//Time Complexity: O(N)O(N), where NN is the total number of nodes in the given tree. We visit each node at most once.
//Space Complexity: O(N)O(N). Even though we don't explicitly use any additional memory, 
//the call stack of our recursion could be as large as the number of nodes in the worst case.
    }
}
// 235. Lowest Common Ancestor of a Binary Search Tree
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root.val > p.val && root.val > q.val){
            return lowestCommonAncestor(root.left, p, q);
        }else if(root.val < p.val && root.val < q.val){
            return lowestCommonAncestor(root.right, p, q);
        }else{
        // 说明要么其中一个和ROOT相等 或者两个处于左右两边
            return root;
        }
    }
}
//236. Lowest Common Ancestor of a Binary Tree
class Solution {
   public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if( root == p || root == q || root == null)
            return root;
        TreeNode left = lowestCommonAncestor( root.left,  p,  q);
        TreeNode right = lowestCommonAncestor( root.right,  p,  q);
        //在左子树中没有找到，那一定在右子树中
        if(left == null)
            return right;
        else if (right == null)//在右子树中没有找到，那一定在左子树中
            return left;
        else
            return root; //  返回的答案一定是 右边的某个根节点或者左边的某个根节点 或者就是根 三种情况之一
        
    }
}

// 979. Distribute Coins in Binary Tree
/*方法：深度优先搜索
如果树的叶子仅包含 0 枚金币（与它所需相比，它的 过载量 为 -1），那么我们需要从它的父亲节点移动一枚金币到这个叶子节点上。
如果说，一个叶子节点包含 4 枚金币（它的 过载量 为 3），那么我们需要将这个叶子节点中的 3 枚金币移动到别的地方去。
总的来说，对于一个叶子节点，需要移动到它中或需要从它移动到它的父亲中的金币数量为 过载量 = Math.abs(num_coins - 1)。
然后，在接下来的计算中，我们就再也不需要考虑这些已经考虑过的叶子节点了。
我们可以用上述的方法来逐步构建我们的最终答案。定义 dfs(node) 为这个节点所在的子树中金币的 过载量，
也就是这个子树中金币的数量减去这个子树中节点的数量。接着，我们可以计算出这个节点与它的子节点之间需要移动金币的数量为 abs(dfs(node.left)) + abs(dfs(node.right))，
这个节点金币的过载量为 node.val + dfs(node.left) + dfs(node.right) - 1。*/
class Solution {
    int ans;
    public int distributeCoins(TreeNode root) {
        ans = 0;
        dfs(root);
        return ans;
    }

    public int dfs(TreeNode node) {
        if (node == null) return 0;
        int L = dfs(node.left);
        int R = dfs(node.right);
        ans += Math.abs(L) + Math.abs(R);
        return node.val + L + R - 1;
    }
}
//173. Binary Search Tree Iterator
class BSTIterator {
    Queue<Integer> queue; // need ;
    public BSTIterator(TreeNode root) {
        queue  = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node= stack.pop();
            queue.offer(node.val);
            cur = node.right; // must be node.right not node.left or cur.right
        }
    }
    
    /** @return the next smallest number */
    public int next() {
            return queue.poll();
    }
    
    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !queue.isEmpty();
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

```
