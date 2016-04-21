/*
203 Remove Linked List Elements
Remove all elements from a linked list of integers that have value val.

Example
Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
Return: 1 --> 2 --> 3 --> 4 --> 5 

*/

/* ****
411
1. 除prev后面的, 所以只有当后面元素没被删除时,才移动prev.

1. 移除节点,要知道前继. 双指针 prev, curr.
2. 链表加dummy除cornner cases(把head改变了). 但dummy不能移动!
*****/
//411
public class Solution {
    public ListNode removeElements(ListNode head, int val) {
        ListNode dummy = new ListNode(0), prev = dummy;
        dummy.next = head;
        while(prev.next != null){
            if(prev.next.val == val) prev.next = prev.next.next;
            else prev = prev.next;
        }
        return dummy.next;
    }
}

public class Solution {
	public ListNode removeElements(ListNode head, int val) {
		ListNode dummy = new ListNode(0);
		dummy.next = head;
		ListNode curr = head, prev = dummy;
		while (curr != null) {
			if (curr.val == val) {
				prev.next = curr.next;
			} else {
				prev = prev.next;
			}
			curr = curr.next;
		}
		return dummy.next;
	}
}