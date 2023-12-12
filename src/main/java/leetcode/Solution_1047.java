package leetcode;

import java.util.LinkedList;

class Solution_1047 {
    public String removeDuplicates(String s) {
        int size = s.length();
        LinkedList<Character> stack = new LinkedList<>();
        for (int i = 0; i < size; i++) {
            if (stack.isEmpty()) {
                stack.addLast(s.charAt(i));
            } else {
                Character end = stack.getLast();
                if (end != null && end.equals(s.charAt(i))) {
                    stack.pollLast();
                } else {
                    stack.addLast(s.charAt(i));
                }
            }
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()) {
            res.append(stack.pollFirst());
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution_1047 test = new Solution_1047();
        System.out.println(test.removeDuplicates("abbaca"));
    }
}