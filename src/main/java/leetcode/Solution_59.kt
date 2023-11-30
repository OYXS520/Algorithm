package leetcode

class Solution_59 {
    fun generateMatrix(n: Int): Array<IntArray> {
        val result = Array<IntArray>(n) {
            IntArray(n)
        }
        var top = 0
        var bottom = n - 1
        var left = 0
        var right = n - 1
        var step = 1
        var count = n * n
        var x = 0
        var y = 0
        while (step <= count) {
            //从左向右
            for (x in left..right) {
                result[y][x] = step
                step++
            }
            x = right
            top++
            //从上到下
            for (y in top..bottom) {
                result[y][x] = step
                step++
            }
            y = bottom
            right--
            //从右向左
            for (x in right downTo left) {
                result[y][x] = step
                step++
            }
            x = left
            bottom--
            //从下向上
            for (y in bottom downTo top) {
                result[y][x] = step
                step++
            }
            y = top
            left++
        }
        return result
    }
}

fun main() {
    val generateMatrix = Solution_59().generateMatrix(3)
    for (generateMatrix in generateMatrix) {
        println(generateMatrix.contentToString())
    }
}