package algorithm.sort

import util.swap

/**
 * 插入排序
 * 时间复杂度O(N^2)
 */
class InsertionSort {
    fun sort(array: IntArray?){
        if (array == null || array.size <= 2){
            return
        }
        for (i in 1 until array.size){
            //每次找到小就和前一位替换，每次最小的拍到最前面
            for (j in i - 1 downTo 0){
                if (array[j + 1 ] < array[j]){
                    swap(array,j,j + 1)
                }else{
                    break
                }
            }
        }
    }

}
fun testInsertionSort(){
    val arrayOf = intArrayOf(9, 2, 5, 1, 6, 3, 6)
    InsertionSort().sort(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}