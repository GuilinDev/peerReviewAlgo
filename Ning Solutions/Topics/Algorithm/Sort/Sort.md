```java
class Sort {
private static void swap(Object[] arr, int i, int j) {
        Object t = arr[i];
        arr[i] = arr[j];
        arr[j] = t;
    }
//=====================================================================
public static void selectSort(int[] arr){

        int n = arr.length;
        for( int i = 0 ; i < n ; i ++ ){
            int minIndex = i;
            for( int j = i + 1 ; j < n ; j ++ )
                if( arr[j] < arr[minIndex] )
                    minIndex = j;
            swap( arr , i , minIndex);
        }
    }

//======================================================================
public static void InsertionSort(Comparable[] arr){
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            for( int j = i; j > 0 && arr[j].compareTo(arr[j-1]) < 0 ; j--)
                swap(arr, j, j-1);

        }
    }
//====================================================================  
static void heapify(int[] array, int length, int i) {
    int left = 2 * i + 1, right = 2 * i + 2;
    int largest = i;

    if (left < length && array[left] > array[largest]) {
        largest = left;
    }
    if (right < length && array[right] > array[largest]) {
        largest = right;
    }

    if (largest != i) {
        int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
        heapify(array, length, largest);
    }
}

public static void heapSort(int[] array) {
    if (array.length == 0) return;

    int length = array.length;
    for (int i = length / 2-1; i >= 0; i--) 
        heapify(array, length, i);

    for (int i = length - 1; i >= 0; i--) {
        int temp = array[0]; array[0] = array[i]; array[i] = temp;
        heapify(array, i, 0);
    }
}


//====================================================================  
public static void mergeSort(int[] array, int left, int right) {
    if (right <= left) return;
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
    
//=======================================================================    
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
            swap(a, counter, i);
            counter++;
        }
    }
    swap(a, pivot, count);
    return counter;
}
public void swap(int[] nums, int i, int j){
    int temp = nums[i];
    nums[i] = nums[j];
    nums[j] = temp;
}
}

//======================================================================= 
// 493. Reverse Pairs 
// important reverse pair if i < j and nums[i] > 2*nums[j].
public class Solution {
    public int reversePairs(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        return mergeSort(nums, 0, nums.length - 1);
    }
    private int mergeSort(int[] nums, int l, int r) {
        if (l >= r) return 0;
        int mid = l + (r - l)/2;
        int count = mergeSort(nums, l, mid) + mergeSort(nums, mid + 1, r);
        int[] cache = new int[r - l + 1];
        int i = l, t = l, c = 0;
        for (int j = mid + 1; j <= r; j++, c++) {
            while (i <= mid && nums[i] <= 2 * (long)nums[j]) i++;
            while (t <= mid && nums[t] < nums[j]) cache[c++] = nums[t++];
            cache[c] = nums[j];
            count += mid - i + 1;
        }
        while (t <= mid) cache[c++] = nums[t++];
        System.arraycopy(cache, 0, nums, l, r - l + 1);
        return count;
    }
}


```
 