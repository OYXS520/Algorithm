package algorithm.sort

/**
 * 归并排序
 * 时间复杂度O(N * logN)
 */
class MergeSort {
    fun sort(array: Array<Int>?) {
        if (array == null || array.size <= 2) {
            return
        }
        progress(array, 0, array.size - 1)
    }

    private fun progress(array: Array<Int>, L: Int, R: Int) {
        if (L == R) {
            return
        }
        val mid = L + ((R - L) shr 1) //获取中间值，并且不会越界
        progress(array, L, mid)
        progress(array, mid + 1, R)
        merge(array, L, mid, R)
    }

    private fun merge(array: Array<Int>, L: Int, M: Int, R: Int) {
        val help = IntArray(R - L + 1)
        var i = 0
        var p1 = L
        var p2 = M + 1
        //两个指针分别遍历左右两个已经排好序的数组，并按顺序放到另一个数组中
        while (p1 <= M && p2 <= R) {
            help[i++] = if (array[p1] <= array[p2]) {
                array[p1++]
            } else {
                array[p2++]
            }
        }
        //还有剩余的就直接按序放到最后
        while (p1 <= M) {
            help[i++] = array[p1++]
        }
        while (p2 <= R) {
            help[i++] = array[p2++]
        }
        //把排好序的复制回原数组
        for (index in help.indices) {
            array[L + index] = help[index]
        }

    }
}

fun testMergeSort() {
    val arrayOf = arrayOf(9, 2, 5, 1, 6, 3, 6)
    MergeSort().sort(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}