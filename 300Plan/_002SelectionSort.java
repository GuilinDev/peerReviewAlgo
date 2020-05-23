import java.util.Arrays;

public class _002SelectionSort {
    public static void main(String[] args) {
        _002SelectionSort test = new _002SelectionSort();
        test.selectionSort(new int[]{2,100,3,5,0});
    }
    private void selectionSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        Arrays.stream(arr).forEach(ele -> System.out.print(ele + " "));
        int len = arr.length;
        for (int i = 0; i < len - 1; i++) {
            int min_index = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[j] < arr[min_index]) { // find the minimum
                    min_index = j;
                }
            }
            //Swap the found minimum element with the first element
            if (i != min_index) {
                arr[i] = arr[i] ^ arr[min_index];
                arr[min_index] = arr[i] ^ arr[min_index];
                arr[i] = arr[i] ^ arr[min_index];
            }
        }
        System.out.println();
        Arrays.stream(arr).forEach(ele -> System.out.print(ele + " "));
    }
}
