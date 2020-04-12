## Solution 3
> 用两个queue，压入O(n)，弹出O(1)
```java
class MyStack {

    // 队列中元素只能从 后端（rear）入队（push），然后从 前端（front）端出队（pop）
    // 新元素永远从 queue1 的后端入队，同时 queue1 的后端也是栈的 栈顶（top）元素。
    private Queue<Integer> queue1;
    private Queue<Integer> queue2;
    private int top; //额外元素保存栈顶元素
    /** Initialize your data structure here. */
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue1.offer(x);
        top = x;
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        // 交换queue1和queue2索引的指向
        while(queue1.size() > 1) { 
            top = queue1.poll();
            queue2.offer(top);
        }
        int result = queue1.poll(); // 这个会移除
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return result;
    }
    
    /** Get the top element. */
    public int top() {
        return top;
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue1.size() == 0 && queue2.size() == 0;
    }
}

/**
* Your MyStack object will be instantiated and called as such:
* MyStack obj = new MyStack();
* obj.push(x);
* int param_2 = obj.pop();
* int param_3 = obj.top();
* boolean param_4 = obj.empty();
*/
```

## Solution 3
> 用两个queue，压入O(1)，弹出O(n)
```java
    
```

## Solution 3
> 只用一个queue，压入O(n)，弹出O(1)
```java
class MyStack {

    Queue<Integer> queue;
    /** Initialize your data structure here. */
    public MyStack() {
        queue = new LinkedList<>();
    }
    
    /** Push element x onto stack. */
    public void push(int x) {
        queue.add(x);
        for (int i = 1; i < queue.size(); i++) {//只用rotate size - 1个元素
            queue.add(queue.poll()); //弹出最旧的元素，压入到最新的位置，以此来rotate
        }
    }
    
    /** Removes the element on top of the stack and returns that element. */
    public int pop() {
        return queue.poll();
    }
    
    /** Get the top element. */
    public int top() {
        return queue.peek();
    }
    
    /** Returns whether the stack is empty. */
    public boolean empty() {
        return queue.size() == 0;
    }
}

/**
* Your MyStack object will be instantiated and called as such:
* MyStack obj = new MyStack();
* obj.push(x);
* int param_2 = obj.pop();
* int param_3 = obj.top();
* boolean param_4 = obj.empty();
*/
```