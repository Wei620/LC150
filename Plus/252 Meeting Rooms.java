/*
252 Meeting Rooms

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,

Given [[0, 30],[5, 10],[15, 20]],
return false.
*/

/*****
1. 指完整参加，所以任何两个会的时间不能重复。
2. 按开始时间进行排序。

238
1. arrays排序同样可以用comparator.

*****/
public boolean canAttendMeetings(Interval[] intervals) {
	if (intervals == null)
		return false;
	// Sort the intervals by start time
	Arrays.sort(intervals, new Comparator<Interval>() {
		public int compare(Interval a, Interval b) { return a.start - b.start; }
	});
	
	for (int i = 1; i < intervals.length; i++)
		if (intervals[i].start < intervals[i - 1].end)
			return false;
	return true;
}