package leetcode

class Solution_19 {
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        if (head == null || n < 0) {
            return head
        }
        var fast = head //快指针
        var slow = head //慢指针
        var counter = 0
        while (counter < n && fast != null) { //快指针先走n步
            fast = fast.next
            counter++
        }
        if (counter != n) {
            return head
        }
        if (counter == n && fast == null){ //要删除的是第一个节点
            return slow.next
        }
        while (fast?.next != null) {
            slow = slow?.next
            fast = fast.next
        }
//        slow.next 就是要删除的元素
        slow?.next = slow?.next?.next
        return head
    }
}

fun main() {
    val test = MyLinkedList()
    test.addAtTail(1)
//    test.addAtTail(2)
//    test.addAtTail(3)
//    test.addAtTail(4)
//    test.addAtTail(5)
    test.sout()
    val swapPairs = Solution_19().removeNthFromEnd(test.head,1)
    sout(swapPairs)
}