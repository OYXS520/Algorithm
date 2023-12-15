package leetcode;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

class Solution_239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int L = 0;
        int R = 0;
        //窗口范围[L,R]
        int size = nums.length;
        //小根堆 大的在后面
        LinkedList<Integer> queue = new LinkedList<>();
        int[] res = new int[Math.max(size - k + 1, 1)]; //存储最大值
        for (R = 0; R < size; R++) {
            if (R - L >= k) {
                res[L] = queue.getLast(); //计算上次的最大值
                if (queue.getLast() == nums[L]) {
                    queue.pollLast();
                }
                L++;
            }
            while (!queue.isEmpty() && queue.getFirst() < nums[R]) {
                queue.pollFirst();
            }
            queue.addFirst(nums[R]);
        }
        res[res.length - 1] = queue.getLast();
        return res;
    }

    public static void main(String[] args) {
        Solution_239 test = new Solution_239();
        int[] ints = {7157, 9172, 7262, -9146, 3087, 5117, 4046, 7726, -1071,
                6011, 5444, -48, -1385, -7328, 3255, 1600, 586, -5160, -371, -5978,
                9837, 3255, -6137, 8587, -3403, 9775, 260, 6016, 9797, 3371, 2395,
                6851, 2349, -7019, 9318, 1211, -3110, 8735, -7507, 1784, 7400, -5799
                , 3169, -7696, -8991, -2222, -9434, -4490, 4034, -831, -9656,
                5488, -4395, 9339, 4104, -9058, -4072, -1172, 1758, 6878, -5570,
                -6380, 9550, -9389, 1411, 2298, 3516, 551, 9196, 5215, -237, -4146,
                1682, 4418, -4639, 7759, 9593, -9588, 3041, 9208, -7331, -797, -2529,
                7738, -2944, 4351, 5091, -9448, -5404, 6200, -1425, -3983, 678, 8456,
                -8085, 5162, 7165, 4692, -494, -9249, 8514, 521, -8835, 6745, -5775,
                -575, 1876, -5464, 5053, 5567, 3456, 5873, 1965, 4316, 2126, 9462,
                -59, 6544, -1547, 7015, -8928, -3903, -3020, 5865, -9479, 6723,
                9214, 5705, 5136, 7725, 945, -1995, -2288, 4579, 7103, 9938,
                4495, -730, -3180, 7717, 6824, 794, -894, -1439, -1641, -4577,
                9362, -8817, -6035, -7980, -1278, -1928, -5390, -2342, 1189,
                -2340, 4788, -1814, 5927, 3115, 9017, 6801, 7884, -5719, 5992, 7477, -486,
                -2734, -1557, 3169, 5288, -8295, -5651, 2491, -3394, 8302,
                -8822, 5638, 7654, 7350, 9884, -5392, 881, -4874, 5582, 8309, -8514,};
        int[] ints1 = {-7, -8, 7, 5, 7, 1, 6, 0};
        System.out.println(Arrays.toString(test.maxSlidingWindow(ints, 45)));
    }
}