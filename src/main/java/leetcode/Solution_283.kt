package leetcode

class Solution_283 {
    fun moveZeroes(nums: IntArray): Unit {
        var L = 0
        var R = 0
        while (R < nums.size) {
            if (nums[R] != 0) {
                val tmp = nums[R]
                nums[R] = nums[L]
                nums[L] = tmp
                L ++
            }
            R ++
        }
    }
}

fun main() {
    val test = intArrayOf(0,1,0,3,12)
    Solution_283().moveZeroes(test)
    println(test.contentToString())
}