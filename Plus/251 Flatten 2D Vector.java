/*
251 Flatten 2D Vector
Implement an iterator to flatten a 2d vector.

For example,

Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].
*/

/* ****
1. 只初始化外层iterator, 内层初始化和赋值都在hasNext里进行, 边界条件少.

293
1. 返j.hasNext()前,查j非空
2. 调next()前,已经hasNext() == true, j必然非空,有next().

411
1. 两级Iterator分别怎么写.
2. 仅初始化外层 iterI.
3. hasNext查内层 iterJ
	iterI.next()可能指空list.
*****/

public class Vector2D {
	private Iterator<List<Integer>> i;
	private Iterator<Integer> j;
	
	public Vector2D(List<List<Integer>> vec2d) {
		i = vec2d.iterator();
	}
	
	public int next() {
		hasNext();
		return j.next();
	}
	public boolean hasNext() {
		while ((j == null || !j.hasNext()) && i.hasNext())
			j = i.next().iterator(); // i.next may be empty
		return j != null && j.hasNext();
	}
}