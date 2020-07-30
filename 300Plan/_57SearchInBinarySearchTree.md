LC700

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def searchBST(self, root: TreeNode, val: int) -> TreeNode:
        if not root:
            return None
        current = root
        while (current):
            if current.val == val:
                return current
            elif current.val > val:
                current = current.left
            else:
                current = current.right
        return None
```

递归
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def searchBST(self, root: TreeNode, val: int) -> TreeNode:
        if not root:
            return None
        current = root
        while (current):
            if current.val == val:
                return current
            elif current.val > val:
                return self.searchBST(current.left, val)
            else:
                return self.searchBST(current.right, val)
```
