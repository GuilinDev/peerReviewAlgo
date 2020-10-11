package main.java.ningSL;

public class ForceSearch {
    public static void main(String[] args) {
        System.out.println(forceSearch("abcdefg", "eg"));
    }
    public static int forceSearch(String txt, String pat) {
        int M = txt.length();
        int N = pat.length();
        for (int i = 0; i <= M - N; i++) {
            int j;
            for (j = 0; j < N; j++) {
                if (txt.charAt(i + j) != pat.charAt(j)) break;
            }
            if (j == N) {
                return i;
            }
            }
            return -1;
    }
}
