package algorithm.sort

/**
 * 冒泡排序
 * 时间复杂度O(N^2)
 */
class BubbleSort {
    fun sort(array: Array<Int>?) {
        if (array == null || array.size <= 2) {
            return
        }
        for (i in (array.size - 1) downTo 0) {
            //没一轮就会把i之前的数组中的最大值放在i这个位置，也就是当前的最后面
            for (j in 0 until i) {
                if (array[j] > array[j + 1]) {
                    swap(array, j, j + 1)//把大的交换到后面
                }
            }
        }
    }

    /**
     * 通过异或实现交换
     */
    private fun swap(array: Array<Int>, j: Int, i: Int) {
        array[i] = array[i] xor array[j]
        array[j] = array[i] xor array[j]
        array[i] = array[i] xor array[j]
    }
}

fun testBubbleSort() {
    val arrayOf = arrayOf(9, 2, 5, 1, 6, 3, 6)
    BubbleSort().sort(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}
