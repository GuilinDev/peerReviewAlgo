## Solution 1
> 双指针，在不同的题目中，注意使用while(fast != null && fast.next != null)或者while(fast.next != null && fast.next.next != null)，前者的情况如果为奇数时slow停留在正中间，偶数则停留在后半部分的起始；后者奇数时停留在正中间，偶数时停留在前半部分的最后一个。

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
        public ListNode middleNode(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            while (fast != null && fast.next != null) {
                slow = slow.next;
                fast = fast.next.next;
            }
            return slow;
        }
    }
```