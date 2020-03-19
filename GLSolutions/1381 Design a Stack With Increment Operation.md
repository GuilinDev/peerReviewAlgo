## Solution 1
> 每次increament的时候修改范围内所有的值

```java

```

## Solution 2
> Lazy Increment，用一个额外的数组来记录increment的值，为O(1)

```java
    class CustomStack {

        Stack<Integer> stack;
        int qSize;
        int[] inc;//inc[i]表示stack[0]到stack[i]
        public CustomStack(int maxSize) {
            stack = new Stack<>();
            qSize = maxSize;
            inc = new int[qSize];
        }

        public void push(int x) {
            if (stack.size() < qSize) {
                stack.push(x);
            }
        }

        public int pop() {
            int index = stack.size() - 1; // 只修改索引处的值
            if (index < 0) {
                return -1;
            } 
            if (index > 0) {
                inc[index - 1] += inc[index];
            }
            
            int res = stack.pop() + inc[index];
            inc[index] = 0;
            return res;
        }

        public void increment(int k, int val) {
            int i = Math.min(k, stack.size()) - 1; //前最小值（k, size）个数在stack的上部，加上val
            if (i >= 0) { //这里无需更新所有范围内的值，为O(1)，可以叫Lazy Increment，也可以用while，则为O(n)
                inc[i] += val;
            }
        }
    }

    /**
    * Your CustomStack object will be instantiated and called as such:
    * CustomStack obj = new CustomStack(maxSize);
    * obj.push(x);
    * int param_2 = obj.pop();
    * obj.increment(k,val);
    */
```