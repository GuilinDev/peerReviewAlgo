选择一种遍历方式DFS即可
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isUnivalTree(self, root: TreeNode) -> bool:
        def inOrder(node):
            return not node or node.val == root.val and inOrder(node.left) and inOrder(node.right)
        
        return inOrder(root)
```
