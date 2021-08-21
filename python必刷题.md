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
## 429. N-ary Tree Level Order Traversal
```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""

class Solution:
    def levelOrder(self, root: 'Node') -> List[List[int]]:
        if not root:
            return []
        result, queue = [], [root]
        while queue:
            curLevel = []
            curLevelChildren = []
            for i in queue:
                curLevel.append(i.val)
                for j in i.children:
                    if j:
                        curLevelChildren.append(j)
            
            result.append(curLevel)
            queue = curLevelChildren[:]
        return result
                
```
## 589. N-ary Tree Preorder Traversal
Iterative
```python
"""
# Definition for a Node.
class Node:
    def __init__(self, val=None, children=None):
        self.val = val
        self.children = children
"""

class Solution:
    def preorder(self, root: 'Node') -> List[int]:
        result, stack = [], [root]
        while stack:
            cur = stack.pop()
            if not cur:
                continue
            if cur.children: # null check
                for i in cur.children[::-1]: #reversed(cur.children)
                    stack.append(i)
            if not cur.children:
                result.append(cur.val)
            else:
                stack.append(Node(cur.val))
        return result
```

Recursive
```python
class Solution:
    def preorder(self, root: 'Node') -> List[int]:
        result = []
        def helper(cur: 'Node'):
            if not cur:
                return
            result.append(cur.val)
            for i in cur.children:
                helper(i)
        helper(root)
        return result
```
## 590. N-ary Tree Postorder Traversal
```python
class Solution:
    def postorder(self, root: 'Node') -> List[int]:
        result, stack = [], [root]
        while stack:
            cur = stack.pop()
            if not cur:
                continue
            if not cur.children:
                result.append(cur.val)
            else:
                stack.append(Node(cur.val))
            if cur.children:
                for i in reversed(cur.children):
                    stack.append(i)
        return result
```
## 987. Vertical Order Traversal of a Binary Tree
> 优先级： 列号从小到大 -> 同列的，行号从小到大（这时还没排序） -> 同列同行的，按照数字的大小来排序， 所以用三元组(col, row, val)来进行排序
> 先遍历一遍形成每个节点的三元组，然后比较大小，可用Hashmap再排序，或使用优先队列
> 也可以直接一次DFS的时候同时做三元组的映射
```python
# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right
class Solution:
    def verticalTraversal(self, root: Optional[TreeNode]) -> List[List[int]]:
        hashmap = dict() # node: [col, row, val]
        def dfs(node: Optional[TreeNode]):
            if not node:
                return
            col, row, val = hashmap[node]
            if node.left:
                hashmap[node.left] = [col - 1, row + 1, node.left.val]
                dfs(node.left)
            if node.right:
                hashmap[node.right] = [col + 1, row + 1, node.right.val]
                dfs(node.right)
        
        hashmap[root] = [0, 0, root.val]
        dfs(root)
        it = sorted(hashmap.values()) # lambda x:[x[0],x[1],x[2]]
        l = len(hashmap)
        pos = 0 # 同一列的所有元素的下一个位置
        result = []
        while pos < l:
            oneColumn = []
            idx = pos
            while idx < l and it[idx][0] == it[pos][0]:
                oneColumn.append(it[idx][2])
                idx += 1
            result.append(oneColumn)
            pos = idx
        return result
```

## 2. Add Two Numbers
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def addTwoNumbers(self, l1: Optional[ListNode], l2: Optional[ListNode]) -> Optional[ListNode]:
        if not l1:
            return l2
        elif not l2:
            return l1
        
        carry = 0
        dummy = ListNode(-1)
        idx = dummy
        
        while l1 or l2:
            tempSum = carry
            if l1:
                tempSum += l1.val
                l1 = l1.next
            if l2:
                tempSum += l2.val
                l2 = l2.next
            idx.next = ListNode(tempSum % 10)
            idx = idx.next
            carry = tempSum // 10
            
        if carry == 1:
            idx.next = ListNode(carry)
        
        return dummy.next
```
## 24. Swap Nodes in Pairs
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head
        dummy = ListNode(-1)
        dummy.next = head
        idx = dummy
        
        while idx.next and idx.next.next:
            first = idx.next
            second = idx.next.next
            
            first.next = second.next
            idx.next = second
            
            # idx.next.next = first
            second.next = first
            
            idx = idx.next.next
            
        return dummy.next
            
```

```python
class Solution:
    def swapPairs(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head
        second = head.next
        head.next = self.swapPairs(head.next.next)
        second.next = head
        
        return second
```

## 206. Reverse Linked List
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head
        first, second = None, head
        
        while second:
            temp = second.next
            second.next = first
            first = second
            second = temp
            
        return first
```
```python
class Solution:
    def reverseList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head
        result = self.reverseList(head.next)
        head.next.next = head
        head.next = None
        return result
```
## 141. Linked List Cycle
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def hasCycle(self, head: ListNode) -> bool:
        if not head or not head.next:
            return False
        slow = head
        fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                return True
        return False
```

## 23. Merge k Sorted Lists
```python

```

## 147. Insertion Sort List
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def insertionSortList(self, head: Optional[ListNode]) -> Optional[ListNode]:
        if not head or not head.next:
            return head
        dummy = ListNode(-1)
        dummy.next = head
        
        sortedRight = head
        unsortedLeft = head.next
        
        while unsortedLeft:
            insert = dummy 
            while unsortedLeft.val > insert.next.val and insert != sortedRight:
                insert = insert.next
            if insert == sortedRight:
                sortedRight = sortedRight.next
                unsortedLeft = unsortedLeft.next
            else:
                sortedRight.next = sortedRight.next.next
                temp = insert.next
                insert.next = unsortedLeft
                unsortedLeft.next = temp
                
                unsortedLeft = sortedRight.next              
        
        return dummy.next
```
