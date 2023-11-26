package study


//给定两个有符号32位整数a和b，不能使用算术运算符，分别实现a和b的加、减、乘、除运算
//【要求】
//如果给定a、b执行加减乘除的运算结果就会导致数据的溢出，那么你实现的函数不必对此
//负责，除此之外请保证计算过程不发生溢出
class ArithmeticOperation {
    //加法思路：1.取异或得各位数相加信息；2.取与得各位数进位信息；
    //二者再重复上述1和2，发现不产生进位信息时，异或结果即为答案
    //如果溢出，用户活该
    fun add(a: Int, b: Int): Int {
        var a = a
        var b = b
        var sum = a
        while (b != 0) {
            sum = a xor b
            b = a and b shl 1
            a = sum
        }
        return sum
    }

    //相反数
    fun negNum(n: Int): Int {
        return add(n.inv(), 1)
    }

    //减法思路：
    //a加上b的相反数，即a+( ~b+1)
    fun minus(a: Int, b: Int): Int {
        return add(a, negNum(b))
    }

    //乘法思路：
    //类比乘法计算思路：根据其中一个数的每一位1的位置对另一个数字进行左移位并累加
    fun multi(a: Int, b: Int): Int {
        var a = a
        var b = b
        var res = 0
        while (b != 0) {
            if (b and 1 != 0) {
                res = add(res, a)
            }
            a = a shl 1
            b = b ushr 1
        }
        return res
    }

    fun isNeg(n: Int): Boolean {
        return n < 0
    }

    //除法思路：
    //1.被除数左移至极限小于除数的位置
    //2.然后除数减去这个数
    //被除数重置
    //将得到的数重复上述流程至得到的数小于被除数为止
    //以下方法考虑了负数相除的情况
    fun div(a: Int, b: Int): Int {
        var x = if (isNeg(a)) negNum(a) else a
        val y = if (isNeg(b)) negNum(b) else b
        var res = 0
        var i = 31
        while (i > -1) {
            if (x shr i >= y) {
                res = res or (1 shl i)
                x = minus(x, y shl i)
            }
            i = minus(i, 1)
        }
        return if (isNeg(a) xor isNeg(b)) negNum(res) else res
    }

    fun divide(a: Int, b: Int): Int {
        if (b == 0) {
            throw RuntimeException("divisor is 0")
        }
        return if (a == Int.MIN_VALUE && b == Int.MIN_VALUE) {
            1
        } else if (b == Int.MIN_VALUE) {
            0
        } else if (a == Int.MIN_VALUE) {
            val res = div(add(a, 1), b)
            add(res, div(minus(a, multi(res, b)), b))
        } else {
            div(a, b)
        }
    }
    fun main(args: Array<String>) {
        var a = (Math.random() * 100000).toInt() - 50000
        var b = (Math.random() * 100000).toInt() - 50000
        println("a = $a, b = $b")
        println(add(a, b))
        println(a + b)
        println("=========")
        println(minus(a, b))
        println(a - b)
        println("=========")
        println(multi(a, b))
        println(a * b)
        println("=========")
        println(divide(a, b))
        println(a / b)
        println("=========")
        a = Int.MIN_VALUE
        b = 32
        println(divide(a, b))
        println(a / b)
    }
}

