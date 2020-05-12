## Solution 1
> BFS，经典写法，要掌握

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<>();
        if (root == null) {
            return results;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int oneLevelSize = queue.size(); // 检查每层的元素个数
            List<Integer> oneLevelEle = new ArrayList<>(); // 缓存每层的所有元素
            while (oneLevelSize > 0) {
                TreeNode node = queue.poll();
                oneLevelEle.add(node.val); // 把当前层遍历到的元素存入

                // 检查当前层每个元素的左右孩子
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                oneLevelSize--; //当前层的元素遍历完一个
            }
            results.add(oneLevelEle);
        }
        return results;
    }
}
```

## Solution 2
> 递归写法，先序遍历，给每层创建位置然后填充

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
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> results = new ArrayList<List<Integer>>();
        if (root == null) {
            return results;
        }
        levelOrderHelper(results, root, 0);
        return results;
    }
    private void levelOrderHelper(List<List<Integer>> results, TreeNode current, int level) {
        if (current == null) { // 基线条件
            return;
        }
        if (results.size() == level) { // 按照index，从0开始，给当前层的元素准备动态数组的缓存，当递归level + 1的时候新到一层才会发生
            results.add(new ArrayList<Integer>());
        }
        results.get(level).add(current.val); // 根据level的值填充

        // 尾递归，这是个先序遍历
        levelOrderHelper(results, current.left, level + 1);
        levelOrderHelper(results, current.right, level + 1);
    }
}
```