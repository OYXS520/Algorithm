package leetcode

class Solution_26 {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.size <= 1){
            return 1
        }
        var L = 1
        var R = 1
        while (R < nums.size) {
            if (nums[R - 1] != nums[R]) {
                nums[L] = nums[R]
                L++
            }
            R++
        }
        return L
    }
}

fun main() {
    val test = intArrayOf(0,0,1,1,1,2,2,3,4,5,5,5,6,6,9,)
    println(Solution_26().removeDuplicates(test))
    println(test.contentToString())
}