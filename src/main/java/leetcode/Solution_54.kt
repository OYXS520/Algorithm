package leetcode

class Solution_54 {
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val width = matrix[0].size
        val height = matrix.size

        var count = width * height
        val result = mutableListOf<Int>()
        var top = 0
        var bottom = height - 1
        var left = 0
        var right = width - 1
        var step = 1
        var x = 0
        var y = 0
        while (step <= count) {
            if (left> right || top > bottom)break
            //从左向右
            for (x in left..right) {
                result.add(matrix[y][x])
                step++
            }
            x = right
            top++
            if (left> right || top > bottom)break
            //从上到下
            for (y in top..bottom) {
                result.add(matrix[y][x])
                step++
            }
            y = bottom
            right--
            if (left> right || top > bottom)break
            //从右向左
            for (x in right downTo left) {
                result.add(matrix[y][x])
                step++
            }
            x = left
            bottom--
            if (left> right || top > bottom)break
            //从下向上
            for (y in bottom downTo top) {
                result.add(matrix[y][x])
                step++
            }
            y = top
            left++
        }
        return result
    }
}

fun main() {
    val test1 = arrayOf(
        intArrayOf(1,2,3),
        intArrayOf(8,9,4),
        intArrayOf(7,6,5),
    )
    val test2 = arrayOf(
        intArrayOf(1,2,3,4),
        intArrayOf(5,6,7,8),
        intArrayOf(9,10,11,12),
    )
    println(Solution_54().spiralOrder(test1).toString())
    println(Solution_54().spiralOrder(test2).toString())
}