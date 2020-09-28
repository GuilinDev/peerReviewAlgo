LC 622 Design Cicular Queue

循环队列应用场景：
* Memory Management: The unused memory locations in the case of ordinary queues can be utilized in circular queues.
* Traffic system: In computer controlled traffic system, circular queues are used to switch on the traffic lights one by one repeatedly as per the time set.
* CPU Scheduling: Operating systems often maintain a queue of processes that are ready to execute or that are waiting for a particular event to occur.
* Lock Free Queue: When high performance is required, we don't want to use lock. Circular Queue can is the top 1 data structure for lock free queue.

head == tail 说明循环队列为空
head == (tail + 1) % len 说明循环队列为满

整个队列预留一个空位，(详细解释)[https://zhuanlan.zhihu.com/p/79163010]


```java
class MyCircularQueue {
    int head;
    int tail;
    int len; // 当前array实际含有的元素
    final int[] cq;

    /** Initialize your data structure here. Set the size of the queue to be k. */
    public MyCircularQueue(int k) {
        this.head = 0; // 头部在第一个位置
        this.tail = -1; // 尾部初始化没有
        this.len = 0;
        this.cq = new int[k];
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (!isFull()) {
            tail = (tail + 1) % cq.length; // tail指针位置向后移动一位，或者去头部
            cq[tail] = value;
            len++;
            return true;
        } else return false;
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (!isEmpty()) {
            head = (head + 1) % cq.length; // head指针位置向后移动一位，或者去头部
            len--;
            return true;
        } else return false;
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : cq[head];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : cq[tail];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return len == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return len == cq.length;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
```
