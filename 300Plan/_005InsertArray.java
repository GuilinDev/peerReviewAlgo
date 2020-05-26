import java.util.Arrays;

public class _005InsertArray {
    public static void main(String[] args) {
        int[] arr = {0, 10, 20, 30, 40, 0, 0};
        Arrays.stream(arr).forEach(ele -> System.out.print(ele + " "));
        _005InsertArray test = new _005InsertArray();
        int[] resultArr = test.addX(2, arr, 321);
        System.out.println();
        Arrays.stream(resultArr).forEach(ele -> System.out.print(ele + " "));
    }

    public static int[] addX(int n, int arr[], int x) {

        int len = arr.length;
        // create a new array of size n+1
        int newarr[] = new int[len + 1];

        /**
         * insert the elements from the old array into the new array
         * insert all elements till n then insert x at n+1
         */
        for (int i = 0; i < n; i++)
            newarr[i] = arr[i];
        for (int i = len - 1; i >= n; i--) {
            newarr[i + 1] = arr[i];
        }

        newarr[n] = x;

        return newarr;
    }


}
