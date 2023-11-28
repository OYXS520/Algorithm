package leetcode

class Solution_69 {
    fun mySqrt(x: Int): Int { //可改二分查找
        val num = x.toLong()
        if (x <= 1){
            return x
        }
        var i = 0L
        var res = 0L
        while (i <= num) {
            res = (i * i).toLong()
            if (res > num) {
                return (i - 1).toInt()
            }
            i++
        }
        return i.toInt()
    }
}
fun test69() {
    println(Solution_69().mySqrt(2147395600))
}

fun main() {
    test69()
}