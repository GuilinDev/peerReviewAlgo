package main.java.ningSL.Questions;

import java.util.HashMap;

//SubArraySum
class SubArraySum {
    public static void main(String[] args) {
        int[] nums = new int[]{-1,1,-3,-4,-5,-6,18};
        System.out.println(subarraySum(nums,0));
    }
    public static int subarraySum(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int sum = 0;
        map.put(0, 0);
        int max = 0;
        for (int i = 0; i < nums.length; i ++) {
            sum += nums[i];
            if(map.containsKey(sum - k)) {
               max = Math.max(max, i - map.get(sum - k) + 1);
            }
            map.put(sum, i + 1);
        }
        return max;
    }
}
