/*
234 Palindrome Linked List

Given a singly linked list, determine if it is a palindrome.

Follow up:
 Could you do it in O(n) time and O(1) space?

*/

/* ****
1.	反转前半部，跨过median，比较后半部分。
2.	link的情况， 想个数。
		array, 	len/2 median 或后半部分开始
				(len+1)/2 后半部分开始。
3.  Palindrome， 前后两部分完全匹配， curr2走到底。

411
1.  反转前半部分好, 不用找中间点.
2.  二次扫遍判断时, 用后端curr好,curr2!=null
3.  跨过奇数median
*****/

public class Solution {
    public boolean isPalindrome(ListNode head) {
		ListNode dummy = new ListNode(0), curr = head;
		dummy.next = head;
		int len = 0;
		while(curr != null){
			len++;
			curr = curr.next;
		}
		if(len <= 1) return true;
		
		int half = len/2;
		curr = dummy.next;
		for(int i = 1; i < len/2; i++){// curr在head， 只要换len/2 -1 次
			ListNode tmp = curr.next.next;
			curr.next.next = dummy.next;
			dummy.next = curr.next;
			curr.next = tmp;
		}
		
		ListNode curr2 = len%2 == 0? curr.next: curr.next.next; //奇数要跨过median
		curr = dummy.next;
		while(curr2 != null && curr.val == curr2.val){
			curr = curr.next;
			curr2 = curr2.next;
		}
		
		return curr2 == null;
    }	
}
