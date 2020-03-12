1:Two sum
class Solution {
    public int[] twoSum(int[] nums, int target) {
        if (nums == null || nums.length < 2) return new int[0];
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for (int i = 0; i < nums.length; i ++) {
            if (map.containsKey(target - nums[i])) { // target - nums[i]; // containsKey not contains
                return new int[]{map.get(target - nums[i]),i};
            }
            map.put(nums[i],i);
        }
        return new int[0];        
    }
}

2:Merge Two Sorted Lists   
class Solution {
   public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null || l2 == null) {
            return l1 == null ? l2 : l1;
        }
        ListNode head = null; 
        if (l1.val < l2.val) {
            head = l1; // not head.next = l1. since null can't have next;
            head.next = mergeTwoLists(l1.next, l2);
        } else {
            head = l2;
            head.next = mergeTwoLists(l1,l2.next);
        }  
} }

3:Remove Duplicates from Sorted Array    
class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int total = 1;
        for (int i = 1; i < nums.length; i ++) {
            if (nums[i] > nums[i - 1]) {
                nums[total] = nums[i];
                total ++;
            }    }
        return total;    
    }  }

4:Plus One  
class Solution {
    public int[] plusOne(int[] digits) {
        if (digits == null || digits.length == 0) return new int[0];
        for (int i = digits.length - 1; i >= 0; i --) { // i -- not i ++ starting from digits.length - 1;
            if (digits[i] < 9) {
                digits[i] = digits[i] + 1;
                return digits;
            }
            digits[i] = 0; // one will go to next position;
        }
        int[] result = new int[digits.length + 1];
            result[0] = 1;
         return result;
    }
}

 5 Merge Sorted Array
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1 == null || nums2 == null ) return;
        int lastIndex = m + n - 1;
        int index1 = m - 1;
        int index2 = n - 1;
        while (index1 >= 0 && index2 >= 0) { // not using for in the lastIndex
            if (nums1[index1] > nums2[index2]) {
                nums1[lastIndex --] = nums1[index1 --]; // don't confused for nums1 and nums2.
            } else {
                nums1[lastIndex --] = nums2[index2--];
            }
        }
        while(index2 >= 0) {
            nums1[lastIndex --] = nums2[index2--];
        }
    }
}

6: Rotate Array
  public void rotate(int[] nums, int k) { // 三次倒转
       k  %= nums.length;
       if (k == 0) return ;
       revertArray(nums, 0 , nums.length - 1);
       revertArray(nums,0, k- 1);
       revertArray(nums,k,nums.length - 1);
    }
    public void revertArray(int[] nums, int start, int end) {
         while(start < end) {
           int temp = nums[end];
           nums[end] = nums[start];
           nums[start] = temp; 
           start ++;
           end --;
         }
    }
