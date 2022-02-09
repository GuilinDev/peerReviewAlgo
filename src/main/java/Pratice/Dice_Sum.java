package Pratice;

public class Dice_Sum {
    int count = 0;
    public double getPossibility(int dice, int target){
        if (dice <= 0 || target < dice || target > 6 * dice) {
            return 0.0;
        }
        int total = (int) Math.pow(6, dice);
        helper(dice, target);

        System.out.println(count);
        System.out.println(total);
        return (double)count/total;
    }

    public void helper(int dice, int cur){
        if (dice == 0 && cur == 0){
            count++;
            return;
        }
        if (dice <= 0 || cur <= 0){
            return;
        }
        for (int i = 1; i <= 6; i++){
            helper(dice-1, cur-i);
        }
        return;
    }
    public double getMemPossibility(int dice, int target){
        if (dice <= 0 || target < dice || target > 6*dice) {
            return 0.0;
        }
        int total = (int) Math.pow(6, dice); //这里注意一下，pow的返回类型是double
        int[][] memo = new int[dice+1][target+1];
        int count = dfsMemo(dice, target, memo);
        return (double)count/total;
    }
    public int dfsMemo(int dice, int target, int[][] memo) {
        int res = 0;
        //三个终止条件，能加速就加速吧。
        if (dice == 0 && target == 0) return 1;
        if (target > dice * 6 || target < dice) {
            return 0;
        }
        if (memo[dice][target] != 0) {
            return memo[dice][target];
        }

        for (int i = 1; i <= 6; i++) {
            res += dfsMemo(dice - 1, target - i, memo);
        }
        //这一步是更新记忆矩阵
        memo[dice][target] = res;
        return res;
    }

    public static void main(String[] args) {
        Dice_Sum test = new Dice_Sum();
        int dice = 10;
        int target = 20;
        double res1 = test.getPossibility(dice, target);
        double res2 = test.getMemPossibility(dice,target);
        System.out.print(res1);
        System.out.print(res2);
    }
}
