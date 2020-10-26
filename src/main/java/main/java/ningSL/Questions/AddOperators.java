package main.java.ningSL.Questions;

import java.util.ArrayList;
import java.util.List;

public class AddOperators {
    public static void main(String[] args) {
        String num = "123";
        int target = 6;
        System.out.println(addOperators(num,target));
    }

    public static List<String> addOperators(String num, int target) {
        List<String> rst = new ArrayList<String>();
        if(num == null || num.length() == 0) return rst;
        helper(rst, "", num, target, 0, 0, 0);
        return rst;
    }
    public static void helper(List<String> rst, String path, String num, int target, int level, long eval, long multed){
        if(level == num.length()){
            if(target == eval)
                rst.add(path);
            return;
        }
        for(int i = level; i < num.length(); i++){
            if(i != level && num.charAt(level) == '0') break;
            long cur = Long.parseLong(num.substring(level, i + 1));
            if(level == 0){
                helper(rst, path + cur, num, target, i + 1, cur, cur);
            }
            else{
                helper(rst, path + "+" + cur, num, target, i + 1, eval + cur , cur);
                helper(rst, path + "-" + cur, num, target, i + 1, eval -cur, -cur);
                helper(rst, path + "*" + cur, num, target, i + 1, eval - multed + multed * cur, multed * cur );
            }
        }
    }
}
