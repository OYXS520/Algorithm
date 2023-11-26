package leetcode

class Solution_5 {
    fun longestPalindrome(s: String): String {
        val charArray = CharArray(s.length * 2 + 1) { index ->
            if (index % 2 != 0) {
                s.get(index / 2)
            } else {
                '#'
            }
        }
        return maxLcpsLength(charArray)
    }

    private fun maxLcpsLength(charArray: CharArray): String {
        val arr = IntArray(charArray.size) //保存每一个元素的最大回文半径
        var R = -1 //当前的回文半径最右边的位置
        var C = -1 //当前最大回文半径时的中心位置
        var max = Int.MIN_VALUE //最大回文半径
        var maxIndex = 0
        for (index in charArray.indices) {
            arr[index] = if (R > index) Math.min(arr[2 * C - index], R - index) else 1
            while (index + arr[index] < charArray.size && index - arr[index] > -1) {
                if (charArray[index + arr[index]] == charArray[index - arr[index]]) {
                    arr[index]++
                } else {
                    break
                }
            }
            if (index + arr[index] > R) {
                R = index + arr[index]
                C = index
            }
            if (max < arr[index]){
                maxIndex = index
                max = arr[index]
            }
        }
        return String(charArray)
            .substring((maxIndex - arr[maxIndex] + 1) until (maxIndex + arr[maxIndex]))
            .replace("#","")
    }
}

fun test5() {
    Solution_5()
}

fun main() {
    val test = "babad"
    println(Solution_5().longestPalindrome(test))
}