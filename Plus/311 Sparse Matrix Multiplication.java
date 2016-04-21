/*
311 Sparse Matrix Multiplication

Given two sparse matrices A and B, return the result of AB.

You may assume that A's column number is equal to B's row number.

Example:

A = [
  [ 1, 0, 0],
  [-1, 0, 3]
]

B = [
  [ 7, 0, 0 ],
  [ 0, 0, 0 ],
  [ 0, 0, 1 ]
]


     |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
                  | 0 0 1 |
*/

/*****
1. 图形化想象。 把非零A[i][k]一次运算完， *B[k][j]逐列累加更新 C[i][j], j in [0,nB-1]

303
1. 记住如何写矩阵乘
2. 若（i，j,val)给出， 用j找b的row， c[i][k] +=val* b[j][k] k = 0,..., nb-1

411
1. 非零乘累加
*****/

public class Solution {
	public int[][] multiply(int[][] A, int[][] B) {
		int m = A.length, n = A[0].length, nB = B[0].length;
		int[][] C = new int[m][nB];
		for(int i = 0; i < m; i++) {
			for(int k = 0; k < n; k++) {
				if (A[i][k] != 0) {
					for (int j = 0; j < nB; j++) {
						if (B[k][j] != 0) C[i][j] += A[i][k] * B[k][j];
					}
				}
			}
		}
		return C;
	}
}

public int[][] multiply(int[][] A, int[][] B) {
	int m = A.length, n = A[0].length, nB = B[0].length;
	int[][] result = new int[m][nB];
	List[] indexA = new List[m];
	for(int i = 0; i < m; i++) {
		List<Integer> numsA = new ArrayList<>();
		for(int j = 0; j < n; j++) {
			if(A[i][j] != 0){
				numsA.add(j);
				numsA.add(A[i][j]);
			}
		}
		indexA[i] = numsA;
	}
	for(int i = 0; i < m; i++) {
		List<Integer> numsA = indexA[i];
		for(int p = 0; p < numsA.size() - 1; p += 2) { // skipped 0-element
			int colA = numsA.get(p);
			int valA = numsA.get(p + 1);
			for(int j = 0; j < nB; j ++) {
				int valB = B[colA][j]; // use colA
				result[i][j] += valA * valB;
			}
		}
	}
	return result;
}