/*
315 Count of Smaller Numbers After Self

You are given an integer array nums and you have to return a new counts array. The counts array has the property where counts[i] is the number of smaller elements to the right of nums[i]. 

Example:
Given nums = [5, 2, 6, 1]

To the right of 5 there are 2 smaller elements (2 and 1).
To the right of 2 there is only 1 smaller element (1).
To the right of 6 there is 1 smaller element (1).
To the right of 1 there is 0 smaller element.


Return the array [2, 1, 1, 0]. 

*/

/*
The basic idea is to do merge sort to nums[]. To record the result, we need to keep the index of each number in the original array. So instead of sort the number in nums, we sort the indexes of each number. Example: nums = [5,2,6,1], indexes = [0,1,2,3] After sort: indexes = [3,1,0,2]

While doing the merge part, say that we are merging left[] and right[], left[] and right[] are already sorted.

We keep a rCnt to record how many numbers from right[] we have added and keep an array count[] to record the result.

When we move a number from right[] into the new sorted array, we increase rCnt by 1.

When we move a number from left[] into the new sorted array, we increase count[ index of the number ] by rCnt.

*/

/* -----
1. BIT 两个函数add（x, val), xth（1-based） increase by val 
			 sum(x)， presum include xth
				x如果用0-based标号（数组索引） excluded。
2. BIT 的数组里 Tree[0] 不用。 数组后移一个放入， add(i+1, nums[i])
	本题 统计个数 所以 add(nums[i]+1, 1)
3. 比我小， presum（统计值的个数） 统计值 抬升后最小0， 0-based， 所以统计值exclulded。

313
1. BIT cons, 限制idx的最大值. 本题,限制了nums里的最大值.
		因为是数组实现,费空间
2. BIT适合动态添加,动态求和.
		题 比我后面的. 所以由后向前扫, 动态添加.
3. 求和用于统计个数, 值作索引.
*****/

public class Solution {

    /*
    In this solution, we use a binary indexed tree (BIT)
    Our assumption is that all elements in nums are positive
    */

    static int MAX = 11000; //we set max value that can be store in the tree
    int[] tree = new int[MAX];

    public List<Integer> countSmaller(int[] nums) {
        Integer[] result = new Integer[nums.length];

        //make all elements in the array posive while maintaining their order
        makePositive(nums);

        for(int i=nums.length-1; i>=0; i--){
            result[i] = sum(nums[i]);
            add(nums[i]+1, 1); // can be move to the above.
        }
        return Arrays.asList(result);
    }
	
	// All nums[] >= 0.
    public void makePositive(int[] nums){
        int min = MAX;
        for(int i=0; i<nums.length; i++)    
            min = Math.min(min, nums[i]);
        if(min < 0){
            min = -min；//+1;
            for(int i=0; i<nums.length; i++)
                nums[i] += min;
        }
    }

    public void add(int idx, int val){
        while(idx<MAX){
            tree[idx] += val;
            idx += (idx & (-idx));
        }
    }

    public int sum(int idx){
        int result = 0;
        while(idx>0){
            result += tree[idx];
            //idx &= (idx-1);
			idx -= (idx & (-idx));
		}
        return result;
    }
}

int[] count;
public List<Integer> countSmaller(int[] nums) {
    List<Integer> res = new ArrayList<Integer>();     

    count = new int[nums.length];
    int[] indexes = new int[nums.length];
    for(int i = 0; i < nums.length; i++){
        indexes[i] = i;
    }
    mergesort(nums, indexes, 0, nums.length - 1);
    for(int i = 0; i < count.length; i++){
        res.add(count[i]);
    }
    return res;
}
private void mergesort(int[] nums, int[] indexes, int start, int end){
    if(end <= start){
        return;
    }
    int mid = (start + end) / 2;
    mergesort(nums, indexes, start, mid);
    mergesort(nums, indexes, mid + 1, end);

    merge(nums, indexes, start, end);
}
private void merge(int[] nums, int[] indexes, int start, int end){
    int mid = (start + end) / 2;
    int lIdx = start;
    int rIdx = mid+1;
    int rCnt = 0;    // the number of merged element which was in the right half part. 
    int[] tmp = new int[end - start + 1];

    int k = 0;
    while(lIdx <= mid && rIdx <= end){
        if(nums[indexes[rIdx]] < nums[indexes[lIdx]]){
            tmp[k] = indexes[rIdx];
            rCnt++;
            rIdx++;
        }else{ //nums[indexes[rIdx]] > nums[indexes[lIdx]]
            tmp[k] = indexes[lIdx];
            count[indexes[lIdx]] += rCnt;
            lIdx++;
        }
        k++;
    }
	
	// remaining
    while(lIdx <= mid){
        tmp[k] = indexes[lIdx];
        count[indexes[lIdx]] += rCnt;
        lIdx++;
        k++;
    }
    while(rIdx <= end){
        tmp[k++] = indexes[rIdx++];
    }
	
	// update index[start ... end]
    for(int i = start; i <= end; i++){
        indexes[i] = tmp[i - start];
    }
}