## Solution 1
> 一个Hashmap一个双向链表

```java
class LRUCache {

    class DLinkedNode {
        int key;
        int value;
        DLinkedNode next;
        DLinkedNode pre;
    }

    /**
     * Always add the new node right after head
     */
    private void addNode(DLinkedNode node) {
        node.pre = head;
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
    }

    /**
     * Removing an existing node from the doulble linked list
     */
    private void removeNode(DLinkedNode node) {
        DLinkedNode pre = node.pre;
        DLinkedNode next = node.next;

        pre.next = next;
        next.pre = pre;
    }

    /**
     * Move certain node in between to the head.
     */
    private void moveToHead(DLinkedNode node){
        this.removeNode(node);
        this.addNode(node);
    }

    /**
     * Removing the tail node
     */
    private DLinkedNode removeTailNode() {
        DLinkedNode node = tail.pre;
        this.removeNode(node);
        return node;
    }

    /**
     * 定义需要的变量
     */
    //线程安全用HashTable
    private HashMap<Integer, DLinkedNode> cache = new HashMap<Integer, DLinkedNode>();
    private int count;
    private int capacity;
    private DLinkedNode head, tail;

    public LRUCache(int capacity) {
        this.count = 0;
        this.capacity = capacity;

        head = new DLinkedNode();
        head.pre = null;

        tail = new DLinkedNode();
        tail.next = null;

        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null) {//cache中不存在该key，返回-1
            return -1;
        }

        this.moveToHead(node);

        return node.value;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null) {//插入的时候没有该键值对，新插入

            DLinkedNode newNode = new DLinkedNode();
            newNode.key = key;
            newNode.value = value;

            this.cache.put(key, newNode);
            this.addNode(newNode);

            count++;

            if (count > capacity) {//这时候需要删除tail的值
                DLinkedNode tail = this.removeTailNode();
                this.cache.remove(tail.key);
                count--;
            }
        } else {//还有空位就直接插入
            node.value = value;
            this.moveToHead(node);
        }
    }

}



/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
```