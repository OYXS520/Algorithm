package study

//位运算的题目
//给定两个有符号32位整数a和b，返回a和b中较大的。
//【要求】
//不用做任何比较判断。
class MaxNumber {
    //取反
    fun flip(n: Int): Int {
        return n xor 1
    }

    //n是非负数，返回1
    //n是负数，返回0
    fun sign(n: Int): Int {
        return flip(n shr 31 and 1)
    }

    fun getMax1(a: Int, b: Int): Int {
        //注意此处可能会溢出
        val c = a - b //取差
        val scA = sign(c) //a-b为非负数，scA为1；a-b为负数，scA为0
        val scB = flip(scA) //scA为0，scB为1；scA为1，scB为0
        // scA为0，scB为1；scA为1，scB必为0
        //此时scA和scB其中一个为0，一个为1
        return a * scA + b * scB
    }

    //解决getMax1的可能存在的溢出问题
    fun getMax2(a: Int, b: Int): Int {
        val c = a - b
        //分别记录abc的状态
        val sa = sign(a)
        val sb = sign(b)
        val sc = sign(c)
        //记录sa和sb的异或，如果sa和sb不一样，则为1,；一样，则为0
        val difSab = sa xor sb
        //sa和sb的符号一样，为1；不一样，为0
        val sameSab = flip(difSab)

        //注意此时a和b如果符号相同，则a-b不会溢出
        //返回a的情况：reaturnA=1,returnB=0
        // 			1)a和b相同符号且a-b>=0;此时difSab=0，sameSab=1,则sc也必须为1即c必须a>b
        // 			2)a和b符号不相同并且a>0;此时difSab=1，sameSab=0，则sc无所谓但sa必须为1即a必须>0

        //返回b的情况:returnA=0,returnB=1
        //			1)a和b相同符号a-b<0;此时difSab=0，sameSab=1,sa无所谓，但sc必须为0即必须a<b
        //			2)a和b符号不相同并且a<0;此时difSab=1，sameSab=0，则sc无所谓但a必须<0
        val returnA = difSab * sa + sameSab * sc
        val returnB = flip(returnA)
        return a * returnA + b * returnB
    }

    fun main(args: Array<String>) {
        var a = -16
        var b = 1
        println(getMax1(a, b))
        println(getMax2(a, b))
        a = 2147483647
        b = -2147480000
        println(getMax1(a, b)) // wrong answer because of overflow
        println(getMax2(a, b))
    }
}

