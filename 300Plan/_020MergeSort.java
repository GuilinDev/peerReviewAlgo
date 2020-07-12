public class _020MergeSort {
    private void sort(int[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left ) / 2;

            // divide 成两部分
            sort(arr, left, mid);
            sort(arr, mid + 1, right);

            // 合并两部分
            merge(arr, left, right, mid);
        }
    }
    private void merge(int[] arr, int left, int right, int mid) {
        int lLen = mid - left + 1;
        int rLen = right - mid;
        int[] leftPart = new int[lLen];
        int[] rightPart = new int[rLen];

        for (int i = 0; i < lLen; i++) {
            leftPart[i] = arr[left + i];
        }
        for (int i = 0; i < rLen; i++) {
            rightPart[i] = arr[mid + 1 + i];
        }

        int lIndex = 0, rIndex = 0, index = left;

        while (lIndex < lLen && rIndex < rLen) {
            if (leftPart[lIndex] <= rightPart[rIndex]) {
                arr[index] = leftPart[lIndex];
                lIndex++;
            } else {
                arr[index] = rightPart[rIndex];
                rIndex++;
            }
            index++;
        }
        // 检查还未排序的
        while (lIndex < lLen) {
            arr[index] = leftPart[lIndex];
            lIndex++;
            index++;
        }
        while (rIndex < rLen) {
            arr[index] = rightPart[rIndex];
            rIndex++;
            index++;
        }
    }

    public static void main(String[] args) {
        _020MergeSort test = new _020MergeSort();
        int arr[] = { 12, 11, 13, 5, 6, 7 };
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
        test.sort(arr, 0, arr.length - 1);
        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}
