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
        int right = minDepth(root.right);
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

public void pathSum(TreeNode root, int sum, List<Integer> currentResult,
		List<List<Integer>> result) {
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






```
