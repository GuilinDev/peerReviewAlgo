## Solution 1
> 先序的顺序第一个肯定是root，所以二叉树的根结点可以确定，由于题目中说了没有相同的元素，所以利用先序的根，遍历中序遍历，直到找到和根节点值相同的位置。则此元素左边的都是根节点的左子树的元素，右边的都是根节点右子树的元素。然后递归调用就可以重建二叉树.

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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }

        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree (int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRight) {
        if (pLeft > pRight || iLeft > iRight) { // 基线条件避免死循环
            return null;
        }

        // 1.根据先序当前的左边结点是root的特性重建根结点
        TreeNode current = new TreeNode(preorder[pLeft]);

        // 2.在中序中寻找重建的根结点的值
        int index = 0;
        for (index = iLeft; index <= iRight; index++) {
            if (inorder[index] == preorder[pLeft]) {
                break;
            }
        }

        // 3.根据在中序中找到的root的左边是左子树，root的右边是右子树的特性，递归重建左右子树

        // 左子树，前序的左边不算root，到index处；中序的左边一直到找到的root（不含）处
        current.left = buildTree(preorder, pLeft + 1, pLeft + index - iLeft, inorder, iLeft, index - 1);
        // 右子树，从index到最右边
        current.right = buildTree(preorder, pLeft + index - iLeft + 1, pRight, inorder, index + 1, iRight);

        return current;
    }
}
```

上面的解法在第2步的时候，每次根据前序的root在中序中寻找相关的index的时候，线性查找，可以用一个额外的HashMap来保存中序遍历数组中的每个元素的值和下标，通过散列表的直接查找O（1）
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
    HashMap<Integer, Integer> map;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0 || inorder == null || inorder.length == 0 || preorder.length != inorder.length) {
            return null;
        }
        map = new HashMap<Integer, Integer>();
        // 注意没有重复值，所以可以用中序的元素当作key
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
    }

    private TreeNode buildTree (int[] preorder, int pLeft, int pRight, int[] inorder, int iLeft, int iRight) {
        if (pLeft > pRight || iLeft > iRight) { // 基线条件避免死循环
            return null;
        }

        // 1.根据先序当前的左边结点是root的特性重建根结点
        TreeNode current = new TreeNode(preorder[pLeft]);

        // 2.在中序中寻找重建的根结点的值
        int index = map.get(preorder[pLeft]);

        // 3.根据在中序中找到的root的左边是左子树，root的右边是右子树的特性，递归重建左右子树

        // 左子树，前序的左边不算root，到index处；中序的左边一直到找到的root（不含）处
        current.left = buildTree(preorder, pLeft + 1, pLeft + index - iLeft, inorder, iLeft, index - 1);
        // 右子树，从index到最右边
        current.right = buildTree(preorder, pLeft + index - iLeft + 1, pRight, inorder, index + 1, iRight);

        return current;
    }
}
```

```java

```