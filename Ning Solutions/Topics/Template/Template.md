```java
class Sort {
// merge sort 是后续遍历; quick order 是前序遍历
//==============================Merge sort======================================
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return; // what difference if I put right < left
    int mid = (left + right) / 2; // (left + right) / 2
    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
           if(arr[i] <= arr[j]){
             temp[k++] = arr[i++];
           } else 
             temp[k++] = arr[j++];
        }
        while (i <= mid)   temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];// don't forget this
        }
        // 也可以用 System.arraycopy(a, start1, b, start2, length)
    }
    
//=================================Quick sort======================================    
public static void quickSort(int[] array, int begin, int end) {
    if (end <= begin) return;
    int pivot = partition(array, begin, end);
    quickSort(array, begin, pivot - 1);
    quickSort(array, pivot + 1, end);
}

static int partition(int[] a, int begin, int end) { 
    // pivot: 标杆位置，counter: 小于pivot的元素的个数
    int pivot = end, counter = begin;
    for (int i = begin; i < end; i++) {
        if (a[i] < a[pivot]) {
            int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
            counter++;
        }
    }
    int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
    return counter;
}
//================================Topologic sort=======================================  
void sort() {
    Queue<Integer> q = new LinkedList(); // 定义一个队列 q
    // 将所有入度为 0 的顶点加入到队列 q
    for (int v = 0; v < V; v++) {
        if (indegree[v] == 0) q.add(v);
    }
    // 循环，直到队列为空
    while (!q.isEmpty()) {
        int v = q.poll();
        // 每次循环中，从队列中取出顶点，即为按照入度数目排序中最小的那个顶点
        print(v); // DO something here
        // 将跟这个顶点相连的其他顶点的入度减 1，如果发现那个顶点的入度变成了 0，将其加入到队列的末尾
        for (int u = 0; u < adj[v].length; u++) {
            if (--indegree[u] == 0) {
                q.add(u);
            }
        }
    }
}
}

 //================================Search======================================= 
class search {
   public int binarySearch(int[] array, int target) { 
       int left = 0, right = array.length - 1;
       while (left <= right) { // <= 
          int mid = (right - left) / 2 + left; 
           if (array[mid] == target) { 
               return mid; 
           } else if (array[mid] > target) { 
               right = mid - 1; 
           } else { 
               left = mid + 1; 
           } 
       } 
       return -1; 
   } 
 //================================DFS BFS======================================= 
 class Solution {
     public List<Integer> preorderTraversal(TreeNode root) {
         LinkedList<Integer> res = new LinkedList<>();
         if (root == null) return res;
         Stack<TreeNode> temp = new Stack<TreeNode>(); // need to be TreeNode not Integer
         temp.push(root);
         while (!temp.isEmpty()) {
             TreeNode node = temp.pop(); // TreeNode not node
             res.add(node.val);
             if (node.right != null) {
                 temp.push(node.right); // right first.
             }
             if (node.left != null) {
                 temp.push(node.left);
             }   
         }
         return res;
     }
 }

class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode cur = root;
        while(cur != null || !stack.isEmpty()){
            while (cur != null){
                stack.push(cur);
                cur = cur.left;
            }
            TreeNode node = stack.pop();
            res.add(node.val);
            cur = node.right; // not cur.right, it will be null in the begin, need to be node.right; 
        }
      return res;  
    }
}
//BFS
class Solution {
public List<List<Integer>> levelOrder(TreeNode root) { 
    List<List<Integer>> allResults = new ArrayList<>(); 
    if (root == null) { 
        return allResults; 
    } 
    Queue<TreeNode> nodes = new LinkedList<>(); 
    nodes.add(root); 
    while (!nodes.isEmpty()) { 
        int size = nodes.size(); 
        List<Integer> results = new ArrayList<>(); 
        for (int i = 0; i < size; i++) { 
            TreeNode node = nodes.poll(); 
            results.add(node.val); 
            if (node.left != null) { 
                nodes.add(node.left); 
            } 
            if (node.right != null) { 
                nodes.add(node.right); 
            } 
        } 
        allResults.add(results); 
    } 
    return allResults; 
} 
}

}
 //================================Sliding window=======================================
// 76 567 438 3
class Solution {   
   public static String minWindow(String s, String t) {
           HashMap<Character, Integer> window = new HashMap<>();
           HashMap<Character, Integer> need = new HashMap<>();
           for (char t1 : t.toCharArray()) {
               need.put(t1, need.getOrDefault(t1,0) + 1);
           }
           int left = 0; int right = 0; int valid = 0;
           while (right < s.length()) { // 区间[left, right)是左闭右开的，所以初始情况下窗口没有包含任何元素：
               char s1 = s.charAt(right);
               right ++;
               if (need.containsKey(s1)) { 
                  window.put(s1,window.getOrDefault(s1,0) + 1);
               // Do something in window and may need to add valid
               if (window.get(s1).equals(need.get(s1))){ // 哈希表值对比不能用 ==
                    valid ++;
                }
                }
               // 右指针移动当于在寻找一个「可行解」，然后移动左指针在优化这个「可行解」，最终找到最优解
               while (valid == need.size()) {
                   char s2 = s.charAt(left);
                   left ++;
                   if (need.containsKey(s2)) { 
                   if (window.get(s2).equals(need.get(s2))){
                      valid --;
                   }
                      window.put(s2,window.get(s2) - 1);
                   }
   
               }
           }
       }
}

//================================Trie=======================================
public class Trie {
    private boolean isEnd;
    private Trie[] next;
    public Trie() {
        isEnd = false;
        next = new Trie[26];
    }
 
    public void insert(String word) {
        if (word == null || word.length() == 0) return;
        Trie curr = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i ++) {
            int n = words[i] - 'a';
            if(curr.next[n] == null) {
                curr.next[n] = new Trie(); // not new Trie[26];
            }
            curr = curr.next[n];
        }
        curr.isEnd = true;
    }

    public boolean search(String word) {
        Trie node = searchPrefix(word);
        return node!=null && node.isEnd;
    }

    public boolean startsWith(String prefix) {
        Trie node = searchPrefix(prefix);
        return node != null;
    }

    private Trie searchPrefix(String word) {
        Trie node = this;
        char[] words = word.toCharArray();
        for (int i = 0; i < words.length; i ++) {
            node = node.next[words[i] - 'a'];
            if(node == null) return null;
        }
        return node;
    }
}
//================================Union Find=======================================
public class UnionFind {
        public static int count = 0;
        private int[] parent;
        public UnionFind(int n) {
            count = n;
            parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }
        public int find(int p, int[] parent) {
           if(p == parent[p]) return p;
           parent[p] = find(parent[p], parent);
           return parent[p];
        }
        public void union(int p, int q) {
            int rootP = find(p, parent);
            int rootQ = find(q, parent);
            if (rootP == rootQ) return;
            parent[rootP] = rootQ;
            count--;
        }
}
//================================String matching=======================================
public class forceSearch{
    public static int forceSearch(String txt, String pat) {
        int M = txt.length();
        int N = pat.length();
        for (int i = 0; i <= M - N; i++) {
            int j;
            for (j = 0; j < N; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) break;
            }
            if (j == N) {
                return i;
            }
            }
            return -1;
    }
}








```
 