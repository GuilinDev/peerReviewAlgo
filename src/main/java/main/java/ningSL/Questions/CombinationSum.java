package main.java.ningSL.Questions;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {
    public static void main(String[] args) {
        int[] can = new int[]{2,5};
        int target = 7;
        System.out.println(combinationSum(can,target));

    }

        public static List<List<Integer>> combinationSum(int[] candidates, int target) {
            List<List<Integer>> res = new ArrayList<>();
            if(candidates == null || candidates.length == 0) return res;
            List<Integer> temp = new ArrayList<>();
            backTracking(res,temp, candidates, target, 0);
            return res;
        }
        public static void backTracking(List<List<Integer>> res, List<Integer> temp, int[] candidates, int target, int level) {
            System.out.println("level: " + level +" temp:  " + temp.toString());
            if (target < 0) {
                System.out.println("less return");
                return;
            }
            if(target == 0){
                res.add(new ArrayList<>(temp));
                System.out.println("equal return");
                return; //要返回
            }
            for (int i = level; i < candidates.length;  i ++){ // 用LEVEL是因为 避免出现相同子集（不同顺序 但是内容一样）
                temp.add(candidates[i]);
            //    System.out.println("level: " + level + " i:  " + i);
                backTracking(res,temp,candidates, target - candidates[i], i); // 注意必须是I  因为元素可以再利用
                temp.remove(temp.size() - 1);
              //  System.out.println("remove last");
            }
            int f = 1;
            System.out.println("Done: " + f);
        }

}
