## Dec. 2020

### 953 Verifying an Alien Dictionary * 
直接顺序对比
```java
class Solution {
    public boolean isAlienSorted(String[] words, String order) {
        if (words == null || words.length == 0) {
            return true;
        }
        String prev = words[0];
        for (int i = 1; i < words.length; i++) {
            // compare prev and curr
            if(!check(prev, words[i], order)) {
                return false;
            }
            prev = words[i];
        }
        return true;
    }
    private boolean check(String prev, String curr, String order) {
        int idx1 = 0, idx2 = 0;
        while (idx1 < prev.length() && idx2 < curr.length()) {
            int p1 = order.indexOf(prev.charAt(idx1));
            int p2 = order.indexOf(curr.charAt(idx2));
            if (p1 < p2) {
                return true;
            } else if (p1 > p2) {
                return false;
            } else { // same char
                idx1++;
                idx2++;
            }
            
        }
        if (idx1 < prev.length() && idx2 >= curr.length()) { // if prev is longer than curr
            return false;
        }
        return true;
    }
}
```

### 1249 Minimum Remove to Make Valid Parentheses
额外Stack和Set记录哪些字符串可以删掉
```java
class Solution {
    public String minRemoveToMakeValid(String s) {
        if (s.isEmpty()) {
            return "";
        }
        Set<Integer> indicesToRemove = new HashSet<>();
        Stack<Integer> stack = new Stack<>();
        
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.push(i);
            }
            if (ch == ')') {
                if (stack.isEmpty()) {
                    indicesToRemove.add(i);
                } else {
                    stack.pop();
                }
            }
        }
        
        while (!stack.isEmpty()) { // some '(' left inside the stack
            indicesToRemove.add(stack.pop());
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indicesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
}
```

### 1428 Leftmost Column with at Least a One
对所有行进行遍历，对每一行最左边出现的1的位置进行记录（找1的过程利用二分搜索），最后返回所有行中最左边1的列数。
```java
/**
 * // This is the BinaryMatrix's API interface.
 * // You should not implement it, or speculate about its implementation
 * interface BinaryMatrix {
 *     public int get(int row, int col) {}
 *     public List<Integer> dimensions {}
 * };
 */

class Solution {
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        int rows = binaryMatrix.dimensions().get(0);
        int cols = binaryMatrix.dimensions().get(1);
        System.out.println(rows);
        
        int smallestIndex = Integer.MAX_VALUE;
        
        for (int i = 0; i < rows; i++) {
            int left = 0, right = cols - 1;
            while (left + 1 < right) {
                int mid = left + (right - left) / 2; // mid element of one row
                if (binaryMatrix.get(i, mid) == 0) { // one the right
                    left = mid;
                } else { // itself or on the left
                    right = mid;
                }
            }
            if (binaryMatrix.get(i, left) == 1) {
                smallestIndex = Math.min(smallestIndex, left);
            }  else if (binaryMatrix.get(i, right) == 1) {
                smallestIndex = Math.min(smallestIndex, right);
            } 
        }
        return smallestIndex == Integer.MAX_VALUE ? -1 : smallestIndex;
    }
}
```

### 238 Product of Array Except Self
[238 Product of Array Except Self](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#238-product-of-array-except-self)

### 973 K Closest Points to Origin
[973 K Closest Points to Origin](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer-1#973-k-closest-points-from-origin)

### 560 Subarray Sum Equals K
[560 Subarray Sum Equals K](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zi-shu-zu-subarray#560-subarray-sum-equals-k)

### 680 Valid Palindrome II
双指针
```java
class Solution {
    public boolean validPalindrome(String s) {
        if (s.isEmpty() || s.length() <= 2) {
            return true;
        }
        int len = s.length();
        int left = 0, right = len - 1;
        while (left < right && s.charAt(left) == s.charAt(right)) {
            left++;
            right--;
        }
        
        if (left == right) {
            return true;
        }
        
        // left++, right--
        if (checkPalindrome(s, left + 1, right) || checkPalindrome(s, left, right - 1)) {
            return true;
        }
        return false;
    }    
    private boolean checkPalindrome(String str, int left, int right) {
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
```

### 415 Add Strings
各一个指针
```java
class Solution {
    public String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();

        int carry = 0;
        int p1 = num1.length() - 1;
        int p2 = num2.length() - 1;
        while (p1 >= 0 || p2 >= 0) {
            int x1 = p1 >= 0 ? num1.charAt(p1) - '0' : 0;
            int x2 = p2 >= 0 ? num2.charAt(p2) - '0' : 0;
            int value = (x1 + x2 + carry) % 10;
            carry = (x1 + x2 + carry) / 10;
            res.append(value);
            p1--;
            p2--;    
        }
        
        if (carry != 0)
            res.append(carry);
        
        return res.reverse().toString();
    }
}
```

### 67 Add Binary
[67 Add Binary](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#67-add-binary)

### 273 Integer to English
[273 Integer to English](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#273-integer-to-english-words)

### 269 Alien Dictionary
最好拓扑排序的解法
[269 Alien Dictionary](https://app.gitbook.com/@guilindev/s/interview/leetcode/graph#269-alien-dictionary)

### 199 Binary Tree Right Side View
[199 Binary Tree Right Side View](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-3#199-binary-tree-right-side-view)

### 158 Read N Characters Given Read4 II - Call multiple times
```java
/**
 * The read4 API is defined in the parent class Reader4.
 *     int read4(char[] buf4); 
 */

public class Solution extends Reader4 {
    /**
     * @param buf Destination buffer
     * @param n   Number of characters to read
     * @return    The number of actual characters read
     */
    char[] bufCache = new char[4];
    int bufPtr = 0;
    int bufCount = 0;

    public int read(char[] buf, int n) {
        int nIndex = 0;

        // fill out every position until reach n
        while (nIndex < n) {
            // only if bufPtr does not reach the end of bufCache array, we can assign value from bufCache to final buf array
            if (bufPtr < bufCount) {
                buf[nIndex++] = bufCache[bufPtr++];
            }

            // if we already used all characters from bufCache, we need to read new characters by calling read4()
            // and then fill the bufCache
            else {
                bufCount = read4(bufCache);
                bufPtr = 0;

                // if no more characters we can read, we should break the entire loop and return 0
                if (bufCount == 0) {
                    break;
                }
            }
        }
        return nIndex;
    } 
}
```
解法2，利用queue
[158 Read N Characters Given Read4 II - Call multiple times](https://qingshijiao.github.io/2019/11/06/leetcode%E7%94%A8Read4%20API%E8%AF%BB%E5%8F%96N%E4%B8%AA%E5%AD%97%E7%AC%A6II/)

### 301 Remove Invalid Parenthesis
[301 Remove Invalid Parenthesis](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-3#301-remove-invalid-parentheses)

### 1570 Dot Product of Two Sparse Vectors
```java
class SparseVector {
    
    Map<Integer, Integer> map;      // For all non-zero values in the vector, we map the index to the non-zero value.
    
    // O(nums.length) time.
    // O(numNonZeroValues) space.
    SparseVector(int[] nums) {
        map = new HashMap<>();
        // Store the position and value of non-zero values.
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] != 0) {
                map.put(i, nums[i]);        
            }
        }
    }
    
	// Return the dotProduct of two sparse vectors
    // O(min(vec1numNonZeroValues, vec2numNonZeroValues)) time because we iterate through non-zero values of the vector that has fewer non-zero values and for each value we check in O(1) time whether the other vector has a non-zero value at that index.
    // O(1) space.
    public int dotProduct(SparseVector vec) {        
        if (vec.map.size() < this.map.size()) return vec.dotProduct(this);      // We want to iterate through the smaller map.

        int result = 0;
        for (Integer currIdx : this.map.keySet()) {
            // If both vectors have a non-zero value at currIdx then multiply the values and add them to the result.
            if (vec.map.containsKey(currIdx)) {
                result += this.map.get(currIdx) * vec.map.get(currIdx);
            }
        }
        return result;
    }
}

// Your SparseVector object will be instantiated and called as such:
// SparseVector v1 = new SparseVector(nums1);
// SparseVector v2 = new SparseVector(nums2);
// int ans = v1.dotProduct(v2);
```

### 211	Design Add and Search Words Data Structure
[Trie](https://app.gitbook.com/@guilindev/s/interview/leetcode/trie-qian-zhui-shu#211-add-and-search-word-data-structure-design)

### 215	Kth Largest Element in an Array
[pq/quick selct](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer-1#215-kth-largest-element-in-an-array)

### 721	Accounts Merge
[根据邮件和用户名合并账户 - DFS](https://leetcode-cn.com/problems/accounts-merge/solution/zhang-hu-he-bing-by-leetcode/)

### 125	Valid Palindrome	
[双指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-2#125-valid-palindrome)

### 124	Binary Tree Maximum Path Sum
[任意起点的路径最大值 - 回溯](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#124-binary-tree-maximum-path-sum)

### 278	First Bad Version	
[Binary Search](https://app.gitbook.com/@guilindev/s/interview/leetcode/er-fen-sou-suo#278-first-bad-version)

### 88	Merge Sorted Array	
[从后向前](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/he-bing-you-xu-de-arrays#88-merge-sorted-array)

### 29	Divide Two Integers
[不用乘除和取余的除法 - 位操作](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled#29-divide-two-integers)

### 426	Convert Binary Search Tree to Sorted Doubly Linked List
[BST转换为有序链表 - 中序遍历+备忘录](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#426-convert-binary-search-tree-to-sorted-doubly-linked-list)

### 297	Serialize and Deserialize Binary Tree	
[先序或层序](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#297-serialize-and-deserialize-binary-tree)

### 438	Find All Anagrams in a String
[字符串中找到所有异位词子串](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#297-serialize-and-deserialize-binary-tree)

### 636	Exclusive Time of Functions
[抢占CPU资源的函数独占时间 - 栈](https://leetcode-cn.com/problems/exclusive-time-of-functions/solution/han-shu-de-du-zhan-shi-jian-by-leetcode/)

### 523	Continuous Subarray Sum
[找一个子数组，大小是给定k的n倍 - hashmap](https://leetcode-cn.com/problems/continuous-subarray-sum/solution/lian-xu-de-zi-shu-zu-he-by-leetcode/)

### 282	Expression Add Operators
[回溯](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#282-expression-add-operators)

### 173	Binary Search Tree Iterator	
[BST返回和检查下一个最小数 - Stack](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#173-binary-search-tree-iterator)

### 34	Find First and Last Position of Element in Sorted Array
[左右边界](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zai-array-zhong-cha-xun-yuan-su#34-find-first-and-last-position-of-element-in-sorted-array-search-for-a-range)

### 528	Random Pick with Weight
[前缀和+二分](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag0#528-random-pick-with-weight)

### 987	Vertical Order Traversal of a Binary Tree
[垂序遍历 DFS记录坐标+排序](https://leetcode-cn.com/problems/vertical-order-traversal-of-a-binary-tree/solution/er-cha-shu-de-chui-xu-bian-li-by-leetcode-2/)

### 50	Pow(x, n)
[递归折半](https://app.gitbook.com/@guilindev/s/interview/leetcode/er-fen-sou-suo#50-power-x-n)

### 65	Valid Number	
[检查字符串是否是数](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#65-valid-number)

### 986	Interval List Intersections
[两两合并两个数组中的intervals - 双指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-2#986-interval-list-intersections)

### 133	Clone Graph
[BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/graph#133-clone-graph)

### 31	Next Permutation	
[记住特殊的解法](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#31-next-permutation)

### 708	Insert into a Sorted Circular Linked List $
[插入元素到循环有序链表中](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/)
[one pass or two passes](https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/discuss/149374/Java-5ms-One-Pass-and-Two-Pass-Traverse-With-Detailed-Comments-and-Edge-cases!)
```java
class Solution {
    public Node insert(Node start, int x) {
        // if start is null, create a node pointing to itself and return
        if (start == null) {
            Node node = new Node(x, null);
            node.next = node;
            return node;
        }
        // if start is not null, try to insert it into correct position
        // 1st pass to find max node
        Node cur = start;
        while (cur.val <= cur.next.val && cur.next != start) 
            cur = cur.next;
        // 2nd pass to insert the node in to correct position
        Node max = cur;
        Node dummy = new Node(0, max.next); // use a dummy head to make insertion process simpler
        max.next = null; // break the cycle
        cur = dummy;
        while (cur.next != null && cur.next.val < x) {
            cur = cur.next;
        }
        cur.next = new Node(x, cur.next); // insert
        Node newMax = max.next == null ? max : max.next; // reconnect to cycle
        newMax.next = dummy.next;
        return start;
    }
}
```

### 249	Group Shifted Strings $
[找出可以往后shift的字符串](https://leetcode.com/problems/group-shifted-strings/)
Basically we need to form some sort of key for each word to group them. (Remember the idea of group all anagrams?)

Consider acf and pru. Now notice the differnce between each characters.
acf = 0->2->3, and pru = 0->2->3. So these two form same group. So in this case, you can simply use integers ASCII difference to form key.

Now consider corner case, az and ba, where az = 0->25 and ba = 0->-1. When it goes negative, just make it positive(rotate or mod equivalent) by adding 26. So it becomes, ba = 0->25, which forms same group.
```java
class Solution {
    public List<List<String>> groupStrings(String[] strings) {
    Map<String, List<String>> map = new HashMap<>();

    for(String s : strings) {
        String key = getKey(s);
        List<String> list = map.getOrDefault(key, new ArrayList<>());
        list.add(s);
        map.put(key, list);
    }
    return new ArrayList<>(map.values());
}

private String getKey(String s) {
    char[] chars = s.toCharArray();
    String key = "";
    for(int i = 1; i < chars.length; i++) {
        int diff = chars[i] - chars[i-1];
        key += diff < 0 ? diff + 26 : diff;
        key += ",";
    }
    return key;
}
}
```

### 42	Trapping Rain Water	
[DP/stack](https://app.gitbook.com/@guilindev/s/interview/leetcode/stack#42-trapping-rain-water)

### 340	Longest Substring with At Most K Distinct Characters $
[最多包含k个不同字符的子字符串 ](https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/)
```java
class Solution {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
    Map<Character, Integer> map = new HashMap<>();
    int left = 0;
    int best = 0;
    for(int i = 0; i < s.length(); i++) {
        char c = s.charAt(i);
        map.put(c, map.getOrDefault(c, 0) + 1);
        while (map.size() > k) {
            char leftChar = s.charAt(left);
            if (map.containsKey(leftChar)) {
                map.put(leftChar, map.get(leftChar) - 1);                     
                if (map.get(leftChar) == 0) { 
                    map.remove(leftChar);
                }
            }
            left++;
        }
        best = Math.max(best, i - left + 1);
    }
    return best;
} 
}
```

### 543	Diameter of Binary Tree	
[DFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#543-diameter-of-binary-tree)

### 398	Random Pick Index
[有重复元素的随机数返回 - 蓄水池抽样问题](https://leetcode-cn.com/problems/random-pick-index/solution/xu-shui-chi-chou-yang-wen-ti-by-an-xin-9/)

### 139	Word Break
[DP + 剪枝/Trie](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#139-word-break)

### 339	Nested List Weight Sum	
[嵌套数组求和 DFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/dfs#339-nested-list-weight-sum)

### 785	Is Graph Bipartite?
[判断是否二分图 DFS/BFS/UF](https://leetcode-cn.com/problems/is-graph-bipartite/solution/bfs-dfs-bing-cha-ji-san-chong-fang-fa-pan-duan-er-/)

### 270	Closest Binary Search Tree Value $
[top down](https://leetcode.com/problems/closest-binary-search-tree-value/)
```java
class Solution {
    public int closestValue(TreeNode root, double target) {
		int ret = root.val;
		while (root != null) {
			if (Math.abs(target - root.val) < Math.abs(target - ret)) {
				ret = root.val;
				if (Math.abs(target - root.val) <= 0.5)
					break;
			}
			root = root.val > target ? root.left : root.right;
		}
		return ret;
	} 
}
```

### 378	Kth Smallest Element in a Sorted Matrix
[二分法](https://app.gitbook.com/@guilindev/s/interview/leetcode/er-fen-sou-suo#378-kth-smallest-element-in-a-sorted-matrix)

### 670	Maximum Swap
[非负整数中交换一次两位得到最大 - 存索引](https://leetcode-cn.com/problems/maximum-swap/solution/zui-da-jiao-huan-by-leetcode/)

### 76	Minimum Window Substring
[两个hashmap双指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-2#76-minimum-window-substring)

### 317	Shortest Distance from All Buildings
[周围楼间最短距离盖楼 BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag5#317-shortest-distance-from-all-buildings)

### 536	Construct Binary Tree from String $
[从一个数字和小括号的字符串生成二叉树 DFS或Stack](https://leetcode.com/problems/construct-binary-tree-from-string/)
```java
class Solution {
    public TreeNode str2tree(String s) {
        Stack<TreeNode> stack = new Stack<>();
        for(int i = 0, j = i; i < s.length(); i++, j = i){
            char c = s.charAt(i);
            if(c == ')')    stack.pop();
            else if(c >= '0' && c <= '9' || c == '-'){
                while(i + 1 < s.length() && s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9') i++;
                TreeNode currentNode = new TreeNode(Integer.valueOf(s.substring(j, i + 1)));
                if(!stack.isEmpty()){
                    TreeNode parent = stack.peek();
                    if(parent.left != null)    parent.right = currentNode;
                    else parent.left = currentNode;
                }
                stack.push(currentNode);
            }
        }
        return stack.isEmpty() ? null : stack.peek();
    }
}
```
OP's solution is on average O(nlgn); worse case O(n^2).
This could be solved in O(n). Here's my recursive one:
```java
class Solution {
    int p;
    
    public TreeNode str2tree(String s) {
        if (s == null || s.isEmpty()) return null;
        p = 0;
        s = "(" + s + ")";
        return build(s);
    }
    
    private TreeNode build(String s) {
        int start = p + 1;
        int end = start + 1;
        while (end < s.length() && Character.isDigit(s.charAt(end))) end++;
        int val = Integer.valueOf(s.substring(start, end));
        TreeNode root = new TreeNode(val);
        p = end;
        if (s.charAt(p) == '(') {
            root.left = build(s);
            if (s.charAt(p) == '(') {
                root.right = build(s);
            }
        }
        p++;
        return root;
    }
}
```

### 896	Monotonic Array
[直接两次或一次遍历](https://leetcode-cn.com/problems/monotonic-array/solution/dan-diao-shu-lie-by-leetcode/)

### 621	Task Scheduler
[两个任务间至少间隔n个点](https://app.gitbook.com/@guilindev/s/interview/leetcode/queue#621-task-scheduler)

### 1060 Missing Element in Sorted Array $
[找出有序数组中从1开始第Kth缺失的元素 - 二分](https://leetcode.com/problems/missing-element-in-sorted-array/solution/)
```java
class Solution {
  // Return how many numbers are missing until nums[idx]
  int missing(int idx, int[] nums) {
    return nums[idx] - nums[0] - idx;
  }

  public int missingElement(int[] nums, int k) {
    int n = nums.length;
    // If kth missing number is larger than 
    // the last element of the array
    if (k > missing(n - 1, nums))
      return nums[n - 1] + k - missing(n - 1, nums);

    int left = 0, right = n - 1, pivot;
    // find left = right index such that 
    // missing(left - 1) < k <= missing(left)
    while (left != right) {
      pivot = left + (right - left) / 2;

      if (missing(pivot, nums) < k) left = pivot + 1;
      else right = pivot;
    }

    // kth missing number is greater than nums[idx - 1]
    // and less than nums[idx]
    return nums[left - 1] + k - missing(left - 1, nums);
  }
}
```

### 825	Friends Of Appropriate Ages
[适龄朋友交友](https://leetcode-cn.com/problems/friends-of-appropriate-ages/solution/gua-ling-de-peng-you-by-leetcode/)

### 236	Lowest Common Ancestor of a Binary Tree
[top down](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#236-lowest-common-ancestor-of-binary-tree)

### 311	Sparse Matrix Multiplication $
[两个稀疏矩阵的乘法](https://leetcode.com/problems/sparse-matrix-multiplication/discuss/76154/Easiest-JAVA-solution)
```java
class Solution {
    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length, n = A[0].length, nB = B[0].length;
        int[][] C = new int[m][nB];

        for(int i = 0; i < m; i++) {
            for(int k = 0; k < n; k++) {
                if (A[i][k] != 0) {
                    for (int j = 0; j < nB; j++) {
                        if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
                    }
                }
            }
        }
        return C;   
    }
}
```

### 140	Word Break II
[列举所有可拆分的情况](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#140-word-break-ii)

### 56	Merge Intervals
[排序](https://app.gitbook.com/@guilindev/s/interview/leetcode/hui-wen-jie-gou#56-merge-intervals)

### 938	Range Sum of BST
[BST的范围和](https://leetcode-cn.com/problems/range-sum-of-bst/solution/er-cha-sou-suo-shu-de-fan-wei-he-by-leetcode/)

### 616	Add Bold Tag in String $
[Dict子串中加入<b>标识](https://leetcode.com/problems/add-bold-tag-in-string/discuss/104248/Java-Solution-boolean-array)
```java
class Solution {
    public String addBoldTag(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];
        for (int i = 0, end = 0; i < s.length(); i++) {
            for (String word : dict) {
                if (s.startsWith(word, i)) {
                    end = Math.max(end, i + word.length());
                }
            }
            bold[i] = end > i;
        }
        
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!bold[i]) {
                result.append(s.charAt(i));
                continue;
            }
            int j = i;
            while (j < s.length() && bold[j]) j++;
            result.append("<b>" + s.substring(i, j) + "</b>");
            i = j - 1;
        }
        
        return result.toString();
    }
}
```

### 827	Making A Large Island
[将1格海填充成陆地求得最大岛屿 记录连通块标号（不是UF）](https://leetcode-cn.com/problems/making-a-large-island/solution/zui-da-ren-gong-dao-by-leetcode/)

### 23	Merge k Sorted Lists
[Merge Sort](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#23-merge-k-sorted-lists)

### 138	Copy List with Random Pointer
[in place](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#138-copy-list-with-random-pointer)

### 314	Binary Tree Vertical Order Traversal $
[树形结构的从左向右Traversal - BFS](https://leetcode.com/problems/binary-tree-vertical-order-traversal/discuss/76401/5ms-Java-Clean-Solution)
* BFS, put node, col into queue at the same time
* Every left child access col - 1 while right child col + 1
* This maps node into different col buckets
* Get col boundary min and max on the fly
* Retrieve result from cols
```java
class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> res = new ArrayList<>();
    if (root == null) {
        return res;
    }
    
    Map<Integer, ArrayList<Integer>> map = new HashMap<>();
    Queue<TreeNode> q = new LinkedList<>();
    Queue<Integer> cols = new LinkedList<>();

    q.add(root); 
    cols.add(0);

    int min = 0;
    int max = 0;
    
    while (!q.isEmpty()) {
        TreeNode node = q.poll();
        int col = cols.poll();
        
        if (!map.containsKey(col)) {
            map.put(col, new ArrayList<Integer>());
        }
        map.get(col).add(node.val);

        if (node.left != null) {
            q.add(node.left); 
            cols.add(col - 1);
            min = Math.min(min, col - 1);
        }
        
        if (node.right != null) {
            q.add(node.right);
            cols.add(col + 1);
            max = Math.max(max, col + 1);
        }
    }

    for (int i = min; i <= max; i++) {
        res.add(map.get(i));
    }

    return res;
}
}
```

### 689	Maximum Sum of 3 Non-Overlapping Subarrays
[找出三个不重叠子数组各自最大和的起始index - DP](https://leetcode-cn.com/problems/maximum-sum-of-3-non-overlapping-subarrays/solution/san-ge-wu-zhong-die-zi-shu-zu-de-zui-da-he-by-leet/)
```java
class Solution {
        /**
         * cache[i] = sum(nums[i],...,nums[i+k])
         * int max_idx(int[] arr, int i, int j) -> 数组arr在[i,j]范围内的最大值的第一次出现的位置
         * left[i] = max_idx(cache, 0, i)
         * right[i] = max_idx(cache, i, cache.lenght - 1)
         *
         * @param nums
         * @param k
         * @return
         */
        public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
            int[] cache = new int[nums.length - k + 1];
            int[] left = new int[nums.length - k + 1];
            int[] right = new int[nums.length - k + 1];
            int sum = 0;
            for (int i = 0; i < k; i++) {
                sum += nums[i];
            }
            cache[0] = sum;
            for (int i = 1; i < cache.length; i++) {
                int v = cache[i] = sum = sum + nums[i + k - 1] - nums[i - 1];
                if (v > cache[left[i - 1]]) {
                    left[i] = i;
                } else {
                    left[i] = left[i - 1];
                }
            }
            right[right.length - 1] = right.length - 1;
            for (int i = cache.length - 2; i >= 0; i--) {
                if (cache[i] >= cache[right[i + 1]]) {
                    right[i] = i;
                } else {
                    right[i] = right[i + 1];
                }
            }
            int interval = k << 1;
            int[] res = new int[]{0, k, interval};
            int max = Integer.MIN_VALUE;
            int max_m = cache.length - k;
            for (int m = k; m < max_m; m++) {
                int v = cache[left[m - k]] + cache[m] + cache[right[m + k]];
                if (v > max) {
                    max = v;
                    res[0] = left[m - k];
                    res[1] = m;
                    res[2] = right[m + k];
                }
            }
            return res;
        }
    }
```

### 347	Top K Frequent Elements
[堆排序](https://app.gitbook.com/@guilindev/s/interview/leetcode/hash-table#347-top-k-frequent-elements)

### 146	LRU Cache
[背诵题](https://app.gitbook.com/@guilindev/s/interview/leetcode/gao-ji-de-shu-ju-jie-gou-he-suan-fa#146-lru-cache)

### 10	Regular Expression Matching
[星号*匹配前一个字符](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#10-regular-expression-matching)

### 766	Toeplitz Matrix
[判断托普利茨矩阵（有一条斜线元素相等）- 检查左上邻居](https://leetcode-cn.com/problems/toeplitz-matrix/solution/tuo-pu-li-ci-ju-zhen-by-leetcode/)

### 1197 Minimum Knight Moves
[BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-3#1197-minimum-knight-moves)

### 163	Missing Ranges $
[有序数组给出上下边界，找出所有缺失的区间](https://leetcode.com/problems/missing-ranges/)
* Find the range between lower and first element in the array.
* Find ranges between adjacent elements in the array.
* Find the range between upper and last element in the array.
```java
class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0){
            result.add(formRange(lower,upper));
            return result;
        }

        // 1st step
        if (nums[0] > lower){
            result.add(formRange(lower,nums[0]-1));
        }

        // 2nd step
        for (int i = 0; i < nums.length-1; i++){
            if (nums[i+1] != nums[i] && nums[i+1] > nums[i] +1) {
                result.add(formRange(nums[i]+1, nums[i+1]-1));
            }
        }

       // 3rd step
        if (nums[nums.length-1] < upper){
            result.add(formRange(nums[nums.length-1]+1, upper));
        }
        return result;
    }
    
    public String formRange(int low, int high){
        return low == high ? String.valueOf(low) : (low + "->" + high);
    }
}
}
```

### 71	Simplify Path
[把Unix路径规范和简化 - 顺序栈](https://leetcode-cn.com/problems/simplify-path/solution/71-jian-hua-lu-jing-shun-xu-zhan-lian-shi-zhan-by-/)

### 1498 Number of Subsequences That Satisfy the Given Sum Condition
[子数组相加小于目标值的总数（大数据集）](https://leetcode-cn.com/problems/number-of-subsequences-that-satisfy-the-given-sum-condition/solution/man-zu-tiao-jian-de-zi-xu-lie-shu-mu-by-leetcode-s/)

### 43	Multiply Strings
[字符串相乘保存很大的数 - 错位相加+额外数组](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#43-multiply-strings)

### 1004 Max Consecutive Ones III
[01数组中可以将k个0变成1，求最多连续的1 - 滑动窗口](https://leetcode-cn.com/problems/max-consecutive-ones-iii/solution/javahua-dong-chuang-kou-by-coldpepsi/)

### 200	Number of Islands
[三种方法](https://app.gitbook.com/@guilindev/s/interview/leetcode/dfs#200-number-of-islands)

### 304	Range Sum Query 2D - Immutable
[矩阵不可变，二维区域和搜索 - DP缓存](https://leetcode-cn.com/problems/range-sum-query-2d-immutable/solution/er-wei-qu-yu-he-jian-suo-ju-zhen-bu-ke-bian-by-lee/)

### 333	Largest BST Subtree $
[找出有最多结点的子BST](https://leetcode.com/problems/largest-bst-subtree/)
```java
class Solution {
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        if (isValid(root, null, null)) return countNode(root);
        return Math.max(largestBSTSubtree(root.left), largestBSTSubtree(root.right));
    }

    public boolean isValid(TreeNode root, Integer min, Integer max) {
        if (root == null) return true;
        if (min != null && min >= root.val) return false;
        if (max != null && max <= root.val) return false;
        return isValid(root.left, min, root.val) && isValid(root.right, root.val, max);
    }

    public int countNode(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return 1 + countNode(root.left) + countNode(root.right);
    }
}
```

### 247	Strobogrammatic Number II
[找出所有180度旋转后的中心对称数](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag5#247-strobogrammatic-number-ii)

### 1047 Remove All Adjacent Duplicates In String
[删除字符串中所有的相邻并且重复的字符 - 替换/栈](https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string/solution/shan-chu-zi-fu-chuan-zhong-de-suo-you-xiang-lin-zh/)

### 203	Remove Linked List Elements
[将等于目标值的元素从链表中删除](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#203-remove-linked-list-elements)

### 253	Meeting Rooms II
[共需要多少个会议室](https://app.gitbook.com/@guilindev/s/interview/leetcode/hui-wen-jie-gou#253-meeting-rooms-ii)

### 525	Contiguous Array
[二叉树组中找到同样数量的0和1的子数组 - HashMap](https://app.gitbook.com/@guilindev/s/interview/leetcode/hash-table#525-contiguous-array)

### 1424 Diagonal Traverse II
[二维整数列表的对角线遍历 Treemap](https://leetcode-cn.com/problems/diagonal-traverse-ii/solution/treemapan-dui-jiao-xian-ju-he-zhi-by-zuo-zhou-ren/)

### 266	Palindrome Permutation $
[字符串打散后是否是回文 - 计数/hashset](https://leetcode.com/problems/palindrome-permutation/)
The idea is to iterate over string, adding current character to set if set doesn't contain that character, or removing current character from set if set contains it.
When the iteration is finished, just return set.size()==0 || set.size()==1.

set.size()==0 corresponds to the situation when there are even number of any character in the string, and
set.size()==1 corresponsds to the fact that there are even number of any character except one.
```java
class Solution {
    public boolean canPermutePalindrome(String s) {
        Set<Character> set=new HashSet<Character>();
        for(int i=0; i<s.length(); ++i){
            if (!set.contains(s.charAt(i)))
                set.add(s.charAt(i));
            else 
                set.remove(s.charAt(i));
        }
        return set.size()==0 || set.size()==1;
    }
}
```

### 78	Subsets
[背诵题](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#78-subsets)

### 109	Convert Sorted List to Binary Search Tree
[排好序的链表转换成BST - 快慢指针找中点](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#109-convert-sorted-list-to-binary-search-tree)

### 1233 Remove Sub-Folders from the Filesystem
[删除给定数组中的所有子文件夹 - 排序去重](https://leetcode-cn.com/problems/remove-sub-folders-from-the-filesystem/solution/java-pai-xu-zhi-hou-mei-ge-jin-xing-pan-duan-bu-cu/)

### 977	Squares of a Sorted Array
[非递减数组的平方形成新数组，有负数 - 双指针](https://leetcode-cn.com/problems/squares-of-a-sorted-array/solution/you-xu-shu-zu-de-ping-fang-by-leetcode-solution/)

### 227	Basic Calculator II
[有加减乘除没有括号](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#227-basic-calculator-ii)

### 381	Insert Delete GetRandom O(1) - Duplicates allowed
[O(1) 时间插入、删除和获取随机元素 - 允许重复](https://leetcode-cn.com/problems/insert-delete-getrandom-o1-duplicates-allowed/solution/o1-shi-jian-cha-ru-shan-chu-he-huo-qu-sui-ji-yua-5/)

### 958	Check Completeness of a Binary Tree
[检查二叉树是否是完全二叉树 BFS](https://leetcode-cn.com/problems/check-completeness-of-a-binary-tree/solution/er-cha-shu-de-wan-quan-xing-jian-yan-by-leetcode/)

### 114	Flatten Binary Tree to Linked List
[先序](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#114-flatten-binary-tree-to-linked-list)

### 489	Robot Room Cleaner
[489+回溯](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag0#489-robot-room-cleaner)

### 246	Strobogrammatic Number $
[判断是否是中心对称数](https://leetcode.com/problems/strobogrammatic-number/)
```java
class Solution {
    public boolean isStrobogrammatic(String num) {
        Map<Character, Character> map = new HashMap<Character, Character>();
        map.put('6', '9');
        map.put('9', '6');
        map.put('0', '0');
        map.put('1', '1');
        map.put('8', '8');
    
        int l = 0, r = num.length() - 1;
        while (l <= r) {
            if (!map.containsKey(num.charAt(l))) return false;
            if (map.get(num.charAt(l)) != num.charAt(r))
                return false;
            l++;
            r--;
        }
        
        return true;
    }
}
```

### 8 String to Integer (atoi)
[各种边界条件](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#8-string-to-integer-atoi)

### 157	Read N Characters Given Read4 $
[Read4读取N个字符](https://leetcode.com/problems/read-n-characters-given-read4/)
```java
class Solution {
    public int read(char[] buf, int n) {
        boolean eof = false;      // end of file flag
        int total = 0;            // total bytes have read
        char[] tmp = new char[4]; // temp buffer
        
        while (!eof && total < n) {
            int count = read4(tmp);
            
            // check if it's the end of the file
            eof = count < 4;
            
            // get the actual count
            count = Math.min(count, n - total);
            
            // copy from temp buffer to buf
            for (int i = 0; i < count; i++) 
            buf[total++] = tmp[i];
        }
        
        return total;
    }
}
```

### 695	Max Area of Island
[DFS/BFS/UF](https://app.gitbook.com/@guilindev/s/interview/leetcode/dfs#695-max-area-of-islands)

### 934	Shortest Bridge
[将0变成1形成最短的桥的最小转换数 - BFS](https://leetcode-cn.com/problems/shortest-bridge/solution/zui-duan-de-qiao-by-leetcode/)

### 129	Sum Root to Leaf Numbers
[root到leaf形成的最大数](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#129-sum-root-to-leaf-numbers)

### 752	Open the Lock
[BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag1#752-open-the-lock)

### 1539 Kth Missing Positive Number
[严格升序数组中找出缺失的正整数 - 二分](https://leetcode-cn.com/problems/kth-missing-positive-number/solution/di-k-ge-que-shi-de-zheng-zheng-shu-by-leetcode-sol/)

### 647	Palindromic Substrings
[所有回文子串 - 直接计算或DP 复杂度一样](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#647-palindromic-substrings)

### 463	Island Perimeter
[注意是否有湖的情况](https://app.gitbook.com/@guilindev/s/interview/leetcode/hash-table#463-island-perimeter)

### 863	All Nodes Distance K in Binary Tree
[DFS添加父节点引用/BFS当作图处理](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#863-all-nodes-distance-k-in-binary-tree)

### 33 Search in Rotated Sorted Array
[二分](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zai-array-zhong-cha-xun-yuan-su#33-search-in-rotated-sorted-array)

### 921	Minimum Add to Make Parentheses Valid
[使括号字符串有效的最小添加数 - 计数平衡](https://leetcode-cn.com/problems/minimum-add-to-make-parentheses-valid/solution/shi-gua-hao-you-xiao-de-zui-shao-tian-jia-by-leetc/)

### 1242 Web Crawler Multithreaded $
[根据URL的pattern设计多线程网络爬虫](https://leetcode.com/problems/web-crawler-multithreaded/)
```java
class Solution {
    public List<String> crawl(String startUrl, HtmlParser htmlParser) {
        String hostname = getHostname(startUrl);
        
        Set<String> visited = ConcurrentHashMap.newKeySet();
        visited.add(startUrl);
        
        return crawl(startUrl, htmlParser, hostname, visited)
            .collect(Collectors.toList());
    }
    
    private Stream<String> crawl(String startUrl, HtmlParser htmlParser, String hostname, Set<String> visited) {
        Stream<String> stream = htmlParser.getUrls(startUrl)
            .parallelStream()
            .filter(url -> isSameHostname(url, hostname))
            .filter(url -> visited.add(url))
            .flatMap(url -> crawl(url, htmlParser, hostname, visited));
        
        return Stream.concat(Stream.of(startUrl), stream);
    }
    
    private String getHostname(String url) {
        int idx = url.indexOf('/', 7);
        return (idx != -1) ? url.substring(0, idx) : url;
    }
    
    private boolean isSameHostname(String url, String hostname) {
        if (!url.startsWith(hostname)) {
            return false;
        }
        return url.length() == hostname.length() || url.charAt(hostname.length()) == '/';
    }
}
```

### 920	Number of Music Playlists
[播放列表，大数据集 - DP](https://leetcode-cn.com/problems/number-of-music-playlists/solution/bo-fang-lie-biao-de-shu-liang-by-leetcode/)

### 380	Insert Delete GetRandom O(1)
[常数时间对数据结构插入删除和随机访问](https://leetcode-cn.com/problems/insert-delete-getrandom-o1/solution/chang-shu-shi-jian-cha-ru-shan-chu-he-huo-qu-sui-j/)

### 844	Backspace String Compare
[#代表退格的字符串判断相等](https://leetcode-cn.com/problems/backspace-string-compare/solution/bi-jiao-han-tui-ge-de-zi-fu-chuan-by-leetcode-solu/)

### 435	Non-overlapping Intervals
[找到需要移除的最小区间数目，让剩下的互不重叠 - DP](https://leetcode-cn.com/problems/non-overlapping-intervals/solution/wu-zhong-die-qu-jian-by-leetcode/)

### 529	Minesweeper
[扫雷游戏 - DFS/BFS](https://leetcode-cn.com/problems/minesweeper/solution/sao-lei-you-xi-by-leetcode-solution/)

### 329	Longest Increasing Path in a Matrix
[DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#329-longest-increasing-path-in-a-matrix)

### 875	Koko Eating Bananas
[吃香蕉的速度 - 二分](https://leetcode-cn.com/problems/koko-eating-bananas/solution/ai-chi-xiang-jiao-de-ke-ke-by-leetcode/)

### 548	Split Array with Equal Sum $
[将数组分为三个子数组，三者sum相同 - HashMap/HashSet](https://leetcode.com/problems/split-array-with-equal-sum/solution/)
```java
public class Solution {
    public boolean splitArray(int[] nums) {
        HashMap < Integer, ArrayList < Integer >> map = new HashMap < > ();
        int summ = 0, tot = 0;
        for (int i = 0; i < nums.length; i++) {
            summ += nums[i];
            if (map.containsKey(summ))
                map.get(summ).add(i);
            else {
                map.put(summ, new ArrayList < Integer > ());
                map.get(summ).add(i);
            }
            tot += nums[i];
        }
        summ = nums[0];
        for (int i = 1; i < nums.length - 5; i++) {
            if (map.containsKey(2 * summ + nums[i])) {
                for (int j: map.get(2 * summ + nums[i])) {
                    j++;
                    if (j > i + 1 && j < nums.length - 3 && map.containsKey(3 * summ + nums[i] + nums[j])) {
                        for (int k: map.get(3 * summ + nums[j] + nums[i])) {
                            k++;
                            if (k < nums.length - 1 && k > j + 1 && 4 * summ + nums[i] + nums[j] + nums[k] == tot)
                                return true;
                        }
                    }
                }
            }
            summ += nums[i];
        }
        return false;
    }
}
```
```java
public class Solution {
    public boolean splitArray(int[] nums) {
        if (nums.length < 7)
            return false;
        int[] sum = new int[nums.length];
        sum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        for (int j = 3; j < nums.length - 3; j++) {
            HashSet < Integer > set = new HashSet < > ();
            for (int i = 1; i < j - 1; i++) {
                if (sum[i - 1] == sum[j - 1] - sum[i])
                    set.add(sum[i - 1]);
            }
            for (int k = j + 2; k < nums.length - 1; k++) {
                if (sum[nums.length - 1] - sum[k] == sum[k - 1] - sum[j] && set.contains(sum[k - 1] - sum[j]))
                    return true;
            }
        }
        return false;
    }
}
```

### 1209 Remove All Adjacent Duplicates in String II
[删除只是k个相邻并相同的字符 - 栈](https://leetcode-cn.com/problems/remove-all-adjacent-duplicates-in-string-ii/solution/shan-chu-zi-fu-chuan-zhong-de-suo-you-xiang-lin--4/)

### 408	Valid Word Abbreviation $
[理解题意](https://leetcode.com/problems/valid-word-abbreviation/)
 Iterate over the characters of abbr and skip number of characters of word. Then compare ith chracter of word with jth character of abbr
```java
class Solution {
    public boolean validWordAbbreviation(String word, String abbr) {
        int number = 0;
        int i = 0, j = 0;
        while (i < word.length() && j < abbr.length()) {
            if (Character.isDigit(abbr.charAt(j))) {
                number = number * 10 + abbr.charAt(j) - '0';
                if (number == 0) return false;
                j++;
            } else {
                i += number;
                if (i >= word.length() || word.charAt(i) != abbr.charAt(j)) return false;
                number = 0;
                i++; j++;
            }
        }
        i += number;
        return i == word.length() && j == abbr.length();
    }
}
```

### 162	Find Peak Element
[二分](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zai-array-zhong-cha-xun-yuan-su#162-find-peak-element)

### 724	Find Pivot Index
[中心索引，左右的sum相同 - 前缀和](https://leetcode-cn.com/problems/find-pivot-index/solution/xun-zhao-shu-zu-de-zhong-xin-suo-yin-by-leetcode/)

### 1032 Stream of Characters
[设计字符流并查询 - Trie](https://leetcode-cn.com/problems/stream-of-characters/solution/java-trie-by-carl-x/)

### 658	Find K Closest Elements
[排序数组中找到距离给定数绝对值最近的的k个数 - 双指针/二分](https://leetcode-cn.com/problems/find-k-closest-elements/solution/pai-chu-fa-shuang-zhi-zhen-er-fen-fa-python-dai-ma/)

### 1344 Angle Between Hands of a Clock
[钟表上时针和分针的夹角 - 数学转换](https://leetcode-cn.com/problems/angle-between-hands-of-a-clock/solution/shi-zhong-zhi-zhen-de-jia-jiao-by-leetcode/)

### 1411 Number of Ways to Paint N × 3 Grid
[n x 3的网格涂色，相邻格子颜色不同 - 递推](https://leetcode-cn.com/problems/number-of-ways-to-paint-n-x-3-grid/solution/gei-n-x-3-wang-ge-tu-tu-se-de-fang-an-shu-by-leetc/)

### 772	Basic Calculator III $
[计算器的加减乘除加括号 - 两个stacks](https://leetcode.com/problems/basic-calculator-iii/)
```java
public static int calculate(String s) {
        if (s == null || s.length() == 0) return 0;
        Stack<Integer> nums = new Stack<>(); // the stack that stores numbers
        Stack<Character> ops = new Stack<>(); // the stack that stores operators (including parentheses)
        int num = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == ' ') continue;
            if (Character.isDigit(c)) {
                num = c - '0';
                // iteratively calculate each number
                while (i < s.length() - 1 && Character.isDigit(s.charAt(i+1))) {
                    num = num * 10 + (s.charAt(i+1) - '0');
                    i++;
                }
                nums.push(num);
                num = 0; // reset the number to 0 before next calculation
            } else if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // do the math when we encounter a ')' until '('
                while (ops.peek() != '(') nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
                ops.pop(); // get rid of '(' in the ops stack
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!ops.isEmpty() && precedence(c, ops.peek())) nums.push(operation(ops.pop(), nums.pop(),nums.pop()));
                ops.push(c);
            }
        }
        while (!ops.isEmpty()) {
            nums.push(operation(ops.pop(), nums.pop(), nums.pop()));
        }
        return nums.pop();
    }

    private static int operation(char op, int b, int a) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/': return a / b; // assume b is not 0
        }
        return 0;
    }
    // helper function to check precedence of current operator and the uppermost operator in the ops stack 
    private static boolean precedence(char op1, char op2) {
        if (op2 == '(' || op2 == ')') return false;
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false;
        return true;
    }
```

### 939	Minimum Area Rectangle
[一组点形成最小矩形，平行于X和Y轴 - 按列排序或者对角线](https://leetcode-cn.com/problems/minimum-area-rectangle/solution/zui-xiao-mian-ji-ju-xing-by-leetcode/)

### 1026 Maximum Difference Between Node and Ancestor
[top down](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#1026-maximum-difference-between-node-and-ancestor)

### 1094 Car Pooling
[顺风车拼车](https://leetcode-cn.com/problems/car-pooling/solution/gou-zao-fu-zai-shu-zu-jian-cha-fu-zai-ji-ke-by-the/)

### 974	Subarray Sums Divisible by K
[找到所有子数组可被K整除 - hashmap](https://leetcode-cn.com/problems/subarray-sums-divisible-by-k/solution/he-ke-bei-k-zheng-chu-de-zi-shu-zu-by-leetcode-sol/)

### 39 Combination Sum
[背诵题](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#39-combination-sum)

### 92 Reverse Linked List II
[从m到n位置进行翻转链表](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#92-reverse-linked-list-ii)

### 19 Remove Nth Node From End of List
[快慢指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#19-remove-nth-node-from-end-of-list)

### 622	Design Circular Queue
[622](https://app.gitbook.com/@guilindev/s/interview/leetcode/design#622-design-circular-queue)

### 767	Reorganize String
[重排字符让相邻字符不同](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag0#767-reorganize-string)

### 348	Design Tic-Tac-Toe
[不用绘制棋盘](https://app.gitbook.com/@guilindev/s/interview/leetcode/design#348-design-tic-tac-toe)

### 295	Find Median from Data Stream
[两个优先队列](https://app.gitbook.com/@guilindev/s/interview/leetcode/heap#295-find-median-from-data-stream)

### 787	Cheapest Flights Within K Stops
[最多经过K次中转的最便宜机票 - Dijkstra](https://leetcode-cn.com/problems/cheapest-flights-within-k-stops/solution/k-zhan-zhong-zhuan-nei-zui-bian-yi-de-hang-ban-b-2/)

### 824	Goat Latin
[山羊拉丁替换字符](https://leetcode-cn.com/problems/goat-latin/solution/shan-yang-la-ding-wen-by-leetcode/)


### 540 Single Element in a Sorted Array
找出只出现一次的元素，别的都是出现两次，二分查找
```java
class Solution {
    /**
    [1,1,2,3,3,4,4,8,8]
    */
    public int singleNonDuplicate(int[] nums) {
        int start = 0, end = nums.length - 1;
        while (start < end) {
            // We want the first element of the middle pair,
            // which should be at an even index if the left part is sorted.
            int mid = start + (end - start)/2;
            if (mid % 2 == 1) {
                mid--;
            }
            
            if (nums[mid] != nums[mid+1]) {//didn't find a pair. The single element must be on the left.
                end = mid;
            } else {// found a pair. The single element must be on the right.
                start = mid + 2;
            }
        }
        // 'start' should always be at the beginning of a pair.
        // When 'start > end', start must be the single element.
        return nums[start];
    }
}
```

### 161	One Edit Distance $
[判断两个字符串是否可以edit增删替换一次相等 - 直接判断一次](https://leetcode.com/problems/one-edit-distance/solution/)
```java
class Solution {
  public boolean isOneEditDistance(String s, String t) {
    int ns = s.length();
    int nt = t.length();

    // Ensure that s is shorter than t.
    if (ns > nt)
      return isOneEditDistance(t, s);

    // The strings are NOT one edit away distance  
    // if the length diff is more than 1.
    if (nt - ns > 1)
      return false;

    for (int i = 0; i < ns; i++)
      if (s.charAt(i) != t.charAt(i))
        // if strings have the same length
        if (ns == nt)
          return s.substring(i + 1).equals(t.substring(i + 1));
        // if strings have different lengths
        else
          return s.substring(i).equals(t.substring(i + 1));

    // If there is no diffs on ns distance
    // the strings are one edit away only if
    // t has one more character. 
    return (ns + 1 == nt);
  }
}
```

### 283	Move Zeroes
[双指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#283-move-zeroes)

### 332	Reconstruct Itinerary
[DFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag1#332-reconstruct-itinerary)

### 678	Valid Parenthesis String
[星号可表示任意左右括号，判断是否有小括号 - 两个栈](https://leetcode-cn.com/problems/valid-parenthesis-string/solution/fen-zhi-tan-xin-shuang-xiang-bian-li-shuang-zhan-j/)

### 1262 Greatest Sum Divisible by Three
[数组中sum可被3整除的元素和 - DP](https://leetcode-cn.com/problems/greatest-sum-divisible-by-three/solution/kuo-zhan-yi-xia-ba-ti-mu-de-chu-3bian-cheng-chu-kx/)

### 1216 Valid Palindrome III $
[可以移除K个字符的 - DP](https://leetcode.com/problems/valid-palindrome-iii/discuss/397606/Find-Longest-Palindromic-Subsequence.)
相当于找最长回文子字符串
```java
class Solution {
    public boolean isValidPalindrome(String str, int k) {
        int n = str.length();

        StringBuilder stringBuilder = new StringBuilder(str).reverse();
        int lps = lcs(str, stringBuilder.toString(), n, n);

        return (n - lps <= k);
    }

    /*
    longest palindromic subsequence:
    LCS of the given string & its reverse will be the longest palindromic sequence.
     */
    private int lcs(String X, String Y, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else if (X.charAt(i - 1) == Y.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}
```

### 983	Minimum Cost For Tickets
[连续旅行n天购票方法使票价最低 - 从后向前DP](https://leetcode-cn.com/problems/minimum-cost-for-tickets/solution/java-dong-tai-gui-hua-si-lu-bu-zou-cong-hou-xiang-/)

### 452	Minimum Number of Arrows to Burst Balloons
[排序](https://app.gitbook.com/@guilindev/s/interview/leetcode/greedy#452-minimum-number-of-arrows-to-burst-balloons)

### 791	Custom Sort String
[根据另一个字符串的顺序对一个字符串排序 - Hashmap或者array保持顺序](https://leetcode-cn.com/problems/custom-sort-string/solution/zi-ding-yi-zi-fu-chuan-pai-xu-by-leetcode/)

### 15 3Sum
[排序](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/ksum#15-3sum)

### 406	Queue Reconstruction by Height
[按照身高的属性排序](https://leetcode-cn.com/problems/queue-reconstruction-by-height/solution/gen-ju-shen-gao-zhong-jian-dui-lie-by-leetcode-sol/)

### 405	Convert a Number to Hexadecimal
[转换整数为十六进制](https://leetcode-cn.com/problems/convert-a-number-to-hexadecimal/solution/shu-zi-zhuan-huan-wei-shi-liu-jin-zhi-jie-fa-shou-/)

### 885	Spiral Matrix III
[逐渐内收螺旋打印](https://leetcode-cn.com/problems/spiral-matrix-iii/solution/luo-xuan-ju-zhen-iii-by-leetcode/)

### 277	Find the Celebrity
[两个数组](https://app.gitbook.com/@guilindev/s/interview/leetcode/graph#277-find-the-celebrity)

### 14 Longest Common Prefix
[居然可以二分查找](https://leetcode-cn.com/problems/longest-common-prefix/solution/zui-chang-gong-gong-qian-zhui-by-leetcode-solution/)

### 703	Kth Largest Element in a Stream
[最小堆](https://app.gitbook.com/@guilindev/s/interview/leetcode/heap#703-kth-largest-element-in-a-stream)

### 288	Unique Word Abbreviation $
[把中间的字符缩写，判断是否唯一 - HashMap](https://leetcode.com/problems/unique-word-abbreviation/solution/)
```java
class ValidWordAbbr {
    private final Map<String, Set<String>> abbrDict = new HashMap<>();

    public ValidWordAbbr(String[] dictionary) {
        for (String s : dictionary) {
            String abbr = toAbbr(s);
            Set<String> words = abbrDict.containsKey(abbr)
                ? abbrDict.get(abbr) : new HashSet<>();
            words.add(s);
            abbrDict.put(abbr, words);
        }
    }

    public boolean isUnique(String word) {
        String abbr = toAbbr(word);
        Set<String> words = abbrDict.get(abbr);
        return words == null || (words.size() == 1 && words.contains(word));
    }

    private String toAbbr(String s) {
        int n = s.length();
        if (n <= 2) {
            return s;
        }
        return s.charAt(0) + Integer.toString(n - 2) + s.charAt(n - 1);
    }
}
```

### 1269 Number of Ways to Stay in the Same Place After Some Steps
[数组的索引n次左右操作后是否停在原地 - DP](https://leetcode-cn.com/problems/number-of-ways-to-stay-in-the-same-place-after-some-steps/solution/ting-zai-yuan-di-de-fang-an-shu-by-leetcode-soluti/)

### 91 Decode Ways
[DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#91-decode-ways)

### 98 Validate Binary Search Tree
[传入上下边界](https://app.gitbook.com/@guilindev/s/interview/leetcode/dfs#98-validate-binary-search-tree)

### 1329 Sort the Matrix Diagonally
[矩阵按对角线升序排序 - 直接解](https://leetcode-cn.com/problems/sort-the-matrix-diagonally/solution/bao-li-jie-fa-by-liweiwei1419/)

### 152	Maximum Product Subarray
[维护最大最小两个数](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zi-shu-zu-subarray#152-maximum-product-subarray)

### 1361 Validate Binary Tree Nodes
[验证是二叉树还是图 - DFS/UF](https://leetcode-cn.com/problems/validate-binary-tree-nodes/solution/judgedai-ma-ji-caseyou-cuo-wu-ti-gong-lia-zheng-qu/)

### 567	Permutation in String
[判断一个字符串是否是另一个的排列 - 滑动窗口](https://leetcode-cn.com/problems/permutation-in-string/solution/zi-fu-chuan-de-pai-lie-by-leetcode/)

### 839	Similar String Groups
[交换字符串中两个字符的位置，相同则为相似字符串 - UF](https://leetcode-cn.com/problems/similar-string-groups/solution/wen-ti-zhuan-hua-wei-qiu-tu-de-lian-tong-fen-liang/)

### 698	Partition to K Equal Sum Subsets
[是否可以把数组划分为K个子数组让每个子数组和相等 - DP](https://leetcode-cn.com/problems/partition-to-k-equal-sum-subsets/solution/hua-fen-wei-kge-xiang-deng-de-zi-ji-by-leetcode/)

### 325	Maximum Size Subarray Sum Equals k $
[和等于k的最长子数组长度 - Hashmap](https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/)
```java
class Solution {
    public int maxSubArrayLen(int[] nums, int k) {
        int sum = 0, max = 0;
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            sum = sum + nums[i];
            if (sum == k) max = i + 1;
            else if (map.containsKey(sum - k)) max = Math.max(max, i - map.get(sum - k));
            if (!map.containsKey(sum)) map.put(sum, i);
        }
        return max;
    }
}
```

### 358	Rearrange String k Distance Apart $
[重排字符让相同的字符至少相隔k个距离](https://leetcode.com/problems/rearrange-string-k-distance-apart/)
The greedy algorithm is that in each step, select the char with highest remaining count if possible (if it is not in the waiting queue). PQ is used to achieve the greedy. A regular queue waitQueue is used to "freeze" previous appeared char in the period of k.

In each iteration, we need to add current char to the waitQueue and also release the char at front of the queue, put back to maxHeap. The "impossible" case happens when the maxHeap is empty but there is still some char in the waitQueue.
```java
class Solution {
    public String rearrangeString(String str, int k) {
        
        StringBuilder rearranged = new StringBuilder();
        //count frequency of each char
        Map<Character, Integer> map = new HashMap<>();
        for (char c : str.toCharArray()) {
            if (!map.containsKey(c)) {
                map.put(c, 0);
            }
            map.put(c, map.get(c) + 1);
        }
        
        //construct a max heap using self-defined comparator, which holds all Map entries, Java is quite verbose
        Queue<Map.Entry<Character, Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<Character, Integer>>() {
            public int compare(Map.Entry<Character, Integer> entry1, Map.Entry<Character, Integer> entry2) {
                return entry2.getValue() - entry1.getValue();
            }
        });
        
        Queue<Map.Entry<Character, Integer>> waitQueue = new LinkedList<>();
        maxHeap.addAll(map.entrySet());
        
        while (!maxHeap.isEmpty()) {
            
            Map.Entry<Character, Integer> current = maxHeap.poll();
            rearranged.append(current.getKey());
            current.setValue(current.getValue() - 1);
            waitQueue.offer(current);
            
            if (waitQueue.size() < k) { // intial k-1 chars, waitQueue not full yet
                continue;
            }
            // release from waitQueue if char is already k apart
            Map.Entry<Character, Integer> front = waitQueue.poll();
            //note that char with 0 count still needs to be placed in waitQueue as a place holder
            if (front.getValue() > 0) {
                maxHeap.offer(front);
            }
        }
        
        return rearranged.length() == str.length() ? rearranged.toString() : "";
    }
    
}
```

### 480	Sliding Window Median
[滑动窗口的中位数 - 295流中位数的改造](https://leetcode-cn.com/problems/sliding-window-median/solution/dui-295ti-shao-zuo-gai-jin-by-jerry_nju/)

### 494	Target Sum
[数组中非负整数组合加减符号使其等于目标数 - DP](https://leetcode-cn.com/problems/target-sum/solution/mu-biao-he-by-leetcode/)

### 515	Find Largest Value in Each Tree Row
[二叉树的每一行找最大值 - BFS/DFS](https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/solution/javadai-ma-bfshe-dfsliang-chong-jie-jue-si-lu-yi-j/)

### 341	Flatten Nested List Iterator
[对嵌套链表进行迭代 - Stack](https://app.gitbook.com/@guilindev/s/interview/leetcode/stack#341-flatten-nested-list-iterator)

### 919	Complete Binary Tree Inserter
[完全二叉树插入新结点](https://leetcode-cn.com/problems/complete-binary-tree-inserter/solution/wan-quan-er-cha-shu-ceng-xu-bian-li-bian-li-dao-zi/)

### 153	Find Minimum in Rotated Sorted Array
[二分](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zai-array-zhong-cha-xun-yuan-su#153-find-minimum-in-rotated-sorted-array)

### 637	Average of Levels in Binary Tree
[BFS/DFS](https://leetcode-cn.com/problems/average-of-levels-in-binary-tree/solution/er-cha-shu-de-ceng-ping-jun-zhi-by-leetcode-soluti/)

### 399	Evaluate Division
[组成方程并除法求职](https://leetcode-cn.com/problems/evaluate-division/solution/ni-zhen-de-zhi-dao-bing-cha-ji-de-gou-zao-guo-chen/)

### 1055 Shortest Way to Form String $
[从一个字符串做一些添加形成subsequence的最短路径](https://leetcode.com/problems/shortest-way-to-form-string/discuss/330938/Accept-is-not-enough-to-get-a-hire.-Interviewee-4-follow-up)

### 1439 Find the Kth Smallest Sum of a Matrix With Sorted Rows
[从矩阵中每行选择一个数字，组成一个array，求出第k大的array - BFS](https://leetcode-cn.com/problems/find-the-kth-smallest-sum-of-a-matrix-with-sorted-rows/solution/java-bfs-zui-xiao-dui-by-bran_wang/)

### 692	Top K Frequent Words
[小根堆](https://leetcode-cn.com/problems/top-k-frequent-words/solution/qian-kge-gao-pin-dan-ci-by-leetcode/)

### 498	Diagonal Traverse
[矩阵的对角线遍历](https://leetcode-cn.com/problems/diagonal-traverse/solution/dui-jiao-xian-bian-li-by-leetcode/)

### 346	Moving Average from Data Stream
[队列](https://app.gitbook.com/@guilindev/s/interview/leetcode/queue#346-moving-average-from-data-stream)

### 300	Longest Increasing Subsequence
[二分](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#300-longest-increasing-subsequence)

### 1 Two Sum
[注意题意别翻船](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/ksum#1-2sum)

### 79 Word Search
[DFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#79-word-search)

### 207	Course Schedule
[拓扑排序](https://app.gitbook.com/@guilindev/s/interview/leetcode/graph#207-course-schedule)

### 773	Sliding Puzzle
[2x3的板上滑动谜题，类似华容道 - BFS或优化的A*搜索](https://leetcode-cn.com/problems/sliding-puzzle/solution/hua-dong-mi-ti-by-leetcode/)

### 224	Basic Calculator
[计算器，只有加减和括号](https://app.gitbook.com/@guilindev/s/interview/leetcode/stack#224-basic-calculator)

### 128	Longest Consecutive Sequence
[DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#128-longest-consecutive-sequence)

### 102	Binary Tree Level Order Traversal
[BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#102-binary-tree-level-order-traversal)

### 969	Pancake Sorting
[对数组前k个元素进行翻转完成排序 - 查找最大值然后翻转](https://leetcode-cn.com/problems/pancake-sorting/solution/cha-zhao-zui-da-zhi-suo-zai-wei-zhi-fan-zhuan-zhi-/)

### 1305 All Elements in Two Binary Search Trees
[返回两颗二叉树的所有整数并升序排列](https://leetcode-cn.com/problems/all-elements-in-two-binary-search-trees/solution/java-san-chong-jie-fa-by-npe_tle/)

### 150	Evaluate Reverse Polish Notation
[逆波兰数 - 栈/递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/stack#150-evaluate-reverse-polish-notation)

### 345	Reverse Vowels of a String
[翻转一次元音字符](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#345-reverse-vowels-of-a-string)

### 686	Repeated String Match
[一个字符串重复n次后，另一个字符串是否是子串](https://leetcode.com/problems/repeated-string-match/discuss/108086/Java-Solution-Just-keep-building-(OJ-Missing-Test-Cases))

### 490	The Maze
[BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-3#490-the-maze)

### 449	Serialize and Deserialize BST
[反序列化时二分](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#449-serialize-and-deserialize-bst)

### 54 Spiral Matrix
[四个变量控制位置](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#54-spiral-matrix)

### 286	Walls and Gates $
[矩阵中将每个房间更新为距离最近门的距离 - BFS](https://leetcode.com/problems/walls-and-gates/discuss/72745/Java-BFS-Solution-O(mn)-Time)
```java
class Solution {
    public void wallsAndGates(int[][] rooms) {
        if (rooms.length == 0 || rooms[0].length == 0) return;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                if (rooms[i][j] == 0) queue.add(new int[]{i, j});
            }
        }
        while (!queue.isEmpty()) {
            int[] top = queue.remove();
            int row = top[0], col = top[1];
            if (row > 0 && rooms[row - 1][col] == Integer.MAX_VALUE) {
                rooms[row - 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row - 1, col});
            }
            if (row < rooms.length - 1 && rooms[row + 1][col] == Integer.MAX_VALUE) {
                rooms[row + 1][col] = rooms[row][col] + 1;
                queue.add(new int[]{row + 1, col});
            }
            if (col > 0 && rooms[row][col - 1] == Integer.MAX_VALUE) {
                rooms[row][col - 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col - 1});
            }
            if (col < rooms[0].length - 1 && rooms[row][col + 1] == Integer.MAX_VALUE) {
                rooms[row][col + 1] = rooms[row][col] + 1;
                queue.add(new int[]{row, col + 1});
            }
        }
    }
}
```

### 127	Word Ladder
[无向图BFS](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-3#127-word-ladder)

### 307	Range Sum Query - Mutable
[线段树](https://app.gitbook.com/@guilindev/s/interview/leetcode/design#307-range-sum-query-mutable)

### 387	First Unique Character in a String
[HashMap或者Array记录是否出现](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#387-first-unique-character-in-a-string)

### 143	Reorder List
[首尾重构链表 - Stack](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#143-reorder-list)

### 364	Nested List Weight Sum II $
[返回嵌套列表的总和并按照深度加权 - BFS](https://leetcode.com/problems/nested-list-weight-sum-ii/)
```java
class Solution {
    public int depthSumInverse(List<NestedInteger> nestedList) {
        if (nestedList == null) return 0;
        Queue<NestedInteger> queue = new LinkedList<NestedInteger>();
        int prev = 0;
        int total = 0;
        for (NestedInteger next: nestedList) {
            queue.offer(next);
        }
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            int levelSum = 0;
            for (int i = 0; i < size; i++) {
                NestedInteger current = queue.poll();
                if (current.isInteger()) levelSum += current.getInteger();
                List<NestedInteger> nextList = current.getList();
                if (nextList != null) {
                    for (NestedInteger next: nextList) {
                        queue.offer(next);
                    }
                }
            }
            prev += levelSum;
            total += prev;
        }
        return total;
    }
}
```

### 148	Sort List
[Merge Sort](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#148-sort-list)

### 73 Set Matrix Zeroes
[通过第一行第一列记录0的位置](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#73-set-matrix-zeroes)

### 420	Strong Password Checker
[强密码验证](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag#420-strong-password-checker)

### 605	Can Place Flowers
[贪心](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#605-can-place-flowers)

### 419	Battleships in a Board
[计算二维数组上可以放多少个X - 修改值](https://leetcode-cn.com/problems/battleships-in-a-board/solution/zhi-xiu-gai-ji-jin-jie-by-directivex/)

### 1162 As Far from Land as Possible
[找出NxN的网格中一个海洋离最近陆地最大 - BFS](https://leetcode-cn.com/problems/as-far-from-land-as-possible/solution/di-tu-fen-xi-by-leetcode-solution/)

### 3 Longest Substring Without Repeating Characters
[HashMap/hashset/array](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#3-longest-substring-without-repeating-characters)

### 739	Daily Temperatures
[单调栈Monotonic Stack](https://leetcode-cn.com/problems/daily-temperatures/solution/mei-ri-wen-du-by-leetcode-solution/)

### 20 Valid Parentheses
[注意有没有别的字符等](https://app.gitbook.com/@guilindev/s/interview/leetcode/stack#20-valid-parentheses)

### 285	Inorder Successor in BST
[中序遍历的下一个，利用BST特性](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#285-inorder-successor-in-bst)

### 336	Palindrome Pairs
[找出所有能组成回文词的两个字符串](https://app.gitbook.com/@guilindev/s/interview/leetcode/hash-table#336-palindrome-pairs)

### 154	Find Minimum in Rotated Sorted Array II
[有重复元素的数组找到最小值](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zai-array-zhong-cha-xun-yuan-su#154-find-minimum-in-rotated-sorted-array-ii)

### 22 Generate Parentheses
[递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#22-generate-parentheses)

### 2 Add Two Numbers
[链表从后往前](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#2-add-two-numbers)

### 392	Is Subsequence
[双指针/DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#392-is-subsequence)

### 257	Binary Tree Paths
[返回二叉树所有root到leaf的路径](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#257-binary-tree-paths)

### 865	Smallest Subtree with all the Deepest Nodes
[返回包含最深子结点的子树(不是叶子结点) - DFS](https://leetcode-cn.com/problems/smallest-subtree-with-all-the-deepest-nodes/solution/ju-you-suo-you-zui-shen-jie-dian-de-zui-xiao-zi-sh/)

### 234	Palindrome Linked List
[Stack/递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#234-palindrome-linked-list)

### 57 Insert Interval
[在有序区间的列表中插入一个新的区间 - 贪心](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag4#57-insert-interval)

### 1213 Intersection of Three Sorted Arrays $
[三个有序数组的交集 - 三指针](https://leetcode.com/problems/intersection-of-three-sorted-arrays/solution/)
```java
class Solution {
    public List<Integer> arraysIntersection(int[] arr1, int[] arr2, int[] arr3) {
        List<Integer> ans = new ArrayList <>();
        // prepare three pointers to iterate through three arrays
        // p1, p2, and p3 point to the beginning of arr1, arr2, and arr3 accordingly
        int p1 = 0, p2 = 0, p3 = 0;

        while (p1 < arr1.length && p2 < arr2.length && p3 < arr3.length) {

            if (arr1[p1] == arr2[p2] && arr2[p2] == arr3[p3]) {
                ans.add(arr1[p1]);
                p1++;
                p2++;
                p3++;
            } else {
                if (arr1[p1] < arr2[p2]) {
                    p1++;
                } else if (arr2[p2] < arr3[p3]) {
                    p2++;
                } else {
                    p3++;
                }

            }
        }
        return ans;
    }
}
```

### 1053 Previous Permutation With One Swap
[整数数组中交换一次元素得到按字典序比原数组小的最大数组](https://leetcode-cn.com/problems/previous-permutation-with-one-swap/solution/han-xiang-xi-fen-xi-si-lu-jian-dan-ti-mu-you-qu-by/)

### 17 Letter Combinations of a Phone Number
[回溯](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#17-letter-combinations-of-a-phone-number)

### 538	Convert BST to Greater Tree
[BST的值+比它大的值的和](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#538-convert-bst-to-greater-tree)

### 759	Employee Free Time $
[intervals所有员工都没工作的空闲时间 - 扫描线](https://leetcode.com/problems/employee-free-time/solution/)
```java
class Solution {
    public List<Interval> employeeFreeTime(List<List<Interval>> avails) {
        int OPEN = 0, CLOSE = 1;

        List<int[]> events = new ArrayList();
        for (List<Interval> employee: avails)
            for (Interval iv: employee) {
                events.add(new int[]{iv.start, OPEN});
                events.add(new int[]{iv.end, CLOSE});
            }

        Collections.sort(events, (a, b) -> a[0] != b[0] ? a[0]-b[0] : a[1]-b[1]);
        List<Interval> ans = new ArrayList();

        int prev = -1, bal = 0;
        for (int[] event: events) {
            // event[0] = time, event[1] = command
            if (bal == 0 && prev >= 0)
                ans.add(new Interval(prev, event[0]));
            bal += event[1] == OPEN ? 1 : -1;
            prev = event[0];
        }

        return ans;
    }
}
```

### 1382 Balance a Binary Search Tree
[将二叉树变平衡 - 贪心类似AVL旋转](https://leetcode-cn.com/problems/balance-a-binary-search-tree/solution/jiang-er-cha-sou-suo-shu-bian-ping-heng-by-leetcod/)

### 53 Maximum Subarray
[顺序扫描或DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zi-shu-zu-subarray#53-maximum-subarray)

### 121	Best Time to Buy and Sell Stock
[股票买卖 - 只可买一次](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#121-best-time-to-buy-and-sell-stock)

### 1314 Matrix Block Sum
[矩阵的区域的每个元素的和都满足条件 - 二维前缀和](https://leetcode-cn.com/problems/matrix-block-sum/solution/jing-dian-de-qian-zhui-he-wen-ti-by-lirunjun/)

### 206	Reverse Linked List
[别翻船](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#206-reverse-linked-list)

### 122	Best Time to Buy and Sell Stock II
[股票买卖 - 可无限买卖](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#122-best-time-to-buy-and-sell-stock-ii)

### 66 Plus One
[注意进位](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#66-plus-one)

### 1027 Longest Arithmetic Subsequence
[整数数组中的最长等差数列 - DP/hashmap二分优化](https://leetcode-cn.com/problems/longest-arithmetic-subsequence/solution/hashmap-bao-li-yong-shi-ji-bai-9642-nei-cun-ji-bai/)

### 443	String Compression
[原地压缩字符串数组中的每一个字符 - 双指针](https://leetcode-cn.com/problems/string-compression/solution/ya-suo-zi-fu-chuan-by-leetcode/)

### 545	Boundary of Binary Tree
[求二叉树的左边界右边界和叶子 - 先序遍历](https://app.gitbook.com/@guilindev/s/interview/leetcode/dfs#545-boundary-of-binary-tree)

### 416	Partition Equal Subset Sum
[正整数数组分成两个子集让二者和相等 - DP](https://leetcode-cn.com/problems/partition-equal-subset-sum/solution/fen-ge-deng-he-zi-ji-by-leetcode-solution/)

### 189	Rotate Array
[数组元素向右旋转k步 - 翻转](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#189-rotate-array)

### 1146 Snapshot Array
[实现快照数组](https://app.gitbook.com/@guilindev/s/interview/leetcode/tag#1146-snapshot-array)

### 694	Number of Distinct Islands $
[岛屿数量](https://leetcode.com/problems/number-of-distinct-islands/solution/)
DFS
```java
class Solution {
    int[][] grid;
    boolean[][] seen;
    Set<Integer> shape;
    
    public void explore(int r, int c, int r0, int c0) {
        if (0 <= r && r < grid.length && 0 <= c && c < grid[0].length &&
                grid[r][c] == 1 && !seen[r][c]) {
            seen[r][c] = true;
            shape.add((r - r0) * 2 * grid[0].length + (c - c0));
            explore(r+1, c, r0, c0);
            explore(r-1, c, r0, c0);
            explore(r, c+1, r0, c0);
            explore(r, c-1, r0, c0);
        }
    }
    public int numDistinctIslands(int[][] grid) {
        this.grid = grid;
        seen = new boolean[grid.length][grid[0].length];
        Set shapes = new HashSet<HashSet<Integer>>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[0].length; c++) {
                shape = new HashSet<Integer>();
                explore(r, c, r, c);
                if (!shape.isEmpty()) {
                    shapes.add(shape);
                }
            }
        }

        return shapes.size();
    }
}
```

### 36 Valid Sudoku
[三个hashset](https://app.gitbook.com/@guilindev/s/interview/leetcode/hash-table#36-valid-sudoku)

### 11 Container With Most Water
[双指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#11-container-with-most-water)

### 1287 Element Appearing More Than 25% In Sorted Array
[有序数组里面超过25%次数的元素 - 检查数字后面25%的元素](https://leetcode-cn.com/problems/element-appearing-more-than-25-in-sorted-array/solution/li-yong-you-xu-shu-zu-te-xing-qiu-jie-by-user8300r/)

### 90 Subsets II
[背诵题](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#90-subsets-ii)

### 219	Contains Duplicate II
[是否包含两个元素其索引和值的绝对值都小于k，有重复值 - hashmap/hashset](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#219-contains-duplicate-ii)

### 1460 Make Two Arrays Equal by Reversing Sub-arrays
[一个整数数组的子数组翻转任意次后能否成为另一个整数数组 - 判断排序后是否相同](https://leetcode-cn.com/problems/make-two-arrays-equal-by-reversing-sub-arrays/solution/gen-wan-mo-fang-chai-bu-duo-by-supperbug/)

### 230	Kth Smallest Element in a BST
[Stack/递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#230-kth-smallest-element-in-bst)

### 21 Merge Two Sorted Lists
[别翻船](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#21-merge-two-sorted-lists)

### 1254 Number of Closed Islands
[二维数组中周围全是水封闭岛屿的数量 - DFS](https://leetcode-cn.com/problems/number-of-closed-islands/solution/java-dfssou-suo-by-ppppjqute-2/)

### 242	Valid Anagram
[别翻船](https://app.gitbook.com/@guilindev/s/interview/leetcode/hash-table#242-valid-anagram)

### 46 Permutations
[背诵题](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#46-permutations)

### 117	Populating Next Right Pointers in Each Node II
[将不一定完美的二叉树填充一个索引为向右的指针 - 每一行看作链表](https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node-ii/solution/bfsjie-jue-zui-hao-de-ji-bai-liao-100de-yong-hu-by/)

### 32 Longest Valid Parentheses
[字符串中最长有效括号 - Stack/DP](https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/)

### 62 Unique Paths
[机器人走路 - DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#62-unique-paths)

### 350	Intersection of Two Arrays II
[两个整数数组的交集，有重复数字 - 双指针/hashmap](https://app.gitbook.com/@guilindev/s/interview/leetcode/hash-table#350-intersection-of-two-arrays-ii)

### 1143 Longest Common Subsequence
[两个字符串的最长公共子序列 - DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#1143-longest-common-subsequence)

### 268	Missing Number
[傻傻位操作](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled#268-missing-number)

### 1091 Shortest Path in Binary Matrix
[二进制矩阵左上到右下的最短距离，八个方向 - 标准BFS](https://leetcode-cn.com/problems/shortest-path-in-binary-matrix/solution/1091java-bfszhi-jie-da-bai-98-xiang-jie-by-ustcyyw/)

### 859	Buddy Strings
[能否交换字符串字符变成另一个字符串 Buddy String](https://leetcode-cn.com/problems/buddy-strings/solution/qin-mi-zi-fu-chuan-by-leetcode/)

### 209	Minimum Size Subarray Sum
[二分/滑动窗口/队列](https://app.gitbook.com/@guilindev/s/interview/leetcode/hua-dong-chuang-kou#209-minimize-size-subarray-sum)

### 5 Longest Palindromic Substring
[直接算/DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#5-longest-palindromic-substring)

### 168	Excel Sheet Column Title
[excel列数字转换成字母](https://leetcode-cn.com/problems/excel-sheet-column-title/solution/guan-yu-n-de-li-jie-by-douya0808/)

### 136	Single Number
[傻傻位操作](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled#136-single-number)

### 16 3Sum Closest
[维护一个min](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/ksum#16-3sum-closest)

### 1528 Shuffle String
[前后半截重排字符串](https://leetcode-cn.com/problems/shuffle-string/solution/zhong-xin-pai-lie-zi-fu-chuan-by-leetcode-solution/)

### 38 Count and Say
[直接模拟](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#38-count-and-say)

### 384	Shuffle an Array
[随机shuffle一个整数数组 - 洗牌算法](https://leetcode-cn.com/problems/shuffle-an-array/solution/da-luan-shu-zu-by-leetcode/)

### 572	Subtree of Another Tree
[检查一棵树是否存在子树与另一颗树相同 ](https://leetcode-cn.com/problems/subtree-of-another-tree/solution/ling-yi-ge-shu-de-zi-shu-by-leetcode-solution/)

### 334	Increasing Triplet Subsequence
[未排序的数组上寻找是否有递增长度为3的子数组 - DP/双指针](https://leetcode-cn.com/problems/increasing-triplet-subsequence/solution/334-di-zeng-de-san-yuan-zi-xu-lie-liang-chong-si-l/)

### 51 N-Queens
[回溯](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#51-n-queens)

### 516	Longest Palindromic Subsequence
[最长回文子序列 - DP](https://leetcode-cn.com/problems/longest-palindromic-subsequence/solution/dong-tai-gui-hua-si-yao-su-by-a380922457-3/)

### 349	Intersection of Two Arrays
[两个数字数组的交集 - HashSet/双指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/hash-table#349-intersection-of-two-arrays)

### 7 Reverse Integer
[注意越界](https://app.gitbook.com/@guilindev/s/interview/leetcode/math#7-reverse-integer)

### 24 Swap Nodes in Pairs
[dummy/递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#24-swap-nodes-in-pairs)

### 303	Range Sum Query - Immutable
[直接计算](https://app.gitbook.com/@guilindev/s/interview/leetcode/design#303-range-sum-query-immutable)

### 131	Palindrome Partitioning
[分割字符串让每个子串都是回文串 - 回溯](https://leetcode-cn.com/problems/palindrome-partitioning/solution/hui-su-you-hua-jia-liao-dong-tai-gui-hua-by-liweiw/)

### 134	Gas Station
[贪心](https://app.gitbook.com/@guilindev/s/interview/leetcode/greedy#134-gas-station)

### 44 Wildcard Matching
[正则表达式，问号表示任意单个，星号表示任意字符串 - DP](https://leetcode-cn.com/problems/wildcard-matching/solution/tong-pei-fu-pi-pei-by-leetcode-solution/)

### 442	Find All Duplicates in an Array
[找出出现两次的元素 - 取负数做索引标记](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#442-find-all-duplicates-in-an-array)

### 706	Design HashMap
[设计哈希表 - 数组+取模](https://leetcode-cn.com/problems/design-hashmap/solution/she-ji-ha-xi-biao-by-leetcode/)

### 160	Intersection of Two Linked Lists
[双指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#160-intersection-of-two-linked-lists)

### 665	Non-decreasing Array
[判断一个整数数组最多改变一个元素的情况下能否成为非递减数组 - 双指针](https://leetcode-cn.com/problems/non-decreasing-array/solution/javashuang-zhi-zhen-yi-ci-xun-huan-chang-shu-kong-/)

### 75 Sort Colors
[三路快排](https://app.gitbook.com/@guilindev/s/interview/leetcode/hui-wen-jie-gou#75-sort-colors)

### 239	Sliding Window Maximum
[滑动窗口的最大值 - Deque](https://app.gitbook.com/@guilindev/s/interview/leetcode/heap#239-sliding-window-maximum)

### 205	Isomorphic Strings
[同构词 - hashmap](https://leetcode-cn.com/problems/isomorphic-strings/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-42/)

### 858	Mirror Reflection
[光线反射检查哪个接收器最先接到 - 模拟/数学](https://leetcode-cn.com/problems/mirror-reflection/solution/jing-mian-fan-she-by-leetcode/)

### 116	Populating Next Right Pointers in Each Node
[完美二叉树创建右指针索引](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#116-populating-next-right-pointers-in-each-node)

### 414	Third Maximum Number
[整数数组中第3大的数 维护三个变量/queue/快选](https://leetcode-cn.com/problems/third-maximum-number/solution/9465-8526-by-bobby996/)

### 72 Edit Distance
[每次增删改一个字符 - DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#72-edit-distance)

### 25 Reverse Nodes in k-Group
[递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#25-reverse-nodes-in-k-group)

### 208	Implement Trie (Prefix Tree)
[Trie](https://app.gitbook.com/@guilindev/s/interview/leetcode/trie-qian-zhui-shu#208-implement-trie-prefix-tree)

### 28 Implement strStr()
[KMP了解下](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#28-implement-substr)

### 159	Longest Substring with At Most Two Distinct Characters
[滑动窗口+hashmap](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-2#159-longest-substring-with-at-most-two-distinct-characters)

### 617	Merge Two Binary Trees
[递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#617-merge-two-binary-tree)

### 328	Odd Even Linked List
[双指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#328-odd-even-linked-list)

### 191	Number of 1 Bits
[n & (n - 1)](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled#191-number-of-1-bits)

### 235	Lowest Common Ancestor of a Binary Search Tree
[递归/迭代](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#235-lowest-common-ancestor-of-a-binary-search-tree)

### 6 ZigZag Conversion
[ZigZag Conversion](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#6-zigzag-conversion)

### 151	Reverse Words in a String
[两次翻转](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#151-reverse-words-in-a-string)

### 141	Linked List Cycle
[快慢指针](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#141-linked-list-cycle)

### 221	Maximal Square
[二维矩阵只含1的最大正方形 - DP](https://leetcode-cn.com/problems/maximal-square/solution/zui-da-zheng-fang-xing-by-leetcode-solution/)

### 74 Search a 2D Matrix
[坐标换算当作一维](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zai-array-zhong-cha-xun-yuan-su#74-search-a-2d-matrix)

### 94 Binary Tree Inorder Traversal
[Stack解法](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#94-binary-tree-inorder-traversal)

### 240	Search a 2D Matrix II
[右上角或左下角](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/zai-array-zhong-cha-xun-yuan-su#240-search-a-2d-matrix-ii)

### 26 Remove Duplicates from Sorted Array
[交换/保留一个值](https://app.gitbook.com/@guilindev/s/interview/leetcode/array/26-27-80-zai-array-zhong-yi-chu-yuan-su#26-remove-duplicates-from-sorted-array)

### 145	Binary Tree Postorder Traversal
[Stack解法](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#145-binary-tree-postorder-traversal)

### 13 Roman to Integer
[考虑每个字符的前后](https://app.gitbook.com/@guilindev/s/interview/leetcode/string#13-roman-to-integer)

### 41 First Missing Positive
[缺失的第一个正数 - 桶排序思想](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#41-first-missing-positive)

### 64 Minimum Path Sum
[矩阵中的最小path和 - DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#64-minimum-path-sum)

### 445	Add Two Numbers II
[链表从前往后加 - 两个stack](https://app.gitbook.com/@guilindev/s/interview/leetcode/linkedlist#445-add-two-numbers-ii)

### 105	Construct Binary Tree from Preorder and Inorder Traversal
[套路](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#105-construct-binary-tree-from-preorder-and-inorder-traversal)

### 218	The Skyline Problem
[扫描线](https://app.gitbook.com/@guilindev/s/interview/leetcode/sao-miao-xian#218-the-skyline-problem)

### 112	Path Sum
[判断是否有路径](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#112-path-sum)

### 55 Jump Game
[DP/贪心](https://app.gitbook.com/@guilindev/s/interview/leetcode/greedy#55-jump-game)

### 48	Rotate Image
[旋转90度 - 转置矩阵](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#48-rotate-image)

### 155	Min Stack
[两个stack/一个stack](https://app.gitbook.com/@guilindev/s/interview/leetcode/stack#155-min-stack)

### 395	Longest Substring with At Least K Repeating Characters
[至少有k个重复字符的最长字串（每个字符都不少于k次）- 递归+hashmap](https://leetcode-cn.com/problems/longest-substring-with-at-least-k-repeating-characters/solution/yi-dong-de-di-gui-ha-xi-by-lxsyz/)

### 322	Coin Change
[硬币可以任意取， DP](https://app.gitbook.com/@guilindev/s/interview/leetcode/divide-and-conquer#322-coin-change)

### 104	Maximum Depth of Binary Tree
[多解法](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#104-maximum-depth-of-binary-tree)

### 4 Median of Two Sorted Arrays
[二分搜索](https://app.gitbook.com/@guilindev/s/interview/leetcode/array#4-median-of-two-sorted-arrays)

### 101	Symmetric Tree
[递归/非递归](https://app.gitbook.com/@guilindev/s/interview/leetcode/untitled-1#101-symmetric-tree)

### 310	Minimum Height Trees
[图中找到最小高度树（不一定二叉树）- BFS拓扑排序](https://leetcode-cn.com/problems/minimum-height-trees/solution/zui-rong-yi-li-jie-de-bfsfen-xi-jian-dan-zhu-shi-x/)

### 47 Permutations II
[背诵题](https://app.gitbook.com/@guilindev/s/interview/leetcode/backtracking#47-permutations-ii)



### Extra: Merge Two Sorted Interval Lists
样例1
输入: [(1,2),(3,4)] and list2 = [(2,3),(5,6)]
输出: [(1,4),(5,6)]
解释:
(1,2),(2,3),(3,4) --> (1,4)
(5,6) --> (5,6)
样例2
输入: [(1,2),(3,4)] 和 list2 = [(4,5),(6,7)]
输出: [(1,2),(3,5),(6,7)]
解释:
(1,2) --> (1,2)
(3,4),(4,5) --> (3,5)
(6,7) --> (6,7)
```java
class Interval {
    int start;
    int end;
    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}

class myComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval i1, Interval i2) {
        if (i1.start == i2.start) {
            return 0;
        } else {
            return i1.start < i2.start? -1: 1;
        }
    }
}

class IntervalMerge {
    public List<Interval> mergeList(List<Interval> l1, List<Interval> l2) {
        if (l1 == null || l1.size()  == 0) {
            return l2;
        } else if (l2 == null || l2.size() == 0) {
            return l1;
        }

        Collections.sort(l1, new myComparator());
        Collections.sort(l2, new myComparator());

        List<Interval> result = new ArrayList<>();
        int ix1 = 0;
        int ix2 = 0;
        // Get the first interval
        Interval prev = null;
        if (l1.get(0).start < l2.get(0).start) {
            prev = l1.get(0);
            ix1 ++;
        } else {
            prev = l2.get(0);
            ix2 ++;
        }
        // Move two pointers to merge lists
        while (ix1 < l1.size() || ix2 < l2.size()) {
            if (ix2 == l2.size() || (ix1 < l1.size() && l1.get(ix1).start < l2.get(ix2).start)) {
                // merge prev with ix1
                if (prev.end < l1.get(ix1).start) {
                    result.add(prev);
                    prev = l1.get(ix1);
                } else {
                    prev.end = Math.max(prev.end, l1.get(ix1).end);
                }
                ix1 ++;
            } else {
                // merge prev with ix2
                if (prev.end < l2.get(ix2).start) {
                    result.add(prev);
                    prev = l2.get(ix2);
                } else {
                    prev.end = Math.max(prev.end, l2.get(ix2).end);
                }
                ix2 ++;
            }
        }
        result.add(prev);
        return result;
    }
}
```