package main.java.ningSL.Questions;

import java.util.HashMap;
import java.util.Map;

public class MusicPairs {
    public static void main(String[] args) {
        int[] nums1 = { 37, 23, 60 };
        int[] nums2 = { 10, 50, 90, 30 };
        int[] nums3 = { 30, 20, 150, 100, 40 };
        int[] nums4 = { 60, 60, 60 };
        System.out.println(solve(nums1));
        System.out.println(solve(nums2));
        System.out.println(solve(nums3));
        System.out.println(solve(nums4));
    }

    private static int solve(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for(int n : nums) {
            n = n % 60;
            int key = 60 - n == 60 ? 0 : 60 - n;
            if(map.containsKey(key))
                res += map.get(key);
            map.put(n, map.getOrDefault(n, 0) + 1);
        }
        return res;
    }
}
