package leetcode

class Solution_367 {
    fun isPerfectSquare(num: Int): Boolean {
        var tmp = 0L
        var L = 0L
        var R = num.toLong()
        while (L <= R){
            tmp = ((L + R) / 2)
            if (tmp * tmp < num){
                L = tmp + 1
            }else{
                R = tmp - 1
            }
        }
        return (L * L).toInt() == num
    }
}

fun main() {
    println(Solution_367().isPerfectSquare(2))
}