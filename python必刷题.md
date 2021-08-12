## 94. Binary Tree Inorder Traversal
Iterative
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        result, stack = [], [root]
        while len(stack) > 0:
            cur = stack.pop()
            if not cur:
                continue
            stack.append(cur.right)
            if not cur.left and not cur.right:
                result.append(cur.val)
            else: # add lower right most first
                stack.append(TreeNode(cur.val))
            stack.append(cur.left)
        return result
```
recursive
```python
class Solution:
    def inorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        result = []
        def helper(cur: Optional[TreeNode]):
            if not cur:
                return
            helper(cur.left)
            result.append(cur.val)
            helper(cur.right)
        helper(root)
        return result
```
## 144. Binary Tree Preorder Traversal
```python
class Solution:
    def preorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        result, stack = [], [root]
        while len(stack) > 0:
            cur = stack.pop()
            if not cur:
                continue
            stack.append(cur.right)
            stack.append(cur.left)
            # if not cur.left and not cur.right:
            #     result.append(cur.val)
            # else:
            #     stack.append(TreeNode(cur.val))
            result.append(cur.val)
        return result
```
## 145. Binary Tree Postorder Traversal
```python
class Solution:
    def postorderTraversal(self, root: Optional[TreeNode]) -> List[int]:
        result, stack = [], [root]
        while len(stack) > 0:
            cur = stack.pop()
            if not cur:
                continue
            
            if not cur.left and not cur.right:
                result.append(cur.val)
            else:
                stack.append(TreeNode(cur.val))
                
            stack.append(cur.right)
            stack.append(cur.left)
        return result
```
