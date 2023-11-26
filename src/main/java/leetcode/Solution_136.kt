package leetcode

/**
 * https://leetcode.cn/problems/single-number/description/
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 */
class Solution_136 {
    fun singleNumber(nums: IntArray): Int {
        var target = 0
         for (num in nums) {
             target = target xor num
        }
        return target
    }
}
fun testSolution_136(){
    val arrayOf:IntArray = intArrayOf(9, 2, 5, 1, 6, 3, 6)
    Solution_136().singleNumber(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}