package algorithm.sort

import util.swap

/**
 * 选择排序
 * 时间复杂度O(N^2)
 */
class SelectionSort {

    fun sort(array: IntArray?){
        if(array == null || array.size <= 2){
            return
        }
        //1,2,3,4
        for(i in array.indices){
            var minIndex = i //保存后续的最小索引
            for (j in (i + 1) until array.size){
                if (array[j] < array[minIndex]){
                    minIndex = j
                }
            }
            swap(array,minIndex,i) //最小索引与当前位置的索引交换位置
        }
    }

}
fun testSelectionSort(){
    val arrayOf = intArrayOf(9, 2, 5, 1, 6, 3, 6)
    SelectionSort().sort(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}