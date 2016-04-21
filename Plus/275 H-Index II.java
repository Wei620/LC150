/*
275 H-Index II

Follow up for H-Index: What if the citations array is sorted in ascending order? Could you optimize your algorithm? 

Hint:
1.Expected runtime complexity is in O(log n) and the input is sorted.

*/

// citations[mid] is increasing function
// citations[mid] - (len-mid) strictly increasing function of mid.
// mid1 < mid2, citations[mid1] <= citations[mid2], mid1 - len < mid2 - len,
// citation[mid1] - (len-mid1) < citation[mid2] - (len-mid2) 

/*****
1. BS需构造严格单调增序列, citations[mid] - (len-mid)
2. 目标是多少? 
	citations已排序, 根据href定义, 当上式>=0, len-mid 是href
	mid越小, href越大. 上式 = 0 (目标), mid最小, href最大.
3. BS找不到(上式 = 0)时, 找 上式 > 0(target), return len - mid where mid = high + 1;	

293
1. 按定义 找 citation[i] >= len - i. I里是顺序枚举法。
2. citation[i] - (len - i)严格增， 209是可以处理一般增的。所以BS常规形式也可以。

411
1. 209开区间
2. target 0. 为什么用209形式, 因为可能找不到 == target的情况. "有2篇引用是4, 一篇引用是5, 一篇是2"
3. h指的是引用篇数. 根据定义 返 len - r.
		不能反 citi[] 1) 越界的情况 2) citi >= hIndex

*****/
//411
public class Solution {
    public int hIndex(int[] citations) {
        int len = citations.length, l = 0, r = len;
        while(l < r){
            int mid = l + (r - l)/2;
            if(citations[mid] - (len - mid) >= 0) r = mid;
            else l = mid + 1;
        }
        return len - r;
    }
}


//209 统一形式。
public int hIndex(int[] citations) {
	int left=0, len = citations.length, right= len-1, mid;
	while(left <= right)
	{
		mid = left + (right - left)/2;
		if(citations[mid] >= (len-mid)){
			right = mid - 1;
		}
		else{
			left = mid + 1;
		}
	}
	return len - (right+1); // len - mid
}

class Solution {
public int hIndex(int[] citations) {
	int left=0, len = citations.length, right= len-1, mid;
	while(left <= right)
	{
		mid = left + (right - left)/2;
		if(citations[mid]== (len-mid)){
			return len-mid; //original: citations[mid];
		}
		else if(citations[mid] > (len-mid)){
			right = mid - 1;
		}
		else{
			left = mid + 1;
		}
	}
	return len - (right+1); // len - mid
}
}