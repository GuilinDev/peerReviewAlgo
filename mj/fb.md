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

### 273 Integer to English

### 269 Alien Dictionary
最好拓扑排序的解法

### 199 Binary Tree Right Side View

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

### 301 Remove Invalid Parenthesis

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
### 215	Kth Largest Element in an Array
### 721	Accounts Merge
### 125	Valid Palindrome	
### 124	Binary Tree Maximum Path Sum
### 278	First Bad Version	
### 88	Merge Sorted Array	
### 29	Divide Two Integers
### 426	Convert Binary Search Tree to Sorted Doubly Linked List
### 297	Serialize and Deserialize Binary Tree	
### 438	Find All Anagrams in a String
### 636	Exclusive Time of Functions
### 523	Continuous Subarray Sum
### 282	Expression Add Operators	
### 173	Binary Search Tree Iterator	
### 34	Find First and Last Position of Element in Sorted Array
### 528	Random Pick with Weight
### 987	Vertical Order Traversal of a Binary Tree
### 50	Pow(x, n)
### 65	Valid Number	
### 986	Interval List Intersections
### 133	Clone Graph
### 31	Next Permutation	
### 708	Insert into a Sorted Circular Linked List
### 249	Group Shifted Strings
### 42	Trapping Rain Water	
### 340	Longest Substring with At Most K Distinct Characters	
### 543	Diameter of Binary Tree	
### 398	Random Pick Index
### 139	Word Break
### 339	Nested List Weight Sum	
### 785	Is Graph Bipartite?
### 270	Closest Binary Search Tree Value
### 378	Kth Smallest Element in a Sorted Matrix
### 670	Maximum Swap
### 76	Minimum Window Substring
### 317	Shortest Distance from All Buildings
### 536	Construct Binary Tree from String
### 896	Monotonic Array
### 621	Task Scheduler
### 1060 Missing Element in Sorted Array
### 825	Friends Of Appropriate Ages
### 236	Lowest Common Ancestor of a Binary Tree
### 311	Sparse Matrix Multiplication
### 140	Word Break II
### 56	Merge Intervals
### 938	Range Sum of BST
### 616	Add Bold Tag in String
### 827	Making A Large Island
### 23	Merge k Sorted Lists
### 138	Copy List with Random Pointer
### 314	Binary Tree Vertical Order Traversal
### 689	Maximum Sum of 3 Non-Overlapping Subarrays
### 347	Top K Frequent Elements
### 146	LRU Cache
### 10	Regular Expression Matching
### 766	Toeplitz Matrix
### 1197 Minimum Knight Moves
### 163	Missing Ranges
### 71	Simplify Path
### 1498 Number of Subsequences That Satisfy the Given Sum Condition
### 43	Multiply Strings
### 1004 Max Consecutive Ones III
### 200	Number of Islands
### 304	Range Sum Query 2D - Immutable
### 333	Largest BST Subtree
### 247	Strobogrammatic Number II
### 1047 Remove All Adjacent Duplicates In String
### 203	Remove Linked List Elements
### 253	Meeting Rooms II
### 525	Contiguous Array
### 1424 Diagonal Traverse II
### 266	Palindrome Permutation
### 78	Subsets
### 109	Convert Sorted List to Binary Search Tree
### 1233 Remove Sub-Folders from the Filesystem
### 977	Squares of a Sorted Array
### 227	Basic Calculator II
### 381	Insert Delete GetRandom O(1) - Duplicates allowed
### 958	Check Completeness of a Binary Tree
### 114	Flatten Binary Tree to Linked List
### 489	Robot Room Cleaner
### 246	Strobogrammatic Number
### 8 String to Integer (atoi)
### 157	Read N Characters Given Read4
### 695	Max Area of Island
### 934	Shortest Bridge
### 129	Sum Root to Leaf Numbers
### 752	Open the Lock
### 1539 Kth Missing Positive Number
### 647	Palindromic Substrings
### 463	Island Perimeter
### 863	All Nodes Distance K in Binary Tree
### 33 Search in Rotated Sorted Array
### 921	Minimum Add to Make Parentheses Valid
### 1242 Web Crawler Multithreaded
### 920	Number of Music Playlists
### 380	Insert Delete GetRandom O(1)
### 844	Backspace String Compare
### 435	Non-overlapping Intervals
### 529	Minesweeper
### 329	Longest Increasing Path in a Matrix
### 875	Koko Eating Bananas
### 548	Split Array with Equal Sum
### 1209 Remove All Adjacent Duplicates in String II
### 408	Valid Word Abbreviation
### 162	Find Peak Element
### 724	Find Pivot Index
### 1032 Stream of Characters
### 658	Find K Closest Elements
### 1344 Angle Between Hands of a Clock
### 1411 Number of Ways to Paint N × 3 Grid
### 772	Basic Calculator III
### 939	Minimum Area Rectangle
### 1026 Maximum Difference Between Node and Ancestor
### 1094 Car Pooling
### 974	Subarray Sums Divisible by K
### 39 Combination Sum
### 92 Reverse Linked List II
### 19 Remove Nth Node From End of List
### 622	Design Circular Queue
### 767	Reorganize String
### 348	Design Tic-Tac-Toe
### 295	Find Median from Data Stream
### 787	Cheapest Flights Within K Stops
### 824	Goat Latin

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

### 161	One Edit Distance
### 283	Move Zeroes
### 332	Reconstruct Itinerary
### 678	Valid Parenthesis String
### 1262 Greatest Sum Divisible by Three
### 1216 Valid Palindrome III
### 983	Minimum Cost For Tickets
### 452	Minimum Number of Arrows to Burst Balloons
### 791	Custom Sort String
### 15 3Sum
### 406	Queue Reconstruction by Height
### 405	Convert a Number to Hexadecimal
### 885	Spiral Matrix III
### 277	Find the Celebrity
### 14 Longest Common Prefix
### 703	Kth Largest Element in a Stream
### 288	Unique Word Abbreviation
### 1269 Number of Ways to Stay in the Same Place After Some Steps
### 91 Decode Ways
### 98 Validate Binary Search Tree
### 1329 Sort the Matrix Diagonally
### 152	Maximum Product Subarray
### 1361 Validate Binary Tree Nodes
### 567	Permutation in String
### 839	Similar String Groups
### 698	Partition to K Equal Sum Subsets
### 325	Maximum Size Subarray Sum Equals k
### 358	Rearrange String k Distance Apart
### 480	Sliding Window Median
### 494	Target Sum
### 515	Find Largest Value in Each Tree Row
### 341	Flatten Nested List Iterator
### 919	Complete Binary Tree Inserter
### 153	Find Minimum in Rotated Sorted Array
### 637	Average of Levels in Binary Tree
### 399	Evaluate Division
### 1055 Shortest Way to Form String
### 1439 Find the Kth Smallest Sum of a Matrix With Sorted Rows
### 692	Top K Frequent Words
### 498	Diagonal Traverse
### 346	Moving Average from Data Stream
### 300	Longest Increasing Subsequence
### 1 Two Sum
### 79 Word Search
### 207	Course Schedule
### 773	Sliding Puzzle
### 224	Basic Calculator
### 128	Longest Consecutive Sequence
### 102	Binary Tree Level Order Traversal
### 969	Pancake Sorting
### 1305 All Elements in Two Binary Search Trees
### 150	Evaluate Reverse Polish Notation
### 345	Reverse Vowels of a String
### 686	Repeated String Match
### 490	The Maze
### 449	Serialize and Deserialize BST
### 54 Spiral Matrix
### 286	Walls and Gates
### 127	Word Ladder
### 307	Range Sum Query - Mutable
### 387	First Unique Character in a String
### 143	Reorder List
### 597	Friend Requests I: Overall Acceptance Rate
### 364	Nested List Weight Sum II
### 148	Sort List
### 73 Set Matrix Zeroes
### 420	Strong Password Checker
### 605	Can Place Flowers
### 419	Battleships in a Board
### 1162 As Far from Land as Possible
### 3 Longest Substring Without Repeating Characters
### 739	Daily Temperatures
### 20 Valid Parentheses
### 285	Inorder Successor in BST
### 336	Palindrome Pairs
### 154	Find Minimum in Rotated Sorted Array II
### 22 Generate Parentheses
### 2 Add Two Numbers
### 392	Is Subsequence
### 257	Binary Tree Paths
### 865	Smallest Subtree with all the Deepest Nodes
### 234	Palindrome Linked List
### 57 Insert Interval
### 1213 Intersection of Three Sorted Arrays
### 1053 Previous Permutation With One Swap
### 17 Letter Combinations of a Phone Number
### 538	Convert BST to Greater Tree
### 759	Employee Free Time
### 1382 Balance a Binary Search Tree
### 53 Maximum Subarray
### 121	Best Time to Buy and Sell Stock
### 1314 Matrix Block Sum
### 206	Reverse Linked List
### 122	Best Time to Buy and Sell Stock II
### 66 Plus One
### 1027 Longest Arithmetic Subsequence
### 443	String Compression
### 545	Boundary of Binary Tree
### 416	Partition Equal Subset Sum
### 189	Rotate Array
### 1146 Snapshot Array
### 694	Number of Distinct Islands
### 36 Valid Sudoku
### 11 Container With Most Water
### 1287 Element Appearing More Than 25% In Sorted Array
### 90 Subsets II
### 219	Contains Duplicate II
### 1460 Make Two Arrays Equal by Reversing Sub-arrays
### 230	Kth Smallest Element in a BST
### 21 Merge Two Sorted Lists
### 1038 Binary Search Tree to Greater Sum Tree
### 1254 Number of Closed Islands
### 242	Valid Anagram
### 46 Permutations
### 117	Populating Next Right Pointers in Each Node II
### 32 Longest Valid Parentheses
### 62 Unique Paths
### 350	Intersection of Two Arrays II
### 1143 Longest Common Subsequence
### 268	Missing Number
### 1091 Shortest Path in Binary Matrix
### 859	Buddy Strings
### 209	Minimum Size Subarray Sum
### 5 Longest Palindromic Substring
### 168	Excel Sheet Column Title
### 136	Single Number
### 16 3Sum Closest
### 1528 Shuffle String
### 38 Count and Say
### 384	Shuffle an Array
### 572	Subtree of Another Tree
### 334	Increasing Triplet Subsequence
### 51 N-Queens
### 516	Longest Palindromic Subsequence
### 349	Intersection of Two Arrays
### 7 Reverse Integer
### 24 Swap Nodes in Pairs
### 178	Rank Scores
### 303	Range Sum Query - Immutable
### 131	Palindrome Partitioning
### 134	Gas Station
### 44 Wildcard Matching
### 442	Find All Duplicates in an Array
### 706	Design HashMap
### 160	Intersection of Two Linked Lists
### 665	Non-decreasing Array
### 75 Sort Colors
### 239	Sliding Window Maximum
### 205	Isomorphic Strings
### 858	Mirror Reflection
### 116	Populating Next Right Pointers in Each Node
### 414	Third Maximum Number
### 72 Edit Distance
### 25 Reverse Nodes in k-Group
### 208	Implement Trie (Prefix Tree)
### 28 Implement strStr()
### 159	Longest Substring with At Most Two Distinct Characters
### 617	Merge Two Binary Trees
### 328	Odd Even Linked List
### 191	Number of 1 Bits
### 235	Lowest Common Ancestor of a Binary Search Tree
### 6 ZigZag Conversion
### 151	Reverse Words in a String
### 141	Linked List Cycle
### 221	Maximal Square
### 74 Search a 2D Matrix
### 94 Binary Tree Inorder Traversal
### 240	Search a 2D Matrix II
### 26 Remove Duplicates from Sorted Array
### 145	Binary Tree Postorder Traversal
### 13 Roman to Integer
### 41 First Missing Positive
### 64 Minimum Path Sum
### 445	Add Two Numbers II
### 105	Construct Binary Tree from Preorder and Inorder Traversal
### 218	The Skyline Problem
### 112	Path Sum
### 55 Jump Game
### 48	Rotate Image
### 155	Min Stack
### 395	Longest Substring with At Least K Repeating Characters
### 322	Coin Change
### 104	Maximum Depth of Binary Tree
### 4 Median of Two Sorted Arrays
### 101	Symmetric Tree
### 310	Minimum Height Trees
### 47 Permutations II
### 394	Decode String


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