/*
266 Palindrome Permutation


Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:
Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
*/

// use a boolean[26] instead. A(65) + 32 = a(97)

/*****
1. 奇数次还是后数次，可以用boolean反转。
2. 用hashset也可以。不如boolean[26]高效。s.toLowerCase().
3. odd次数可同时统计， 见II odd += b[c - 'a'] == true? 1 : -1;

411
1. 有特殊字符。
2. oddCnt <= 1.
*****/
//411
public class Solution {
    public boolean canPermutePalindrome(String s) {
        char[] cArr = s.toCharArray();
        int[] freq = new int[128];
        int oddCnt = 0;
        for(char c : cArr){
            if(++freq[c] % 2 == 0) oddCnt--;
            else oddCnt++;
        }
        return oddCnt <= 1;
    }
}

public class Solution {
	public boolean canPermutePalindrome(String s) {
		Set<Character> set=new HashSet<Character>();
		for(int i=0; i<s.length(); ++i){
			if (!set.contains(s.charAt(i)))
				set.add(s.charAt(i));
			else
				set.remove(s.charAt(i));
		}
		return set.size()==0 || set.size()==1;
	}
}