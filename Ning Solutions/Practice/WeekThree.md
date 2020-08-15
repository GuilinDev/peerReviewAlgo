```java
//426. Convert Binary Search Tree to Sorted Doubly Linked List
class Solution {
  public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }

        Node first = null;
        Node last = null;

        Deque<Node> stack = new ArrayDeque<>();
        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (first == null) {
                first = root;
            }
            if (last != null) {
                last.right = root;
                root.left = last;
            }
            last = root;
            root = root.right;
        }
        first.left = last;
        last.right = first;
        return first;
    }
}

// 173. Binary Search Tree Iterator
class BSTIterator {
    Stack<TreeNode> stack = new Stack<>();
    TreeNode cur = null;

    public BSTIterator(TreeNode root) {
        cur = root;
    }

    /** @return the next smallest number */
    public int next() {
        int res = -1;
        while (cur != null || !stack.isEmpty()) {
            // 节点不为空一直压栈
            while (cur != null) {
                stack.push(cur);
                cur = cur.left; // 考虑左子树
            }
            // 节点为空，就出栈
            cur = stack.pop();
            res = cur.val;
            // 考虑右子树
            cur = cur.right;
            break; // even sometime next() is o(h) but amortized time complex is o(1);
        }
        return res;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return cur != null || !stack.isEmpty();
    }
}

/*只需要把 stack 和 cur 作为成员变量，然后每次调用 next 就执行一次 while 循环，并且要记录当前值，结束掉本次循环。*/

// 127. Word Ladder
class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
       Set<String> wordSet = new HashSet(wordList);
       if (!wordSet.contains(endWord)) {
           return 0;
       } 
       Set<String> visited = new HashSet<>();    
       Queue<String> queue = new LinkedList<>();
       queue.offer(beginWord);
       visited.add(beginWord); 
       int level = 0;
       while (!queue.isEmpty()) {
           int size = queue.size();
           level ++;
           for (int i = 0; i < size; i ++) {
               String cur = queue.poll();
               if (cur.equals(endWord)) return level;
               for (int k = 0; k < cur.length(); k ++){
                   char[] chars = cur.toCharArray();// 不能放外面 因为每一次只改变一个字符 不能互相影响
                   for(char c = 'a'; c <= 'z'; c ++) {
                       chars[k] = c;
                       String str = new String(chars);
                       if (!visited.contains(str) && wordSet.contains(str)){
                           queue.offer(str);
                           visited.add(str);
                       }
                   }
               }
           }
       } 
      return 0;  
    }
}


```
