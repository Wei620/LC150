import java.util.*;

/**
 * Given n non-negative integers representing the histogram's bar height where
 * the width of each bar is 1, find the area of largest rectangle in the
 * histogram.
 *
 * Above is a histogram where width of each bar is 1, given height =
 * [2,1,5,6,2,3].
 *
 * The largest rectangle is shown in the shaded area, which has area = 10 unit.
 *
 * For example,
 * Given height = [2,1,5,6,2,3],
 * return 10.
 *
 * Tags: Array, Stack
 */
 
 
 /*
    http://www.geeksforgeeks.org/largest-rectangle-under-histogram/
    1) Create an empty stack.

    2) Start from first bar, and do following for every bar ‘hist[i]’ where ‘i’ varies from 0 to n-1.
        a) If stack is empty or hist[i] is higher than the bar at top of stack, then push ‘i’ to stack.
        b) If this bar is smaller than the top of stack, then keep removing the top of stack while top of the stack is greater. Let the removed bar be hist[tp]. Calculate area of rectangle with hist[tp] as smallest bar. For hist[tp], the ‘left index’ is previous (previous to tp) item in stack and ‘right index’ is ‘i’ (current index).

    3) If the stack is not empty, then one by one remove all bars from stack and do step 2.b for every removed bar.
*/
    class LargestRectangleInHist {
    public static void main(String[] args) {
        int[] height = { 2, 1, 5, 6, 2, 3 };
        int[] height2 = { 1, 2, 3, 4, 5, 6 };
        System.out.println(new LargestRectangleInHist().largestRectangleArea(height2));
    }

    /**
     * Only height is smaller do update happens
     * Stack for indices
     * add a zero height into the group
     */
    // pop - right hedge. 
	// s.peek + 1  - left hedge. poped already. 
    public int largestRectangleArea(int[] height) {
        if (height == null || height.length == 0) return 0;
        height = Arrays.copyOf(height, height.length + 1); // add a zero - for the last element.
        int max = 0;
        Stack<Integer> s = new Stack<Integer>(); // store indices
        for (int i = 0; i < height.length; i++) {
            while (!s.isEmpty() && height[i] < height[s.peek()]) { // update when current height is smaller
                int h = height[s.pop()];
                int w = (s.isEmpty() ? i : i - s.peek() - 1); 
                // s.peek() + 1: tp or the element prior to tp that has been poped out.
				max = Math.max(max, h * w);
            }
            s.push(i); // push index into stack
        }
        return max;
    }
}