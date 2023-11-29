package leetcode

import kotlin.math.min

class Solution_209 {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var L = 0
        // [L,R)
        var minLen = Int.MAX_VALUE
        var value = 0
        for (R in nums.indices) {
            value += nums[R]
            while (value >= target) {
                minLen = Math.min(minLen, R - L + 1)
                value -= nums[L]
                L++
            }
        }
        return if (minLen == Int.MAX_VALUE) {
            0
        } else {
            minLen
        }
    }
}

fun main() {
    println(Solution_209().minSubArrayLen(11, intArrayOf(1, 1, 1, 1, 1, 1, 1, 1)))
}