package leetcode

class Solution_76 {
    //优化后的算法
    fun minWindow(s: String, t: String): String {
        val pointer = IntArray(128) //需求数组
        var char = ' '
        var need = 0
        for (char in t) {
            pointer[char.code]-- //需要的
            need --
        }
        var L = 0
        var R = 0
        var start = 0
        val sLen = s.length
        var end = sLen + 1
        while (R < sLen) {
            char = s[R]
            if (pointer[char.code] < 0){ //找到了一个需要的
                need ++
            }
            pointer[char.code]++
            while (need == 0) {
                if (R - L < end - start) {
                    start = L
                    end = R
                }
                if (pointer[s[L].code] == 0){ //排除的是一个需要的
                    need -- //需要的又要欠下一个了
                }
                pointer[s[L].code]--
                L++
            }
            R++
        }
        return if (end - start == sLen + 1){
            ""
        }else{
            s.substring(start,end + 1)
        }
    }
}

fun main() {
    println(Solution_76().minWindow("ADOBECODEBANC", "ABC"))
    println(Solution_76().minWindow("a", "aa"))
}