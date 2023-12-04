package leetcode;

/**
 * 面试题 02.07. 链表相交
 * https://leetcode.cn/problems/intersection-of-two-linked-lists-lcci/
 */
public class Solution02_07 {
    public ListNode getIntersectionNode1(ListNode headA, ListNode headB) {
        while (headA != headB) {
            headA = headA != null ? headA.next : headB;
            headB = headB != null ? headB.next : headA;
        }
        return headA;
    }

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        int lenA = 0;
        int lenB = 0;
        ListNode fast = headA;
        ListNode slow = headB;
        while (fast != null || slow != null) {
            if (fast != null) {
                lenA++;
                fast = fast.next;
            }
            if (slow != null) {
                lenB++;
                slow = slow.next;
            }
        }
        int diff = Math.abs(lenA - lenB);
        if (lenA > lenB) {
            fast = headA;
            slow = headB;
        } else {
            fast = headB;
            slow = headA;
        }
        for (lenA = 0; lenA < diff && fast != null; lenA++) {
            fast = fast.next;
        }
        while (fast != slow && fast != null && slow != null) {
            fast = fast.next;
            slow = slow.next;
        }
        if (fast == null || slow == null) {
            return null;
        } else {
            return slow;
        }
    }


    public static void main(String[] args) {

    }
}