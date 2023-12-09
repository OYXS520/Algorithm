package leetcode;

class Solution_28 {
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null || needle.length() > haystack.length()) {
            return -1;
        }
        int[] needleArr = getNextArr(needle.toCharArray());
        int L = 0;
        int R = 0;
        while (L < needle.length() && R < haystack.length()) {
            if (haystack.charAt(R) == needle.charAt(L)) {
                L++;
                R++;
            } else if (L == 0) {
                R++;
            } else {
                L = needleArr[L];
            }
        }
        return L == needle.length() ? R - L : -1;
    }

    private int[] getNextArr(char[] needle) {
        if (needle == null || needle.length == 0) {
            return null;
        }
        int size = needle.length;
        int[] arr = new int[size];
        arr[0] = -1;
        if (size == 1) {
            return arr;
        }
        arr[1] = 0;
        int index = 2;
        int cn = 0;
        while (index < size) {
            if (needle[index - 1] == needle[cn]) {
                arr[index] = cn + 1;
                cn++;
                index++;
            } else if (cn > 0) {
                cn = arr[cn];
            } else {
                arr[index] = 0;
                index++;
            }
        }
        return arr;
    }

    public static void main(String[] args) {
        Solution_28 test = new Solution_28();
        System.out.println(test.strStr("aldfasdfasdf", "asdfas"));
    }

}