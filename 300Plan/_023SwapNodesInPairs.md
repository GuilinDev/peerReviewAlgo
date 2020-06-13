递归，空间复杂度O(n)
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        if not head:
            return None
        if not head.next:
            return head
        
        newHead = head.next # 每轮递归，直到最后一个node
        head.next = self.swapPairs(head.next.next) # 每轮递归两两一组，方便递归里“归”的时候进行交换
        newHead.next = head # 上一步已将前面的node指向后面，这步将后面的node指向前面
        
        return newHead                                                                 
        
```
迭代，空间复杂度O(1)
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next
class Solution:
    def swapPairs(self, head: ListNode) -> ListNode:
        dummy = ListNode()
        dummy.next = head
        current = dummy
        
        # 两个一组交换
        while current.next and current.next.next:
            first = current.next
            second = current.next.next
            first.next = second.next
            
            current.next = second
            current.next.next = first
            
            current = current.next.next
            
        return dummy.next
        
```
