## Solution 1
>  递归

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
        public int countNodes(TreeNode root) {
            if (root == null) {
                return 0;
            }
            
            // 计算当前结点的左右结点各自的height
            int leftLeft = countLeft(root.left);
            int leftRight = countRight(root.left);//注意这里是左子树的右子树
            
            if (leftLeft == leftRight) {// 1^left-1代表2^1的left次方，是完美二叉树
                return 1 + ((1 << leftLeft) - 1) + countNodes(root.right); 
            }
            
            //最后一层有偏左的叶子结点
            assert(leftLeft == leftRight + 1) : "Complete Binary Tree";
            return 1 + ((1 << leftRight) - 1) + countNodes(root.left);      
        }
        
        private int countLeft(TreeNode node) {
            if (node == null) {
                return 0;
            }
            return 1 + countLeft(node.left);
        }
        private int countRight(TreeNode node) {
            if (node == null) {
                return 0;
            }
            return 1 + countRight(node.right);
        }
    }
```