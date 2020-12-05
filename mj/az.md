## Dec. 2020

### 937 Reorder Data in Log Files
[自定义重写comparator](https://leetcode-cn.com/problems/reorder-data-in-log-files/solution/zhong-xin-pai-lie-ri-zhi-wen-jian-by-leetcode/)

```java
class Solution {
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            // 分成标识符和logs两部分
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) {
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0) return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : -1;
        });
        return logs;
    }
}
```

### 763 Partition Labels
[贪心算法](https://leetcode-cn.com/problems/partition-labels/solution/hua-fen-zi-mu-qu-jian-by-leetcode-solution/)

### 1192 Critical Connections in a Network
[tarjan算法DFS实现](https://leetcode-cn.com/problems/critical-connections-in-a-network/solution/1192-java-dfstarjansuan-fa-shi-jian-fu-za-du-ove-b/)

### 692 Top K Frequent Words
[小根堆](https://leetcode-cn.com/problems/top-k-frequent-words/solution/qian-kge-gao-pin-dan-ci-by-leetcode/)

### 588 Design In-Memory File System
[实现ls/mkdir/addContentToFile/readContentFromFile](https://leetcode.com/problems/design-in-memory-file-system/solution/)
[简洁实现](https://leetcode.com/problems/design-in-memory-file-system/discuss/861440/The-most-clean-and-easiest-Java-solution.)

### 547 Friend Circles
[BFS/DFS/Union Find](https://app.gitbook.com/@guilindev/s/interview/leetcode/gao-ji-de-shu-ju-jie-gou-he-suan-fa#547-friend-circles)

### 1335 Minimum Difficulty of a Job Schedule   
[工作计划的最低难度，DP](https://leetcode-cn.com/problems/minimum-difficulty-of-a-job-schedule/solution/dong-tai-gui-hua-zi-di-xiang-shang-by-yue-ke-2/) 
[逆序遍历工作，可以1D的DP](https://leetcode-cn.com/problems/minimum-difficulty-of-a-job-schedule/solution/dong-tai-gui-hua-yi-wei-shu-zu-you-hua-by-shuwan12/)

### 49 Group Anagrams
[49 Group Anagrams](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#49-group-anagrams)

### 1152 Analyze User Website Visit Pattern 
用户网站访问行为分析，主要是了解题意

1. Sort all the entries using their timestamp as we need to consider that as well.
2. Now create a list of websites visited by particular User ( added based on timestamp order as all entries sorted in first step)
3. Now generate 3 websites sequence for that user and generate a set to we can avoid duplicate sequence.
4. Now calculate the frequencey of each sequence
5. Get the most used sequence

```java
class Solution {
    // 创建包含三个attrs的类
    static class Visit {
        String userName;
        int timestamp;
        String website;

        Visit(String u, int t, String w) {
            userName = u;
            timestamp = t;
            website = w;
        }

        Visit() {
        }
    }

    public List<String> mostVisitedPattern(String[] username, int[] timestamp, String[] website) {

        // Convert all the entry as visit object to ease of understand
        List<Visit> visitList = new ArrayList<>();
        for (int i = 0; i < username.length; i++) {

            visitList.add(new Visit(username[i], timestamp[i], website[i]));
        }

        // Sort all the visit entries using their timestamp
        Comparator<Visit> cmp = Comparator.comparingInt(v -> v.timestamp);
        Collections.sort(visitList, cmp);

        //Collect list of websites for each user
        Map<String, List<String>> userWebSitesMap = new HashMap<>();
        for (Visit v : visitList) {
            userWebSitesMap.putIfAbsent(v.userName, new ArrayList<>());
            userWebSitesMap.get(v.userName).add(v.website);
        }

        Map<List<String>, Integer> seqUserFreMap = new HashMap<>();
        // Now get all the values of all the users
        for (List<String> websitesList : userWebSitesMap.values()) {
            if (websitesList.size() < 3)
                continue; // no need to consider less than 3 entries of web site visited by user
            Set<List<String>> sequencesSet = generate3Seq(websitesList);
            // Now update the frequency of the sequence ( increment by 1 for 1 user)
            for (List<String> seq : sequencesSet) {
                seqUserFreMap.putIfAbsent(seq, 0);
                seqUserFreMap.put(seq, seqUserFreMap.get(seq) + 1);
            }
        }

        List<String> res = new ArrayList<>();
        int MAX = 0;
        for (Map.Entry<List<String>, Integer> entry : seqUserFreMap.entrySet()) {
            if (entry.getValue() > MAX) {
                MAX = entry.getValue();
                res = entry.getKey();
            } else if (entry.getValue() == MAX) {
                if (entry.getKey().toString().compareTo(res.toString()) < 0) {
                    res = entry.getKey();
                }
            }
        }
        return res;
    }

    // It will not return duplicate seq for each user that why we are using Set
    private Set<List<String>> generate3Seq(List<String> websitesList) {
        Set<List<String>> setOfListSeq = new HashSet<>();
        for (int i = 0; i < websitesList.size(); i++) {
            for (int j = i + 1; j < websitesList.size(); j++) {
                for (int k = j + 1; k < websitesList.size(); k++) {
                    List<String> list = new ArrayList<>();
                    list.add(websitesList.get(i));
                    list.add(websitesList.get(j));
                    list.add(websitesList.get(k));
                    setOfListSeq.add(list);
                }
            }
        }
        return setOfListSeq;
    }
}
```

### 994 Rotting Oranges
[BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-3#994-rotting-oranges)

### 460 LFU Cache
[LinkedHashSet](https://app.gitbook.com/@guilindev/s/interview/leetcode/gao-ji-de-shu-ju-jie-gou-he-suan-fa#460-lfu)

### 103 Binary Tree Zigzag Level Order Traversal
[BFS或者DFS判断奇偶](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#103-binary-tree-zigzag-level-order-traversal)

### 212 Word Search II
[Trie](https://app.gitbook.com/@guilindev/s/interview/leetcode/trie-qian-zhui-shu#212-word-search-ii)

### 210 Course Schedule II
[拓扑排序](https://app.gitbook.com/@guilindev/s/interview/leetcode/graph#210-course-schedule-ii)

### 726 Number of Atoms
[原子的数量，递归或Stack](https://leetcode-cn.com/problems/number-of-atoms/solution/yuan-zi-de-shu-liang-by-leetcode/)

### 185 Department Top Three Salaries
[SQL部门前三高的工资](https://leetcode-cn.com/problems/department-top-three-salaries/solution/bu-men-gong-zi-qian-san-gao-de-yuan-gong-by-leetco/)
```sql
SELECT
    d.Name AS 'Department', e1.Name AS 'Employee', e1.Salary
FROM
    Employee e1
        JOIN
    Department d ON e1.DepartmentId = d.Id
WHERE
    3 > (SELECT
            COUNT(DISTINCT e2.Salary)
        FROM
            Employee e2
        WHERE
            e2.Salary > e1.Salary
                AND e1.DepartmentId = e2.DepartmentId
        )
;
```

### 682 Baseball Game
[利用Stack](https://leetcode-cn.com/problems/baseball-game/solution/bang-qiu-bi-sai-by-leetcode/)

### 901 Online Stock Span
[股票价格跨度 - 单调栈](https://leetcode-cn.com/problems/online-stock-span/solution/gu-piao-jie-ge-kua-du-by-leetcode/)

### 1167 Minimum Cost to Connect Sticks * 
[连接棍子的最低费用 - 贪心算法](https://leetcode.com/problems/minimum-cost-to-connect-sticks/solution/)
```java
class Solution {
    public int connectSticks(int[] sticks) {
        int totalCost = 0;
 
        PriorityQueue<Integer> pq = new PriorityQueue<>();
 
        // add all sticks to the min heap.
        for (int stick : sticks) {
            pq.add(stick);
        }
 
        // combine two of the smallest sticks until we are left with just one.
        while (pq.size() > 1) {
            int stick1 = pq.remove();
            int stick2 = pq.remove();
            
            int cost = stick1 + stick2;
            totalCost += cost;
            
            pq.add(stick1 + stick2);
        }
 
        return totalCost;
    }
}
```

### 1120 Maximum Average Subtree * 
[求子树元素的最大平均值 - 后序遍历](https://leetcode.com/problems/maximum-average-subtree/solution/)
```java
class Solution {
    // for each node in the tree, we will maintain three values
    class State {
        // count of nodes in the subtree
        int nodeCount;
        
        // sum of values in the subtree 
        int valueSum;
        
        // max average found in the subtree
        double maxAverage;

        State(int nodes, int sum, double maxAverage) {
            this.nodeCount = nodes;
            this.valueSum = sum;
            this.maxAverage = maxAverage;
        }
    }

    public double maximumAverageSubtree(TreeNode root) {
        return maxAverage(root).maxAverage;
    }

    State maxAverage(TreeNode root) {
        if (root == null) {
            return new State(0, 0, 0);
        }
        
        // postorder traversal, solve for both child nodes first.
        State left = maxAverage(root.left);
        State right = maxAverage(root.right);

        // now find nodeCount, valueSum and maxAverage for current node `root`
        int nodeCount = left.nodeCount + right.nodeCount + 1;
        int sum = left.valueSum + right.valueSum + root.val;
        double maxAverage = Math.max(
                (1.0 * (sum)) / nodeCount, // average for current node
                Math.max(right.maxAverage, left.maxAverage) // max average from child nodes
        );
        
        return new State(nodeCount, sum, maxAverage);
    }
}
```

### 1010 Pairs of Songs With Total Durations Divisible by 60
[歌曲列表总时间可被60整除的歌曲](https://leetcode-cn.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/solution/java-2ms-ji-bai-10000-by-keen0126/)

### 819 Most Common Word
[最常见的单词并且不在禁用列表中](https://leetcode-cn.com/problems/most-common-word/solution/zui-chang-jian-de-dan-ci-by-leetcode/)

### 957 Prison Cells After N Days
[N天后的牢房状态 - 模拟](https://leetcode-cn.com/problems/prison-cells-after-n-days/solution/n-tian-hou-de-lao-fang-by-leetcode/)

### 123 Best Time to Buy and Sell Stock III
[股票买卖 - 2次交易](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#123-best-time-to-buy-and-sell-stock-iii)

### 437 Path Sum III
[找出路径总数 - 前缀和+回溯](https://leetcode-cn.com/problems/path-sum-iii/solution/qian-zhui-he-di-gui-hui-su-by-shi-huo-de-xia-tian/)

### 472 Concatenated Words
[连接词是给定dict中至少两个单词连接而成的新词 - Trie](https://leetcode-cn.com/problems/concatenated-words/solution/472-lian-jie-ci-java-trie-dfs-by-mmmmmmosky/)

### 1597 Build Binary Expression Tree From Infix Expression * 
[二叉表达式树Recursion + 2 Passes](https://leetcode.com/problems/build-binary-expression-tree-from-infix-expression/discuss/861513/Recursion-%2B-2-Passes)
The tricky part of this question is the data structure. I decided I would convert all characters to nodes, and store them in a list.

    Conversion with recursion:
        Convert all characters into nodes and store them in list l.
        Recursion: if we detect a group (...), call expTree recursively and insert the returned tree root into l.
    Pass 1 for elements in l:
        For * and / leaf nodes, 'adopt' left and right neighbors to be its children (neighbors are removed from the list).
    Pass 2 for the remaining elements in l:
        Do the same as in pass 1 for + and - leaf nodes.

After this, we should have one element remaining in l - our root node.
```java
class Solution {
    public Node expTree(String s) {
        if(s.isEmpty()) return null;
        Deque<Node> deque = new ArrayDeque<>();
        for(int i=0;i<s.length();i++){
            if(s.charAt(i) == '('){
                int j = i+1;
                for(int bal=1; j<s.length(); j++){
                    if(s.charAt(j) =='(') bal++;
                    else if(s.charAt(j) ==')') bal--;
                    if(bal == 0) break;
                }
                Node tmp = expTree(s.substring(i+1, j));
                if(tmp !=null) deque.add(tmp);
                i = j;
            } else {
                deque.add(new Node(s.charAt(i)));
            }
        }
        return helper(helper(deque, '*', '/'), '+', '-').poll();
    }
    private Deque<Node> helper(Deque<Node> deque, char op1, char op2){
        if(deque.isEmpty()) return null;
        Deque<Node> tmp = new ArrayDeque<>();
        tmp.offer(deque.poll());
        while(!deque.isEmpty()){
            Node oper = deque.poll();
            if(oper.left == null && (oper.val == op1 || oper.val == op2)){
                oper.left = tmp.pollLast();
                oper.right = deque.poll();
            }
            tmp.offer(oper);
        }
        return tmp;
    }
}
```

### 1520 Maximum Number of Non-Overlapping Substrings
[最多不重叠的子字符串 - 贪心](https://leetcode-cn.com/problems/maximum-number-of-non-overlapping-substrings/solution/zui-duo-de-bu-zhong-die-zi-zi-fu-chuan-by-leetcode/)

### 126 Word Ladder II
[单词接龙II - BFS](https://leetcode-cn.com/problems/word-ladder-ii/solution/dan-ci-jie-long-ii-by-leetcode-solution/)

### 733 Flood Fill
[图像渲染 - BFS/DFS](https://leetcode-cn.com/problems/flood-fill/solution/tu-xiang-xuan-ran-by-leetcode-solution/)

### 121 Best Time to Buy and Sell Stock
[股票买卖 - 只可买一次](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#121-best-time-to-buy-and-sell-stock)

### 735 Asteroid Collision
[行星碰撞正负表示运行方向 - Stack存储](https://leetcode-cn.com/problems/asteroid-collision/solution/xing-xing-peng-zhuang-by-leetcode/)

### 323 Number of Connected Components in an Undirected Graph
[图中的连通分量 - BFS/DFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/dfs#323-number-of-connected-components-in-an-undirected-graph)

### 496 Next Greater Element I
[一个array是另一个的子集，找子集元素在母集中的最大元素 - 单调栈](https://leetcode-cn.com/problems/next-greater-element-i/solution/xia-yi-ge-geng-da-yuan-su-i-by-leetcode/)

### 353 Design Snake Game *
[设计贪吃蛇 - Queue和HashSet](https://leetcode.com/problems/design-snake-game/solution/)
```java
class SnakeGame {

HashMap<Pair<Integer, Integer>, Boolean> snakeMap;
Deque<Pair<Integer, Integer>> snake;
int[][] food;
int foodIndex;
int width;
int height;

/**
 * Initialize your data structure here.
 *
 * @param width - screen width
 * @param height - screen height
 * @param food - A list of food positions E.g food = [[1,1], [1,0]] means the first food is
 *     positioned at [1,1], the second is at [1,0].
 */
public SnakeGame(int width, int height, int[][] food) {
    this.width = width;
    this.height = height;
    this.food = food;
    this.snakeMap = new HashMap<Pair<Integer, Integer>, Boolean>();
    this.snakeMap.put(new Pair<Integer, Integer>(0,0), true); // intially at [0][0]
    this.snake = new LinkedList<Pair<Integer, Integer>>();
    this.snake.offerLast(new Pair<Integer, Integer>(0,0));
}

/**
 * Moves the snake.
 *
 * @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
 * @return The game's score after the move. Return -1 if game over. Game over when snake crosses
 *     the screen boundary or bites its body.
 */
public int move(String direction) {

    Pair<Integer, Integer> snakeCell = this.snake.peekFirst();
    int newHeadRow = snakeCell.getKey();
    int newHeadColumn = snakeCell.getValue();

    switch (direction) {
    case "U":
        newHeadRow--;
        break;
    case "D":
        newHeadRow++;
        break;
    case "L":
        newHeadColumn--;
        break;
    case "R":
        newHeadColumn++;
        break;
    }

    Pair<Integer, Integer> newHead = new Pair<Integer, Integer>(newHeadRow, newHeadColumn);
    Pair<Integer, Integer> currentTail = this.snake.peekLast();

    // Boundary conditions.
    boolean crossesBoundary1 = newHeadRow < 0 || newHeadRow >= this.height;
    boolean crossesBoundary2 = newHeadColumn < 0 || newHeadColumn >= this.width;

    // Checking if the snake bites itself.
    boolean bitesItself = this.snakeMap.containsKey(newHead) && !(newHead.getKey() == currentTail.getKey() && newHead.getValue() == currentTail.getValue());
    
    // If any of the terminal conditions are satisfied, then we exit with rcode -1.
    if (crossesBoundary1 || crossesBoundary2 || bitesItself) {
        return -1;
    }

    // If there's an available food item and it is on the cell occupied by the snake after the move,
    // eat it.
    if ((this.foodIndex < this.food.length)
        && (this.food[this.foodIndex][0] == newHeadRow)
        && (this.food[this.foodIndex][1] == newHeadColumn)) {
        this.foodIndex++;
    } else {
        this.snake.pollLast();
        this.snakeMap.remove(currentTail);
    }

    // A new head always gets added
    this.snake.addFirst(newHead);

    // Also add the head to the set
    this.snakeMap.put(newHead, true);

    return this.snake.size() - 1;
}
    
}

/**
 * Your SnakeGame object will be instantiated and called as such: SnakeGame obj = new
 * SnakeGame(width, height, food); int param_1 = obj.move(direction);
 */
```

### 681 Next Closest Time *
[根据现在时刻的数字计算下一个最近的时刻 - 直接模拟](https://leetcode.com/problems/next-closest-time/solution/)
```java
class Solution {
    public String nextClosestTime(String time) {
        int cur = 60 * Integer.parseInt(time.substring(0, 2));
        cur += Integer.parseInt(time.substring(3));
        Set<Integer> allowed = new HashSet();
        for (char c: time.toCharArray()) if (c != ':') {
            allowed.add(c - '0');
        }

        while (true) {
            cur = (cur + 1) % (24 * 60);
            int[] digits = new int[]{cur / 60 / 10, cur / 60 % 10, cur % 60 / 10, cur % 60 % 10};
            search : {
                for (int d: digits) if (!allowed.contains(d)) break search;
                return String.format("%02d:%02d", cur / 60, cur % 60);
            }
        }
    }
}
```

### 403 Frog Jump
[能否跳到最后一个元素上 - 两种DP](https://leetcode-cn.com/problems/frog-jump/solution/qing-wa-guo-he-by-leetcode/)

### 1328 Break a Palindrome
[生成字典序最小的回文串 - 检查前半截](https://leetcode-cn.com/problems/break-a-palindrome/solution/java-by-gaaakki-7/)

### 362 Design Hit Counter * 
[点击计数器 - 队列](https://leetcode.com/problems/design-hit-counter/discuss/83505/Simple-Java-Solution-with-explanation)
```java
class Solution {
    public class HitCounter {
        Queue<Integer> q = null;
        /** Initialize your data structure here. */
        public HitCounter() {
            q = new LinkedList<Integer>();
        }
        
        /** Record a hit.
            @param timestamp - The current timestamp (in seconds granularity). */
        public void hit(int timestamp) {
            q.offer(timestamp);
        }
        
        /** Return the number of hits in the past 5 minutes.
            @param timestamp - The current timestamp (in seconds granularity). */
        public int getHits(int timestamp) {
            while(!q.isEmpty() && timestamp - q.peek() >= 300) {
                q.poll();
            }
            return q.size();
        }
    }
}
```

### 1479 Sales by Day of the Week *
[一周内每天销售情况 - sql](https://leetcode.com/problems/sales-by-day-of-the-week/discuss/686155/MySql-solution)
```sql
SELECT i.item_category AS Category,
    SUM(CASE WHEN DAYOFWEEK(o.order_date) = 2 THEN quantity ELSE 0 END) AS Monday,
    SUM(CASE WHEN DAYOFWEEK(o.order_date) = 3 THEN quantity ELSE 0 END) AS Tuesday,
    SUM(CASE WHEN DAYOFWEEK(o.order_date) = 4 THEN quantity ELSE 0 END) AS Wednesday,
    SUM(CASE WHEN DAYOFWEEK(o.order_date) = 5 THEN quantity ELSE 0 END) AS Thursday,
    SUM(CASE WHEN DAYOFWEEK(o.order_date) = 6 THEN quantity ELSE 0 END) AS Friday,
    SUM(CASE WHEN DAYOFWEEK(o.order_date) = 7 THEN quantity ELSE 0 END) AS Saturday,
    SUM(CASE WHEN DAYOFWEEK(o.order_date) = 1 THEN quantity ELSE 0 END) AS Sunday
FROM Items i
LEFT JOIN Orders o
ON i.item_id = o.item_id
GROUP BY i.item_category
ORDER BY i.item_category;
```

### 188 Best Time to Buy and Sell Stock IV
[股票买卖 - k笔交易DP](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/solution/si-chong-jie-fa-tu-jie-188mai-mai-gu-piao-de-zui-j/)

### 1429 First Unique Number *
[一队列的数字中找第一个出现一次的数字 - Queue/HashMap](https://leetcode.com/problems/first-unique-number/solution/)
```java
class FirstUnique {

  private Queue<Integer> queue = new ArrayDeque<>();
  private Map<Integer, Boolean> isUnique = new HashMap<>();

  public FirstUnique(int[] nums) {
    for (int num : nums) {
      // Notice that we're calling the "add" method of FirstUnique; not of the queue. 
      this.add(num);
    }
  }

  public int showFirstUnique() {
    // We need to start by "cleaning" the queue of any non-uniques at the start.
    // Note that we know that if a value is in the queue, then it is also in
    // isUnique, as the implementation of add() guarantees this.
    while (!queue.isEmpty() && !isUnique.get(queue.peek())) {
      queue.remove();
    }
    // Check if there is still a value left in the queue. There might be no uniques.
    if (!queue.isEmpty()) {
      return queue.peek(); // We don't want to actually *remove* the value.
    }
    return -1;
  }

  public void add(int value) {
    // Case 1: We need to add the number to the queue and mark it as unique. 
    if (!isUnique.containsKey(value)) {
      isUnique.put(value, true);
      queue.add(value);
    // Case 2 and 3: We need to mark the number as no longer unique.
    } else {
      isUnique.put(value, false);
    }
  }
}
```

### 1268 Search Suggestions System
[搜索推荐系统 - Trie/二分查找](https://leetcode-cn.com/problems/search-suggestions-system/solution/suo-tui-jian-xi-tong-by-leetcode-solution/)

### 45 Jump Game II
[贪心](https://app.gitbook.com/@guilindev/s/interview/leetcode/greedy#45-jump-game-ii)

### 84 Largest Rectangle in Histogram
[栈](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/array-zhong-de-shu-zi-zu-cheng-san-jiao-xing#84-largest-rectangle-in-histogram)

### 815 Bus Routes
[图的BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag#815-bus-route)

### 706 Design HashMap
[设计哈希表 - 数组+取模](https://leetcode-cn.com/problems/design-hashmap/solution/she-ji-ha-xi-biao-by-leetcode/)

### 518 Coin Change II
[求所有的钱币组合数 - DP](https://leetcode-cn.com/problems/coin-change-2/solution/ling-qian-dui-huan-ii-by-leetcode/)

### 1166 Design File System *
[创建Path和获取路径 - HashMap/Trie](https://leetcode.com/problems/design-file-system/discuss/366094/JavaPython-TrieTree-solution)
```java
class FileSystem {
    class File{
        String name;
        int val = -1;
        Map<String, File> map = new HashMap<>();
        
        File(String name){
            this.name = name;
        }
    }
    File root;
    public FileSystem() {
        root = new File("");
    }
    
    public boolean create(String path, int value) {
        String[] array = path.split("/");
        File cur = root;
        
        for(int i=1;i<array.length;i++){
            String cur_name = array[i];
            if(cur.map.containsKey(cur_name)==false){
                if(i==array.length-1){
                    cur.map.put(cur_name, new File(cur_name));
                }else{
                    return false;
                }
            }
            cur = cur.map.get(cur_name);
        }
        
        if(cur.val!=-1){
            return false;
        }
        
        cur.val = value;
        return true;
    }
    
    public int get(String path) {
        String[] array = path.split("/");
        File cur = root;
        for(int i=1;i<array.length;i++){
            String cur_name = array[i];
            if(cur.map.containsKey(cur_name)==false){
                return -1;
            }
            cur = cur.map.get(cur_name);
        }
        
        return cur.val;
        
        
    }
}

/**
 * Your FileSystem object will be instantiated and called as such:
 * FileSystem obj = new FileSystem();
 * boolean param_1 = obj.create(path,value);
 * int param_2 = obj.get(path);
 */

```

### 503 Next Greater Element II
[只有一个循环数组 - 单调栈](https://leetcode-cn.com/problems/next-greater-element-ii/solution/xia-yi-ge-geng-da-yuan-su-ii-by-leetcode/)

### 1315 Sum of Nodes with Even-Valued Grandparent
[祖父节点数为偶数 - 层序遍历](https://leetcode-cn.com/problems/sum-of-nodes-with-even-valued-grandparent/solution/ceng-xu-bian-li-by-liweiwei1419/)

### 1293 Shortest Path in a Grid with Obstacles Elimination
[网格中最短路径 - BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag0#1293-shortest-path-in-a-grid-with-obstacles-elimination)

### 1011 Capacity To Ship Packages Within D Days
[D天内送达包裹 - 二分搜索](https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/solution/zai-dtian-nei-song-da-bao-guo-de-neng-li-by-lenn12/)

### 466 Count The Repetitions
[字符串+整数生成另一个字符串 - 找出循环体](https://leetcode-cn.com/problems/count-the-repetitions/solution/tong-ji-zhong-fu-ge-shu-by-leetcode-solution/)

### 642 Design Search Autocomplete System
[Trie解法](https://app.gitbook.com/@guilindev/s/interview/leetcode/design#642-design-search-autocomplete-system)

### 502 IPO
[贪心解法](https://leetcode-cn.com/problems/ipo/solution/ipo-by-leetcode-3/)

### 316 Remove Duplicate Letters
[去重使字典序最小](https://leetcode-cn.com/problems/remove-duplicate-letters/solution/qu-chu-zhong-fu-zi-mu-by-leetcode/)

### 564 Find the Closest Palindrome
[一个整数的最近回文数 - 获取前一半](https://leetcode-cn.com/problems/find-the-closest-palindrome/solution/qu-de-qian-mian-yi-ban-de-shu-ju-bing-qie-fan-zhua/)

### 895 Maximum Frequency Stack
[实现一个栈可以去最多的数 - hashmap](https://leetcode-cn.com/problems/maximum-frequency-stack/solution/zui-da-pin-lu-zhan-by-leetcode/)

### 1258 Synonymous Sentences *
[近义词的句子 BFS](https://leetcode.com/problems/synonymous-sentences/discuss/430604/Java-BFS-Solution-Picture-Explain-Clean-code)
```java
class Solution {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> pair : synonyms) {
            String w1 = pair.get(0), w2 = pair.get(1);
            connect(graph, w1, w2);
            connect(graph, w2, w1);
        }
        // BFS
        Set<String> ans = new TreeSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(text);
        while (!q.isEmpty()) {
            String out = q.remove();
            ans.add(out); // Add to result
            String[] words = out.split("\\s");
            for (int i = 0; i < words.length; i++) {
                if (graph.get(words[i]) == null) continue;
                for (String synonym : graph.get(words[i])) { // Replace words[i] with its synonym
                    words[i] = synonym;
                    String newText = String.join(" ", words);
                    if (!ans.contains(newText)) q.add(newText);
                }
            }
        }
        return new ArrayList<>(ans);
    }
    void connect(Map<String, List<String>> graph, String v1, String v2) {
        graph.putIfAbsent(v1, new LinkedList<>());
        graph.get(v1).add(v2);
    }
}
```

### 1416 Restore The Array
[一连串数恢复成数组 - 从右向左DP](https://leetcode-cn.com/problems/restore-the-array/solution/hui-fu-shu-zu-by-leetcode-solution/)

### 233 Number of Digit One
[比一个正整数小的所有数出现1的次数 - 组合数学](https://leetcode-cn.com/problems/number-of-digit-one/solution/shu-zi-1-de-ge-shu-by-leetcode/)

### 1628 Design an Expression Tree With Evaluate Function * 
[二叉树表达式带符号 Stack](https://leetcode.com/problems/design-an-expression-tree-with-evaluate-function/discuss/933995/Java-Stack-%2B-OOP)
```java
abstract class Node {
    public abstract int evaluate();
    // define your fields here  
};

class TreeNode extends Node{
    // Constructor 
    String val;
    TreeNode left;
    TreeNode right;
    TreeNode(String val) {
        this.val = val;
    }
    
    // Abstract method cannot have body; so fill in body here.
    public int evaluate() {
        return dfs(this); // use this keyword for current TreeNode object.
    }
    
    private int dfs(TreeNode root) {
        if (root.left == null && root.right == null) {
            return Integer.valueOf(root.val);
        }
        int left = dfs(root.left);
        int right = dfs(root.right);
        String operator = root.val;
        int res = 0;
        if (operator.equals("+")) {
            res = left + right;
        } else if (operator.equals("-")) {
            res = left - right;
        } else if (operator.equals("*")) {
            res = left * right;
        } else {
            res = left / right;
        }
        return res;
    }
    
};


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input 
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
    String operators = "+-*/";
    Stack<TreeNode> stack = new Stack<>();
    Node buildTree(String[] postfix) {
        for (String str : postfix) {
            if (operators.contains(str)) {
                TreeNode cur = new TreeNode(str);
                cur.right = stack.pop();
                cur.left = stack.pop();
                stack.push(cur);
            } else {
                stack.push(new TreeNode(str));
            }
        }
        return stack.pop();
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder obj = new TreeBuilder();
 * Node expTree = obj.buildTree(postfix);
 * int ans = expTree.evaluate();
 */

```

### 854 K-Similar Strings
[交换k次的相似字符串 - BFS](https://leetcode.com/problems/k-similar-strings/discuss/140099/JAVA-BFS-32-ms-cleanconciseexplanationwhatever)

### 1155 Number of Dice Rolls With Target Sum
[掷骰子n次组合成一个整数的总组合 - DP](https://leetcode-cn.com/problems/number-of-dice-rolls-with-target-sum/solution/zuo-ti-guo-cheng-ji-lu-dpjie-fa-by-maverickbytes/)

### 95 Unique Binary Search Trees II
[打印1~n组成的BST](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#95-unique-binary-search-trees-ii)

### 262 Trips and Users
[行程和用户SQL](https://leetcode-cn.com/problems/trips-and-users/solution/san-chong-jie-fa-cong-nan-dao-yi-zong-you-gua-he-n/)

### 797 All Paths From Source to Target
[DAG中的所有路径 DFS](https://leetcode-cn.com/problems/all-paths-from-source-to-target/solution/suo-you-ke-neng-de-lu-jing-by-leetcode/)

### 1102 Path With Maximum Minimum Value * 
[矩阵中得分最高的路径 Dijkstra](https://leetcode.com/problems/path-with-maximum-minimum-value/discuss/324923/Clear-Code-Dijkstra-Algorithm-(C%2B%2BJavaPythonGoPHP))
```java
class Solution {
    public int maximumMinimumPath(int[][] A) {
        final int[][] DIRS = {{0,1},{1,0},{0,-1},{-1,0}};
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(b[0], a[0]));
        pq.add(new int[] {A[0][0], 0, 0});
        int maxscore = A[0][0];
        A[0][0] = -1; // visited
        while(!pq.isEmpty()) {
            int[] top = pq.poll();
            int i = top[1], j = top[2], n = A.length, m = A[0].length;
            maxscore = Math.min(maxscore, top[0]);
            if(i == n - 1 && j == m - 1)
                break;
            for(int[] d : DIRS) {
                int newi = d[0] + i, newj = d[1] + j;
                if(newi >= 0 && newi < n && newj >= 0 && newj < m && A[newi][newj]>=0){
                    pq.add(new int[] {A[newi][newj], newi, newj});
                    A[newi][newj] = -1;
                }
            }
        }
        return maxscore;
    }
}
```

### 1505 Minimum Possible Integer After at Most K Adjacent Swaps On Digits
[一个整数k次交换相邻数得到最小数 - 贪心](https://leetcode-cn.com/problems/minimum-possible-integer-after-at-most-k-adjacent-swaps-on-digits/solution/zui-duo-k-ci-jiao-huan-xiang-lin-shu-wei-hou-de-da/)

### 99 Recover Binary Search Tree
[两个数错位的BST的恢复](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#99-recover-binary-search-tree)

### 356 Line Reflection * 
[根据y轴的直线上的镜像](https://leetcode.com/problems/line-reflection/discuss/82968/11ms-two-pass-HashSet-based-Java-Solution)
```java
public class Solution {
    public boolean isReflected(int[][] points) {
        HashSet<Integer> pointSet = new HashSet<>();
        int sum;
        int maxX, minX;
        
        minX = Integer.MAX_VALUE;
        maxX = Integer.MIN_VALUE;
        for(int[] point:points) {
            maxX = Math.max(maxX, point[ 0 ]);
            minX = Math.min(minX, point[ 0 ]);
            pointSet.add(Arrays.hashCode(point));
        }
        
        sum = maxX+minX;
        for(int[] point:points) {
            if(!pointSet.contains(Arrays.hashCode(new int[]{sum-point[ 0 ], point[ 1 ]}))) {
                return false;
            }
        }
        return true;
    }
}
```

### 1139 Largest 1-Bordered Square
[边界上全为1的子网格的元素数 - 2d DP](https://leetcode.com/problems/largest-1-bordered-square/discuss/345334/clean-JAVA-DP-over-100-!-just-to-store-the-maximum-possibility)

### 471 Encode String with Shortest Length *
[压缩字符串 - 2d DP](https://leetcode.com/problems/encode-string-with-shortest-length/discuss/95599/Accepted-Solution-in-Java)
```java
public class Solution {

    public String encode(String s) {
        String[][] dp = new String[s.length()][s.length()];
        
        for(int l=0;l<s.length();l++) {
            for(int i=0;i<s.length()-l;i++) {
                int j = i+l;
                String substr = s.substring(i, j+1);
                // Checking if string length < 5. In that case, we know that encoding will not help.
                if(j - i < 4) {
                    dp[i][j] = substr;
                } else {
                    dp[i][j] = substr;
                    // Loop for trying all results that we get after dividing the strings into 2 and combine the   results of 2 substrings
                    for(int k = i; k<j;k++) {
                        if((dp[i][k] + dp[k+1][j]).length() < dp[i][j].length()){
                            dp[i][j] = dp[i][k] + dp[k+1][j];
                        }
                    }
                    
                    // Loop for checking if string can itself found some pattern in it which could be repeated.
                    for(int k=0;k<substr.length();k++) {
                        String repeatStr = substr.substring(0, k+1);
                        if(repeatStr != null 
                        && substr.length()%repeatStr.length() == 0 
                        && substr.replaceAll(repeatStr, "").length() == 0) {
                            String ss = substr.length()/repeatStr.length() + "[" + dp[i][i+k] + "]";
                            if(ss.length() < dp[i][j].length()) {
                                dp[i][j] = ss;
                            }
                        }
                    }
                }
            }
        }
        
        return dp[0][s.length()-1];
    }
}
```

### 85 Maximal Rectangle
[Stack或DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/array-zhong-de-shu-zi-zu-cheng-san-jiao-xing#85-maximum-rectangle)

### 33 Search in Rotated Sorted Array
[无重复数，先确定边界](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zai-array-zhong-cha-xun-yuan-su#33-search-in-rotated-sorted-array)

### 992 Subarrays with K Different Integers
[刚好K个不同数的子数组的总数 - 滑动窗口](https://leetcode-cn.com/problems/subarrays-with-k-different-integers/solution/gen-ju-guan-fang-ti-jie-zheng-li-de-si-lu-by-shang/)

### 909 Snakes and Ladders
[蛇梯棋](https://leetcode-cn.com/problems/snakes-and-ladders/solution/she-ti-qi-by-leetcode/)

### 229 Majority Element II
[两个candidates](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#229-majority-element-ii)

### 135 Candy
[按照rating给小朋友分糖果](https://app.gitbook.com/@guilindev/s/interview/leetcode/greedy#135-candy)

### 1135 Connecting Cities With Minimum Cost *
[带权图，最低成本联通所有城市 Kruskal’s最小生成树](https://leetcode.com/problems/connecting-cities-with-minimum-cost/discuss/344867/Java-Kruskal's-Minimum-Spanning-Tree-Algorithm-with-Union-Find)

* Sort edges to no-descresing order
* Pick the smallest edge that does not form a cycle
* Repeat until MST is formed and every node is connected*.

```java
class Solution {
    
    int[] parent;
    int n;
    
    private void union(int x, int y) {
        int px = find(x);
        int py = find(y);
        
        if (px != py) {
            parent[px] = py;
            n--;
        }
    }
    
    private int find(int x) {
        if (parent[x] == x) {
            return parent[x];
        }
        parent[x] = find(parent[x]); // path compression
        return parent[x];
    }
    
    public int minimumCost(int N, int[][] connections) {
        parent = new int[N + 1];
        n = N;
        for (int i = 0; i <= N; i++) {
            parent[i] = i;
        }
        
        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));
        
        int res = 0;
        
        for (int[] c : connections) {
            int x = c[0], y = c[1];
            if (find(x) != find(y)) {
                res += c[2];
                union(x, y);
            }
        }
        
        return n == 1 ? res : -1;
    }
}
```

### 12 Integer to Roman
[穷举法](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#12-integer-to-roman)

### 410 Split Array Largest Sum
[分割成m个连续非空子数组让每个最大 DP/二分](https://leetcode-cn.com/problems/split-array-largest-sum/solution/fen-ge-shu-zu-de-zui-da-zhi-by-leetcode-solution/)

### 1100 Find K-Length Substrings With No Repeated Characters *
[返回串中所有无重复的子串 - 滑动窗口](https://leetcode.com/problems/find-k-length-substrings-with-no-repeated-characters/discuss/347622/Java-sliding-window-clear-code)
```java
class Solution {
    public int numKLenSubstrNoRepeats(String S, int K) {
        int ans = 0;
        Set<Character> set = new HashSet<>();
        int i = 0;
        
        for (int j = 0; j < S.length(); j++) {
            while (set.contains(S.charAt(j))) {
                set.remove(S.charAt(i++));
            }
            set.add(S.charAt(j));
            
            if (j - i + 1 == K) {
                ans++;
                set.remove(S.charAt(i++));
            }
        }
        return ans;
    }
}
```

### 37 Sudoku Solver
[回溯](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#37-sudoku-solver)

### 113 Path Sum II
[DFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#113-path-sum-ii)

### 1031 Maximum Sum of Two Non-Overlapping Subarrays
[将数组分成两个不重叠有最大值的子数组](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag0#1031-maximum-sum-of-two-non-overlapping-subarrays)

### 829 Consecutive Numbers Sum
[一个整数由连续整数相加得到的方法数 - 数学法](https://leetcode.com/problems/consecutive-numbers-sum/discuss/128959/JavaPython-3-5-liners-O(N-0.5)-Math-method-w-explanation-and-analysis.)

### 1130 Minimum Cost Tree From Leaf Values
[用一个正整数生成一个让非叶子节点最小值的二叉树 - 区间DP](https://leetcode-cn.com/problems/minimum-cost-tree-from-leaf-values/solution/java-dp-by-ke-xue-jia-12/)

### 615 Average Salary: Departments VS Company
[部门与公司的平均工资](https://leetcode.com/problems/average-salary-departments-vs-company/discuss/104242/My-solution-using-LEFT-JOIN)
```sql
SELECT d1.pay_month, d1.department_id, 
CASE WHEN d1.department_avg > c1.company_avg THEN 'higher'
     WHEN d1.department_avg < c1.company_avg THEN 'lower'
     ELSE 'same'
END AS 'comparison'
FROM ((SELECT LEFT(s1.pay_date, 7) pay_month, e1.department_id, AVG(s1.amount) department_avg
FROM salary s1
JOIN employee e1 ON s1.employee_id = e1.employee_id
GROUP BY pay_month, e1.department_id) d1
LEFT JOIN (SELECT LEFT(pay_date, 7) pay_month, AVG(amount) company_avg
FROM salary
GROUP BY pay_month) c1 ON d1.pay_month = c1.pay_month)
ORDER BY pay_month DESC, department_id;
```

### 354 Russian Doll Envelopes
[长宽的套娃信封 - 排序](https://leetcode-cn.com/problems/russian-doll-envelopes/solution/e-luo-si-tao-wa-xin-feng-wen-ti-by-leetcode/)

### 291 Word Pattern II * 
[HashMap + 回溯](https://leetcode.com/problems/word-pattern-ii/discuss/73721/My-simplified-java-version)
```java
class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        Map<Character, String> map = new HashMap<>();

        return helper(pattern, 0, str, 0, map);
    }

    public boolean helper(String pattern, int pPos, String str, int sPos, Map<Character, String> map) {
        if(sPos == str.length() && pPos == pattern.length()) return true;
        
        if(sPos == str.length() || pPos == pattern.length()) return false;
    
        char c = pattern.charAt(pPos);
        
        for(int i = sPos; i < str.length(); i++) {
            String substr = str.substring(sPos, i+1);
            
            if(map.containsKey(c) && map.get(c).equals(substr) ) {
                if(helper(pattern, pPos+1, str, i+1, map)) return true;
            }
            
            if(!map.containsKey(c) && !map.containsValue(substr) ) {
                map.put(c, substr);
                if(helper(pattern, pPos+1, str, i+1, map)) return true;
                map.remove(c);
            }
        }           
        return false;
    }

}
```

### 1375 Bulb Switcher III
[灯泡开关III 三种颜色，直接模拟](https://leetcode.com/problems/bulb-switcher-iii/discuss/532538/JavaC%2B%2BPython-Straight-Forward-O(1)-Space)

### 1057 Campus Bikes
[排序取出距离](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag4#1057-campus-bikes)

### 889 Construct Binary Tree from Preorder and Postorder Traversal
[递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#889-construct-binary-from-preorder-and-postorder-traversal)

### 652 Find Duplicate Subtrees
[序列化](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag1#652-find-duplicate-subtrees)
