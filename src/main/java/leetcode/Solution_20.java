package leetcode;

import java.util.LinkedList;

class Solution_20 {
    public Character pairCharset(char left){
        switch (left){
            case '(':
                return ')';
            case '{':
                return '}';
            case '[':
                return ']';
        }
        return null;
    }
    public boolean isValid(String s) {
        LinkedList<Character> chars = new LinkedList<>();
        int size = s.length();
        for (int i = 0; i < size; i++) {
            char ch = s.charAt(i);
            Character pair = pairCharset(ch);
            if (pair == null){
                if (chars.isEmpty()){
                    return false;
                }
                Character character = chars.pollLast();
                if (!character.equals(ch)){
                    return false;
                }
            }else {
                chars.addLast(pair);
            }
        }
        return chars.isEmpty();
    }

    public static void main(String[] args) {
        Solution_20 test = new Solution_20();
        System.out.println(test.isValid("{()()[([])]"));
    }
}