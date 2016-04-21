/*
215 Kth Largest Element in an Array

Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, not the kth distinct element. 

For example,
 Given [3,2,1,5,6,4] and k = 2, return 5. 

Note: 
 You may assume k is always valid, 1 ≤ k ≤ array's length.

*/

/* ****
1. Kth largest element, 用 minHeap， 栈里最后剩的是k个最大元素， 栈顶就是kth最大
2. 默认PriorityQueue是minHeap. maxHeap需要写降序comparator. 或者给入PQ队前取负，出队再取负恢复。

411
1. 先offer, 再poll
2. maxheap, 可取反用minheap, 也可写comparator<Integer>. Int1.intValue()
*****/

//O(N lg K) running time + O(K) memory
public int findKthLargest(int[] nums, int k) {
	final PriorityQueue<Integer> pq = new PriorityQueue<>();
	for(int val : nums) {
		pq.offer(val);
		if(pq.size() > k) {
			pq.poll();
		}
	}
	return pq.peek();
}