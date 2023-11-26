package leetcode

class Solution_27 {
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var size = nums.size
        var index = size - 1
        while (index >= 0) {
            if (nums[index] == `val`) {
                var i = index
                while (i < size) { //后面的向前挪
                    if (i == size - 1) {
                        nums[i] = -1 //最后一项没有值可以填，根据需求随便
                    } else {
                        nums[i] = nums[i + 1]
                    }
                    i++
                }
                size--
            }
            index--
        }
        return size
    }
}

fun test() {
    val test = intArrayOf(0, 1, 2, 2, 3, 0, 4, 2)
    println(Solution_27().removeElement(test, 2))
    println(test.contentToString())
}

fun main() {
    test()
}