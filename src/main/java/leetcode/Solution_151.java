package leetcode;

class Solution_151 {
    public String reverseWords(String s) {
        s = s.trim(); //去掉首尾空格
        int L = s.length() - 1,R = L;
        StringBuilder res = new StringBuilder();
        while (R >= 0){
            while (R >= 0 && s.charAt(R) != ' ') R --;
            res.append(s.substring(R + 1, L + 1) + " "); // 添加单词
            while (R >= 0 && s.charAt(R) == ' ') R--;     // 跳过单词间空格
            L = R;
        }
        return res.toString().trim();
    }
}