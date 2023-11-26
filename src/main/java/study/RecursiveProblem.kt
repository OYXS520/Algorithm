package study

//经典递归问题

//region 汉诺塔问题
//
//down：减少的
object Hanoi {
    fun hanoi(n: Int) {
        if (n > 0) {
            func(n, n, "left", "mid", "right")
        }
    }

    // 1~i 圆盘 目标是from -> to,other是另外一个
    fun func(rest: Int, down: Int, from: String, help: String, to: String) {
        //如果剩余只有1块底，就把这一块移动到to
        if (rest == 1) {
            println("move $down from $from to $to")
        } else {
            //将rest-1从from移动到help
            func(rest - 1, down - 1, from, to, help)
            //将最底下一块从from移动到to
            func(1, down, from, help, to)
            //将rest-1从help移动到to
            func(rest - 1, down - 1, help, from, to)
        }
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val n = 4
        hanoi(n)
    }
}
//endregion


//region 打印一个字符串的全部子序列，包括空字符串
object PrintAllSubsquences {
    fun printAllSubsquence(str: String) {
        val chs = str.toCharArray()
        process(chs, 0)
    }

    //当前来到i位置
    fun process(chs: CharArray, i: Int) {
        //如果i已经抵达终止位置，直接打印值
        if (i == chs.size) {
            println(String(chs))
            return
        }
        //新增下一个字符进入考虑范围，并把之前的字符串也考虑进去
        process(chs, i + 1)
        val tmp = chs[i]
        chs[i] = 0.toChar()
        //不要当前字符的路
        process(chs, i + 1)
        chs[i] = tmp
    }

    fun function(str: String) {
        val chs = str.toCharArray()
        process(chs, 0, ArrayList())
    }

    //当前来到i位置，要和不要，走两条路
    //之前的选择，所形成的列表
    fun process(chs: CharArray, i: Int, res: List<Char?>?) {
        if (i == chs.size) {
            printList(res)
        }
        val resKeep = copyList(res)
        resKeep!!.add(chs[i])
        process(chs, i + 1, resKeep)
        val resNoInclude: List<Char?>? = copyList(res)
        process(chs, i + 1, resNoInclude)
    }

    fun printList(res: List<Char?>?) {
        // ...;
    }

    fun copyList(list: List<Char?>?): MutableList<Char?>? {
        return null
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val test = "abc"
        printAllSubsquence(test)
    }
}
//endregion


//region 打印一个字符串的全部排列，要求不要出现重复的排列
object PrintAllPermutations {
    fun Permutation(str: String?): ArrayList<String> {
        val res: ArrayList<String> = ArrayList()
        if (str == null || str.length == 0) {
            return res
        }
        //将字符串转换为数组
        val chs = str.toCharArray()
        process(chs, 0, res)
        res.sort()
        return res
    }

    //res为记录结果的List
    //str[i..]范围上，所有的字符，都可以在i位置上，后续都去尝试
    //str[0..i-1]范围上，是之前做的选择
    //请把所有的字符串形成的全排列，加入到res里去
    fun process(chs: CharArray, i: Int, res: ArrayList<String>) {
        //如果i等于chs的最后一位，把chs添加入res
        if (i == chs.size) {
            res.add(String(chs))
        }
        val visit = BooleanArray(26)
        //遍历i之后j的数组
        for (j in i until chs.size) {
            //如果chs[j]不为a，意思是如果已经尝试过a进行位置置换，则下次遇到就不尝试了
            //其实不加这段判断同样可以获得结果，但是结果需要去重
            if (!visit[chs[j] - 'a']) {
                visit[chs[j] - 'a'] = true
                //交换字符串实现后续process，随后交换回来
                swap(chs, i, j)
                process(chs, i + 1, res)
                swap(chs, i, j)
            }
        }
    }

    //交换chs[i]和chs[j]的位置
    fun swap(chs: CharArray, i: Int, j: Int) {
        val tmp = chs[i]
        chs[i] = chs[j]
        chs[j] = tmp
    }
}
//endregion


//region 数字转字符串问题
// 规定1和A对应、2和B对应、3和C对应... 那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
//给定一个只有数字字符组成的字符串str，返回有多少种转化结果。

//规定1和A对应、2和B对应、3和C对应... 那么一个数字字符串比如"111"，就可以转化为"AAA"、"KA"和"AK"。
//给定一个只有数字字符组成的字符串str，返回有多少种转化结果。
//特殊情况1：如果存在0的位数，0是不能单独转换的，只有10和20的情况
//特殊情况2：如果存在某位3-9的范围中，那么也单独转换，因为不存在大于26的组合位数
object ConvertToLetterString {
    fun number(str: String?): Int {
        return if (str == null || str.length == 0) {
            0
        } else process(str.toCharArray(), 0)
    }

    fun process(chs: CharArray, i: Int): Int {
        //如果i已经抵达最后位置，那么仅返回一种有效结果
        if (i == chs.size) {
            return 1
        }
        //如果存在0，由于之前的分配方法存在错误，所以返回0
        if (chs[i] == '0') {
            return 0
        }
        //如果某位上为1，res从i+1上持续递归
        if (chs[i] == '1') {
            var res = process(chs, i + 1)
            //如果i+1没有越界，res尝试并累加i+2上的数据
            if (i + 1 < chs.size) {
                res += process(chs, i + 2)
            }
            return res
        }
        //如果某位上为2，res从i+1上持续递归
        if (chs[i] == '2') {
            var res = process(chs, i + 1)
            //但仅有在i+1没有越界时，
            if (i + 1 < chs.size && chs[i + 1] >= '0' && chs[i + 1] <= '6') {
                res += process(chs, i + 2)
            }
            return res
        }
        return process(chs, i + 1)
    }

    @JvmStatic
    fun main(args: Array<String>) {
        println(number("11111"))
    }
}
//endregion

//region 最大价值问题
//给定两个长度都为N的数组weights和values，weights[i]和values[i]分别代表
//i号物品的重量和价值。给定一个正数bag，表示一个载重bag的袋子，你装的物
//品不能超过这个重量。返回你能装下最多的价值是多少？
object Knapsack {
    fun maxValue1(weights: IntArray, values: IntArray, bag: Int): Int {
        return process1(weights, values, 0, 0, bag)
    }

    //i...的货物自由选择，形成最大的价值返回
    fun process1(weights: IntArray, values: IntArray, i: Int, alreadyweight: Int, bag: Int): Int {
        //当已装的重量超过bag，即超重了，则返回0
        if (alreadyweight > bag) {
            return 0
        }
        //当i已经超过重量表的长度，意味着都装不下
        return if (i == weights.size) {
            0
        } else Math.max( //递归判断i+1的情况
            process1(weights, values, i + 1, alreadyweight, bag),  //递归判断i商品的价值放入后，i+1商品的递归的情况
            values[i] + process1(weights, values, i + 1, alreadyweight + weights[i], bag)
        )
    }

    fun maxValue2(c: IntArray, p: IntArray, bag: Int): Int {
        val dp = Array(c.size + 1) { IntArray(bag + 1) }
        for (i in c.indices.reversed()) {
            for (j in bag downTo 0) {
                dp[i][j] = dp[i + 1][j]
                if (j + c[i] <= bag) {
                    dp[i][j] = Math.max(dp[i][j], p[i] + dp[i + 1][j + c[i]])
                }
            }
        }
        return dp[0][0]
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val weights = intArrayOf(3, 2, 4, 7)
        val values = intArrayOf(5, 6, 3, 19)
        val bag = 11
        println(maxValue1(weights, values, bag))
        println(maxValue2(weights, values, bag))
    }
}
//endregion


//region 卡牌游戏
//给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸
//牌，规定玩家A先拿，玩家B后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A
//和玩家B都绝顶聪明。请返回最后获胜者的分数。
//【举例】
//arr=［1， 2， 100， 4］。
//开始时，玩家A只能拿走1或4。如果开始时玩家A拿走1，则排列变为［2，100，4］，接下来
//玩家 B可以拿走2或4，然后继续轮到玩家A。。
//如果开始时玩家A拿走4，则排列变为［1，2，100］，接下来玩家B可以拿走1或100，然后继
//续轮到玩家A。。
//L
//玩家A作为绝顶聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。所以玩家A会先拿1，
//让排列变为［2，100，4］，接下来玩家B不管怎么选，100都会被玩家 A拿走。玩家A会获胜，
//分数为101。所以返回101。
//arr=［1， 100，2］。
//开始时，玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会获胜，
//分数为100。所以返回100。
object CardsInLine {
    fun win1(arr: IntArray?): Int {
        return if (arr == null || arr.size == 0) {
            0
        } else Math.max(f(arr, 0, arr.size - 1), s(arr, 0, arr.size - 1))
    }

    //i代表左边一位，j代表右边一位
    fun f(arr: IntArray, i: Int, j: Int): Int {
        return if (i == j) {
            arr[i]
        } else Math.max(arr[i] + s(arr, i + 1, j), arr[j] + s(arr, i, j - 1))
        //返回    (i的位数的值 + i的后一位到第j位的最小值) 和 (j的位数的值 + i位到j的前一位的最小值) 的最大值
    }

    //i代表左边一位，j代表右边一位
    fun s(arr: IntArray, i: Int, j: Int): Int {
        return if (i == j) {
            0
        } else Math.min(f(arr, i + 1, j), f(arr, i, j - 1))
        //返回	(i的后一位到第j位的最大值) 和 (i位到j的前一位的最大值) 的最小值
    }

    fun win2(arr: IntArray?): Int {
        if (arr == null || arr.size == 0) {
            return 0
        }
        val f = Array(arr.size) { IntArray(arr.size) }
        val s = Array(arr.size) { IntArray(arr.size) }
        for (j in arr.indices) {
            f[j][j] = arr[j]
            for (i in j - 1 downTo 0) {
                f[i][j] = Math.max(arr[i] + s[i + 1][j], arr[j] + s[i][j - 1])
                s[i][j] = Math.min(f[i + 1][j], f[i][j - 1])
            }
        }
        return Math.max(f[0][arr.size - 1], s[0][arr.size - 1])
    }

    @JvmStatic
    fun main(args: Array<String>) {
        val arr = intArrayOf(1, 9, 1)
        println(win1(arr))
        println(win2(arr))
    }
}
//endregion