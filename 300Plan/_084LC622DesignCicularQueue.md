LC 622 Design Cicular Queue

循环队列应用场景：
* Memory Management: The unused memory locations in the case of ordinary queues can be utilized in circular queues.
* Traffic system: In computer controlled traffic system, circular queues are used to switch on the traffic lights one by one repeatedly as per the time set.
* CPU Scheduling: Operating systems often maintain a queue of processes that are ready to execute or that are waiting for a particular event to occur.
* Lock Free Queue: When high performance is required, we don't want to use lock. Circular Queue can is the top 1 data structure for lock free queue.

head == tail 说明循环队列为空
head == (tail + 1) % len 说明循环队列为满

(详细解释)[https://zhuanlan.zhihu.com/p/79163010]


```java

```
