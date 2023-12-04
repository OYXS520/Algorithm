package leetcode

class Solution_206 {
    fun reverseList(head: ListNode?): ListNode? {
        if (head?.next == null) {
            return head
        }

        var pre = head
        var cur = head.next
        head.next = null
        var temp: ListNode?
        while (true) {
            temp = cur?.next
            cur?.next = pre
            if (temp == null){
                return cur
            }else{
                pre = cur
                cur = temp
            }
        }
    }
}

fun main() {
    val test = MyLinkedList()
//    test.addAtTail(1)
//    test.addAtTail(2)
//    test.addAtTail(3)
//    test.addAtTail(4)
//    test.addAtTail(5)
    test.sout()
    val solution206 = Solution_206()
    sout(solution206.reverseList(test.head))
}