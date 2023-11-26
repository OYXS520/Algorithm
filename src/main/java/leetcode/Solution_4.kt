package leetcode

import kotlin.math.ceil

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
class Solution_4 {

    fun findMedianSortedArrays(nums1: IntArray, nums2: IntArray): Double {
        val isDouble = (nums1.size + nums2.size) % 2 == 0
        val target = (nums1.size + nums2.size) / 2 + 1
        var min = nums1[0]
        var minNext = nums1[0]
        var p1 = 0
        var p2 = 0
        while (p1 < nums1.size || p2 < nums2.size) {
            var num1 = if (p1 < nums1.size) {
                nums1[p1]
            } else {
                Int.MAX_VALUE
            }
            var num2 = if (p2 < nums2.size) {
                nums2[p2]
            } else {
                Int.MAX_VALUE
            }

            minNext = min
            min = Math.min(num1,num2)
            if (num1 < num2) {

                p1++
            } else {

                p2++
            }
            if (p1 + p2 == target) {
                break
            }
        }
        return if (isDouble) {
            ((min + minNext) / 2).toDouble()
        } else {
            minNext.toDouble()
        }
    }
}

fun test4() {
    println(Solution_4().findMedianSortedArrays(intArrayOf(1, 3, 5, 7, 9), intArrayOf(2)))
}

fun main() {
    test4()
}