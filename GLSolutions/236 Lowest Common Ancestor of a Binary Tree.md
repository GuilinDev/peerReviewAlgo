## Solution 1
> 递归
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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || p == root || q == root) { // 基线条件
            return root;
        }

        // 尝试找p和q，根据基线条件返回
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        // 这时候p，q分别位于root的左右子树
        if (left != null && right != null) { 
            return root;
        }

        // 这时候left或者right之一是root，返回不为null的那个
        return left != null ? left : right;
    }
}
```