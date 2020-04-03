```java
//589. N-ary Tree Preorder Traversal
class Solution {
     public List<Integer> preorder(Node root) {
         List<Integer> res = new LinkedList<Integer>();
         if (root == null) return res;
         Stack<Node> stack = new Stack<Node>();
         stack.push(root);
         while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            if (node.children != null && node.children.size() != 0) { 
              for (int i = node.children.size() - 1; i >=0; i--) {  // preOrder, need push from last node to front node
                   Node child = node.children.get(i);
                   stack.push(child);
              }
            }
         }
         return res;
    }
}

// 144. Binary Tree Preorder Traversal
class Solution {
    public List<Integer> preorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> temp = new Stack<TreeNode>(); // need to be TreeNode not Integer
        temp.push(root);
        while (!temp.isEmpty()) {
            TreeNode node = temp.pop(); // TreeNode not node
            res.add(node.val);
            if (node.right != null) {
                temp.push(node.right); // right first.
            }
            if (node.left != null) {
                temp.push(node.left);
            }   
        }
        return res;
    }
}

// 94. Binary Tree Inorder Traversal
class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            cur = node.right; // not cur.right, it will be null in the begin, need to be node.right; 
        }
      return res;  
    }
}
//105. Construct Binary Tree from Preorder and Inorder Traversal
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
         // pleft + (nums of left sub tree) = pleft + 1 + (rootIndex - ileft - 1) ----need to think root itself.       
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
