后序遍历+全局变量
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def findTilt(self, root: TreeNode) -> int:
        self.tilt = 0
        def postOrder(node: TreeNode) -> int:
            # 后序遍历每个节点
            if not node:
                return 0
            left = postOrder(node.left)
            right = postOrder(node.right)
            self.tilt += abs(left - right) # 每一轮递归更新坡度
            return left + right + node.val
        postOrder(root)
        return self.tilt
        
```
