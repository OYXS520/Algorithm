package algorithm.sort

import kotlin.math.pow

/**
 * 基数排序
 */
class RadixSort {
    fun sort(array: IntArray?) {
        if (array == null || array.size <= 2) {
            return
        }
        radixSort(array, 0, array.size - 1,maxBits(array))
    }

    /**
     * 获取数组中，最大的那个数的位数
     */
    fun maxBits(array: IntArray): Int {
        var max = Int.MAX_VALUE
        for (i in array.indices) {
            max = Math.max(max, array[i])
        } //找到数组中最大的那个数
        var res = 0
        //求最大的数所占的位数
        while (max != 0) {
            res++
            max /= 10
        }
        return res
    }

    /**
     * array[L,R]排序从
     * @param digit 表示最大的数，所在的位数
     */
    fun radixSort(array: IntArray, L: Int, R: Int, digit: Int) {
        val radix = 10 //前缀和数组大小
        var i = 0
        var j = 0
        val bucket = IntArray(R - L + 1) //根据原数组开启一个同样空间的数组，用于存储排序过程和排序结果
        for (d in 1..digit) { //从个位向高位开始排序
            val count = IntArray(radix) //开启一个前缀和数组，保存当前索引位置，比自己小的元素的个数
            i = L
            // 词频数组，例如[3,2,1,5]表示记录了3个0，2个1，1个2，5个3
            // 获取每个元素的当前位上的数出现的次数，也就是统计当前位的词频
            while (i <= R) {
                j = getDigit(array[i], d)
                count[j]++
                i++
            }
            i = 1
            // 前缀数组，例如把词频数组[3,2,1,5]转换为前缀数组为[3,5,6,11]，
            // 表示：小于等于0的数有3个，小于等于1的数有5个，小于等于2的数有6个，小于等于3的数有11个
            // 把词频数组，转换为前缀数组
            while (i < radix) {
                count[i] = count[i] + count[i - 1]
                i++
            }
            i = R
            //从后面向前
            //这里就通过前缀数组和位，完成了数组中根据当前位的一次入队和出队的操作
            while (i >= L) {
                j = getDigit(array[i], d) //获取当前元素的当前位上的值
                //根据当前位上的值，和前缀数组可以知道该元素保存的位置
                bucket[count[j] - 1] = array[i] //例如132,个位上是2，而前缀数组中比较小的数有6个（包含当前这个数），那么132就应该放在第五个位置
                count[j]--
                i--
            }
            i = L
            j = 0
            while (i <= R) {
                array[i] = bucket[j]
                i++
                j++
            }
        }
    }

    /**
     * 获取一个数某一位上的数
     * getDigit(1241,1)=1
     * getDigit(1241,2)=4
     * getDigit(1241,3)=2
     * getDigit(1241,4)=1
     */
    private fun getDigit(x: Int, d: Int): Int {
        return ((x / 10.0.pow((d - 1).toDouble()).toInt())) % 10
    }
}
fun testRadixSort() {
    val arrayOf = intArrayOf(9, 2, 5, 1, 6, 3, 6)
    RadixSort().sort(arrayOf)
    for (i in arrayOf) {
        println(i)
    }
}
