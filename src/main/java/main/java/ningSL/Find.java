package main.java.ningSL;

import java.util.HashMap;
import java.util.Map;

class Find {
    int[] parent;
    HashMap<Integer, Integer> map   = new HashMap<>();
    public static void main(String[] args) {
        int[] nums = new int[]{5,4,1,3,2,7,8};
         System.out.println(longestConsecutive(nums));
    }

    public static int longestConsecutive(int[] nums) {
        UF uf = new UF(nums.length);
        Map<Integer,Integer> map = new HashMap<Integer,Integer>(); // <value,index>
        for (int i = 0; i < nums.length; i ++){
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i ++){
            if (map.containsKey(nums[i] + 1)){
                uf.union(i, map.get(nums[i] + 1));
            }
            if (map.containsKey(nums[i] - 1)){
                uf.union(i, map.get(nums[i] - 1));
            }
        }

        return uf.maxUnion();
    }



}
