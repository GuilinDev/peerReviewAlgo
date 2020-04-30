跟0088 - Merge Two Sorted Arrays容易一起问
## Solution 1
> 迭代
```java

```

## Solution 2
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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }

        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1; // first node
        } else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2; // first node
        }
    }
}
```