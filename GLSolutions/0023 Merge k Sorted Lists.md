## Solution 1
> Merge Sort的思想
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
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return divideConquer(lists, 0, lists.length - 1);
    }
    private ListNode divideConquer(ListNode[] lists, int left, int right) {
        if (left == right) { // 递归的初始条件
            return lists[left]; // return lists[right]; //已合并完成
        }
        if (left < right) {
            int mid = left + (right - left) / 2;
            ListNode list1 = divideConquer(lists, left, mid);
            ListNode list2 = divideConquer(lists, mid + 1, right);
            // 到此已经递归两两“分组”完毕，这时候可以开始合并了
            return mergeTwoLists(list1, list2);
        } else {
            return null;
        }
    }
    private ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null || list2 == null) {
            return list1 == null ? list2 : list1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                current.next = list1;
                list1 = list1.next;
            } else {
                current.next = list2;
                list2 = list2.next;
            }
            current = current.next;
        }
        // 不要忘记还没有合并完单个元素的链表
        current.next = list1 == null ? list2 : list1;

        return dummy.next;
    }
}
```

## Solution 2
> 最小堆的办法
```java

```