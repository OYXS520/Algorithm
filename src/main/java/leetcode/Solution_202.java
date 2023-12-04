package leetcode;

class Solution_202 {
    public int getNextNumber(int num){
        int res = 0;
        while (num > 0) {
            int temp = num % 10;
            res += temp * temp;
            num = num / 10;
        }
        return res;
    }
    public boolean isHappy(int n) {
        int fast = n;
        int slow = n;
        do {
            fast = getNextNumber(fast);
            fast = getNextNumber(fast);
            slow = getNextNumber(slow);
        }while (fast != slow);
        return slow == 1;
    }
}