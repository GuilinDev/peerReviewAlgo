```java
//146. LRU Cache
//哈希表查找快，但是数据无固定顺序；链表有顺序之分，插入删除快，
// 但是查找慢。所以结合一下，形成一种新的数据结构：哈希链表。
// 另外两大要点：双向链表，链表中要放键值 不能只放值
// 双向链表三大操作，addfirst, remove, removeLast
class Node {
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
//然后依靠我们的 Node 类型构建一个双链表，
// 实现几个需要的 API（这些操作的时间复杂度均为 O(1)O(1))：
class DoubleList {  
    private Node head, tail; // 头尾虚节点
    private int size; // 链表元素数

    public DoubleList() {
        head = new Node(0, 0);
        tail = new Node(0, 0);
        head.next = tail; // 忘记这一步直接让ADDFIRST返回NULL 
        tail.pre = head;
        size = 0;
    }

    // 在链表头部添加节点 x
    public void addFirst(Node x) {
        x.next = head.next;
        x.prev = head;
        head.next.prev = x;
        head.next = x;
        size++;
    }

 // 删除链表中的 x 节点（x 一定存在）
//为什么必须要用双向链表?因为我们需要删除操作。删除一个节点不光要得到该节点本身的指针，
// 也需要操作其前驱节点的指针，而双向链表才能支持直接查找前驱，保证操作的时间复杂度 O(1)O(1)。
    public void remove(Node x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--; // 要放这里不能放下面 因为有时候是直接用这个方法
    }
    
    // 删除链表中最后一个节点，并返回该节点
    public Node removeLast() {
        if (tail.prev == head) // 这里很容易因为链表原因写成 tail.prev = head
            return null;
        Node last = tail.prev;
        remove(last);
        return last;
    }
    
    // 返回链表长度
    public int size() { return size; }
}
// 两个主方法 put get. 先写put 都要先看看有没有KEY
class LRUCache {
    // key -> Node(key, val)
    private HashMap<Integer, Node> map;
    // Node(k1, v1) <-> Node(k2, v2)...
    private DoubleList cache;
    // 最大容量
    private int cap;

    public LRUCache(int capacity) {
        this.cap = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int val = map.get(key).val;
        // 利用 put 方法把该数据提前
        put(key, val); // not map.put(key,value)
        return val;
    }

    public void put(int key, int val) {
        // 先把新节点 x 做出来
        Node x = new Node(key, val);

        if (map.containsKey(key)) {
            // 删除旧的节点，新的插到头部
            cache.remove(map.get(key));
            cache.addFirst(x);
            // 更新 map 中对应的数据
            map.put(key, x); // don't forget this
        } else {
            if (cap == cache.size()) { //注意这个是跟SIZE比
                // 删除链表最后一个数据
                Node last = cache.removeLast();
                map.remove(last.key);
//当缓存容量已满，我们不仅仅要删除最后一个 Node 节点，还要把 map 中映射到该节点的 key 同时删除，
// 而这个 key 只能由 Node 得到。如果 Node 结构中只存储 val，那么我们就无法得知 key 是什么，
// 就无法删除 map 中的键，造成错误。   
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
