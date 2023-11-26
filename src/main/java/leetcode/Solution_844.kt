package leetcode

import java.util.*

class Solution_844 {
    fun backspaceCompare(s: String, t: String): Boolean {
        val cs = convert(s)
        val ts = convert(t)
        return cs == ts
    }

    fun convert(tchs: String): String {
        var stack = Stack<Char>()
        for (tch in tchs) {
            if (tch != '#') {
                stack.push(tch)
            } else {
                if (stack.isNotEmpty()) {
                    stack.pop()
                }
            }
        }
        return String(stack.toCharArray())
    }

    fun backspaceCompare2(s: String, t: String): Boolean {
        var ps = s.length - 1
        var pt = t.length - 1
        while (ps >= 0 || pt >= 0) {
            ps = getNextChar(ps, s)
            pt = getNextChar(pt, t)
            if (ps >= 0 && pt >= 0) {
                if (s[ps] == t[pt]) {
                    ps--
                    pt--
                } else {
                    return false
                }
            } else if (ps >= 0 && pt < 0) { //下面这两种情况是如果一方已经结束，另一方还有东西，那么一定会把字符删除完
                ps = getNextChar(ps, s)
                if (ps >= 0) {
                    return false
                }
            } else if (pt >= 0 && ps < 0) {
                pt = getNextChar(pt, t)
                if (pt >= 0) {
                    return false
                }
            }
        }
        return ps < 0 && pt < 0
    }

    //返回p之前的下一个有效字符的位置
    fun getNextChar(p: Int, str: String): Int {
        if (p < 0) return p
        if (str[p] != '#') return p
        var count = 1
        var index = p - 1
        while (count > 0 && index >= 0) {
            if (str[index] == '#') {
                count++
            } else {
                count--
            }
            index--
        }
        if (index >= 0 && count == 0 && str[index] == '#') { //如果下一个任然是"#",md给爷再来
            return getNextChar(index, str)
        }
        return index
    }
}

fun test844() {
//    println(Solution_844().backspaceCompare2("bxj##tw", "bxo#j##tw"))
//    println(Solution_844().backspaceCompare2("a#c", "b"))
    println(Solution_844().backspaceCompare2("nzp#o#g", "b#nzp#o#g"))
//    println(Solution_844().getNextChar(6, "bxo#j##tw"))
//    println(Solution_844().getNextChar(3, "c#d#"))
//    println(Solution_844().getNextChar(10, "12345#d####"))
//    println(Solution_844().getNextChar(1, "a#c"))
//    println(Solution_844().getNextChar(5, "######"))
//    println(Solution_844().getNextChar(5, "bxo###tw"))
//    println(Solution_844().getNextChar(4, "bxj##tw"))
}

fun main() {
    test844()
//    println(Solution_844().convert("1#,#121#"))
}
