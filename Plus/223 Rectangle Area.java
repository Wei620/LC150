/*
223 Rectangle Area
https://leetcode.com/problems/rectangle-area/

Find the total area covered by two rectilinear rectangles in a 2D plane.

Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.

Rectangle Area 


Assume that the total area is never beyond the maximum possible value of int.

*/

/* ****
411
1. 最优解.投影法, 注意right, top的解法.
*****/

// (A,B) left-bottom, (C, D) right-top
public class Solution {
    int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {
    	int left = Math.max(A,E), right = Math.max(Math.min(C,G), left);
    	int bottom = Math.max(B,F), top = Math.max(Math.min(D,H), bottom);
    	return (C-A)*(D-B) + (G-E)*(H-F)  - (right-left)*(top-bottom);
    }
}

/*
Instead of checking whether the rectangles overlap, I max right with left (and
top with bottom ). Haven't seen that in other solutions.*/