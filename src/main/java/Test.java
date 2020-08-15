import java.util.HashMap;
import java.util.Map;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello");
        int[] nums = new int[]{1,2,3,4,6,1,7,9};
        int k = 7;
        String s = "anc";
       System.out.println(subarraySum(nums,k));
        System.out.println(s.substring(0,0));

    }

        public static int subarraySum(int[] nums, int k) {
            int sum = 0, result = 0;
            Map<Integer, Integer> preSum = new HashMap<>();
            preSum.put(0, 1);
            for (int i = 0; i < nums.length; i++) {
                sum += nums[i];
                if (preSum.containsKey(sum - k)) {
                    result += preSum.get(sum - k);
                }
                preSum.put(sum, preSum.getOrDefault(sum, 0) + 1);
            }

            return result;
        }

}
