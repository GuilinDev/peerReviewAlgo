## Solution 1
> 需要考虑的时候push或者pop时候是最小值怎么做，可以用一个辅助栈保存最小值，也可以一个栈+一个变量

push的时候检查是否是最小值，如果是，直接更新；pop的时候也检查，如果是则遍历stack找到最小值，pop的时间为O（n）
```java
class MinStack {

    Stack<Integer> stack;
    int minEle;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<>();
        minEle = Integer.MAX_VALUE;
    }
    
    public void push(int x) {
        if (x <= minEle) {
            minEle = x;
        }
        stack.push(x);
    }
    
    public void pop() {
        int temp = stack.pop(); // already poped here
        if (temp <= minEle) {
            // iterate stack to find the minimum value
            minEle = Integer.MAX_VALUE;
            for (int num : stack) {
                minEle = Math.min(minEle, num);
            }
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        // 题目要求是pop、top 和 getMin 操作总是在 非空栈 上调用，所以不用判断stack是否为空
        return minEle;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```

优化一下pop的办法，push时总是把当前元素中的最小值保存在当前待进入的最小值之下，这样pop出最小值的时候，再pop一下就是当前最小值
```java
class MinStack {
    int min = Integer.MAX_VALUE;
    Stack<Integer> stack = new Stack<Integer>();
    public void push(int x) {
        //当前值更小
        if(x <= min){   
            //将之前的最小值保存
            stack.push(min);
            //更新最小值
            min = x;
        }
        stack.push(x);
    }

    public void pop() {
        //如果弹出的值是最小值，那么将下一个元素更新为最小值，注意是两次弹出，之前是暂存一下
        if(stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}
```

## Solution 2
> 不用java本身stack带的方法
```java
class MinStack {
    class Node{
        int value;
        int min;
        Node next;

        Node(int x, int min){
            this.value = x;
            this.min = min;
            next = null;
        }
    }
    Node head;
    //每次加入的节点放到头部
    public void push(int x) {
        if(null==head){
            head = new Node(x,x);
        }else{
            //当前值和之前头结点的最小值较小的做为当前的 min
            Node n = new Node(x, Math.min(x, head.min));
            n.next = head;
            head = n;
        }
    }

    public void pop() {
        if(head!=null)
            head = head.next;
    }

    public int top() {
        if(head != null)
            return head.value;
        return -1;
    }

    public int getMin() {
        if(null != head)
            return head.min;
        return -1;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
```