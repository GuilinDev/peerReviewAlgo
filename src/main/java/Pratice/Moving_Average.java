package Pratice;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/*Given a stream of input, and a API int getNow() to get the current time stamp,
        Finish two methods:

        1. void record(int val) to save the record.
        2. double getAvg() to calculate the averaged value of all the records in 5 minutes.*/
public class Moving_Average {
    private Queue<Event> queue = new LinkedList<>();
    private int sum = 0;

    //这个是每次记录读进来的时间的,这个不用自己写,就是直接返回当前系统时间
    private int getNow(){
        return 0; //暂时写个0，苟活
    }

    private boolean isExpired(int curTime, int preTime){
        return curTime - preTime > 300;
    }
    private void removeExpireEvent(){
        while (!queue.isEmpty() && isExpired(getNow(), queue.peek().time)){
            Event curE = queue.poll();
            sum -= curE.val;
        }
    }
    public void record(int val){
        Event event = new Event(getNow(), val);
        queue.offer(event);
        sum += val;

        removeExpireEvent();
    }

    public double getAvg(){
        removeExpireEvent();
        if (!queue.isEmpty()){
            return (double) sum/queue.size();
        }

        return 0.0;
    }
}
class Event{
    int val;
    int time;
    public Event(int val, int time){
        this.val = val;
        this.time = time;
    }
}

/*public class Movingaverage {
    private Queue<Event> queue = new LinkedList<>();
    private int sum = 0;

    // record an event
    public void record(int val) {
        Event event = new Event(getNow(), val);
        queue.offer(event);
        sum += event.val;

        removeExpiredRecords();
    }

    private int getNow() {
        return 0;
    }

    private void removeExpiredRecords() {
        while (!queue.isEmpty() && expired(getNow(), queue.peek().time)) {
            Event curr = queue.poll();
            sum -= curr.val;
        }
    }
    private double getAvg() {
        removeExpiredRecords();
        if (queue.isEmpty()) {
            return 0;
        } else {
            return (double) sum / queue.size();
        }
    }

    private boolean expired(int currTime, int prevTime) {
        return currTime - prevTime > 5;
    }

    class Event {
        int time;
        int val;

        public Event (int time, int val) {
            this.time = time;
            this.val = val;
        }
    }
}*/

/*public class Moving_Average2 {
    //queue的容量被限制
    private Deque<Event> queue = new LinkedList<>(); //改成deque的话，可以从后面查
    private long sum = 0; //改用long显得严谨点儿
    int dataNum = 0;

    //这个是每次记录读进来的时间的,这个不用自己写,就是直接返回当前系统时间
    //假设它返回的是秒
    private int getNow(){
        return 0;
    }

    private boolean isExpired(int curTime, int preTime){
        return curTime - preTime > 300;
    }
    private void removeExpireEvent(){
        while (!queue.isEmpty() && isExpired(getNow(), queue.peekFirst().time)){
            Event curE = queue.poll();
            sum -= curE.val;
            dataNum -= curE.size;
        }
    }
    public void record(int val){ //其实就是record这里有了两种办法，一种是建个新的，另一种就是合起来
        Event last = queue.peekLast();
        if (getNow() - last.time < 10){
            last.size += 1;
            last.val += val;
        }
        else {
            Event event = new Event(getNow(), val);
            queue.offer(event);
        }
        dataNum += 1;
        sum += val;
        removeExpireEvent();
    }

    public double getAvg(){
        removeExpireEvent();
        if (!queue.isEmpty()){
            return (double) sum/dataNum;
        }
        return 0.0;
    }
}
class Event2{
    int val;
    int time;
    int size;
    public Event2(int val, int time){
        this.val = val;
        this.time = time;
        this.size = 1;
    }


    //实现find Median，其实O1操作的话，要始终维护两个heap，这样塞进去会很慢
//原有基础上实现的话，那就直接quick select的办法了。
//复杂度是On，因为每次average case是去掉一半，就是O(n)+O(n/2)+O(n/4)+... 最后出来是O(2n)
    //那这个需要把整个queue给倒出来再塞回去。
    public double getMedian(){
        removeExpireEvent();
        int[] temp = new int[queue.size()];
        for (int i = 0; i<queue.size(); i++){
            temp[i] = queue.poll().val;
        }
        //这里还得把queue还原回去,先不写了。
        int len = temp.length;
        if (len % 2 == 0){
            return 0.5*(findKth(temp, len/2, 0, len-1) + findKth(temp, len/2-1, 0, len-1));
        }
        return (double)findKth(temp, len/2, 0, len-1);
    }
    public int findKth(int[] temp, int k, int start, int end){
        int pivot = temp[start];
        int left = start, right = end;
        while (left < right){
            while (temp[right] > pivot && left < right){
                right--;
            }
            while (temp[left] <= pivot && left < right){
                left++;
            }
            swap(temp, left, right);
        }
        swap(temp, start, right);
        if (k == right){
            return pivot;
        }
        else if (k < right){
            return findKth(temp, k, start, right-1);
        }

        return findKth(temp, k, right+1, end);
    }
    public void swap(int[] temp, int left, int right){
        int i = temp[left];
        temp[left] = temp[right];
        temp[right] = i;
    }
}*/

