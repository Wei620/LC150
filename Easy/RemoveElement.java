/**
 * Given an array and a value, remove all instances of that value in place and 
 * return the new length.
 * 
 * The order of elements can be changed. It doesn't matter what you leave 
 * beyond the new length.
 * 
 * Tags: Array, Two pointers
 */
class RemoveElement {
    public static void main(String[] args) {
        RemoveElement r = new RemoveElement();
        // int[] A = { 1 };
        // int[] A = { 1, 2, 3, 4 };
        int[] A = { 1, 2, 1 };
        int elem = 1;
        System.out.println(r.removeElement(A, elem));
    }
	
	/*****
	1. 顺序不变的写法。
	2. 如果顺序可变， 把最后元素填充值elem to be removo。 这样改动的较少。
	*****/
	
	//顺序不变
	public int removeElement(int[] A, int elem) {
		if(A == null) return 0;
		int len = 0;
		for(int num : A){
			if(num != elem){
				A[len++] = num;
			}
		}
		return len;
	}
    
    /**
     * Order is not important
     * Just move the last elem to removed position
     */
    public int removeElement(int[] A, int elem) {
        if (A == null || A.length == 0) return 0;
        int i = 0;
        int len = A.length;
        while (i < len) {
            if (A[i] == elem) {
                A[i] = A[len - 1]; // move last element
                len--; // decrease length
            } else i++; // move on
        }
        return len;	//new length
    }
}
