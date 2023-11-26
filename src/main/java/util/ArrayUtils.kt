package util

fun swap(array: IntArray, minIndex: Int, swapIndex: Int) {
    val temp = array[minIndex]
    array[minIndex] = array[swapIndex]
    array[swapIndex] = temp
}