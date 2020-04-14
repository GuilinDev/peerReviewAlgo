```java
 
class Solution {
//2:Merge Two Sorted Lists 
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
          return head;
      } 

   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
               if (l1 == null || l2 == null) {
                   return l1 == null ? l2 : l1;
               }
               if (l1.val < l2.val) {     
                   l1.next = mergeTwoLists(l1.next, l2);
                   return l1;
               } else {
                   l2.next = mergeTwoLists(l1,l2.next);
                   return l2;
               }  
         }

   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
           if (l1 == null || l2 == null) {
               return l1 == null ? l2 : l1;
           }
           ListNode dummy = new ListNode(-1);
           ListNode cur = dummy; 
           while (l1 != null && l2 != null) {
               if (l1.val <= l2.val) {
                   cur.next = l1;
                   cur = l1;
                   l1 = l1.next;
                   
               } else {
                   cur.next = l2;
                   cur = l2;
                   l2 = l2.next;
               }
           }
           cur.next = (l1 == null) ? l2 : l1;
           return dummy.next; 
       }
    
// 2. Add Two Numbers
 public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if(l1 != null){ //  if not while
                sum += l1.val;
                l1 = l1.next; // l1 = l1.next not l1.next = l1
            }
            if(l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            ListNode node = new ListNode(sum % 10);
            cur.next = node;
            cur = node;
            carry = sum / 10;
        }
        if (carry != 0){
            cur.next = new ListNode(carry);
        }
        return dummy.next;  
    }

// 445. Add Two Numbers II
  public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        Stack<ListNode> s1 = new Stack<>(); // 涉及到对链表最后一个元素操作时候 可以把链表变成STACK。
        Stack<ListNode> s2 = new Stack<>();
        while (l1 != null){
            s1.push(l1);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2);
            l2 = l2.next;  
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = null;
        int carry = 0;
        while (!s1.isEmpty() || !s2.isEmpty()){
            int sum = carry;
            if (!s1.isEmpty()){
                sum += s1.pop().val;
            }
            if (!s2.isEmpty()){
                sum += s2.pop().val;
            }
            ListNode node = new ListNode(sum % 10);
            carry = sum / 10;
            node.next = cur;
            dummy.next = node;
            cur = node;
        }
        if (carry != 0){
             ListNode node = new ListNode(carry);
            node.next = dummy.next;
            dummy.next = node;
        }
       return dummy.next; 
        
    }

// 23. Merge k Sorted Lists
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return mergeListHelper(lists, 0 , lists.length - 1); 
    }
    
    private ListNode mergeListHelper(ListNode[] lists, int left, int right) {
        if (left == right) { // remember this binary search way
            return lists[left];
        } 
        if (left > right) {
            return null;
        } else { 
            int mid = left + (right - left) /2 ;
            ListNode leftnode = mergeListHelper(lists, left, mid);
            ListNode rightnode = mergeListHelper(lists, mid + 1, right);
            return mergeTwoLists(leftnode, rightnode);    
        }
     
        }
    
     private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = l1;
                cur = l1;
                l1 = l1.next;
                
            } else {
                cur.next = l2;
                cur = l2;
                l2 = l2.next;
            }
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
    }






}

```
