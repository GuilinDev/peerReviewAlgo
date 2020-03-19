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
            return;
        }
        if(left < n){
        generateParenthesisHelper(left + 1, right, n, res, s + "("); // current logic is s1 = s+ "("; and pass s1 to next level
        }
        if(right < left){
        generateParenthesisHelper(left, right + 1, n, res, s + ")");// current logic is s2 = s + ")"; and pass s2 to next level
            };
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



```
