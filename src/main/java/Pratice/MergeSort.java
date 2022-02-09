package Pratice;

public class MergeSort {
    public static void main(String[] args) {

    }

    public void mergeSort(int[] nums) {

        int left = 0; int right = nums.length;
        mergeSortHelper(nums, left,right);

    }
    public void mergeSortHelper(int[] nums, int left, int right) {
        if(nums == null || nums.length == 0) return ;
        int mid = left + (right - left) / 2;
        mergeSortHelper(nums, left, mid);
        mergeSortHelper(nums, mid + 1, right);
        mergeSortedArray(nums, left, mid, right);
    }

    public void mergeSortedArray(int[] nums, int left, int mid, int right){
        int[] temp = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while(i <= mid && j <= right){
            if(nums[i] < nums[j]){
                temp[k ++] = nums[i++];
            } else {
                temp[k ++] = nums[j ++];
            }

        }
        while(i <= mid) temp[k ++] = nums[i ++];
        while(j <= right) temp[k ++] = nums[j ++];

        for (int p = 0; p < temp.length; p ++){
            nums[left + p ] = temp[p];
        }



    }


}
