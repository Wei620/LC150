/*
239 Sliding Window Maximum

Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very right. You can only see the k numbers in the window. Each time the sliding window moves right by one position.

For example,
 Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
Window position                Max
---------------               -----
[1  3  -1] -3  5  3  6  7       3
 1 [3  -1  -3] 5  3  6  7       3
 1  3 [-1  -3  5] 3  6  7       5
 1  3  -1 [-3  5  3] 6  7       5
 1  3  -1  -3 [5  3  6] 7       6
 1  3  -1  -3  5 [3  6  7]      7


Therefore, return the max sliding window as [3,3,5,5,6,7].

Note: 
 You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.

Follow up:
 Could you solve it in linear time?

Hint:
1.How about using a data structure such as deque (double-ended queue)?
2.The queue size need not be the same as the window’s size.
3.Remove redundant elements and the queue should store only elements that need to be considered.

*/

/*****
293
1. 	像 209 Min Size Subarray Sum?
2.	朴素法 log(nk)
3.	deque用来存索引.num可用索引找到. 右尾.
		存索引[i-k+1, i], 其中 a[x] < a[i], x < i, 是多余的. 
				a[x]在范围, a[i]也在范围. a[i]是better选择.
			清除这些x, 要从后往前, 否则,最左不满足,后面的都没法清.
				里面降序.
4.  左边移除的时候要检查索引 q.peek() < i - k + 1.
5.  左查索引, 右查数. 消除a[i]只有靠索引越界.

411.
1. 双队 deque, 左-过期, 右-铲除添加
2. deque里放的是index.
3. res的大小. n - (k - 1)
4. 过期边界 i - x + 1 = k, x = i - k + 1
5. peek(), peekLast(). 要先检查 isEmpty().

*****/

public int[] maxSlidingWindow(int[] a, int k) {
	if (a == null || k <= 0) {
		return new int[0];
	}
	int n = a.length;
	int[] res = new int[n-k+1];
	int idx = 0;
	// store index
	Deque<Integer> q = new ArrayDeque<>();
	for (int i = 0; i < a.length; i++) {
	// remove numbers out of range k
		while (!q.isEmpty() && q.peek() < i - k + 1) {
			q.poll();
		}
		// remove smaller numbers in k range as they are useless
		// q is in descending order
		while (!q.isEmpty() && a[q.peekLast()] < a[i]) {
			q.pollLast();
		}
		// q contains index... r contains content
		q.offer(i);
		if (i >= k - 1) {// exclude the intial phase
			res[idx++] = a[q.peek()];
		}
	}
	return r;
}

/*
We scan the array from 0 to n-1, keep "promising" elements in the deque. The
algorithm is amortized O(n) as each element is put and polled once.
At each i, we keep "promising" elements, which are potentially max number in
window [i-(k-1),i] or any subsequent window. This means
1. If an element in the deque and it is out of i-(k-1), we discard them. We just need
to poll from the head, as we are using a deque and elements are ordered as the
sequence in the array
2. Now only those elements within [i-(k-1),i] are in the deque. We then discard
elements smaller than a[i] from the tail. This is because if a[x] <a[i] and x<i,
then a[x] has no chance to be the "max" in [i-(k-1),i], or any other subsequent
window: a[i] would always be a better candidate.
3. As a result elements in the deque are ordered in both sequence in array and
their value. At each step the head of the deque is the max element in [i-(k-1),i]
*/
