/*
229 Majority Element II

Given an integer array of size n, find all elements that appear more than ⌊ n/3 ⌋ times. The algorithm should run in linear time and in O(1) space.

Hint:
1.How many majority elements could it possibly have?


*/

/*
For those who aren't familiar with Boyer-Moore Majority Vote algorithm, I found a
great article (http://goo.gl/64Nams) that helps me to understand this fantastic
algorithm!! Please check it out!
The essential concepts is you keep a counter for the majority number X. If you find a
number Y that is not X, the current counter should deduce 1. The reason is that if
there is 5 X and 4 Y, there would be one (5-4) more X than Y. This could be
explained as "4 X being paired out by 4 Y".
And since the requirement is finding the majority for more than ceiling of [n/3], the
answer would be less than or equal to two numbers. So we can modify the algorithm
to maintain two counters for two majorities.*/

/*****
1. 最多两个超过floor（n/3）次。
2. 3个distinct数组成一组triple， ”最多“ n/3组， 最多有两个distinct数不能成组。三个distince会自动成一组。
   如果一个元素是majority， 它至少出现 n/3 + 1， 至少一个无法成组（如果某一个triple里无majority，majority会剩的更多）。
   所以如果存在majority， 一定会出现在不能成组的两个distinct之中。
3. 最后要验证这两个distinct中哪个重复次数大于n/3. 
4. cdd1，cdd2初始值任意。个数均为零。 cnt为零，表示cdd空缺。
*****/

public class Solution {
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if(nums == null || nums.length == 0) return list; 

        int cdd1 = 0, cdd2 = 1, cnt1 = 0, cnt2 = 0;
        for(int x : nums){
            if(x == cdd1) cnt1++;
            else if(x == cdd2) cnt2++;
            else if(cnt1 == 0){ //空位
                cdd1 = x;
                cnt1++;
            }
            else if(cnt2 == 0){
                cdd2 = x;
                cnt2++;
            }
            else{
                cnt1--;
                cnt2--; // (x, cdd1, cdd2)组成1 triple
            }
        }
        
        cnt1 = 0;
        cnt2 = 0;
        for(int x : nums){
            if(x == cdd1) cnt1++;
            else if(x == cdd2) cnt2++;
        }
        
        if(cnt1 > nums.length/3) list.add(cdd1);
        if(cnt2 > nums.length/3) list.add(cdd2);
        
        return list;
    }
}
			