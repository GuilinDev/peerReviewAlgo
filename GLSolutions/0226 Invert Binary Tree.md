## Solution 1
> 树结构经典的递归解法
```java
    /**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode invertTree(TreeNode root) {
        //(root.left == null && root.right == null)不是必须，主要是看想让递归从哪里返回
        if (root == null || (root.left == null && root.right == null)) { 
            return root;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        
        root.left = right;
        root.right = left;
        
        return root;
    }
}
```

## Solution 2
> 使用Stack进行DFS

## Solution 3
> 使用Queue进行BFS，相当于Levels Order Traversal