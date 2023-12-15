package leetcode;

import java.util.LinkedList;

class Solution_150 {
    interface Calculate{
        int calculate(int arg1,int arg2);
    }
    public Calculate isCharset(String token){
        if(token.length() != 1){
            return null;
        }
        char charset = token.charAt(0);
        switch (charset) {
            case '+':
                return new Calculate() {
                    @Override
                    public int calculate(int arg1, int arg2) {
                        return arg1 + arg2;
                    }
                };
            case '-':
                return new Calculate() {
                    @Override
                    public int calculate(int arg1, int arg2) {
                        return arg1 - arg2;
                    }
                };
            case '*':
                return new Calculate() {
                    @Override
                    public int calculate(int arg1, int arg2) {
                        return arg1 * arg2;
                    }
                };
            case '/':
                return new Calculate() {
                    @Override
                    public int calculate(int arg1, int arg2) {
                        return arg1 / arg2;
                    }
                };
        }
        return null;
    }
    int convert(String token){
        return Integer.parseInt(token);
    }
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (String token : tokens) {
            Calculate charset = isCharset(token);
            if (charset != null){
                if (stack.isEmpty()){
                    throw new IllegalStateException("参数错误");
                }
                Integer arg1 = stack.pollLast();
                Integer arg2 = stack.pollLast();
                int calculate = charset.calculate(arg2, arg1);
                stack.addLast(calculate);
            }else {
                stack.addLast(convert(token));
            }
        }
        return stack.getFirst();
    }

    public static void main(String[] args) {
        Solution_150 test = new Solution_150();
        System.out.println(test.evalRPN(new String[]{"4","13","5","/","+"}));
    }
}