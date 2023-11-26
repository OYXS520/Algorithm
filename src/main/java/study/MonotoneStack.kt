package study

import java.util.*

/**
 * 单调栈
 */
class MonotoneStack {
    fun getElementLeftAndRightMaxValue(array: IntArray): Array<Array<Int?>> {
        val stack = Stack<Stack<Int>>() //保存索引，栈底 > 栈顶
        val res = Array<Array<Int?>>(array.size) {
            Array(2) { null }
        }
        for (index in array.indices) {
            A@ while (stack.isNotEmpty()) {
                val vs = stack.peek() //vs中是相同大小的值的索引，新入栈的在栈顶
                while (vs.isNotEmpty()) {
                    val value = array[vs.peek()] //当前层的值
                    if (array[index] > value) { //新入栈的元素大于栈顶元素
                        val pop = stack.pop() //栈顶需要出栈，出栈就会触发结算操作
                        while (pop.isNotEmpty()) {
                            val p = pop.pop()
                            res[p][0] = if (stack.isEmpty()) null else stack.peek().peek() //左边最近的比自己大的值就是栈中下一层的最后一个索引
                            res[p][1] = index
                        }
                    } else if (array[index] == value) { //如果新入栈的值和栈顶层相同
                        vs.push(index) //把新入栈的值添加到当前层的最顶（最新）
                    } else {
                        break@A
                    }
                }
            }
            //当前值是最小的，或者栈是空的，直接把当前值创建一层，添加到栈顶
            val top = Stack<Int>()
            top.push(index)
            stack.push(top)
        }
        while (stack.isNotEmpty()) {
            val vs = stack.pop()
            while (vs.isNotEmpty()) {
                val index = vs.pop()
                res[index][0] = if (stack.isEmpty()) null else stack.peek().peek() //右最大还是上一层的最后一个，如果已经是最后一层,直接设置为null
                res[index][1] = null //左就没有最大，自己就是最大
            }
        }
        return res
    }
    fun getElementLeftAndRightMinValue(array: IntArray): Array<Array<Int?>> {
        val stack = Stack<Stack<Int>>() //保存索引，栈底 < 栈顶
        val res = Array<Array<Int?>>(array.size) {
            Array(2) { null }
        }
        for (index in array.indices) {
            A@ while (stack.isNotEmpty()) {
                val vs = stack.peek() //vs中是相同大小的值的索引，新入栈的在栈顶
                while (vs.isNotEmpty()) {
                    val value = array[vs.peek()] //当前层的值
                    if (array[index] < value) { //新入栈的元素小于栈顶元素
                        val pop = stack.pop() //栈顶需要出栈，出栈就会触发结算操作
                        while (pop.isNotEmpty()) {
                            val p = pop.pop()
                            res[p][0] = if (stack.isEmpty()) null else stack.peek().peek() //左边最近的比自己小的值就是栈中下一层的最后一个索引
                            res[p][1] = index
                        }
                    } else if (array[index] == value) { //如果新入栈的值和栈顶层相同
                        vs.push(index) //把新入栈的值添加到当前层的最顶（最新）
                    } else {
                        break@A
                    }
                }
            }
            //当前值是最小的，或者栈是空的，直接把当前值创建一层，添加到栈顶
            val top = Stack<Int>()
            top.push(index)
            stack.push(top)
        }
        while (stack.isNotEmpty()) {
            val vs = stack.pop()
            while (vs.isNotEmpty()) {
                val index = vs.pop()
                res[index][0] = if (stack.isEmpty()) null else stack.peek().peek() //右最大还是上一层的最后一个，如果已经是最后一层,直接设置为null
                res[index][1] = null //左就没有最大，自己就是最大
            }
        }
        return res
    }
}

fun testMonotoneStack() {
    val array = intArrayOf(5, 4, 3, 6, 1, 2, 0, 7, 5, 2, 4, 3)
    val elementLeftAndRightMaxValue = MonotoneStack().getElementLeftAndRightMinValue(array)
    for (arrayOfInts in elementLeftAndRightMaxValue) {
        println(arrayOfInts.contentToString())
    }
}