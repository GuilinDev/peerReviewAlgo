package DP;

import java.util.ArrayList;
import java.util.List;


public class CoinChanges {
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) {
  //     System.out.println(getGroup(30));
       int[] nums = {3,5};
      System.out.println(getMaxOnly(30, nums));
        System.out.println(max);
    }

    // 3* X + 5* Y + ...= total, and X + Y + Z = min.
   public static ArrayList<String> getGroup(int total){
       ArrayList<String> res = new ArrayList<>();
       for (int i = 0; i <= total/3; i ++){
           for (int j = 0; j <= total/5; j ++){
               int output = 3*i + 5* j;
               if (output == total){
                   res.add(i +"," + j);
               }
           }
       }
       return res;
   }

    public static List<List<Integer>> getGroup(int total, int[] nums){
        ArrayList<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<Integer>();
        getGroupHelper(total, nums, res, temp, 0,0 );
        return res;
    }
    public static void getGroupHelper(int total, int[] nums, List<List<Integer>> res, List<Integer> temp, int curSum, int index){
        if (curSum == total){
            max = Math.min(max, temp.size());
            res.add(new ArrayList(temp));
            return;
        }
        if (curSum > total) return;
        for (int i = index; i < nums.length; i ++){
            temp.add(nums[i]);
            getGroupHelper(total, nums, res, temp, curSum + nums[i], index);
            temp.remove(temp.size() - 1);
        }

    }

    public static int getMaxOnly(int total, int[] nums){
        getMaxOnlyHelper(total, nums, 0, 0);
        return max;

    }

    public static void getMaxOnlyHelper(int total, int[] nums, int index, int count){
        if (total == 0){
            max = Math.max(max, count);
            return;
        }
        if (total < 0) {
            return;
        }

        for (int i = index; i < nums.length; i ++){
            count++;
            getMaxOnlyHelper(total - nums[i], nums, index, count);
            count--;
        }
    }


    int getMinCountsHelper(int total, int[] values) {
        // 如果余额为0，说明当前组合成立，将组合加入到待选数组中
        if (0 == total) { return 0; }

        int valueLength = values.length;
        int minCount = Integer.MAX_VALUE;
        for (int i = 0;  i < valueLength; i ++) { // 遍历所有面值
            int currentValue = values[i];

            // 如果当前面值大于硬币总额，那么跳过
            if (currentValue > total) { continue; }

            int rest = total - currentValue; // 使用当前面值，得到剩余硬币总额
            int restCount = getMinCountsHelper(rest, values);

            // 如果返回-1，说明组合不可信，跳过
            if (restCount == -1) { continue; }

            int totalCount = 1 + restCount; // 保留最小总额
            if (totalCount < minCount) { minCount = totalCount; }
        }

        // 如果没有可用组合，返回-1
        if (minCount == Integer.MAX_VALUE) { return -1; }

        return minCount; // 返回最小硬币数量
    }

    int getMinCountOfCoinsAdvance() {
        int[] values = { 3, 5 }; // 硬币面值的数组
        int total = 11; // 总值

        return getMinCountsHelper(total, values); // 输出答案
    }

}
