## Solution 1
> 与100 - Same Tree类似，从根节点比较是否相同+递归左右子树比较，对树递归掌握要熟练
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (t == null) {
            return true;
        }
        if (s == null) { // 此时t不会等于null
            return false;
        }

        // 本身为根的子树进行对比，看是否为相同的树
        // 递归左右孩子树继续找
        return isSameTree(s, t) ||isSubtree(s.left, t) || isSubtree(s.right, t) ;
    }
    private boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) { // 比较当前根的值
            return false;
        }

        // 递归比较左右孩子
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);
    }
}
```