package Pratice;

import java.util.*;
/*Given n sorted stream, and a constant number k. The stream type is like iterator
and it has two functions, move() and getValue(), find a list of numbers that each
of them appears at least k times in these streams. Duplicate numbers in a stream
should be counted as once.
(1,1,1,3,4,5,6,6)
{1,2,7,7,8} K = 2 only 1 return since 6, 7 , duplicate in their own stream


Note: In the interview, we should use min heap method
 =============================================================================
code
=============================================================================*/

public class Merge_K_Sorted_Streams {
    static class Stream{
        Iterator<Integer> iterator;
        public Stream(List<Integer> list){
            this.iterator = list.iterator();
        }
        public boolean move(){
            return iterator.hasNext();
        }
        public int getValue(){
            return iterator.next();
        }
    }
    class Num{
        int val;
        Stream stream;
        public Num(Stream stream){
            this.stream = stream;
            this.val = stream.getValue();
        }
    }
    public List<Integer> getNumberInAtLeastKStream(List<Stream> lists, int k){
        List<Integer> res = new ArrayList<>();
        if (lists == null || lists.size() == 0) return res;
        PriorityQueue<Num> minHeap = new PriorityQueue<>((a,b) -> a.val - b.val);
        //先把所有的stream放进heap里面
        for (Stream s: lists) {
            if (s.move()){ //这里先判断一下要不就崩了
                minHeap.offer(new Num(s));
            }
        }

        while (!minHeap.isEmpty()){
            Num cur = minHeap.poll();
            int curValue = cur.val;
            int count = 1;
            while (cur.stream.move()){
                int nextVal = cur.stream.getValue();
                if (nextVal == curValue){
                    continue;
                }
                else {
                    cur.val = nextVal;
                    minHeap.offer(cur);
                    break;
                }
            }
            //更新其他stream的头部，就是把指针往后挪，相同的数字就计数了。
            while (!minHeap.isEmpty() && curValue == minHeap.peek().val){
                count++;
                Num num = minHeap.poll();
//                int numVal = num.val;

                while (num.stream.move()){
                    int nextVal = num.stream.getValue();
                    if (curValue == nextVal){
                        continue;
                    }
                    else {
                        num.val = nextVal;
                        minHeap.offer(num);
                        break;
                    }
                }
            }

            if (count >= k){
                res.add(curValue);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Merge_K_Sorted_Streams test = new Merge_K_Sorted_Streams();
        Integer[] arr1 = {1,2,3,4};
        Integer[] arr2 = {2,5,6};
        Integer[] arr3 = {2,5,7};

        List<Integer> l1 = Arrays.asList(arr1);
        List<Integer> l2 = Arrays.asList(arr2);
        List<Integer> l3 = Arrays.asList(arr3);

        List<Stream> lists = new ArrayList<>();
        lists.add(new Stream(l1));
        lists.add(new Stream(l2));
        lists.add(new Stream(l3));

        List<Integer> res = test.getNumberInAtLeastKStream(lists, 2);
        System.out.println(res);
    }


}
