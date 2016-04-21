/*
278	First Bad Version

You are a product manager and currently leading a team to develop a new product. Unfortunately, the latest version of your product fails the quality check. Since each version is developed based on the previous version, all the versions after a bad version are also bad.

Suppose you have n versions [1, 2, ..., n] and you want to find out the first bad one, which causes all the following ones to be bad.

You are given an API bool isBadVersion(version) which will return whether version is bad. Implement a function to find the first bad version. You should minimize the number of calls to the API.

Credits:
*/

/* ****
1. 209 闭区间, 左找程序.
2. 解法, 开区间右找? end 端移动是mid?
		左找,动end.
		右找,动start

411
1. 开区间闭区间, 本质区别 while(), r, return, 
2. 已知最后一个是坏的, 所以可用开区间.
*****/
// 411 开区间
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while(l < r){
            int mid = l + (r - l)/2;
            if(isBadVersion(mid)) r = mid;
            else l = mid + 1;
        }
        return r;
    }
}


//411 闭区间
public class Solution extends VersionControl {
    public int firstBadVersion(int n) {
        int l = 1, r = n;
        while(l <= r){
            int mid = l + (r - l)/2;
            if(isBadVersion(mid)) r = mid - 1;
            else l = mid + 1;
        }
        return r + 1;
    }
}

public int firstBadVersion(int n) {
    int start = 1, end = n;
    while (start < end) {
        int mid = start + (end-start) / 2;
        if (!isBadVersion(mid)) start = mid + 1;
        else end = mid;
    }
    return start;
}

//209
public int firstBadVersion(int n) {
    int start = 1, end = n - 1;
    while (start <= end) {
        int mid = start + (end-start) / 2;
        if (isBadVersion(mid)) end = mid - 1;
        else end = mid + 1;
    }
    return end+1;
}