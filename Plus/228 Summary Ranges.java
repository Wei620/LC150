/*
228 Summary Ranges

Given a sorted integer array without duplicates, return the summary of its ranges.

For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"]. 

*/

/* ****
1. while的i++很巧妙， 推出循环是， i是range的右边界。

411
1. 双指针. for-while转化 for-if-continue

*****/
//[E]
public class Solution {
    public List<String> summaryRanges(int[] nums) {
        List<String> summary = new ArrayList<>();
        for (int i = 0, j = 0; j < nums.length; ++j) {
            // check if j + 1 extends the range [nums[i], nums[j]]
            if (j + 1 < nums.length && nums[j + 1] == nums[j] + 1)
                continue;
            // put the range [nums[i], nums[j]] into the list
            if (i == j)
                summary.add(nums[i] + "");
            else
                summary.add(nums[i] + "->" + nums[j]);
            i = j + 1;
        }
        return summary;
    }
}

public List<String> summaryRanges(int[] nums) {
	List<String> list=new ArrayList();
	for(int i=0;i<nums.length;i++){
		int a=nums[i];
		while(i+1<nums.length&&(nums[i+1]-nums[i])==1){
			i++;
		}
		if(a!=nums[i]){
			list.add(a+"->"+nums[i]);
		}else{
			list.add(a+"");
		}
	}
	return list;
}