class ListNode:
    def __init__(self, x):
        self.val = x
        self.next = None


def getNode(head, n, i):
    """
    :param head: 链表头结点
    :param n: 链表长度
    :param i: 返回的第i个结点node i
    :return:
    """
    if head is None:
        return None
    if i >= n:
        return None
    if i == 0:
        return head

    return getNode(head.next, n, i - 1)


#  创建一个链表
node = ListNode(0)
first = ListNode(10)
node.next = first
second = ListNode(20)
first.next = second
third = ListNode(30)
second.next = third
fourth = ListNode(40)
third.next = fourth
fifth = ListNode(50)
fourth.next = fifth

v = getNode(first, 6, 3)
print(v)
