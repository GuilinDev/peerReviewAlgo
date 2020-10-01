```java
class Sort {
// merge sort is post order; quick order is pre order
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
            arr[left + p] = temp[p];
        }// don't forget this
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
public class TreeNode { 
    int val; 
    TreeNode left; 
    TreeNode right; 
    TreeNode(int x) { 
        val = x; 
    } 
} 

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








```
 