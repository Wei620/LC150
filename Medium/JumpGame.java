/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Determine if you are able to reach the last index.
 * 
 * For example:
 * A = [2,3,1,1,4], return true.
 * 
 * A = [3,2,1,0,4], return false.
 * 
 * Tags: Array, Greedy, DP
 */
class JumpGame {
    public static void main(String[] args) {
        JumpGame j = new JumpGame();
        int[] A = {3, 2, 1, 0, 4};
        int[] B = {2, 3, 1, 1, 4};
        int[] C = {0};
        int[] D = {2, 5, 0, 0};
        System.out.println(j.canJump(A));
        System.out.println(j.canJump(B));
        System.out.println(j.canJump(C));
        System.out.println(j.canJump(D));
    }
	
	public boolean canJamp(int[] A){
		if (A == null) return false;
		int maxStep = Integer.MIN_VALUE;
		for(int i = 0; i < A.length, i++){
			maxStep = Math.max(maxStep, i + A[i]);
			if(maxStep > A.length - 1){
				return true;
			}
		}
		return false;
	}
	
	// dp[i] represents whether we can reach the ith step (0-based)
	// dp[i] = max(j + A[j]) >= i, where 0 <= j < i && dp[j] == true
	public boolean canJamp(int[] A){
		if (A == null || A.length == 0) return false;
		int currMax = 0;
		for(int i = 0; i < A.length; i++){
			if(currMax >= i){
				currMax = Math.max(currMax, i + A[i]);
			}
		}
		return currMax >= A.length;
	}
	
	// JumpGame II
	public int canJump(int[] A){
		if (A == null) return -1;
		int currMaxStep = -1, nextMaxStep = 0, step = -1;
		for(int i = 0; i < A.length; i++){
			if(i > currMaxStep){
				if(nextMaxStep > currMaxStep){
					step++;
					currMaxStep = nextMaxStep;
					if(currMaxStep > A.length - 1){
						return step;
					}	
				}
			}
			nextMaxStep = Math.max(nextMaxStep, i + A[i]);
		}
		return step;
	}
	
	// dp[i] the maxReachableIdx after ith Step
	// dp[i+1] = max(j + A[j]), where dp[i - 1] + 1 <= j <= dp[i]

	// dp[0] = A[0]
	public int canJump(int[] A){
		if (A == null) return -1;
		int dp_prev = -1, dp = 0;
		for(int i = 0; i < A.length; i++){
			int tmp = dp;
			for(int j = dp_prev + 1; j <= dp; j++){
				dp = Math.max(dp, j + A[j]);
			}
			if(dp > A.Length - 1) return i+1;
			dp_prev = tmp;
		}
		return -1
	}
	
	
    
    /**
     * Dynamic Programming
     * Keep track of the maximum of jumps we left
     * Initialized as A[0]
     * Traverse from second to second last
     * Reduce 1 every time we jump
     * maxJump should be max of maxJump - 1 and A[i]
     * if maxJump reduces to zero, we are not able to reach anymore
     */
    public boolean canJump(int[] A) {
        if (A == null || A.length == 0) return false;
        if (A.length == 1) return true; // already reach last index
        if (A[0] == 0) return false; // note its important cause we start from 1
        int maxJump = A[0];
        for (int i = 1; i < A.length - 1; i++) {
            maxJump = Math.max(maxJump - 1, A[i]);  //how to deplete maxJump, A[i] = 0, i = m,... n
            if (maxJump == 0) return false;
        }
        return true;
    }
}
