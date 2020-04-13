## Solution 1
> 用stack来装元素，然后相加，返回最后的链表，这样不用翻转链表了
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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();

        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2= l2.next;
        }

        ListNode head = null; // 初始null是最后一个node
        int carry = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || carry > 0) {
            int sum = carry;
            sum += stack1.isEmpty() ? 0 : stack1.pop();
            sum += stack2.isEmpty() ? 0 : stack2.pop();

            ListNode node = new ListNode(sum % 10);
            node.next = head; // 新产生的结点指向head
            head = node; //挪动head
            carry = sum / 10;
        }
        return head;
    }
}
```

## Solution 2
> 我觉得可以用Recuision来练习下solution1的过程
