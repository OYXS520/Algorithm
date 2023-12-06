package leetcode;

import java.util.Arrays;

class Solution_344 {
    public void reverseString(char[] s) {
        if (s == null || s.length <=1){
            return;
        }
        int L = 0;
        int R = s.length - 1;
        char temp = ' ';
        while (L < R) {
            temp = s[L];
            s[L] = s[R];
            s[R] = temp;
            L++;
            R--;
        }
    }

    public static void main(String[] args) {
        Solution_344 test = new Solution_344();
        char[] example = "shuouyang".toCharArray();
        test.reverseString(example);
        System.out.println(Arrays.toString(example));
    }
}