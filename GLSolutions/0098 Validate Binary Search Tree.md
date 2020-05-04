## Solution 1
> 前序遍历，用Long值来cover所有的Integer的情况，注意BST是不能等于的

> 前序递归写法
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        //用Long的原因是可能TreeNode里面的val有可能是Integer.MIN_VALUE或者Interger.MAX_VALUE，BST里一般没有重复值
        return dfs(root, java.lang.Long.MIN_VALUE, java.lang.Long.MAX_VALUE);
    }
    private boolean dfs(TreeNode node, long lower, long upper) {
        if (node == null) {
            return true;
        }
        if (node.val <= lower || node.val >= upper || !dfs(node.left, lower, node.val) || !dfs(node.right, node.val, upper)) {
            return false;
        }
        return true;
    }
}
```

> 前序使用栈，掌握递归就行
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Stack<TreeNode> st = new Stack<>();
    Stack<Long> upperList = new Stack<>(), 
        lowerList = new Stack<>();
        
    public boolean isValidBST(TreeNode root) {
        long lower = Long.MIN_VALUE, upper = Long.MAX_VALUE, val;
        update(root, lower, upper);
        while (!st.empty()) {
            root = st.pop();
            lower = lowerList.pop();
            upper = upperList.pop();
            if (root == null) continue;
            val = (long)root.val;
            if (val <= lower) return false;
            if (val >= upper) return false;
            update(root.right, val, upper);
            update(root.left, lower, val);
        }
        return true;
    }

    void update(TreeNode node, long lower, long upper) {
        st.push(node);
        lowerList.push(lower);
        upperList.push(upper);
    }
}
```

> 前序使用队列BFS，掌握递归就行
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    Queue<TreeNode> queue = new LinkedList<>();
    Queue<Long> upperList = new LinkedList<>(), 
        lowerList = new LinkedList<>();
        
    public boolean isValidBST(TreeNode root) {
        long lower = Long.MIN_VALUE, upper = Long.MAX_VALUE, val;
        update(root, lower, upper);
        while (!queue.isEmpty()) {
            root = queue.poll();
            lower = lowerList.poll();
            upper = upperList.poll();
            if (root == null) continue;
            val = root.val;
            if (val <= lower) return false;
            if (val >= upper) return false;
            update(root.left, lower, val);
            update(root.right, val, upper);
        }
        return true;
    }

    void update(TreeNode root, long lower, long upper) {
        queue.offer(root);
        lowerList.offer(lower);
        upperList.offer(upper);
    }
}

```

## Solution 2
> 中序遍历, 递归解法
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    // 类变量，在dfs中不用每次递归都传参
    long lastVal = Long.MIN_VALUE;
    public boolean isValidBST(TreeNode root) {
        return dfs(root);
    }
    boolean dfs(TreeNode node) {
        if (node == null) {
            return true;
        }
            
        /* 中序遍历左根右，只需对值判断一次，先递归到最左边，对比lastVal（最小值开始），然后更新lastVal为当前node，然后对比当前node的所有右子树，相当于比左子树的时候，lastVal是left，root应该比left大；比较右子树的时候，lastVal是root，右子树应该比root大
        */
        if (!dfs(node.left)) {
            return false;
        }
        if (node.val <= lastVal) { //这是上一轮的lastVal
            return false;
        }
        lastVal = (long) node.val; //这里更新lastVal
        if (!dfs(node.right)) {
            return false;
        }
        return true;
    }
}
```
> 中序遍历, Stack解法
```java
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public boolean isValidBST(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        long lastVal = Long.MIN_VALUE;
        
        while (!stack.empty() || root != null) {
            while(root != null) { //先到最左的子树
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= lastVal) {
                return false;
            }
            lastVal = (long)root.val;
            root = root.right;
        }
        return true;
    }
}
```