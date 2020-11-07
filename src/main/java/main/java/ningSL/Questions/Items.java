package main.java.ningSL.Questions;

import java.util.ArrayList;
import java.util.List;

public class Items {
    public static void main(String[] args) {
        String s = "|**|*|*";
        List<Integer> startindices = new ArrayList<>();
        startindices.add(1);
        startindices.add(1);
        List<Integer> endindices = new ArrayList<>();
        endindices.add(5);
        endindices.add(6);
        List<Integer> res = numberOfItems(s,startindices,endindices);
        System.out.println(res.get(0) +","+ res.get(1));


    }
    public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        int l = s.length(); int n = startIndices.size();
        List<Integer> res = new ArrayList<>();
        int[] left = new int[l]; int[] right = new int[l]; int[] star = new int[l];
        int pre_left = -1; int post_right = -1, count = 0;
        for (int i = 0; i < l; i ++) {
            if(s.charAt(i) == '|') pre_left = i;
            left[i] = pre_left;
        }
        for(int i = l-1; i >=0; i --) {
            if(s.charAt(i) == '|') post_right = i;
            right[i] = post_right;
        }
        for (int i = 0; i < l; i ++) {
            if(s.charAt(i) == '*') count++;
            star[i] = count;
        }
        for(int i = 0; i < n; i ++) {

            int start = startIndices.get(i) - 1; int end = endIndices.get(i) - 1;
            int a = right[start]; int b = left[end];
            if (a >= b) {
                res.add(0);
            } else {
                int k = star[b] - star[a];
                res.add(k);
            }
        }
        return res;

    }

}

