package main.java.ningSL;

import java.util.Stack;

public class PolishNotation {
    public static void main(String[] args) {
       String[] st = new String[]{"2","1","+","3","*"};
       System.out.println(evalRPN(st)) ;
    }
    public static int evalRPN(String[] tokens) {
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < tokens.length; i ++) {
            if(tokens[i].equals("+")  || tokens[i].equals("-")  || tokens[i].equals("*" )  || tokens[i].equals("/")){
                int num2 = st.pop();
                int num1 = st.pop();
                int cur = getNum(num1, num2, tokens[i]);
                st.push(cur);
            } else {
                st.push(Integer.parseInt(tokens[i]));
            }
        }
        return st.pop();
    }

    public static int getNum(int num1, int num2, String operator){
        switch(operator){
            case "+":
                return num1 + num2;
            case "-":
                return num1 - num2;
            case "*":
                return num1 * num2;
            case "/":
                return num1 / num2;
            default :
                return 0;
        }

    }

}
