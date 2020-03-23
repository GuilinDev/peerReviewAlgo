# 找到数组中最小的k个数
[K Smallest](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)
> 1）全排序然后取前k个数字；2）利用插入排序只排序k个数字；这两个方法不说了

## Solution 1
> 维护一个最大堆，扫描一遍，有额外的维护开销nlogk

```java
    class Solution {
        public int[] getLeastNumbers(int[] arr, int k) {
            if (arr == null || arr.length == 0 || arr.length < k) {
                throw new IllegalArgumentException();
            }
            if (k == 0) {
                return new int[0];
            }
            // 默认是小根堆，实现大根堆需要重写一下比较器
            PriorityQueue<Integer> pq = new PriorityQueue<>((n1, n2) -> n2 - n1);

            for (int num : arr) {
                if (pq.size() < k) { // 先把k个元素放进空的queue中
                    pq.offer(num);
                } else {
                    if (num < pq.peek()) {
                        pq.poll();//把堆顶元素弹出来
                        pq.offer(num);
                    }
                }
            }

            int[] result = new int[k];
            int index = 0;
            for (int num : pq) {
                result[index] = num;
                index++;
            }
            return result;
        }
    }
```

## Solution 2
> 利用QuickSelect，这也是快排的思想，区别是每次只需要看一部分的元素，而不是两边都要做切割，是Reduce and Conquer，快排算是Divide and Conquer

## Solution 3
> 方法1用PriorityQueue本身的实现就是一个二叉搜索树，这里可以自己实现二叉树来找前k个最小的元素