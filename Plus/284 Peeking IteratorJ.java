/*
284 Peeking Iterator

Given an Iterator class interface with methods: next() and hasNext(), design and implement a PeekingIterator that support the peek() operation -- it essentially peek() at the element that will be returned by the next call to next().

Here is an example. Assume that the iterator is initialized to the beginning of the list: [1, 2, 3].

Call next() gets you 1, the first element in the list.

Now you call peek() and it returns 2, the next element. Calling next() after that still return 2.

You call next() the final time and it returns 3, the last element. Calling hasNext() after that should return false.

Hint:

Think of "looking ahead". You want to cache the next element.
Is one variable sufficient? Why or why not?
Test your design with call order of peek() before next() vs next() before peek().
For a clean implementation, check out Google's guava library source code.
*/

/*****
1. 用一个缓存(next), hasNext, peek, next都用peek. next 还要记得更新peek.
2. 初始化时注意next的条件赋值.

303
1. 初始化, next()里赋初值给next.
*****/

//cache the next element. If next is null, there is no more elements in iterator.
class PeekingIterator implements Iterator<Integer> {
    private Integer next = null;
    private Iterator<Integer> iter;
    public PeekingIterator(Iterator<Integer> iterator) {
        // initialize any member here.
        iter = iterator;
		/*
        if (iter.hasNext())
			next = iter.next();
		*/
		next();
    }
    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }
    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        Integer res = next;
        next = iter.hasNext() ? iter.next() : null;
        return res;
    }
    
    @Override
    public boolean hasNext() {
        return next != null;
    }
}