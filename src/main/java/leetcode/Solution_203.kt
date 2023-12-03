package leetcode


class Solution_203 {
    fun removeElements(head: ListNode?, `val`: Int): ListNode? {
        var head = head
        var pre: ListNode? = null
        var cur: ListNode? = head
        while (cur != null) {
            if (cur.`val` == `val`) {
                if (cur == head) {
                    head = cur.next
                    cur = cur.next //判断下一个
                    continue
                }
                if (pre != null) {
                    pre.next = cur.next
                    cur = cur.next //判断下一额
                }
            } else {
                pre = cur
                cur = cur.next //判断下一个
            }
        }
        return head
    }
}

fun main() {
    val head = ListNode(1)
    head.next = ListNode(2)
    head.next!!.next = ListNode(3)
    head.next!!.next!!.next = ListNode(4)
    head.next!!.next!!.next!!.next = ListNode(5)
    head.next!!.next!!.next!!.next!!.next = ListNode(6)
    val head2 = null
    val head3 = ListNode(7)
    head3.next = ListNode(7)
    head3.next!!.next = ListNode(7)
    head3.next!!.next!!.next = ListNode(7)
    head3.next!!.next!!.next!!.next = ListNode(7)
    head3.next!!.next!!.next!!.next!!.next = ListNode(7)
    var removeElements = Solution_203().removeElements(head3, 7)
    while (removeElements != null){
        println(removeElements.`val`)
        removeElements = removeElements.next
    }
}