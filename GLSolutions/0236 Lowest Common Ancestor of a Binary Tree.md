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
        if (root == null || root == p || root == q) {//基线条件，一直递归到null或者递归到p或者q
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        
        if (left != null && right != null) { //root一边一个，返回root
            return root;
        }
        
        return left != null ? left : right;//p和q都在同一边，返回不为null的那一边
    }
}
```

## Solution 2
> 迭代