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

// 206. Reverse Linked List
 public ListNode reverseList(ListNode head) {
        if (head == null) return null;
        ListNode pre = null; ListNode temp = null;
         while (head != null){
             temp = head.next;
             head.next = pre;
             pre = head;
             head = temp;
         }
        return pre;
    }
//recursion
 public ListNode reverseList(ListNode head) {
        if(head == null || head.next == null) return head; // don't forget head.next
        ListNode tail = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return tail; 
    }


// 148. Sort List
class Solution {
    public ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode middle = getMiddleNode(head);
        ListNode next = middle.next; // need middle .next not middle will circle if not do this.
        middle.next = null;
        return mergeSortedList(sortList(head), sortList(next)); 
    }
    
    ListNode getMiddleNode(ListNode head) {
        ListNode slow = head; 
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
       return slow;
    }
    
    ListNode mergeSortedList(ListNode l1, ListNode l2){
        if(l1 == null || l2 == null){
            return l1 == null ? l2 : l1;
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val){
                cur.next = l1;
                cur = cur.next;
                l1 = l1.next;
            } else {
                cur.next = l2;
                cur = cur.next;
                l2 = l2.next;
            }
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next; // dummy.next not dummy
    }
    
}
//138. Copy List with Random Pointer
class Solution {
    public Node copyRandomList(Node head) {
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null){
            map.get(cur).next = map.get(cur.next); // 两次遍历 新表就在新表中操作
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }
}

//141. Linked List Cycle
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode slow = head; ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
           fast =  fast.next.next;
            slow = slow.next;
            if (fast == slow){
                return true;
            }   
        }
        return false;
    }
// 24. Swap Nodes in Pairs
class Solution {
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null) {
            ListNode first = cur.next;
            ListNode second = cur.next.next;           
            first.next = second.next;
            second.next = first;
            cur.next = second;
            cur = cur.next.next;
        }
        return dummy.next; 
    }
}

// 	147	  Insertion Sort List
    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(-1);
        ListNode current = head;
        ListNode pre = dummy;
        ListNode next = null;
        while (current != null) {
            next = current.next;
            while (pre.next != null && pre.next.val < current.val){
                pre = pre.next;
            }
            current.next = pre.next;
            pre.next = current;
            pre = dummy;
            current = next;
        }
        return dummy.next;
    }


}

```
