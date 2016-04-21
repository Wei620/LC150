/*
299 Bulls and Cows 

You are playing the following Bulls and Cows game with your friend: You write down a number and ask your friend to guess what the number is. Each time your friend makes a guess, you provide a hint that indicates how many digits in said guess match your secret number exactly in both digit and position (called "bulls") and how many digits match the secret number but locate in the wrong position (called "cows"). Your friend will use successive guesses and hints to eventually derive the secret number.

For example: 
Secret number:  "1807"
Friend's guess: "7810"

Hint: 1 bull and 3 cows. (The bull is 8, the cows are 0, 1 and 7.) 

Write a function to return a hint according to the secret number and friend's guess, use A to indicate the bulls and B to indicate the cows. In the above example, your function should return "1A3B". 

Please note that both secret number and friend's guess may contain duplicate digits, for example: 
Secret number:  "1123"
Friend's guess: "0111"

In this case, the 1st 1 in friend's guess is a bull, the 2nd or 3rd 1 is a cow, and your function should return "1A1B". 

You may assume that the secret number and your friend's guess only contain digits, and their lengths are always equal.

*/

/* -----
1. bull直接数
2. cow分别统计secret和guess里出现不匹配数字的个数. Sum(min(sarr[],garr[])) 最多全猜中,
	两个数组清晰. (preferred)
	一个数组, s正, g为-.
		++<0, 说明g本身多, 但多了一个s, 所以cow++
		-->0,    s               g,    cow++
411
1. 两遍扫描. 第一遍双统+数bull
2. 比较双统,累加cow
*****/

public class Solution {
	public String getHint(String secret, String guess) {
		int bull = 0, cow = 0;
		int[] sarr = new int[10];
		int[] garr = new int[10];
		for(int i = 0; i < secret.length(); i++){
			if(secret.charAt(i) != guess.charAt(i)){
				sarr[secret.charAt(i)-'0']++;
				garr[guess.charAt(i)-'0']++;
			}else{
				bull++;
			}
		}
		
		for(int i = 0; i <= 9; i++){
			cow += Math.min(sarr[i], garr[i]);
		}
		return (bull + "A" + cow + "B");
	}
}

public String getHint(String secret, String guess) {
	int bulls = 0;
	int cows = 0;
	int[] numbers = new int[10];
	for (int i = 0; i<secret.length(); i++) {
		if (secret.charAt(i) == guess.charAt(i)) bulls++;
		else {
			if (numbers[secret.charAt(i)-'0']++ < 0) cows++;
			if (numbers[guess.charAt(i)-'0']-- > 0) cows++;
		}
	}
	return bulls + "A" + cows + "B";
}