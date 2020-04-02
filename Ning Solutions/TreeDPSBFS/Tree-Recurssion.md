```java
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

//98. Validate Binary Search Tree
class Solution { // use mid inorder traversal
 public boolean isValidBST(TreeNode root) {
   if (root == null) return true;
   Stack<TreeNode> stack = new Stack<>();
   TreeNode pre = null; // USE PRE NODE 
   while (root != null || !stack.isEmpty()) {
      while (root != null) {
         stack.push(root);
         root = root.left;
      }
      root = stack.pop();
      if(pre != null && root.val <= pre.val) return false;
      pre = root;
      root = root.right;
     }
     return true;
  }
}

//230. Kth Smallest Element in a BST
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int steps = 1;
        if (root == null) return Integer.MIN_VALUE;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            if(steps == k){
                return node.val;
            } else {
                steps ++;
            }
            cur = node.right;
        }    
        return Integer.MIN_VALUE;
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





```
