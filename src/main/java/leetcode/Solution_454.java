package leetcode;

import java.util.HashMap;
import java.util.HashSet;

class Solution_454 {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        int len = nums1.length;
        HashMap<Integer,Integer> map = new HashMap<>(len);
        int res = 0;
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int sum = nums1[i] + nums2[j];
                map.put(sum,map.getOrDefault(sum,0) + 1);
            }
        }
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                int sum = nums3[i] + nums4[j];
                res += map.getOrDefault(-sum,0);
            }
        }
        return res;
    }
}