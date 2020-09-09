```java
// 108. Convert Sorted Array to Binary Search Tree
class Solution {
    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        return bstHelper(nums, 0, nums.length - 1); 
    }
    
    public TreeNode bstHelper(int[] nums, int start, int end){
        if (end < start) {
            return null;
        }
        int mid = start + (end - start) / 2; // don't use start + (end - start) >> 1;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = bstHelper(nums, start, mid - 1);
        root.right = bstHelper(nums, mid + 1, end);
        return root;
    }
}

// 929. Unique Email Addresses
class Solution {
    public int numUniqueEmails(String[] emails) {
        Set<String> set = new HashSet<>();
        for (String email : emails) {
            String[] mail = email.split("@");
            char[] mailChars = mail[0].toCharArray();
            StringBuilder localName = new StringBuilder();
            for (char c : mailChars){
                if(c == '.') continue;
                if(c == '+') break;
                localName.append(c);
            }
            set.add(localName.toString()+ "@" + mail[1]);
        }
        return set.size();
    }

    public int numUniqueEmails(String[] emails) {
        Set<String> normalized = new HashSet<>(); // used to save simplified email address, cost O(n) sapce.
        for (String email : emails) {
            String[] parts = email.split("@"); // split into local and domain parts.
            String[] local = parts[0].split("\\+"); // split local by '+'.
            normalized.add(local[0].replace(".", "") + "@" + parts[1]); // remove all '.', and concatenate '@' and domain.        
        }
        return normalized.size();
    }
}

//1011. Capacity To Ship Packages Within D Days
class Solution {
   public  int shipWithinDays(int[] weights, int D) {
        int left = 0, right = 0;
        for (int w: weights) {
            left = Math.max(left, w); // 每天最小需要携带的重量
            right += w; //每天最大携带重量 就是一天带完。 问题变成在最小和最大直接找一个最小值 使得搬运天数为D
        }
       
      while(left <= right){
            int mid = (left+right)/2;
            if(greaterThenD(mid,weights,D)) left = mid + 1; // 当超过D天时候意味着 我们需要提高每天的最大运载
            else right = mid - 1;
        }
        return left;
    }
    
 private boolean greaterThenD(int maxLoad, int[] weights, int d) { //每天的最大运载重量为w，问在d天内能否运完
    int curLoad = maxLoad;
    for (int weight : weights) {
        if (d <= 0) {
            return true;
        }
        curLoad -= weight;
        while (curLoad < 0) {
            curLoad = maxLoad - weight; 
            // need start from maxLoad and minus this weight again. 
            // since previous curload already invalid
            d--;
        }
     }
        return d <= 0;
   }
}

// 538. Convert BST to Greater Tree
class Solution {
       public TreeNode convertBST(TreeNode root) {
        TreeNode node = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        int sum = 0;
        while (node != null || !stack.isEmpty()) {
           while (node != null){
               stack.push(node);
               node = node.right; // 二叉树从大到小的方式 值得学习,先遍历右子树
             }
                node = stack.pop();//第一个是最大值弹出 
                sum += node.val;
                node.val = sum;
                node = node.left;
        }
        return root;
    }
}

public class Solution {
    int sum = 0; //全局变量 
    public TreeNode convertBST(TreeNode root) { // 右根左 不是后续遍历 后续是左根右
        if (root == null) return null;
        convertBST(root.right);
        root.val += sum;
        sum = root.val;
        convertBST(root.left);
        return root;
    }
}
// 703. Kth Largest Element in a Stream
   class KthLargest {
        final PriorityQueue<Integer> q;
        final int k;
       // PriorityQueue<Integer> queue = new PriorityQueue<>(10, Collections.reverseOrder()); MAX HEAP
        public KthLargest(int k, int[] a) {
            this.k = k;
            q = new PriorityQueue<>(k);
            for (int n : a)
                add(n);
        }

        public int add(int n) {
            if (q.size() < k)
                q.offer(n);
            else if (q.peek() < n) {
                q.poll();
                q.offer(n);
            }
            return q.peek();
        }
    }
// 776. Split BST
class Solution {
  public TreeNode[] splitBST(TreeNode root, int V) {
        if(root == null) {
            return new TreeNode[]{null, null};
        }
        if(root.val>V){
            TreeNode[] subR = splitBST(root.left, V);
             root.left = subR[1];
             return new TreeNode[]{subR[0], root};
        } else {
            TreeNode[] subR = splitBST(root.right, V);
            root.right = subR[0];
            return new TreeNode[]{root, subR[1]};
        }
    }
}

// 35. Search Insert Position
class Solution {
    public int searchInsert(int[] nums, int target) {
        if (nums == null || nums.length == 0) return 0;
        int left = 0; int right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left)/ 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            } 
        }
        return left; //用right 考虑到当只有一个元素时候 如果TARGET小 就返回 -1 了
    }
}

// 674. Longest Continuous Increasing Subsequence
class Solution {
    public int findLengthOfLCIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int max = 1;
        int curMax = 1;
        for (int i = 1; i < nums.length; i ++){
            if (nums[i] > nums[i - 1]){
                curMax ++;
                max = Math.max(curMax, max);
            } else { // need else;
                curMax = 1;
            }
        }
        return max;
    }
}

// 1143. Longest Common Subsequence
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int[][] dp = new int[text1.length() + 1][text2.length() + 1];
        for(int i = 0; i < text1.length(); i ++) {
            for (int j = 0; j < text2.length(); j ++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;  // 两个中有一个空字符串情况
                }
                if (text1.charAt(i) == text2.charAt(j)) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                } else {
                    dp[i + 1][j + 1] = Math.max(dp[i][j + 1], dp[i + 1][j]); // 这里不能加一 
                }
            }
        }
        return dp[text1.length()][text2.length()];
        
    }
}

// 409. Longest Palindrome
class Solution {
public int longestPalindrome(String s) {
        if(s==null || s.length()==0) return 0;
        HashSet<Character> hs = new HashSet<Character>();
        int count = 0;
        for(int i=0; i<s.length(); i++){
            if(hs.contains(s.charAt(i))){
                hs.remove(s.charAt(i));
                count++;
            }else{
                hs.add(s.charAt(i));
            }
        }
        if(!hs.isEmpty()) return count*2+1;
        return count*2;
}
}

//54. Spiral Matrix
class Solution {
    
      public List<Integer> spiralOrder(int[][] matrix) {
        LinkedList<Integer> result = new LinkedList<>();
        if(matrix==null||matrix.length==0) return result;
        int left = 0;
        int right = matrix[0].length - 1;
        int top = 0;
        int bottom = matrix.length - 1;
        int numEle = matrix.length * matrix[0].length;
        while (numEle >= 1) {
            for (int i = left; i <= right && numEle >= 1; i++) {
                result.add(matrix[top][i]);
                numEle--;
            }
            top++;
            for (int i = top; i <= bottom && numEle >= 1; i++) {
                result.add(matrix[i][right]);
                numEle--;
            }
            right--;
            for (int i = right; i >= left && numEle >= 1; i--) {
                result.add(matrix[bottom][i]);
                numEle--;
            }
            bottom--;
            for (int i = bottom; i >= top && numEle >= 1; i--) {
                result.add(matrix[i][left]);
                numEle--;
            }
            left++;
        }
        return result;
    }
}
// 59. Spiral Matrix II
class Solution {
    public int[][] generateMatrix(int n) {
        int top1 = 0;
        int top2 = n - 1;
        int down1 = 0;
        int down2 = n - 1;
        int[][] board = new int[n][n];
        int cur = 1; // draw the picture;
        while (cur <= n * n) {
            for (int i = top1; i <= top2; i ++){ // need <= top2
                board[top1][i] = cur;
                cur ++;
            }
            top1 ++;
            for (int j = top1; j <= down2; j ++) {
                board[j][top2] = cur;
                cur ++;
            }
            top2 --;
            for (int i = top2; i >= down1; i --){
                board[down2][i] = cur;
                cur ++;
            }
            down2 --;
            for (int i = down2; i >= top1; i --) {
                board[i][down1] = cur;
                cur ++;
            }
            down1 ++;
        }
        return board;
    }
}
//17. Letter Combinations of a Phone Number
class Solution {
    public List<String> letterCombinations(String digits) {
        List<String> res = new ArrayList<>();
        String[] phone = new String[]{" ","","abc", "def", "ghi","jkl", "mno","pqrs","tuv","wxyz"};// 0 put " ";
        if(digits.length() != 0) { // don't miss this.
        lettterCombinHelper(digits, "", res, phone, 0);
        }
        return res;
    }
    
    public void lettterCombinHelper(String digits, String temp, List<String> res, String[] phone, int start) {
        if (temp.length() == digits.length()) {
            res.add(temp);
            return; // don't forget this
        }
        for (int i = start; i < digits.length(); i ++ ) {
            String c = digits.substring(i, i + 1);  //  can't use Integer.valueOf(digits.charAt(i)); 
            int index = Integer.valueOf(c);
            String nums = phone[index];
            for (int j = 0; j < nums.length(); j ++) {
                char cur = nums.charAt(j);
                lettterCombinHelper(digits, temp + cur, res, phone, i + 1);
            }
        }
    }
}
//136. Single Number
class Solution {
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int num : nums){
            ans = ans^ num;
        }
        return ans;
    }
}

// 7. Reverse Integer
class Solution {
    public int reverse(int x) {
         long result = 0;
      while (x != 0) {
         result = (result * 10) + (x % 10);
         if (result > Integer.MAX_VALUE || result < Integer.MIN_VALUE) {
            return 0;
         }
         x /= 10;
      }
      return (int)result;
    }
}
// 19. Remove Nth Node From End of List
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(-1);
        ListNode slow = dummy, fast = dummy;
        dummy.next = head;
        
        int steps = n;
        while (steps > 0) { // fast先走n步
            fast = fast.next;
            steps--;
        }
        while (fast.next != null) { // 同时走直到fast走到最后一个
            slow = slow.next;
            fast = fast.next;
        }
        slow.next = slow.next.next; //此时slow在待移除节点的前一个，移除
        return dummy.next;
    }
}
// 238. Product of Array Except Self
class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] front = new int[n]; int[] back = new int[n]; int[] res = new int[n];
        front[0] = 1;
        back[n - 1] = 1;
        for (int i = 1; i < n; i ++) {
            front[i] = nums[i - 1] * front[i - 1];
        }
        
        for (int j = n - 2; j >= 0; j --) {
            back[j] = back[j + 1] * nums[j + 1];
        }
        
        for (int i = 0; i < n; i ++) {
            res[i] = front[i] * back[i];
        }
        return res;
    }
}


```
