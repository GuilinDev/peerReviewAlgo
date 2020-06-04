# Definition for singly-linked list.
class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


class Solution1:
    def reverseList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head

        prev = None
        temp = None
        while head is not None:
            temp = head.next  # 先记住下一轮待翻转的节点位置
            head.next = prev  # 翻转

            # 两个索引分别向后挪动一步
            prev = head;
            head = temp

        return prev  # 此时head在翻转后第一个节点前面的None处


class Solution2:
    def reverseList(self, head: ListNode) -> ListNode:
        if head is None or head.next is None:
            return head

        result = self.reverseList(head.next)  # 头部递归直到result是最后一个节点

        # 开始按递归栈依次翻转当前节点和前一个节点
        head.next.next = head
        head.next = None

        return result
