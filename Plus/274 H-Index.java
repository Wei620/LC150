/*
274 H-Index

Given an array of citations (each citation is a non-negative integer) of a researcher, write a function to compute the researcher's h-index. 

According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her N papers have at least h citations each, and the other N − h papers have no more than h citations each." 

For example, given citations = [3, 0, 6, 1, 5], which means the researcher has 5 papers in total and each of them had received 3, 0, 6, 1, 5 citations respectively. Since the researcher has 3 papers with at least 3 citations each and the remaining two with no more than 3 citations each, his h-index is 3. 

Note: If there are several possible values for h, the maximum one is taken as the h-index. 

Hint:
1.An easy approach is to sort the array first.
2.What are the possible values of h-index?
3.A faster approach is to use extra space.

*/

// 1. hmax = len
// 2. track backword.

/* ****
1. 一篇文章可以被引用0,1,2, ....., n次, 统计被引用i次的篇数.
2. 从后往前扫(因为要找比较大的href), 根据定义验证 i是不是href.
	count[i] + .... + count[n] >= i
3. len >= count[i] + .... + count[n], 即 i <= len.
   空间减至 count[1+len], 引用次数>len的都放count[len]. 图形化想象.
   
 293
1. h 至少有h篇引用超过h次
2. 法1，统计。看引用8次以上的，有没有8篇。 引用次数由高到低，求和枚举。引用的最大统计，为文章篇数。
   法2 已排序。h（len-i）尽量大，所以i从0开始枚举。a[i]是a[]最小， a[i] >= len -i 即可。
   
411.
1. 离散序列的值域统计.
2. 离散化, 值域做x轴.避免x过大, 可以部分压缩. 
		本题, 原序列长度. 
3. 找最大满足 sum(count[i], ... count[end]) > i
   i从后往前扫
*****/
public int hIndex(int[] citations) {
	int len = citations.length;
	int[] count = new int[len + 1];
	for (int c: citations)
		if (c >= len)
			count[len]++;
		else
			count[c]++;
		
		int total = 0;
		for (int i = len; i >= 0; i--) {
			total += count[i];
		if (total >= i)
			return i;
	}
	return 0;
}

// For a better solution for sorted citation, see II
public int hIndex(int[] citations) {
    Arrays.sort(citations);
    int len=citations.length;
    for(int i=0;i<len;i++){ // i 越小， h越大
        if(citations[i]>=len-i) return len-i;
    }
    return 0;
}