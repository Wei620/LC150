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
        ListNode dummy = new ListNode(0);
        dummy.next = head;
		
		/*
		 * dummy - check the insertion position from the begining.
		 * p - current element
		 * pre - the element before the current one
		 * c - the p may insert after c.
		 */
        
        for (ListNode p = pre = head, head.next; p != null; pre = p, p = p.next) {// pre -> p
            for (ListNode c = dummy; c != pre; c = c.next) {//c!=pre because p is right after pre already.       
                if (c.next.val > p.val) {  // ascending sort
                    pre.next = p.next; // skip p
                    p.next = c.next; // insert right after c
                    c.next = p;
                    break;
                }
            }
        }
        return dummy.next;
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
