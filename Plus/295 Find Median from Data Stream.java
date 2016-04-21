/*
295 Find Median from Data Stream

Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
Examples: 
[2,3,4] , the median is 3
[2,3], the median is (2 + 3) / 2 = 2.5
Design a data structure that supports the following two operations:
void addNum(int num) - Add a integer number from the data stream to the data structure.
double findMedian() - Return the median of all elements so far.
For example:
add(1)
add(2)
findMedian() -> 1.5
add(3) 
findMedian() -> 2

Understand the problem:
Use two heaps. The maxHeap stores the number which is less than the current number. The minHeap stores the number which is greter than the current number. 
We also need to keep the two heaps balanced in size. 

For the method findMedian(), we need to check if the two heaps have the same size. If yes, there must be even number of elements so far, so the median is the average of the top of the minHeap and the maxHeap. If not, i.e. odd number of elements so far, the median is the top of the heap which one more element. 
*/

/*****
###
1. 一个minheap存mid以上的部分就足够, 压入maxheap的不会再有用.
		//My logic
		private int size, mid;
		public void addNum(int num) {
			large.offer(num);
			size++;
			while(large.size > size/2){
				mid = large.poll();
			}
		}
		public double findMedian() {
			return size%2==0? (mid+large.peek())/2.0 : mid;
		}
	错! 来的stream不是sorted,出栈后就不能再用了.
2. 双栈法. 有交换.
	进一个large,从large里出一个到small. (l to s)
	small比large长, 从small里选一个进large (s to l)
	确保 0 <= lsize - ssize <= 1; 奇数个mid是l.peek, 偶数个mid是l.peek 和 r.peek的均值.

303
1. 注意findMedian,如何确定奇偶数.
*****/

class MedianFinder {
    private Queue<Integer> small = new PriorityQueue(new Comparator<Integer>(){public int compare(int i, int j){return j - i;}});
    private Queue<Integer> large = new PriorityQueue();
	
    public void addNum(int num) {
        large.offer(num); // how to maintain the balance?
        small.offer(large.poll());
        if (large.size() < small.size()) // median = large.top(), must >=
            large.offer(small.poll());
    }
	
    public double findMedian() {
        return large.size() > small.size()? large.peek():(large.peek() + small.peek()) / 2.0;
    }
};
/*
I keep two heaps (or priority queues):
    1)(Max-heap)small has the smaller half of the numbers.
    2)(Min-heap)large has the larger half of the numbers.
    3) large.size() == small.size() + 0 or 1
This gives me direct access to the one or two middle values (they're the tops of the
heaps), so getting the median takes O(1) time. And adding a number takes O(log n)
time.
*/

class MedianFinder {
    private Queue<Long> small = new PriorityQueue(),
        large = new PriorityQueue();
    public void addNum(int num) {
        large.add((long) num);
        small.add(-large.poll());
        if (large.size() < small.size())
            large.add(-small.poll());
    }
    public double findMedian() {
        return large.size() > small.size()? large.peek():(large.peek() - small.peek()) / 2.0;
    }
};

/*
Simplify the maintainess of small(max-heap). Put -num to small, so -top is the largest in the smaller part.
*/

