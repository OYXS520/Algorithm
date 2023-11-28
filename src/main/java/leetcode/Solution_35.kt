package leetcode

class Solution_35 {
    fun searchInsert(nums: IntArray, target: Int): Int {
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
        if (target > nums[c]){
            return c + 1
        }
        return c
    }
}

fun test35() {
    val test = intArrayOf(1,3,6)
    println(Solution_35().searchInsert(test, 4))
}

fun main() {
    test35()
}