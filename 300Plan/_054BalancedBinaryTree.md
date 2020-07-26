朴素的递归解法
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def isBalanced(self, root: TreeNode) -> bool:
        def findMaxDepth(node: TreeNode) -> int:
            if not node:
                return 0
            return 1 + max(findMaxDepth(node.left), findMaxDepth(node.right))
        
        if not root:
            return True
        left = findMaxDepth(root.left)
        right = findMaxDepth(root.right)
        
        return abs(left - right) <= 1 and self.isBalanced(root.left) and self.isBalanced(root.right)
```

求树深度的时候进行记忆化搜索
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def __init__(self):
            self.memo = {} # 记忆化
    def isBalanced(self, root: TreeNode) -> bool:        
        def findMaxDepth(node: TreeNode) -> int:
            if not node:
                return 0
            if node in self.memo:
                return self.memo[node]
            self.memo[node] = 1 + max(findMaxDepth(node.left), findMaxDepth(node.right))
            return self.memo[node]
        
        if not root:
            return True
        
        left = findMaxDepth(root.left)
        right = findMaxDepth(root.right)
        
        return abs(left - right) <= 1 and self.isBalanced(root.left) and self.isBalanced(root.right)
```
