### 
```java
    /**
   
ArrayDeque和LinkedList都实现了Deque的接口，而Deque接口文档中将推荐实现Deque接口的集合适合用于栈Stack和队列使用
下面是栈Stack和队列的方法对照
1 - 下表列出了Deque与Queue相对应的接口：
QueueMethod	Equivalent Deque Method	说明
add(e)	addLast(e)	向队尾插入元素，失败则抛出异常
offer(e)	offerLast(e)	向队尾插入元素，失败则返回false 
remove()	removeFirst()	获取并删除队首元素，失败则抛出异常
poll()	pollFirst()	获取并删除队首元素，失败则返回null 
element()	getFirst()	获取但不删除队首元素，失败则抛出异常
peek()	peekFirst()	获取但不删除队首元素，失败则返回null 
2 -下表列出了Deque与Stack对应的接口：
Stack Method	Equivalent Deque Method	说明
push(e)	addFirst(e)	向栈顶插入元素，失败则抛出异常
无	offerFirst(e)	向栈顶插入元素，失败则返回false 
pop()	removeFirst()	获取并删除栈顶元素，失败则抛出异常
无	pollFirst()	获取并删除栈顶元素，失败则返回null 
peek()	peekFirst()	获取但不删除栈顶元素，失败则抛出异常
无	peekFirst()	获取但不删除栈顶元素，失败则返回null 
当使用队列功能时建议使用LikedList
当使用栈功能时建议使用ArrayDeque
 **/

 /*
    ArrayList和Array相互转换
    E[] arr = new E[]{};
    List list = Arrays.asList(arr)
*/






```

