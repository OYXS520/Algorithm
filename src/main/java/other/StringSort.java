package other;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StringSort {
    public static void main(String[] args) throws IOException {
        testStringSort();
    }
    public static void testStringSort() throws IOException {
        String[] A = {"cde", "afg", "abc"};
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        for (int i = 0; i < 3; i++) {
            A[i] = bufferedReader.readLine();
        }
        new StringSort().sort(A);
//        for (String s : A) {
//            System.out.println(s);
//        }
    }

    public void sort(String[] strings) {
        for (int bit = 1; bit <= maxLength(strings); bit++) {

            //选择排序的逻辑
            for (int i = 0; i < strings.length; i++) {
                int min = i;
                for (int j = i + 1; j < strings.length; j++) {
                    if (getBitChar(strings[j], bit) < getBitChar(strings[min], bit)) {
                        min = j;
                    } else {
                        break;
                    }
                }
                swap(strings, min, i);
            }

        }
    }

    public void swap(String[] strings, int p1, int p2) {
        String temp = strings[p1];
        strings[p1] = strings[p2];
        strings[p2] = temp;
    }

    public int maxLength(String[] strings) {
        int max = 0;
        for (String string : strings) {
            if (string.length() > max) {
                max = string.length();
            }
        }
        return max;
    }

    /**
     * 获取str倒数第bit个上的字符
     *
     * @param str
     * @param bit 从1 到 字符串长度-1
     * @return
     */
    public char getBitChar(String str, int bit) {
        if (bit > str.length()) {
            return 0;
        }
        return str.charAt(str.length() - bit);
    }
}
