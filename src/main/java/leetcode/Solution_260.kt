package leetcode

/**
 * https://leetcode.cn/problems/single-number-iii/
 * 给你一个整数数组 nums，其中恰好有两个元素只出现一次，其余所有元素均出现两次。 找出只出现一次的那两个元素。你可以按 任意顺序 返回答案。
 * 你必须设计并实现线性时间复杂度的算法且仅使用常量额外空间来解决此问题。
 */
class Solution_260 {
    fun singleNumber(nums: IntArray): IntArray {
        var eor = 0
        for (num in nums) {
            eor = eor xor num
        }
        //eor == a ^ b
        //eor != 0
        //eor 必然有一个位置上是1
        val rightOne = eor  and ( eor.inv() + 1) //提取最右的1
        var onlyOne = 0
        for (cur in nums) {
            if ((cur and rightOne) == 1){
                onlyOne = onlyOne xor cur
            }
        }
        return intArrayOf(onlyOne,eor xor onlyOne)
    }
}
fun testSolution_260(){
    val arrayOf:IntArray = intArrayOf(0,11111,3,3,4,4,2,2,5,5)
    for (i in Solution_260().singleNumber(arrayOf)) {
        println(i)
    }
}