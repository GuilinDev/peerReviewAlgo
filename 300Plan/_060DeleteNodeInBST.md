LC450 按照题目要求O(h), h为树的深度

```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def deleteNode(self, root: TreeNode, key: int) -> TreeNode:
        
        def findMin(node: TreeNode):
            while node.left:
                node = node.left
            return node
        
        def findKeyAndDelete(node: TreeNode, key: int) -> TreeNode:
            if not node:
                return None
            if key < node.val: # 递归查找并更新当前节点的左子树
                node.left = findKeyAndDelete(node.left, key)
            elif key > node.val: # 递归查找并更新当前节点的右子树
                node.right = findKeyAndDelete(node.right, key)
            else: # 已找到待删除
                """
                处理四种情况：
                1. 待删除节点没有left和right，返回null，表示直接就删除了
                2. 待删除节点只有left没有right，返回left，表示直接接上left
                3. 待删除节点没有left只有right，返回right，表示直接接上right
                4. 待删除节点既有left又有right，返回right中的最小节点，根据BST特征这个最小节点应该是替代被删除的节点
                """
                if not node.right: # 情况1和2
                    return node.left
                elif not node.left: # 情况3
                    return node.right
                
                # 情况4，当前节点有左右子树，删除的操作是将当前待删除节点替换成右子树中的最小值，然后递归删除右子树中的最小值
                minNodeInRight = findMin(node.right)
                node.val = minNodeInRight.val
                node.right = findKeyAndDelete(node.right, minNodeInRight.val)
                
            return node
        return findKeyAndDelete(root, key)
```
