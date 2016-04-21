/*
218 The Skyline Problem

https://leetcode.com/problems/the-skyline-problem/

A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance. Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A), write a program to output the skyline formed by these buildings collectively (Figure B).
 Buildings   Skyline Contour    
The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height. It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are perfect rectangles grounded on an absolutely flat surface at height 0.

For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12], [15 20 10], [19 24 8] ] .

The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ] that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline contour. 

For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].

Notes: 
• The number of buildings in any input list is guaranteed to be in the range [0, 10000].
• The input list is already sorted in ascending order by the left x position Li. 
• The output list must be sorted by the x position. 
• There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]

*/

/*****
1. 对于某一个边界点x，找到所有包含他的矩形[a,b),其中最高的矩形为词典skyline高度。 若h不同于前一点的高度， res.add((x, h)),
2. 

283
1. 看代码，目标 - 心领神会。
2. h1, 1队当前高度。 两个序列波形， 当前态取高。
3. 图想， 某队出一个新点，前一高度作废。
		h = max(h1,h2), 两队当前高度比。

411
0. base里转化坐标, list<int[]>, 注意get()[1]
1. 可以用queue, poll, addAll()
2. 按x的大小出队更新h1, h2. 同x同出.
3. merge时要判断h和get(last)[1]相同不.

*****/


public class Solution {
    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings.length == 0)
            return new LinkedList<int[]>();
        return recurSkyline(buildings, 0, buildings.length - 1);
    }

    private LinkedList<int[]> recurSkyline(int[][] buildings, int p, int q) {
        if (p < q) {
            int mid = p + (q - p) / 2;
                return merge(recurSkyline(buildings, p, mid),
                             recurSkyline(buildings, mid + 1, q));
        } else { // base 
            LinkedList<int[]> rs = new LinkedList<int[]>(); // （x, y)
            rs.add(new int[] { buildings[p][0], buildings[p][2] });
            rs.add(new int[] { buildings[p][1], 0 });
            return rs;
        }
    }

    private LinkedList<int[]> merge(LinkedList<int[]> l1, LinkedList<int[]> l2) {
        LinkedList<int[]> rs = new LinkedList<int[]>();
        int h1 = 0, h2 = 0; //list里的x还没到,只有h1,h2才能比.
        while (l1.size() > 0 && l2.size() > 0) {
            int x = 0, h = 0;
            if (l1.getFirst()[0] < l2.getFirst()[0]) {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                l1.removeFirst();
            } else if (l1.getFirst()[0] > l2.getFirst()[0]) {
                x = l2.getFirst()[0];
                h2 = l2.getFirst()[1];
                l2.removeFirst();
            } else {
                x = l1.getFirst()[0];
                h1 = l1.getFirst()[1];
                h2 = l2.getFirst()[1];
                l1.removeFirst();
                l2.removeFirst();
            }			
			
			h = Math.max(h1, h2); //和对方作比较
            if (rs.size() == 0 || h != rs.getLast()[1]) {
                rs.add(new int[] { x, h });
            }
        }
        rs.addAll(l1);
        rs.addAll(l2);
        return rs;
    }

	
	// arraylist
	public int shortest(String word1, String word2) {
		List<Integer> list1 = map.get(word1); // in ascengding order
		List<Integer> list2 = map.get(word2);
		int ret = Integer.MAX_VALUE;
		int i = 0, j = 0;
		while(i < list1.size() && j < list2.size()) {
			int index1 = list1.get(i), index2 = list2.get(j);
			if(index1 < index2) { //相当于h1 < h2
				ret = Math.min(ret, index2 - index1);
				i++;
			} else {
				ret = Math.min(ret, index1 - index2);
				j++;
			}
		}
		return ret;
	}
}
