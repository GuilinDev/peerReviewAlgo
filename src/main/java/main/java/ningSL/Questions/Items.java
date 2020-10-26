package main.java.ningSL.Questions;

public class Items {
    public static void main(String[] args) {
        String s = "|**|*|*";
        int[] startindices = new int[]{1,1};
        int[] endindices = new int[]{5,6};
        int[] res = numberOfItems(s,startindices,endindices);
        System.out.println(res[0] +","+ res[1]);
    }
   public static int[] numberOfItems(String s, int[] startindices, int[] endindices){
        int l = s.length(), n = startindices.length;
        int[] res = new int[n];
        int[] left = new int[l]; int[] right = new int[l]; int[] star = new int[l];
        int pre_left = -1, post_right = -1, count = 0;
        for(int i = 0; i < l; i++){
            if(s.charAt(i) == '|') pre_left = i;
            left[i] = pre_left;
        }
        for(int i = l - 1; i >= 0; i--){
            if(s.charAt(i) == '|') post_right = i;
            right[i] = post_right;
        }
        for(int i = 0; i < l; i++){
            if(s.charAt(i) == '*') count++;
            star[i] = count;
        }
        for(int i = 0; i < n; i++) {
            int start = startindices[i] - 1, end = endindices[i] - 1;
            int a = right[start], b = left[end];
            if(a >=b ) res[i] = 0;
            else res[i] = star[b] - star[a];
        }
        return res;
    }
}
