LC 235 Lowest Common Ancestor of a Binary Search Tree
递归

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None

class Solution:
    def lowestCommonAncestor(self, root: 'TreeNode', p: 'TreeNode', q: 'TreeNode') -> 'TreeNode':
        if not root:
            return None
        
        if root.val < min(p.val, q.val): # 比二者较小的小，查找右子树
            return self.lowestCommonAncestor(root.right, p, q)
        elif root.val > max(p.val, q.val): # 比二者较大的大，查找左子树
            return self.lowestCommonAncestor(root.left, p, q)
        else: # p,q分别在当前节点的左右子树
            return root

```
