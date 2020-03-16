## Solution 1
> 先递归左边，检查是否有k个，找到就返回，没找到再递归右边

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
        int count;
        int num = 0;
        public int kthSmallest(TreeNode root, int k) {
            count = k;
            kthSmallest(root);
            return num;
        }
        private void kthSmallest(TreeNode node) {
            if (node == null) {
                return;
            }
            kthSmallest(node.left);
            count--;
            if (count == 0) {
                num = node.val;
            }
            //左子树已经递归完还没找到
            kthSmallest(node.right);
        }
    }
```

## Solution 2
> 迭代使用自己建的Stack来模拟上面递归的过程

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
        public int kthSmallest(TreeNode root, int k) {
            Stack<TreeNode> stack = new Stack<>();
            TreeNode current = root;
            while (current != null || !stack.isEmpty()) {
                //先检查BST的左子树
                while (current != null) {
                    stack.push(current);
                    current = current.left;
                }
                //弹出当前的栈顶元素并减去k一次，检查k的值，是0即为所找元素
                current = stack.pop();
                k--;
                if (k == 0) {
                    return current.val; 
                }
                current = current.right; //注意这里的current可能是null
            }
            throw new IllegalArgumentException("There is now kth smallest element.");
        }
    }
```