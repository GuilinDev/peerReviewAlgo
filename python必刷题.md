## 94. Binary Tree Inorder Traversal
```python
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
