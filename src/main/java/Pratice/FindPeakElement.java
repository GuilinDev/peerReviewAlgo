package Pratice;

public class FindPeakElement {
   /* Input: nums = [1,2,1,3,5,6,4]
    Output: 5
    Explanation: Your function can return either index number 1 where the peak element is 2, or index number 5 where the peak element is 6.*/
   public int findPeakElement(int[] nums) {
       int N = nums.length;
       int left = 0, right = N - 1;
       while (right - left > 1) {
           int mid = left + (right - left) / 2;
           if (nums[mid] < nums[mid + 1]) {
               left = mid + 1;
           } else {
               right = mid;
           }
       }
       return (left == N - 1 || nums[left] > nums[left + 1]) ? left : right;
   }

    public int findPeakElement2(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
