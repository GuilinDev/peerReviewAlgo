package main.java.ningSL;

public class ConsetiveSum {
    public static void main(String[] args) {
        System.out.println(consecutiveNumbersSum(15));
    }

    public static int consecutiveNumbersSum(int N) {
        int sn=N;
        int x= (int) Math.sqrt(2*sn);
        int ans=0;
        for (int n=x;n>=1;n--){
            if (sn > n*(n-1)/2&&(sn- n*(n-1)/2)%n == 0){
                ans++;
            }
        }
        return ans;
    }
}
