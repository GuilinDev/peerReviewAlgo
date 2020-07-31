LC98，最简单应该就是中序遍历了
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    # 中序遍历
    pre = float('-inf')
    def isValidBST(self, root: TreeNode) -> bool:
        if not root:
            return True
        
        # 先递归到最左边的叶子节点
        if not self.isValidBST(root.left):
            return False
        
        if root.val <= self.pre:
            return False
        
        self.pre = root.val
        
        return self.isValidBST(root.right)
```
