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
    int result;
    public int diameterOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        result = 1;
        paths(root);
        return result - 1;//边的数量是path中所有结点数量减1
    }
    private int paths(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int L = paths(node.left);
        int R = paths(node.right);
        result = Math.max(result, L + R + 1); //以每个结点为root，计算通过自己root的最长path，与之前递归层比较

        //当前递归层应该返回左右子树中比较大的一个path
        return Math.max(L, R) + 1; //比较左右子树看那个path更长
    }
}
```