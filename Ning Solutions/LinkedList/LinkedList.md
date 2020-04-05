```java
//2:Merge Two Sorted Lists  
class Solution {

   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = null; 
        if (l1.val < l2.val) {
            head = l1; // not head.next = l1. since null can't have next;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1,l2.next);
        }  
  } 
}
```
