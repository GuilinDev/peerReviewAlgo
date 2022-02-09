package Pratice;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Validate_Python_Indentation {
    public boolean validate(String[] lines){
        //就用stack来存之前的line就行
        Stack<String> stack = new Stack<>();
        for (String line : lines){
            int level = getIndent(line);
            //先检查是不是第一行
            if (stack.isEmpty()){
                if (level != 0) {
                    System.out.println(line);
                    return false;
                }
            }
            //再检查上一行是不是control statement
            else if (stack.peek().charAt(stack.peek().length()-1) ==':'){
                if (getIndent(stack.peek()) + 1 != level){
                    System.out.println(line);
                    return false;
                }
            }
            else {
                while (!stack.isEmpty() && getIndent(stack.peek()) > level){
                    stack.pop();
                }
                if (getIndent(stack.peek()) != level){
                    System.out.println(line);
                    return false;
                }
            }
            stack.push(line);
        }
        return true;
    }
    //这里如果它说n个空格算一次tab的话，就最后返回的时候res/n好了。
    public int getIndent(String line){
        int res = 0;
        for (int i = 0; i < line.length(); i++){
            if (line.charAt(i) == ' '){
                res++;
            }
            else break;
        }
        return res;
    }
    public static void main(String[] args) {
        Validate_Python_Indentation test = new Validate_Python_Indentation();
        String[] lines = {
                "def:",
                " abc:",
                "  b2c:",
                "   cc",
                " b5c",
                "b6c"
        };
        System.out.println(test.validate(lines));
        //先这样吧，应该行了。
    }


/*============= Following Code Credit to Zhu Siyao ===============*/

    public static boolean valid_python_indentation(List<String> inputs){
        Stack<Integer> stack = new Stack<>();
        for(int i=0;i<inputs.size();i++){
            String str =  inputs.get(i);
            String abbr = getAbbr(str);
            int level = str.length()-abbr.length();

            if(i!=0 && inputs.get(i-1).charAt(inputs.get(i-1).length()-1)==':'){
                if(level<=stack.peek()) return false;
            }else{
                while(!stack.isEmpty() && level<stack.peek()) stack.pop();
                if(!stack.isEmpty() && level!=stack.peek()) return false;

            }
            stack.push(level);
            System.out.println(level);
        }

        return true;

    }

    private static String getAbbr(String str) {
        String result = str.trim();
        return result;
    }



}