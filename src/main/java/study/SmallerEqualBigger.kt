package study



/**
 * 题目：链表，把小的放在左边，等于的放在中间，大于的放在右边
 */
class SmallerEqualBigger {
    fun listPartition2(Head: Node, pivot: Int): Node? {
        var head: Node? = Head
        var sH: Node? = null //small head
        var sT: Node? = null //small tail
        var eH: Node? = null //equal head
        var eT: Node? = null //equal tail
        var mH: Node? = null //big head
        var mT: Node? = null //big tail
        var next: Node? = null
        while (head != null) {
            next = head.next
            head.next = null
            if (head.value < pivot) {
                if (sH == null) {
                    sH = head
                    sT = head
                } else {
                    sT?.next = head
                    sT = head
                }
            } else if (head.value == pivot) {
                if (eH == null) {
                    eH = head
                    eT = head
                } else {
                    eT?.next = head
                    eT = head
                }
            } else {
                if (mH == null) {
                    mH = head
                    mT = head
                } else {
                    mT?.next = head
                    mT = head
                }
            }
            head = next
        }
        if (sT != null) {
            sT.next = eH
            eT = eT ?: sT
        }
        if (eT != null) {
            eT.next = mH
        }
        return sH ?: (eH ?: mH)
    }
}