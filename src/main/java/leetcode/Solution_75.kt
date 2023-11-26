package leetcode


class Solution_75 {

    fun sortColors(nums: IntArray) {
        val whiteValue = 1
        var redBorder = -1
        var blueBorder = nums.size
        var i = 0
        while (i < blueBorder){
            if (nums[i] < whiteValue){
                val temp = nums[i]
                nums[i] = nums[redBorder + 1]
                nums[redBorder + 1] = temp
                redBorder ++
                i ++
            }else if (nums[i] == whiteValue){
                i ++
            }else {
                val temp = nums[i]
                nums[i] = nums[blueBorder - 1]
                nums[blueBorder - 1] = temp
                blueBorder --
            }
        }
    }
}

fun test75() {
    val arrayOf:IntArray = intArrayOf(2,0,2,1,1,0)
    Solution_75().sortColors(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}