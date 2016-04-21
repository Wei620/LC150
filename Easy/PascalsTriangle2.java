import java.util.ArrayList;
import java.util.List;

/**
 * Given an index k, return the kth row of the Pascal's triangle.
 * 
 * For example, given k = 3,
 * Return [1,3,3,1].
 * 
 * Note:
 * Could you optimize your algorithm to use only O(k) extra space?
 * 
 * Tags: Array
 */
class PascalsTriangle2 {

    public static void main(String[] args) {
        PascalsTriangle2 p = new PascalsTriangle2();
        int k = 3;
        System.out.println(p.getRow(k).toString());
    }
	
	/*****
	1. k是0based
	2. 先加1到首行， k = 0
	3. i = 1 to k. 行内 j = i - 1 to 1, i和0都是1
	*****/
    
    /**
     * Generate in-place within a list
     * 0, 0, 0, 0, initialized, 1, 0, 0, 0
     * i = 1, 1, 1, 0, 0
     * i = 2, 1, 2, 1, 0
     * i = 3, 1, 3, 3, 1
     */
    public List<Integer> getRow(int k) {
        List<Integer> row = new ArrayList<Integer>(k + 1);
        row.add(1);//k is 0-based 行首1
        for (int i = 1; i <= k; i++) { // repeat k times
            for (int j = i - 1; j >= 1; j--) { // do it backwards
                row.set(j, row.get(j - 1) + row.get(j));
            }
            row.add(1); // add 1 at the end 行尾1
        }
        return row;
    }
}
