## Solution 1
> 检查有效括号的题目我们需要用到辅助栈，这个题面中的depth其实就是栈的最大深度。“你需要从中选出任意一组有效括号字符串 A 和 B，使 max(depth(A), depth(B)) 的可能取值最小”。这句话其实相当于让A字符串和B字符串的depth尽可能的接近(平分)。为什么呢？因为seq对应的栈上，每个左括号都对应一个深度，而这个左括号，要么是A的，要么是B的。所以，栈上的左括号只要按奇偶尽可能平均分配给A和B就可以啦！时间复杂度很明显是 O(n)的，空间复杂度也是O(n)

```java
    class Solution {
        public int[] maxDepthAfterSplit(String seq) {
            int len = seq.length();
            int[] result = new int[len];
            int index = 0;
            for (char ch : seq.toCharArray()) {
                // 判断左括号‘(’是因为判断栈的深度，给定的seq总是有效的
                result[index] = (ch == '(' ? index & 1 : (index + 1) & 1); //(index & 1和index + 1) & 1轮流得到奇数或偶数加入到结果中
                index++;
            }
            return result;
        }
    }
```