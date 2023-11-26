package algorithm

import util.swap
import java.util.*

/**
 * 堆排序
 * 堆实际就是一个完全二叉树
 */
class HeapSort {
    fun sort(array: IntArray?) {
        if (array == null || array.size <= 2) {
            return
        }
//        for (index in array.indices) {
//            heapInsert(array, index)
//        } //第一种，依次向堆中添加节点，使整体变成大根堆
        for (i in array.indices.reversed()) {
            heapify(array, i, array.size)
        }//第二种，已知所有节点，从子节点变成大根堆的方法
        // 大根堆索引为0的位置一定是整个堆中最大的
        var heapSize = array.size - 1
        swap(array, 0, heapSize) // 索引把索引为0的节点放到未排好序的数组最后面，这样就找到当前数组的的最大值
        while (heapSize > 0) {
            heapify(array, 0, heapSize) //因为交换了节点，所以新堆不满足大根堆的条件，把前面未排好序的数组，进行一次大根堆整理
            swap(array, 0, heapSize - 1) //整理好的堆又是一个大根堆，头节点又是最大的，所以把头节点与未排好序的数组的最后一位交换位置
            heapSize-- //因为最后一位已经确定了，未排序的数组缩减
        }
    }

    /**
     * 从子找父比较替换
     */
    fun heapInsert(array: IntArray, Index: Int) {
        var index = Index
        while (array[index] > array[(index - 1) / 2]) { //如果当前节点大于父节点
            swap(array, index, (index - 1) / 2) //替换当前节点和父节点的位置
            index = (index - 1) / 2 //当前节点替换到新位置后，任然需要和当前父节点进行比较
        }
    }

    /**
     * 从父找子比较替换
     */
    fun heapify(array: IntArray, Index: Int, heapSize: Int) {
        var index = Index
        var left = index * 2 + 1 //获取当前节点的左子节点的下标
        while (left < heapSize) {//下方还有子节点
            //比较两个子节点中，谁的值大，把下标给largest
            var largest = if (left + 1 < heapSize && array[left + 1] > array[left]) {
                left + 1
            } else {
                left
            }
            //父节点和较大的字节点进行比较，把下标给largest
            largest = if (array[largest] > array[index]) {
                largest
            } else {
                index
            }
            if (largest == index) {
                break
            }
            //把当前节点和较大的子节点进行交换
            swap(array, largest, index)
            index = largest
            //获取当前节点的左子节点的索引
            left = index * 2 + 1
        }
    }
}

class LessK {
    fun sort(array: IntArray, k: Int) {
        val heap = PriorityQueue<Int>() //默认小根堆
        var index = 0
        //把前k个添加到小根堆中
        while (index <= Math.min(array.size, k)) {
            heap.add(array[index]) //前面的入堆
            index++
        }
        //前面的小根堆已经排好
        var i = 0
        while (index < array.size) {
            heap.add(array[index]) //把新的元素添加入堆，会自动转为小根堆，堆顶为最小元素
            array[i] = heap.poll() //把堆顶的最小元素出堆，添加到数组最前面，最小的元素已经找出
            i++ //数组索引后移，等待下一个最小元素
            index++ //找下一个元素
        }
        //由于前面先添加了K个元素
        while (heap.isNotEmpty()) {
            array[i] = heap.poll()
            i++
        }

    }
}

fun testHeapSort() {
    val arrayOf = intArrayOf(9, 2, 5, 1, 6, 3, 6)
    HeapSort().sort(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}
