package study

/**
 * 求解最长回文子串问题
 */
class Manacher {
    //填充子串
    fun manacherString(str: String): CharArray {
        val charArray = str.toCharArray()
        val res = CharArray(str.length * 2 + 1)
        var index = 0
        for (i in res.indices) {
            res[i] = if ((i and 1) == 0) { //i%2 == 0
                '#'
            } else {
                charArray[index++]
            }
        }
        return res
    }

    fun maxLcpsLength(str: String): Int {
        if (str == null || str.isEmpty()) {
            return 0
        }
        val charArr = manacherString(str) //1221 -> #1#2#2#1
        val pArr = IntArray(charArr.size) //回文半径数组
        var R = -1 //回文见右边界再往右一个位#，最右的有效区是R-1位置，注意这个边界是在遍历途中慢慢增加而不是str的长度
        var C = -1 //记录中心
        var max = Int.MIN_VALUE //max记录扩出来的最大值
        for (i in charArr.indices) {//每一个位置求回文半径
            //i至少的回文区域，先给pArr[i]

            //至少不需要检验的区域
            //如果R在i外，则为1
            //否则则位其余两个瓶颈中，更小的那个则为半径
            //2*C-i为i'的位置
            //初始设置为1或
            pArr[i] = if (R > i) Math.min(pArr[2 * C - i], R - i) else 1//这里已经直接得到了R内不需要计算的那部分回文长度
            while (i + pArr[i] < charArr.size && i - pArr[i] > -1) {
                //如果二者值相等，则半径++继续比较
                if (charArr[i + pArr[i]] == charArr[i - pArr[i]]) { //这里就是根据上面计算出的继续计算后续还有没有相同的
                    pArr[i]++ //有就半径继续增加
                } else {
                    break
                }
            }
            //如果i+半径突破右边界，那么C和R做相应的改变
            if (i + pArr[i] > R) {
                R = i + pArr[i]
                C = i
            }
            max = Math.max(max, pArr[i])
        }
        return max - 1
    }
}