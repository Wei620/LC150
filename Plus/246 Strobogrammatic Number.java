/*
246 Strobogrammatic Number

A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example,

the numbers "69", "88", and "818" are all strobogrammatic.
*/

/*****
1.	hashmap 理论上快, char[10]更快. 默认 'x'
2.  010是不是?
*****/
//411 not tested.
public boolean isStrobogrammatic(String num) {
	char[] map = {0, 1, -1, -1, -1, -1, 9, -1, 8, 6};
	int l = 0, r = num.length() - 1;
	while(l <= r){
		if(map[num.charAt(l)] != num.charAt(r)){
			return false;
		}
	}
	return true;}



public class Solution {
    public boolean isStrobogrammatic(String num) {
		int l = 0, r = num.length() - 1;
		while(l <= r){
			if(!isValid(num.charAt(l),num.charAt(r))){
				return false;
			}
		}
        return true;
    }

    private boolean isValid(char c, char b) {
        switch (c) {
            case '1':
                return b == '1';
            case '6':
                return b == '9';
            case '9':
                return b == '6';
            case '8':
                return b == '8';
            case '0':
                return b == '0';
            default:
                return false;
        }
    }
}

public boolean isStrobogrammatic(String num) {
	for (int i=0, j=num.length()-1; i <= j; i++, j--)
		if (!"00 11 88 696".contains(num.charAt(i) + "" + num.charAt(j)))
			return false;
	return true;
}


