package study

import test.RandomArray
import java.util.*

/**
 * 滑动窗口
 */
class SlidingWindow(
    val array: IntArray,
) {
    var windowL: Int = -1 //当前滑动窗口的左指针
    var windowR: Int = 0 //当前滑动窗口的右指针

    //采用双端队列 存放数组的索引
    //维持的是当windowR不再变化时,windowL变化时,谁会依次成为窗口最大值,这个信息信息
    private val queueMax: LinkedList<Int> = LinkedList()

    //维持的是当windowR不再变化时,windowL变化时,谁会依次成为窗口最小值,这个信息信息
    private val queueMin: LinkedList<Int> = LinkedList()

    /**
     * @param increaseL 左指针增加多少
     * @param increaseR 右指针增加多少
     */
    fun generalSlidingWindowMaxValue(increaseL: Int = 0, increaseR: Int = 0) {
        if (increaseL < 0 || increaseR < 0 ||
            increaseR + windowR > array.size ||
            increaseL + windowL >= increaseR + windowR
        ) {
            throw IllegalStateException("窗口异常,左值大于右值")
        }
        for (i in 0 until increaseR) {
            windowRightIncrease()
        }
        for (i in 0 until increaseL) {
            windowLeftIncrease()
        }

    }

    //获取窗口最大值
    fun getWindowMaxValue(): Int {
        return if (queueMax.isEmpty()) -1 else array[queueMax.first]
    }

    //获取窗口最小值
    fun getWindowMinValue(): Int {
        return if (queueMin.isEmpty()) -1 else array[queueMin.first]
    }

    fun windowRightIncrease() {
        //最大值的逻辑
        while (queueMax.isNotEmpty()) {
            if (array[queueMax.last] > array[windowR]) {
                break
            } else {
                queueMax.removeLast() //把没有当前大的从尾直接丢弃
            }
        }
        queueMax.addLast(windowR) //紧贴放在比自己大的后面,保持上一个丢弃了自己是最大的
        //最小值的逻辑
        while (queueMin.isNotEmpty()) {
            if (array[queueMin.last] < array[windowR]) {
                break
            } else {
                queueMin.removeLast()
            }
        }
        queueMin.addLast(windowR)
        windowR++
    }

    fun windowLeftIncrease() {
        if (windowL + 1 >= windowR) {
            throw IllegalStateException("窗口左值大于不可以超过右值")
        }
        windowL++
        //最大值的逻辑
        if (queueMax.isNotEmpty() && windowL == queueMax.first) {
            queueMax.removeFirst()
        }
        //最小值的逻辑
        if (queueMin.isNotEmpty() && windowL == queueMin.first) {
            queueMin.removeFirst()
        }
    }
}

fun testSlidingWindow() {
    val generateRandomArray = intArrayOf(9, -13, 0, 4, -6, 8, 4, 0, 11, -9)
//        RandomArray.generateRandomArray(10, 15)
//    println(generateRandomArray.contentToString())
    val test = SlidingWindow(generateRandomArray)
    test.generalSlidingWindowMaxValue(0, 5)
    println("max: ${test.getWindowMaxValue()},min: ${test.getWindowMinValue()}")
    test.generalSlidingWindowMaxValue(2, 2)
    println("max: ${test.getWindowMaxValue()},min: ${test.getWindowMinValue()}")
    test.generalSlidingWindowMaxValue(4, 0)
    println("max: ${test.getWindowMaxValue()},min: ${test.getWindowMinValue()}")
}

fun testArrayMax() {
    val arrays = intArrayOf(4, 3, 5, 4, 3, 3, 6, 7)
    val windowWidth = 3
    val slidingWindow = SlidingWindow(arrays)
    slidingWindow.generalSlidingWindowMaxValue(0, 3)
    println(slidingWindow.getWindowMaxValue())
    for (i in 0 until arrays.size - windowWidth) {
        slidingWindow.generalSlidingWindowMaxValue(1, 1)
        println(slidingWindow.getWindowMaxValue())
    }
}