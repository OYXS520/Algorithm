package leetcode


class Solution_15 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        if (nums.size < 3) {
            return listOf()
        }
        nums.sort()
        var R = 0
        var L = 0
        val mutableList = mutableListOf<List<Int>>()
        for (index in nums.indices) {
            if (nums[index] > 0){
                return mutableList
            }
            if (index > 0 && nums[index] == nums[index - 1]){//如果新的数和之前的数相同就跳过
                continue
            }
            R = nums.size - 1
            L = index + 1
            while (L < R){
                if (nums[index] + nums[R] + nums[L] == 0) {
                    mutableList.add(listOf(nums[index],nums[L],nums[R]))
                    while (L < R && nums[R] == nums[R - 1]){ //如果下一个值和当前值相同
                        R--
                    }
                    while (L < R && nums[L] == nums[L + 1]){ //如果下一个值和当前值相同
                        L ++
                    }
                    R-- //移动到下一个不相同的位置
                    L++
                }else if (nums[index] + nums[R] + nums[L] > 0){
                    R--
                }else{
                    L++
                }
            }
        }
        return mutableList
    }
//    fun threeSum(nums: IntArray): List<List<Int>> {
//        val result = arrayListOf<List<Int>>()
//        poccess(result,nums, null, 0)
//        return result
//    }
//
//    fun isZero(e: List<Int>): Boolean {
//        if (e.size != 3) return false
//        var result = 0
//        for (i in e) {
//            result += i
//        }
//        return result == 0
//    }
//
//    private fun poccess(result:MutableList<List<Int>>,nums: IntArray, temp: MutableList<Int>?, index: Int){
//        if (nums.size == index){
//            return
//        }
//        var res = temp
//        if (res == null) {
//            res = mutableListOf()
//        }
//
//        if (isZero(res)) {
//            result.add(res)
//            return
//        }
//
//        poccess(result,nums, mutableListOf(nums[index]).apply { addAll(res) } ,index + 1) //要当前元素
//        poccess(result,nums, res,index + 1) //不要当前元素
//
//    }
}

fun test15() {
    var nums = intArrayOf(-1,0,1,2,-1,-4)
    val threeSum = Solution_15().threeSum(nums)
    for (list in threeSum) {
        for (i in list) {
            print("$i,")
        }
        println()
    }
}

fun main() {
    test15()
}