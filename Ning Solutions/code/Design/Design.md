```java
//146. LRU Cache
public class LRUCache {

class DLinkedNode {
  int key;
  int value;
  DLinkedNode pre;
  DLinkedNode post;
}
/**
 * Always add the new node right after head;
 */
private void addNode(DLinkedNode node) {
  node.pre = head;
  node.post = head.post;
  head.post.pre = node;
  head.post = node;
}

/**
 * Remove an existing node from the linked list.
 */
private void removeNode(DLinkedNode node){
  DLinkedNode pre = node.pre;
  DLinkedNode post = node.post;
  pre.post = post;
  post.pre = pre;
}
/**
 * Move certain node in between to the head.
 */
private void moveToHead(DLinkedNode node){
  this.removeNode(node);
  this.addNode(node);
}

// pop the current tail. 
private DLinkedNode popTail(){
  DLinkedNode res = tail.pre;
  this.removeNode(res);
  return res;
}

private HashMap<Integer, DLinkedNode> 
  cache = new HashMap<Integer, DLinkedNode>();
private int count;
private int capacity;
private DLinkedNode head, tail;

public LRUCache(int capacity) {
  this.count = 0;
  this.capacity = capacity;
  head = new DLinkedNode();
  head.pre = null;
  tail = new DLinkedNode();
  tail.post = null;
  head.post = tail;
  tail.pre = head;
}

public int get(int key) {
  DLinkedNode node = cache.get(key);
  if(node == null){
    return -1; // should raise exception here.
  }
  // move the accessed node to the head;
  this.moveToHead(node);
  return node.value;
}


public void put(int key, int value) {
  DLinkedNode node = cache.get(key);
  if(node == null){
    DLinkedNode newNode = new DLinkedNode();
    newNode.key = key;
    newNode.value = value;
    this.cache.put(key, newNode);
    this.addNode(newNode);
    ++count;
    if(count > capacity){
      // pop the tail
      DLinkedNode tail = this.popTail();
      this.cache.remove(tail.key);
      --count;
    }
  }else{
    // update the value.
    node.value = value;
    this.moveToHead(node);
  }
}

}

//232. Implement Queue using Stacks
class MyQueue {
    /** Initialize your data structure here. */
    private Stack<Integer> s1 = new Stack<>();
    private Stack<Integer> s2 = new Stack<>();  
    public MyQueue() { }

    /** Push element x to the back of queue. */
    public void push(int x) {
        s1.push(x);
    }
    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
        return s2.pop();   
    }
    
    /** Get the front element. */
    public int peek() {
         if (!s2.isEmpty()) {
            return s2.peek();
        } else {
            while (!s1.isEmpty())
                s2.push(s1.pop());
        }
        return s2.peek(); 
    }
    
    /** Returns whether the queue is empty. */
    public boolean empty() {
        return s1.isEmpty() && s2.isEmpty();
    }
}

// 706. Design HashMap
class MyHashMap {
        final ListNode[] nodes = new ListNode[10000];

        public void put(int key, int value) {
            int i = idx(key);
            if (nodes[i] == null)
                nodes[i] = new ListNode(-1, -1);
            ListNode prev = find(nodes[i], key);
            if (prev.next == null)
                prev.next = new ListNode(key, value);
            else prev.next.val = value;
        }

        public int get(int key) {
            int i = idx(key);
            if (nodes[i] == null)
                return -1;
            ListNode node = find(nodes[i], key);
            return node.next == null ? -1 : node.next.val;
        }

        public void remove(int key) {
            int i = idx(key);
            if (nodes[i] == null) return;
            ListNode prev = find(nodes[i], key);
            if (prev.next == null) return;
            prev.next = prev.next.next;
        }

        int idx(int key) { return Integer.hashCode(key) % nodes.length;}

        ListNode find(ListNode bucket, int key) {
            ListNode node = bucket, prev = null;
            while (node != null && node.key != key) {
                prev = node;
                node = node.next;
            }
            return prev;
        }
        class ListNode {
            int key, val;
            ListNode next;
            ListNode(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
    }

```
