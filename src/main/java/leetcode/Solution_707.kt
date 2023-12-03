package leetcode
class Solution_707{

}
class MyLinkedList() {
    var head: ListNode? = null

    //根据节点索引查找值
    fun get(index: Int): Int {
        var node = head
        var count = 0
        while (node != null) {
            if (count == index) {
                return node.`val`
            }
            node = node.next
            count++
        }
        return -1
    }

    //头插
    fun addAtHead(`val`: Int) {
        val newNode = ListNode(`val`)
        newNode.next = head
        head = newNode
    }

    fun addAtTail(`val`: Int) {
        if (head == null) {
            head = ListNode(`val`)
            return
        }
        var node = head
        while (node?.next != null) {
            node = node.next
        }
        node?.next = ListNode(`val`)
    }

    fun addAtIndex(index: Int, `val`: Int) {
        if (head == null || index == 0) {
            if (index == 0){
                addAtHead(`val`)
            }
            return
        }

        var pre = head
        var cur = head!!.next
        var count = 1
        while (cur != null) {
            if (count == index) {
                val node = ListNode(`val`)
                pre!!.next = node
                node.next = cur
                return
            }
            pre = cur
            cur = cur.next
            count++
        }
        if (count == index) {
            pre!!.next = ListNode(`val`)
        }
    }

    fun deleteAtIndex(index: Int) {
        if (index == 0 || head == null) {
            if (head != null) {
                head = head!!.next
            }
            return
        }
        var pre = head
        var cur = head!!.next
        var count = 1
        while (cur != null) {
            if (count == index) {
                pre!!.next = cur.next
                return
            }
            pre = cur
            cur = cur.next
            count++
        }
    }

    fun sout() {
        var node = head
        while (node != null) {
            print("${node.`val`} -> ")
            node = node.next
        }
        println()
    }
}

fun main() {
    val test = MyLinkedList()
//    test.addAtHead(7)
//    test.sout()
//    test.addAtHead(2)
//    test.sout()
//    test.addAtHead(1)
//    test.sout()
//    test.addAtIndex(3, 0)
//    test.sout()
//    test.deleteAtIndex(2)
//    test.sout()
//    test.addAtHead(6)
//    test.sout()
//    test.addAtTail(4)
//    test.sout()
//    test.get(4)
//    test.sout()
//    test.addAtHead(4)
//    test.sout()
//    test.addAtIndex(5, 0)
//    test.sout()
//    test.addAtHead(6)
//    test.sout()

    test.addAtIndex(1, 0)
    test.sout()
    test.get(0)

//    test.addAtIndex(0,10)
//    test.sout()
//    test.addAtIndex(0,20)
//    test.sout()
//    test.addAtIndex(1,30)
//    test.sout()
//    test.get(0)
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * var obj = MyLinkedList()
 * var param_1 = obj.get(index)
 * obj.addAtHead(`val`)
 * obj.addAtTail(`val`)
 * obj.addAtIndex(index,`val`)
 * obj.deleteAtIndex(index)
 */