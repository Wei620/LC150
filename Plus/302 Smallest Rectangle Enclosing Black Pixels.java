/*
302 Smallest Rectangle Enclosing Black Pixels

Represented by an Image is a binary matrix with 0 as a white Pixel and 1 as a Black Pixel. The Black pixels are connected, ie, there is only one Black Region. Pixels are connected Horizontally and Vertically. Given the location (x, y) of one of the Black pixels, return the smallest of the Area (axis-aligned) rectangle that encloses All Black pixels.

For example, given the following image:

  [
   "0010",
   "0110",
   "0100"
 ]
and x = 0 , y = 2 ,

Return 6 .

Links: http://leetcode.com/problems/smallest-rectangle-enclosing-black-pixels/

Solution:

Find all the black pixel contains the smallest rectangle. Here we find with two points. Since a given black pixel point (x, y), and China Unicom are all black pixel to row search, for example, all the column containing black pixel, and mapped to the row x, must be continuous. So that we can use binary search, search inside 0 to y containing black pixel of the leftmost column. Then continue to search up and down and right border. And when the lower boundary of the search right boundary, in fact, we are looking for it is the first '0', so to pass a boolean variable searchLo to judge.

Time Complexity - O (mlogn + nlogm), Space Complexity - O (1)
*/

/*****
1. 不能投影法, O(mn)太大.
2. 二分扩散查找. 给出查找范围的同时, 还要指出扫描线的范围. 
3. 图形化想象. 还要指出向小or向大, "==findLB" 的用法. 只改变找到后的方向. 
4. 找边界,看前面的算法. 

313 用原解
1. 左找最小>0, 右找最小 <1 209开区间
2. (x,y)不用再找了
3. 000[1]11(1)11[0]00 拆分后,有单调性, 可以BS.
	左找 hi是找着的, 右找 hi是没找到的.
		BS. 左, 找到了朝左移
		    右, 没找到了朝左移

*****/

private char[][] image;

// Original
public int minArea(char[][] iImage, int x, int y) {
	image = iImage;
	int m = image.length, n = image[0].length;

	int left = searchColumns(0, y, 0, m, true);
	int right = searchColumns(y + 1, n, 0, m, false);
	int top = searchRows(0, x, left, right, true);
	int bottom = searchRows(x + 1, m, left, right, false);
	return (right - left) * (bottom - top);
}
//[lom hi) 209
private int searchColumns(int lo, int hi, int top, int bottom, boolean findLB) {
    while (lo != hi) {
        int k = top, mid = (lo + hi) / 2;
        while (k < bottom && image[k][mid] == '0') ++k; // 目标 313-3里1
		boolean isFound = k < bottom;
        if (isFound == findLB)
            hi = mid;
        else
            lo = mid + 1;
    }
    return hi; // hi == lo
}

private int searchRows(int lo, int hi, int left, int right, boolean findLB) {
    while (lo != hi) {
        int k = left, mid = (lo + hi) / 2;
        while (k < right && image[mid][k] == '0') ++k;
        if (k < right == findLB)
            hi = mid;
        else
            lo = mid + 1;
    }
    return hi;
}