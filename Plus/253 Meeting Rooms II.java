/*
253 Meeting Rooms II
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,

Given [[0, 30],[5, 10],[15, 20]],
return 2.
*/

/*****
1. 先把interval按start排序。
2. 遍历排序后的intervals， 看curr的start前是否有会议室空闲。start >= end
3. 会议室复用interval数据结构。 找最早结束的会议室(heap.poll())， 用end排序的heap。
		找到了，修改heap.poll.end,再放回heap。
		找不到，curr直接放回heap，相当于多了一个会议室。
4. heap.size().

293
1. conf一定要出PQ,否则修改的end,没法奏效.
   
*****/

public int minMeetingRooms(Interval[] intervals) {
	if (intervals == null || intervals.length == 0)
	return 0;
	// Sort the intervals by start time
	Arrays.sort(intervals, new Comparator<Interval>() {
		public int compare(Interval a, Interval b) {
			return a.start - b.start;
		}
	});
	// Use a min heap to track the minimum end time of merged intervals
	PriorityQueue<Interval> heap = new PriorityQueue<Interval>(intervals.length, new Comparator<Interval>() {
		public int compare(Interval a, Interval b) { return a.end - b.end; }
	});
	
	// start with the first meeting, put it to a meeting room
	heap.offer(intervals[0]);
	for (int i = 1; i < intervals.length; i++) {
		// get the meeting room that finishes earliest
		Interval conf = heap.poll();
		if (intervals[i].start >= conf.end) {
			// if the current meeting starts right after
			// there's no need for a new room, merge the interval
			conf.end = intervals[i].end;
		} else {
			// otherwise, this meeting needs a new room
			heap.offer(intervals[i]);
		}
		// don't forget to put the meeting room back
		heap.offer(interval);
	}
	return heap.size();
}