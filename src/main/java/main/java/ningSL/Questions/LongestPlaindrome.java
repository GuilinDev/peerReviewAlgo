package main.java.ningSL.Questions;

public class LongestPlaindrome {
    public static void main(String[] args) {
        String input = "cbbd";
        longestPalindrome(input);
    }

        static int lo = 0; static int maxLen = 0;
        public static String longestPalindrome(String s) {
            if (s.length() < 2) return s;
            int index = 5;
            int j = 0;
            while(j< s.length()){
                testJ(j);
                j++;
            }
            for (int i = 0; i < s.length(); i ++) {
                extendedPalindrome(s, i , i,index);
                extendedPalindrome(s, i, i + 1,index);
            }
            return s.substring(lo, lo + maxLen);
        }
        public static int testJ(int j ){
            j = j+1;
            return j;
        }
        public static void extendedPalindrome(String s, int l, int r, int index){
            //  int l = left; int r = right;
            index++;
            s = s + "k";
            while (l >= 0 && l < s.length() && r >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                l --;
                r ++;
            }

            if (maxLen < r - l - 1){ //
                lo = l + 1;
                maxLen = r - l - 1;
            }
        }

}
