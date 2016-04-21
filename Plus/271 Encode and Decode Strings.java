/*
271 Encode and Decode Strings

Design an algorithm to encode a list of strings to a string. The encoded string is then sent over the network and is decoded back to the original list of strings.

Machine 1 (sender) has the function:

string encode(vector<string> strs) {
  // ... your code
  return encoded_string;
}
Machine 2 (receiver) has the function:

vector<string> decode(string s) {
  //... your code
  return strs;
}
 

So Machine 1 does:

string encoded_string = encode(strs);
 

and Machine 2 does:

vector<string> strs2 = decode(encoded_string);
 

strs2 in Machine 2 should be the same as strs in Machine 1.

Implement the encode and decode methods.

Note:

The string may contain any possible characters out of 256 valid ascii characters. Your algorithm should be generalized enough to work on any possible characters.
Do not use class member/global/static variables to store states. Your encode and decode algorithms should be stateless.
Do not rely on any library method such as eval or serialize methods. You should implement your own encode/decode algorithm.
链接： http://leetcode.com/problems/encode-and-decode-strings/

题解：

encode and decode。这里我们可以维护一个StringBuilder，读出每个input string的长度，append一个特殊字符，例如'/'，再append string。这样再decode的时候我们就可以利用java的String.indexOf(char，startIndex)来算出自startIndex其第一个'/'的位置，同时计算出接下来读取的string长度，用String.substring()读出字符串以后我们更新index，来进行下一次读取。 
*/
// divide "/"
// ecode "str0str1"

/*****
1. 网络传输, 可分割. 插入"/"
2. 加密.密钥最好不断变化. 当前词(的长度)作密钥.  w0(key)/w0w1(key)/

293
1. decode里的while形式很好. 
2. 取/后面的部分做decode结果, 更新 i = slash + size + 1
3. 需要完整接收,才能decode. 

411
1. 找一个不出现在str里的char或string做分隔符。 加盐，否则太简单。
2. s/s编码， 原来是 size/s编码
3. i = slash + 1 + size;
*****/

public class Codec {
	// Encodes a list of strings to a single string.
	public String encode(List<String> strs) {
		StringBuilder sb = new StringBuilder();
		for(String s : strs) {
			//sb.append(s.length()).append('/').append(s);
			sb.append(s).append('/').append(s);
		}
		return sb.toString();
	}
	// Decodes a single string to a list of strings.
	public List<String> decode(String s) {
		List<String> ret = new ArrayList<String>();
		int i = 0;
		while(i < s.length()) {
			int slash = s.indexOf('/', i);
			int size = slash - i;//Integer.valueOf(s.substring(i, slash));
			//ret.add(s.substring(slash + 1, slash + size + 1));
			ret.add(s.substring(i, slash));
			i = slash + size + 1;
		}
		return ret;
	}
}