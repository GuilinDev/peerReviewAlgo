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

### 1167 Minimum Cost to Connect Sticks
[]()

### 1120 Maximum Average Subtree
[]()

### 1010 Pairs of Songs With Total Durations Divisible by 60
[]()

### 819 Most Common Word

### 957 Prison Cells After N Days

### 123 Best Time to Buy and Sell Stock III

### 437 Path Sum III

### 472 Concatenated Words

### 1597 Build Binary Expression Tree From Infix Expression

### 1520 Maximum Number of Non-Overlapping Substrings

### 126 Word Ladder II

### 733 Flood Fill

### 121 Best Time to Buy and Sell Stock

### 735 Asteroid Collision

### 323 Number of Connected Components in an Undirected Graph

### 496 Next Greater Element I

### 353 Design Snake Game

### 681 Next Closest Time

### 403 Frog Jump

### 1328 Break a Palindrome

### 362 Design Hit Counter

### 1479 Sales by Day of the Week

### 188 Best Time to Buy and Sell Stock IV

### 1429 First Unique Number

### 1268 Search Suggestions System

### 45 Jump Game II

### 84 Largest Rectangle in Histogram

### 815 Bus Routes

### 706 Design HashMap

### 518 Design File System

### 1166 Design File System

### 503 Next Greater Element II

### 1315 Sum of Nodes with Even-Valued Grandparent

### 1293 Shortest Path in a Grid with Obstacles Elimination

### 1011 Capacity To Ship Packages Within D Days

### 466 Count The Repetitions

### 642 Design Search Autocomplete System

### 502 IPO

### 316 Remove Duplicate Letters

### 564 Find the Closest Palindrome

### 895 Maximum Frequency Stack

### 1258 Synonymous Sentences

### 1416 Restore The Array

### 233 Number of Digit One

### 1628 K-Similar Strings

### 854 K-Similar Strings

### 1155 Number of Dice Rolls With Target Sum

### 95 Unique Binary Search Trees II

### 262 Trips and Users

### 797 All Paths From Source to Target

### 1102 Path With Maximum Minimum Value

### 1505 Minimum Possible Integer After at Most K Adjacent Swaps On Digits

### 99 Recover Binary Search Tree

### 356 Line Reflection

### 1139 Largest 1-Bordered Square

### 471 Encode String with Shortest Length

### 85 Maximal Rectangle

### 33 Search in Rotated Sorted Array

### 992 Subarrays with K Different Integers

### 909 Snakes and Ladders

### 229 Majority Element II

### 135 Candy

### 1135 Connecting Cities With Minimum Cost

### 12 Integer to Roman

### 410 Split Array Largest Sum

### 1100 Find K-Length Substrings With No Repeated Characters

### 37 Sudoku Solver

### 113 Path Sum II

### 1031 Maximum Sum of Two Non-Overlapping Subarrays

### 829 Consecutive Numbers Sum

### 1130 Minimum Cost Tree From Leaf Values

### 615 Average Salary: Departments VS Company

### 354 Russian Doll Envelopes

### 291 Word Pattern II

### 1375 Bulb Switcher III

### 1057 Campus Bikes

### 889 Construct Binary Tree from Preorder and Postorder Traversal

### 1084 Sales Analysis III

### 652 Find Duplicate Subtrees
