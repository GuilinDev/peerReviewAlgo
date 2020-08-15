```java
//1:Two sum
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i ++) {
            if (map.containsKey(target - nums[i])) { // target - nums[i]; // containsKey not contains
                return new int[]{map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[0];        
    }
// not recommend sort in two sum because it is aslking for index. after sort, index will missing.
//we need to copy to another copy to keep order.
// int[] nums2 = Arrays.copyOf(nums, nums.length);

//2:Merge Two Sorted Lists  
   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = null; 
        if (l1.val < l2.val) {
            head = l1; // not head.next = l1. since null can't have next;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1,l2.next);
        } 
     return head; 
  } 


//3:Remove Duplicates from Sorted Array    

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int total = 1;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] > nums[i - 1]) {
                nums[total] = nums[i]; // you need to remove eletement not just count
                total ++;
            }    }
        return total;    
    }  

//4:Plus One  
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return new int[0];
        for (int i = digits.length - 1; i >= 0; i --) { // i -- not i ++ starting from digits.length - 1; // 倒序
            if (digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }
            digits[i] = 0; // one will go to next position;
        }
        int[] result = new int[digits.length + 1];
            result[0] = 1;
         return result;
    }


// 5 Merge Sorted Array

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null ) return;
        int lastIndex = m + n - 1;
        int index1 = m - 1;
        int index2 = n - 1;
        while (index1 >= 0 && index2 >= 0) { // not using for in the lastIndex
            if (nums1[index1] > nums2[index2]) {
                nums1[lastIndex --] = nums1[index1 --]; // don't confused for nums1 and nums2.
            } else {
                nums1[lastIndex --] = nums2[index2--];
            }
        }
        while(index2 >= 0) {
            nums1[lastIndex --] = nums2[index2--];
        }
    }


//6: Rotate Array
  public void rotate(int[] nums, int k) { // 三次倒转
       k  %= nums.length; // need k %
       if (k == 0) return ;
       revertArray(nums, 0 , nums.length - 1);
       revertArray(nums,0, k- 1);
       revertArray(nums,k,nums.length - 1);
    }
    public void revertArray(int[] nums, int start, int end) {
         while(start < end) {
           int temp = nums[end];
           nums[end] = nums[start];
           nums[start] = temp; 
           start ++;
           end --;
         }
    }
}

// 7. Merge Intervals
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)-> a[0] - b[0]);
        int i = 0;
        int k = 0;
        while (i < intervals.length){
            int left = intervals[i][0];
            int right = intervals[i][1];
            while(i < intervals.length - 1 && right >= intervals[i + 1][0]){ // don't forget = case.
                i ++;
                right = Math.max(right, intervals[i][1]);
            }
            intervals[k][0] = left; // can use original arrays
            intervals[k][1] = right;
            k ++; 
            i ++;
        }     
        return Arrays.copyOf(intervals, k); 
      // it is K not k + 1. since it has k ++ in the end.
    }  
}

// 8.Insert Interval
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        int[][] res = new int[intervals.length + 1][2];
        int i = 0, k = 0;
        while (i < intervals.length && newInterval[0] > intervals[i][1]) {
            res[k++] = intervals[i];
            i++;
        }
        int[] tmp = new int[]{newInterval[0], newInterval[1]};
        while (i < intervals.length && newInterval[1] >= intervals[i][0]) {
            tmp[0] = Math.min(tmp[0], intervals[i][0]);
            tmp[1] = Math.max(tmp[1], intervals[i][1]);
            i++;
        }
        res[k++] = tmp;
        while (i < intervals.length) {
            res[k++] = intervals[i];
            i++;
        }
        return Arrays.copyOf(res,k); 
    }
}

// 9 Non-overlapping Intervals
class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        int len = intervals.length;
        if(len == 0) return 0;
        Arrays.sort(intervals, (a,b) -> a[1] - b[1]);
        int cur_end = intervals[0][1];
        int i = 1;
        int count = 0;
        while(i < len) {
            while(i < len && intervals[i][0] < cur_end){
                i ++;
                count++;
            }
            if(i < len){ // need this one. think about case [[1,2],[1,2],[1,2],[1,2]]
              cur_end = intervals[i++][1];
            }
        }
        return count;
    }
}

// 10 Meeting Rooms
class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        if(intervals.length == 0) return true;
        Arrays.sort(intervals, (a,b) -> a[1] - b[1]);
        int i = 1;
        int cur_end = intervals[0][1];
        while(i < intervals.length) {
            if(intervals[i][0] < cur_end){
                return false;
            }
            cur_end = intervals[i++][1];
        }
        return true;
    }
}

// 11 Meeting Rooms II
// PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder()); (Max heap)
// PriorityQueue<Integer> pq = new PriorityQueue<>((x, y) -> y - x);(Max heap)
//boolean add(E element): inserts the specified element into this priority queue.
//public remove(): removes a single instance of the specified element from this queue, if it is present
//public poll(): retrieves and removes the head of this queue,returns null if empty.
//public peek(): retrieves, but does not remove, the head of this queue, returns null if empty.
//boolean contains(Object o): This method returns true if this queue contains the specified element
//boolean offer(E e): This method is used to insert a specific element into the priority queue.
//int size(): The method is used to return the number of elements present in the set.
//toArray(): This method is used to return an array containing all of the elements in this queue.

class Solution {
    public int minMeetingRooms(int[][] intervals) {
        if(intervals.length == 0) return 0;
        Arrays.sort(intervals,(a,b)->a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        int rooms = 0;
        for(int i=0; i<intervals.length; i++) {
            minHeap.offer(intervals[i][1]); 
            if (intervals[i][0] < minHeap.peek()) {
// when new join and find his start time earlier than any end time, that means he need open new room.
                rooms ++;
            } else {
// need clean room for other people to use.
                minHeap.poll();
            }
        }
        return rooms;
    }
}

// 12  Interval List Intersections
// https://labuladong.gitbook.io/algo/suan-fa-si-wei-xi-lie/qu-jian-jiao-ji-wen-ti
class Solution {
  public int[][] intervalIntersection(int[][] A, int[][] B) {
    List<int[]> ans = new ArrayList();
    int i = 0, j = 0;
    while (i < A.length && j < B.length) {
       int a1 = A[i][0], a2 = A[i][1];
       int b1 = B[j][0], b2 = B[j][1];
       if(b1 <= a2 && b2 >= a1){
           ans.add(new int[]{Math.max(a1,b1),Math.min(a2,b2)});
       }
        if(a2 < b2){
            i++;
        } else {
            j++;
        }
    }
    return ans.toArray(new int[0][]);
  }
}



```
