/**
 * Given a list, rotate the list to the right by k places, where k is
 * non-negative.
 * 
 * For example:
 * Given 1->2->3->4->5->NULL and k = 2,
 * return 4->5->1->2->3->NULL.
 * 
 * Tags: Linked List, Two Pointers
 */
class RotateList {
    public static void main(String[] args) {
        int n = -4;
        System.out.println(n % 5);
    }
    
    /**
     * Two pointers
     * Move tail pointer to the end of the list to get length
     * Move preHead pointer to len - n % len indexed(1-based) node as the predecessor of the break point (preHead.next)
     * Connect tail with head, update new head
     * Set preHead.next to null to unlink the list
     */
    public static ListNode rotateRight(ListNode head, int n) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode tail = dummy, preHead = dummy;
        // get length and move tail to the end of list
        int len;
        for (len = 0; tail.next != null; len++) tail = tail.next; // from dummy, so tail.next != null.
        // get the len-n%len th node, dummy is node(0)
        for (int j = 0; J < len - n % len; j++) preHead = preHead.next;
        tail.next = dummy.next; 
        dummy.next = preHead.next; // preHead.next will be returned.
        preHead.next = null; // break linkedlist
        return dummy.next;
    }
    
    static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
    
    /**
     * My own implementation
     * Calculate length of list, then adjust n in range
     * Break the list at right point, which is len - n % len
     */
    public static ListNode rotateRightB(ListNode head, int n) {
        if (head == null || head.next == null) return head;
        int len = listLength(head);
        n %= len;
        if (n == 0) return head;
        if (n < 0) n += len;
        n = len - n;
        ListNode p = new ListNode(0);
        p.next = head;
        while (n > 0) {
            p = p.next;
            n--;
        }
        ListNode newHead = p.next;
        p.next = null;
        ListNode cur = newHead;
        while (cur.next != null) cur = cur.next;
        cur.next = head;
        return newHead;
    }
    
    int listLength(ListNode head) {
        ListNode cur = head;
        int res = 0;
        while (cur != null) {
            cur = cur.next;
            res++;
        }
        return res;
    }
}