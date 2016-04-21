/*
281 Zigzag Iterator

Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
 

By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
。
*/

/*****
1. 只有两个, 如果j还没完, j换到i. 永远从i除.

411
1. 队列法, 队中有空指针, 但hasNext()保证队首的不是空.队空, hasNext 反false.
*****/

public class ZigzagIterator {
    private Iterator<Integer> iOut, iAlt;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        iOut = v2.iterator();
        iAlt = v1.iterator();
    }
    public int next() {
        if (iAlt.hasNext()) {
			Iterator<Integer> tmp = iAlt;
			iAlt = iOut;
			iOut = tmp;
		}
        return iOut.next();
    }
    public boolean hasNext() {
        return iOut.hasNext() || iAlt.hasNext();
    }
}

//Cyclic k list
public class ZigzagIterator {
    private Queue<Iterator<Integer>> q = new LinkedList<Iterator<Integer>>;
    
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        q.add(v1.iterator());
        q.add(v2.iterator());
    }
    
    public int next() {
		hasNext();
        if(!q.isEmpty()){
			Iterator<Integer> curr = q.poll;
			q.offer(curr);
			return curr.next();
        }
		return null;
    }
    
	// remove next null iterator
    public boolean hasNext() {
        while(!q.isEmpty()){
            Iterator<Integer> curr = q.peek();
            if(curr.hasNext()){
                return true;
            }else{
				q.poll();
			}    
        }
		return false;
    }
}