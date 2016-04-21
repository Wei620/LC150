/*
206 Reverse Linked List

Reverse a singly linked list.

click to show more hints.

Hint: 
A linked list can be reversed either iteratively or recursively. Could you implement both?

*/

/*****
1. Iterative, 终止条件 curr.next == null.
2. recursive, 把curr后的元素插到prehead(dummy)后.

411. 
1. 要排除 head == null.
*****/

// iterative solution
public ListNode reverseList(ListNode head) {
	ListNode dummy = new ListNode(0);
	dummy.next = head;
	ListNode curr = head;
	if(curr == null) return null;
	
	while(curr.next != null){
		ListNode tmp = curr.next.next;
		curr.next.next = dummy.next;
		dummy.next = curr.next;
		curr = tmp;
	}
	return dummy.next;
}
// recursive solution
public ListNode reverseList(ListNode head) {
	ListNode dummy = new ListNode(0);
	dummy.next = head;
	return reverseListInt(head, dummy);
}
public ListNode reverseListInt(ListNode curr, ListNode prehead) {
	if(curr == null)
		return prehead.next;
	ListNode next = curr.next;
	next.next = prehead.next;
	prehead.next = next;
	return reverseListInt(next, prehead);
}