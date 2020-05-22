import java.util.Arrays;

public class _001BubbleSort {
    public static void main(String[] args) {
        _001BubbleSort test = new _001BubbleSort();
        test.bubbleSort(new int[]{2,100,3,5,0});
    }
    private void bubbleSort(int[] arr) {
        assert arr == null || arr.length == 0 : "Array should not be null";
        Arrays.stream(arr).forEach(ele -> System.out.print(ele + " "));
        int len = arr.length;

        for (int i = 0; i < len - 1; i++) {
            // bigger number to right
            for (int j = i + 1; j < len; j++) {
                if (arr[i] > arr[j]) {
                    arr[i] = arr[i] ^ arr[j];
                    arr[j] = arr[i] ^ arr[j];
                    arr[i] = arr[i] ^ arr[j];
                }
            }
        }
        System.out.println();
        Arrays.stream(arr).forEach(ele -> System.out.print(ele + " "));
    }
}
