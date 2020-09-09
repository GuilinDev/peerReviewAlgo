```java
class Sort {
//==============================Merge sort======================================  
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return; // what difference if I put right < left
    int mid = (left + right) >> 1; // (left + right) / 2
    mergeSort(array, left, mid);
    mergeSort(array, mid + 1, right);
    merge(array, left, mid, right);
}

public static void merge(int[] arr, int left, int mid, int right) {
        int[] temp = new int[right - left + 1]; // 中间数组
        int i = left, j = mid + 1, k = 0;
        while (i <= mid && j <= right) {
            temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
        }
        while (i <= mid)   temp[k++] = arr[i++];
        while (j <= right) temp[k++] = arr[j++];

        for (int p = 0; p < temp.length; p++) {
            arr[left + p] = temp[p];
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

 



```
 