package main.java.ningSL.Questions;

import java.util.Stack;

public class Calculator {
    public static void main(String[] args) {
        String s =
                "3+2*2";
        System.out.println(calculate(s));
    }

        public static int calculate(String s) {
            Stack<Integer> stack = new Stack<Integer>();
            char sign = '+';
            int num = 0;
            for (int i = 0; i < s.length(); i ++) {
                char c = s.charAt(i);
                if (Character.isDigit(c)){
                    num = num* 10 + (c - '0');
                }
                if(!Character.isDigit(c) && c != ' ' ){
                    if (sign=='+'){
                        stack.push(num);
                    }
                    if (sign == '-') stack.push(-num);
                    if (sign == '*') stack.push(stack.pop() * num);
                    if (sign == '/') stack.push(stack.pop() / num);
                    sign = c;
                    num = 0;
                }
            }
            int res = 0;
            for (int n: stack){
                res += n;
            }
            return res;
        }



}
