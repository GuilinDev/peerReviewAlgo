## Solution 1
> 迭代
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode pre = null;
        ListNode temp = null;

        while (head != null) {
            temp = head.next; //head作为"当前"的结点，先记住“当前”结点的next以防丢失，这通常是链表操作的第一步
            head.next = pre; // 将当前结点的next指向前面
            pre = head; //将pre索引移动一步到“当前”结点
            head = temp; //“当前”结点向下一个结点挪动一步
            //以此类推
        }
        return pre; //遍历结束后当前结点head已经在最后一个结点的下一个null的位置，而pre则是在最后一个结点
    }
}
```

## Solution 1
> 递归
```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode node = reverseList(head.next);

        // 头递归结束后只考虑两个结点的情况就行了
        head.next.next = head;
        head.next = null;

        return node;
    }
}
```