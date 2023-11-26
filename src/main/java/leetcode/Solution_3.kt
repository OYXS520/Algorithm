package leetcode

/**
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
class Solution_3 {
    fun lengthOfLongestSubstring(s: String): Int {
        if (s.length == 0) {
            return 0
        }
        if (s.length == 1) {
            return 1
        }
        val chs = s.toCharArray()
        var right = 0
        var left = 1
        var max = 0
        while (left < s.length) {
            for (index in right until left) {
                if (chs[index] == chs[left]) { //新入的元素发现和旧的相同
                    max = Math.max(left - right, max)
                    right = index + 1 //右边界移动到旧的相同元素的下一个
                    break
                }
            }
            left++ //左指针每次都扩大，直到整个数组遍历完成
        }
        max = Math.max(left - right, max) //所有节点算完再计算依次最大
        return max
    }
}
fun test3(){
    val testStr = "pwwkew"
    println(Solution_3().lengthOfLongestSubstring(testStr))
}
fun main() {
    test3()
}