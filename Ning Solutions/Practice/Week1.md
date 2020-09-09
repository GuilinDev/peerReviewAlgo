```java
// 1. Two Sum
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        if (nums == null || nums.length == 0 ) return res;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i ++) {
            if (map.containsKey(target - nums[i])){ // containsKey not contains. map.put not map.add
               int index1 = map.get(target - nums[i]);
               res[0] = index1;
               res[1] = i; 
            } else {
                map.put(nums[i], i);
            }
        }
        return res;
    }
}

// 146, LRU cache
class Node {
    int key, value;
    Node pre, next;
    public Node (int key, int value) { // order
        this.key = key;
        this.value = value;
    }
}

class DoubleList {
    Node head;
    Node tail;
    int size;
    public DoubleList(int size){ // put size in the construct method
        this.size = size;
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail; // need link head and tail in the construct time
        tail.pre = head;  
    }
    
    public void remove(Node node) { // size --
        node.pre.next = node.next;
        node.next.pre = node.pre;
        node.pre = null; // best to add this for garbage
        node.next = null;
        size --;
    }
    
    public void addFirst(Node node) { 
        node.next = head.next; // link node first and link head next.
        node.pre = head;
        head.next.pre = node; 
        head.next = node;
        size ++;
    }
    
    public Node removeLast() {
        if (tail.pre == head){ // if empty case
            return null;
        }
        Node node = tail.pre;
        remove(node);
        return node;
    }
    
    public int getSize() {
        return this.size;
    }
    
}

class LRUCache {
    DoubleList cache;
    HashMap<Integer, Node> map;
    int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        cache = new DoubleList(0);
        map = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)){
            return -1;
        }
        Node node = map.get(key);
        put(key, node.value);
        return node.value;
        
    }
    
    public void put(int key, int value) {
        if(map.containsKey(key)){
           Node node = map.get(key);
            cache.remove(node);
            node.value = value;
            cache.addFirst(node);
            return;
        }
        if (cache.getSize() == capacity){
           Node lastNode = cache.removeLast();
           map.remove(lastNode.key); 
        }
        Node newNode = new Node(key,value);
        map.put(key,newNode);
        cache.addFirst(newNode);
    }
}

//2. Add Two Numbers
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if(l1 == null || l2 == null){
            return (l1== null) ? l2 : l1;
        }
        int carry = 0;
        ListNode head = new ListNode(-1);
        ListNode cur = head;
        while (l1 != null || l2 != null) {
            int sum = carry;
            if (l1 != null){
                int val = l1.val;
                l1 = l1.next;
                sum += val;
            }
            if(l2 != null) {
                int val = l2.val;
                l2 = l2.next;
                sum += val;
            }
            carry = sum / 10;
            int value = sum % 10;
            ListNode node = new ListNode(value);
            cur.next = node;
            cur = cur.next;
        }
        if (carry != 0){ 
            cur.next = new ListNode(carry);
        }
        return head.next;
    }
}

// 4. Median of Two Sorted Arrays
// https://windliang.wang/2018/07/18/leetCode-4-Median-of-Two-Sorted-Arrays/
class Solution {
    /**
    * 二分搜索，log(min(m, n))
    */
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        //if nums1 length is greater than switch them so that 
        // nums1 is always smaller than nums2, 方便复用代码
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }
        int x = nums1.length;
        int y = nums2.length;

        int low = 0;
        int high = x;
        while (low <= high) {
            // 把两个数组分别划分
            int partitionX = (low + high) / 2; // nums1的中间
            int partitionY = (x + y + 1) / 2 - partitionX; // nums2的中间

            //if partitionX is 0 it means nothing is there on left side. Use -INF for maxLeftX
            //if partitionX is length of input then there is nothing on right side. Use +INF for minRightX
            int maxLeftX = (partitionX == 0) ? Integer.MIN_VALUE : nums1[partitionX - 1];
            int minRightX = (partitionX == x) ? Integer.MAX_VALUE : nums1[partitionX];

            int maxLeftY = (partitionY == 0) ? Integer.MIN_VALUE : nums2[partitionY - 1];
            int minRightY = (partitionY == y) ? Integer.MAX_VALUE : nums2[partitionY];

            if (maxLeftX <= minRightY && maxLeftY <= minRightX) {
                //We have partitioned array at correct place
                // Now get max of left elements and min of right elements to get the median in case of even length combined array size
                // or get max of left for odd length combined array size.
                if ((x + y) % 2 == 0) {
                    return ((double)Math.max(maxLeftX, maxLeftY) + Math.min(minRightX, minRightY))/2;
                } else {
                    return (double)Math.max(maxLeftX, maxLeftY);
                }
            } else if (maxLeftX > minRightY) { //we are too far on right side for partitionX. Go on left side.
                high = partitionX - 1;
            } else { //we are too far on left side for partitionX. Go on right side.
                low = partitionX + 1;
            }
        }

        //Only we we can come here is if input arrays were not sorted. Throw in that scenario.
        throw new IllegalArgumentException();
    }
}

//3 - Longest Substring Without Repeating Characters
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        for (int left = 0, right = 0; right < s.length(); right ++) {
            char c = s.charAt(right);
            if (map.containsKey(c)){
                left = Math.max(left, map.get(c) + 1); // need + 1; check case " ";
            }
            map.put(c,right);
            res = Math.max(res, right - left + 1);
        }
        return res;
    }
}

// 5. Longest Palindromic Substring
class Solution {
    private int lo, maxLen;
    public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2)  return s;
        for (int i = 0; i < len - 1; i++) {
            extendPalindrome(s, i, i);  //assume odd length, try to extend Palindrome as possible
            extendPalindrome(s, i, i + 1); //assume even length.
        }
        return s.substring(lo, lo + maxLen);
    }
    private void extendPalindrome(String s, int left, int right) {//字符串和两个指针
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        //注意上面while循环是在左右不等才停止的，当前的maxLen是成员变量，需要维持奇偶中大的一个（较小的不进循环）
        if (maxLen < right - left - 1) {
            lo = left + 1;//回文子字符串的下标
            maxLen = right - left - 1;//回文子字符串的上标
        }
    }
}
// 973. K Closest Points to Origin
class Solution {
   public int[][] kClosest(int[][] points, int K) {
    PriorityQueue<int[]> pq = new PriorityQueue<int[]>((p1, p2) -> p2[0] * p2[0] + p2[1] * p2[1] - p1[0] * p1[0] - p1[1] * p1[1]);
    for (int[] p : points) {
        pq.offer(p);
        if (pq.size() > K) {
            pq.poll();
        }
    }
    int[][] res = new int[K][2];
    while (K > 0) {
        res[--K] = pq.poll();
    }
    return res;
}
}

//42. Trapping Rain Water 
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0) return 0;
        int n = height.length;
        int left = 0; int right = n - 1;
        int leftMax = height[0];
        int rightMax = height[n - 1];
        int sum = 0;
        while (left <= right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            if (leftMax < rightMax) { //看解释
                sum += leftMax - height[left];
                left ++;
            } else {
                sum += rightMax - height[right];
                right --;
            }
        }
        return sum;
    }
}
/*我们只在乎min(l_max, r_max)。对于上面的情况，
我们已经知道l_max < r_max了，至于这个r_max是不是右边最大的，
不重要，重要的是height[i]能够装的水只和l_max有关。*/


// 11 Container With Most Water
class Solution {
    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int maxArea = 0;
        while(left < right) {
            int w = Math.min(height[left], height[right]);
            int l = right - left;
            maxArea = Math.max(maxArea, w*l);
            if(height[left] < height[right]){
                left ++;
            } else {
                right --;
            }
        }
        return maxArea;
    }
}

// 53 Maximum Subarray
class Solution {
    public int maxSubArray(int[] a) {
    int maxSum = 0, thisSum = 0, max=a[0];
    for(int i=0; i<a.length; i++) {
        if(a[i]>max) max =a[i];
        thisSum += a[i];
        if(thisSum > maxSum)
            maxSum = thisSum;
        if(thisSum < 0) // check explain in below
            thisSum = 0;
    }
    if (maxSum==0) return max;
    return maxSum;
    }
    /*如果a[i]是负数，那么它不可能代表最优序列的起点，
因为任何包含a[i]的作为起点的子序列都可以通过使用a[i+1]作为起点得到改进。
类似的，任何负的子序列也不可能是最优子序列的前缀（原理相同）。
*/
}

// 200. Number of Islands
// DFS.
class Solution {
    int[][] dis = new int[][]{{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int total = 0;
        for (int r = 0; r < grid.length; r ++) {
            for (int c = 0; c < grid[0].length; c++) {
                if (grid[r][c] == '1') {
                    searchArea(grid, r , c);
                    total ++;
                }
            }
        }
        return total;
    }
    
    private void searchArea(char[][] grid, int r, int c){
        if(!inArea(grid,r,c)){
            return;
        }
        if(grid[r][c] != '1'){
            return;
        }
        grid[r][c] = '2';
        for (int i = 0; i < 4; i ++){
            int nextR = r + dis[i][0];
            int nextC = c + dis[i][1];
            searchArea(grid, nextR, nextC);
        }
    }
 
    private boolean inArea(char[][] grid, int r, int c){
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}

// 695. Max Area of Island
class Solution {
    int[][] dis = new int[][]{{-1,0}, {0, -1}, {1, 0}, {0, 1}};
    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return 0;
        int max = 0;
        for (int r = 0; r < grid.length; r ++){
            for (int c = 0; c < grid[0].length; c ++){
                if (grid[r][c] == 1){
                    int area = getArea(grid, r , c);
                    max = Math.max(area, max);    
                } 
            }
        }
        return max;
    }
    
    private int getArea(int[][] grid, int r, int c){
        if (!inArea(grid, r, c)){
            return 0;
        }
        if(grid[r][c] != 1){
            return 0;
        }
        grid[r][c] = 2;
        int sum = 1;
        for (int i = 0; i < 4; i ++){
            int nextR = r + dis[i][0];
            int nextC = c + dis[i][1];
            sum += getArea(grid, nextR, nextC);
        }
        return sum;
        
    }
   
    private boolean inArea(int[][] grid, int r, int c) {
        return r >= 0 && r < grid.length && c >= 0 && c < grid[0].length;
    }
}

// 56. Merge Intervals
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b) -> a[0] - b[0]); // -> not =>
        int k = 0;
        int i = 0;
        while (i < intervals.length) {
            int start = intervals[i][0]; // this is special 2D array case which fix column size 2;
            int end = intervals[i][1];
            while (i < intervals.length - 1 && end >= intervals[i + 1][0]){ //test overlap need to first check this end with next start
                i ++;
                end = Math.max(intervals[i][1], end);// need to check bigger one, since previous just check overlap.
            }
            intervals[k][0] = start;
            intervals[k][1] = end;
            k ++;
            i ++; 
        }
        return Arrays.copyOf(intervals, k);
    }
}

// 23. Merge k Sorted Lists
class Solution {
   public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        ListNode l1 = merge(lists, left, mid);
        ListNode l2 = merge(lists, mid + 1, right);
        return mergeTwoLists(l1, l2);
    }

    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val) {
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists(l1,l2.next);
            return l2;
        }
    }
}

class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if(node != null){
               pq.offer(node);  
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while(!pq.isEmpty()) {
            ListNode node = pq.poll();
            cur.next = node;
            cur = cur.next;
            if(node.next != null){
                pq.offer(node.next);
            }
        }

        return dummy.next;    
    }
}
// 20. Valid Parentheses
class Solution {
    public boolean isValid(String s) {
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()){
            if (c == '(' || c == '{' || c == '['){
                stack.push(c);
            }
            if (stack.isEmpty()) return false; // need this one like case "]";
            if ((c == ')' && stack.pop() != '(') ||
                (c == '}' && stack.pop() != '{') ||
                (c == ']' && stack.pop() != '[') ) {
                return false;
            }
        }
        if (!stack.isEmpty()) return false;
        return true;
        
    }
}
// 253 Meeting Rooms II
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

// 33. Search in Rotated Sorted Array
class Solution { 
        public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0){return -1;}
        int start = 0;
        int end = nums.length - 1; // binary search use start, end, not left right.
        int mid;
        while (start <= end) { 
             mid = start + (end - start) /2;
            if (nums[mid] == target) {return mid;}
            if (nums[start] <= nums[mid]){ // start less equals it is mid not target
                if(target >= nums[start] && target < nums[mid]){
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
            } else {
                if(target <= nums[end] && nums[mid] < target ){ 
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
            }
        }
        return -1;
    }
}

// 301. Remove Invalid Parentheses
class Solution {
    public List<String> removeInvalidParentheses(String s) {
        List<String> result = new ArrayList<>();
        if (s == null) {
            return result;
        }

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();//存储状态
        //初始化
        visited.add(s);
        queue.add(s);
        boolean found = false;//在某个level是否发现valid的状态
        while (!queue.isEmpty()) {
            s = queue.poll();
            if (isValid(s)) {
                //找到一个答案，加入到结果集
                result.add(s);
                found = true;
            }
            if (found) {//当前状态已加入结果集，跳出当前循环，检查下一个状态
                continue;
            }
            //产生所有的状态
            for (int i = 0; i < s.length(); i++) {
                //只移除左括号或右括号，字母什么的不移除
                if (s.charAt(i) != '(' && s.charAt(i) != ')') {
                    continue;
                }
                //产生一个临时状态
                String temp = s.substring(0, i) + s.substring(i + 1);
                if (!visited.contains(temp)) {
                    queue.add(temp);
                    visited.add(temp);
                }
            }
        }

        return result;
    }
    
    public boolean isValid(String s){
        int count = 0;
        for (int i=0; i<s.length(); i++){
            if(s.charAt(i)=='(') count++;
            if(s.charAt(i)==')') count--;
            if(count<0) return false;
        }
        return count==0;
    }
}

// 322 Coin Change
//递归方法 和理解的回溯没有本质的区别 只不过回溯写法特殊一点 因为往往是记录了 走过的路径 以及这些路径的特殊要求 比如子集 比如排列和组合。
//如果不要求路径 只要求一个最大值 最小值 一般一个全局变量可以达到目的，其实本质上这个全局变量就和回溯法传入的那个纪录路径的数据结构一样。

class Solution {
    int res = Integer.MAX_VALUE;
    public int coinChange(int[] coins, int amount) {
        if(coins.length == 0){
            return -1;
        }
        findWay(coins,amount,0);
       // 如果没有任何一种硬币组合能组成总金额，返回 -1。
        if(res == Integer.MAX_VALUE){
            return -1;
        }
        return res;
    }

    public void findWay(int[] coins,int amount,int count){
        if(amount < 0){
            return;
        }
        if(amount == 0){
            res = Math.min(res,count);
        }
        for(int i = 0;i < coins.length;i++){
            findWay(coins,amount-coins[i],count+1);
        }
    }
}

class Solution {  
    int[] memo;
    // int res = Integer.MAX_VALUE; // why can't put res outside
    public int coinChange(int[] coins, int amount) {
        memo = new int[amount + 1];
        return  coinChangeHelper(coins, amount);   
    }
    
    public int coinChangeHelper(int[] coins, int amount){
        if (amount < 0) return -1;
        if(amount == 0) return 0;
        if (memo[amount] != 0){
            return memo[amount];
        }
        int res = Integer.MAX_VALUE;
        for(int i = 0; i < coins.length; i++){
            int value = coinChangeHelper(coins, amount - coins[i]);  
            if (value >= 0){ // don't forget this one. when to compare.
               res = Math.min(res, value + 1);  
            }
        }
        memo[amount] = (res == Integer.MAX_VALUE ? -1 : res);
        return memo[amount];
        // we can't return (res == Integer.MAX_VALUE ? -1 : res) directly
     }
    
}
// 状态转移方程本质是把问题转换成一维或者二维数组中格子和格子之间的关系。本格子可以由前面的哪些个格子决定。
class Solution {    
    // int res = Integer.MAX_VALUE; // why can't put res outside
    public int coinChange(int[] coins, int amount) {
            if(coins.length == 0){
            return -1;
        }
         int[] memo = new int[amount + 1];
         memo[0] = 0;   
         for (int i = 1; i <= amount; i ++) { // start from i = 1
             int min = Integer.MAX_VALUE; 
             // 每个状态之间互相不影响  所以他们的最小值也不能互相影响。
             //但是考虑到动态规划 状态和状态直接是可以转移的
             for (int j = 0; j < coins.length; j ++){
               if(i - coins[j] >= 0 && memo[i-coins[j]] < min){ 
                   // 考虑到做了选择后要如何，以及选择之后的状态如何转移 i - coins[j] 是做选择
                    min = memo[i-coins[j]] + 1;
                }
             }
             memo[i] = min;
         } 
        return memo[amount] == Integer.MAX_VALUE ? -1 : memo[amount];
    }
}

// 138. Copy List with Random Pointer
class Solution {
    public Node copyRandomList(Node head) {
        HashMap<Node, Node> map = new HashMap<>();
        Node cur = head;
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next; 
        }
        cur = head;
        while (cur != null) {
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
       return map.get(head);
    }
}

// 297. Serialize and Deserialize Binary Tree
public class Codec {

    // Encodes a tree to a single string.
    public String spliter = ",";
    public String nulval = "null";
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        if(root == null) return sb.append(nulval).toString();
        serializeHelper(root,sb);
        return sb.toString();
    }
    
    public void serializeHelper(TreeNode node, StringBuilder sb) {
        if(node == null) {
            sb.append(nulval).append(spliter);
            return;
        }
        sb.append(node.val).append(spliter);
        serializeHelper(node.left, sb);
        serializeHelper(node.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        Queue<String> queue = new LinkedList<>();
        queue.addAll(Arrays.asList(data.split(spliter)));
        return deserializeHelper(queue);
    }
    public TreeNode deserializeHelper(Queue<String> queue) {
        String cur = queue.poll();
        if (cur.equals(nulval)) {
            return null;
        } 
        TreeNode node = new TreeNode(Integer.valueOf(cur));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);
        return node; 
    }
}


```
