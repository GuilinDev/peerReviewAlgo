import java.util.*;

public class Test {
    public static void main(String[] args) {
        System.out.println("hello");
        String input = "12-8/2";
        System.out.println(calculate(input));
        HashMap<String, Integer> map = new HashMap<>();
        map.put("abc", 1);
        map.put("def", 2);
        

    }

    public static int calculate(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int num = 0;
        int len = s.length();
        Stack<Integer> stack = new Stack<>();//存符号两边的数字
        char sign = '+';

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                //   num = Integer.valueOf(ch);
                num = num * 10 + (ch - '0'); //n有可能是上一轮的数字 多位数的多个数字
            }

            if ((!Character.isDigit(ch) && //不是数字，是符号
                    ch != ' ') || //不是空格
                    i == len - 1) { //最后一个位置虽然是数字但位置特殊也要计算

                // 加法和减法是直接push，加正数和负数的区别
                if (sign == '+') {
                    stack.push(num);
                }
                if (sign == '-') {
                    stack.push(-num);
                }
                //乘法和除法是先计算两旁的数再push
                if (sign == '*') {
                    stack.push(stack.pop() * num); //stack中最顶上的数字是当前符号的前一个数字
                }
                if (sign == '/') {
                    stack.push(stack.pop() / num);
                }
                sign = ch; //更新当前sign的符号，下一次循环根据sign的值处理该sign两边的数字
                num = 0;
            }
        }

        int result = 0;
        //循环做完后，所有乘法和除法也做完并push了，目前stack里面只剩加法和减法符号
        for (int ele : stack) { //这样遍历是先进先出（顺序不影响）
            result += ele;
        }

        return result;
    }

}
