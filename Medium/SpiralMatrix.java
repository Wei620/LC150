import java.util.*;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of
 * the matrix in spiral order.
 * 
 * For example,
 * Given the following matrix:
 * 
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * You should return [1,2,3,6,9,8,7,4,5].
 * 
 * Tags: Array
 */
class SpiralMatrix {
    public static void main(String[] args) {
        
    }
	/* ****
	1. 和II不同，中心处理是或关系。
	*****/
    
    /**
     * Remember which level it is right now
     * Do level by level till reach center
     */
    public ArrayList<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0) return res;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        for(int lv = 0; 2 * lv < m && 2 * lv < n; lv++) { // note 2 * level
			int iMin = lv, iMax = m - 1 - lv;
			int jMin = lv, jMax = n - 1 - lv;//(iMin, jMin), (iMax, jMax)

            for (int j = jMin; j < jMax; j++) res.add(matrix[iMin][j]); 
            for (int i = iMin; i < iMax; i++) res.add(matrix[i][jMax]);   
            
            if (iMin == iMax || jMin ==  jMax){
                res.add(matrix[iMax][jMax]);
                break;
            } 

            for (int j = jMax; i > jMin; j--) res.add(matrix[iMax][j]);
            for (int i = iMax; i > iMin; i--) res.add(matrix[i][jMin]);
        }
        return res;
    }
    
    /**
     * Use rMin, rMax, cMin, cMax, to store the boundries
     * Use i, j to track the position
     * Move i, j around to add elements
     * Break whenever out of bounds to avoid duplicate traversal
     */
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<Integer>();
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) return res;
        int iMin = 0;
        int iMax = matrix.length - 1;
        int jMin = 0;
        int jMax = matrix[0].length - 1;
        int i = 0;
        int j = 0;
        // update boundry as soon as we traverse through it
        while (iMin <= iMax && jMin <= jMax) {
            for (j = jMin; j <= jMax; j++) res.add(matrix[iMin][j]);
            iMin++; if(iMin > iMax) break; // break as soon as possible
            for (i = iMin; i <= iMax; i++) res.add(matrix[i][jMax]);
            jMax--; if(jMax < jMin) break;
            for (j = jMax; j >= jMin; j--) res.add(matrix[iMax][j]);
            iMax--; if(iMax < iMin) break;
            for (i = iMax; i >= iMin; i--) res.add(matrix[i][jMin]);
            jMin++;
        }
        return res;
    }
}
