package Pratice;

public class ExcelAndNumber {
  /*  A -> 1
    B -> 2
    C -> 3         ...
    Z -> 26
    AA -> 27
    AB -> 28*/
    public int titleToNumber(String s) {
            int r = 0;
            for (char c : s.toCharArray())
                r = r * 26 + (int) (c - 'A' + 1);
            return r;
        }
/*  1 -> A
    2 -> B
    3 -> C
    ...
            26 -> Z
    27 -> AA
    28 -> AB*/
    public String convertToTitle(int n) {
        String ans = "";
        while (n > 0) {
            n --;
            ans = (char)(n % 26 + 'A') + ans;
            n /= 26;
        }
        return ans;
    }

}
