public class Test2 {
    public static void main(String[] args) {
        int[] nums = new int[]{1,-2,-3,-4};
        System.out.println(findSings(nums));
    }

    public static int findSings(int[] nums){
        int total = 0;
        for (int i = 0; i < nums.length; i ++) {
            if(nums[i] == 0) return 0;
            if(nums[i] < 0) total ++;
        }
        if(total % 2 == 0) return 1;
        return -1;
    }

}
