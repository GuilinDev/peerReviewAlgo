package main.java.ningSL;

import java.util.*;

public class MaxSwap {
    public static void main(String[] args) {
        int num = 2736;
        System.out.println(maximumSwap(num));
        TreeMap<Integer, Integer> tm = new TreeMap<>();
        ArrayList res = new ArrayList<>();
        res.addAll(tm.entrySet());
    }

     /*   public static int maximumSwap(int num) {
            char[] digits = Integer.toString(num).toCharArray();
            int[] buckets = new int[10];
            for (int i = 0; i < digits.length; i++) {
                buckets[digits[i] - '0'] = i;
            }

            for (int i = 0; i < digits.length; i++) {
                for (int k = 9; k > digits[i] - '0'; k--) {
                    if (buckets[k] > i) {
                        char tmp = digits[i];
                        digits[i] = digits[buckets[k]];
                        digits[buckets[k]] = tmp;
                        return Integer.valueOf(new String(digits));
                    }
                }
            }

            return num;
        }*/


        public static int maximumSwap(int num) {
            char[] s1 = Integer.toString(num).toCharArray();
            char[] s2 = Integer.toString(num).toCharArray();
            PriorityQueue<Character> pq = new PriorityQueue<>((a, b) -> b - a);
            for (char c : s2){
                pq.offer(c);
            }
            char swap = 'a';
            int index = -1;
            for (int i = 0; i < s1.length; i ++){
                char val = pq.poll();
                if(s1[i] != val){
                    swap = val;
                    index = i;
                    break;
                }

            }

            if(swap!= 'a' && index != -1){
                for (int i = s1.length - 1; i >= 0; i --){
                    if(s1[i] == swap){
                        char temp = s1[index];
                        s1[index] = s1[i];
                        s1[i] = temp;

                    }
                }
                return Integer.valueOf(new String(s1));
            }

            return Integer.valueOf(new String(s1));
        }



}
