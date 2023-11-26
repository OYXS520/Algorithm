package algorithm.sort

import util.swap

/**
 * 快排
 */
class QuickSort {
    fun sort(array: IntArray?) {
        if (array == null || array.size <= 2) {
            return
        }
        quickSort(array, 0, array.size - 1)
    }

    private fun quickSort(array: IntArray, L: Int, R: Int) {
        if (L < R) {
            //快排3.0
            //swap(array, L + (Math.random() * (R - L + 1)).toInt(), R) //随机选择一个位置，充当最后一位，消除最差情况
            val p: IntArray = partition(array, L, R)
            quickSort(array, L, p[0]) // < 区
            quickSort(array, p[1], R) // > 区
        }
    }

    private fun partition(array: IntArray, L: Int, R: Int): IntArray {
        var index = L
        var less = L - 1 //<区右边界
        var more = R // >区左边界
        while (index < more) {
            if (array[index] < array[R]) { //如果当前的值，小于最右边的值
                swap(array, less + 1, index) //把当前值与左边界后一位交换位置
                less++ //扩大左边界
                index++ //指针后移
            } else if (array[index] > array[R]) { //如果当前位置的值大于最右边的值
                swap(array, more - 1, index) //把当前位置的值与右边界前一位交换位置
                more-- //右边界向前移动
                //由于新交换过来的这一位，是由后面交换过来的，未参与比较，所以指针不动
            } else { //当前值等于最右边的值，
                index++ //直接指针后移，比较下一位
            }
        }
        //完成上述逻辑后，所有比最右边值小的都会放在右边界之前，所有比它大的都会放在左边界之后，
        //最后只剩下它自己，所有要把它自己和左边界交换位置，让它和自己相等的数在一起
        swap(array, more, R)
        return intArrayOf(less, more + 1) //返回左右边界的位置
    }


}

fun testQuickSort(){
    val arrayOf = intArrayOf(9, 2, 5, 1, 6, 3, 6)
    QuickSort().sort(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}