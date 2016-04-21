/**
 * Given a linked list, remove the nth node from the end of list and return its 
 * head.
 * 
 * For example,
 * 
 * Given linked list: 1->2->3->4->5, and n = 2.
 * 
 * After removing the second node from the end, the linked list becomes 
 * 1->2->3->5.
 * 
 * Note:
 * Given n will always be valid.
 * Try to do this in one pass.
 * 
 * Tags: Linked list, Two pointers
 */
class RemoveNthNodeFromEnd {
    public static void main(String[] args) {
        
    }
	
	public ListNode removeNthFromEnd(ListNode head, int n){
		
	}
    
    /**
     * Dummy head and Runner's technique
     */
	/* ****
	1. 第一次移动 p2和p1差n步， 和p1.next查n-1步. 如果n比总长还要大， p2提前到null. 直接返 head
	2. p2 p1同步，直至p2到最后元素。 p1.next就是要删除的。
	3. 倒数nth 就是 len - n， 单指针数数也很直观。
	*****/
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
		if(n <= 0) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode p1 = dummy;
        ListNode p2 = dummy;
		for(int i = 0; i < n; i++){
			if(p2 == null) return head;
            p2 = p2.next;
        }
        while (p2.next != null) {
            p1 = p1.next;
            p2 = p2.next;
        }
        
        p1.next = p1.next.next;
        return dummy.next;
    }
	
	public ListNode removeNthFromEnd(ListNode head, int n) {
		if(n <= 0) return head;
        ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode curr = dummy.next;
		int len = 0;
		while(curr != null){
			len++;
			curr = curr.next;			
		}
		//find the len - n th的predecessor
		ListNode prev = dummy;
		for(int i = 1; i <= len - n; i++){
			prev = prev.next
		}
		
		prev.next = prev.next.next;
		return dummy.next;
	}
}