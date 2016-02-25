/**
 * Given an array of non-negative integers, you are initially positioned at the
 * first index of the array.
 * 
 * Each element in the array represents your maximum jump length at that
 * position.
 * 
 * Your goal is to reach the furthestCurrStep index in the minimum number of jumps.
 * 
 * For example:
 * Given array A = [2,3,1,1,4]
 * 
 * The minimum number of jumps to reach the furthestCurrStep index is 2. (Jump 1 step from
 * index 0 to 1, then 3 steps to the furthestCurrStep index.)
 * 
 * Tags: Array, Greedy, DP
 */
class JumpGame2 {
    public static void main(String[] args) {
        
    }
    
    /**
     * Use furthestCurrStep to store how far we already can reach
     * Compare i with furthestCurrStep
     * If we run out of it, update and add 1 more step to result
     * Return if furthestCurrStep is already bigger than or equal to the length
     * Use furthestNextStep to store how far we can reach for the next step
     */
    public int jump(int[] A) {
        int step = 0;
        int furthestCurrStep = 0; // how far we already can reach
        int furthestNextStep = A[0]; // how far can we reach for next step
        
        for (int i = 1; i < A.length; i++) {
            if (i > furthestCurrStep) { // run out of we can reach, need one more step
                if(furthestNextStep > furthestCurrStep){
                    step++;
                    furthestCurrStep = furthestNextStep;
                    if (furthestCurrStep >= A.length - 1) return step;
                }
                else return -1; //cannot reach i
            }
            furthestNextStep = Math.max(furthestNextStep, i + A[i]);
        }
        return step;
    }
}
