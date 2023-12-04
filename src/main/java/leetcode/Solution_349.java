package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

class Solution_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0){
            return new int[0];
        }
        HashSet<Integer> numSet1 = new HashSet<>();
        HashSet<Integer> list = new HashSet<>();
        for (int num1 : nums1) {
            numSet1.add(num1);
        }
        for (int num2 : nums2) {
            if (numSet1.contains(num2)){
                list.add(num2);
            }
        }
        int[] result = new int[list.size()];
        int i = 0;
        for (Integer integer : list) {
            result[i++] = integer;
        }
        return result;
    }

    public static void main(String[] args) {
        Solution_349 solution_349 = new Solution_349();
        int[] intersection = solution_349.intersection(new int[]{4,9,5}, new int[]{9,4,9,8,4});
        System.out.println(Arrays.toString(intersection));
    }
}