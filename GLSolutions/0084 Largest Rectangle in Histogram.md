## Solution 1
> 暴力解法就是遍历每一个位置，最后得到最大的面积值，因为是两个循环嵌套，所以O(n^2)

> 优化：维护一个关于索引的单调递增栈，用一个Stack来保存元素的indices，遇到比栈中下标对应的值更大或等于的高度时，将这个更大或等于的高度对应的下标压入栈中；遇到比栈中下标对应的值更小的高度时，将之前所有比该值大的出栈，分别计算面积，与准备返回的area进行比较，取较大值。所以Stack中保存的是单调递增的索引。另外在最后时需要保持不上升，所以在最后压入一个0, O(n)。

```java
class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        if (heights == null || heights.length == 0) {
            return maxArea;
        }
        int len = heights.length;
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i <= len; i++) { // <=len 因为最后会手动加一个0
            int h = (i == len ? 0 : heights[i]); // 遍历完后在最后的位置添加一个0，方便比较
            if (stack.isEmpty() || heights[stack.peek()] <= h) { // 刚开始为空或者栈顶的元素小于新来的元素
                stack.push(i);
            } else { // 栈顶元素大于新来的元素，不能保证上升序列，这时候挨个出栈并计算面积
                int index = stack.pop();
                int tempArea = 0;
                if (stack.isEmpty()) {// 栈里最后一个元素是so far遍历到的数组中最小的值，这时候求面积就是包括所有bars
                    tempArea = heights[index] * i; //注意这里与len的区别，有可能stack为空时还没遍历完
                } else { // 中间的面积
                    tempArea = heights[index] * ((i - 1) - stack.peek());
                }
                i--; //需要从栈顶元素的index开始比，所以退回一步，进栈时才i++
                maxArea = Math.max(maxArea, tempArea);
            }
        }
        return maxArea;
    }
}
```