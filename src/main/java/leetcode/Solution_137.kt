package leetcode

/**
 * https://leetcode.cn/problems/single-number-ii/
 * 给你一个整数数组 nums ，除某个元素仅出现 一次 外，其余每个元素都恰出现 三次 。请你找出并返回那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法且使用常数级空间来解决此问题。
 */
class Solution_137 {
    fun singleNumber(nums: IntArray): Int {
       TODO()
    }
}
fun testSolution_137(){
    val arrayOf:IntArray = intArrayOf(9, 2, 5, 1, 6, 3, 6)
    Solution_136().singleNumber(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}