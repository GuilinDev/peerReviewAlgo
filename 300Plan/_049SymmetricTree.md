Leetcode 101，需要比较n1和n2左右两个节点，就需要比较n1的左节点-n2的右节点，和n1的右节点-n2的左节点
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isSymmetric(self, root: TreeNode) -> bool:
        if not root:
            return True
        
        def isSymm(left: TreeNode, right: TreeNode):
            if not left and not right:
                return True
            if not left or not right:
                return False
            if left.val != right.val:
                return False
            return isSymm(left.left, right.right) and isSymm(left.right, right.left)
        
        return isSymm(root.left, root.right)
```
