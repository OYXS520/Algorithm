package leetcode

class Solution_704 {
    fun search(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1
        var c = 0
        while (l <= r) {
            c = (l + r) shr 1
            if (nums[c] > target) {
                r = c - 1
            } else if (nums[c] < target) {
                l = c + 1
            } else {
                return c
            }
        }
        return -1
    }

    fun search2(nums: IntArray, target: Int): Int {
        var l = 0
        var r = nums.size - 1
        var c = 0
        while (l <= r) {
            c = (l + r) / 2
            if (nums[c] < target) {
                l = c + 1
            } else {
                r = c - 1
            }
        }
        return l //l 和 r + 1 都指向的是同一个值
    }
}

fun test704() {
    println(Solution_704().search2(intArrayOf(5), 5))
}

fun main() {
    test704()
}