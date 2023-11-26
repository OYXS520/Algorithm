package study

import java.util.*
import kotlin.Comparator

/**
 * 贪心算法
 * 在某一个标准下，优先考虑最满足标准的样本，最后考虑最不满足标准的样本，最终得到一个答案的算法，叫作贪心算法。
 * 也就是说，不从整体最优上加以考虑，所做出的是在某种意义上的局部最优解。
 * 局部最优 -？-> 整体最优
 */
class GreedyAlgorithm {
    //region 案例1
    /**
     * 题目：
     * 一些项目要占用一个会议室宣讲，会议室不能同时容纳两个项目的宣讲。
     * 给你每一个项目开始的时间和结束的时间（给你一个数 组，里面是一个个具体的项目），
     * 你来安排宣讲的日程，要求会议室进行的宣讲的场次最多。
     * 返回这个最多的宣讲场次。
     */
    class Program(
        /**会议开始时间*/
        val start: Int,
        /**会议结束时间*/
        val end: Int
    )

    class ProgramComparator : Comparator<Program> {
        override fun compare(p0: Program, p1: Program): Int {
            return p0.end - p1.end //根据会议结束时间排序，先结束的在前
        }

    }

    /**
     * @param timePoint 会议开始时间
     */
    fun meetingRoomSchedule(programs: Array<Program>, timePoint: Int): Int {
        var tp = timePoint
        Arrays.sort(programs, ProgramComparator())
        var result = 0
        //从左到右依次遍历所有会议
        for (i in programs.indices) {
            if (tp <= programs[i].start) { //找到最近并且耗时最短的会议
                result++
                tp = programs[i].end //下一个会议的时间点为当前会议的结束
            }
        }
        return result
    }
    //endregion

    //region 案例2
    /**
     * 题目，给定一个字符串数组，给这个数组进行排序，后续有数组中的字符串按顺序进行拼接，得到的字典序最小
     * 字典序：a < b   ,ab < ac , ldjalfj > afjldsaj
     */
    class StringCompComparator : Comparator<String> {
        override fun compare(p0: String, p1: String): Int {
            return (p0 + p1).compareTo(p1 + p0)
        }
    }

    fun minimumLexicographicOrder(strs: Array<String>?): String {
        if (strs == null || strs.size == 0) {
            return ""
        }
        Arrays.sort(strs, StringCompComparator())
        var str = ""
        for (index in strs.indices) {
            str += strs[index]
        }
        return str
    }
    //endregion
    //region 案例3
    /**
     * 题目
     * 一块金条切成两半，是需要花费和长度数值一样的铜板的。比如长度为20的金条，不管切成长度多大的两半，都要花费20个铜板。
     * 一群人想整分整块金条，怎么分最省铜板？
     * 例如，给定数组｛10，20.30］，代表一共三个人，整块金条长度为10+20+30=60。
     * 金条要分成10.20.30三个部分。如果先把长度60的金条分成10和50，花费60；
     * 再把长度50的金条分成20和30，花费50；一共花费110铜板。
     * 但是如果先把长度60的金条分成30和30，花费60；再把长度30金条分成10和20，
     * 花费30；一共花费90铜板。
     * 输入一个数组，返回分割的最小代价。
     */
    fun lessMoney(arr: IntArray): Int {
        val pQ = PriorityQueue<Int>() //默认小根堆
        for (i in arr.indices) {
            pQ.add(i) //把所有需求丢到小根堆
        }
        var sum = 0
        var cur = 0
        while (pQ.size > 1) {
            cur = pQ.poll() + pQ.poll() //每次取出最小的两个,组成一个大的
            sum += cur
            pQ.add(cur) //把大的也丢进小根堆中，参与·上一次·的划分
        }
        return sum
    }
    //endregion

    //region 案例四
    /**
     * 输入：
     * 正数数组costs
     * 正数数组profits
     * 正数k
     * 正数m
     * 含义：
     * costs［i］表示i号项目的花费
     * profits［i］表示i号项目在扣除花费之后还能挣到的钱（利润）
     * k表示你只能串行的最多做k个项目
     * m表示你初始的资金
     * 说明：
     * 你每做完一个项目，马上获得的收益，可以支持你去做下一个项目。
     * 输出：
     * 你最后获得的最大钱数。
     */
    class Node(val p: Int = 0, val c: Int = 0)
    class MinCostComparator : Comparator<Node> {
        override fun compare(p0: Node, p1: Node): Int { //按投资从小到达排序
            return p0.c - p1.c
        }
    }

    class MaxProfitComparator : Comparator<Node> {
        override fun compare(p0: Node, p1: Node): Int { //按利润从大到小排序
            return p1.p - p0.p
        }
    }

    fun findMaximizedCapital(k: Int, w: Int, profits: IntArray, capital: IntArray): Int {
        var count = w
        val minCostQ = PriorityQueue<Node>(MinCostComparator())
        val maxProfitQ = PriorityQueue<Node>(MaxProfitComparator())
        for (i in profits.indices) {
            minCostQ.add(Node(profits[i], capital[i]))
        }
        for (i in 0 until k) {
            while (!minCostQ.isEmpty() && minCostQ.peek().c <= w) {
                maxProfitQ.add(minCostQ.poll())
            }
            if (maxProfitQ.isEmpty()) {
                return count
            }
            count += maxProfitQ.poll().p
        }
        return count
    }
    //endregion

    //region 案例五
    /**
     * 一个数据流中，随时可以取得中位数
     */
    //通过定义一个大根堆和一个小根堆，实现一个类似水平仪的结构，当两个堆长度相差大于1时，说明需要调整堆的大小，
    //使得大根堆永远放较小的二分之一个数，小根堆永远放较大的二分之一个数，并且大小堆的堆顶永远存放的是中间的两个数
    //endregion

    //region 案例六
    /**
     * N皇后问题是指在N*N的棋盘上要摆N个皇后，要求任何两个皇后不同行、不同列，也不在同一条斜线上。
     * 给定一个整数n，返回n皇后的摆法有多少种。
     * n=1，返回1。
     * n=2或3，2皇后和3皇后问题无论怎么摆都不行，返回0。
     * n=8，返回92。
     */
    //region 方法1
    fun num1(n: Int): Int {
        if (n < 1) {
            return 0
        }
        val record = IntArray(n) //record[i] -> i行的皇后放在了第几列
        return process1(0, record, n)
    }

    //潜台词：record[0..i-1]的皇后，任何两个皇后一定都不共行、不共列，不共斜线
    //目前米到了第i行
    // record[0..i-1]表示之前的行，放了的皇后位置
    // n代表整体一共有多少行
    //返回值是，摆完所有的皇后，合理的摆法有多少种
    private fun process1(i: Int, record: IntArray, n: Int): Int {
        if (i == n) return 1
        var res = 0
        for (j in 0 until n) {
            // 当前立行的皇后，放在j列，会不会和之前（0..i-1）的皇后，不共行共列或者共斜线，
            // 如果是，认为有效
            // 如果不是，认为无效
            if (isValid(record, i, j)) {
                record[i] = j
                res += process1(i + 1, record, n)
            }
        }
        return res
    }

    // record[0..i-1]你需要看，record[i..]不需要看
    //返回i行皇后，放在了j列，是香有效
    private fun isValid(record: IntArray, i: Int, j: Int): Boolean {
        for (k in 0 until i) {
            if (j == record[k] || Math.abs(record[k] - j) == Math.abs(i - k)) {
                return false
            }
        }
        return true
    }
    //endregion

    //region 方法二
    fun num2(n: Int): Int {
        if (n < 1 || n > 2) {
            return 0
        }
        var limit = if (n == 32) -1 else (1 shl n) - 1
        return process2(limit, 0, 0, 0)
    }

    private fun process2(limit: Int, colLim: Int, leftDiaLim: Int, rightDiaLim: Int): Int {
        if (colLim == limit) {
            return 1
        }
        var pos = 0
        var mostRightOne = 0
        pos = limit and ((colLim or leftDiaLim or rightDiaLim).inv())
        var res = 0
        while (pos != 0) {
            mostRightOne = pos and (pos.inv() + 1)
            pos -= mostRightOne
            res += process2(
                limit, colLim or mostRightOne,
                (leftDiaLim or mostRightOne) shl 1,
                (rightDiaLim or mostRightOne) ushr 1
            )
        }
        return res
    }
    //endregion
    //endregion
}