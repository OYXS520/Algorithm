package leetcode

class Solution_24 {
    fun swapPairs(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }
        var root = head
        var pre: ListNode? = null
        var cur = head
        while (cur?.next != null) {
            if (pre == null) {
                pre = cur.next
                root = pre
                cur.next = cur.next?.next
                pre?.next = cur
            }else{
                pre.next = cur.next
                cur.next = cur.next?.next
                pre.next?.next = cur
            }
            pre = cur
            cur = cur.next
        }
        return root
    }
}

fun main() {
    val test = MyLinkedList()
    test.addAtTail(1)
    test.addAtTail(2)
    test.addAtTail(3)
//    test.addAtTail(4)
//    test.addAtTail(5)
    test.sout()
    val swapPairs = Solution_24().swapPairs(test.head)
    sout(swapPairs)
}