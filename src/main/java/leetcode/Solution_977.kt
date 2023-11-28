package leetcode

class Solution_977 {
    fun sortedSquares(nums: IntArray): IntArray {
        var L = 0
        var R = nums.size - 1
        val res = IntArray(nums.size)
        var index = R
        while (L <= R) {
            val RV = nums[R] * nums[R]
            val LV = nums[L] * nums[L]
            if (RV > LV) {
                res[index] = RV
                R--
            } else {
                res[index] = LV
                L ++
            }
            index--
        }
        return res
    }

}

fun main() {
    println(Solution_977().sortedSquares(intArrayOf(-6,-5,-4,-1)).contentToString())
}