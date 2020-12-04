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
[]()

### 362 Design Hit Counter
[]()

### 1479 Sales by Day of the Week
[]()

### 188 Best Time to Buy and Sell Stock IV
[]()

### 1429 First Unique Number
[]()

### 1268 Search Suggestions System
[]()

### 45 Jump Game II
[]()

### 84 Largest Rectangle in Histogram
[]()

### 815 Bus Routes
[]()

### 706 Design HashMap
[]()

### 518 Design File System
[]()

### 1166 Design File System
[]()

### 503 Next Greater Element II
[]()

### 1315 Sum of Nodes with Even-Valued Grandparent
[]()

### 1293 Shortest Path in a Grid with Obstacles Elimination
[]()

### 1011 Capacity To Ship Packages Within D Days
[]()

### 466 Count The Repetitions
[]()

### 642 Design Search Autocomplete System
[]()

### 502 IPO
[]()

### 316 Remove Duplicate Letters
[]()

### 564 Find the Closest Palindrome
[]()

### 895 Maximum Frequency Stack
[]()

### 1258 Synonymous Sentences
[]()

### 1416 Restore The Array
[]()

### 233 Number of Digit One
[]()

### 1628 K-Similar Strings
[]()

### 854 K-Similar Strings
[]()

### 1155 Number of Dice Rolls With Target Sum
[]()

### 95 Unique Binary Search Trees II
[]()

### 262 Trips and Users
[]()

### 797 All Paths From Source to Target
[]()

### 1102 Path With Maximum Minimum Value
[]()

### 1505 Minimum Possible Integer After at Most K Adjacent Swaps On Digits
[]()

### 99 Recover Binary Search Tree
[]()

### 356 Line Reflection
[]()

### 1139 Largest 1-Bordered Square
[]()

### 471 Encode String with Shortest Length
[]()

### 85 Maximal Rectangle
[]()

### 33 Search in Rotated Sorted Array
[]()

### 992 Subarrays with K Different Integers
[]()

### 909 Snakes and Ladders
[]()

### 229 Majority Element II
[]()

### 135 Candy
[]()

### 1135 Connecting Cities With Minimum Cost
[]()

### 12 Integer to Roman
[]()

### 410 Split Array Largest Sum
[]()

### 1100 Find K-Length Substrings With No Repeated Characters
[]()

### 37 Sudoku Solver
[]()

### 113 Path Sum II
[]()

### 1031 Maximum Sum of Two Non-Overlapping Subarrays
[]()

### 829 Consecutive Numbers Sum
[]()

### 1130 Minimum Cost Tree From Leaf Values
[]()

### 615 Average Salary: Departments VS Company
[]()

### 354 Russian Doll Envelopes
[]()

### 291 Word Pattern II
[]()

### 1375 Bulb Switcher III
[]()

### 1057 Campus Bikes
[]()

### 889 Construct Binary Tree from Preorder and Postorder Traversal
[]()

### 1084 Sales Analysis III
[]()

### 652 Find Duplicate Subtrees
[]()
