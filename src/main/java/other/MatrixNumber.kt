package other

class MatrixNumber {
    //从0到Max
    fun out(max: Int) {
        for (row in 0 until Int.MAX_VALUE) { //从0行打印到整数最大值的行
            for (column in 0 until Int.MAX_VALUE) {//从0列打印到整数最大值的列
                val base = column + row  //获取当前元素所在第几层
                // base * (base + 1) / 2 算出当前层之前的层的元素总数
                val value = base * (base + 1) / 2 + column + 1 //加上当前元素是当前层的第几个
                if (value <= max) {  //小于等于max就打印
                    print("$value ")
                } else {
                    if (column == 0) { //如果当前层的第1个元素都大于max，那么整体结束
                        return
                    }
                    println() //每层的换行符
                    break
                }
            }
        }
    }

    fun minDollar(array: IntArray, target: Int) {

    }
}
