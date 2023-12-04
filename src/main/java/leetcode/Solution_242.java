package leetcode;
public class Solution_242 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int len = 'z' - 'a' + 1;
        int[] record = new int[len];
        int size = s.length();
        for (int i = 0; i < size; i++) {
            record[s.charAt(i) - 'a']++;
            record[t.charAt(i) - 'a']--;
        }
        for (int i = 0; i < len; i++) {
            if (record[i] != 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        Solution_242 test = new Solution_242();
        System.out.println(test.isAnagram("anagram", "nagaram"));
        System.out.println(test.isAnagram("rat", "car"));
    }
}