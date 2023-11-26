package leetcode

/**
 *
 */
//BFS 广度优先算法
class Solution_315 {
    private lateinit var Result: IntArray
    fun countSmaller(nums: IntArray): List<Int> {
        Result = IntArray(nums.size)
        process(nums,0,nums.size - 1 )
        return Result.toList()
    }

    private fun process(nums: IntArray, L: Int, R: Int) {
        if (L == R) {
            return
        }
        val mid = L + ((R - L) shr 1)
        process(nums, mid + 1, R)
        process(nums, L, mid)
        marge(nums, L, mid, R)
    }

    private fun marge(nums: IntArray, L: Int, mid: Int, R: Int) {
        val help = IntArray(R - L + 1)
        var p1 = L
        var p2 = R
        var i = 0
        while (p2 >= mid && p1 >= 0){
            if (nums[p2] > nums[p1]){
                help[i] = nums[p2] //大的放在左侧
            }else{
                help[i] = nums[p1]
            }
            i++

        }
        while (p2 >= mid){

        }

    }
}

fun test315() {
    val arrayOf = intArrayOf(9, 2, 5, 1, 6, 3, 6)
    Solution_315().countSmaller(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}