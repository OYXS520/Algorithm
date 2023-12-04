package leetcode


class ListNode(var `val`: Int) {
    @JvmField
    var next: ListNode? = null
}

/**
 * https://leetcode.cn/problems/palindrome-linked-list/
 */
class Solution_234 {

    fun isPalindrome(head: ListNode?): Boolean {
        if (head?.next == null) {
            return true
        }
        var slow = head //慢指针
        var fast = head //快指针
        while (slow?.next != null && fast?.next?.next != null) {
            slow = slow.next
            fast = fast.next?.next
        }//慢指针已经指向中间节点的位置，如果链表的长度为偶数，指向中间后面的那个节点
//        使用栈的方式去判断，会有一半
//        val stack = Stack<Int>()
//        while (slow != null){
//            stack.push(slow.`val`)
//            slow = slow.next
//        }
//        var index = head
//        while (stack.isNotEmpty()){
//            if (index?.`val` != stack.pop()){
//                return false
//            }
//            index = index?.next
//        }
//        return true
        fast = slow?.next
        slow?.next = null
        var n3: ListNode? = null
        while (fast != null) {
            n3 = fast.next
            fast.next = slow
            slow = fast
            fast = n3
        } //从中间位置到末尾，链表反转，slow指向最后节点
        n3 = slow //最后节点 此时后半段的节点是反转了的
        fast = head
        var res = true
        while (slow != null && fast != null) {
            if (slow.`val` != fast.`val`) {
                res = false
                break
            }
            slow = slow.next
            fast = fast.next
        }
        //恢复节点
        slow = n3?.next //slow为最后的节点，但是反转后，它的next就是上一个节点
        n3?.next = null
        while (slow != null) {
            fast = slow.next
            slow.next = n3
            n3 = slow
            slow = fast
        }
        return res
    }
}

fun test234() {
    val head = ListNode(1)
    head.next = ListNode(2)
    head.next?.next = ListNode(3)
    head.next?.next?.next = ListNode(2)
    head.next?.next?.next?.next = ListNode(1)
    println(Solution_234().isPalindrome(head))

}