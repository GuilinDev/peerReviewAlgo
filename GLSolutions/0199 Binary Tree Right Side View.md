树的形状无法提前知晓，所以无法设计优于O(n),注意如果右边的子树没有了，是可以看到左子树的

## Solution 1
> DFS

```java
```

## Solution 2
> BFS

```java
```

## Solution 3
> 先序遍历递归写法

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
 // 设定一个全局变量记录当前插入ans的最大层数，递归先序遍历右子树即可得到二叉树的右视图。
class Solution {
    int level = -1;
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        traversingBinaryTree(root, 0, result);
        return result;
    }

    public void traversingBinaryTree(TreeNode node, int currentLevel, List<Integer> result) {
        if (node == null) {
            return;
        }
        if (currentLevel > level) {
            level = currentLevel;
            result.add(node.val);
        }
        // 先看右子树 再看左子树
        traversingBinaryTree(node.right, currentLevel + 1, result);
        traversingBinaryTree(node.left, currentLevel + 1, result);
    }
}
```