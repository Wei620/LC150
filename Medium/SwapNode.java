/**
 * Given a linked list, swap every two adjacent nodes and return its head.
 * 
 * For example,
 * Given 1->2->3->4, you should return the list as 2->1->4->3.
 * 
 * Your algorithm should use only constant space. You may not modify the values
 * in the list, only nodes itself can be changed.
 * 
 * Tags: Linkedlist
 */
class SwapNode {
	
	//418
	public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prev = dummy, curr = head;
		
		while(curr != null && curr.next != null){
			ListNode tmp = curr.next.next;
			curr.next.next = curr;
			prev.next = curr.next;
			curr.next = tmp;
			prev = curr;
			curr = curr.next;
		}
		
        return dummy.next;
    }

    /**
     * create a node at before the head
     * swap two next nodes on the node before them
     */
    public ListNode swapPairs(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;

        while (cur != null && cur.next != null && cur.next.next != null) {
            cur.next = swap(cur.next, cur.next.next);
            cur = cur.next.next;
        }

        return dummy.next;
    }
    
    // next1 and nex2 are adjacent.
    private ListNode swap(ListNode next1, ListNode next2) {
        next1.next = next2.next;
        next2.next = next1;
        return next2; // return latter node 
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
