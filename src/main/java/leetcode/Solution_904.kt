package leetcode

class Solution_904 {

    fun totalFruit(fruits: IntArray): Int {
        var kind1: Int = -1
        var kind1Size: Int = 0
        var kind2: Int = -1
        var kind2Size: Int = 0
        var L = 0
        var R = 0
        var max = Int.MIN_VALUE
        val size = fruits.size
        while (R < size) {
            var fruit = fruits[R]
            if (kind1Size == 0 || kind1 == fruit) {
                kind1 = fruit
                kind1Size++
                R ++
                continue
            }
            if (kind2Size == 0 || kind2 == fruit) {
                kind2 = fruit
                kind2Size++
                R ++
                continue
            }
            max = Math.max(max, kind1Size + kind2Size)
            while (kind1Size != 0 && kind2Size != 0) {
                fruit = fruits[L]
                if (kind1 == fruit) {
                    kind1Size--
                }
                if (kind2 == fruit) {
                    kind2Size--
                }
                L++
            }

        }
        max = Math.max(max, kind1Size + kind2Size)
        return max
    }
}

fun main() {
    val test1 = intArrayOf(1,2,1)
    val test2 = intArrayOf(0,1,2,2)
    val test3 = intArrayOf(1,2,3,2,2)
    val test4 = intArrayOf(3,3,3,1,2,1,1,2,3,3,4)
    println(Solution_904().totalFruit(test1))
    println(Solution_904().totalFruit(test2))
    println(Solution_904().totalFruit(test3))
    println(Solution_904().totalFruit(test4))
}