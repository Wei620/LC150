/**
 * Sort a linked list using insertion sort.
 * 
 * Tags: Linkedlist, Sort
 */
class InsertionSortList {
    public static void main(String[] args) {
        
    }
    
    /**
     * Check the list one by one to find a node that has smaller value than 
     * nodes before it and swap
     */
    public static ListNode insertionSortList(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode pre = new ListNode(0);
        pre.next = head;
		
		/*
		 * pre - check the insertion position from the begining.
		 * p - current element
		 * prev - the element before the current one
		 * c - the p may insert after c.
		 */
        
        for (ListNode p = head.next, prev = head; p != null; prev = p, p = p.next) {// prev -> p
            for (ListNode c = pre; c != prev; c = c.next) {// p is right after prev already.       
                if (c.next.val > p.val) { 
                    prev.next = p.next; // skip p
                    p.next = c.next; // insert between cur and cur.next
                    c.next = p;
                    break;
                }
            }
        }
        return pre.next;
    }
    
    public static class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
