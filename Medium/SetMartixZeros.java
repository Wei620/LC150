/**
 * Given a m x n matrix, if an element is 0, set its entire row and column to
 * 0. Do it in place.
 *
 * Tags: Array
 */
class SetMartixZeros {

    public static void main(String[] args) {

    }

    /**
     * Go through the matrix and use first row and first col to remember which
     * cols and rows are to be sets
     * Use two flags for whether first row and first col should be set
     */
	 
	/* ****
	1. 	一行一类被占用，引两个变量表示。
	2. 两遍扫描，顺序相反。
	3. 第二遍时， 四个或条件（j==0, i==0在先）， 查询归零。
	*****/
    public static void setZeros(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        boolean firstRow = false;
        boolean firstCol = false;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                    if (i == 0) firstRow = true;
                    if (j == 0) firstCol = true;
                }
            }
        }
        
        // The order gurantees that matrix[0][i] or matrix[j][0] can be overriden. 
        for(int i = row - 1; i >= 0; i--){
            for(int j = col -1; j >= 0; j--){
                boolean isZero = (i == 0 && firstRow) || (j == 0 && firstCol) ||
                                    matrix[0][j] == 0 || matrix[i][0] == 0; 
                if(isZero){
                    matrix[i][j] = 0;
                }
            }
            //if(firstCol) matrix[i][0] = 0;
        }
        /*
        for (int i = row - 1; i > 0; i--) {
            if (matrix[i][0] == 0) {
                for (int j = col - 1; j > 0; j--) {
                    matrix[i][j] = 0;
                }
            }
        }
        for (int j = col - 1; j > 0; j--) {
            if (matrix[0][j] == 0) {
                for (int k = row - 1; k > 0; k--) {
                    matrix[k][j] = 0;
                }
            }
        }

        if (firstRow) {
            for (int j = 0; j < col; j++) {
                matrix[0][j] = 0;
            }
        }
        if (firstCol) {
            for (int i = 0; i < row; i++) {
                matrix[i][0] = 0;
            }
        }*/
    }
}
