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
            kthSmallest(node.right);
        }
    }
```