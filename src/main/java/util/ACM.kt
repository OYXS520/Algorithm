package util

import java.util.*

class ACM {
    fun main(args: Array<String>) {
        //1.数据输入
        val `in` = Scanner(System.`in`)
        //读数字
        val numLen: Int = `in`.nextInt()
        val numArr = IntArray(numLen)
        var i = 0
        while (`in`.hasNextInt() && i < numLen) {
            numArr[i] = `in`.nextInt()
            i++
        }
        //读字符串
        val strLen: Int = `in`.nextInt()
        `in`.nextLine() //数字到字符串要换行
        val strArr = arrayOfNulls<String>(strLen)
        //或者 strArr[] = in.nextLine().split(" ");
        var j = 0
        while (`in`.hasNextLine() && j < strLen) {
            strArr[j] = `in`.nextLine()
            j++
        }

        //2. 处理
//        val solution = Solution()
//        val result: String = solution.process(numArr, strArr)
        val result: String = "？？？"

        //3. 输出
        println(result)
        //四舍五入输出小数
        val str = String.format("%.2f", 3.555)
        println(str)
    }
}