package leetcode;

class Solution_541 {
    public String reverseStr(String s, int k) {
        int size = s.length();
        char[] sArray = s.toCharArray();
        int L;
        int R;
        char temp;
        for (int index = 0; index < size; index += 2 * k) { //一定需要翻转的
            L = index;
            R = index + k > size ? size - 1 : index + k - 1 ;
            while (L < R) {
                temp = sArray[L];
                sArray[L] = sArray[R];
                sArray[R] = temp;
                L++;
                R--;
            }
        }
        return new String(sArray);
    }

    public static void main(String[] args) {
        Solution_541 test = new Solution_541();
        System.out.println(test.reverseStr("abcd", 4));
    }
}