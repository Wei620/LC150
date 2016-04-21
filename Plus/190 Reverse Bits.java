/*
190 Reverse Bits

Reverse bits of a given 32 bits unsigned integer.

For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), return 964176192 (represented in binary as 00111001011110000010100101000000).

Follow up:
 If this function is called many times, how would you optimize it? 

Related problem: Reverse Integer

*/

/* ****
1. BIT前后位置反转.
2. 累加前先左移, n取ith位累加. 取低位是需要>>>(不保留符号位). 
3. 多次调用,缓存byte反转的情况. 可用Byte[256],也可用map..

411
1. 不是数组,不能直接换位
2. 必须32位全作, 需要补零.
3. >>> 左移时,补零. 最高位不是符号位.
*****/

public int reverseBits(int n) {
	int result = 0;
	for (int i = 0; i < 32; i++) {
		result <<= 1;
		result += (n >>> i) & 1;
		//n >>>= 1; // CATCH: must do unsigned shift
		//if (i < 31) // CATCH: for last digit, don't shift!
		//	result <<= 1;
	}
	return result;
}
/*
How to optimize if this function is called multiple times? We can divide an int into 4
bytes, and reverse each byte then combine into an int. For each byte, we can use
cache to improve performance.*/

// cache
private final Map<Byte, Integer> cache = new HashMap<Byte, Integer>();

public int reverseBits(int n) {
	byte[] bytes = new byte[4];
	for (int i = 0; i < 4; i++){ // convert int into 4 bytes
		bytes[i] = (byte)((n >>> 8*i) & 0xFF);
	}
	int result = 0;
	for (int i = 0; i < 4; i++) {
		result += reverseByte(bytes[i]); // reverse per byte
		if (i < 3) result <<= 8;
	}
	return result;
}

private int reverseByte(byte b) {
	Integer value = cache.get(b); // first look up from cache
	if (value != null){
		return value;
	}
	
	value = 0;
	// reverse by bit
	for (int i = 0; i < 8; i++) {
		value += ((b >>> i) & 1);
		if (i < 7){
			value <<= 1;
		}
	}
	cache.put(b, value);
	return value;
}