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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        return isSymmetricLeftRight(root.left, root.right);
    }
    private boolean isSymmetricLeftRight(TreeNode treeLeft, TreeNode treeRight) {
        if (treeLeft == null && treeRight == null) { //基线条件1
            return true;
        } else if (treeLeft == null || treeRight == null) { // 基线条件2
            return false;
        } 
        if (treeLeft.val != treeRight.val) { // 对比左右当前值
            return false;
        }
        return isSymmetricLeftRight(treeLeft.left, treeRight.right) && isSymmetricLeftRight(treeLeft.right, treeRight.left);
    }
}
```

## Solution 2
> 迭代，用queue来挨个比较

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
    public boolean isSymmetric(TreeNode root) {
        if (root == null) {
            return true;
        }
        //这里用一个Queue也是可以的，每次while循环依次取出和加入就可以了，为了方便阅读用了两个Queue
        Queue<TreeNode> q1 = new LinkedList<>();
        Queue<TreeNode> q2 = new LinkedList<>();
        q1.offer(root.left);
        q2.offer(root.right);

        while (!q1.isEmpty() && !q2.isEmpty()) {
            TreeNode left = q1.poll();
            TreeNode right = q2.poll();

            if (left == null && right == null) {
                continue;
            }
            if ((left == null || right == null) || left.val != right.val) {
                return false;
            }

            // 如果null被offer进去，下一轮会不等，所以无需判断null
            q1.offer(left.left);
            q1.offer(left.right);
            q2.offer(right.right);
            q2.offer(right.left);
        }
        return true;
    }
}
```