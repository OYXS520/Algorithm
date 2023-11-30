package leetcode

class Solution_76 {
    //有优化的空间
    fun minWindow(s: String, t: String): String {
        val pointer = IntArray(128) //需求数组
        var char = ' '
        for (char in t) {
            pointer[char.code]-- //需要的
        }
        var L = 0
        var R = 0
        var start = 0
        val sLen = s.length
        var end = sLen + 1
        while (R < sLen) {
            char = s[R]
            pointer[char.code]++
            var includeAll = true
            while (includeAll) {
                for (char in t) {
                    if (pointer[char.code] < 0) {
                        includeAll = false
                        break
                    }
                }
                if (includeAll){
                    if (R - L < end - start) {
                        start = L
                        end = R
                    }
                    pointer[s[L].code]--
                    L++
                }

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