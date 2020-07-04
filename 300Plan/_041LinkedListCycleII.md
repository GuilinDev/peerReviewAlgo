先用快慢指针判断该链表是否有环，如果没有环，直接返回null，如果有环(这时候slow和fast是在一个node上)，将fast重新指向head（将slow重新指向head也可以），然后继续走，不过这时候fast每次只走一步，若干步和slow和fast相遇的node就是环的入口
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def detectCycle(self, head: ListNode) -> ListNode:
        slow = head
        fast = head
        while fast and fast.next:
            slow = slow.next
            fast = fast.next.next
            if slow == fast:
                break
                
        if not fast or not fast.next:
            return None
        
        fast = head
        while slow != fast:
            slow = slow.next
            fast = fast.next
        
        return slow # return fast
```

同样的思路，另一种写法
```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def detectCycle(self, head: ListNode) -> ListNode:
        try: # 如果为空，或只有一个结点，或者没环会进入except
            slow = head.next
            fast = head.next.next
            while slow != fast:
                slow = slow.next
                fast = fast.next.next
        except:
            return None
        
        fast = head # 让slow或fast的中的其中一个指向head
        while slow != fast:
            slow = slow.next
            fast = fast.next
        
        return slow # return fast
```
