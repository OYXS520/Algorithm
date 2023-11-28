package leetcode

class Solution_34 {
    fun searchRange(nums: IntArray, target: Int): IntArray {
        if (nums.isEmpty()) {
            return intArrayOf(-1, -1)
        }
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
//        println("----->$c, $l,${r + 1}") //l会指向第一个target，r 会指向l的前一个
        if (l >= nums.size || l < 0 || nums[l] != target) {
            return intArrayOf(-1, -1) //没有找到结果
        }
        r++
        while (r < nums.size && nums[r] == target) {
            r++
        }
        return intArrayOf(l, r - 1)
    }
}

fun test34() {
    val test = intArrayOf(0,1,3,3,3,4,5,6,7,8)
    println(Solution_34().searchRange(test, 9).contentToString())
}

fun main() {
    test34()
}