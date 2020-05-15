## Solution 1
> 递归,O(n)

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
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        
        ListNode node = head;
        int count = 0;
        while (node != null && count < k) { // 递归k个元素一组
            node = node.next;
            count++;
        }

        // head - head-pointer to direct part, 
        // node - head-pointer to reversed part;
        if (count == k) { // 最后一组不到k个的元素不用翻转
            node = reverseKGroup(node, k); 

            while (count > 0) { // 递归后俩俩翻转
                ListNode temp = head.next;
                head.next = node;
                node = head;
                head = temp;

                count--;
            }
            head = node; // 更新node位置
        }
        return head;
    }
}
```

## Solution 2
> 非递归，使用dummy，比较繁琐

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
    public ListNode reverseKGroup(ListNode head, int k) {//1->2->3->4->5 : Say k is 3
        if(k==1) return head;
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode ptr1 = dummy;//boundary before the 1st node
        ListNode ptr2 = dummy;//boundary after the kth node
        int count = 0;
        boolean delete = false;//For deleting the last dummy node
        while(count<k){//loop till the kth element : Till 3 in this case
            count++;
            ptr2=ptr2.next;
            if(ptr2==null) break;//if before reaching kth element ptr2 is null, break out of the loop. Example 1->2
            if(count==k){
                if(ptr2.next==null){
                    ptr2.next = new ListNode(-1);//dummy2
                    delete = true;
                }
                ptr2 = ptr2.next;
                count = 0;//Make count 0 to again start with the next group
                ptr1 = reverseK(ptr1,ptr2);
                ptr2 = ptr1;
            }
        }
        if(delete) ptr1.next = null;
        return dummy.next;
    }
    private ListNode reverseK(ListNode ptr1, ListNode ptr2){//ptr1->|1->2->3|->4->5 : ptr1 is dummy and ptr2 is 4 here
        ListNode prev = ptr1;
        ListNode temp1 = ptr1.next;
        ListNode temp2 = ptr1.next.next;
        while(temp1!=ptr2){
            temp1.next = prev;//In the first iteration 1 points to ptr1, Last iteration 3->2
            prev = temp1;//last iteration prev becomes 3
            temp1 = temp2;
            temp2 = temp2.next;
        }
        ptr1.next = prev;//Now a reversed circular list is created, because 3<-ptr1<-1<-2<-3
        while(prev.next!=ptr1){
            prev=prev.next;//till prev points to 1
        }
        //1 must point to 4
        prev.next = ptr2;//K List now is reversed: ptr1->3->2->1->4->5
        return prev;
    }
}
```