/*
267 Palindrome Permutation

Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
*/

/* ****
1. 复合题.
2. 字频统计,提早淘汰 奇数次>1. 边统计边计算基数词.
3. 标出mid(奇数次字母), 取剩下的一半字母, for(j = 0; j < map[i]/2; j++)
4. 传统排列, base的时候, list + mid + 反list.
5. 排列的备选组无法排序,所以只能往前查找已swapped的重复元素.

293
1. permutation, in-place swap,临时结果就是原数组.

411
1. Permutation swap-skip 原理。
2. ArrayList<Character> 不如 str.toCharArray().
*****/

public List<String> generatePalindromes(String s) {	
	// step 1. build character count map and count odds
	int odd = 0;
	int[] map = new int[26];
	for (int i = 0; i < s.length(); i++) {
		char c = s.charAt(i);
		int idx = c - 'a';
		map[idx] += 1;
		odd += map[idx] % 2 != 0 ? 1 : -1;
	}	
	// cannot form any palindromic string
	if (odd > 1) return res;
	
	// step 2. add half count of each character to cStr
	String mid;
	String cStr = "";
	for(int i = 0; i < map.length; i++){
		char c = 'a' + i;
		if(map[i] %2 == 1){ //NOTE： map[i] == 3 is ok。
			mid = c; //only one
		}
		for(int j = 0; j < map[i]/2; i++){
			cStr += c;
		}
	}

	// step 3. generate all the permutations
	List<String> res = new ArrayList<>();
	getPerm(cStr.toCharArray(), 0, res, mid);
	return res;
}

// generate all unique permutation from list
void getPerm(char[] list, int pos, List<String> res, String mid) {
	if (pos == list.length) {
		// form the palindromic string
		String str = new String(list) + mid;
		for(int i = list.length - 1; i >= 0; i--){
			str += list[i];
		}
		res.add(str);
		return;
	}
	
	// pos swap with [pos, end], any elemnt within the range can be positioned at pos.
	for (int i = pos; i < list.size(); i++) {
		// avoid duplication
		boolean skipped = false;
		for(int j = pos; j < i; j++){
			if(list[i] == list[j]){
				skipped = true;
				break;
			}
		}
		if(skipped) continue;

		swap(list, i, pos);
		// recursion
		getPerm(list, pos + 1, res, mid);
			// backtracking
		swap(list, i, pos);
	}
}