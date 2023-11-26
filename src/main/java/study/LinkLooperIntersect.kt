package study

import java.io.File

/**
【题目】给定两个可能有环也可能无环的单链表，头节点head1和head2。请实
现一个函数，如果两个链表相交，请返回相交的 第一个节点。如果不相交，返
回null
【要求】如果两个链表长度之和为N，时间复杂度请达到O(N)，额外空间复杂度
请达到0(1)。
 */
class LinkLooperIntersect {
    /**
     * 获取两个单链表的相交节点
     */
    fun getIntersectNode(head1: Node?, head2: Node?): Node? {
        if (head1 == null || head2 == null) {
            return null
        }
        //链表如果是环状链表，返回入环节点
        val loop1 = getLoopNode(head1)
        val loop2 = getLoopNode(head2)
        if (loop1 != null && loop2 == null) {
            return noLoop(head1, head2)
        }
        //不存在两个链表一个有环一个无环，但是相交的情况
        //所以直接讨论两个都有环且相交的情况
        if (loop1 != null && loop2 != null) {
            return bothLoop(head1, loop1, head2, loop2)
        }
        return null
    }
    /**
     * 找到链表第一个入环节点，如果五环，返回null
     */
    fun getLoopNode(head: Node?): Node? {
        if (head?.next?.next == null) {
            return null
        }
        var slow = head.next //慢指针 走一步
        var fast = head.next?.next //快指针 走两步
        //使循环条件成立
        while (slow != fast) {
            if (fast?.next?.next == null) { //如果遇到null节点，那么一定无环
                return null
            }
            fast = fast.next?.next //快指针走两步
            slow = slow?.next //慢指针走一步
        } //当快指针进入循环后，慢指针才进入，慢指针进入后，快指针顶多再跑一圈就能追上慢指针，此时快慢指针指向同一个节点
        fast = head //n2 -> walk again from head   让快指针回到头节点
        while (slow != fast) {  //快慢指针一起走，最终一定会在入环节节点相遇
            slow = slow?.next
            fast = fast?.next
        }
        return slow
    }

    /**
     * 两个无环链表相交，只能是Y型结构的的情况，不存在X型
     * Y型，那么两个链表最后一定是共用的。
     * 如果两个链表都无环，返回第一个相交节点，如果不相交，返回null
     */
    fun noLoop(head1: Node?, head2: Node?): Node? {
        if (head1 == null || head2 == null) {
            return null
        }
        var cur1 = head1
        var cur2 = head2
        var n = 0
        //统计链表1的长度
        while (cur1?.next != null) {
            n++
            cur1 = cur1.next
        }
        //减去链表2的长度
        while (cur2?.next != null) {
            n--
            cur2 = cur2.next
        }
        //如果链表1和链表2的最后一个节点不是同一个，那么两个链表一定不相交
        if (cur1 != cur2) {
            return null
        }
        cur1 = if (n > 0) head1 else head2 //选出长的那个链表
        cur2 = if (cur1 == head1) head2 else head1 //选出短的那个链表
        n = Math.abs(n)
        //让长的链表先移动n步，与短的链表对齐
        while (n != 0) {
            n--
            cur1 = cur1?.next
        }
        //让对齐后的两个链表一起走，走到的第一个相同的节点，就是Y字的路口节点
        while (cur1 != cur2) {
            cur1 = cur1?.next
            cur2 = cur2?.next
        }
        return cur1
    }

    /**
     * 两个有环链表，返回第一个相交节点，如果不相交，返回null
     * <img width="640" height="310" src="/Users/shu/Downloads/IMG_A7544AE079A3-1.jpeg" alt="" />
     */
    fun bothLoop(head1: Node, loop1: Node, head2: Node, loop2: Node): Node? {
        File("")
        var cur1: Node? = null
        var cur2: Node? = null
        if (loop1 == loop2) {
            cur1 = head1
            cur2 = head2
            var n = 0
            while (cur1 != loop1) {
                n++
                cur1 = cur1?.next
            }
            while (cur2 != loop2) {
                n--
                cur2 = cur2?.next
            }
            cur1 = if (n > 0) head1 else head2 //选出长的那个链表
            cur2 = if (cur1 == head1) head2 else head1 //选出短的那个链表
            n = Math.abs(n)
            //让长的链表先移动n步，与短的链表对齐
            while (n != 0) {
                n--
                cur1 = cur1?.next
            }
            //让对齐后的两个链表一起走，走到的第一个相同的节点，就是Y字的路口节点
            while (cur1 != cur2) {
                cur1 = cur1?.next
                cur2 = cur2?.next
            }
            return cur1
        } else {

            cur1 = loop1.next
            //两个节点在环上分布有交点，返回任意一个交点都行
            while (cur1 != loop1) {
                if (cur1 == loop2) {
                    return loop1
                }
                cur1 = cur1?.next
            }
            return null
        }
    }


}